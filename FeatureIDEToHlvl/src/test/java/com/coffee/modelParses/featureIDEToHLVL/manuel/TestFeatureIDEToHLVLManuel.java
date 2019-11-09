package com.coffee.modelParses.featureIDEToHLVL.manuel;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Test;

import com.coffee.modelParsers.featureIDEToHlvlParser.FeatureIDEToHLVL;
import com.github.coffeeframework.utils.ParsingParameters;

public class TestFeatureIDEToHLVLManuel {

	public static final String COMMONT_PATH_INPUT_MOBILE="temp/DataTestFeatureIDE/featureIDE/TestManuel/MobilePhone";
	public static final String COMMONT_PATH_INPUT_SMART="temp/DataTestFeatureIDE/featureIDE/TestManuel/SmartHome";
	public static final String COMMONT_PATH_INPUT_COMPUTADOR="temp/DataTestFeatureIDE/featureIDE/TestManuel/Computer";
	public static final String COMMONT_PATH_OUTPUT="temp/DataTestFeatureIDE/hlvl/TestManuel/TestResult";

	private FeatureIDEToHLVL fToH;
	private HashSet<String> setElements;
	private HashSet<String> setRelations;
	private ArrayList<String> listValoresCorrectosElements;
	private ArrayList<String> listValoresCorrectosRelations;

	
	public void asignarParamsMobile() {
		setElements = new HashSet<String>();
		setRelations = new HashSet<String>();
		listValoresCorrectosElements = new ArrayList<String>();
		listValoresCorrectosRelations = new ArrayList<String>();
		ParsingParameters params = new ParsingParameters();
		params.setInputPath(COMMONT_PATH_INPUT_MOBILE);
		params.setOutputPath(COMMONT_PATH_OUTPUT);
		params.setTargetName("MobilePhoneGenerated");
		fToH = new FeatureIDEToHLVL(params);
		
		agregarValoresCorrectosMobile();
	}
	
	public void agregarValoresCorrectosMobile() {
		String one = "boolean MP3";
		String two = "boolean Camera";
		String three = "boolean Media";
		String four = "boolean GPS";
		String five	=  "boolean Calls"; 
		String six = "boolean High Resolution";
		String seven = "boolean Colour"; 
		String eight = "boolean Basic";
		String nine = "boolean Screen";
		String ten = "boolean MobilePhone";
		
		listValoresCorrectosElements.add(one);
		listValoresCorrectosElements.add(two);
		listValoresCorrectosElements.add(three);
		listValoresCorrectosElements.add(four);
		listValoresCorrectosElements.add(five);
		listValoresCorrectosElements.add(six);
		listValoresCorrectosElements.add(seven);
		listValoresCorrectosElements.add(eight);
		listValoresCorrectosElements.add(nine);
		listValoresCorrectosElements.add(ten);
		
		String r0 = "group(Screen,[Basic, Colour, High Resolution])";
		String r1 = "decomposition(MobilePhone,[Calls])<1>";
		String r2 = "decomposition(MobilePhone,[GPS])<0>"; 
		String r3 =	"group(Media,[Camera, MP3])[1,*]"; 
		String r4 ="decomposition(MobilePhone,[Media])<0>"; 
		String r5 ="expression(((~ (GPS)) OR (~ (Basic))))"; 
		String r6 ="expression(((High Resolution) OR (~ (Camera))))";
		
		listValoresCorrectosRelations.add(r0);
		listValoresCorrectosRelations.add(r1);
		listValoresCorrectosRelations.add(r2);
		listValoresCorrectosRelations.add(r3);
		listValoresCorrectosRelations.add(r4);
		listValoresCorrectosRelations.add(r5);
		listValoresCorrectosRelations.add(r6);
		
	}
	public void asignarParamsSmart() {
		setElements = new HashSet<String>();
		setRelations = new HashSet<String>();
		ParsingParameters params = new ParsingParameters();
		params.setInputPath(COMMONT_PATH_INPUT_SMART);
		params.setOutputPath(COMMONT_PATH_OUTPUT);
		params.setTargetName("SmartHomeGenerated");
		fToH = new FeatureIDEToHLVL(params);
		
		agregarValoresCorrectosSmart();
	}
	
