package nl.cwi.sen.metastudio.datastructures;

abstract public class Path_TermImpl
extends Path
{
  static private aterm.ATerm pattern = null;

  protected aterm.ATerm getPattern() {
    return pattern;
  }
  private static int index_steps = 0;
  public shared.SharedObject duplicate() {
    Path_Term clone = new Path_Term();
     clone.init(hashCode(), getAnnotations(), getAFun(), getArgumentArray());
    return clone;
  }

  protected aterm.ATermAppl make(aterm.AFun fun, aterm.ATerm[] i_args, aterm.ATermList annos) {
    return getDatastructuresFactory().makePath_Term(fun, i_args, annos);
  }
  static public void initializePattern()
  {
    pattern = getStaticFactory().parse("term(<term>)");
  }

  static public Path fromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(pattern);

    if (children != null) {
      Path tmp = getStaticDatastructuresFactory().makePath_Term(Steps.fromTerm( (aterm.ATerm) children.get(0)));
      tmp.setTerm(trm);
      return tmp;
    }
    else {
      return null;
    }
  }
  public boolean isTerm()
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
          throw new RuntimeException("Argument 0 of a Path_Term should have type Steps");
        }
        break;
      default: throw new RuntimeException("Path_Term does not have an argument at " + i );
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
