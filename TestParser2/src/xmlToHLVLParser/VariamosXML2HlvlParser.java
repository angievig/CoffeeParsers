package xmlToHLVLParser;

import java.util.ArrayList;

import basicHLVLPackage.DecompositionType;
import basicHLVLPackage.GroupType;
import basicHLVLPackage.HlvlBasicFactory;
import basicHLVLPackage.HlvlBasicKeys;
import basicHLVLPackage.IHlvlParser;
import basicHLVLPackage.IhlvlBasicFactory;
import utils.FileUtils;
import utils.ParsingParameters;

/**
 * Esta es una clase que es responsable de extraer informaci�n del objeto del
 * elemento XML y del objeto de dependencia XML y convertir esa informaci�n en
 * el c�digo HLVL.
 * 
 * @version 1.5, 19/01/2019
 * @author Joan David Colina Echeverry
 */
public class VariamosXML2HlvlParser implements IHlvlParser, HlvlBasicKeys {

	private ArrayList<Dependecy> importantXmlDependecy;

	private ArrayList<Element> importantXmlElement;

	private XmlReader xmlReader;

	private ParsingParameters params;

	private StringBuilder codee;

	public VariamosXML2HlvlParser(ParsingParameters params) {

		codee = new StringBuilder();
		this.params = params;
	}

	public void writeFile() {

		FileUtils.writeHLVLProgram(params.getOutputPath(), codee.toString());
		System.out.println("Conversion complete");
	}

	/**
	 * this method is responsible for load the XML fiel and inicializate the
	 * ArrayList
	 * 
	 * @param path: string that represent the XML source to load.
	 */
	public void loadArrayLists() {
		xmlReader = new XmlReader();
		xmlReader.loadXmlFiel(params.getInputPath());
		importantXmlDependecy = xmlReader.getImportantXmlDependecy();
		importantXmlElement = xmlReader.getImportantXmlElement();
	}

	/**
	 * this method is responsible for become the Array's Dependecy to HLVL code
	 * 
	 * @return ArrayList<CharSequence> that contain all HLVL code lines
	 */
	public void converterXmlDependecyToHLVLCode() {
		IhlvlBasicFactory converter = new HlvlBasicFactory();
		for (int i = 0; i < importantXmlDependecy.size(); i++) {
			String target = getValidName(searchForName(importantXmlDependecy.get(i).getTarget()));
			String source = getValidName(searchForName(importantXmlDependecy.get(i).getSource()));
			String caso = importantXmlDependecy.get(i).getRelType();
			switch (caso) {
			case "mandatory":
				codee.append(converter.getDecomposition(target, source, DecompositionType.Mandatory));
				break;
			case "optional":
				codee.append(converter.getDecomposition(target, source, DecompositionType.Optional));

				break;
			case "requires":
				codee.append(converter.getImplies(target, source));

				break;
			case "excludes":
				codee.append(converter.getMutex(target, source));
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
		String result = "";
		for (int i = 0; i < importantXmlElement.size(); i++) {
			if (importantXmlElement.get(i).getId().equals(id)) {
				result = (String) importantXmlElement.get(i).getName();
			}
		}
		return result;
	}

	/**
	 * this method is responsible for become the Array's Element to HLVL code
	 * 
	 * @return ArrayList<CharSequence> that contain all HLVL code lines
	 */
	public void converterXmlElementToHLVLCode() {
		IhlvlBasicFactory converter = new HlvlBasicFactory();
		codee.append("elements:");
		for (int i = 0; i < importantXmlElement.size(); i++) {
			String name = importantXmlElement.get(i).getName();
			name = getValidName(name);
			String caso = importantXmlElement.get(i).getType();
			switch (caso) {
			case "root":
				codee.append(converter.getCore(name));
				codee.append(converter.getElement(name));
				break;
			case "general":
				codee.append(converter.getElement(name));
				break;
			case "leaf":
				codee.append(converter.getElement(name));
				break;
			case "bundle":
				if (importantXmlElement.get(i).getBundleType().endsWith("OR"))
					codee.append(converter.getGroup(findRootBundle(importantXmlElement.get(i)),
							findGroupsElements(importantXmlElement.get(i)), GroupType.Or));
				else
					codee.append(converter.getGroup(findRootBundle(importantXmlElement.get(i)),
							findGroupsElements(importantXmlElement.get(i)), GroupType.Alternative));
				break;
			}
		}
	}

	public String findRootBundle(Element element) {
		String name = "";
		if (element.getBundleType() != null || !element.getBundleType().equals("")) {
			String id = element.getId();
			for (int i = 0; i < importantXmlDependecy.size(); i++) {

				if (id.equals(importantXmlDependecy.get(i).getSource())) {
					name = searchForName(importantXmlDependecy.get(i).getTarget());
					break;
				}

			}
		}
		name = getValidName(name);
		return name;
	}

	public ArrayList<String> findGroupsElements(Element element) {
		ArrayList<String> result = new ArrayList<String>();
		if (element.getBundleType() != null || !element.getBundleType().equals("")) {
			String id = element.getId();

			for (int i = 0; i < importantXmlDependecy.size(); i++) {

				if (id.equals(importantXmlDependecy.get(i).getTarget())) {
					String name = searchForName(importantXmlDependecy.get(i).getSource());
					name = getValidName(name);
					result.add(name);
				}
			}
		}

		return result;
	}

	@Override
	public String getValidName(String name) {
		return name.replaceAll(" ", "_").replaceAll("\\-", "Minus").replaceAll("\\+", "Plus").replaceAll("\\.", "dot")
				.replaceAll("/", "");
	}

	@Override
	public void parse() throws Exception {
		codee.append(HlvlBasicKeys.MODEL_LABEL);
		converterXmlElementToHLVLCode();
		converterXmlDependecyToHLVLCode();
		writeFile();
	}
}
