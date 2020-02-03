package com.coffee.modelParsers.attHLVLPackage;

import com.coffee.modelParsers.basicHLVLPackage.IHlvlBasicFactory;

public interface IHlvlAttFactory extends IHlvlBasicFactory {

	/**
	 * Method that returns a String with the HLVL syntax for an specific attribute
	 * with a specific id and type.
	 * @param identifier Identifier of the attribute required.
	 * @param type Attribute type that has to be correspondent to the AttType Interface.
	 * @return
	 */
	public String getAtt(String identifier, AttType type);
}
