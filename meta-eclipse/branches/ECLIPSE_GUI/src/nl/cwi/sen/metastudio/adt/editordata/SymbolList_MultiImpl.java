package nl.cwi.sen.metastudio.adt.editordata;

abstract public class SymbolList_MultiImpl
extends SymbolList
{
  protected void init(int hashCode, aterm.ATermList annos, aterm.AFun fun,	aterm.ATerm[] args) {
    super.init(hashCode, annos, fun, args);
  }
  protected void initHashCode(aterm.ATermList annos, aterm.AFun fun, aterm.ATerm[] i_args) {
  	super.initHashCode(annos, fun, i_args);
  }
  protected SymbolList_MultiImpl(EditorDataFactory factory) {
    super(factory);
  }
  private static int index_head = 0;
  private static int index_tail = 1;
  public shared.SharedObject duplicate() {
    SymbolList_Multi clone = new SymbolList_Multi(factory);
     clone.init(hashCode(), getAnnotations(), getAFun(), getArgumentArray());
    return clone;
  }

  public boolean equivalent(shared.SharedObject peer) {
    if (peer instanceof SymbolList_Multi) {
      return super.equivalent(peer);
    }
    return false;
  }
  protected aterm.ATermAppl make(aterm.AFun fun, aterm.ATerm[] i_args, aterm.ATermList annos) {
    return getEditorDataFactory().makeSymbolList_Multi(fun, i_args, annos);
  }
  public aterm.ATerm toTerm() {
    if (term == null) {
      term = getEditorDataFactory().toTerm(this);
    }
    return term;
  }

  public boolean isMulti()
  {
    return true;
  }

  public boolean hasHead()
  {
    return true;
  }

  public boolean hasTail()
  {
    return true;
  }

  public String getHead()
  {
   return ((aterm.ATermAppl) this.getArgument(index_head)).getAFun().getName();
  }

  public SymbolList setHead(String _head)
  {
    return (SymbolList) super.setArgument(getFactory().makeAppl(getFactory().makeAFun(_head, 0, true)), index_head);
  }

  public SymbolList getTail()
  {
    return (SymbolList) this.getArgument(index_tail) ;
  }

  public SymbolList setTail(SymbolList _tail)
  {
    return (SymbolList) super.setArgument(_tail, index_tail);
  }

  public aterm.ATermAppl setArgument(aterm.ATerm arg, int i) {
    switch(i) {
      case 0:
        if (! (arg instanceof aterm.ATermAppl)) { 
          throw new RuntimeException("Argument 0 of a SymbolList_Multi should have type str");
        }
        break;
      case 1:
        if (! (arg instanceof SymbolList)) { 
          throw new RuntimeException("Argument 1 of a SymbolList_Multi should have type SymbolList");
        }
        break;
      default: throw new RuntimeException("SymbolList_Multi does not have an argument at " + i );
    }
    return super.setArgument(arg, i);
  }
}
