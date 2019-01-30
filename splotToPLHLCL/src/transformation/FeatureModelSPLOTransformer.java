package transformation;

/* 
 * Conjunto de librerias importadas necesarias para el correcto funcionamiento de
 *
 *  la aplicacion. 
 * 
 * */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.xml.transform.TransformerException;

import DTO.TransformerInDTO;
import constants.ConstraintSymbolsConstant;
import constants.GNUPrologConstraintSymbolsConstant;
import constants.PLHLCLConstant;
import constants.SWIPrologConstraintSymbolsConstant;
import constants.TransformerConstants;
import constraints.BooleanVariable;
import constraints.PropositionalFormula;
import enumerations.NotationType;
import fm.FeatureGroup;
import fm.FeatureModel;
import fm.FeatureModelException;
import fm.FeatureTreeNode;
import fm.RootNode;
import fm.SolitaireFeature;
import fm.XMLFeatureModel;
import utils.FileUtils;

/**
 * La Clase TransformationManager es la encargada de realizar las respectivas
 * transformaciones de los modelos de un formalismo base a GNUProlog-SWI Prolog
 * 
 * @author Juliana Jaramillo Echeverri, Jose Ignacio Lopez Velez
 * @version 1.7.0, 10/01/2011
 * @author Luisa Rincon
 * @Version 2.0 Abril/2013
 * @author Angela Villota
 * @Version 3.0 Abril/2018
 */
public class FeatureModelSPLOTransformer implements PLHLCLConstant, TransformerConstants{

	private StringBuilder groups;
	private StringBuilder mandatories;
	private StringBuilder optional;
	private StringBuilder orRelations;
	private String allFeaturesString;
	private String allDomainString;
	private Long constraintCounter;
	private String modelName="";

	// Los elementos comunes que son diferentes segun el editor
	private String header;
	private String endConstraintProgramFile;

	private List<String> variabilityElementList;
	private NotationType notationType;

	private FeatureModelTransformerRules transformerRules;

	private void init(TransformerInDTO inDTO) {

		notationType = inDTO.getNotationType();
		mandatories = new StringBuilder();
		optional = new StringBuilder();
		orRelations = new StringBuilder();
		groups = new StringBuilder();
		allFeaturesString = allDomainString = "";
		constraintCounter = 0L;
		variabilityElementList = new ArrayList<String>();
		transformerRules = new FeatureModelTransformerRules(notationType);

		if (notationType.equals(NotationType.GNU_PROLOG)) {
			header = GNUPrologConstraintSymbolsConstant.HEADER;
			endConstraintProgramFile = GNUPrologConstraintSymbolsConstant.END;
		}
		else 
			if(notationType.equals(NotationType.SWI_PROLOG)) {
			header = SWIPrologConstraintSymbolsConstant.HEADER;
			endConstraintProgramFile = SWIPrologConstraintSymbolsConstant.END;
			allDomainString = ("L ins 0..1,\n");
			}else {
				header= PLHLCLConstant.HEADER;
				endConstraintProgramFile = PLHLCLConstant.END;
			}
	}

