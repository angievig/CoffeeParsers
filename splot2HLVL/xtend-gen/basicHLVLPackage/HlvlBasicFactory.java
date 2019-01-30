package basicHLVLPackage;

import basicHLVLPackage.DecompositionType;
import basicHLVLPackage.GroupType;
import basicHLVLPackage.HlvlBasicKeys;
import basicHLVLPackage.IhlvlBasicFactory;
import java.util.List;
import org.eclipse.xtend2.lib.StringConcatenation;

/**
 * Class in xtend implementing the IhlvlFactory
 * this class implements the transformation rules from basic
 * variability models in the basic dialect of HLVL (Hlvl(basic))
 * @author Angela Villota
 * Coffee V1
 * January 2019
 */
@SuppressWarnings("all")
public class HlvlBasicFactory implements IhlvlBasicFactory, HlvlBasicKeys {
  private int numId = 0;
  
  private String id = "r";
  
  @Override
  public String getCore(final String element) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(this.id);
    int _plusPlus = this.numId++;
    _builder.append(_plusPlus);
    _builder.append(HlvlBasicKeys.COLON);
    _builder.append(" ");
    _builder.append(HlvlBasicKeys.CORE);
    _builder.append(HlvlBasicKeys.OPEN_CALL);
    _builder.append(element);
    _builder.append(HlvlBasicKeys.CLOSE_CALL);
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  @Override
  public String getCoreList(final List<String> identifiers) {
    String _xblockexpression = null;
    {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(this.id);
      int _plusPlus = this.numId++;
      _builder.append(_plusPlus);
      _builder.append(HlvlBasicKeys.COLON);
      _builder.append(" ");
      _builder.append(HlvlBasicKeys.CORE);
      _builder.append(HlvlBasicKeys.OPEN_CALL);
      String out = _builder.toString();
      for (final String id : identifiers) {
        String _out = out;
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append(id);
        _builder_1.append(HlvlBasicKeys.COMMA);
        _builder_1.append(" ");
        out = (_out + _builder_1);
      }
      StringConcatenation _builder_2 = new StringConcatenation();
      int _length = out.length();
      int _minus = (_length - 2);
      String _substring = out.substring(0, _minus);
      _builder_2.append(_substring);
      _builder_2.append(HlvlBasicKeys.CLOSE_CALL);
      _builder_2.newLineIfNotEmpty();
      out = _builder_2.toString();
      _xblockexpression = out;
    }
    return _xblockexpression;
  }
  
  @Override
  public String getDecomposition(final String parent, final String child, final DecompositionType type) {
    String _xblockexpression = null;
    {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(this.id);
      int _plusPlus = this.numId++;
      _builder.append(_plusPlus);
      _builder.append(HlvlBasicKeys.COLON);
      _builder.append(" ");
      _builder.append(HlvlBasicKeys.DECOMPOSITION);
      _builder.append(HlvlBasicKeys.OPEN_CALL);
      _builder.append(parent);
      _builder.append(HlvlBasicKeys.COMMA);
      _builder.append(" ");
      _builder.append(HlvlBasicKeys.OPEN_LIST);
      _builder.append(child);
      _builder.append(HlvlBasicKeys.CLOSE_LIST);
      _builder.append(HlvlBasicKeys.CLOSE_CALL);
      String out = _builder.toString();
      if (type != null) {
        switch (type) {
          case Mandatory:
            String _out = out;
            out = (_out + (HlvlBasicKeys.MANDATORY + "\n"));
            break;
          case Optional:
            String _out_1 = out;
            out = (_out_1 + (HlvlBasicKeys.OPTIONAL + "\n"));
            break;
          default:
            break;
        }
      }
      _xblockexpression = out;
    }
    return _xblockexpression;
  }
  
  @Override
  public String getDecompositionList(final String parent, final List<String> children, final DecompositionType type) {
    String _xblockexpression = null;
    {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(this.id);
      int _plusPlus = this.numId++;
      _builder.append(_plusPlus);
      _builder.append(HlvlBasicKeys.COLON);
      _builder.append(" ");
      _builder.append(HlvlBasicKeys.DECOMPOSITION);
      _builder.append(HlvlBasicKeys.OPEN_CALL);
      _builder.append(" ");
      _builder.append(parent);
      _builder.append(HlvlBasicKeys.COMMA);
      _builder.append(" ");
      _builder.append(HlvlBasicKeys.OPEN_LIST);
      String out = _builder.toString();
      for (final String id : children) {
        String _out = out;
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append(id);
        _builder_1.append(HlvlBasicKeys.COMMA);
        _builder_1.append(" ");
        out = (_out + _builder_1);
      }
      StringConcatenation _builder_2 = new StringConcatenation();
      int _length = out.length();
      int _minus = (_length - 2);
      String _substring = out.substring(0, _minus);
      _builder_2.append(_substring);
      _builder_2.append(HlvlBasicKeys.CLOSE_LIST);
      _builder_2.append(HlvlBasicKeys.CLOSE_CALL);
      out = _builder_2.toString();
      if (type != null) {
        switch (type) {
          case Mandatory:
            String _out_1 = out;
            out = (_out_1 + (HlvlBasicKeys.MANDATORY + "\n"));
            break;
          case Optional:
            String _out_2 = out;
            out = (_out_2 + (HlvlBasicKeys.OPTIONAL + "\n"));
            break;
          default:
            break;
        }
      }
      _xblockexpression = out;
    }
    return _xblockexpression;
  }
  
