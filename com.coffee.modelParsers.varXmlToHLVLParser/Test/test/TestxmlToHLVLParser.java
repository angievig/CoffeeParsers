package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import com.coffee.modelParsers.utils.ParsingParameters;
import com.coffee.modelParsers.varXmlToHLVLParser.VariamosXMLToHlvlParser;

class TestxmlToHLVLParser {

	private VariamosXMLToHlvlParser variamosXMLToHlvlParser;

	@Test
	public void test() {

	}

	@Test
	public void InputStringCommonTest() {
		variamosXMLToHlvlParser = new VariamosXMLToHlvlParser();
		String xml = "<mxGraphModel>\r\n" + "  <root>\r\n" + "    <mxCell id=\"0\"/>\r\n"
				+ "    <mxCell id=\"feature\" parent=\"0\"/>\r\n"
				+ "    <root label=\"Casa\" type=\"root\" id=\"1\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"321\" y=\"20\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </root>\r\n"
				+ "    <general label=\"Baño\" type=\"general\" id=\"2\">\r\n"
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
				+ "    <leaf label=\"Bañera\" type=\"leaf\" selected=\"1\" id=\"12\">\r\n"
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
				+ "    <leaf label=\"Bañera\" type=\"leaf\" selected=\"1\" id=\"clon12\">\r\n"
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
		String result = "model  Auto_generated\r\n" + 
				"elements: \r\n" + 
				"	boolean Sala_de_juegos\r\n" + 
				"	boolean Bañera\r\n" + 
				"	boolean Cuarto_secundario\r\n" + 
				"	boolean ducha\r\n" + 
				"	boolean Lava_Platos\r\n" + 
				"	boolean Garaje\r\n" + 
				"	boolean Orno\r\n" + 
				"	boolean Casa\r\n" + 
				"	boolean Baño\r\n" + 
				"	boolean Cocina\r\n" + 
				"	boolean Cuarto_Principal\r\n" + 
				"	boolean Biblioteca\r\n" + 
				"relations:\r\n" + 
				"	r0: coreElements(Casa)\r\n" + 
				"	r1:group(Casa,[Cocina, Baño, Cuarto_Principal, Cuarto_secundario])[1,*]\r\n" + 
				"	r2:group(Casa,[Biblioteca, Sala_de_juegos])[1,*]\r\n" + 
				"	r3:group(Baño,[Bañera, ducha])[1,*]\r\n" + 
				"	r4:decomposition(Lava_Platos,[Cocina])<1>\r\n" + 
				"	r5:decomposition(Cocina,[Orno])<0>\r\n" + 
				"	r6: implies(Cuarto_Principal,Baño)\r\n" + 
				"	r7:decomposition(Baño,[Cuarto_secundario])<0>\r\n" + 
				"	r8:decomposition(Casa,[Garaje])<0>\r\n" + 
				"	r9: mutex(Biblioteca, Sala_de_juegos)\r\n" + 
				"\r\n" + 
				"";
		try {
			String variamosResult = variamosXMLToHlvlParser.parse(xml);
//			System.out.println(result);
			//System.out.println(variamosResult);
			assertTrue(result.equals(variamosResult));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void InputStringSmallTest() {
		variamosXMLToHlvlParser = new VariamosXMLToHlvlParser();
		String xml = "<mxGraphModel>\r\n" + "  <root>\r\n" + "    <mxCell id=\"0\"/>\r\n"
				+ "    <mxCell id=\"feature\" parent=\"0\"/>\r\n"
				+ "    <root label=\"casa\" type=\"root\" id=\"1\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"420\" y=\"30\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </root>\r\n"
				+ "    <bundle label=\"bundle\" type=\"bundle\" bundleType=\"OR\" lowRange=\"1\" highRange=\"1\" id=\"2\">\r\n"
				+ "      <mxCell style=\"shape=ellipse\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"330\" y=\"110\" width=\"35\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </bundle>\r\n"
				+ "    <general label=\"garaje\" type=\"general\" id=\"3\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"180\" y=\"210\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <bundle label=\"bundle\" type=\"bundle\" bundleType=\"AND\" lowRange=\"1\" highRange=\"1\" id=\"4\">\r\n"
				+ "      <mxCell style=\"shape=ellipse\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"660\" y=\"160\" width=\"35\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </bundle>\r\n"
				+ "    <leaf label=\"Ante-Jardin\" type=\"leaf\" selected=\"1\" id=\"5\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"360\" y=\"230\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </leaf>\r\n" + "    <rel_general_bundle type=\"relation\" id=\"6\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"3\" target=\"2\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_bundle>\r\n" + "    <rel_leaf_bundle type=\"relation\" id=\"7\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"5\" target=\"2\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_leaf_bundle>\r\n" + "    <leaf label=\"cocina\" type=\"leaf\" selected=\"1\" id=\"8\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"590\" y=\"240\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </leaf>\r\n" + "    <rel_leaf_bundle type=\"relation\" id=\"9\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"8\" target=\"4\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_leaf_bundle>\r\n" + "    <rel_bundle_root type=\"relation\" id=\"10\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"4\" target=\"1\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_bundle_root>\r\n" + "    <rel_bundle_root type=\"relation\" id=\"11\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"2\" target=\"1\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_bundle_root>\r\n" + "    <general label=\"baño\" type=\"general\" id=\"12\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"720\" y=\"230\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <rel_general_bundle type=\"relation\" id=\"13\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"12\" target=\"4\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_bundle>\r\n"
				+ "    <general label=\"cuartoPrincipal\" type=\"general\" id=\"14\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"160\" y=\"30\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <rel_general_root type=\"relation\" relType=\"mandatory\" id=\"15\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"14\" target=\"1\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_root>\r\n"
				+ "    <general label=\"cuartoInvitador\" type=\"general\" id=\"16\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"660\" y=\"40\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <rel_general_root type=\"relation\" relType=\"optional\" id=\"17\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"16\" target=\"1\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_root>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"requires\" id=\"18\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"14\" target=\"3\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <rel_general_leaf type=\"relation\" relType=\"excludes\" id=\"19\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"16\" target=\"5\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_leaf>\r\n" + "    <mxCell id=\"component\" parent=\"0\" visible=\"0\"/>\r\n"
				+ "    <mxCell id=\"binding_feature_component\" parent=\"0\" visible=\"0\"/>\r\n"
				+ "    <leaf label=\"Ante-Jardin\" type=\"leaf\" selected=\"1\" id=\"clon5\">\r\n"
				+ "      <mxCell style=\"fillColor=#DCDCDC;\" vertex=\"1\" parent=\"binding_feature_component\">\r\n"
				+ "        <mxGeometry x=\"360\" y=\"230\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </leaf>\r\n"
				+ "    <leaf label=\"cocina\" type=\"leaf\" selected=\"1\" id=\"clon8\">\r\n"
				+ "      <mxCell style=\"fillColor=#DCDCDC;\" vertex=\"1\" parent=\"binding_feature_component\">\r\n"
				+ "        <mxGeometry x=\"590\" y=\"240\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </leaf>\r\n" + "  </root>\r\n" + "</mxGraphModel>";
		String result = "model  Auto_generated\r\n" + "elements: \r\n" + "	boolean casa\r\n"
				+ "	boolean baño\r\n" + "	boolean garaje\r\n" + "	boolean cuartoPrincipal\r\n"
				+ "	boolean AnteMinusJardin\r\n" + "	boolean cuartoInvitador\r\n" + "	boolean cocina\r\n"
				+ "relations:\r\n" + "	r0: coreElements(casa)\r\n"
				+ "	r1:group(casa,[garaje, AnteMinusJardin])[1,*]\r\n" + "	r2:group(casa,[cocina, baño])[1,1]\r\n"
				+ "	r3:decomposition(cuartoPrincipal,[casa])<1>\r\n"
				+ "	r4:decomposition(casa,[cuartoInvitador])<0>\r\n" + "	r5: implies(cuartoPrincipal,garaje)\r\n"
				+ "	r6: mutex(AnteMinusJardin, cuartoInvitador)\r\n" + "\r\n";

		try {
			String variamosResult = variamosXMLToHlvlParser.parse(xml);
			System.out.println(result);
			System.out.println(variamosResult);
			assertTrue(result.equals(variamosResult));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void InputStringBigTest() {
		variamosXMLToHlvlParser = new VariamosXMLToHlvlParser();
		String xml = "<mxGraphModel>\r\n" + "  <root>\r\n" + "    <mxCell id=\"0\"/>\r\n"
				+ "    <mxCell id=\"feature\" parent=\"0\"/>\r\n"
				+ "    <root label=\"Universidad\" type=\"root\" id=\"1\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"380\" y=\"20\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </root>\r\n"
				+ "    <general label=\"Direccion Academica\" type=\"general\" id=\"2\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"45.5\" y=\"72\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <bundle label=\"bundle\" type=\"bundle\" bundleType=\"XOR\" lowRange=\"1\" highRange=\"1\" id=\"3\">\r\n"
				+ "      <mxCell style=\"shape=ellipse\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"260\" y=\"80\" width=\"35\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </bundle>\r\n"
				+ "    <rel_general_bundle type=\"relation\" id=\"4\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"2\" target=\"3\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_bundle>\r\n" + "    <rel_bundle_root type=\"relation\" id=\"5\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"3\" target=\"1\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_bundle_root>\r\n"
				+ "    <general label=\"Departamento de finanzas\" type=\"general\" id=\"6\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"26\" y=\"140\" width=\"130\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <rel_general_bundle type=\"relation\" id=\"7\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"6\" target=\"3\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_bundle>\r\n"
				+ "    <general label=\"Departamento de recursos humanos\" type=\"general\" id=\"8\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"14.5\" y=\"6.5\" width=\"190\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <rel_general_bundle type=\"relation\" id=\"9\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"8\" target=\"3\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_bundle>\r\n"
				+ "    <general label=\"Departamento de investigacion\" type=\"general\" id=\"10\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"96.5\" y=\"236.5\" width=\"150\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <general label=\"Departamento TIC\" type=\"general\" id=\"11\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"694\" y=\"201\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <general label=\"Departamento de ingenieria\" type=\"general\" id=\"12\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"821.5\" y=\"181\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <general label=\"Departamento de economía\" type=\"general\" id=\"13\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"838\" y=\"94\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <general label=\"Departamento de humanidades\" type=\"general\" id=\"14\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"854.5\" y=\"54.5\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <general label=\"Departamento de Salud\" type=\"general\" id=\"15\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"834\" y=\"139.5\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <general label=\"Departamento de ciencias exactas\" type=\"general\" id=\"16\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"517\" y=\"216.5\" width=\"160\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <general label=\"Bienestar Universitario\" type=\"general\" id=\"17\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"272\" y=\"312\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <bundle label=\"bundle\" type=\"bundle\" bundleType=\"AND\" lowRange=\"1\" highRange=\"1\" id=\"21\">\r\n"
				+ "      <mxCell style=\"shape=ellipse\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"690\" y=\"70\" width=\"35\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </bundle>\r\n"
				+ "    <rel_general_bundle type=\"relation\" id=\"22\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"16\" target=\"21\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_bundle>\r\n" + "    <rel_general_bundle type=\"relation\" id=\"23\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"14\" target=\"21\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_bundle>\r\n" + "    <rel_general_bundle type=\"relation\" id=\"24\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"13\" target=\"21\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_bundle>\r\n" + "    <rel_general_bundle type=\"relation\" id=\"25\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"15\" target=\"21\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_bundle>\r\n" + "    <rel_general_bundle type=\"relation\" id=\"26\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"12\" target=\"21\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_bundle>\r\n" + "    <rel_general_bundle type=\"relation\" id=\"27\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"11\" target=\"21\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_bundle>\r\n" + "    <rel_bundle_root type=\"relation\" id=\"28\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"21\" target=\"1\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_bundle_root>\r\n"
				+ "    <bundle label=\"bundle\" type=\"bundle\" bundleType=\"OR\" lowRange=\"1\" highRange=\"1\" id=\"29\">\r\n"
				+ "      <mxCell style=\"shape=ellipse\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"364.5\" y=\"124.5\" width=\"35\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </bundle>\r\n" + "    <rel_bundle_root type=\"relation\" id=\"30\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"29\" target=\"1\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_bundle_root>\r\n" + "    <rel_general_bundle type=\"relation\" id=\"31\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"10\" target=\"29\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_bundle>\r\n" + "    <rel_general_bundle type=\"relation\" id=\"32\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"17\" target=\"29\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_bundle>\r\n"
				+ "    <general label=\"Jefe de departamento\" type=\"general\" id=\"33\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"934.5\" y=\"271\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"mandatory\" id=\"34\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"33\" target=\"16\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"mandatory\" id=\"35\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"33\" target=\"11\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"mandatory\" id=\"36\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"33\" target=\"12\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"mandatory\" id=\"37\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"33\" target=\"15\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"mandatory\" id=\"38\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"33\" target=\"13\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"mandatory\" id=\"39\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"33\" target=\"14\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <general label=\"Ingenieria Sistemas\" type=\"general\" id=\"40\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"580\" y=\"350\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <general label=\"Ingenieria Telematica\" type=\"general\" id=\"41\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"600\" y=\"400\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <general label=\"Ingenieria Industria\" type=\"general\" id=\"42\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"810\" y=\"370\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"mandatory\" id=\"43\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"40\" target=\"11\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"mandatory\" id=\"44\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"41\" target=\"11\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <general label=\"Ingenieria civil\" type=\"general\" id=\"45\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"960\" y=\"360\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <general label=\"Ingenieria electrica\" type=\"general\" id=\"46\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"1190\" y=\"360\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"mandatory\" id=\"47\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"42\" target=\"12\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"mandatory\" id=\"48\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"45\" target=\"12\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"optional\" id=\"49\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"46\" target=\"12\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"excludes\" id=\"50\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"46\" target=\"45\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <general label=\"Administracion\" type=\"general\" id=\"51\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"1130\" y=\"150\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <general label=\"Economia y finanzas esteriores\" type=\"general\" id=\"52\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"1150\" y=\"210\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"mandatory\" id=\"53\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"52\" target=\"13\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"mandatory\" id=\"54\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"51\" target=\"13\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n" + "    <general label=\"Sociologia\" type=\"general\" id=\"55\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"1140\" y=\"50\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <general label=\"Sicologia\" type=\"general\" id=\"56\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"1180\" y=\"100\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <general label=\"Antropologia\" type=\"general\" id=\"57\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"1030\" y=\"30\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"mandatory\" id=\"58\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"56\" target=\"14\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"optional\" id=\"59\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"55\" target=\"14\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"optional\" id=\"60\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"57\" target=\"14\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n" + "    <general label=\"Musica\" type=\"general\" id=\"62\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"900\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"mandatory\" id=\"63\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"62\" target=\"14\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"requires\" id=\"64\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"57\" target=\"62\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <general label=\"Director de carrera\" type=\"general\" id=\"65\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"878\" y=\"460.5\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <general label=\"Director de estudiantes\" type=\"general\" id=\"66\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"264.5\" y=\"420\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"mandatory\" id=\"67\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"66\" target=\"17\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"mandatory\" id=\"68\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"65\" target=\"40\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"mandatory\" id=\"69\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"65\" target=\"41\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"mandatory\" id=\"70\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"65\" target=\"42\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"mandatory\" id=\"71\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"65\" target=\"45\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"mandatory\" id=\"72\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"65\" target=\"46\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"mandatory\" id=\"73\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"65\" target=\"52\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"mandatory\" id=\"74\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"65\" target=\"51\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"mandatory\" id=\"75\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"65\" target=\"56\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"mandatory\" id=\"76\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"65\" target=\"55\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"mandatory\" id=\"77\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"65\" target=\"57\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"mandatory\" id=\"78\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"65\" target=\"62\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <leaf label=\"I2T\" type=\"leaf\" selected=\"1\" id=\"80\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"101\" y=\"320\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </leaf>\r\n"
				+ "    <rel_leaf_general type=\"relation\" relType=\"mandatory\" id=\"81\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"80\" target=\"10\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_leaf_general>\r\n"
				+ "    <general label=\"Investigacion Estudiantil\" type=\"general\" id=\"82\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"12\" y=\"372\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </general>\r\n"
				+ "    <rel_general_general type=\"relation\" relType=\"optional\" id=\"83\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"82\" target=\"10\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_general_general>\r\n"
				+ "    <leaf label=\"Voluntariado Investigacion\" type=\"leaf\" selected=\"1\" id=\"84\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry y=\"454.5\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </leaf>\r\n"
				+ "    <rel_leaf_general type=\"relation\" relType=\"optional\" id=\"85\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"84\" target=\"82\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_leaf_general>\r\n"
				+ "    <leaf label=\"Monitores Investigacion\" type=\"leaf\" selected=\"1\" id=\"86\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"180\" y=\"480\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </leaf>\r\n"
				+ "    <rel_leaf_general type=\"relation\" relType=\"mandatory\" id=\"87\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"86\" target=\"82\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_leaf_general>\r\n"
				+ "    <leaf label=\"Programas de acompañamiento\" type=\"leaf\" selected=\"1\" id=\"88\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"380\" y=\"395.5\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </leaf>\r\n"
				+ "    <rel_leaf_general type=\"relation\" relType=\"mandatory\" id=\"89\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"88\" target=\"17\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_leaf_general>\r\n"
				+ "    <leaf label=\"Sicologos\" type=\"leaf\" selected=\"1\" id=\"90\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"441\" y=\"460\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </leaf>\r\n"
				+ "    <leaf label=\"Profesores Deportes\" type=\"leaf\" selected=\"1\" id=\"91\">\r\n"
				+ "      <mxCell style=\"\" vertex=\"1\" parent=\"feature\">\r\n"
				+ "        <mxGeometry x=\"320\" y=\"520\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </leaf>\r\n"
				+ "    <rel_leaf_general type=\"relation\" relType=\"optional\" id=\"92\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"90\" target=\"66\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_leaf_general>\r\n"
				+ "    <rel_leaf_general type=\"relation\" relType=\"optional\" id=\"93\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"91\" target=\"66\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_leaf_general>\r\n"
				+ "    <rel_leaf_leaf type=\"relation\" relType=\"excludes\" id=\"94\">\r\n"
				+ "      <mxCell edge=\"1\" parent=\"feature\" source=\"91\" target=\"90\">\r\n"
				+ "        <mxGeometry relative=\"1\" as=\"geometry\"/>\r\n" + "      </mxCell>\r\n"
				+ "    </rel_leaf_leaf>\r\n" + "    <mxCell id=\"component\" parent=\"0\" visible=\"0\"/>\r\n"
				+ "    <mxCell id=\"binding_feature_component\" parent=\"0\" visible=\"0\"/>\r\n"
				+ "    <leaf label=\"I2T\" type=\"leaf\" selected=\"1\" id=\"clon80\">\r\n"
				+ "      <mxCell style=\"fillColor=#DCDCDC;\" vertex=\"1\" parent=\"binding_feature_component\">\r\n"
				+ "        <mxGeometry x=\"40\" y=\"320\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </leaf>\r\n"
				+ "    <leaf label=\"Voluntariado Investigacion\" type=\"leaf\" selected=\"1\" id=\"clon84\">\r\n"
				+ "      <mxCell style=\"fillColor=#DCDCDC;\" vertex=\"1\" parent=\"binding_feature_component\">\r\n"
				+ "        <mxGeometry x=\"50\" y=\"430\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </leaf>\r\n"
				+ "    <leaf label=\"Monitores Investigacion\" type=\"leaf\" selected=\"1\" id=\"clon86\">\r\n"
				+ "      <mxCell style=\"fillColor=#DCDCDC;\" vertex=\"1\" parent=\"binding_feature_component\">\r\n"
				+ "        <mxGeometry x=\"180\" y=\"480\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </leaf>\r\n"
				+ "    <leaf label=\"Programas de acompañamiento\" type=\"leaf\" selected=\"1\" id=\"clon88\">\r\n"
				+ "      <mxCell style=\"fillColor=#DCDCDC;\" vertex=\"1\" parent=\"binding_feature_component\">\r\n"
				+ "        <mxGeometry x=\"430\" y=\"410\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </leaf>\r\n"
				+ "    <leaf label=\"Sicologos\" type=\"leaf\" selected=\"1\" id=\"clon90\">\r\n"
				+ "      <mxCell style=\"fillColor=#DCDCDC;\" vertex=\"1\" parent=\"binding_feature_component\">\r\n"
				+ "        <mxGeometry x=\"380\" y=\"460\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </leaf>\r\n"
				+ "    <leaf label=\"Profesores Deportes\" type=\"leaf\" selected=\"1\" id=\"clon91\">\r\n"
				+ "      <mxCell style=\"fillColor=#DCDCDC;\" vertex=\"1\" parent=\"binding_feature_component\">\r\n"
				+ "        <mxGeometry x=\"390\" y=\"510\" width=\"100\" height=\"35\" as=\"geometry\"/>\r\n"
				+ "      </mxCell>\r\n" + "    </leaf>\r\n" + "  </root>\r\n" + "</mxGraphModel>";
		String result = "model  Auto_generated\r\n" + "elements: \r\n" + "	boolean Director_de_estudiantes\r\n"
				+ "	boolean Programas_de_acompañamiento\r\n" + "	boolean Ingenieria_civil\r\n"
				+ "	boolean Ingenieria_electrica\r\n" + "	boolean Sicologos\r\n" + "	boolean Profesores_Deportes\r\n"
				+ "	boolean Administracion\r\n" + "	boolean Economia_y_finanzas_esteriores\r\n"
				+ "	boolean Departamento_de_investigacion\r\n" + "	boolean Departamento_TIC\r\n"
				+ "	boolean Jefe_de_departamento\r\n" + "	boolean Sociologia\r\n"
				+ "	boolean Departamento_de_ingenieria\r\n" + "	boolean Sicologia\r\n"
				+ "	boolean Departamento_de_economía\r\n" + "	boolean Antropologia\r\n"
				+ "	boolean Departamento_de_humanidades\r\n" + "	boolean Departamento_de_Salud\r\n"
				+ "	boolean Departamento_de_ciencias_exactas\r\n" + "	boolean Bienestar_Universitario\r\n"
				+ "	boolean Universidad\r\n" + "	boolean Direccion_Academica\r\n"
				+ "	boolean Departamento_de_finanzas\r\n" + "	boolean Departamento_de_recursos_humanos\r\n"
				+ "	boolean I2T\r\n" + "	boolean Investigacion_Estudiantil\r\n" + "	boolean Ingenieria_Sistemas\r\n"
				+ "	boolean Musica\r\n" + "	boolean Voluntariado_Investigacion\r\n"
				+ "	boolean Ingenieria_Telematica\r\n" + "	boolean Ingenieria_Industria\r\n"
				+ "	boolean Monitores_Investigacion\r\n" + "	boolean Director_de_carrera\r\n" + "relations:\r\n"
				+ "	r0:group(Universidad,[Departamento_de_investigacion, Bienestar_Universitario])[1,*]\r\n"
				+ "	r1: coreElements(Universidad)\r\n"
				+ "	r2:group(Universidad,[Direccion_Academica, Departamento_de_finanzas, Departamento_de_recursos_humanos])[1,*]\r\n"
				+ "	r3:group(Universidad,[Departamento_de_ciencias_exactas, Departamento_de_humanidades, Departamento_de_economía, Departamento_de_Salud, Departamento_de_ingenieria, Departamento_TIC])[1,1]\r\n"
				+ "	r4:decomposition(Jefe_de_departamento,[Departamento_de_ciencias_exactas])<1>\r\n"
				+ "	r5:decomposition(Jefe_de_departamento,[Departamento_TIC])<1>\r\n"
				+ "	r6:decomposition(Jefe_de_departamento,[Departamento_de_ingenieria])<1>\r\n"
				+ "	r7:decomposition(Jefe_de_departamento,[Departamento_de_Salud])<1>\r\n"
				+ "	r8:decomposition(Jefe_de_departamento,[Departamento_de_economía])<1>\r\n"
				+ "	r9:decomposition(Jefe_de_departamento,[Departamento_de_humanidades])<1>\r\n"
				+ "	r10:decomposition(Ingenieria_Sistemas,[Departamento_TIC])<1>\r\n"
				+ "	r11:decomposition(Ingenieria_Telematica,[Departamento_TIC])<1>\r\n"
				+ "	r12:decomposition(Ingenieria_Industria,[Departamento_de_ingenieria])<1>\r\n"
				+ "	r13:decomposition(Ingenieria_civil,[Departamento_de_ingenieria])<1>\r\n"
				+ "	r14:decomposition(Departamento_de_ingenieria,[Ingenieria_electrica])<0>\r\n"
				+ "	r15: mutex(Ingenieria_civil, Ingenieria_electrica)\r\n"
				+ "	r16:decomposition(Economia_y_finanzas_esteriores,[Departamento_de_economía])<1>\r\n"
				+ "	r17:decomposition(Administracion,[Departamento_de_economía])<1>\r\n"
				+ "	r18:decomposition(Sicologia,[Departamento_de_humanidades])<1>\r\n"
				+ "	r19:decomposition(Departamento_de_humanidades,[Sociologia])<0>\r\n"
				+ "	r20:decomposition(Departamento_de_humanidades,[Antropologia])<0>\r\n"
				+ "	r21:decomposition(Musica,[Departamento_de_humanidades])<1>\r\n"
				+ "	r22: implies(Antropologia,Musica)\r\n"
				+ "	r23:decomposition(Director_de_estudiantes,[Bienestar_Universitario])<1>\r\n"
				+ "	r24:decomposition(Director_de_carrera,[Ingenieria_Sistemas])<1>\r\n"
				+ "	r25:decomposition(Director_de_carrera,[Ingenieria_Telematica])<1>\r\n"
				+ "	r26:decomposition(Director_de_carrera,[Ingenieria_Industria])<1>\r\n"
				+ "	r27:decomposition(Director_de_carrera,[Ingenieria_civil])<1>\r\n"
				+ "	r28:decomposition(Director_de_carrera,[Ingenieria_electrica])<1>\r\n"
				+ "	r29:decomposition(Director_de_carrera,[Economia_y_finanzas_esteriores])<1>\r\n"
				+ "	r30:decomposition(Director_de_carrera,[Administracion])<1>\r\n"
				+ "	r31:decomposition(Director_de_carrera,[Sicologia])<1>\r\n"
				+ "	r32:decomposition(Director_de_carrera,[Sociologia])<1>\r\n"
				+ "	r33:decomposition(Director_de_carrera,[Antropologia])<1>\r\n"
				+ "	r34:decomposition(Director_de_carrera,[Musica])<1>\r\n"
				+ "	r35:decomposition(I2T,[Departamento_de_investigacion])<1>\r\n"
				+ "	r36:decomposition(Departamento_de_investigacion,[Investigacion_Estudiantil])<0>\r\n"
				+ "	r37:decomposition(Investigacion_Estudiantil,[Voluntariado_Investigacion])<0>\r\n"
				+ "	r38:decomposition(Monitores_Investigacion,[Investigacion_Estudiantil])<1>\r\n"
				+ "	r39:decomposition(Programas_de_acompañamiento,[Bienestar_Universitario])<1>\r\n"
				+ "	r40:decomposition(Director_de_estudiantes,[Sicologos])<0>\r\n"
				+ "	r41:decomposition(Director_de_estudiantes,[Profesores_Deportes])<0>\r\n"
				+ "	r42: mutex(Sicologos, Profesores_Deportes)\r\n" + "\r\n";

		try {
			String variamosResult = variamosXMLToHlvlParser.parse(xml);
//			System.out.println(result);
//			System.out.println(variamosResult);
			assertTrue(result.equals(variamosResult));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void CommonTest() {

		ParsingParameters params = new ParsingParameters();
		params.setInputPath("test-data/xmlToHLVLFiles/commonTest");
		params.setOutputPath("test-data/HLVLFiles/");
		params.setTargetName("commonTest");
		variamosXMLToHlvlParser = new VariamosXMLToHlvlParser(params);
		try {
			variamosXMLToHlvlParser.parse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String resultado = "model  commonTest_generated\r\n" + "elements: \r\n" + "	boolean Sala_de_juegos\r\n"
				+ "	boolean Bañera\r\n" + "	boolean Cuarto_secundario\r\n" + "	boolean ducha\r\n"
				+ "	boolean Lava_Platos\r\n" + "	boolean Garaje\r\n" + "	boolean Orno\r\n" + "	boolean Casa\r\n"
				+ "	boolean Baño\r\n" + "	boolean Cocina\r\n" + "	boolean Cuarto_Principal\r\n"
				+ "	boolean Biblioteca\r\n" + "relations:\r\n" + "	r0: coreElements(Casa)\r\n"
				+ "	r1:group(Casa,[Cocina, Baño, Cuarto_Principal, Cuarto_secundario])[1,*]\r\n"
				+ "	r2:group(Casa,[Biblioteca, Sala_de_juegos])[1,*]\r\n" + "	r3:group(Baño,[Bañera, ducha])[1,*]\r\n"
				+ "	r4:decomposition(Lava_Platos,[Cocina])<1>\r\n" + "	r5:decomposition(Cocina,[Orno])<0>\r\n"
				+ "	r6: implies(Cuarto_Principal,Baño)\r\n" + "	r7:decomposition(Baño,[Cuarto_secundario])<0>\r\n"
				+ "	r8:decomposition(Casa,[Garaje])<0>\r\n" + "	r9: mutex(Biblioteca, Sala_de_juegos)\r\n";

		try {
			File file = new File("test-data/HLVLFiles/commonTest.hlvl");
			FileReader f = new FileReader(file);
			BufferedReader in = new BufferedReader(f);
			String linea = in.readLine();
			String datosHLVL = "";
			while (!linea.equals("")) {
				datosHLVL += linea + "\n";
				linea = in.readLine();

			}
			// System.out.println(datosHLVL);
			// System.out.println(resultado);
			assertTrue(datosHLVL.equals(resultado));
			in.close();
			f.close();
		} catch (IOException e) {
			fail();
		}

	}

	@Test
	public void smallTest() {

		ParsingParameters params = new ParsingParameters();
		params.setInputPath("test-data/xmlToHLVLFiles/smallTest");
		params.setOutputPath("test-data/HLVLFiles/");
		params.setTargetName("smallTest");
		variamosXMLToHlvlParser = new VariamosXMLToHlvlParser(params);
		try {
			variamosXMLToHlvlParser.parse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String resultado = "model  smallTest_generated\r\n" + "elements: \r\n" + "	boolean casa\r\n"
				+ "	boolean baño\r\n" + "	boolean garaje\r\n" + "	boolean cuartoPrincipal\r\n"
				+ "	boolean AnteMinusJardin\r\n" + "	boolean cuartoInvitador\r\n" + "	boolean cocina\r\n"
				+ "relations:\r\n" + "	r0: coreElements(casa)\r\n"
				+ "	r1:group(casa,[garaje, AnteMinusJardin])[1,*]\r\n" + "	r2:group(casa,[cocina, baño])[1,1]\r\n"
				+ "	r3:decomposition(cuartoPrincipal,[casa])<1>\r\n"
				+ "	r4:decomposition(casa,[cuartoInvitador])<0>\r\n" + "	r5: implies(cuartoPrincipal,garaje)\r\n"
				+ "	r6: mutex(AnteMinusJardin, cuartoInvitador)\r\n";

		try {
			File file = new File("test-data/HLVLFiles/smallTest.hlvl");
			FileReader f = new FileReader(file);
			BufferedReader in = new BufferedReader(f);
			String linea = in.readLine();
			String datosHLVL = "";
			while (!linea.equals("")) {
				datosHLVL += linea + "\n";
				linea = in.readLine();

			}
//			System.out.println(datosHLVL);
//			System.out.println(resultado);
			assertTrue(datosHLVL.equals(resultado));
			in.close();
			f.close();
		} catch (IOException e) {
			fail();
		}
	}

	@Test
	public void bigTest() {

		ParsingParameters params = new ParsingParameters();
		params.setInputPath("test-data/xmlToHLVLFiles/bigTest");
		params.setOutputPath("test-data/HLVLFiles/");
		params.setTargetName("bigTest");
		variamosXMLToHlvlParser = new VariamosXMLToHlvlParser(params);
		try {
			variamosXMLToHlvlParser.parse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String resultado = "model  SmallTest_generated\r\n" + "elements: \r\n" + "	boolean Director_de_estudiantes\r\n"
				+ "	boolean Programas_de_acompañamiento\r\n" + "	boolean Ingenieria_civil\r\n"
				+ "	boolean Ingenieria_electrica\r\n" + "	boolean Sicologos\r\n" + "	boolean Profesores_Deportes\r\n"
				+ "	boolean Administracion\r\n" + "	boolean Economia_y_finanzas_esteriores\r\n"
				+ "	boolean Departamento_de_investigacion\r\n" + "	boolean Departamento_TIC\r\n"
				+ "	boolean Jefe_de_departamento\r\n" + "	boolean Sociologia\r\n"
				+ "	boolean Departamento_de_ingenieria\r\n" + "	boolean Sicologia\r\n"
				+ "	boolean Departamento_de_economía\r\n" + "	boolean Antropologia\r\n"
				+ "	boolean Departamento_de_humanidades\r\n" + "	boolean Departamento_de_Salud\r\n"
				+ "	boolean Departamento_de_ciencias_exactas\r\n" + "	boolean Bienestar_Universitario\r\n"
				+ "	boolean Universidad\r\n" + "	boolean Direccion_Academica\r\n"
				+ "	boolean Departamento_de_finanzas\r\n" + "	boolean Departamento_de_recursos_humanos\r\n"
				+ "	boolean I2T\r\n" + "	boolean Investigacion_Estudiantil\r\n" + "	boolean Ingenieria_Sistemas\r\n"
				+ "	boolean Musica\r\n" + "	boolean Voluntariado_Investigacion\r\n"
				+ "	boolean Ingenieria_Telematica\r\n" + "	boolean Ingenieria_Industria\r\n"
				+ "	boolean Monitores_Investigacion\r\n" + "	boolean Director_de_carrera\r\n" + "relations:\r\n"
				+ "	r0:group(Universidad,[Departamento_de_investigacion, Bienestar_Universitario])[1,*]\r\n"
				+ "	r1: coreElements(Universidad)\r\n"
				+ "	r2:group(Universidad,[Direccion_Academica, Departamento_de_finanzas, Departamento_de_recursos_humanos])[1,*]\r\n"
				+ "	r3:group(Universidad,[Departamento_de_ciencias_exactas, Departamento_de_humanidades, Departamento_de_economía, Departamento_de_Salud, Departamento_de_ingenieria, Departamento_TIC])[1,1]\r\n"
				+ "	r4:decomposition(Jefe_de_departamento,[Departamento_de_ciencias_exactas])<1>\r\n"
				+ "	r5:decomposition(Jefe_de_departamento,[Departamento_TIC])<1>\r\n"
				+ "	r6:decomposition(Jefe_de_departamento,[Departamento_de_ingenieria])<1>\r\n"
				+ "	r7:decomposition(Jefe_de_departamento,[Departamento_de_Salud])<1>\r\n"
				+ "	r8:decomposition(Jefe_de_departamento,[Departamento_de_economía])<1>\r\n"
				+ "	r9:decomposition(Jefe_de_departamento,[Departamento_de_humanidades])<1>\r\n"
				+ "	r10:decomposition(Ingenieria_Sistemas,[Departamento_TIC])<1>\r\n"
				+ "	r11:decomposition(Ingenieria_Telematica,[Departamento_TIC])<1>\r\n"
				+ "	r12:decomposition(Ingenieria_Industria,[Departamento_de_ingenieria])<1>\r\n"
				+ "	r13:decomposition(Ingenieria_civil,[Departamento_de_ingenieria])<1>\r\n"
				+ "	r14:decomposition(Departamento_de_ingenieria,[Ingenieria_electrica])<0>\r\n"
				+ "	r15: mutex(Ingenieria_civil, Ingenieria_electrica)\r\n"
				+ "	r16:decomposition(Economia_y_finanzas_esteriores,[Departamento_de_economía])<1>\r\n"
				+ "	r17:decomposition(Administracion,[Departamento_de_economía])<1>\r\n"
				+ "	r18:decomposition(Sicologia,[Departamento_de_humanidades])<1>\r\n"
				+ "	r19:decomposition(Departamento_de_humanidades,[Sociologia])<0>\r\n"
				+ "	r20:decomposition(Departamento_de_humanidades,[Antropologia])<0>\r\n"
				+ "	r21:decomposition(Musica,[Departamento_de_humanidades])<1>\r\n"
				+ "	r22: implies(Antropologia,Musica)\r\n"
				+ "	r23:decomposition(Director_de_estudiantes,[Bienestar_Universitario])<1>\r\n"
				+ "	r24:decomposition(Director_de_carrera,[Ingenieria_Sistemas])<1>\r\n"
				+ "	r25:decomposition(Director_de_carrera,[Ingenieria_Telematica])<1>\r\n"
				+ "	r26:decomposition(Director_de_carrera,[Ingenieria_Industria])<1>\r\n"
				+ "	r27:decomposition(Director_de_carrera,[Ingenieria_civil])<1>\r\n"
				+ "	r28:decomposition(Director_de_carrera,[Ingenieria_electrica])<1>\r\n"
				+ "	r29:decomposition(Director_de_carrera,[Economia_y_finanzas_esteriores])<1>\r\n"
				+ "	r30:decomposition(Director_de_carrera,[Administracion])<1>\r\n"
				+ "	r31:decomposition(Director_de_carrera,[Sicologia])<1>\r\n"
				+ "	r32:decomposition(Director_de_carrera,[Sociologia])<1>\r\n"
				+ "	r33:decomposition(Director_de_carrera,[Antropologia])<1>\r\n"
				+ "	r34:decomposition(Director_de_carrera,[Musica])<1>\r\n"
				+ "	r35:decomposition(I2T,[Departamento_de_investigacion])<1>\r\n"
				+ "	r36:decomposition(Departamento_de_investigacion,[Investigacion_Estudiantil])<0>\r\n"
				+ "	r37:decomposition(Investigacion_Estudiantil,[Voluntariado_Investigacion])<0>\r\n"
				+ "	r38:decomposition(Monitores_Investigacion,[Investigacion_Estudiantil])<1>\r\n"
				+ "	r39:decomposition(Programas_de_acompañamiento,[Bienestar_Universitario])<1>\r\n"
				+ "	r40:decomposition(Director_de_estudiantes,[Sicologos])<0>\r\n"
				+ "	r41:decomposition(Director_de_estudiantes,[Profesores_Deportes])<0>\r\n"
				+ "	r42: mutex(Sicologos, Profesores_Deportes)\r\n";

		try {
			File file = new File("test-data/HLVLFiles/bigTest.hlvl");
			FileReader f = new FileReader(file);
			BufferedReader in = new BufferedReader(f);
			String linea = in.readLine();

			String datosHLVL = "";
			while (!linea.equals("")) {
				datosHLVL += linea + "\n";
				linea = in.readLine();

			}
//			System.out.println(datosHLVL);
//			System.out.println(resultado);
			assertTrue(datosHLVL.equals(resultado));
			in.close();
			f.close();
		} catch (IOException e) {
			fail();
		}

	}

}
