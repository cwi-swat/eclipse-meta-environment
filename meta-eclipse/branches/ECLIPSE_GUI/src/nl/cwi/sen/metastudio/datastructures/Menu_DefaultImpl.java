package nl.cwi.sen.metastudio.datastructures;

abstract public class Menu_DefaultImpl
extends Menu
{
  static private aterm.ATerm pattern = null;

  protected aterm.ATerm getPattern() {
    return pattern;
  }
  private static int index_main = 0;
  private static int index_sub = 1;
  public shared.SharedObject duplicate() {
    Menu_Default clone = new Menu_Default();
     clone.init(hashCode(), getAnnotations(), getAFun(), getArgumentArray());
    return clone;
  }

  protected aterm.ATermAppl make(aterm.AFun fun, aterm.ATerm[] i_args, aterm.ATermList annos) {
    return getDatastructuresFactory().makeMenu_Default(fun, i_args, annos);
  }
  static public void initializePattern()
  {
    pattern = getStaticFactory().parse("menu([<str>,<str>])");
  }

  static public Menu fromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(pattern);

    if (children != null) {
      Menu tmp = getStaticDatastructuresFactory().makeMenu_Default((String) children.get(0), (String) children.get(1));
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
      args.add(((aterm.ATermAppl) getArgument(0)).getAFun().getName());
      args.add(((aterm.ATermAppl) getArgument(1)).getAFun().getName());
      setTerm(getFactory().make(getPattern(), args));
    }
    return term;
  }

  public boolean isDefault()
  {
    return true;
  }

  public boolean hasMain()
  {
    return true;
  }

  public boolean hasSub()
  {
    return true;
  }

  public String getMain()
  {
   return ((aterm.ATermAppl) this.getArgument(index_main)).getAFun().getName();
  }

  public Menu setMain(String _main)
  {
    return (Menu) super.setArgument(getFactory().makeAppl(getFactory().makeAFun(_main, 0, true)), index_main);
  }

  public String getSub()
  {
   return ((aterm.ATermAppl) this.getArgument(index_sub)).getAFun().getName();
  }

  public Menu setSub(String _sub)
  {
    return (Menu) super.setArgument(getFactory().makeAppl(getFactory().makeAFun(_sub, 0, true)), index_sub);
  }

  public aterm.ATermAppl setArgument(aterm.ATerm arg, int i) {
    switch(i) {
      case 0:
        if (! (arg instanceof aterm.ATermAppl)) { 
          throw new RuntimeException("Argument 0 of a Menu_Default should have type str");
        }
        break;
      case 1:
        if (! (arg instanceof aterm.ATermAppl)) { 
          throw new RuntimeException("Argument 1 of a Menu_Default should have type str");
        }
        break;
      default: throw new RuntimeException("Menu_Default does not have an argument at " + i );
    }
    return super.setArgument(arg, i);
  }
}
