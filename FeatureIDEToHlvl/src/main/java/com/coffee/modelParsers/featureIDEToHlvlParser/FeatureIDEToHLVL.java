package com.coffee.modelParsers.featureIDEToHlvlParser;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.coffee.modelParsers.attHLVLPackage.AttType;
import com.coffee.modelParsers.attHLVLPackage.HlvlAttFactory;
import com.coffee.modelParsers.attHLVLPackage.IHlvlAttFactory;
import com.coffee.modelParsers.basicHLVLPackage.DecompositionType;
import com.coffee.modelParsers.basicHLVLPackage.GroupType;
import com.coffee.modelParsers.basicHLVLPackage.HlvlBasicFactory;
import com.coffee.modelParsers.basicHLVLPackage.IHlvlParser;
import com.coffee.modelParsers.basicHLVLPackage.IHlvlBasicFactory;
import com.coffee.modelParsers.utils.FileUtils;
import com.coffee.modelParsers.utils.ParsingParameters;
/**
 * This is a class that is responsible for extracting 
 * information from the xmlTree object and converting that 
 * information into HLVL code.
 * 
 * @version 0.5, 19/01/2019
 * @author Joan David Colina Echeverry
 */
public class FeatureIDEToHLVL implements IHlvlParser {

	/**
	 * @param HlvlCode: relationship with the StringBuilder class that fulfills the
	 *                  function of save the HLVL code
	 */
	private StringBuilder HlvlCode;

	/**
	 * @param params: relationship with the ParsingParameters class that fulfills
	 *                the function of save the pahts to load de xml fiel and write
	 *                de HLVL code fiel
	 */
	private ParsingParameters params;
	/**
	 * @param converter: relationship with the HlvlBasicFactory class that fulfills
	 *                   the function of create HLVL code
	 */
	private IHlvlAttFactory converter;
	/**
	 * @param xmlReader: relationship with the XmlReader class that fulfills the
	 *                   function of loading the XML file
	 */
	private XmlReader xmlReader;
	/**
	 * @param xmlTree: relationship with the ArrayList class that represent all XML
	 *                 trees loaded by xmlReader
	 */
	private ArrayList<Node> xmlTree;

	/**
	 * this method is responsible for create a FeatureIDEToHLVL objet and.
	 * 
	 * @param params: params that contain paths necesary to load xml fiel and save
	 *                HLVL fiel.
	 */
	public FeatureIDEToHLVL(ParsingParameters params) {
		this.params = params;
	}

	/**
	 * this method is responsible for create a FeatureIDEToHLVL objet and.
	 * 
	 */
	public FeatureIDEToHLVL() {

	}

	/**
	 * this method is responsible for initializate the xmlReader, load the fiel with
	 * the XML code using InputPath's params and initializate the xmlTree attribute.
	 * 
	 */
	public void Initializate() {
		xmlReader = new XmlReader();
		xmlReader.loadXmlFiel(params.getInputPath());
		xmlTree = xmlReader.getXmlTree();
	}

	/**
	 * this method is responsible for initializate the converter, xmlReader,
	 * HlvlCode, load the fiel with the XML code using InputPath's params and
	 * initializate the xmlTree attribute.
	 * 
	 * @param xml: String with XML code to load and transform
	 */
	public void Initializate(String xml) {
		converter = new HlvlAttFactory();
		xmlReader = new XmlReader();
		HlvlCode = new StringBuilder();
		xmlReader.loadXmlString(xml);
		xmlTree = xmlReader.getXmlTree();

	}

	/**
	 * this method is responsible for create a HLVL code's fiel .hlvl
	 */
	public void writeFile() {
		FileUtils.writeHLVLProgram(params.getOutputPath(), HlvlCode.toString());
		System.out.println("Conversion complete");
	}

	/**
	 * this method is responsible to sheard in xmlTree each Node and transform to
	 * HLVL code
	 * 
	 * @param n: n represent a Node
	 */
	public void readTree(Node n) {

		if (((n.getNodeName().equals("feature")) || (n.getNodeName().equals("or"))) || (n.getNodeName().equals("and"))
				|| (n.getNodeName().equals("alt"))) {
			addElement(n);
			addRelations(n);
		} else if (n.getNodeName().equals("rule")) {
			addConstrains(n);
		}
		NodeList childrens = n.getChildNodes();
		for (int i = 0; i < childrens.getLength(); i++) {
			Node grandchildren = childrens.item(i);
			readTree(grandchildren);
		}
	}

