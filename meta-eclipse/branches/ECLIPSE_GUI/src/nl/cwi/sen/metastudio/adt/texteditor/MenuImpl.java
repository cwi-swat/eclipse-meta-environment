package nl.cwi.sen.metastudio.adt.texteditor;

import java.io.InputStream;
import java.io.IOException;

abstract public class MenuImpl extends TextEditorConstructor
{
  protected MenuImpl(TextEditorFactory factory) {
     super(factory);
  }
  protected void init(int hashCode, aterm.ATermList annos, aterm.AFun fun,	aterm.ATerm[] args) {
    super.init(hashCode, annos, fun, args);
  }
  protected void initHashCode(aterm.ATermList annos, aterm.AFun fun, aterm.ATerm[] i_args) {
  	super.initHashCode(annos, fun, i_args);
  }
  public boolean isEqual(Menu peer)
  {
    return term.isEqual(peer.toTerm());
  }
  public boolean isSortMenu()  {
    return true;
  }

  public boolean isDefault()
  {
    return false;
  }

  public boolean hasMain()
  {
    return false;
  }

  public boolean hasSub()
  {
    return false;
  }

  public String getMain()
  {
     throw new RuntimeException("This Menu has no Main");
  }

  public Menu setMain(String _main)
  {
     throw new RuntimeException("This Menu has no Main");
  }

  public String getSub()
  {
     throw new RuntimeException("This Menu has no Sub");
  }

  public Menu setSub(String _sub)
  {
     throw new RuntimeException("This Menu has no Sub");
  }

}

