package constants;

public interface SWIPrologConstraintSymbolsConstant extends
		ConstraintSymbolsConstant {

	// OPERATION CONSTANTS FOR SWI PROLOG
	public static final String EQUIVALENT = "#<==>";
	public static final String SWI_CONSTRAINTS_SYMBOLS[] = { "#<==>" };

	// HEADER FOR GNU PROLOG
	public static final String HEADER = ":-use_module(library(clpfd)).\nproductline(L):-\n";
	
	public static final String END="label(L).";

}