  @Override
  public String getElement(final String identifier) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(HlvlBasicKeys.ELM_DECLARATION);
    _builder.append(" ");
    _builder.append(identifier);
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  @Override
  public String getGroup(final String parent, final List<String> children, final GroupType type) {
    String _xblockexpression = null;
    {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(this.id);
      int _plusPlus = this.numId++;
      _builder.append(_plusPlus);
      _builder.append(HlvlBasicKeys.COLON);
      _builder.append(" ");
      _builder.append(HlvlBasicKeys.GROUP);
      _builder.append(HlvlBasicKeys.OPEN_CALL);
      _builder.append(" ");
      _builder.append(parent);
      _builder.append(HlvlBasicKeys.COMMA);
      _builder.append(" ");
      _builder.append(HlvlBasicKeys.OPEN_LIST);
      String out = _builder.toString();
      for (final String id : children) {
        String _out = out;
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append(id);
        _builder_1.append(HlvlBasicKeys.COMMA);
        _builder_1.append(" ");
        out = (_out + _builder_1);
      }
      StringConcatenation _builder_2 = new StringConcatenation();
      int _length = out.length();
      int _minus = (_length - 2);
      String _substring = out.substring(0, _minus);
      _builder_2.append(_substring);
      _builder_2.append(HlvlBasicKeys.CLOSE_LIST);
      _builder_2.append(HlvlBasicKeys.CLOSE_CALL);
      out = _builder_2.toString();
      if (type != null) {
        switch (type) {
          case Alternative:
            String _out_1 = out;
            out = (_out_1 + (HlvlBasicKeys.ALTERNATIVE + "\n"));
            break;
          case Or:
            String _out_2 = out;
            out = (_out_2 + (HlvlBasicKeys.OR + "\n"));
            break;
          default:
            break;
        }
      }
      _xblockexpression = out;
    }
    return _xblockexpression;
  }
  
  @Override
  public String getImplies(final String left, final String right) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(this.id);
    int _plusPlus = this.numId++;
    _builder.append(_plusPlus);
    _builder.append(HlvlBasicKeys.COLON);
    _builder.append(" ");
    _builder.append(HlvlBasicKeys.IMPLIES);
    _builder.append(HlvlBasicKeys.OPEN_CALL);
    _builder.append(left);
    _builder.append(HlvlBasicKeys.COMMA);
    _builder.append(" ");
    _builder.append(right);
    _builder.append(HlvlBasicKeys.CLOSE_CALL);
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  @Override
  public String getMutex(final String left, final String right) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(this.id);
    int _plusPlus = this.numId++;
    _builder.append(_plusPlus);
    _builder.append(HlvlBasicKeys.COLON);
    _builder.append(" ");
    _builder.append(HlvlBasicKeys.MUTEX);
    _builder.append(HlvlBasicKeys.OPEN_CALL);
    _builder.append(left);
    _builder.append(HlvlBasicKeys.COMMA);
    _builder.append(" ");
    _builder.append(right);
    _builder.append(HlvlBasicKeys.CLOSE_CALL);
    _builder.newLineIfNotEmpty();
    return _builder.toString();
  }
  
  @Override
  public String parseCNF2expression(final List<String> positives, final List<String> negatives) {
    String _xblockexpression = null;
    {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(this.id);
      int _plusPlus = this.numId++;
      _builder.append(_plusPlus);
      _builder.append(HlvlBasicKeys.COLON);
      _builder.append(" ");
      _builder.append(HlvlBasicKeys.EXPRESSION);
      _builder.append(HlvlBasicKeys.OPEN_CALL);
      String out = _builder.toString();
      for (final String element : negatives) {
        String _out = out;
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append(" ");
        _builder_1.append(HlvlBasicKeys.NEG, " ");
        _builder_1.append(element, " ");
        _builder_1.append(" ");
        _builder_1.append(HlvlBasicKeys.L_OR, " ");
        out = (_out + _builder_1);
      }
      for (final String element_1 : positives) {
        String _out_1 = out;
        StringConcatenation _builder_2 = new StringConcatenation();
        _builder_2.append(" ");
        _builder_2.append(element_1, " ");
        _builder_2.append(" ");
        _builder_2.append(HlvlBasicKeys.L_OR, " ");
        out = (_out_1 + _builder_2);
      }
      StringConcatenation _builder_3 = new StringConcatenation();
      int _length = out.length();
      int _minus = (_length - 3);
      String _substring = out.substring(0, _minus);
      _builder_3.append(_substring);
      _builder_3.append(HlvlBasicKeys.CLOSE_CALL);
      _builder_3.newLineIfNotEmpty();
      out = _builder_3.toString();
      _xblockexpression = out;
    }
    return _xblockexpression;
  }
}
