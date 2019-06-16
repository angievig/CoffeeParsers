package com.coffee.modelParsers.featureIDEToHLVL;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import com.coffee.modelParsers.featureIDEToHlvlParser.FeatureIDEToHLVL;
import com.coffee.modelParsers.utils.ParsingParameters;

public class TestFeatureIDEToHLVL {

	public static final String COMMONT_PATH_INPUT="temp/DataTestFeatureIDE/featureIDE";
	public static final String COMMONT_PATH_INPUT_ATT="temp/DataTestFeatureIDE/featureIDE/featureIDEWithAtt";
	public static final String COMMONT_PATH_INPUT_CONSTRAINS="temp/DataTestFeatureIDE/featureIDE/featureIDEWithConstrains";
	public static final String COMMONT_PATH_OUTPUT="temp/DataTestFeatureIDE/hlvl";

	private FeatureIDEToHLVL fToH;

	@Test
	public void StringWithConstrains() {
		ParsingParameters params = new ParsingParameters();
		params.setInputPath(COMMONT_PATH_INPUT_CONSTRAINS);
		params.setOutputPath(COMMONT_PATH_OUTPUT);
		params.setTargetName("commonTestWithConstrains");
		fToH = new FeatureIDEToHLVL(params);

		try {
			fToH.parse();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//LA SIGUIENTE EXPRESION NO SE PUEDE LOGRAR CON FEATUREIDE
		//expression (3 <= card . confidentiality AND card.\r\n" + "confidentiality <= 5)
		String resultado ="model  commonTestWithConstrains_generated\n" + 
				"elements: \n" + 
				"	boolean Tetris\n" + 
				"	boolean TRESD_Car_Race\n" + 
				"	boolean Chess\n" + 
				"	boolean Games\n" + 
				"	boolean Task\n" + 
				"	att symbolic maxParConn\n" + 
				"	boolean Video_Call\n" + 
				"	boolean GPS\n" + 
				"	att symbolic size\n" + 
				"	boolean RAM\n" + 
				"	boolean GPU\n" + 
				"	att symbolic speed\n" + 
				"	boolean CPU\n" + 
				"	boolean Processors\n" + 
				"	att symbolic resolution\n" + 
				"	boolean Screen\n" + 
				"	att symbolic speed\n" + 
				"	boolean TRESG_Conector\n" + 
				"	boolean Hardware\n" + 
				"	boolean Mobile_Phone\n" + 
				"relations:\n" + 
				"	r0: coreElements(Mobile_Phone)\n" + 
				"	r1:decomposition(Mobile_Phone,[Hardware])<1>\n" + 
				"	r2:decomposition(speed,[TRESG_Conector])<1>\n" + 
				"	r3:decomposition(Hardware,[TRESG_Conector])<0>\n" + 
				"	r4:decomposition(resolution,[Screen])<1>\n" + 
				"	r5:decomposition(Hardware,[Screen])<1>\n" + 
				"	r6:decomposition(Hardware,[Processors])<1>\n" + 
				"	r7:decomposition(speed,[CPU])<1>\n" + 
				"	r8:decomposition(Processors,[CPU])<0>\n" + 
				"	r9:decomposition(Processors,[GPU])<0>\n" + 
				"	r10:decomposition(size,[RAM])<1>\n" + 
				"	r11:decomposition(Hardware,[RAM])<1>\n" + 
				"	r12:decomposition(Hardware,[GPS])<0>\n" + 
				"	r13:decomposition(maxParConn,[Video_Call])<1>\n" + 
				"	r14:decomposition(Mobile_Phone,[Video_Call])<0>\n" + 
				"	r15:decomposition(Mobile_Phone,[Task])<0>\n" + 
				"	r16:group(Games,[Chess, TRESD_Car_Race, Tetris])[1,*]\n" + 
				"	r17:decomposition(Mobile_Phone,[Games])<0>\n" + 
				"	r18:expression(((Task) => (CPU)))\n" + 
				"	r19:expression((((Video_Call) AND ((~ (Task)) AND ((GPS) OR (GPU)))) => (TRESG_Conector)))\n" + 
				"";

		try {
			File file = new File(COMMONT_PATH_OUTPUT+"/commonTestWithConstrains.hlvl");
			FileReader f = new FileReader(file);
			BufferedReader in = new BufferedReader(f);
			String linea = in.readLine();
			String datosHLVL = "";
			while (!linea.equals("")) {
				datosHLVL += linea + "\n";
				linea = in.readLine();
			}
			System.out.println(datosHLVL);
			assertTrue(datosHLVL.equals(resultado));
			in.close();
			f.close();
		} catch (IOException e) {
			fail();
		}
		
	}
	@Test
	public void InputStringCommonTest() {
		fToH= new FeatureIDEToHLVL();
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n" + 
				"<featureModel>\r\n" + 
				"    <properties/>\r\n" + 
				"    <struct>\r\n" + 
				"        <and mandatory=\"true\" name=\"EjemploFeatureIDE\">\r\n" + 
				"            <and mandatory=\"true\" name=\"HardWare\">\r\n" + 
				"                <feature name=\"TRESGConector\"/>\r\n" + 
				"                <feature mandatory=\"true\" name=\"ScreenResolution\"/>\r\n" + 
				"                <and mandatory=\"true\" name=\"Processors\">\r\n" + 
				"                    <feature name=\"CPU\"/>\r\n" + 
				"                    <feature name=\"GPU\"/>\r\n" + 
				"                </and>\r\n" + 
				"                <feature mandatory=\"true\" name=\"RAM\"/>\r\n" + 
				"                <feature name=\"GPS\"/>\r\n" + 
				"            </and>\r\n" + 
				"            <feature name=\"VideoCall\"/>\r\n" + 
				"            <feature name=\"Task\"/>\r\n" + 
				"            <or name=\"Games\">\r\n" + 
				"                <feature name=\"Chess\"/>\r\n" + 
				"                <feature name=\"TRESDCarRace\"/>\r\n" + 
				"                <feature name=\"Testris\"/>\r\n" + 
				"            </or>\r\n" + 
				"        </and>\r\n" + 
				"    </struct>\r\n" + 
				"    <constraints/>\r\n" + 
				"    <calculations Auto=\"true\" Constraints=\"true\" Features=\"true\" Redundant=\"true\" Tautology=\"true\"/>\r\n" + 
				"    <comments/>\r\n" + 
				"    <featureOrder userDefined=\"false\"/>\r\n" + 
				"</featureModel>";
		
		String resultado = "model  Auto_generated\n" + 
				"elements: \n" + 
				"	boolean Testris\n"+
				"	boolean TRESDCarRace\n" + 
				"	boolean Chess\n" + 
				"	"+ 
				"boolean Games\n" + 
				"	boolean Task\n"
				+ "	boolean VideoCall\n" + 
				"	boolean GPS\n" + 
				"	boolean RAM\n" + 
				"	boolean GPU\n"
				+ "	boolean CPU\n" + 
				"	boolean Processors\n" + 
				"	boolean ScreenResolution\n"
				+ "	boolean TRESGConector\n" + 
				"	boolean HardWare\n" + 
				"	boolean EjemploFeatureIDE\n"
				+ "relations:\n" + 
				"	r0: coreElements(EjemploFeatureIDE)\n"
				+ "	r1:decomposition(EjemploFeatureIDE,[HardWare])<1>\n"
				+ "	r2:decomposition(HardWare,[TRESGConector])<0>\n"
				+ "	r3:decomposition(HardWare,[ScreenResolution])<1>\n"
				+ "	r4:decomposition(HardWare,[Processors])<1>\n" + "	r5:decomposition(Processors,[CPU])<0>\n"
				+ "	r6:decomposition(Processors,[GPU])<0>\n" + "	r7:decomposition(HardWare,[RAM])<1>\n"
				+ "	r8:decomposition(HardWare,[GPS])<0>\n" + "	r9:decomposition(EjemploFeatureIDE,[VideoCall])<0>\n"
				+ "	r10:decomposition(EjemploFeatureIDE,[Task])<0>\n"
				+ "	r11:group(Games,[Chess, TRESDCarRace, Testris])[1,*]\n"
				+ "	r12:decomposition(EjemploFeatureIDE,[Games])<0>\n";;

		try {
			String variamosResult = fToH.parse(xml);
			assertTrue(resultado.equals(variamosResult));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void InputStringCommonTestV2() {

		fToH= new FeatureIDEToHLVL();
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n" + 
				"<featureModel>\r\n" + 
				"    <properties/>\r\n" + 
				"    <struct>\r\n" + 
				"        <and mandatory=\"true\" name=\"EjemploFeatureIDE\">\r\n" + 
				"            <or name=\"Games\">\r\n" + 
				"                <feature name=\"Testris\"/>\r\n" + 
				"                <feature name=\"TRESDCarRace\"/>\r\n" + 
				"                <feature name=\"Chess\"/>\r\n" + 
				"            </or>\r\n" + 
				"            <feature name=\"Task\"/>\r\n" + 
				"            <feature name=\"VideoCall\"/>\r\n" + 
				"            <and mandatory=\"true\" name=\"HardWare\">\r\n" + 
				"                <feature name=\"GPS\"/>\r\n" + 
				"                <feature mandatory=\"true\" name=\"RAM\"/>\r\n" + 
				"                <alt mandatory=\"true\" name=\"Processors\">\r\n" + 
				"                    <feature name=\"GPU\"/>\r\n" + 
				"                    <feature name=\"CPU\"/>\r\n" + 
				"                </alt>\r\n" + 
				"                <feature mandatory=\"true\" name=\"ScreenResolution\"/>\r\n" + 
				"                <feature name=\"TRESGConector\"/>\r\n" + 
				"            </and>\r\n" + 
				"        </and>\r\n" + 
				"    </struct>\r\n" + 
				"    <constraints>\r\n" + 
				"        <rule>\r\n" + 
				"            <imp>\r\n" + 
				"                <var>TRESGConector</var>\r\n" + 
				"                <var>GPU</var>\r\n" + 
				"            </imp>\r\n" + 
				"        </rule>\r\n" + 
				"        <rule>\r\n" + 
				"            <imp>\r\n" + 
				"                <var>GPS</var>\r\n" + 
				"                <not>\r\n" + 
				"                    <var>Chess</var>\r\n" + 
				"                </not>\r\n" + 
				"            </imp>\r\n" + 
				"        </rule>\r\n" + 
				"    </constraints>\r\n" + 
				"    <calculations Auto=\"true\" Constraints=\"true\" Features=\"true\" Redundant=\"true\" Tautology=\"true\"/>\r\n" + 
				"    <comments/>\r\n" + 
				"    <featureOrder userDefined=\"false\"/>\r\n" + 
				"</featureModel>\r\n" + 
				"";
		
		String resultado = "model  Auto_generated\n" + 
				"elements: \n" + 
				"	boolean TRESGConector\n" + 
				"	boolean ScreenResolution\n" + 
				"	boolean CPU\n" + 
				"	boolean GPU\n" + 
				"	boolean Processors\n" + 
				"	boolean RAM\n" + 
				"	boolean GPS\n" + 
				"	boolean HardWare\n" + 
				"	boolean VideoCall\n" + 
				"	boolean Task\n" + 
				"	boolean Chess\n" + 
				"	boolean TRESDCarRace\n" + 
				"	boolean Testris\n" + 
				"	boolean Games\n" + 
				"	boolean EjemploFeatureIDE\n" + 
				"relations:\n" + 
				"	r0: coreElements(EjemploFeatureIDE)\n" + 
				"	r1:group(Games,[Testris, TRESDCarRace, Chess])[1,*]\n" + 
				"	r2:decomposition(EjemploFeatureIDE,[Games])<0>\n" + 
				"	r3:decomposition(EjemploFeatureIDE,[Task])<0>\n" + 
				"	r4:decomposition(EjemploFeatureIDE,[VideoCall])<0>\n" + 
				"	r5:decomposition(EjemploFeatureIDE,[HardWare])<1>\n" + 
				"	r6:decomposition(HardWare,[GPS])<0>\n" + 
				"	r7:decomposition(HardWare,[RAM])<1>\n" + 
				"	r8:group(Processors,[GPU, CPU])	r9:decomposition(HardWare,[Processors])<1>\n" + 
				"	r10:decomposition(HardWare,[ScreenResolution])<1>\n" + 
				"	r11:decomposition(HardWare,[TRESGConector])<0>\n" + 
				"	r12:expression(((TRESGConector) => (GPU)))\n" + 
				"	r13:expression(((GPS) => (~ (Chess))))\n";

		try {
			String variamosResult = fToH.parse(xml);
			
			assertTrue(resultado.equals(variamosResult));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void CommonTest() {

		ParsingParameters params = new ParsingParameters();
		params.setInputPath(COMMONT_PATH_INPUT);
		params.setOutputPath(COMMONT_PATH_OUTPUT);
		params.setTargetName("commonTest");
		fToH = new FeatureIDEToHLVL(params);

		try {
			fToH.parse();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String resultado = "model  commonTest_generated\n" + "elements: \n" + "	boolean Testris\n"
				+ "	boolean TRESDCarRace\n" + "	boolean Chess\n" + "	boolean Games\n" + "	boolean Task\n"
				+ "	boolean VideoCall\n" + "	boolean GPS\n" + "	boolean RAM\n" + "	boolean GPU\n"
				+ "	boolean CPU\n" + "	boolean Processors\n" + "	boolean ScreenResolution\n"
				+ "	boolean TRESGConector\n" + "	boolean HardWare\n" + "	boolean EjemploFeatureIDE\n"
				+ "relations:\n" + "	r0: coreElements(EjemploFeatureIDE)\n"
				+ "	r1:decomposition(EjemploFeatureIDE,[HardWare])<1>\n"
				+ "	r2:decomposition(HardWare,[TRESGConector])<0>\n"
				+ "	r3:decomposition(HardWare,[ScreenResolution])<1>\n"
				+ "	r4:decomposition(HardWare,[Processors])<1>\n" + "	r5:decomposition(Processors,[CPU])<0>\n"
				+ "	r6:decomposition(Processors,[GPU])<0>\n" + "	r7:decomposition(HardWare,[RAM])<1>\n"
				+ "	r8:decomposition(HardWare,[GPS])<0>\n" + "	r9:decomposition(EjemploFeatureIDE,[VideoCall])<0>\n"
				+ "	r10:decomposition(EjemploFeatureIDE,[Task])<0>\n"
				+ "	r11:group(Games,[Chess, TRESDCarRace, Testris])[1,*]\n"
				+ "	r12:decomposition(EjemploFeatureIDE,[Games])<0>\n";
		try {
			File file = new File(COMMONT_PATH_OUTPUT+"/commonTest.hlvl");
			FileReader f = new FileReader(file);
			BufferedReader in = new BufferedReader(f);
			String linea = in.readLine();
			
			String datosHLVL = "";
			while (!linea.equals("")) {
				datosHLVL += linea + "\n";
				linea = in.readLine();
			}
			assertTrue(datosHLVL.equals(resultado));
			in.close();
			f.close();
		} catch (IOException e) {
			fail();
		}

	}
	
	@Test
	public void CommonTestV2() {

		ParsingParameters params = new ParsingParameters();
		params.setInputPath(COMMONT_PATH_INPUT);
		params.setOutputPath(COMMONT_PATH_OUTPUT);
		params.setTargetName("commonTest");
		fToH = new FeatureIDEToHLVL(params);

		try {
			fToH.parse();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String resultado = "model  commonTest0_generated\n" + 
				"elements: \n" + 
				"	boolean TRESGConector\n" + 
				"	boolean ScreenResolution\n" + 
				"	boolean CPU\n" + 
				"	boolean GPU\n" + 
				"	boolean Processors\n" + 
				"	boolean RAM\n" + 
				"	boolean GPS\n" + 
				"	boolean HardWare\n" + 
				"	boolean VideoCall\n" + 
				"	boolean Task\n" + 
				"	boolean Chess\n" + 
				"	boolean TRESDCarRace\n" + 
				"	boolean Testris\n" + 
				"	boolean Games\n" + 
				"	boolean EjemploFeatureIDE\n" + 
				"relations:\n" + 
				"	r0: coreElements(EjemploFeatureIDE)\n" + 
				"	r1:group(Games,[Testris, TRESDCarRace, Chess])[1,*]\n" + 
				"	r2:decomposition(EjemploFeatureIDE,[Games])<0>\n" + 
				"	r3:decomposition(EjemploFeatureIDE,[Task])<0>\n" + 
				"	r4:decomposition(EjemploFeatureIDE,[VideoCall])<0>\n" + 
				"	r5:decomposition(EjemploFeatureIDE,[HardWare])<1>\n" + 
				"	r6:decomposition(HardWare,[GPS])<0>\n" + 
				"	r7:decomposition(HardWare,[RAM])<1>\n" + 
				"	r8:group(Processors,[GPU, CPU])	r9:decomposition(HardWare,[Processors])<1>\n" + 
				"	r10:decomposition(HardWare,[ScreenResolution])<1>\n" + 
				"	r11:decomposition(HardWare,[TRESGConector])<0>\n" + 
				"	r12:expression(((TRESGConector) => (GPU)))\n" + 
				"	r13:expression(((GPS) => (~ (Chess))))\n";
		try {
			File file = new File(COMMONT_PATH_OUTPUT+"/commonTest0.hlvl");
			FileReader f = new FileReader(file);
			BufferedReader in = new BufferedReader(f);
			String linea = in.readLine();
		
			String datosHLVL = "";
			while (!linea.equals("")) {
				datosHLVL += linea + "\n";
				linea = in.readLine();
			}
			assertTrue(datosHLVL.equals(resultado));
			in.close();
			f.close();
		} catch (IOException e) {
			fail();
		}

	}
	
	@Test
	public void testWithAttributes() {		ParsingParameters params = new ParsingParameters();
		params.setInputPath(COMMONT_PATH_INPUT_ATT);
		params.setOutputPath(COMMONT_PATH_OUTPUT);
		params.setTargetName("commonTestWithAttributes");
		fToH = new FeatureIDEToHLVL(params);

		try {
			fToH.parse();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String resultado = "model  commonTestWithAttributes_generated\n" + 
				"elements: \n" + 
				"	boolean Tetris\n" + 
				"	boolean TRESD_Car_Race\n" + 
				"	boolean Chess\n" + 
				"	boolean Games\n" + 
				"	boolean Task\n" + 
				"	att symbolic maxParConn\n" + 
				"	boolean Video_Call\n" + 
				"	boolean GPS\n" + 
				"	att symbolic size\n" + 
				"	boolean RAM\n" + 
				"	boolean GPU\n" + 
				"	att symbolic speed\n" + 
				"	boolean CPU\n" + 
				"	boolean Processors\n" + 
				"	att symbolic resolution\n" + 
				"	boolean Screen\n" + 
				"	att symbolic speed\n" + 
				"	boolean TRESG_Conector\n" + 
				"	boolean Hardware\n" + 
				"	boolean Mobile_Phone\n" + 
				"relations:\n" + 
				"	r0: coreElements(Mobile_Phone)\n" + 
				"	r1:decomposition(Mobile_Phone,[Hardware])<1>\n" + 
				"	r2:decomposition(speed,[TRESG_Conector])<1>\n" + 
				"	r3:decomposition(Hardware,[TRESG_Conector])<0>\n" + 
				"	r4:decomposition(resolution,[Screen])<1>\n" + 
				"	r5:decomposition(Hardware,[Screen])<1>\n" + 
				"	r6:decomposition(Hardware,[Processors])<1>\n" + 
				"	r7:decomposition(speed,[CPU])<1>\n" + 
				"	r8:decomposition(Processors,[CPU])<0>\n" + 
				"	r9:decomposition(Processors,[GPU])<0>\n" + 
				"	r10:decomposition(size,[RAM])<1>\n" + 
				"	r11:decomposition(Hardware,[RAM])<1>\n" + 
				"	r12:decomposition(Hardware,[GPS])<0>\n" + 
				"	r13:decomposition(maxParConn,[Video_Call])<1>\n" + 
				"	r14:decomposition(Mobile_Phone,[Video_Call])<0>\n" + 
				"	r15:decomposition(Mobile_Phone,[Task])<0>\n" + 
				"	r16:group(Games,[Chess, TRESD_Car_Race, Tetris])[1,*]\n" + 
				"	r17:decomposition(Mobile_Phone,[Games])<0>\n" + 
				"";


		try {
			File file = new File(COMMONT_PATH_OUTPUT+"/commonTestWithAttributes.hlvl");
			FileReader f = new FileReader(file);
			BufferedReader in = new BufferedReader(f);
			String linea = in.readLine();
		
			String datosHLVL = "";
			while (!linea.equals("")) {
				datosHLVL += linea + "\n";
				linea = in.readLine();
			}
			System.out.println(datosHLVL);
			assertTrue(datosHLVL.equals(resultado));
			in.close();
			f.close();
		} catch (IOException e) {
			fail();
		}

	}

}
