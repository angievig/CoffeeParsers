package xmlToHLVLParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import basicHLVLPackage.DecompositionType;
import basicHLVLPackage.GroupType;
import basicHLVLPackage.HlvlBasicFactory;

import basicHLVLPackage.IHlvlParser;
import basicHLVLPackage.IhlvlBasicFactory;
import utils.FileUtils;
import utils.ParsingParameters;

/**
 * Esta es una clase que es responsable de extraer informaci�n del objeto del
 * elemento XML y del objeto de dependencia XML y convertir esa informaci�n en
 * el c�digo HLVL.
 * 
 * @version 0.5, 19/01/2019
 * @author Joan David Colina Echeverry
 */
public class VariamosXMLToHlvlParser implements IHlvlParser {

	/**
	 * @param ArrayList<Dependecy>: ArrayList with Dependecy objects
	 */
	private ArrayList<Dependecy> importantXmlDependecy;

	/**
	 * @param HashMap<String, Element>: HashMapwith Element objects
	 */
	private HashMap<String, Element> xmlElements;

	/**
	 * @param xmlReader: relationship with the XmlReader class that fulfills the
	 *        function of loading the XML file
	 */
	private XmlReader xmlReader;

	/**
	 * @param params: relationship with the ParsingParameters class that fulfills
	 *        the function of save the pahts to load de xml fiel and write de HLVL
	 *        code fiel
	 */
	private ParsingParameters params;

	/**
	 * @param HlvlCode: relationship with the StringBuilder class that fulfills the
	 *        function of save the HLVL code
	 */
	private StringBuilder HlvlCode;
	/**
	 * @param converter: relationship with the HlvlBasicFactory class that fulfills
	 *        the function of create HLVL code
	 */
	private IhlvlBasicFactory converter;

	/**
	 * this method is responsible for create a VariamosXMLToHlvlParser objet and
	 * inicializate HlvlCode parameter.
	 * 
	 * @param ParsingParameters: params that contain paths necesary to load xml fiel
	 *        and save HLVL fiel.
	 */
	public VariamosXMLToHlvlParser(ParsingParameters params) {
		HlvlCode = new StringBuilder();
		this.params = params;
	}

	/**
	 * this method is responsible for create a HLVL code's fiel .hlvl
	 */
	public void writeFile() {
		FileUtils.writeHLVLProgram(params.getOutputPath(), HlvlCode.toString());
		System.out.println("Conversion complete");
	}

	/**
	 * this method is responsible for load the XML fiel and inicializate the
	 * ArrayList
	 */
	public void loadArrayLists() {
		xmlReader = new XmlReader();
		converter = new HlvlBasicFactory();
		xmlReader.loadXmlFiel(params.getInputPath());
		importantXmlDependecy = xmlReader.getImportantXmlDependecy();
		xmlElements = xmlReader.getXmlElements();

	}

	/**
	 * this method is responsible for create descomposition, implies and mutex HLVL
	 * code
	 */
	public void converterXmlDependecyToHLVLCode() {
		HlvlCode.append(converter.getRelationsLab());
		converterGroupAndCore();
		for (int i = 0; i < importantXmlDependecy.size(); i++) {
			String target = getValidName(searchForName(importantXmlDependecy.get(i).getTarget()));
			String source = getValidName(searchForName(importantXmlDependecy.get(i).getSource()));
			String caso = importantXmlDependecy.get(i).getRelType();
			switch (caso) {
			case "mandatory":
				HlvlCode.append("	" + converter.getDecomposition(target, source, DecompositionType.Mandatory));
				break;
			case "optional":
				HlvlCode.append("	" + converter.getDecomposition(target, source, DecompositionType.Optional));
				break;
			case "requires":
				HlvlCode.append("	" + converter.getImplies(target, source));
				break;
			case "excludes":
				HlvlCode.append("	" + converter.getMutex(target, source));
				break;

			}
		}
	}

	/**
	 * this method is responsible for searh into the ArrayList<Element> the XML
	 * Element's name having the XML Element's id
	 * 
	 * @param id: string that represent the XML Element's id.
	 * @return Strign that represent the XML Element's name.
	 */
	public String searchForName(String id) {
		return xmlElements.get(id).getName();
	}

	/**
	 * this method is responsible for create boolean HLVL code
	 */
	public void converterXmlElementToHLVLCode() {

		for (Entry<String, Element> entry : xmlElements.entrySet()) {
			String name = getValidName(entry.getValue().getName());
			HlvlCode.append("	" + converter.getElement(name));
		}

	}

	/**
	 * this method is responsible for create coreElements and group HLVL code
	 */
	public void converterGroupAndCore() {

		for (Entry<String, Element> entry : xmlElements.entrySet()) {
			String name = getValidName(entry.getValue().getName());
			String caso = entry.getValue().getType();
			switch (caso) {
			case "root":
				HlvlCode.append("	" + converter.getCore(name));
				break;
			case "bundle":
				if (entry.getValue().getBundleType().endsWith("OR"))
					HlvlCode.append("	" + converter.getGroup(findRootBundle(entry.getValue()),
							findGroupsElements(entry.getValue()), GroupType.Or));
				else
					HlvlCode.append("	" + converter.getGroup(findRootBundle(entry.getValue()),
							findGroupsElements(entry.getValue()), GroupType.Alternative));
				break;
			}

		}

	}

	/**
	 * this method is responsible for searh into the ArrayList<Element> the element
	 * that groups the other elements
	 * 
	 * @param element: Element that represent a XML bundle.
	 * @return Strign that represent the XML Element's name.
	 */
	public String findRootBundle(Element element) {
		String name = "";
		String id = element.getId();
		for (int i = 0; i < importantXmlDependecy.size(); i++) {
			if (id.equals(importantXmlDependecy.get(i).getSource())) {
				name = searchForName(importantXmlDependecy.get(i).getTarget());
				break;
			}
		}
		name = getValidName(name);
		return name;
	}

	/**
	 * this method is responsible for searh into the ArrayList<Element> all elements
	 * grouped by the element bundel
	 * 
	 * @param element: Element that represent a XML bundle.
	 * @return ArrayList<String> that contain name of all XML Element.
	 */
	public ArrayList<String> findGroupsElements(Element element) {

		ArrayList<String> result = new ArrayList<String>();
		String id = element.getId();
		for (int i = 0; i < importantXmlDependecy.size(); i++) {
			if (id.equals(importantXmlDependecy.get(i).getTarget())) {
				String name = getValidName(searchForName(importantXmlDependecy.get(i).getSource()));
				result.add(name);
			}
		}
		return result;
	}

	/**
	 * this method is responsible for ensure the format of the item name
	 * 
	 * @param name: name of any Element.
	 * @return String: name in valid format
	 */
	@Override
	public String getValidName(String name) {
		return name.replaceAll(" ", "_").replaceAll("\\-", "Minus").replaceAll("\\+", "Plus").replaceAll("\\.", "dot")
				.replaceAll("/", "");
	}

	/**
	 * this method is responsible create de HLVL code and to persist the HLVL code
	 * 
	 */
	@Override
	public void parse() throws Exception {
		HlvlCode.append(converter.getHeader("basicFeatureModel"));
		converterXmlElementToHLVLCode();
		converterXmlDependecyToHLVLCode();
		writeFile();
	}
}
