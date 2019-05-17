package com.coffee.modelParsers.attHLVLPackage;

import com.coffee.modelParsers.basicHLVLPackage.HlvlBasicFactory;

public class HlvlAttFactory extends HlvlBasicFactory implements HlvlAttKeys, IHlvlAttFactory{

	public HlvlAttFactory() {
		
	}
	
	@Override
	public String getAtt(String identifier, AttType type) {
		String out ="";
		switch (type) {
		case INTEGER:
			out= ATT +SPACE + INTEGER + SPACE + identifier+ "\n";
			break;
		case LONG:
			out= ATT +SPACE + LONG + SPACE + identifier+ "\n";
			break;
		case STRING:
			out= ATT +SPACE + STRING + SPACE + identifier+ "\n";
			break;
		case DOUBLE:
			out= ATT +SPACE + DOUBLE + SPACE + identifier+ "\n";
			break;
		}
		return  out;
	}
}
