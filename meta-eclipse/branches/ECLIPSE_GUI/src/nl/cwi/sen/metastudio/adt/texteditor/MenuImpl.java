package nl.cwi.sen.metastudio.adt.texteditor;


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
    return super.isEqual(peer);
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
     throw new UnsupportedOperationException("This Menu has no Main");
  }

  public Menu setMain(String _main)
  {
     throw new IllegalArgumentException("Illegal argument: " + _main);
  }

  public String getSub()
  {
     throw new UnsupportedOperationException("This Menu has no Sub");
  }

  public Menu setSub(String _sub)
  {
     throw new IllegalArgumentException("Illegal argument: " + _sub);
  }

}

