package nl.cwi.sen.metastudio.adt.texteditor;

abstract public class Event_MouseImpl
extends Event
{
  protected void init(int hashCode, aterm.ATermList annos, aterm.AFun fun,	aterm.ATerm[] args) {
    super.init(hashCode, annos, fun, args);
  }
  protected void initHashCode(aterm.ATermList annos, aterm.AFun fun, aterm.ATerm[] i_args) {
  	super.initHashCode(annos, fun, i_args);
  }
  protected Event_MouseImpl(TextEditorFactory factory) {
    super(factory);
  }
  private static int index_location = 0;
  public shared.SharedObject duplicate() {
    Event_Mouse clone = new Event_Mouse(factory);
     clone.init(hashCode(), getAnnotations(), getAFun(), getArgumentArray());
    return clone;
  }

  public boolean equivalent(shared.SharedObject peer) {
    if (peer instanceof Event_Mouse) {
      return super.equivalent(peer);
    }
    return false;
  }
  protected aterm.ATermAppl make(aterm.AFun fun, aterm.ATerm[] i_args, aterm.ATermList annos) {
    return getTextEditorFactory().makeEvent_Mouse(fun, i_args, annos);
  }
  public aterm.ATerm toTerm() {
    if (term == null) {
      term = getTextEditorFactory().toTerm(this);
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

  public int getLocation()
  {
   return ((aterm.ATermInt) this.getArgument(index_location)).getInt();
  }

  public Event setLocation(int _location)
  {
    return (Event) super.setArgument(getFactory().makeInt(_location), index_location);
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
