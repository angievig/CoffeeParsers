package xmlToHLVLParser;

import java.util.ArrayList;

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
 * @version 1.5, 19/01/2019
 * @author Joan David Colina Echeverry
 */
public class VariamosXML2HlvlParser implements IHlvlParser {

	private ArrayList<Dependecy> importantXmlDependecy;

	private ArrayList<Element> importantXmlElement;

	private XmlReader xmlReader;
	
	private ParsingParameters params;
	
	private ArrayList<String> code;

	public VariamosXML2HlvlParser(ParsingParameters params) {
		code = new ArrayList<String>();
		this.params =params;
	}
	
	
	public void writeFile() {
		String lineCode="";
		for (int i =0; i< code.size();i++) {
			
			lineCode = lineCode + code.get(i);
		}
		FileUtils.writeHLVLProgram(params.getOutputPath(),
				lineCode);
		System.out.println("Conversion complete");
	}
//	public void validateHlvlCode() {
//		ArrayList<String> aux= new ArrayList<String>();
//		String lineCode ="model -----";
//		aux.add(lineCode);
//		lineCode = "Elements:";
//		aux.add(lineCode);
//		for (int i = 0; i < code.size(); i++) {
//		lineCode = code.get(i);
//		if (lineCode.startsWith("boolean")) {
//			
//		}
//		}
//	}
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
	public ArrayList<CharSequence> converterXmlDependecyToHLVLCode() {
		IhlvlBasicFactory converter = new HlvlBasicFactory();
		ArrayList<CharSequence> result = new ArrayList<CharSequence>();
		CharSequence aux = "";
		for (int i = 0; i < importantXmlDependecy.size(); i++) {
			String target = searchForName(importantXmlDependecy.get(i).getTarget());
			String source = searchForName(importantXmlDependecy.get(i).getSource());
			String caso = importantXmlDependecy.get(i).getRelType();
			switch (caso) {
			case "mandatory":
				aux = converter.getDecomposition(target, source,DecompositionType.Mandatory);
				break;
			case "optional":
				aux = converter.getDecomposition(target, source,DecompositionType.Optional);
				break;
			case "requires":
				aux = converter.getImplies(target, source);
				break;
			case "excludes":
				aux = converter.getMutex(target, source);
				break;
			}
			// TODO: Solucionar problema con null.
			if (aux != null && aux != "") {
				System.out.println(aux);

				result.add(aux);
				code.add(""+aux);
			}
			aux = "";
		}

		return result;
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
	public ArrayList<CharSequence> converterXmlElementToHLVLCode() {
		IhlvlBasicFactory converter = new HlvlBasicFactory();
		ArrayList<CharSequence> bundle = new ArrayList<CharSequence>();
		ArrayList<CharSequence> result = new ArrayList<CharSequence>();
		CharSequence aux = "";
		CharSequence auxBundle = "";

		for (int i = 0; i < importantXmlElement.size(); i++) {
			String name = importantXmlElement.get(i).getName();
			String caso = importantXmlElement.get(i).getType();
			switch (caso) {
			case "root":
				aux = converter.getCore(name);
				break;
			case "general":
				aux = converter.getElement(name);
				break;
			case "leaf":
				aux = converter.getElement(name);
				break;
			case "bundle":
				if (importantXmlElement.get(i).getBundleType().endsWith("OR")) {
					
					auxBundle = converter.getGroup(findRootBundle(importantXmlElement.get(i)), findGroupsElements(importantXmlElement.get(i)),GroupType.Or);
				}else {
					auxBundle = converter.getGroup(findRootBundle(importantXmlElement.get(i)), findGroupsElements(importantXmlElement.get(i)),GroupType.Alternative);
				}
			break;
		}
			// TODO: Solucionar problema con null.
			if (aux != null && aux != "") {
				System.out.println(aux);

				result.add(aux);
				code.add(""+aux);
			}
			if (auxBundle != null && auxBundle != "") {
				System.out.println(auxBundle);

				bundle.add(auxBundle);
			//	code.add(""+aux);
			}
			aux = "";
			auxBundle ="";
		}
		
		for (int i = 0; i < bundle.size(); i++) {
			result.add(bundle.get(i));
			code.add(""+bundle.get(i));
		}
		return result;

	}
	public String findRootBundle(Element element) {
		String name = "";
		if (element.getBundleType()!=null||!element.getBundleType().equals("")) {
				String id = element.getId();
			
			for (int i = 0; i < importantXmlDependecy.size(); i++) {
				
				if (id.equals(importantXmlDependecy.get(i).getSource())) {
					name = searchForName(importantXmlDependecy.get(i).getTarget());
					break;
				}
			
		}
			}
		return name;
	}
	public ArrayList<String> findGroupsElements(Element element) {
		ArrayList<String> result = new ArrayList<String>();
		if (element.getBundleType()!=null||!element.getBundleType().equals("")) {
			String id = element.getId();
			
			for (int i = 0; i < importantXmlDependecy.size(); i++) {
				
				if (id.equals(importantXmlDependecy.get(i).getTarget())) {
					String name = searchForName(importantXmlDependecy.get(i).getSource());
					result.add(name);
				}
			}
		}
		
		return result;
	}


	@Override
	public String getValidName(String name) {
		
		return null;
	}


	@Override
	public void parse() throws Exception {
		converterXmlElementToHLVLCode();
		converterXmlDependecyToHLVLCode();
		writeFile();
		
	}
}
