package nl.cwi.sen.metastudio.datastructures;

abstract public class Event_MouseImpl
extends Event
{
  static private aterm.ATerm pattern = null;

  protected aterm.ATerm getPattern() {
    return pattern;
  }
  private static int index_location = 0;
  public shared.SharedObject duplicate() {
    Event_Mouse clone = new Event_Mouse();
     clone.init(hashCode(), getAnnotations(), getAFun(), getArgumentArray());
    return clone;
  }

  protected aterm.ATermAppl make(aterm.AFun fun, aterm.ATerm[] i_args, aterm.ATermList annos) {
    return getDatastructuresFactory().makeEvent_Mouse(fun, i_args, annos);
  }
  static public void initializePattern()
  {
    pattern = getStaticFactory().parse("mouse-event(<int>)");
  }

  static public Event fromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(pattern);

    if (children != null) {
      Event tmp = getStaticDatastructuresFactory().makeEvent_Mouse((Integer) children.get(0));
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
      setTerm(getFactory().make(getPattern(), args));
    }
    return term;
  }

  public boolean isMouse()
  {
    return true;
  }

  public boolean hasLocation()
  {
    return true;
  }

  public Integer getLocation()
  {
   return new Integer(((aterm.ATermInt) this.getArgument(index_location)).getInt());
  }

  public Event setLocation(Integer _location)
  {
    return (Event) super.setArgument(getFactory().makeInt(_location.intValue()), index_location);
  }

  public aterm.ATermAppl setArgument(aterm.ATerm arg, int i) {
    switch(i) {
      case 0:
        if (! (arg instanceof aterm.ATermInt)) { 
          throw new RuntimeException("Argument 0 of a Event_Mouse should have type int");
        }
        break;
      default: throw new RuntimeException("Event_Mouse does not have an argument at " + i );
    }
    return super.setArgument(arg, i);
  }
}
