package nl.cwi.sen.metastudio.adt.texteditor;

abstract public class Menu_DefaultImpl
extends Menu
{
  protected void init(int hashCode, aterm.ATermList annos, aterm.AFun fun,	aterm.ATerm[] args) {
    super.init(hashCode, annos, fun, args);
  }
  protected void initHashCode(aterm.ATermList annos, aterm.AFun fun, aterm.ATerm[] i_args) {
  	super.initHashCode(annos, fun, i_args);
  }
  protected Menu_DefaultImpl(TextEditorFactory factory) {
    super(factory);
  }
  private static int index_main = 0;
  private static int index_sub = 1;
  public shared.SharedObject duplicate() {
    Menu_Default clone = new Menu_Default(factory);
     clone.init(hashCode(), getAnnotations(), getAFun(), getArgumentArray());
    return clone;
  }

  public boolean equivalent(shared.SharedObject peer) {
    if (peer instanceof Menu_Default) {
      return super.equivalent(peer);
    }
    return false;
  }
  protected aterm.ATermAppl make(aterm.AFun fun, aterm.ATerm[] i_args, aterm.ATermList annos) {
    return getTextEditorFactory().makeMenu_Default(fun, i_args, annos);
  }
  public aterm.ATerm toTerm() {
    if (term == null) {
      term = getTextEditorFactory().toTerm(this);
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
