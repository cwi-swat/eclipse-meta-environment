package nl.cwi.sen.metastudio.adt.texteditor;


abstract public class ProcessImpl extends TextEditorConstructor
{
  protected ProcessImpl(TextEditorFactory factory) {
     super(factory);
  }
  protected void init(int hashCode, aterm.ATermList annos, aterm.AFun fun,	aterm.ATerm[] args) {
    super.init(hashCode, annos, fun, args);
  }
  protected void initHashCode(aterm.ATermList annos, aterm.AFun fun, aterm.ATerm[] i_args) {
  	super.initHashCode(annos, fun, i_args);
  }
  public boolean isEqual(Process peer)
  {
    return super.isEqual(peer);
  }
  public boolean isSortProcess()  {
    return true;
  }

  public boolean isDefault()
  {
    return false;
  }

  public boolean hasToChild()
  {
    return false;
  }

  public boolean hasFromChild()
  {
    return false;
  }

  public Pipe getToChild()
  {
     throw new UnsupportedOperationException("This Process has no ToChild");
  }

  public Process setToChild(Pipe _toChild)
  {
     throw new IllegalArgumentException("Illegal argument: " + _toChild);
  }

  public Pipe getFromChild()
  {
     throw new UnsupportedOperationException("This Process has no FromChild");
  }

  public Process setFromChild(Pipe _fromChild)
  {
     throw new IllegalArgumentException("Illegal argument: " + _fromChild);
  }

}

