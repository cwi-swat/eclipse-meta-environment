package nl.cwi.sen.metastudio.datastructures;

abstract public class Event_MenuImpl
extends Event
{
  static private aterm.ATerm pattern = null;

  protected aterm.ATerm getPattern() {
    return pattern;
  }
  private static int index_menu = 0;
  public shared.SharedObject duplicate() {
    Event_Menu clone = new Event_Menu();
     clone.init(hashCode(), getAnnotations(), getAFun(), getArgumentArray());
    return clone;
  }

  protected aterm.ATermAppl make(aterm.AFun fun, aterm.ATerm[] i_args, aterm.ATermList annos) {
    return getDatastructuresFactory().makeEvent_Menu(fun, i_args, annos);
  }
  static public void initializePattern()
  {
    pattern = getStaticFactory().parse("menu-event(<term>)");
  }

  static public Event fromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(pattern);

    if (children != null) {
      Event tmp = getStaticDatastructuresFactory().makeEvent_Menu(Menu.fromTerm( (aterm.ATerm) children.get(0)));
      tmp.setTerm(trm);
      return tmp;
    }
    else {
      return null;
    }
  }
  public boolean isMenu()
  {
    return true;
  }

  public boolean hasMenu()
  {
    return true;
  }

  public Menu getMenu()
  {
    return (Menu) this.getArgument(index_menu) ;
  }

  public Event setMenu(Menu _menu)
  {
    return (Event) super.setArgument(_menu, index_menu);
  }

  public aterm.ATermAppl setArgument(aterm.ATerm arg, int i) {
    switch(i) {
      case 0:
        if (! (arg instanceof Menu)) { 
          throw new RuntimeException("Argument 0 of a Event_Menu should have type Menu");
        }
        break;
      default: throw new RuntimeException("Event_Menu does not have an argument at " + i );
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
