package nl.cwi.sen.metastudio.adt.editordata;


abstract public class SymbolListImpl extends EditorDataConstructor
{
  protected SymbolListImpl(EditorDataFactory factory) {
     super(factory);
  }
  protected void init(int hashCode, aterm.ATermList annos, aterm.AFun fun,	aterm.ATerm[] args) {
    super.init(hashCode, annos, fun, args);
  }
  protected void initHashCode(aterm.ATermList annos, aterm.AFun fun, aterm.ATerm[] i_args) {
  	super.initHashCode(annos, fun, i_args);
  }
  public boolean isEqual(SymbolList peer)
  {
    return super.isEqual(peer);
  }
  public boolean isSortSymbolList()  {
    return true;
  }

  public boolean isEmpty()
  {
    return false;
  }

  public boolean isMulti()
  {
    return false;
  }

  public boolean hasHead()
  {
    return false;
  }

  public boolean hasTail()
  {
    return false;
  }

  public String getHead()
  {
     throw new UnsupportedOperationException("This SymbolList has no Head");
  }

  public SymbolList setHead(String _head)
  {
     throw new IllegalArgumentException("Illegal argument: " + _head);
  }

  public SymbolList getTail()
  {
     throw new UnsupportedOperationException("This SymbolList has no Tail");
  }

  public SymbolList setTail(SymbolList _tail)
  {
     throw new IllegalArgumentException("Illegal argument: " + _tail);
  }

}

