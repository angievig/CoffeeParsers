package com.coffee.modelParsers.basicHLVLPackage;

import java.util.List;

public class CnfExpFactory implements HlvlBasicKeys{

	/**
	 * Method that receives two lists, the first one contains elements that have a positive logical value,
	 * the other list contains elements that have a negative logical value.
	 * @param positives List that contains elements with positive logical value.
	 * @param negatives List that contains elements with negative logical value.
	 * @param numId Id of the CNF expression.
	 * @param id Id of the HLVL expression that called the method.
	 * @return String that contains all of the correspondent CNF expressions required in the lists.
	 */
	public String getCNF2expression(List<String> positives, List<String> negatives, int numId, String id) {
		String out= id+ (numId++) + COLON+  EXPRESSION+ OPEN_CALL;

		for(String element: negatives){
			out +=  NEG+ element+ SPACE+  L_OR + SPACE;
		}
		for(String element: positives){
			out +=  element+ SPACE+ L_OR + SPACE;
		}

		out = out.substring(0, out.length() -3)+ CLOSE_CALL + "\n";

		return out; 
	}
}
