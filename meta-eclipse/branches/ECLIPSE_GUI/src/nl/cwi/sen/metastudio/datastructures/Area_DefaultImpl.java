package nl.cwi.sen.metastudio.datastructures;

abstract public class Area_DefaultImpl
extends Area
{
  static private aterm.ATerm pattern = null;

  protected aterm.ATerm getPattern() {
    return pattern;
  }
  private static int index_start = 0;
  private static int index_length = 1;
  public shared.SharedObject duplicate() {
    Area_Default clone = new Area_Default();
     clone.init(hashCode(), getAnnotations(), getAFun(), getArgumentArray());
    return clone;
  }

  protected aterm.ATermAppl make(aterm.AFun fun, aterm.ATerm[] i_args, aterm.ATermList annos) {
    return getDatastructuresFactory().makeArea_Default(fun, i_args, annos);
  }
  static public void initializePattern()
  {
    pattern = getStaticFactory().parse("area(<int>,<int>)");
  }

  static public Area fromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(pattern);

    if (children != null) {
      Area tmp = getStaticDatastructuresFactory().makeArea_Default((Integer) children.get(0), (Integer) children.get(1));
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
      args.add(new Integer(((aterm.ATermInt) getArgument(1)).getInt()));
      setTerm(getFactory().make(getPattern(), args));
    }
    return term;
  }

  public boolean isDefault()
  {
    return true;
  }

  public boolean hasStart()
  {
    return true;
  }

  public boolean hasLength()
  {
    return true;
  }

  public Integer getStart()
  {
   return new Integer(((aterm.ATermInt) this.getArgument(index_start)).getInt());
  }

  public Area setStart(Integer _start)
  {
    return (Area) super.setArgument(getFactory().makeInt(_start.intValue()), index_start);
  }

  public Integer getLength()
  {
   return new Integer(((aterm.ATermInt) this.getArgument(index_length)).getInt());
  }

  public Area setLength(Integer _length)
  {
    return (Area) super.setArgument(getFactory().makeInt(_length.intValue()), index_length);
  }

  public aterm.ATermAppl setArgument(aterm.ATerm arg, int i) {
    switch(i) {
      case 0:
        if (! (arg instanceof aterm.ATermInt)) { 
          throw new RuntimeException("Argument 0 of a Area_Default should have type int");
        }
        break;
      case 1:
        if (! (arg instanceof aterm.ATermInt)) { 
          throw new RuntimeException("Argument 1 of a Area_Default should have type int");
        }
        break;
      default: throw new RuntimeException("Area_Default does not have an argument at " + i );
    }
    return super.setArgument(arg, i);
  }
}
