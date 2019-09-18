package com.coffee.modelParsers.attHLVLPackage;

import com.coffee.modelParsers.basicHLVLPackage.HlvlBasicFactory;

public class HlvlAttFactory extends HlvlBasicFactory implements HlvlAttKeys, IHlvlAttFactory{

	/**
	 * Constructor for the Hlvl class
	 */
	public HlvlAttFactory() {
		
	}
	
	
	/**
	 * Method that returns a String with the HLVL syntax for an specific attribute
	 * with a specific id and type.
	 * @param identifier Identifier of the attribute required.
	 * @param type Attribute type that has to be correspondent to the AttType Interface.
	 * @return String with the specific attribute in HLVL syntax.
	 */
	@Override
	public String getAtt(String identifier, AttType type) {
		String out ="";
		switch (type) {
		case INTEGER:
			out= ATT +SPACE + INTEGER + SPACE + identifier+ "\n";
			break;
		case SYMBOLIC:
			out= ATT +SPACE + SYMBOLIC + SPACE + identifier+ "\n";
			break;
		case STRING:
			out= ATT +SPACE + STRING + SPACE + identifier+ "\n";
			break;
	
		}
		return  out;
	}
}
