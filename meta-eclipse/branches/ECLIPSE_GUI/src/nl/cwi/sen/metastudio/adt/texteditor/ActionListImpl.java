package nl.cwi.sen.metastudio.adt.texteditor;

import java.io.InputStream;
import java.io.IOException;

abstract public class ActionListImpl extends TextEditorConstructor
{
  protected ActionListImpl(TextEditorFactory factory) {
     super(factory);
  }
  protected void init(int hashCode, aterm.ATermList annos, aterm.AFun fun,	aterm.ATerm[] args) {
    super.init(hashCode, annos, fun, args);
  }
  protected void initHashCode(aterm.ATermList annos, aterm.AFun fun, aterm.ATerm[] i_args) {
  	super.initHashCode(annos, fun, i_args);
  }
  public boolean isEqual(ActionList peer)
  {
    return term.isEqual(peer.toTerm());
  }
  public boolean isSortActionList()  {
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

  public Menu getHead()
  {
     throw new RuntimeException("This ActionList has no Head");
  }

  public ActionList setHead(Menu _head)
  {
     throw new RuntimeException("This ActionList has no Head");
  }

  public ActionList getTail()
  {
     throw new RuntimeException("This ActionList has no Tail");
  }

  public ActionList setTail(ActionList _tail)
  {
     throw new RuntimeException("This ActionList has no Tail");
  }

}

