package nl.cwi.sen.metastudio.adt.texteditor;

abstract public class TextEditorConstructor
extends aterm.pure.ATermApplImpl
implements aterm.ATerm
{
  protected aterm.ATerm term = null;

  TextEditorFactory factory = null;

  public TextEditorConstructor(TextEditorFactory factory) {
    super(factory.getPureFactory());
    this.factory = factory;
  }

  protected void init(int hashCode, aterm.ATermList annos, aterm.AFun fun,	aterm.ATerm[] args) {
    super.init(hashCode, annos, fun, args);
  }
  protected void initHashCode(aterm.ATermList annos, aterm.AFun fun, aterm.ATerm[] i_args) {
  	super.initHashCode(annos, fun, i_args);
  }
  abstract public aterm.ATerm toTerm();
  public String toString() {
    return toTerm().toString();
  }
  protected void setTerm(aterm.ATerm term) {
   this.term = term;
  }
  public TextEditorFactory getTextEditorFactory() {
    return factory;
  }
  public boolean isSortAction() {
    return false;
  }

  public boolean isSortActionList() {
    return false;
  }

  public boolean isSortMenu() {
    return false;
  }

  public boolean isSortEvent() {
    return false;
  }

  public boolean isSortProcess() {
    return false;
  }

  public boolean isSortPipe() {
    return false;
  }

}
