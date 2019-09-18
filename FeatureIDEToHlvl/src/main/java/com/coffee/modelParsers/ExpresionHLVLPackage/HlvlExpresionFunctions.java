package com.coffee.modelParsers.ExpresionHLVLPackage;

public interface HlvlExpresionFunctions {

	/**
	 * Method that creates a String that modules a AND expression in HLVL syntax.
	 * @param param1 First element for the AND expression.
	 * @param param2 Second element for the AND expression.
	 * @return 
	 */
	public String createAnd(String param1, String param2) ;
	/**
	 * Method that creates a String that modules a OR expression in HLVL syntax.
	 * @param param1 First element for the OR expression.
	 * @param param2 Second element for the OR expression.
	 * @return 
	 */
	public String createOr(String param1, String param2);
	/**
	 * Method that creates a String that modules a IMP(implies) expression in HLVL syntax.
	 * @param param1 First element for the IMP expression.
	 * @param param2 Second element for the IMP expression.
	 * @return
	 */
	public String createImp(String param1, String param2) ;
	
	/**
	 * Method that creates a String that modules a NOT expression in HLVL syntax.
	 * @param param1 Element for the NOT expression.
	 * @return
	 */
	public String createNot(String param1) ;
}
