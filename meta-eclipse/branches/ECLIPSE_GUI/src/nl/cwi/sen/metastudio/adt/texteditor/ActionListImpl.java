package nl.cwi.sen.metastudio.adt.texteditor;


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
    return super.isEqual(peer);
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
     throw new UnsupportedOperationException("This ActionList has no Head");
  }

  public ActionList setHead(Menu _head)
  {
     throw new IllegalArgumentException("Illegal argument: " + _head);
  }

  public ActionList getTail()
  {
     throw new UnsupportedOperationException("This ActionList has no Tail");
  }

  public ActionList setTail(ActionList _tail)
  {
     throw new IllegalArgumentException("Illegal argument: " + _tail);
  }

}