	/**
	 * Lee el xml para identificar las caracteristicas raices
	 * 
	 * @param node
	 * @param tab
	 * @param plFile
	 */
	private void traverseDFSGPL(FeatureTreeNode node) {
		// Se agrega a la lista de caracteristicas, la actual (se pone su
		// primera letra en mayuscula, se intercambian los espacios por _ y
		// se reemplazan los simbolos de + y - por Plus y Minus,
		// respectivamente)
		String featureName = evaluarPrimerCaracter(node.getName().charAt(0))
				+ node.getName().trim().substring(1).replaceAll(" ", "_")
						.replaceAll("\\-", "Minus").replaceAll("\\+", "Plus")
						.replaceAll("\\.", "dot").replaceAll("/", "");
		String constraintText = "";
		// Se consultan los elementos principales de la taxonomia de la
		// ontologia

		// Caracteristica raiz
		if (node instanceof RootNode) {
			modelName=featureName;
			
			if (notationType.equals(NotationType.PLHLCL)) {
				header+=SPACE + featureName + LF;
				transformerRules.setModelName(featureName);
				
			}else {
				variabilityElementList.add(featureName);
				// Para la raiz es #=1
				constraintText = transformerRules.getAssignRule(TransformerConstants.ONE, featureName);
				mandatories.append(createConstraintForFile(constraintText));
				
			}

			

		} else {

			FeatureTreeNode parent = (FeatureTreeNode) node.getParent();

			// Se agrega la caracteristica hallada a la variable que
			// almacena las restricciones que representan opcionalidad
			String parentFeatureName = evaluarPrimerCaracter(parent.getName()
					.charAt(0))
					+ parent.getName().trim().substring(1).replaceAll(" ", "_")
							.replaceAll("\\-", "Minus")
							.replaceAll("\\+", "Plus").replaceAll("\\.", "dot")
							.replaceAll("/", "");

			// Para los elementos del modelo con excepcion de las featureGroup
			// se crea la caracteristica asociada como una instancia de la
			// ontologia
			if (!(node instanceof FeatureGroup)) {

				// Se crea la caracteristica opcional en el modelo
				variabilityElementList.add(featureName);

			}
			if (node instanceof SolitaireFeature) {
				// Caracteristica opcional
				if (((SolitaireFeature) node).isOptional()) {

					// Se adiciona la depencencia opcional al modelo de
					// variabilidad
					constraintText = transformerRules
							.getOptionalRule(
							
									parentFeatureName, 
									featureName);
					optional.append(createConstraintForFile(constraintText));

				}
				// Caracteristica obligatoria
				else {

					// Se adiciona la depencencia opcional al modelo de
					// variabilidad
					constraintText = transformerRules
							.getMandatoryRule(
							
									parentFeatureName,
							
									featureName);
					mandatories.append(createConstraintForFile(constraintText));

				}

			}
			// Grupo de caracteristicas
			else if (node instanceof FeatureGroup) {

				List<String> childFeaturesList = new ArrayList<String>();

				// Se obtiene el valor menor y mayor de cardinalidad
				Long minCardinality = new Long(
						String.valueOf(((FeatureGroup) node).getMin()));
				Long maxCardinality = new Long(
						String.valueOf(((FeatureGroup) node).getMax()));
				String constraintText2 = "";
				StringBuilder groupSet = new StringBuilder();

				// El -1 significa el * en la notacion de splot,por lo que se
				// cuenta la cantidad maxima posible segun la cantidad de hijos
				// q tenga el nodo
				if (maxCardinality == -1) {
					maxCardinality = (long) node.getChildCount();
				}

				// Se recorre los hijos de la cardinalidad para construir la
				// restriccion
				Enumeration<FeatureTreeNode> childrenNodes = node.children();
				while (childrenNodes.hasMoreElements()) {
					FeatureTreeNode childNode = childrenNodes.nextElement();
					String childFeatureName = evaluarPrimerCaracter(childNode
							.getName().charAt(0))
							+ childNode.getName().trim().substring(1)
									.replaceAll(" ", "_")
									.replaceAll("\\-", "Minus")
									.replaceAll("\\+", "Plus")
									.replaceAll("\\.", "dot")
									.replaceAll("/", "");
					childFeaturesList.add(childFeatureName);
					groupSet.append(childFeatureName);
					// Si hay m�s elementos se adiciona una coma adicional
					if (childrenNodes.hasMoreElements()) {
						groupSet.append(ConstraintSymbolsConstant.PLUS);
					}
				}

				if (minCardinality.equals(maxCardinality)) {
					if (notationType.equals(NotationType.PLHLCL)) {
						constraintText = transformerRules
								.getGroupalDependencyRule(
										parentFeatureName, 
										childFeaturesList, 
										minCardinality.toString(),
										maxCardinality.toString() );
					}else {
						constraintText = transformerRules
								.getGroupalDependencyRule3()
								.replace(TransformerConstants.FEATURE_1,
										parentFeatureName)
								.replace(TransformerConstants.FEATURES_SET,
										groupSet);
					}
				} else {
					// Para el caso en el que diga cuanto es y no un *
					if (notationType.equals(NotationType.PLHLCL)) {
						constraintText = transformerRules
								.getGroupalDependencyRule(
										parentFeatureName, 
										childFeaturesList, 
										minCardinality.toString(),
										maxCardinality.toString() );
					}else {
						constraintText = transformerRules
								.getGroupalDependencyRule1()
								.replace(TransformerConstants.FEATURE_1,
										parentFeatureName)
								.replace(TransformerConstants.FEATURES_SET,
										groupSet)
								.replace(TransformerConstants.M,
										minCardinality.toString());
						constraintText2 = transformerRules
								.getGroupalDependencyRule2()
								.replace(TransformerConstants.FEATURE_1,
										parentFeatureName)
								.replace(TransformerConstants.FEATURES_SET,
										groupSet)
								.replace(TransformerConstants.N,
										maxCardinality.toString());
					}
				}

				// Se adicional la constraint
				groups.append(createConstraintForFile(constraintText));
				// Si se crearon dos restricciones se crea otro objeto de
				// restriccion
				if (!constraintText2.isEmpty()) {
					groups.append(createConstraintForFile(constraintText2));
				}

			}

		}
		// en esta parte se llama al mismo metodo con cada hijo del nodo
		// actual,
		// de manera recursiva
		for (int i = 0; i < node.getChildCount(); i++) {
			traverseDFSGPL((FeatureTreeNode) node.getChildAt(i));
		}
	}

