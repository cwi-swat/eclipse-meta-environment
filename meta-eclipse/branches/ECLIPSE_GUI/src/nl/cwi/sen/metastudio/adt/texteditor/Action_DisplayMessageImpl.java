package nl.cwi.sen.metastudio.adt.texteditor;

abstract public class Action_DisplayMessageImpl
extends Action
{
  protected void init(int hashCode, aterm.ATermList annos, aterm.AFun fun,	aterm.ATerm[] args) {
    super.init(hashCode, annos, fun, args);
  }
  protected void initHashCode(aterm.ATermList annos, aterm.AFun fun, aterm.ATerm[] i_args) {
  	super.initHashCode(annos, fun, i_args);
  }
  protected Action_DisplayMessageImpl(TextEditorFactory factory) {
    super(factory);
  }
  private static int index_message = 0;
  public shared.SharedObject duplicate() {
    Action_DisplayMessage clone = new Action_DisplayMessage(factory);
     clone.init(hashCode(), getAnnotations(), getAFun(), getArgumentArray());
    return clone;
  }

  public boolean equivalent(shared.SharedObject peer) {
    if (peer instanceof Action_DisplayMessage) {
      return super.equivalent(peer);
    }
    return false;
  }
  protected aterm.ATermAppl make(aterm.AFun fun, aterm.ATerm[] i_args, aterm.ATermList annos) {
    return getTextEditorFactory().makeAction_DisplayMessage(fun, i_args, annos);
  }
  public aterm.ATerm toTerm() {
    if (term == null) {
      term = getTextEditorFactory().toTerm(this);
    }
    return term;
  }

  public boolean isDisplayMessage()
  {
    return true;
  }

  public boolean hasMessage()
  {
    return true;
  }

  public String getMessage()
  {
   return ((aterm.ATermAppl) this.getArgument(index_message)).getAFun().getName();
  }

  public Action setMessage(String _message)
  {
    return (Action) super.setArgument(getFactory().makeAppl(getFactory().makeAFun(_message, 0, true)), index_message);
  }

  public aterm.ATermAppl setArgument(aterm.ATerm arg, int i) {
    switch(i) {
      case 0:
        if (! (arg instanceof aterm.ATermAppl)) { 
          throw new RuntimeException("Argument 0 of a Action_DisplayMessage should have type str");
        }
        break;
      default: throw new RuntimeException("Action_DisplayMessage does not have an argument at " + i );
    }
    return super.setArgument(arg, i);
  }
}
