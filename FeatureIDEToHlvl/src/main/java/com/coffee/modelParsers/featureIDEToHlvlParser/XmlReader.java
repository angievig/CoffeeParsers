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

import com.coffee.modelParsers.utils.FileUtils;
/**
 * This is a class which is responsible for reading a XML file, loading that
 * file into a ArrayList
 * 
 * @version 0.5, 03/04/2019
 * @author Joan David Colina Echeverry
 */
public class XmlReader {

	/**
	 * @param xmlTree: relationship with the ArrayList class that represent all XML
	 *                 trees loaded by xmlReader
	 */
	private ArrayList<Node> xmlTree;


	public XmlReader() {
		xmlTree= new ArrayList<Node>();
	}
	
	/**
	 * this method is responsible to load each XML fiel in path and add it in xmlTree
	 * 
	 * @param path: string that represent the XML source to load.
	 */
	public void loadXmlFiel(String path) {

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
	 * this method return a list of Node objects
	 * 
	 * @return ArrayList: ArrayList with Node objects
	 */
	public ArrayList<Node> getXmlTree() {
		return xmlTree;
	}

	/**
	 * this method change xmlTree's value for paramater.
	 * 
	 * @param xmlTree: ArrayList with Node objects
	 */
	public void setXmlTree(ArrayList<Node> xmlTree) {
		this.xmlTree = xmlTree;
	}

	/**
	 * this method is responsible to load xmlTree the XML code from xml
	 * 
	 * @param xml: string that represent the XML source to load.
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
