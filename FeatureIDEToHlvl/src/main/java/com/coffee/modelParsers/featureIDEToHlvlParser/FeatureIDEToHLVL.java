package com.coffee.modelParsers.featureIDEToHlvlParser;

import java.util.ArrayList;


import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.coffee.modelParsers.ExpresionHLVLPackage.HlvlExpressionKeys;
import com.coffee.modelParsers.attHLVLPackage.AttType;
import com.coffee.modelParsers.attHLVLPackage.HlvlAttFactory;
import com.coffee.modelParsers.attHLVLPackage.IHlvlAttFactory;
import com.coffee.modelParsers.basicHLVLPackage.DecompositionType;
import com.coffee.modelParsers.basicHLVLPackage.GroupType;
import com.coffee.modelParsers.basicHLVLPackage.IHlvlParser;
import com.coffee.modelParsers.utils.FileUtils;
import com.coffee.modelParsers.utils.ParsingParameters;
/**
 * This is a class that is responsible for extracting 
 * information from the xmlTree object and converting that 
 * information into HLVL code.
 * 
 * @version 0.5, 19/01/2019
 * @author Joan David Colina Echeverry
 * Modified by Manuel Quintero
 */
public class FeatureIDEToHLVL implements IHlvlParser {

	/**
	 * Attribute of a StringBuilder object that fulfills the
	 * function of saving the HLVL code.
	 */
	private StringBuilder HlvlCode;

	/**
	 * Attribute that defines a association relation with the ParsingParameters class 
	 * that fulfills the function of saving the paths to load the XML file and write
	 * the HLVL code file.
	 */
	private ParsingParameters params;
	/**
	 * Attribute that defines a association relationship with any class that implements the
	 * IHlvlAttFactory which in this case it would be the HlvlBasicFactory class.This attribute 
	 * fulfills the function of creating the HLVL code.
	 */
	private IHlvlAttFactory converter;
	/**
	 * Attribute that defines a association relationship with the XmlReader class 
	 * that fulfills the function of loading the XML file to the program.
	 */
	private XmlReader xmlReader;
	/**
	 * Attribute that defines a association relationship with the ArrayList class that
	 * represent all XML trees loaded by xmlReader.
	 */
	private ArrayList<Node> xmlTree;

	/**
	 * Constructor for the FeatureIDEToHLVL class that needs a ParsingParameters object to be
	 * instantiated.
	 * 
	 * @param params: A ParsingParameters object that contain the necessary paths to load the
	 *  			   XML file and save HLVL file.
	 */
	public FeatureIDEToHLVL(ParsingParameters params) {
		this.params = params;
	}

	/**
	 * Constructor for the FeatureIDEToHLVL class with no required parameters.
	 * 
	 */
	public FeatureIDEToHLVL() {

	}

	/**
	 * This method is responsible of initializing the xmlReader, loading the file with
	 * that contains the XML Code using the params attribute and getting the InputPath.
	 * Also, this method is responsible of the initialization of the xmlTree attribute.
	 * 
	 */
	//TODO
	public void initialize() {
		xmlReader = new XmlReader();
		xmlReader.loadXmlFile(params.getInputPath());
		xmlTree = xmlReader.getXmlTree();
		
	}

	/**
	 * This method is responsible for initialization of the attributes: converter, xmlReader,HlvlCode.
	 * Also, this method loads the String that contains the XML code and assigns the xmlTree generated to the xmlTree  
	 * attribute. This method requires a String parameter with that XML code that is going to be loaded and transformed.
	 * @param xml String with XML code to load and transform.
	 */
	public void initialize(String xml) {
		converter = new HlvlAttFactory();
		xmlReader = new XmlReader();
		HlvlCode = new StringBuilder();
		xmlReader.loadXmlString(xml);
		xmlTree = xmlReader.getXmlTree();

	}

	/**
	 * This method is responsible of creating the correspondent HLVL file with extension: .hlvl
	 */
	public void writeFile() {
		FileUtils.writeHLVLProgram(params.getOutputPath(), HlvlCode.toString());
		System.out.println("Conversion complete");
	}

	/**
	 * This method is responsible of pruning the xmlTree, by going over each Node object and 
	 * transforming it's information into HLVL code.
	 * 
	 * @param n A Node object.
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
	 * This method is responsible of producing and appending HLVL code that represent mutex and implies  
	 * expressions from the information of a Node object. This is done by using the attribute HlvlCode.
	 * @param n A Node object
	 */
	public void addConstrains(Node n) {
		String expression ="";	
		HlvlCode.append("	"+converter.parserExpression(addComplexConstrains(n.getFirstChild().getNextSibling(), expression)));
	}
	
	
	
