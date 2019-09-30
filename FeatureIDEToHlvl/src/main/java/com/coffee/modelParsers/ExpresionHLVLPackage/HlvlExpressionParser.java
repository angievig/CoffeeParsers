package com.coffee.modelParsers.ExpresionHLVLPackage;

import com.coffee.modelParsers.basicHLVLPackage.HlvlBasicKeys;

public class HlvlExpressionParser implements HlvlExpresionFunctions, HlvlExpressionKeys, HlvlBasicKeys {

	
	/**
	 * Method that creates a String that modules an AND expression in HLVL syntax.
	 * @param param1 First element for the AND expression.
	 * @param param2 Second element for the AND expression.
	 * @return String in the form of an AND expression in HLVL syntax.
	 */
	@Override
	public String createAnd(String param1, String param2) {
		String result = OPEN_CALL+param1 +" "+HlvlExpressionKeys.AND+" "+param2+CLOSE_CALL;
		return result;
	}

	/**
	 * Method that creates a String that modules an OR expression in HLVL syntax.
	 * @param param1 First element for the OR expression.
	 * @param param2 Second element for the OR expression.
	 * @return String in the form of an OR expression in HLVL syntax.
	 */
	@Override
	public String createOr(String param1, String param2) {
		String result = OPEN_CALL+param1 +" "+HlvlExpressionKeys.OR+" "+param2+CLOSE_CALL;
		return result;
	}
	
	/**
	 * Method that creates a String that modules a IMP(implies) expression in HLVL syntax.
	 * @param param1 First element for the IMP expression.
	 * @param param2 Second element for the IMP expression.
	 * @return String in the form of a IMP expression in HLVL syntax.
	 */
	@Override
	public String createImp(String param1, String param2) {
		String result = OPEN_CALL+param1 +" "+HlvlExpressionKeys.IMP+" "+param2+CLOSE_CALL;
		return result;
	}

	/**
	 * Method that creates a String that modules a NOT expression in HLVL syntax.
	 * @param param1 Element for the NOT expression.
	 * @return String in the form of a NOT expression in HLVL syntax.
	 */
	@Override
	public String createNot(String param1) {
		String result = HlvlExpressionKeys.NOT+param1;
		return result;
	}

}
