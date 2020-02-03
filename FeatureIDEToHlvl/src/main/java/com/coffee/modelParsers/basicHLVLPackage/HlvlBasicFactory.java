package com.coffee.modelParsers.basicHLVLPackage;

import java.util.List;

/**
 * Class in extend implementing the IhlvlFactory 
 * this class implements the transformation rules from basic 
 * variability models in the basic dialect of HLVL (Hlvl(basic))
 * @author Angela Villota
 * Coffee V1
 * January 2019
 */
public class HlvlBasicFactory implements IHlvlBasicFactory, HlvlBasicKeys{
	/**
	 * Attribute that defines the numId of the correspondent Hlvl sentence.
	 */
	private int numId=0;
	/**
	 * Attribute that defines the id type of the Hlvl sentence.
	 */
	private String id="r";
	/**
	 * Attribute that defines a Conjunctive Normal Form Expression Factory class
	 */
	private CnfExpFactory expFactory= new CnfExpFactory();
	
	
	/**
	 * Produces an hlvl sentence with the declaration of a boolean element
	 * @param identifier the specific element identifier.
	 * @return String with the HLVL sentence declared with the specified boolean.
	 */
	@Override
	public String getElement(String identifier) {
		return  ELM_DECLARATION + SPACE+ identifier + "\n";
	}
	/**
	 * Produces a core relation in HLVL syntax.
	 * @param element is a String for the identifiers of the element
	 * @return a String with the hlvl sentences in the form coreElements(E1)
	 */
	@Override
	public String getCore(String element) {
		return  id + (numId++) + COLON+ SPACE +  CORE+ OPEN_CALL+ element+ CLOSE_CALL + "\n";
	}
	
	/**
	 * Produces a core relation for the elements that must be included in the
	 * product in HLVL syntax. 
	 * @param identifiers is a list of the identifiers of the elements that must be
	 * included in the product.
	 * @return a String with the HLVL sentences in the form coreElements(E1, E2, E3)
	 */
	@Override
	public String getCoreList(List<String> identifiers) {
		String out= id+ numId++ + COLON+ SPACE+  CORE+ OPEN_CALL;
		for(String id: identifiers){
			out+= id+COMMA+SPACE; 
		}
		out= out.substring(0, out.length() -2 )+ CLOSE_CALL + "\n";

		return out;
	}
	
	/**
	 * Produces an implies declaration of the form left => right
	 * @param left the identifier of the left
	 * @param right the identifier in the right side of the implication
	 * @return A HLVL in the form implies: left => right.
	 */
	@Override
	public String getImplies(String left, String right) {
		// TODO Auto-generated method stub
		return  id+ numId++ +COLON+ SPACE +  IMPLIES+ OPEN_CALL+ left+ COMMA+  right+ CLOSE_CALL + "\n";
	}
	
	
	/**
	 * Produces a mutex declaration for two elements that can't be included 
	 * in a product at the same time in HLVL syntax.
	 * @param left the identifier of the left.
	 * @param right the identifier in the right side of the implication.
	 * @return A HLVL in the form mutex (left,right)
	 */
	@Override
	public String getMutex(String left, String right) {
		// TODO Auto-generated method stub
		return  id+ numId++ + COLON + SPACE + MUTEX + OPEN_CALL + left+ COMMA+ SPACE+  right+ CLOSE_CALL + "\n";
	}
	
	/**
	 * Produces a decomposition declaration for two elements: parent and child, in HLVL syntax.
	 * @param parent Identifier of the parent element.
	 * @param child Identifier of the child element.
	 * @param type Can be Mandatory or Optional, the decomposition types supported by HLVL(basic)
	 * @return An HLVL sentence in the form decomposition(parent, [c1])< card> 
	 */
	@Override
	public String getDecomposition(String parent, String child, DecompositionType type) {
		String out= id+ numId++ + COLON+  DECOMPOSITION+ OPEN_CALL+ parent+ COMMA+  OPEN_LIST+ child+ CLOSE_LIST+ CLOSE_CALL;
		switch (type){
		case Mandatory: 
			out+=MANDATORY + "\n";
			break;
		case Optional: 
			out+=OPTIONAL+ "\n"	;
			break;
		}
		return out;
	}
	
