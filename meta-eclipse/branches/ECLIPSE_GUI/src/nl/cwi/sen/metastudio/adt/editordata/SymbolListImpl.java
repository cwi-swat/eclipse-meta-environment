package nl.cwi.sen.metastudio.adt.editordata;

import java.io.InputStream;
import java.io.IOException;

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
    return term.isEqual(peer.toTerm());
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
     throw new RuntimeException("This SymbolList has no Head");
  }

  public SymbolList setHead(String _head)
  {
     throw new RuntimeException("This SymbolList has no Head");
  }

  public SymbolList getTail()
  {
     throw new RuntimeException("This SymbolList has no Tail");
  }

  public SymbolList setTail(SymbolList _tail)
  {
     throw new RuntimeException("This SymbolList has no Tail");
  }

}