	/**
	 * This method is responsible of producing a String that contains a complex constraint 
	 * in HLVL syntax. This is done by evaluating a Node object that contains the constraint.
	 * @param n A Node Object
	 * @param result String that will contain the correspondent constraint.
	 * @return String that contains the correspondent constraint after evaluating the Node object.
	 */
	public String addComplexConstrains(Node n, String result) {
		String valueNode = n.getNodeName();
		switch (valueNode) {
		case "imp":
			result ="("+addComplexConstrains(n.getFirstChild().getNextSibling(), result)+" "+HlvlExpressionKeys.IMP+" "+addComplexConstrains(n.getLastChild().getPreviousSibling(), result)+")";
			break;
		case "conj":
			result ="("+addComplexConstrains(n.getFirstChild().getNextSibling(), result)+" "+HlvlExpressionKeys.AND+" "+addComplexConstrains(n.getLastChild().getPreviousSibling(), result)+")";
			break;		
		case "disj":
			result ="("+addComplexConstrains(n.getFirstChild().getNextSibling(), result)+" "+HlvlExpressionKeys.OR+" "+addComplexConstrains(n.getLastChild().getPreviousSibling(), result)+")";
			break;
		case "not":
			result ="("+HlvlExpressionKeys.NOT+" "+addComplexConstrains(n.getFirstChild().getNextSibling(), result)+")";
			break;
		case "var":
			result ="("+n.getFirstChild().getNodeValue()+")";
			break;
		}
		return result;
	}
	
	

	/**
	 * This method is responsible to list the names of the children Nodes of n.
	 * @param n A Node object
	 * @return ArrayList: ArrayList that contains all the names from the children Nodes of n.
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
	 * This method is responsible to append in the attribute HlvlCode the code that represents
	 * the Core HLVL.
	 * 
	 */
	public void addCore() {
		if (HlvlCode.indexOf(converter.getRelationsLab()) < 0) {
			HlvlCode.append(converter.getRelationsLab());
		}
	}

	/**
	 * This method is responsible to append in the attribute HlvlCode the code of a HLVL group
	 * that is contained in a Node object.
	 * 
	 * @param n A Node object
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
	 * This method is responsible to append in the attribute HlvlCode the code that represent a
	 * HLVL Descomposition that is contained in a Node object.
	 * 
	 * @param n: A Node object.
	 */
	public void addDescomposition(Node n) {
		//for (int i = 0; i < n.getAttributes().getLength(); i++) {
			if (n.getAttributes().item(0).getNodeName().equals("mandatory")&& 
			   (findNameInNode(n.getParentNode()).equals("")) && 
			   (!n.getParentNode().getNodeName().equals("or")&& 
				!n.getParentNode().getNodeName().equals("alt"))) {
				HlvlCode.append("	" + converter.getCore(findNameInNode(n)));
			} else if (n.getAttributes().item(0).getNodeName().equals("mandatory")&& 
					  (!findNameInNode(n.getParentNode()).equals("")) && 
					  (!n.getParentNode().getNodeName().equals("or")
					&& !n.getParentNode().getNodeName().equals("alt"))) {
				HlvlCode.append("	" + converter.getDecomposition(findNameInNode(n.getParentNode()), findNameInNode(n),DecompositionType.Mandatory));
			} else if ((!findNameInNode(n.getParentNode()).equals("")) 
					&& n.getAttributes().getLength() == 1
					&& (!n.getParentNode().getNodeName().equals("or")
					&& !n.getParentNode().getNodeName().equals("alt"))) {
				HlvlCode.append("	" + converter.getDecomposition(findNameInNode(n.getParentNode()), findNameInNode(n),DecompositionType.Optional));
			}
		//}
	}

	/**
	 * This method is responsible of using the correspondent methods in a specific order
	 * so the HlvlCode attribute appends the Core, the groups and the decomposition of a
	 * specific Node object in a correct way.
	 * @param n
	 */
	public void addRelations(Node n) {
		// SE agrega la etiqueta para las relaciones
		addCore();
		// SE agregan los grupos
		addGroup(n);
		// SE agregan las descomposiciones
		addDescomposition(n);
	}