	/**
	 * Adiciona las restricciones de inclusion y exclusion a la ontologia, segun
	 * lo expresado en el modelo
	 * 
	 * @param featureModel
	 */
	private void traverseConstraintsGPL(FeatureModel featureModel) {

		String constraintText = "";
		StringBuilder constraintFeaturesSet = null;

		for (PropositionalFormula formula : featureModel.getConstraints()) {
			constraintFeaturesSet = new StringBuilder();
			Iterator<BooleanVariable> iter = formula.getVariables().iterator();
			BooleanVariable element;
			List<String> positives= new ArrayList<String>();
			List<String> negatives= new ArrayList<String>();

			while (iter.hasNext()) {
				element = (BooleanVariable) iter.next();
				FeatureTreeNode node = featureModel
						.getNodeByID(element.getID());
				String featureName = evaluarPrimerCaracter(node.getName()
						.charAt(0))
						+ node.getName().trim().substring(1)
								.replaceAll(" ", "_")
								.replaceAll("\\-", "Minus")
								.replaceAll("\\+", "Plus")
								.replaceAll("\\.", "dot").replaceAll("/", "");
				if (element.isPositive()) {
					constraintFeaturesSet.append(featureName);
					positives.add(featureName);
				} else {
					constraintFeaturesSet.append(transformerRules
							.getNegativePropostionalElementRule()
							.replace(TransformerConstants.FEATURE_1,
									featureName));
					negatives.add(featureName);
				}

				// Si hay un siguiente elemento se adiciona un +
				if (iter.hasNext()) {
					constraintFeaturesSet
							.append(ConstraintSymbolsConstant.PLUS);

				}
			}

			if (constraintFeaturesSet.length() > 0) {
				if (notationType.equals(NotationType.PLHLCL)) {
					constraintText = transformerRules
							.getPropositionalConstraintsRulePLHLCL(positives, negatives);
				}else {
					constraintText = transformerRules
							.getPropositionalConstraintsRule().replace(
									TransformerConstants.FEATURES_SET,
									constraintFeaturesSet.toString());
				}
				
				orRelations.append(createConstraintForFile(constraintText));
				constraintCounter++;
			}
		}
	}

	/**
	 * Para archivos creados con el generador de betty, se ajusta el : de la
	 * caracteristica Root, y se la asigna un nombre para que pueda ser
	 * analizado
	 * 
	 * @param filePathInput
	 * @param fileInputName
	 * @param FilePathOutput
	 * @throws IOException
	 */
	public void fixBettyModel(String filePathInput, String xmlFileName,
			String filePathOutput) throws IOException {
		FileInputStream fstream = new FileInputStream(filePathInput);
		// Get the object of DataInputStream
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		StringBuilder striBuilder = new StringBuilder();

		FileWriter fstreamWriter = new FileWriter(filePathOutput);
		BufferedWriter out = new BufferedWriter(fstreamWriter);

		// Read File Line By Line
		while ((strLine = br.readLine()) != null) {
			// Se quita el prefijo que adicina Jena cuando guarda el OWL
			striBuilder.setLength(0);
			if (strLine.contains("r:")) {
				strLine = strLine.replaceAll("r:", ":r");
			}
			if (strLine.contains("name=\"\"")) {
				strLine = strLine.replaceAll("name=\"\"", "name=\""
						+ xmlFileName + "\"");
			}

			striBuilder.append(strLine);
			striBuilder.append("\n");
			out.write(striBuilder.toString());

		}

		out.close();
		// Se borra el anterior archivo creado
		new File(filePathInput).delete();

	}