	/**
	 * this method is responsible to in HlvlCode the HLVL code that 
	 * represent mutex and implies in HLVL from n
	 * 
	 * @param n: n represent a Node
	 */
	public void addConstrains(Node n) {
		NodeList childsAux = n.getChildNodes();
		for (int i = 0; i < childsAux.getLength(); i++) {
			if (childsAux.item(i).getNodeName().equals("imp")) {
				String name1 = "Null";
				String name2 = "Null";
				NodeList granChildrens = childsAux.item(i).getChildNodes();
				for (int j = 0; j < granChildrens.getLength(); j++) {
					if (granChildrens.item(j).getNodeName().equals("var") && name1.equals("Null")) {
						name1 = granChildrens.item(j).getFirstChild().getNodeValue();
					} else if (granChildrens.item(j).getNodeName().equals("var") && !name1.equals("Null")) {
						name2 = granChildrens.item(j).getFirstChild().getNodeValue();
						HlvlCode.append("	" + converter.getImplies(name1, name2));
					} else if (granChildrens.item(j).getNodeName().equals("not")) {
						name2 = granChildrens.item(j).getChildNodes().item(1).getFirstChild().getNodeValue();
						HlvlCode.append("	" + converter.getMutex(name1, name2));
					}
				}

			}
		}
	}

	/**
	 * this method is responsible to group names's childrens from n
	 * 
	 * @param n: n represent a Node
	 * @return ArrayList: ArrayList that contain all names from childrens' n
	 */
	public ArrayList<String> groupNamesChildrens(Node n) {
		ArrayList<String> names = new ArrayList<String>();
		NodeList childrens = n.getChildNodes();
		for (int i = 0; i < childrens.getLength(); i++) {
			Node grandchildren = childrens.item(i);
			if (!findNameInNode(grandchildren).equals("")) {
				names.add(findNameInNode(grandchildren));

			}
		}
		return names;
	}

	/**
	 * this method is responsible to add in HlvlCode the HLVL code that represent
	 * Core HLVL
	 * 
	 */
	public void addCore() {
		if (HlvlCode.indexOf(converter.getRelationsLab()) < 0) {
			HlvlCode.append(converter.getRelationsLab());
		}
	}

	/**
	 * this method is responsible to add in HlvlCode the HLVL code that represent a
	 * HLVL group
	 * 
	 * @param n: n represent a Node
	 */
	public void addGroup(Node n) {
		if (n.getNodeName().equals("or")) {
			ArrayList<String> names = groupNamesChildrens(n);
			HlvlCode.append("	" + converter.getGroup(findNameInNode(n), names, GroupType.Or));
		} else if (n.getNodeName().equals("alt")) {
			ArrayList<String> names = groupNamesChildrens(n);
			HlvlCode.append("	" + converter.getGroup(findNameInNode(n), names, GroupType.And));
		}
	}

	/**
	 * this method is responsible to add in HlvlCode the HLVL code that represent a
	 * HLVL Descomposition
	 * 
	 * @param n: n represent a Node
	 */
	public void addDescomposition(Node n) {
		for (int i = 0; i < n.getAttributes().getLength(); i++) {
			if (n.getAttributes().item(i).getNodeName().equals("mandatory")
					&& (findNameInNode(n.getParentNode()).equals("")) && (!n.getParentNode().getNodeName().equals("or")
							&& !n.getParentNode().getNodeName().equals("alt"))) {
				HlvlCode.append("	" + converter.getCore(findNameInNode(n)));
			} else if (n.getAttributes().item(i).getNodeName().equals("mandatory")
					&& (!findNameInNode(n.getParentNode()).equals("")) && (!n.getParentNode().getNodeName().equals("or")
							&& !n.getParentNode().getNodeName().equals("alt"))) {
				HlvlCode.append("	" + converter.getDecomposition(findNameInNode(n.getParentNode()), findNameInNode(n),
						DecompositionType.Mandatory));
			} else if ((!findNameInNode(n.getParentNode()).equals("")) && n.getAttributes().getLength() == 1
					&& (!n.getParentNode().getNodeName().equals("or")
							&& !n.getParentNode().getNodeName().equals("alt"))) {
				HlvlCode.append("	" + converter.getDecomposition(findNameInNode(n.getParentNode()), findNameInNode(n),
						DecompositionType.Optional));
			}
		}
	}

