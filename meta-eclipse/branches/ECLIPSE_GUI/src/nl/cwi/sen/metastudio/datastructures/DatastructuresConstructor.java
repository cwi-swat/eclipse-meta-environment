package nl.cwi.sen.metastudio.datastructures;

abstract public class DatastructuresConstructor
extends aterm.pure.ATermApplImpl
implements aterm.ATerm
{
  protected aterm.ATerm term = null;

  abstract protected aterm.ATerm getPattern();

  public aterm.ATerm toTerm() {
    if(term == null) {
      java.util.List args = new java.util.LinkedList();
      for(int i = 0; i<getArity() ; i++) {
        args.add(((DatastructuresConstructor) getArgument(i)).toTerm());
      }
      setTerm(getFactory().make(getPattern(), args));
    }
    return term;
  }

  public String toString() {
    return toTerm().toString();
  }

  protected void setTerm(aterm.ATerm term) {
   this.term = term;
  }

  public DatastructuresFactory getDatastructuresFactory() {
    return (DatastructuresFactory) getFactory();
  }

  static protected DatastructuresFactory getStaticDatastructuresFactory() {
    return (DatastructuresFactory) getStaticFactory();
  }

  public boolean isActionList() {
    return false;
  }

  public boolean isMenu() {
    return false;
  }

  public boolean isEvent() {
    return false;
  }

  public boolean isFocus() {
    return false;
  }

  public boolean isArea() {
    return false;
  }

  public boolean isFocusList() {
    return false;
  }

  public boolean isPath() {
    return false;
  }

  public boolean isSteps() {
    return false;
  }

  public boolean isGraph() {
    return false;
  }

  public boolean isNodeList() {
    return false;
  }

  public boolean isNode() {
    return false;
  }

  public boolean isNodeId() {
    return false;
  }

  public boolean isAttributeList() {
    return false;
  }

  public boolean isAttribute() {
    return false;
  }

  public boolean isShape() {
    return false;
  }

  public boolean isDirection() {
    return false;
  }

  public boolean isEdgeList() {
    return false;
  }

  public boolean isEdge() {
    return false;
  }

  public boolean isPolygon() {
    return false;
  }

  public boolean isPoint() {
    return false;
  }

}
