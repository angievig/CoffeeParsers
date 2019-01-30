package constants;

public interface TransformerConstants extends ConstraintSymbolsConstant {

	// OPERATION CONSTANTS FOR SWI PROLOG
	public static final String PROLOG_FILES_TRANSFORMED_PATH = "pl/SPLOTMODELS/PrologFiles/";
	public static final String SXFM_FILES_TO_TRANSFORM = "pl/SPLOTMODELS/modelsToTransform/";
	public static final String PROLOG_FILES_TRANSFORMED_PATH_VOID = "pl/SPLOTMODELS/PrologFiles/voidModels/";
	public static final String PROLOG_FILES_TRANSFORMED_PATH_NO_VOID = "pl/SPLOTMODELS/PrologFiles/no_voidModels/";
	public static final String PROLOG_FILES_TRANSFORMED_PATH_FALSE_PLM = "pl/SPLOTMODELS/PrologFiles/FalsePLM/";
	public static final String RESULTS_FILE = "pl/SPLOTMODELS/PrologFiles/result.txt";
	public static final String PROLOG_EXTENSION = ".pl";
	public static final String SPLOT_EXTENSION = ".splx";
	public static final String FEATURE_1 = "feature1";
	public static final String FEATURE_2 = "feature2";
	public static final String FEATURES_SET = "featuresSet";
	public static final String GROUPAL_DEPENDENCY = "Groupal";
	public static final String M = "m";
	public static final String N = "n";
	
	//Text
	public static final String TO = " TO ";
	public static final String LOW_CARDINALITY = " Lower Cardinality";
	public static final String UPPER_CARDINALITY = " Upper Cardinality";
	public static final String DEPENDENCY = "Dependency ";
	public static final String MODEL_ROOT = "Model root: ";

	
	// Valor que indica que una variable no fue asignada. Feature no fue seleccionda.
	public static final Integer NON_SELECTED_VALUE=0;
	//Instrucci�n que se inserta par saber donde se pueden insertar restricciones en el archivo prolog cuando se comience a hacer el an�lisis
	public static final String ADDING_ZONE="%%Addedconstraints";

}
