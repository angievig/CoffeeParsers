package transformation;

import java.util.ArrayList;
import java.util.List;

import constants.ConstraintSymbolsConstant;
import constants.GNUPrologConstraintSymbolsConstant;
import constants.PLHLCLConstant;
import constants.SWIPrologConstraintSymbolsConstant;
import constants.TransformerConstants;
import enumerations.NotationType;

public class FeatureModelTransformerRules implements PLHLCLConstant{
	private NotationType notationType;
	private String modelName;
	private int constraintCounter=1;


	private static String equivalenteSymbolString;
		
	public FeatureModelTransformerRules(NotationType prologEditorType) {
		super();
		notationType= prologEditorType;
		if (prologEditorType.equals(NotationType.GNU_PROLOG)) {
			equivalenteSymbolString = GNUPrologConstraintSymbolsConstant.EQUIVALENT;
			
		} else {
			equivalenteSymbolString = SWIPrologConstraintSymbolsConstant.EQUIVALENT;
			
		}
	}
	
	public String getMandatoryRule(String parent, String child){
		StringBuilder rule= new StringBuilder();
		if (notationType.equals(NotationType.PLHLCL)) {
			rule.append("C"+constraintCounter+COLONS+SPACE);
			constraintCounter++;
			if(parent.equals(modelName)) {
				rule.append(child);
				rule.append(SPACE);
				rule.append(IS);
				rule.append(SPACE);
				rule.append(MANDATORY);
			}else {
				rule.append(parent);
				rule.append(SPACE);
				rule.append(MANDATORY);
				rule.append(SPACE);
				rule.append(child);
			}
			
		}else {
			//A <=> B
			rule.append(parent);
			rule.append(equivalenteSymbolString);
			rule.append(child);
			
		}
		return rule.toString();
	}
	
	public String getOptionalRule(String parent, String child){
		StringBuilder rule= new StringBuilder();
		if (notationType.equals(NotationType.PLHLCL)) {
			rule.append("C"+constraintCounter+COLONS+SPACE);
			constraintCounter++;
			if(parent.equals(modelName)) {
				rule.append(child);
				rule.append(SPACE);
				rule.append(IS);
				rule.append(SPACE);
				rule.append(OPTIONAL);
			}else {
				rule.append(parent);
				rule.append(SPACE);
				rule.append(OPTIONAL);
				rule.append(SPACE);
				rule.append(child);
			}
			
		}else {
			//A >= B
			rule.append(parent);
			rule.append(ConstraintSymbolsConstant.MORE_OR_EQUALS);
			rule.append(child);
		}
		return rule.toString();

	}
	
	public String getAssignRule(String valueToAssing, String name){
		StringBuilder rule= new StringBuilder();
		//A #= Valor
		rule.append(name);
		rule.append(ConstraintSymbolsConstant.ASSIGN);
		rule.append(valueToAssing);
		return rule.toString();
	}
	
	public String getGroupalDependencyRule(String parent, List<String> children, String min, String max){
		StringBuilder rule= new StringBuilder();
		rule.append("C"+constraintCounter+COLONS+SPACE);
		constraintCounter++;
		rule.append(PARENT);
		rule.append(COLONS);
		rule.append(parent);
		rule.append(SPACE);
		rule.append(GROUP);
		rule.append(COLONS);
		for (String child : children) {
			rule.append(child);
			rule.append(SPACE);
		}
		rule.append(SPACE);
		rule.append(CARDINALITY);
		rule.append(COLONS);
		rule.append(LEFT_BRACKET);
		rule.append(min);
		rule.append(COMMA);
		rule.append(max);
		rule.append(RIGHT_BRACKET);
		
		
		
		return rule.toString();
	}
	public String getGroupalDependencyRule1(){
		StringBuilder rule= new StringBuilder();
		//m * P <= SUM features 
		// Feature1=parent feature
		rule.append(TransformerConstants.M);
		rule.append(ConstraintSymbolsConstant.MULTIPLY);
		rule.append(TransformerConstants.FEATURE_1);
		rule.append(ConstraintSymbolsConstant.LESS_OR_EQUALS);
		rule.append(TransformerConstants.FEATURES_SET);
		return rule.toString();
	}
	
	public String getGroupalDependencyRule2(){
		StringBuilder rule= new StringBuilder();
		//SUM features <= n * P 
		// Feature1=parent feature
		rule.append(TransformerConstants.FEATURES_SET);
		rule.append(ConstraintSymbolsConstant.LESS_OR_EQUALS);
		rule.append(TransformerConstants.FEATURE_1);
		rule.append(ConstraintSymbolsConstant.MULTIPLY);
		rule.append(TransformerConstants.N);
		return rule.toString();
	}
	
