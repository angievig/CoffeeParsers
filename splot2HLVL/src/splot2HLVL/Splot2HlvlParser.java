package splot2HLVL;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import constraints.BooleanVariable;

import constraints.PropositionalFormula;
import fm.FeatureGroup;
import fm.FeatureModel;
import fm.FeatureTreeNode;
import fm.RootNode;
import fm.SolitaireFeature;
import fm.XMLFeatureModel;
import utils.FileUtils;
import utils.ParsingParameters;
import basicHLVLPackage.DecompositionType;
import basicHLVLPackage.GroupType;
import basicHLVLPackage.HlvlBasicFactory;
import basicHLVLPackage.HlvlBasicKeys;
import basicHLVLPackage.IHlvlParser;

/**
 * This class parses splot models to HLVL.
 * implements IHlvlParser, HlvlBasicKeys
 * We used the template provided by the splot site.
 * @author Angela Villota
 * @version coffee V1
 * Jan 2019
 */

public class Splot2HlvlParser implements IHlvlParser, HlvlBasicKeys{
	/**
	 * params is an object with the parsing parameters
	 */
	public ParsingParameters params;
	
	/**
	 * StringBuilder containing the hlvl program
	 */
	public StringBuilder hlvlProgram;
	/**
	 * String builder for the elements' declarations
	 */
	public StringBuilder elements;
	/**
	 * string builder for the relations declarations
	 */
	public StringBuilder relations;
	
	/**
	 * factory implementing the transformation patterns
	 */
	public HlvlBasicFactory factory;
	


	/**
	 * constructor
	 * @param params
	 */
	public Splot2HlvlParser(ParsingParameters params) {
		this.params = params;
		hlvlProgram= new StringBuilder();
		elements= new StringBuilder();
		relations= new StringBuilder();
		factory= new HlvlBasicFactory();
	}

	public void parse() throws Exception{
		

			/* Creates the Feature Model Object
			 * ********************************
			 * - Constant USE_VARIABLE_NAME_AS_ID indicates that if an ID has not been defined for a feature node
			 *   in the XML file the feature name should be used as the ID. 
			 * - Constant SET_ID_AUTOMATICALLY can be used to let the system create an unique ID for feature nodes 
			 *   without an ID specification
			 *   Note: if an ID is specified for a feature node in the XML file it will always prevail
			 */			
			FeatureModel featureModel = new XMLFeatureModel(params.getInputPath(), XMLFeatureModel.USE_VARIABLE_NAME_AS_ID);
		
			// Load the XML file and creates the feature model
			featureModel.loadModel();
			
			//traversing the feature tree for obtaining the feature and the hierarchical dependencies
			traverseDFS(featureModel.getRoot(), 0);
			
			
			traverseConstraints(featureModel);	
			
			// formating the output file
			hlvlProgram.append(MODEL_LABEL);
			hlvlProgram.append(params.getTargetName());
			hlvlProgram.append("\n");
			//including the elements
			hlvlProgram.append(ELEMENTS_LABEL);
			hlvlProgram.append(elements.toString());
			//including the relations
			hlvlProgram.append(RELATIONS_LABEL);
			hlvlProgram.append(relations.toString());
			//including the basic operations
			hlvlProgram.append(OPERATIONS_LABEL);
			hlvlProgram.append(VALID_MODEL);
			hlvlProgram.append(COMMA);
			hlvlProgram.append(NUM_CONF);
			writeFile();
		
	}
		
	private void traverseDFS(FeatureTreeNode node, int tab) {

		// Root Feature
		if ( node instanceof RootNode ) {
			//obtaining a valid name for HLVL
			String identifier= getValidName(node.getName());
			// including the root to the elements
			elements.append("\t"+ factory.getElement(identifier));
			// including the root to the relations
			relations.append("\t"+ factory.getCore(identifier));

		}
		// Solitaire Feature
		else if ( node instanceof SolitaireFeature ) {
			// getting the name  parent of the feature
			String parentName=  getValidName(((FeatureTreeNode) node.getParent()).getName());
			
			// name of the feature
			String featureName= getValidName(node.getName());
			// including the feature to the elements
			elements.append("\t"+ factory.getElement(featureName));
			
			// Optional Feature
			if ( ((SolitaireFeature)node).isOptional()) {
				// including the optional to relations
				relations.append(
						"\t"+ 
						factory.getDecomposition(parentName, featureName, DecompositionType.Optional));
			}
			// Mandatory Feature
			else {
				// including the mandatory to the relations
				relations.append(
						"\t"+ 
						factory.getDecomposition(parentName, featureName, DecompositionType.Mandatory));
			}
			
		}
		// Feature Group
		else if ( node instanceof FeatureGroup ) {
			// getting the parent of the group
			String parentName=  getValidName(((FeatureTreeNode) node.getParent()).getName());
			//TODO comment the min
			
			int maxCardinality = ((FeatureGroup)node).getMax();
			
			// obtaining a list of the children's identifiers
			ArrayList<String> children= new ArrayList<String>();
			Enumeration<FeatureTreeNode> childrenNodes = node.children();
			while (childrenNodes.hasMoreElements()) {
				FeatureTreeNode childNode = childrenNodes.nextElement();
				String childFeatureName = getValidName(childNode.getName());
				children.add(childFeatureName);
				// including the child feature to the elements
				elements.append("\t"+ factory.getElement(childFeatureName));
			}
			
			// Including the Group relations
			// OR relation
			if (maxCardinality==-1) {
				relations.append(
						"\t"+ 
						factory.getGroup(parentName, children, GroupType.Or));
			}
			// Alternative relation
			else {
				relations.append(
						"\t"+ 
						factory.getGroup(parentName, children, GroupType.Alternative));
			}
		}

		//recursive call for each child
		for( int i = 0 ; i < node.getChildCount() ; i++ ) {
			traverseDFS((FeatureTreeNode )node.getChildAt(i), tab+1);
		}
	}
	
	/**
	 * 
	 * @param featureModel
	 */
	private void traverseConstraints(FeatureModel featureModel) {
		
		// for each constrainnt represented as a formula in CNF we call the parseCNF2expression method
		// in the factory
		for( PropositionalFormula formula : featureModel.getConstraints() ) {
			
			// we traverse the set of variables to determine which are negatives and positives
			List<String> positives= new ArrayList<String>();
			List<String> negatives= new ArrayList<String>();
			for(BooleanVariable var: formula.getVariables()) {
				// obtaining the variable's name
				String varName= getValidName(
						featureModel
						.getNodeByID(var.getID()).getName());
				
				if (var.isPositive()) {
					positives.add(varName);
				}
				else {
					negatives.add(varName);
				}
			}
			//adding the CNF to the relations
			relations.append(
					"\t"+ 
					factory.parseCNF2expression(positives, negatives));
		
		}
	}
	
	private void writeFile() {
		FileUtils.writeHLVLProgram(params.getOutputPath(),
				hlvlProgram.toString());
		System.out.println("Conversion complete");
	}
	
	
	public String getValidName(String name) {
		return name.replaceAll(" ", "_")
				.replaceAll("\\-", "Minus")
				.replaceAll("\\+", "Plus")
				.replaceAll("\\.", "dot").replaceAll("/", "");
	}
	/*--------------------------------------------------------------------------------------------
	 * Getters and setters
	 */
	public ParsingParameters getParams() {
		return params;
	}

	public void setParams(ParsingParameters params) {
		this.params = params;
	}
	
	public String getProgram() {
		return hlvlProgram.toString();
	}



}
