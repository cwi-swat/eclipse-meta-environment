package nl.cwi.sen.metastudio.adt.editordata;


abstract public class AreaImpl extends EditorDataConstructor
{
  protected AreaImpl(EditorDataFactory factory) {
     super(factory);
  }
  protected void init(int hashCode, aterm.ATermList annos, aterm.AFun fun,	aterm.ATerm[] args) {
    super.init(hashCode, annos, fun, args);
  }
  protected void initHashCode(aterm.ATermList annos, aterm.AFun fun, aterm.ATerm[] i_args) {
  	super.initHashCode(annos, fun, i_args);
  }
  public boolean isEqual(Area peer)
  {
    return super.isEqual(peer);
  }
  public boolean isSortArea()  {
    return true;
  }

  public boolean isDefault()
  {
    return false;
  }

  public boolean hasStart()
  {
    return false;
  }

  public boolean hasLength()
  {
    return false;
  }

  public int getStart()
  {
     throw new UnsupportedOperationException("This Area has no Start");
  }

  public Area setStart(int _start)
  {
     throw new IllegalArgumentException("Illegal argument: " + _start);
  }

  public int getLength()
  {
     throw new UnsupportedOperationException("This Area has no Length");
  }

  public Area setLength(int _length)
  {
     throw new IllegalArgumentException("Illegal argument: " + _length);
  }

}

