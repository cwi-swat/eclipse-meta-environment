package nl.cwi.sen.metastudio.datastructures;

abstract public class Event_ContentsImpl
extends Event
{
  static private aterm.ATerm pattern = null;

  protected aterm.ATerm getPattern() {
    return pattern;
  }
  private static int index_text = 0;
  public shared.SharedObject duplicate() {
    Event_Contents clone = new Event_Contents();
     clone.init(hashCode(), getAnnotations(), getAFun(), getArgumentArray());
    return clone;
  }

  protected aterm.ATermAppl make(aterm.AFun fun, aterm.ATerm[] i_args, aterm.ATermList annos) {
    return getDatastructuresFactory().makeEvent_Contents(fun, i_args, annos);
  }
  static public void initializePattern()
  {
    pattern = getStaticFactory().parse("contents(<str>)");
  }

  static public Event fromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(pattern);

    if (children != null) {
      Event tmp = getStaticDatastructuresFactory().makeEvent_Contents((String) children.get(0));
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
      setTerm(getFactory().make(getPattern(), args));
    }
    return term;
  }

  public boolean isContents()
  {
    return true;
  }

  public boolean hasText()
  {
    return true;
  }

  public String getText()
  {
   return ((aterm.ATermAppl) this.getArgument(index_text)).getAFun().getName();
  }

  public Event setText(String _text)
  {
    return (Event) super.setArgument(getFactory().makeAppl(getFactory().makeAFun(_text, 0, true)), index_text);
  }

  public aterm.ATermAppl setArgument(aterm.ATerm arg, int i) {
    switch(i) {
      case 0:
        if (! (arg instanceof aterm.ATermAppl)) { 
          throw new RuntimeException("Argument 0 of a Event_Contents should have type str");
        }
        break;
      default: throw new RuntimeException("Event_Contents does not have an argument at " + i );
    }
    return super.setArgument(arg, i);
  }
}
