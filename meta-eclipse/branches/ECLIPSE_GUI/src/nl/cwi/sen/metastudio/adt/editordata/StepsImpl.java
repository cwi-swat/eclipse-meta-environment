package nl.cwi.sen.metastudio.adt.editordata;


abstract public class StepsImpl extends EditorDataConstructor
{
  protected StepsImpl(EditorDataFactory factory) {
     super(factory);
  }
  protected void init(int hashCode, aterm.ATermList annos, aterm.AFun fun,	aterm.ATerm[] args) {
    super.init(hashCode, annos, fun, args);
  }
  protected void initHashCode(aterm.ATermList annos, aterm.AFun fun, aterm.ATerm[] i_args) {
  	super.initHashCode(annos, fun, i_args);
  }
  public boolean isEqual(Steps peer)
  {
    return super.isEqual(peer);
  }
  public boolean isSortSteps()  {
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

  public int getHead()
  {
     throw new UnsupportedOperationException("This Steps has no Head");
  }

  public Steps setHead(int _head)
  {
     throw new IllegalArgumentException("Illegal argument: " + _head);
  }

  public Steps getTail()
  {
     throw new UnsupportedOperationException("This Steps has no Tail");
  }

  public Steps setTail(Steps _tail)
  {
     throw new IllegalArgumentException("Illegal argument: " + _tail);
  }

}

