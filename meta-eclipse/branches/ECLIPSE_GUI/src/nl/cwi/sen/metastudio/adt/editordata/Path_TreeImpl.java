package nl.cwi.sen.metastudio.adt.editordata;

abstract public class Path_TreeImpl
extends Path
{
  protected void init(int hashCode, aterm.ATermList annos, aterm.AFun fun,	aterm.ATerm[] args) {
    super.init(hashCode, annos, fun, args);
  }
  protected void initHashCode(aterm.ATermList annos, aterm.AFun fun, aterm.ATerm[] i_args) {
  	super.initHashCode(annos, fun, i_args);
  }
  protected Path_TreeImpl(EditorDataFactory factory) {
    super(factory);
  }
  private static int index_steps = 0;
  public shared.SharedObject duplicate() {
    Path_Tree clone = new Path_Tree(factory);
     clone.init(hashCode(), getAnnotations(), getAFun(), getArgumentArray());
    return clone;
  }

  public boolean equivalent(shared.SharedObject peer) {
    if (peer instanceof Path_Tree) {
      return super.equivalent(peer);
    }
    return false;
  }
  protected aterm.ATermAppl make(aterm.AFun fun, aterm.ATerm[] i_args, aterm.ATermList annos) {
    return getEditorDataFactory().makePath_Tree(fun, i_args, annos);
  }
  public aterm.ATerm toTerm() {
    if (term == null) {
      term = getEditorDataFactory().toTerm(this);
    }
    return term;
  }

  public boolean isTree()
  {
    return true;
  }

  public boolean hasSteps()
  {
    return true;
  }

  public Steps getSteps()
  {
    return (Steps) this.getArgument(index_steps) ;
  }

  public Path setSteps(Steps _steps)
  {
    return (Path) super.setArgument(_steps, index_steps);
  }

  public aterm.ATermAppl setArgument(aterm.ATerm arg, int i) {
    switch(i) {
      case 0:
        if (! (arg instanceof Steps)) { 
          throw new RuntimeException("Argument 0 of a Path_Tree should have type Steps");
        }
        break;
      default: throw new RuntimeException("Path_Tree does not have an argument at " + i );
    }
    return super.setArgument(arg, i);
  }
  protected int hashFunction() {
    int c = 0 + (getAnnotations().hashCode()<<8);
    int a = 0x9e3779b9;
    int b = (getAFun().hashCode()<<8);
    a += (getArgument(0).hashCode() << 0);

    a -= b; a -= c; a ^= (c >> 13);
    b -= c; b -= a; b ^= (a << 8);
    c -= a; c -= b; c ^= (b >> 13);
    a -= b; a -= c; a ^= (c >> 12);
    b -= c; b -= a; b ^= (a << 16);
    c -= a; c -= b; c ^= (b >> 5);
    a -= b; a -= c; a ^= (c >> 3);
    b -= c; b -= a; b ^= (a << 10);
    c -= a; c -= b; c ^= (b >> 15);

    return c;
  }
}
