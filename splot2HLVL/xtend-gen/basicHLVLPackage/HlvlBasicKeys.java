package basicHLVLPackage;

import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Functions.Function0;

/**
 * Interface declaring  a set of constants used to represent HLVL constructs, and keywords
 * in the basic dialect of HLVL (Hlvl(basic))
 * @author Angela Villota
 * Coffee V1
 * January 2019
 */
@SuppressWarnings("all")
public interface HlvlBasicKeys {
  /**
   * Hlvl Constructs for the dialect HLVL(basic)
   */
  public final static String CORE = "coreElements";
  
  public final static String MUTEX = "mutex";
  
  public final static String IMPLIES = "implies";
  
  public final static String DECOMPOSITION = "decomposition";
  
  public final static String GROUP = "group";
  
  public final static String EXPRESSION = "expression";
  
  /**
   * Elements declaration, all are boolean
   */
  public final static String ELM_DECLARATION = "boolean";
  
  /**
   * Cardinalities for decomposition and group constructs
   */
  public final static String MANDATORY = "<1>";
  
  public final static String OPTIONAL = "<0>";
  
  public final static String ALTERNATIVE = "[1,1]";
  
  public final static String OR = "[1,*]";
  
  /**
   * Syntax elements
   */
  public final static String OPEN_CALL = "(";
  
  public final static String CLOSE_CALL = ")";
  
  public final static String OPEN_LIST = "[";
  
  public final static String CLOSE_LIST = "]";
  
  public final static String COMMA = ",";
  
  public final static String COLON = ":";
  
  /**
   * Model labels
   */
  public final static String MODEL_LABEL = new Function0<String>() {
    public String apply() {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("model ");
      return _builder.toString();
    }
  }.apply();
  
  public final static String ELEMENTS_LABEL = new Function0<String>() {
    public String apply() {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("elements: ");
      _builder.newLine();
      return _builder.toString();
    }
  }.apply();
  
  public final static String RELATIONS_LABEL = new Function0<String>() {
    public String apply() {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("relations:");
      _builder.newLine();
      return _builder.toString();
    }
  }.apply();
  
  public final static String OPERATIONS_LABEL = new Function0<String>() {
    public String apply() {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("operations:");
      _builder.newLine();
      return _builder.toString();
    }
  }.apply();
  
  /**
   * Basic operations
   */
  public final static String VALID_MODEL = "validModel";
  
  public final static String NUM_CONF = "numberOfConfigurations";
  
  public final static String FIND_ONE = "findConfiguration";
  
  public final static String FIND_ALL = "findAllConfigurations";
  
  public final static String VALID_CONF = "validConfiguration";
  
  /**
   * Expressions
   */
  public final static String NEG = "~";
  
  public final static String L_AND = "AND";
  
  public final static String L_OR = "OR";
}
