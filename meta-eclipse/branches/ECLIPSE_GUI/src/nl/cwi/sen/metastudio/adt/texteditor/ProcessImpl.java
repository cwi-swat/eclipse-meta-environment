package nl.cwi.sen.metastudio.adt.texteditor;

import java.io.InputStream;
import java.io.IOException;

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
    return term.isEqual(peer.toTerm());
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
     throw new RuntimeException("This Process has no ToChild");
  }

  public Process setToChild(Pipe _toChild)
  {
     throw new RuntimeException("This Process has no ToChild");
  }

  public Pipe getFromChild()
  {
     throw new RuntimeException("This Process has no FromChild");
  }

  public Process setFromChild(Pipe _fromChild)
  {
     throw new RuntimeException("This Process has no FromChild");
  }

}