	public void agregarValoresCorrectosSmart() {
		listValoresCorrectosElements = new ArrayList<String>();
		listValoresCorrectosRelations = new ArrayList<String>();
		String e0 = "boolean WiFi-n";
		String e1 = "boolean WiFi-b";
		String e2 = "boolean 3G";
		String e3 = "boolean Ethernet";
		String e4 = "boolean Internet";
		String e5 = "boolean Digital Media Server";
		String e6 = "boolean Cache";
		String e7 = "boolean Provider B";
		String e8 = "boolean Provider A";
		String e9 = "boolean Providers";
		String e10 = "boolean VideoOnDemand";
		String e11 = "boolean Contents";
		String e12 = "boolean HDTV32";
		String e13 = "boolean PCPlayer";
		String e14 = "boolean HDTV42";
		String e15 = "boolean Movie Players";
		String e16 = "boolean Anti-Theft Alarm";
		String e17 = "boolean Control Panel";
		String e18 = "boolean CellPhone";
		String e19 = "boolean Control System";
		String e20 = "boolean Lightning";
		String e21 = "boolean Smart Home";
		
		listValoresCorrectosElements.add(e0);
		listValoresCorrectosElements.add(e1);
		listValoresCorrectosElements.add(e2);
		listValoresCorrectosElements.add(e3);
		listValoresCorrectosElements.add(e4);
		listValoresCorrectosElements.add(e5);
		listValoresCorrectosElements.add(e6);
		listValoresCorrectosElements.add(e7);
		listValoresCorrectosElements.add(e8);
		listValoresCorrectosElements.add(e9);
		listValoresCorrectosElements.add(e10);
		listValoresCorrectosElements.add(e11);
		listValoresCorrectosElements.add(e12);
		listValoresCorrectosElements.add(e13);
		listValoresCorrectosElements.add(e14);
		listValoresCorrectosElements.add(e15);
		listValoresCorrectosElements.add(e16);
		listValoresCorrectosElements.add(e17);
		listValoresCorrectosElements.add(e18);
		listValoresCorrectosElements.add(e19);
		listValoresCorrectosElements.add(e20);
		listValoresCorrectosElements.add(e21);
		
		String r0 = "decomposition(Smart Home,[Lightning])<1>";
		String r1 = "group(Control System,[CellPhone, Control Panel])[1,*]";
		String r2 = "decomposition(Smart Home,[Control System])<1>";
		String r3 = "decomposition(Smart Home,[Anti-Theft Alarm])<0>";
		String r4 = "group(Movie Players,[HDTV42, PCPlayer, HDTV32])[1,*]";
		String r5 = "decomposition(Smart Home,[Movie Players])<0>";
		String r6 = "group(Contents,[VideoOnDemand, Digital Media Server])[1,*]";
		String r7 = "decomposition(Smart Home,[Contents])<0>";
		String r8 = "group(Providers,[Provider A, Provider B])[1,*]";
		String r9 = "decomposition(VideoOnDemand,[Providers])<1>";
		String r10 = "decomposition(VideoOnDemand,[Cache])<0>";
		String r11 = "group(Internet,[Ethernet, 3G, WiFi-b, WiFi-n])[1,*]";
		String r12 = "decomposition(Smart Home,[Internet])<0>";
		String r13 = "expression(((~ (Anti-Theft Alarm)) OR (Control Panel)))";
		String r14 = "expression(((Internet) OR (~ (VideoOnDemand))))";
		
		listValoresCorrectosRelations.add(r0);
		listValoresCorrectosRelations.add(r1);
		listValoresCorrectosRelations.add(r2);
		listValoresCorrectosRelations.add(r3);
		listValoresCorrectosRelations.add(r4);
		listValoresCorrectosRelations.add(r5);
		listValoresCorrectosRelations.add(r6);
		listValoresCorrectosRelations.add(r7);
		listValoresCorrectosRelations.add(r8);
		listValoresCorrectosRelations.add(r9);
		listValoresCorrectosRelations.add(r10);
		listValoresCorrectosRelations.add(r11);
		listValoresCorrectosRelations.add(r12);
		listValoresCorrectosRelations.add(r13);
		listValoresCorrectosRelations.add(r14);
		
	}
	public void asignarParamsComputador() {
		setElements = new HashSet<String>();
		setRelations = new HashSet<String>();
		ParsingParameters params = new ParsingParameters();
		params.setInputPath(COMMONT_PATH_INPUT_COMPUTADOR);
		params.setOutputPath(COMMONT_PATH_OUTPUT);
		params.setTargetName("ComputadorGenerated");
		fToH = new FeatureIDEToHLVL(params);
		
		agregarValoresCorrectosComputador();
	}
	