	private String evaluarPrimerCaracter(char caracterInicial) {
		if ((caracterInicial >= 65 && caracterInicial <= 90)
				|| (caracterInicial >= 97 && caracterInicial <= 122))
			return String.valueOf(caracterInicial).toUpperCase();
		return "N".concat(String.valueOf(caracterInicial));
	}

	/**
	 * Controla todo el proceso de transformaci�n
	 * @param inDTO
	 * @throws TransformerException
	 */
	public void transform(TransformerInDTO inDTO) throws TransformerException {

		init(inDTO);
		try {
			// Se lee el modelo de caracteristicas, usando la libreria que
			// provee
			// SPLOT
			FeatureModel featureModel = new XMLFeatureModel(
					inDTO.getInputPath(),
					XMLFeatureModel.USE_VARIABLE_NAME_AS_ID);
			featureModel.loadModel();

			// Restricciones Mandatory, optionales, grupales
			traverseDFSGPL(featureModel.getRoot());
			//Restricciones requires, excludes
			traverseConstraintsGPL(featureModel);
			StringBuilder constraintProgramContent = new StringBuilder();
			if (notationType.equals(NotationType.PLHLCL)) {
				String varsDeclaration= transformerRules
						.declareVariables(variabilityElementList);
				constraintProgramContent.append(header);
				constraintProgramContent.append(VARIABLES);
				constraintProgramContent.append(LF);
				constraintProgramContent.append(varsDeclaration);
				constraintProgramContent.append(LF);
				constraintProgramContent.append(CONSTRAINTS);
				constraintProgramContent.append(mandatories);
				constraintProgramContent.append(optional);
				constraintProgramContent.append(orRelations);
				constraintProgramContent.append(groups);
				constraintProgramContent.append(endConstraintProgramFile);
				
			}else {
				// Se contruye la lista de caracteristicas y de dominios
				StringBuilder featuresList = new StringBuilder("L=[");
				StringBuilder domainsList = new StringBuilder("fd_domain([");
				for (String variabilityElement : variabilityElementList) {
					featuresList.append(variabilityElement);
					featuresList.append(ConstraintSymbolsConstant.COMMA);
					//Para GNU prolog se requiere una instruccion diferente
					if (notationType.equals(NotationType.GNU_PROLOG)) {
						domainsList = domainsList.append(variabilityElement);
						domainsList.append(ConstraintSymbolsConstant.COMMA);
					}
				}
				featuresList.append("],");

				allFeaturesString = featuresList.toString().replace(",],", "],\n");
				if (notationType.equals(NotationType.GNU_PROLOG)) {
					domainsList.append("],");
					allDomainString = domainsList.toString().replace(",],",
							"], 0, 1),\n");
				}
				constraintProgramContent.append(header);
				constraintProgramContent.append(allFeaturesString);
				constraintProgramContent.append(allDomainString);
				constraintProgramContent.append(mandatories);
				constraintProgramContent.append(optional);
				constraintProgramContent.append(orRelations);
				constraintProgramContent.append(groups);
				constraintProgramContent.append(endConstraintProgramFile);
				
			}

			
			
			// Se escribe el archivo
			FileUtils.writePrologProgram(inDTO.getOutputPath(),
					constraintProgramContent.toString());
			System.out.println("Conversion complete");

		} catch (FeatureModelException e) {
			throw new TransformerException(e);

		}

	}

	/**
	 * Adiciona salto de linea y coma al final de cada restriccion
	 * 
	 * @param constraintText
	 * @return
	 */
	private String createConstraintForFile(String constraintText) {
		if (notationType.equals(NotationType.PLHLCL)) {
			return constraintText.concat(ConstraintSymbolsConstant.LF);
		}else {
			return constraintText.concat(ConstraintSymbolsConstant.COMMA).concat(
					ConstraintSymbolsConstant.LF);
		}
		
	}
}
