package com.coffee.modelParsers.basicHLVLPackage;

/**
 * Interface declaring the methods that must be included in a parser
 * for a feature model into the basic dialect of HLVL (Hlvl(basic))
 * @author Angela Villota
 * Coffee V1
 * January 2019
 */
public interface IHlvlParser {
	
	/**
	 * This method is responsible of ensuring that the correct format(no spaces, no symbols, etc)
	 * is always preserved by any element's name.
	 * @param name Name of any Element.
	 * @return String with the name in a valid format.
	 */
	public String getValidName(String name);
	

	/**
	 * This method is responsible for the parsing of the specific model that is contained in a File.
	 * It must follow specific instructions to ensure a correct parsing.
	 */
	public void parse() throws Exception;
	
	/**
	 * This method is responsible for the parsing of the specific XML code that is contained in a String.
	 * It must follow specific instructions to ensure a correct parsing.
	 * @param data: String that contains XML code.
	 * @return String that contains HLVL code that was produced by parsing data
	 */
	public String parse(String data)throws Exception;
	
	


}
