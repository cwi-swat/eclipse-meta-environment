package nl.cwi.sen.metastudio.adt.editordata;


abstract public class PathImpl extends EditorDataConstructor
{
  protected PathImpl(EditorDataFactory factory) {
     super(factory);
  }
  protected void init(int hashCode, aterm.ATermList annos, aterm.AFun fun,	aterm.ATerm[] args) {
    super.init(hashCode, annos, fun, args);
  }
  protected void initHashCode(aterm.ATermList annos, aterm.AFun fun, aterm.ATerm[] i_args) {
  	super.initHashCode(annos, fun, i_args);
  }
  public boolean isEqual(Path peer)
  {
    return super.isEqual(peer);
  }
  public boolean isSortPath()  {
    return true;
  }

  public boolean isRoot()
  {
    return false;
  }

  public boolean isLeftLayout()
  {
    return false;
  }

  public boolean isTree()
  {
    return false;
  }

  public boolean isRightLayout()
  {
    return false;
  }

  public boolean hasSteps()
  {
    return false;
  }

  public Steps getSteps()
  {
     throw new UnsupportedOperationException("This Path has no Steps");
  }

  public Path setSteps(Steps _steps)
  {
     throw new IllegalArgumentException("Illegal argument: " + _steps);
  }

}