	public String getGroupalDependencyRule3(){
		StringBuilder rule= new StringBuilder();
		//1 * P #= SUM features 
		rule.append(ConstraintSymbolsConstant.ONE);
		rule.append(ConstraintSymbolsConstant.MULTIPLY);
		rule.append(TransformerConstants.FEATURE_1);
		rule.append(ConstraintSymbolsConstant.ASSIGN);
		rule.append(TransformerConstants.FEATURES_SET);
		return rule.toString();
	}
	
	public String getPropositionalConstraintsRulePLHLCL(List<String> positives, List<String> negatives){
		StringBuilder rule= new StringBuilder();
		int size= positives.size()+ negatives.size();
		if (size == 2) {
			if (negatives.size()>1) { //it's an excludes
				rule.append("C"+constraintCounter+COLONS+SPACE);
				constraintCounter++;
				rule.append(negatives.get(0));
				rule.append(SPACE);
				rule.append(EXCLUDES);
				rule.append(SPACE);
				rule.append(negatives.get(1));
				
			}else if(negatives.size()==1) { // it's a requieres
				rule.append("C"+constraintCounter+COLONS+SPACE);
				constraintCounter++;
				rule.append(negatives.get(0));
				rule.append(SPACE);
				rule.append(REQUIRES);
				rule.append(SPACE);
				rule.append(positives.get(0));
			}
			//TODO INCLUDE A DISJUNCTION
			
		}
		
			
		
			
		
		return rule.toString();
	}
	public String getPropositionalConstraintsRule(){
		// prop1 + prop2 +--- #>0" EJM (1 - F2) + (1 - F8) #> 0,
		StringBuilder rule= new StringBuilder();
		rule.append(TransformerConstants.FEATURES_SET);
		rule.append(ConstraintSymbolsConstant.MORE);
		rule.append(ConstraintSymbolsConstant.ZERO);
		return rule.toString();
	}
	
	public String getNegativePropostionalElementRule(){
		//( 1-featureName)
		StringBuilder rule= new StringBuilder();
		if (!notationType.equals(NotationType.PLHLCL)) {
			rule.append(ConstraintSymbolsConstant.OPEN_PARENTHESIS);
			rule.append(ConstraintSymbolsConstant.ONE);
			rule.append(ConstraintSymbolsConstant.SUBSTRACTION);
			rule.append(TransformerConstants.FEATURE_1);
			rule.append(ConstraintSymbolsConstant.CLOSE_PARENHESIS);	
		}
		
		return rule.toString();
	}
	
	
	public String getGroupalDependencyName3(){
		StringBuilder dependencyName= new StringBuilder();
		//Parent TO groupedFeatures
		dependencyName.append(TransformerConstants.FEATURE_1);
		dependencyName.append(TransformerConstants.TO);
		dependencyName.append(TransformerConstants.FEATURES_SET);
		return dependencyName.toString();
	}
	
	public String getGroupalDependencyName1(){
		//Parent TO groupedFeatures LowCardinality
		StringBuilder dependencyName= new StringBuilder(getGroupalDependencyName3());
		dependencyName.append(TransformerConstants.LOW_CARDINALITY);
		return dependencyName.toString();
	}
	
	public String getGroupalDependencyName2(){
		//Parent TO groupedFeatures UpperCardinality
		StringBuilder dependencyName= new StringBuilder(getGroupalDependencyName3());
		dependencyName.append(TransformerConstants.UPPER_CARDINALITY);
		return dependencyName.toString();
	}
	
	public String getPropositionalName(){
		StringBuilder dependencyName= new StringBuilder();
		//Dependency F1-F2-F3
		dependencyName.append(TransformerConstants.DEPENDENCY);
		dependencyName.append(TransformerConstants.FEATURES_SET);
		return dependencyName.toString();
	}
	
	public String getRootDependencyName(){
		StringBuilder dependencyName= new StringBuilder();
		//Model root: feature1"
		dependencyName.append(TransformerConstants.MODEL_ROOT);
		dependencyName.append(TransformerConstants.FEATURE_1);
		return dependencyName.toString();
	}
	
	public String getMandatoryOptionalDependencyName(){
		StringBuilder dependencyName= new StringBuilder();
		//feature1 TO feature2"
		dependencyName.append(TransformerConstants.FEATURE_1);
		dependencyName.append(TransformerConstants.TO);
		dependencyName.append(TransformerConstants.FEATURE_2);
		return dependencyName.toString();
	}
	
	public String declareVariables(List<String> vars){
		StringBuilder rule= new StringBuilder();
		
		for (String name : vars) {
			rule.append(TYPE_BOOLEAN);
			rule.append(SPACE);
			rule.append(name);
			rule.append(SPACE);
			rule.append(VARIANTS);
			rule.append(LEFT_BRACKET);
			rule.append(SELECTED);
			rule.append(SPACE);
			rule.append(UNSELECTED);
			rule.append(RIGHT_BRACKET);
			rule.append(LF);
		}

		
		return rule.toString();
	}
	public NotationType getNotationType() {
		return notationType;
	}

	public void setNotationType(NotationType notationType) {
		this.notationType = notationType;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	
}
