package com.coffee.modelParsers.VarXmlToHlvlParser.example;

import com.coffee.modelParsers.utils.ParsingParameters;

import com.coffee.modelParsers.varXmlToHLVLParser.VariamosXMLToHlvlParser;

public class example {

	private static VariamosXMLToHlvlParser variamosXMLToHlvlParser;
	private static String xml ="";
	private static String hlvl ="";
	
	public static void exampleString() {
		variamosXMLToHlvlParser= new VariamosXMLToHlvlParser();
		String xml = "<mxGraphModel>\r\n" + "  <root>\r\n" + "    <mxCell id=\"0\"/>\r\n"
				+ "    <mxCell id=\"feature\" parent=\"0\"/>\r\n"
				+ "    <root label=\"Casa\" type=\"root\" id=\"1\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"321\" y=\"20\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </root>\r\n"
				+ "    <general label=\"Bano\" type=\"general\" id=\"2\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"200\" y=\"200\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <general label=\"Cocina\" type=\"general\" id=\"4\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"340\" y=\"200\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <bundle label=\"bundle\" type=\"bundle\" bundleType=\"XOR\" lowRange=\"1\" highRange=\"1\" id=\"6\">\r\n"
				+ "      <mxCell style=\"shape=ellipse\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"355.5\" y=\"100\" width=\"35\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </bundle>\r\n" + "    <rel_bundle_root type=\"relation\" id=\"7\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"6\" target=\"1\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_bundle_root>\r\n" + "    <rel_general_bundle type=\"relation\" id=\"8\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"4\" target=\"6\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_bundle>\r\n" + "    <rel_general_bundle type=\"relation\" id=\"9\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"2\" target=\"6\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_bundle>\r\n"
				+ "    <bundle label=\"bundle\" type=\"bundle\" bundleType=\"OR\" lowRange=\"1\" highRange=\"1\" id=\"10\">\r\n"
				+ "      <mxCell style=\"shape=ellipse\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"220\" y=\"280\" width=\"35\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </bundle>\r\n"
				+ "    <rel_bundle_general type=\"relation\" id=\"11\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"10\" target=\"2\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_bundle_general>\r\n"
				+ "    <leaf label=\"Banera\" type=\"leaf\" selected=\"1\" id=\"12\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"110\" y=\"350\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </leaf>\r\n"
				+ "    <leaf label=\"ducha\" type=\"leaf\" selected=\"1\" id=\"13\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"280\" y=\"350\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </leaf>\r\n" + "    <rel_leaf_bundle type=\"relation\" id=\"14\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"12\" target=\"10\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_leaf_bundle>\r\n" + "    <rel_leaf_bundle type=\"relation\" id=\"15\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"13\" target=\"10\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_leaf_bundle>\r\n"
				+ "    <leaf label=\"Lava Platos\" type=\"leaf\" selected=\"1\" id=\"17\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"390\" y=\"280\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </leaf>\r\n"
				+ "    <rel_leaf_general type=\"relation\" relType=\"mandatory\" id=\"18\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"17\" target=\"4\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_leaf_general>\r\n" + "    <leaf label=\"Orno\" type=\"leaf\" selected=\"1\" id=\"19\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"580\" y=\"280\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </leaf>\r\n"
				+ "    <rel_leaf_general type=\"relation\" relType=\"optional\" id=\"20\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"19\" target=\"4\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_leaf_general>\r\n"
				+ "    <general label=\"Cuarto Principal\" type=\"general\" id=\"21\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"199\" y=\"130\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <rel_general_bundle type=\"relation\" id=\"22\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"21\" target=\"6\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_bundle>\r\n"
				+ "    <general label=\"Cuarto secundario\" type=\"general\" id=\"23\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"24.5\" y=\"94.5\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <rel_general_bundle type=\"relation\" id=\"24\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"23\" target=\"6\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_bundle>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"requires\" id=\"26\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"21\" target=\"2\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"optional\" id=\"27\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"23\" target=\"2\">\r\n"
				+ "        <mxGeometry x=\"-0.0092\" y=\"-4\" relative=\"1\" as=\"geometry\">\r\n"
				+ "          <mxPoint as=\"offset\"/>\r\n" + "        </mxGeometry>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n" + "    <general label=\"Garaje\" type=\"general\" id=\"28\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"446.5\" y=\"104.5\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <rel_general_root type=\"relation\" relType=\"optional\" id=\"29\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"28\" target=\"1\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_root>\r\n"
				+ "    <bundle label=\"bundle\" type=\"bundle\" bundleType=\"OR\" lowRange=\"1\" highRange=\"1\" id=\"30\">\r\n"
				+ "      <mxCell style=\"shape=ellipse\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"611\" y=\"70\" width=\"35\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </bundle>\r\n" + "    <rel_bundle_root type=\"relation\" id=\"31\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"30\" target=\"1\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_bundle_root>\r\n" + "    <general label=\"Biblioteca\" type=\"general\" id=\"32\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"630\" y=\"140\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <general label=\"Sala de juegos\" type=\"general\" id=\"33\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"800\" y=\"120\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <rel_general_bundle type=\"relation\" id=\"34\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"32\" target=\"30\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_bundle>\r\n" + "    <rel_general_bundle type=\"relation\" id=\"35\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"33\" target=\"30\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_bundle>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"excludes\" id=\"36\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"33\" target=\"32\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n" + "    <mxCell id=\"component\" parent=\"0\" visible=\"0\"/>\r\n"
				+ "    <mxCell id=\"binding_feature_component\" parent=\"0\" visible=\"0\"/>\r\n"
				+ "    <leaf label=\"Banera\" type=\"leaf\" selected=\"1\" id=\"clon12\">\r\n"
				+ "      <mxCell style=\"fillColor=#DCDCDC;\" vertex=\"1\" parent=\"binding_feature_component\">\r\n"
				+ "        <mxGeometry x=\"560\" y=\"200\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </leaf>\r\n"
				+ "    <leaf label=\"ducha\" type=\"leaf\" selected=\"1\" id=\"clon13\">\r\n"
				+ "      <mxCell style=\"fillColor=#DCDCDC;\" vertex=\"1\" parent=\"binding_feature_component\">\r\n"
				+ "        <mxGeometry x=\"280\" y=\"350\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </leaf>\r\n"
				+ "    <leaf label=\"Lava Platos\" type=\"leaf\" selected=\"1\" id=\"clon17\">\r\n"
				+ "      <mxCell style=\"fillColor=#DCDCDC;\" vertex=\"1\" parent=\"binding_feature_component\">\r\n"
				+ "        <mxGeometry x=\"390\" y=\"280\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </leaf>\r\n"
				+ "    <leaf label=\"Orno\" type=\"leaf\" selected=\"1\" id=\"clon19\">\r\n"
				+ "      <mxCell style=\"fillColor=#DCDCDC;\" vertex=\"1\" parent=\"binding_feature_component\">\r\n"
				+ "        <mxGeometry x=\"580\" y=\"280\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </leaf>\r\n" + "  </root>\r\n" + "</mxGraphModel>";
		
		try {
			hlvl= variamosXMLToHlvlParser.parse(xml);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(hlvl);
	}
	
	public static void exampleWithParsingParameters() {
		ParsingParameters params = new ParsingParameters();
		params.setInputPath("data/xml");
		params.setOutputPath("data/hlvl/");
		params.setTargetName("example");
		variamosXMLToHlvlParser = new VariamosXMLToHlvlParser(params);
		try {
			variamosXMLToHlvlParser.parse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] arg0) {
		exampleString();
		exampleWithParsingParameters();
	}
	
	
}
