package nl.cwi.sen.metastudio.adt.editordata;


abstract public class MoveImpl extends EditorDataConstructor
{
  protected MoveImpl(EditorDataFactory factory) {
     super(factory);
  }
  protected void init(int hashCode, aterm.ATermList annos, aterm.AFun fun,	aterm.ATerm[] args) {
    super.init(hashCode, annos, fun, args);
  }
  protected void initHashCode(aterm.ATermList annos, aterm.AFun fun, aterm.ATerm[] i_args) {
  	super.initHashCode(annos, fun, i_args);
  }
  public boolean isEqual(Move peer)
  {
    return super.isEqual(peer);
  }
  public boolean isSortMove()  {
    return true;
  }

  public boolean isLeft()
  {
    return false;
  }

  public boolean isRight()
  {
    return false;
  }

  public boolean isUp()
  {
    return false;
  }

  public boolean isDown()
  {
    return false;
  }

}

