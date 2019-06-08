package exampleFeatureIDE;

import com.coffee.modelParsers.featureIDEToHlvlParser.FeatureIDEToHLVL;
import com.coffee.modelParsers.utils.ParsingParameters;

public class Example {

	public static final String COMMONT_PATH_INPUT="dataExample";
	public static final String COMMONT_PATH_OUTPUT="dataExample";
	FeatureIDEToHLVL featureIDE;
	
	public void imputString(String xml) {
		featureIDE= new FeatureIDEToHLVL();
		try {
			System.out.println(featureIDE.parse(xml));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void imputFile() {
		//Deleted the files .hlvl is a exeption has been catched
		ParsingParameters params = new ParsingParameters();
		params.setInputPath(COMMONT_PATH_INPUT);
		params.setOutputPath(COMMONT_PATH_OUTPUT);
		params.setTargetName("example");
		featureIDE = new FeatureIDEToHLVL(params);
		try {
			featureIDE.parse();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] arg0) {
		
		Example  example= new Example();
		
		example.imputFile();
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
		
		
		example.imputString(xml);
	}
	
}
