package nl.cwi.sen.metastudio.adt.editordata;

abstract public class Steps_MultiImpl
extends Steps
{
  protected void init(int hashCode, aterm.ATermList annos, aterm.AFun fun,	aterm.ATerm[] args) {
    super.init(hashCode, annos, fun, args);
  }
  protected void initHashCode(aterm.ATermList annos, aterm.AFun fun, aterm.ATerm[] i_args) {
  	super.initHashCode(annos, fun, i_args);
  }
  protected Steps_MultiImpl(EditorDataFactory factory) {
    super(factory);
  }
  private static int index_head = 0;
  private static int index_tail = 1;
  public shared.SharedObject duplicate() {
    Steps_Multi clone = new Steps_Multi(factory);
     clone.init(hashCode(), getAnnotations(), getAFun(), getArgumentArray());
    return clone;
  }

  public boolean equivalent(shared.SharedObject peer) {
    if (peer instanceof Steps_Multi) {
      return super.equivalent(peer);
    }
    return false;
  }
  protected aterm.ATermAppl make(aterm.AFun fun, aterm.ATerm[] i_args, aterm.ATermList annos) {
    return getEditorDataFactory().makeSteps_Multi(fun, i_args, annos);
  }
  public aterm.ATerm toTerm() {
    if (term == null) {
      term = getEditorDataFactory().toTerm(this);
    }
    return term;
  }

  public boolean isMulti()
  {
    return true;
  }

  public boolean hasHead()
  {
    return true;
  }

  public boolean hasTail()
  {
    return true;
  }

  public int getHead()
  {
   return ((aterm.ATermInt) this.getArgument(index_head)).getInt();
  }

  public Steps setHead(int _head)
  {
    return (Steps) super.setArgument(getFactory().makeInt(_head), index_head);
  }

  public Steps getTail()
  {
    return (Steps) this.getArgument(index_tail) ;
  }

  public Steps setTail(Steps _tail)
  {
    return (Steps) super.setArgument(_tail, index_tail);
  }

  public aterm.ATermAppl setArgument(aterm.ATerm arg, int i) {
    switch(i) {
      case 0:
        if (! (arg instanceof aterm.ATermInt)) { 
          throw new RuntimeException("Argument 0 of a Steps_Multi should have type int");
        }
        break;
      case 1:
        if (! (arg instanceof Steps)) { 
          throw new RuntimeException("Argument 1 of a Steps_Multi should have type Steps");
        }
        break;
      default: throw new RuntimeException("Steps_Multi does not have an argument at " + i );
    }
    return super.setArgument(arg, i);
  }
}
