package nl.cwi.sen.metastudio.adt.texteditor;

import java.io.InputStream;
import java.io.IOException;

abstract public class PipeImpl extends TextEditorConstructor
{
  protected PipeImpl(TextEditorFactory factory) {
     super(factory);
  }
  protected void init(int hashCode, aterm.ATermList annos, aterm.AFun fun,	aterm.ATerm[] args) {
    super.init(hashCode, annos, fun, args);
  }
  protected void initHashCode(aterm.ATermList annos, aterm.AFun fun, aterm.ATerm[] i_args) {
  	super.initHashCode(annos, fun, i_args);
  }
  public boolean isEqual(Pipe peer)
  {
    return term.isEqual(peer.toTerm());
  }
  public boolean isSortPipe()  {
    return true;
  }

  public boolean isDefault()
  {
    return false;
  }

  public boolean hasRead()
  {
    return false;
  }

  public boolean hasWrite()
  {
    return false;
  }

  public int getRead()
  {
     throw new RuntimeException("This Pipe has no Read");
  }

  public Pipe setRead(int _read)
  {
     throw new RuntimeException("This Pipe has no Read");
  }

  public int getWrite()
  {
     throw new RuntimeException("This Pipe has no Write");
  }

  public Pipe setWrite(int _write)
  {
     throw new RuntimeException("This Pipe has no Write");
  }

}