	/**
	 * 
	 * @param parent Identifier of the parent element.
	 * @param children List with the identifiers of the children elements of the parent element.
	 * @param type Can be Mandatory or Optional, the decomposition types supported by HLVL(basic)
	 * @return An HLVL sentence in the form decomposition(parent, [c1, c2, ..cn])< card> 
	 */
	@Override
	public String getDecompositionList(String parent, List<String> children, DecompositionType type) {
		String out=id+ numId++ + COLON+  DECOMPOSITION+ OPEN_CALL+  parent+ COMMA+  OPEN_LIST;

		for(String id: children){
			out+= id+ COMMA + SPACE;
		}
		out = out.substring(0, out.length() -2 )+ CLOSE_LIST+ CLOSE_CALL;
		switch (type){
		case Mandatory: 
			out+=MANDATORY + "\n";
			break;
		case Optional: 
			out+=OPTIONAL+ "\n"	;
			break;
		}
		return out;
	}
	/**
	 * @param parent Identifier of the parent element.
	 * @param children List with the identifiers of the children elements of the parent element.
	 * @param type can be Mandatory or Optional, the decomposition types supported by HLVL(basic)
	 * @return an HLVL sentence in the form group(parent, [c1, c2, ..cn])[min, max]
	 */
	@Override
	public String getGroup(String parent, List<String> children, GroupType type) {
		String out=id+ (numId++) + COLON+  GROUP+ OPEN_CALL+  parent+ COMMA+  OPEN_LIST;

		for(String id: children){
			out+= id+ COMMA+ SPACE;
		}
		out = out.substring(0, out.length() -2 )+ CLOSE_LIST+ CLOSE_CALL;
		switch (type){
		case Xor: 
			out+=ALTERNATIVE + "\n";
			break;
		case Or: 
			out+=OR+ "\n";
			break;
		default:
			out+= "\n";
		}
		return out;
	}
	/**
	 * Method that calls CnfExpFactory to generate a Conjunctive Normal Form expression for the
	 * elements contained in the positives and negatives lists.
	 * @param positives List of elements that contain a positive logical value.
	 * @param negatives List of elements that contain a negative logical value.
	 * @return String generated by the CnfExpFactory.
	 */
	@Override
	public String parseCNF2expression(List<String> positives, List<String> negatives) {
		return expFactory.getCNF2expression(positives, negatives, numId++, id); 
	}
	
	/**
	 * Generates a String with the Hlvl syntax for the expressions relation.
	 * @param expression String that contains the expression.
	 * @return String with the expression written in the correspondent HLVL syntax.
	 */
	@Override
	public String parserExpression(String expresion) {
		// TODO Auto-generated method stub
		return id+(numId++)+COLON+ EXPRESSION+OPEN_CALL+expresion+CLOSE_CALL+"\n";
	}
	/**
	 * Produces the header of an HLVL file
	 * @param targetName a string with the name of the model (the same as the name of the file)
	 * @return a String with a header for the HLVL file
	 */
	@Override
	public String getHeader(String targetName) {
		return MODEL_LABEL + SPACE + targetName + "\n" + ELEMENTS_LABEL;
		
	}
	/**
	 * Returns the label for the variability relations block
	 * @return String contained in the constant RELATIONS_LABEL.
	 */
	@Override
	public String getRelationsLab() {
		return RELATIONS_LABEL;
	}

	/**
	 * Returns an operations block with the set of basic operations
	 */
	@Override
	public String getBasicOperationsBlock() {
		
		return  OPERATIONS_LABEL + 
		VALID_MODEL +
		COMMA +
		NUM_CONF;
	}

}
