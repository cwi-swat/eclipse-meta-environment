package nl.cwi.sen.metastudio.adt.texteditor;

abstract public class Action_GetContentsImpl
extends Action
{
  protected void init(int hashCode, aterm.ATermList annos, aterm.AFun fun,	aterm.ATerm[] args) {
    super.init(hashCode, annos, fun, args);
  }
  protected void initHashCode(aterm.ATermList annos, aterm.AFun fun, aterm.ATerm[] i_args) {
  	super.initHashCode(annos, fun, i_args);
  }
  protected Action_GetContentsImpl(TextEditorFactory factory) {
    super(factory);
  }
  private static int index_focus = 0;
  public shared.SharedObject duplicate() {
    Action_GetContents clone = new Action_GetContents(factory);
     clone.init(hashCode(), getAnnotations(), getAFun(), getArgumentArray());
    return clone;
  }

  public boolean equivalent(shared.SharedObject peer) {
    if (peer instanceof Action_GetContents) {
      return super.equivalent(peer);
    }
    return false;
  }
  protected aterm.ATermAppl make(aterm.AFun fun, aterm.ATerm[] i_args, aterm.ATermList annos) {
    return getTextEditorFactory().makeAction_GetContents(fun, i_args, annos);
  }
  public aterm.ATerm toTerm() {
    if (term == null) {
      term = getTextEditorFactory().toTerm(this);
    }
    return term;
  }

  public boolean isGetContents()
  {
    return true;
  }

  public boolean hasFocus()
  {
    return true;
  }

  public aterm.ATerm getFocus()
  {
   return this.getArgument(index_focus);
  }

  public Action setFocus(aterm.ATerm _focus)
  {
    return (Action) super.setArgument(_focus, index_focus);
  }

  public aterm.ATermAppl setArgument(aterm.ATerm arg, int i) {
    switch(i) {
      case 0:
        if (! (arg instanceof aterm.ATerm)) { 
          throw new RuntimeException("Argument 0 of a Action_GetContents should have type term");
        }
        break;
      default: throw new RuntimeException("Action_GetContents does not have an argument at " + i );
    }
    return super.setArgument(arg, i);
  }
}
