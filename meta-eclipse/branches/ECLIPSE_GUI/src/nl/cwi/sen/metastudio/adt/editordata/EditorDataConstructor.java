package nl.cwi.sen.metastudio.adt.editordata;

abstract public class EditorDataConstructor
extends aterm.pure.ATermApplImpl
implements aterm.ATerm
{
  protected aterm.ATerm term = null;

  EditorDataFactory factory = null;

  public EditorDataConstructor(EditorDataFactory factory) {
    super(factory);
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
  public EditorDataFactory getEditorDataFactory() {
    return factory;
  }
  public boolean isSortFocus() {
    return false;
  }

  public boolean isSortArea() {
    return false;
  }

  public boolean isSortFocusList() {
    return false;
  }

  public boolean isSortSymbolList() {
    return false;
  }

  public boolean isSortPath() {
    return false;
  }

  public boolean isSortSteps() {
    return false;
  }

  public boolean isSortMove() {
    return false;
  }

}
