package nl.cwi.sen.metastudio.adt.texteditor;

abstract public class Event_ContentsImpl
extends Event
{
  protected void init(int hashCode, aterm.ATermList annos, aterm.AFun fun,	aterm.ATerm[] args) {
    super.init(hashCode, annos, fun, args);
  }
  protected void initHashCode(aterm.ATermList annos, aterm.AFun fun, aterm.ATerm[] i_args) {
  	super.initHashCode(annos, fun, i_args);
  }
  protected Event_ContentsImpl(TextEditorFactory factory) {
    super(factory);
  }
  private static int index_text = 0;
  public shared.SharedObject duplicate() {
    Event_Contents clone = new Event_Contents(factory);
     clone.init(hashCode(), getAnnotations(), getAFun(), getArgumentArray());
    return clone;
  }

  public boolean equivalent(shared.SharedObject peer) {
    if (peer instanceof Event_Contents) {
      return super.equivalent(peer);
    }
    return false;
  }
  protected aterm.ATermAppl make(aterm.AFun fun, aterm.ATerm[] i_args, aterm.ATermList annos) {
    return getTextEditorFactory().makeEvent_Contents(fun, i_args, annos);
  }
  public aterm.ATerm toTerm() {
    if (term == null) {
      term = getTextEditorFactory().toTerm(this);
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
