package nl.cwi.sen.metastudio.datastructures;

abstract public class FocusList_MultiImpl
extends FocusList
{
  static private aterm.ATerm pattern = null;

  protected aterm.ATerm getPattern() {
    return pattern;
  }
  private static int index_head = 0;
  private static int index_tail = 1;
  public shared.SharedObject duplicate() {
    FocusList_Multi clone = new FocusList_Multi();
     clone.init(hashCode(), getAnnotations(), getAFun(), getArgumentArray());
    return clone;
  }

  protected aterm.ATermAppl make(aterm.AFun fun, aterm.ATerm[] i_args, aterm.ATermList annos) {
    return getDatastructuresFactory().makeFocusList_Multi(fun, i_args, annos);
  }
  static public void initializePattern()
  {
    pattern = getStaticFactory().parse("[<term>,<list>]");
  }

  static public FocusList fromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(pattern);

    if (children != null) {
      FocusList tmp = getStaticDatastructuresFactory().makeFocusList_Multi(Focus.fromTerm( (aterm.ATerm) children.get(0)), FocusList.fromTerm( (aterm.ATerm) children.get(1)));
      tmp.setTerm(trm);
      return tmp;
    }
    else {
      return null;
    }
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

  public Focus getHead()
  {
    return (Focus) this.getArgument(index_head) ;
  }

  public FocusList setHead(Focus _head)
  {
    return (FocusList) super.setArgument(_head, index_head);
  }

  public FocusList getTail()
  {
    return (FocusList) this.getArgument(index_tail) ;
  }

  public FocusList setTail(FocusList _tail)
  {
    return (FocusList) super.setArgument(_tail, index_tail);
  }

  public aterm.ATermAppl setArgument(aterm.ATerm arg, int i) {
    switch(i) {
      case 0:
        if (! (arg instanceof Focus)) { 
          throw new RuntimeException("Argument 0 of a FocusList_Multi should have type Focus");
        }
        break;
      case 1:
        if (! (arg instanceof FocusList)) { 
          throw new RuntimeException("Argument 1 of a FocusList_Multi should have type FocusList");
        }
        break;
      default: throw new RuntimeException("FocusList_Multi does not have an argument at " + i );
    }
    return super.setArgument(arg, i);
  }
  protected int hashFunction() {
    int c = getArgument(1).hashCode() + (getAnnotations().hashCode()<<8);
    int a = 0x9e3779b9;
    int b = (getAFun().hashCode()<<8);
    a += (getArgument(1).hashCode() << 8);
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