	public void addRelations(Node n) {
		// SE agrega la etiqueta para las relaciones
		addCore();
		// SE agregan los grupos
		addGroup(n);
		// SE agregan las descomposiciones
		addDescomposition(n);
	}

	/**
	 * this method is responsible to find the value's attribute name from n
	 * @param n: n represent a Node
	 * @return String: ArrayList that represent the node's name
	 */
	public String findNameInNode(Node n) {
		String nodeName = "";
		if (n.getAttributes() != null) {
			for (int i = 0; i < n.getAttributes().getLength(); i++) {
				if (n.getAttributes().item(i).getNodeName().equals("name")) {
					nodeName = n.getAttributes().item(i).getNodeValue();
				}
			}
		}
		return nodeName;
	}

	/**
	 * this method is responsible to add in HlvlCode the HLVL code that represent to
	 * n
	 * @param n: n represent a Node
	 */
	public void addElement(Node n) {
		for (int i = 0; i < n.getAttributes().getLength(); i++) {
			if (n.getAttributes().item(i).getNodeName().equals("name")) {
				if (params != null) {
					HlvlCode.insert(converter.getHeader(params.getTargetName() + "_generated").length(),
							"	" + converter.getElement(n.getAttributes().item(i).getNodeValue()));
					addAttributes(n,converter.getHeader(params.getTargetName() + "_generated").length());
				} else {

					HlvlCode.insert((converter.getHeader("Auto_generated").length()),
							"	" + converter.getElement(n.getAttributes().item(i).getNodeValue()));
					addAttributes(n,converter.getHeader("Auto_generated").length());
				}
			}
		}
	}
	
	public void addAttributes(Node n, int pos) {
		for (int i = 0; i < n.getChildNodes().getLength(); i++) {
			if (n.getChildNodes().item(i).getNodeName().equals("attribute")) {
			
					String name =n.getChildNodes().item(i).getAttributes().item(0).getNodeValue();
					String type=n.getChildNodes().item(i).getAttributes().item(1).getNodeValue();
					switch (type) {
					case "double":
						HlvlCode.insert(pos,"	" + converter.getAtt(name,AttType.DOUBLE));
						break;
					case "long":
						HlvlCode.insert(pos,"	" + converter.getAtt(name,AttType.LONG));
						break;
					case "integer":
						HlvlCode.insert(pos,"	" + converter.getAtt(name,AttType.INTEGER));
						break;
					case "string":
						HlvlCode.insert(pos,"	" + converter.getAtt(name,AttType.STRING));
						break;
					}

			}
		}
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
	 * this method is responsible to call Initializate(), create converter,
	 * HlvlCode, call writeFiel(), and call readTree(Node n) for each node in
	 * xmlTree
	 * 
	 */
	@Override
	public void parse() throws Exception {
		Initializate();
		for (int i = 0; i < xmlTree.size(); i++) {
			converter = new HlvlAttFactory();
			HlvlCode = new StringBuilder();
			HlvlCode.append(converter.getHeader(params.getTargetName() + "_generated"));
			readTree(xmlTree.get(i));
			writeFile();
			params.setTargetName(params.getTargetName() + i);
			HlvlCode.delete(0, HlvlCode.length());
		}

	}

	/**
	 * this method is responsible to call Initializate(String data), call
	 * readTree(Node n)
	 * 
	 * @param data: String that cantain XML code.
	 * @return String: HLVL code from data
	 */
	@Override
	public String parse(String data) throws Exception {
		Initializate(data);
		HlvlCode.append(converter.getHeader("Auto_generated"));
		readTree(xmlTree.get(0));
		return HlvlCode.toString();
	}

}
