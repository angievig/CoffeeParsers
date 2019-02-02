package xmlToHLVLParser;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import utils.FileUtils;

/**
 * This is a class which is responsible for reading a XML file, loading that
 * file into tree structure. and extract information from XML tree to create XML
 * element object or XML dependency objects too.
 * 
 * @version 1.5, 19/01/2019
 * @author Joan David Colina Echeverry
 */
public class XmlReader {

	private ArrayList<Dependecy> importantXmlDependecy;
	private ArrayList<Element> importantXmlElement;

	/**
	 * this method return a list of Dependecy objects
	 * 
	 * @return ArrayList<Dependecy>: ArrayList with Dependecy objects
	 */
	public ArrayList<Dependecy> getImportantXmlDependecy() {
		return importantXmlDependecy;
	}

	/**
	 * this method change importantXMLDependecy' value for paramater.
	 * 
	 * @param ArrayList<Dependecy>: ArrayList with Dependecy objects
	 */
	public void setImportantXmlDependecy(ArrayList<Dependecy> importantXmlDependecy) {
		this.importantXmlDependecy = importantXmlDependecy;
	}

	/**
	 * this method return a list of Element objects
	 * 
	 * @return ArrayList<Element>: ArrayList with Element objects
	 */
	public ArrayList<Element> getImportantXmlElement() {
		return importantXmlElement;
	}

	/**
	 * this method change importantXMLElement' value for paramater.
	 * 
	 * @param ArrayList<Element>: ArrayList with Element objects
	 */
	public void setImportantXmlElement(ArrayList<Element> importantXmlElement) {
		this.importantXmlElement = importantXmlElement;
	}

	/**
	 * this method is responsible for inicializate importantXmlDependecy and
	 * importantXMLElement Arrays. and this methos have to create a DocumentBuilder
	 * to load the XML file. to then, read that fiel and load into Dependecy and
	 * Element objects.
	 * 
	 * @param path: string that represent the XML source to load.
	 */
	public void loadXmlFiel(String path) {
		importantXmlDependecy = new ArrayList<Dependecy>();
		importantXmlElement = new ArrayList<Element>();
		
		FileUtils fileUtils = new FileUtils();
		List<File> xmlFiel= fileUtils.readFileFromDirectory(path);
		//System.out.println("--"+xmlFiel.get(0));
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			for (int i = 0; i < xmlFiel.size(); i++) {
				
				org.w3c.dom.Document xmlTree = builder.parse(xmlFiel.get(i));
				readDocument(xmlTree);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * this method is responsible for create and add to importarXmlElement a Element
	 * object with type equal to General or Root. to create this objet is necesary a
	 * name, id and type.
	 * 
	 * @param n: node from XML tree
	 */
	public void addGeneralAndRootElement(Node n) {
		Element xmlElement = new Element();

		String name = n.getAttributes().item(1).getNodeValue();
		xmlElement.setName(name);

		String id = n.getAttributes().item(0).getNodeValue();
		xmlElement.setId(id);

		String type = n.getAttributes().item(2).getNodeValue();
		xmlElement.setType(type);

		importantXmlElement.add(xmlElement);
	}

	/**
	 * this method is responsible for create and add to importarXmlElement a Element
	 * object with type equal to Leaf. to create this objet is necesary a name, id,
	 * type and select.
	 * 
	 * @param n: node from XML tree
	 */
	public void addLeafElement(Node n) {
		Element xmlElement = new Element();

		String name = n.getAttributes().item(1).getNodeValue();
		xmlElement.setName(name);

		String id = n.getAttributes().item(0).getNodeValue();
		xmlElement.setId(id);

		String type = n.getAttributes().item(2).getNodeValue();
		xmlElement.setType(type);

		String select = n.getAttributes().item(3).getNodeValue();
		xmlElement.setSelected(select);

		importantXmlElement.add(xmlElement);
	}

	/**
	 * this method is responsible for create and add to importarXmlElement a Element
	 * object with type equal to bundle. to create this objet is necesary a name,
	 * id, type and bundleType.
	 * 
	 * @param n: node from XML tree
	 */
	public void addBundleElement(Node n) {
		Element xmlElement = new Element();

		String name = n.getAttributes().item(5).getNodeValue();
		
		xmlElement.setName(name);

		String id = n.getAttributes().item(2).getNodeValue();
		xmlElement.setId(id);

		String type = n.getAttributes().item(3).getNodeValue();
		xmlElement.setType(type);

		String bundleType = n.getAttributes().item(0).getNodeValue();
	
		xmlElement.setBundleType(bundleType);

		importantXmlElement.add(xmlElement);
	}

	/**
	 * this method is responsible for travel whole XML tree and call methos to
	 * create Element or Dependecy object.
	 * 
	 * @param n: node from XML tree
	 */
	public void readDocument(Node n) {
		if (n != null) {
			if (n.getNodeName().equals("general") || n.getNodeName().equals("root")) {
				if (n.getAttributes() != null && n.getAttributes().item(1) != null && n.getAttributes().item(0) != null
						&& n.getAttributes().item(2) != null) {
					addGeneralAndRootElement(n);
				}
			} else if (n.getNodeName().equals("leaf")) {
				if (n.getAttributes() != null && n.getAttributes().item(1) != null && n.getAttributes().item(0) != null
						&& n.getAttributes().item(2) != null) {
					addLeafElement(n);
				}
			} else if (n.getNodeName().equals("bundle")) {
				if (n.getAttributes() != null && n.getAttributes().item(1) != null && n.getAttributes().item(0) != null
						&& n.getAttributes().item(2) != null) {
					addBundleElement(n);
				}
			} else if (n.getNodeName().indexOf("rel_") > (-1)) {
				Dependecy newDependecy = new Dependecy();
				if (n.getAttributes() != null && n.getAttributes().item(0) != null
						&& n.getAttributes().item(1) != null) {
					newDependecy.setId(n.getAttributes().item(0).getNodeValue());
					newDependecy.setRelType(n.getAttributes().item(1).getNodeValue());
					if (n.getAttributes().item(2) != null) {
						newDependecy.setType(n.getAttributes().item(2).getNodeValue());
					} else {
						newDependecy.setRelType("bundle");
						newDependecy.setType("relation");
					}
				}
				AddAtributesFromChildren(n, newDependecy);
				importantXmlDependecy.add(newDependecy);
			}
			NodeList childrens = n.getChildNodes();
			for (int i = 0; i < childrens.getLength(); i++) {
				Node grandchildren = childrens.item(i);
				readDocument(grandchildren);
			}
		}
	}

	/**
	 * this method is responsible for travel the node's children and add source and
	 * target parameter to Dependecy object.
	 * 
	 * @param n: node from XML tree
	 * @param newDeprendecy: Dependecy that need source and target parameter
	 */
	public void AddAtributesFromChildren(Node n, Dependecy newDependecy) {
		NodeList childrens = n.getChildNodes();
		for (int i = 0; i < childrens.getLength(); i++) {
			Node newNode = childrens.item(i);
			if (newNode.getNodeName().equals("mxCell") && newNode.getAttributes() != null) {

				newDependecy.setSource(newNode.getAttributes().item(2).getNodeValue());
				newDependecy.setTarget(newNode.getAttributes().item(3).getNodeValue());
			}
		}
	}
}
