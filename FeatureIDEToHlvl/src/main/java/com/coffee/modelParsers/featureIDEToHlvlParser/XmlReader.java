package com.coffee.modelParsers.featureIDEToHlvlParser;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Node;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.github.coffeeframework.utils.FileUtils;

/**
 * This is a class which is responsible for reading a XML file, loading that
 * file into a ArrayList
 * 
 * @version 0.5, 03/04/2019
 * @author Joan David Colina Echeverry
 * Modified by Manuel Quintero
 */
public class XmlReader {

	/**
	 * Attribute that represents a association relationship with the ArrayList class 
	 * that is used to store and represent all XML trees loaded by xmlReader
	 */
	private ArrayList<Node> xmlTree;
	
	/**
	 * Constructor of the class XmlReader
	 */
	public XmlReader() {
		xmlTree= new ArrayList<Node>();
	}
	
	/**
	 * This method is responsible of loading each XML file in the specific path and 
	 * add it in the xmlTree attribute
	 * 
	 * @param path String that represent the XML source that is going to be loaded.
	 */
	public void loadXmlFile(String path) {

		List<File> xmlFiel = FileUtils.readFileFromDirectory(path);
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			for (int i = 0; i < xmlFiel.size(); i++) {
				xmlTree.add(builder.parse(xmlFiel.get(i)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method returns a ArrayList<Node> that represents the xmlTree. 
	 * It contains all the Nodes of the current xmlTree.
	 * 
	 * @return An ArrayList with the Node objects of the current xmlTree.
	 */
	public ArrayList<Node> getXmlTree() {
		return xmlTree;
	}

	/**
	 * This method is used to set a new value for the attribute xmlTree.
	 * 
	 * @param xmlTree ArrayList with Node objects that represent a xmlTree.
	 */
	public void setXmlTree(ArrayList<Node> xmlTree) {
		this.xmlTree = xmlTree;
	}

	/**
	 * This method is responsible to load xmlTree from a  XML code that is received as
	 * a parameter.
	 * 
	 * @param xml String that contains the XML code to load.
	 */
	public void loadXmlString(String xml) {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			xmlTree.add(builder.parse(new InputSource(new StringReader(xml))));
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
}
