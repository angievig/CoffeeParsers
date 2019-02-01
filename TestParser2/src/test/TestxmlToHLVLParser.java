package test;

import org.junit.jupiter.api.Test;
import utils.ParsingParameters;
import xmlToHLVLParser.VariamosXML2HlvlParser;

class TestxmlToHLVLParser {

	private VariamosXML2HlvlParser parser;
	
	@Test
	void test() {
		
		int i=1;
		ParsingParameters params= new ParsingParameters();
		
		params.setInputPath("test-data/xmlToHLVLFiles/documentoXML3.xml");
		params.setOutputPath("test-data/HLVLFiles/");
		params.setTargetName("testXmlToHLVL"+i);
		
		parser = new VariamosXML2HlvlParser(params);
		parser.loadArrayLists();
		try {
			parser.parse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
