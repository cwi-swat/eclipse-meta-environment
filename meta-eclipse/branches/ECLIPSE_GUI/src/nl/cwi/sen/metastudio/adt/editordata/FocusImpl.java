package nl.cwi.sen.metastudio.adt.editordata;


abstract public class FocusImpl extends EditorDataConstructor
{
  protected FocusImpl(EditorDataFactory factory) {
     super(factory);
  }
  protected void init(int hashCode, aterm.ATermList annos, aterm.AFun fun,	aterm.ATerm[] args) {
    super.init(hashCode, annos, fun, args);
  }
  protected void initHashCode(aterm.ATermList annos, aterm.AFun fun, aterm.ATerm[] i_args) {
  	super.initHashCode(annos, fun, i_args);
  }
  public boolean isEqual(Focus peer)
  {
    return super.isEqual(peer);
  }
  public boolean isSortFocus()  {
    return true;
  }

  public boolean isEmpty()
  {
    return false;
  }

  public boolean isNotEmpty()
  {
    return false;
  }

  public boolean hasPath()
  {
    return false;
  }

  public boolean hasSort()
  {
    return false;
  }

  public boolean hasArea()
  {
    return false;
  }

  public boolean hasUnparsed()
  {
    return false;
  }

  public Path getPath()
  {
     throw new UnsupportedOperationException("This Focus has no Path");
  }

  public Focus setPath(Path _path)
  {
     throw new IllegalArgumentException("Illegal argument: " + _path);
  }

  public String getSort()
  {
     throw new UnsupportedOperationException("This Focus has no Sort");
  }

  public Focus setSort(String _sort)
  {
     throw new IllegalArgumentException("Illegal argument: " + _sort);
  }

  public Area getArea()
  {
     throw new UnsupportedOperationException("This Focus has no Area");
  }

  public Focus setArea(Area _area)
  {
     throw new IllegalArgumentException("Illegal argument: " + _area);
  }

  public int getUnparsed()
  {
     throw new UnsupportedOperationException("This Focus has no Unparsed");
  }

  public Focus setUnparsed(int _unparsed)
  {
     throw new IllegalArgumentException("Illegal argument: " + _unparsed);
  }

}

