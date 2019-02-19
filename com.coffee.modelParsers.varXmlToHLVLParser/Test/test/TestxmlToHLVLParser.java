package test;

import org.junit.jupiter.api.Test;

import com.coffee.modelParsers.utils.ParsingParameters;
import com.coffee.modelParsers.varXmlToHLVLParser.VariamosXMLToHlvlParser;

class TestxmlToHLVLParser {

	private VariamosXMLToHlvlParser parser;
	
	@Test
	public void test() {
		
		int i=1;
		ParsingParameters params= new ParsingParameters();
		
		params.setInputPath("test-data/xmlToHLVLFiles");
		params.setOutputPath("test-data/HLVLFiles/");
		params.setTargetName("testXmlToHLVL"+i);
		
		parser = new VariamosXMLToHlvlParser(params);
		parser.loadArrayLists();
		try {
			parser.parse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testBoundle() {
		
	}

}