	public void agregarValoresCorrectosComputador() {
		listValoresCorrectosElements = new ArrayList<String>();
		listValoresCorrectosRelations = new ArrayList<String>();
		String e0 = "boolean Planta De Poder";
		listValoresCorrectosElements.add(e0);
		String e1 = "boolean 24 Pulgadas";
		listValoresCorrectosElements.add(e1);
		String e2 = "boolean 17 Pulgadas";
		listValoresCorrectosElements.add(e2);
		String e3 = "boolean 14 Pulgadas";
		listValoresCorrectosElements.add(e3);
		String e4 = "boolean Size";
		listValoresCorrectosElements.add(e4);
		String e5 = "boolean 4K";
		listValoresCorrectosElements.add(e5);
		String e6 = "boolean 3D";
		listValoresCorrectosElements.add(e6);
		String e7 = "boolean Tecnologia";
		listValoresCorrectosElements.add(e7);
		String e8 = "boolean Pantalla";
		listValoresCorrectosElements.add(e8);
		String e9 = "boolean NVIDIA";
		listValoresCorrectosElements.add(e9);
		String e10 = "boolean ATI";
		listValoresCorrectosElements.add(e10);
		String e11 = "boolean Tarjeta Grafica";
		listValoresCorrectosElements.add(e11);
		String e12 = "boolean 10 TB";
		listValoresCorrectosElements.add(e12);
		String e13 = "boolean 5 TB";
		listValoresCorrectosElements.add(e13);
		String e14 = "boolean 2 TB";
		listValoresCorrectosElements.add(e14);
		String e15 = "boolean 1 TB";
		listValoresCorrectosElements.add(e15);
		String e16 = "boolean 500 GB";
		listValoresCorrectosElements.add(e16);
		String e17 = "boolean Disco";
		listValoresCorrectosElements.add(e17);
		String e18 = "boolean 64 GB";
		listValoresCorrectosElements.add(e18);
		String e19 = "boolean 32 GB";
		listValoresCorrectosElements.add(e19);
		String e20 = "boolean 16 GB";
		listValoresCorrectosElements.add(e20);
		String e21 = "boolean 8 GB";
		listValoresCorrectosElements.add(e21);
		String e22 = "boolean 4 GB";
		listValoresCorrectosElements.add(e22);
		String e23 = "boolean Memoria";
		listValoresCorrectosElements.add(e23);
		String e24 = "boolean Intel";
		listValoresCorrectosElements.add(e24);
		String e25 = "boolean AMD";
		listValoresCorrectosElements.add(e25);
		String e26 = "boolean Procesador";
		listValoresCorrectosElements.add(e26);
		String e27 = "boolean CPU";
		listValoresCorrectosElements.add(e27);
		String e28 = "boolean Windows Server";
		listValoresCorrectosElements.add(e28);
		String e29 = "boolean Windows 10";
		listValoresCorrectosElements.add(e29);
		String e30 = "boolean Windows";
		listValoresCorrectosElements.add(e30);
		String e31 = "boolean OS X Server";
		listValoresCorrectosElements.add(e31);
		String e32 = "boolean Yosemite";
		listValoresCorrectosElements.add(e32);
		String e33 = "boolean Mac SO";
		listValoresCorrectosElements.add(e33);
		String e34 = "boolean Red Hat";
		listValoresCorrectosElements.add(e34);
		String e35 = "boolean Fedora";
		listValoresCorrectosElements.add(e35);
		String e36 = "boolean Linux";
		listValoresCorrectosElements.add(e36);
		String e37 = "boolean SO";
		listValoresCorrectosElements.add(e37);
		String e38 = "boolean Clon";
		listValoresCorrectosElements.add(e38);
		String e39 = "boolean Apple";
		listValoresCorrectosElements.add(e39);
		String e40 = "boolean Lenovo";
		listValoresCorrectosElements.add(e40);
		String e41 = "boolean Toshiba";
		listValoresCorrectosElements.add(e41);
		String e42 = "boolean Dell";
		listValoresCorrectosElements.add(e42);
		String e43 = "boolean Marca";
		listValoresCorrectosElements.add(e43);
		String e44 = "boolean Portatil";
		listValoresCorrectosElements.add(e44);
		String e45 = "boolean Mesa";
		listValoresCorrectosElements.add(e45);
		String e46 = "boolean Personal";
		listValoresCorrectosElements.add(e46);
		String e47 = "boolean Servidor";
		listValoresCorrectosElements.add(e47);
		String e48 = "boolean Tipo";
		listValoresCorrectosElements.add(e48);
		String e49 = "boolean Computador";
		listValoresCorrectosElements.add(e49);
		
		String r0 = "group(Tipo,[Servidor, Personal])";
		listValoresCorrectosRelations.add(r0);
		String r1 = "group(Personal,[Mesa, Portatil])";
		listValoresCorrectosRelations.add(r1);
		String r2 = "group(Marca,[Dell, Toshiba, Lenovo, Apple, Clon])";
		listValoresCorrectosRelations.add(r2);
		String r3 = "group(SO,[Linux, Mac SO, Windows])";
		listValoresCorrectosRelations.add(r3);
		String r4 = "group(Linux,[Fedora, Red Hat])";
		listValoresCorrectosRelations.add(r4);
		String r5 = "group(Mac SO,[Yosemite, OS X Server])";
		listValoresCorrectosRelations.add(r5);
		String r6 = "group(Windows,[Windows 10, Windows Server])";
		listValoresCorrectosRelations.add(r6);
		String r7 = "group(Procesador,[AMD, Intel])";
		listValoresCorrectosRelations.add(r7);
		String r8 = "group(Memoria,[4 GB, 8 GB, 16 GB, 32 GB, 64 GB])";
		listValoresCorrectosRelations.add(r8);
		String r9 = "group(Disco,[500 GB, 1 TB, 2 TB, 5 TB, 10 TB])";
		listValoresCorrectosRelations.add(r9);
		String r10 = "group(Tarjeta Grafica,[ATI, NVIDIA])";
		listValoresCorrectosRelations.add(r10);
		String r11 = "group(Tecnologia,[3D, 4K])[1,*]";
		listValoresCorrectosRelations.add(r11);
		String r12 = "group(Size,[14 Pulgadas, 17 Pulgadas, 24 Pulgadas])";
		listValoresCorrectosRelations.add(r12);
		String r13 = "decomposition(Computador,[Planta De Poder])<0>";
		listValoresCorrectosRelations.add(r13);
		String r14 = "expression(((Personal) OR (~ (Windows 10))))";
		listValoresCorrectosRelations.add(r14);
		String r15 = "expression(((Servidor) OR (~ (Windows Server))))";
		listValoresCorrectosRelations.add(r15);
		String r16 = "expression(((~ (Pantalla)) OR (Portatil)))";
		listValoresCorrectosRelations.add(r16);
		String r17 = "expression(((Servidor) OR (~ (Red Hat))))";
		listValoresCorrectosRelations.add(r17);
		String r18 = "expression(((Servidor) OR (~ (OS X Server))))";
		listValoresCorrectosRelations.add(r18);
		String r19 = "expression(((Personal) OR (~ (Fedora))))";
		listValoresCorrectosRelations.add(r19);
		String r20 = "expression(((Personal) OR (~ (Yosemite))))";
		listValoresCorrectosRelations.add(r20);
		
	}
	
	
	public String cargarValoresGenerados(int modelo) {
		File file =  null;
		switch (modelo) {
		case 1:
			file = new File(COMMONT_PATH_OUTPUT+"/MobilePhoneGenerated.hlvl");
			break;
		case 2:
			file = new File(COMMONT_PATH_OUTPUT+"/SmartHomeGenerated.hlvl");
			break;
		case 3:
			file = new File(COMMONT_PATH_OUTPUT+"/ComputadorGenerated.hlvl");
			break;
		default:
			break;
		}
		FileReader f;
		String linea = "";
		String datosHLVL = "";
		try {
			f = new FileReader(file);
			BufferedReader in = new BufferedReader(f);
			linea = in.readLine();
			while (!linea.equals("")) {
				datosHLVL += linea + "\n";
				linea = in.readLine();
			}
			in.close();
			f.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datosHLVL;
	}
	
	public void asignarValoresGeneradosHash(String result) {
		String[] values = result.split("\n");
		boolean elementos = false;
		boolean relaciones = false;
		
		for (int i = 0; i < values.length; i++) {
			if(relaciones) {
				setRelations.add(values[i]);
			}
			if(values[i].equals("relations:")) {
				elementos = false;
				relaciones = true;
			}
			if(elementos) {
				setElements.add(values[i]);
			}
			if(values[i].equals("elements: ")) {
				elementos = true;
			}
		}
	}
	
	@Test
	public void testModeloMobilePhone() {
		asignarParamsMobile();
		try {
			fToH.parse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		String result = cargarValoresGenerados(1);
		
		asignarValoresGeneradosHash(result);

		boolean exist = false;
		for (String string : setElements) {
			for (String string2 : listValoresCorrectosElements) {
				exist = string.contains(string2);
				if(exist) {
					break;
				}
			}
			assertTrue(exist);
			exist = false;
		}
		for (String string : setRelations) {
			for (String string2 : listValoresCorrectosRelations) {
				exist = string.contains(string2);
				if(exist) {
					break;
				}
			}
			assertTrue(exist);
			exist = false;
		}
	}
	
	@Test
	public void testModeloSmartHome() {
		asignarParamsSmart();
		try {
			fToH.parse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		String result = cargarValoresGenerados(2);
		
		asignarValoresGeneradosHash(result);

		boolean exist = false;
		for (String string : setElements) {
			for (String string2 : listValoresCorrectosElements) {
				exist = string.contains(string2);
				if(exist) {
					break;
				}
			}
			assertTrue(exist);
			exist = false;
		}
		for (String string : setRelations) {
			for (String string2 : listValoresCorrectosRelations) {
				exist = string.contains(string2);
				if(exist) {
					break;
				}
			}
			assertTrue(exist);
			exist = false;
		}
	}
	@Test
	public void testModeloComputador() {
		asignarParamsComputador();
		try {
			fToH.parse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		String result = cargarValoresGenerados(3);
		
		asignarValoresGeneradosHash(result);

		boolean exist = false;
		for (String string : setElements) {
			for (String string2 : listValoresCorrectosElements) {
				exist = string.contains(string2);
				if(exist) {
					System.out.println(string2);
					break;
				}
			}
			assertTrue(exist);
			exist = false;
		}
		for (String string : setRelations) {
			for (String string2 : listValoresCorrectosRelations) {
				exist = string.contains(string2);
				if(exist) {
					break;
				}
			}
			assertTrue(exist);
			exist = false;
		}
	}

}
