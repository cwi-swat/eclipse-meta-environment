package nl.cwi.sen.metastudio.adt.editordata;

abstract public class Area_DefaultImpl
extends Area
{
  protected void init(int hashCode, aterm.ATermList annos, aterm.AFun fun,	aterm.ATerm[] args) {
    super.init(hashCode, annos, fun, args);
  }
  protected void initHashCode(aterm.ATermList annos, aterm.AFun fun, aterm.ATerm[] i_args) {
  	super.initHashCode(annos, fun, i_args);
  }
  protected Area_DefaultImpl(EditorDataFactory factory) {
    super(factory);
  }
  private static int index_start = 0;
  private static int index_length = 1;
  public shared.SharedObject duplicate() {
    Area_Default clone = new Area_Default(factory);
     clone.init(hashCode(), getAnnotations(), getAFun(), getArgumentArray());
    return clone;
  }

  public boolean equivalent(shared.SharedObject peer) {
    if (peer instanceof Area_Default) {
      return super.equivalent(peer);
    }
    return false;
  }
  protected aterm.ATermAppl make(aterm.AFun fun, aterm.ATerm[] i_args, aterm.ATermList annos) {
    return getEditorDataFactory().makeArea_Default(fun, i_args, annos);
  }
  public aterm.ATerm toTerm() {
    if (term == null) {
      term = getEditorDataFactory().toTerm(this);
    }
    return term;
  }

  public boolean isDefault()
  {
    return true;
  }

  public boolean hasStart()
  {
    return true;
  }

  public boolean hasLength()
  {
    return true;
  }

  public int getStart()
  {
   return ((aterm.ATermInt) this.getArgument(index_start)).getInt();
  }

  public Area setStart(int _start)
  {
    return (Area) super.setArgument(getFactory().makeInt(_start), index_start);
  }

  public int getLength()
  {
   return ((aterm.ATermInt) this.getArgument(index_length)).getInt();
  }

  public Area setLength(int _length)
  {
    return (Area) super.setArgument(getFactory().makeInt(_length), index_length);
  }

  public aterm.ATermAppl setArgument(aterm.ATerm arg, int i) {
    switch(i) {
      case 0:
        if (! (arg instanceof aterm.ATermInt)) { 
          throw new RuntimeException("Argument 0 of a Area_Default should have type int");
        }
        break;
      case 1:
        if (! (arg instanceof aterm.ATermInt)) { 
          throw new RuntimeException("Argument 1 of a Area_Default should have type int");
        }
        break;
      default: throw new RuntimeException("Area_Default does not have an argument at " + i );
    }
    return super.setArgument(arg, i);
  }
}
