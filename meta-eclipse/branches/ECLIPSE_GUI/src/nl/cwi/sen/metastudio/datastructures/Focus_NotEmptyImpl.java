package nl.cwi.sen.metastudio.datastructures;

abstract public class Focus_NotEmptyImpl
extends Focus
{
  static private aterm.ATerm pattern = null;

  protected aterm.ATerm getPattern() {
    return pattern;
  }
  private static int index_path = 0;
  private static int index_sort = 1;
  private static int index_area = 2;
  private static int index_unparsed = 3;
  public shared.SharedObject duplicate() {
    Focus_NotEmpty clone = new Focus_NotEmpty();
     clone.init(hashCode(), getAnnotations(), getAFun(), getArgumentArray());
    return clone;
  }

  protected aterm.ATermAppl make(aterm.AFun fun, aterm.ATerm[] i_args, aterm.ATermList annos) {
    return getDatastructuresFactory().makeFocus_NotEmpty(fun, i_args, annos);
  }
  static public void initializePattern()
  {
    pattern = getStaticFactory().parse("focus(<term>,<str>,<term>,<int>)");
  }

  static public Focus fromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(pattern);

    if (children != null) {
      Focus tmp = getStaticDatastructuresFactory().makeFocus_NotEmpty(Path.fromTerm( (aterm.ATerm) children.get(0)), (String) children.get(1), Area.fromTerm( (aterm.ATerm) children.get(2)), (Integer) children.get(3));
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
      args.add(((DatastructuresConstructor) getArgument(0)).toTerm());
      args.add(((aterm.ATermAppl) getArgument(1)).getAFun().getName());
      args.add(((DatastructuresConstructor) getArgument(2)).toTerm());
      args.add(new Integer(((aterm.ATermInt) getArgument(3)).getInt()));
      setTerm(getFactory().make(getPattern(), args));
    }
    return term;
  }

  public boolean isNotEmpty()
  {
    return true;
  }

  public boolean hasPath()
  {
    return true;
  }

  public boolean hasSort()
  {
    return true;
  }

  public boolean hasArea()
  {
    return true;
  }

  public boolean hasUnparsed()
  {
    return true;
  }

  public Path getPath()
  {
    return (Path) this.getArgument(index_path) ;
  }

  public Focus setPath(Path _path)
  {
    return (Focus) super.setArgument(_path, index_path);
  }

  public String getSort()
  {
   return ((aterm.ATermAppl) this.getArgument(index_sort)).getAFun().getName();
  }

  public Focus setSort(String _sort)
  {
    return (Focus) super.setArgument(getFactory().makeAppl(getFactory().makeAFun(_sort, 0, true)), index_sort);
  }

  public Area getArea()
  {
    return (Area) this.getArgument(index_area) ;
  }

  public Focus setArea(Area _area)
  {
    return (Focus) super.setArgument(_area, index_area);
  }

  public Integer getUnparsed()
  {
   return new Integer(((aterm.ATermInt) this.getArgument(index_unparsed)).getInt());
  }

  public Focus setUnparsed(Integer _unparsed)
  {
    return (Focus) super.setArgument(getFactory().makeInt(_unparsed.intValue()), index_unparsed);
  }

  public aterm.ATermAppl setArgument(aterm.ATerm arg, int i) {
    switch(i) {
      case 0:
        if (! (arg instanceof Path)) { 
          throw new RuntimeException("Argument 0 of a Focus_NotEmpty should have type Path");
        }
        break;
      case 1:
        if (! (arg instanceof aterm.ATermAppl)) { 
          throw new RuntimeException("Argument 1 of a Focus_NotEmpty should have type str");
        }
        break;
      case 2:
        if (! (arg instanceof Area)) { 
          throw new RuntimeException("Argument 2 of a Focus_NotEmpty should have type Area");
        }
        break;
      case 3:
        if (! (arg instanceof aterm.ATermInt)) { 
          throw new RuntimeException("Argument 3 of a Focus_NotEmpty should have type int");
        }
        break;
      default: throw new RuntimeException("Focus_NotEmpty does not have an argument at " + i );
    }
    return super.setArgument(arg, i);
  }
}