	/**
	 * This method is responsible to find the value of the attribute 'name' from a specific Node.
	 * @param n A Node object.
	 * @return A String that contains a representation of an ArrayList that 
	 * symbolizes the node's name.
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
	 * This method is responsible to insert in the HlvlCode attribute 
	 * the HLVL code that represents a element. This element is contained
	 * in a Node object.
	 * @param n A Node object.
	 */
	public void addElement(Node n) {
		for (int i = 0; i < n.getAttributes().getLength(); i++) {
			if (n.getAttributes().item(i).getNodeName().equals("name")) {
				if (params != null) {
					HlvlCode.insert(converter.getHeader(params.getTargetName() + "_generated").length(),
							"	" + converter.getElement(n.getAttributes().item(i).getNodeValue()));
					addAttributes(n,converter.getHeader(params.getTargetName() + "_generated").length(), findNameInNode(n));
				} else {

					HlvlCode.insert((converter.getHeader("Auto_generated").length()),
							"	" + converter.getElement(n.getAttributes().item(i).getNodeValue()));
					addAttributes(n,converter.getHeader("Auto_generated").length(),n.getParentNode().getNodeName());
				}
			}
		}
	}
	/**
	 * This method is responsible to insert in the HlvlCode attribute all of the
	 * attributes that are part of a specific Node. This means all of it's child
	 * nodes  name attributes and also the correspondent decomposition relation depending
	 * on it's child's type.
	 * @param n A Node object
	 * @param pos Index where the value of the attribute 'name' of the child nodes will be 
	 * inserted.
	 * @param nameFather Name of the father element of the decomposition.
	 */
	public void addAttributes(Node n, int pos,String nameFather) {
	
		for (int i = 0; i < n.getChildNodes().getLength(); i++) {
			if (n.getChildNodes().item(i).getNodeName().equals("attribute")) {
			
					String name =n.getChildNodes().item(i).getAttributes().item(0).getNodeValue();
					String type=n.getChildNodes().item(i).getAttributes().item(1).getNodeValue();
					switch (type) {
					case "double":
						HlvlCode.insert(pos,"	" + converter.getAtt(name,AttType.SYMBOLIC));
						HlvlCode.insert(HlvlCode.length(),"	" + converter.getDecomposition(name, nameFather,DecompositionType.Mandatory) );
						break;
					case "long":
						HlvlCode.insert(pos,"	" + converter.getAtt(name,AttType.SYMBOLIC));
						HlvlCode.insert(HlvlCode.length(),"	" + converter.getDecomposition(name, nameFather,DecompositionType.Mandatory) );
						break;
					case "integer":
						HlvlCode.insert(pos,"	" + converter.getAtt(name,AttType.INTEGER));
						HlvlCode.insert(HlvlCode.length(),"	" + converter.getDecomposition(name, nameFather,DecompositionType.Mandatory) );
						break;
					case "string":
						HlvlCode.insert(pos,"	" + converter.getAtt(name,AttType.STRING));
						HlvlCode.insert(HlvlCode.length(),"	" + converter.getDecomposition(name, nameFather,DecompositionType.Mandatory) );
						break;
					}

			}
		}
	}

	/**
	 * This method is responsible of ensuring that the correct format is always
	 * preserved by any element's name.
	 * @param name Name of any Element.
	 * @return String with the name in a valid format.
	 */
	@Override
	public String getValidName(String name) {
		return name.replaceAll(" ", "_").replaceAll("\\-", "Minus").replaceAll("\\+", "Plus").replaceAll("\\.", "dot")
				.replaceAll("/", "");
	}

	/**
	 * This method is responsible for the parsing of the specific model that is contained in a File.
	 * It must follow specific instructions to ensure a correct parsing.
	 * First, the method must call the method initialize() that doesn't receives parameters. 
	 * Second, this method must instantiate the attributes of converter and HlvlCode. 
	 * Third, the method must call the method readTree(Node n) for every node in the xmlTree so, first
	 * it will repeat step two and after that it will call the method readTree(Node n) for the specific node in the loop.
	 * Last, the method must call writeFile() so the HLVL code generated so far is saved,
	 */
	@Override
	public void parse() throws Exception {
		initialize();
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
	 * This method is responsible for the parsing of the specific XML code that is contained in a String.
	 * It must follow specific instructions to ensure a correct parsing.
	 * First, the method must call the method initialize(String xml) that receives a String parameter. 
	 * Second, this method must append in the HlvlCode attribute the header of the code with the targetName "Auto_generated". 
	 * Last, the method must call the method readTree(Node n) for the only node in the xmlTree.
	 * @param data: String that contains XML code.
	 * @return String that contains HLVL code that was produced by parsing data
	 */
	@Override
	public String parse(String data) throws Exception {
		initialize(data);
		HlvlCode.append(converter.getHeader("Auto_generated"));
		readTree(xmlTree.get(0));
		return HlvlCode.toString();
	}
	

}
