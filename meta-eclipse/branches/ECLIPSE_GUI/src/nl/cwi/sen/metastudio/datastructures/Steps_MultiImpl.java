package nl.cwi.sen.metastudio.datastructures;

abstract public class Steps_MultiImpl
extends Steps
{
  static private aterm.ATerm pattern = null;

  protected aterm.ATerm getPattern() {
    return pattern;
  }
  private static int index_head = 0;
  private static int index_tail = 1;
  public shared.SharedObject duplicate() {
    Steps_Multi clone = new Steps_Multi();
     clone.init(hashCode(), getAnnotations(), getAFun(), getArgumentArray());
    return clone;
  }

  protected aterm.ATermAppl make(aterm.AFun fun, aterm.ATerm[] i_args, aterm.ATermList annos) {
    return getDatastructuresFactory().makeSteps_Multi(fun, i_args, annos);
  }
  static public void initializePattern()
  {
    pattern = getStaticFactory().parse("[<int>,<list>]");
  }

  static public Steps fromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(pattern);

    if (children != null) {
      Steps tmp = getStaticDatastructuresFactory().makeSteps_Multi((Integer) children.get(0), Steps.fromTerm( (aterm.ATerm) children.get(1)));
      tmp.setTerm(trm);
      return tmp;
    }
    else {
      return null;
    }
  }
  public aterm.ATerm toTerm() {
    if(term == null) {
      java.util.List args = new java.util.LinkedList();
      args.add(new Integer(((aterm.ATermInt) getArgument(0)).getInt()));
      args.add(((DatastructuresConstructor) getArgument(1)).toTerm());
      setTerm(getFactory().make(getPattern(), args));
    }
    return term;
  }

  public boolean isMulti()
  {
    return true;
  }

  public boolean hasHead()
  {
    return true;
  }

  public boolean hasTail()
  {
    return true;
  }

  public Integer getHead()
  {
   return new Integer(((aterm.ATermInt) this.getArgument(index_head)).getInt());
  }

  public Steps setHead(Integer _head)
  {
    return (Steps) super.setArgument(getFactory().makeInt(_head.intValue()), index_head);
  }

  public Steps getTail()
  {
    return (Steps) this.getArgument(index_tail) ;
  }

  public Steps setTail(Steps _tail)
  {
    return (Steps) super.setArgument(_tail, index_tail);
  }

  public aterm.ATermAppl setArgument(aterm.ATerm arg, int i) {
    switch(i) {
      case 0:
        if (! (arg instanceof aterm.ATermInt)) { 
          throw new RuntimeException("Argument 0 of a Steps_Multi should have type int");
        }
        break;
      case 1:
        if (! (arg instanceof Steps)) { 
          throw new RuntimeException("Argument 1 of a Steps_Multi should have type Steps");
        }
        break;
      default: throw new RuntimeException("Steps_Multi does not have an argument at " + i );
    }
    return super.setArgument(arg, i);
  }
}
