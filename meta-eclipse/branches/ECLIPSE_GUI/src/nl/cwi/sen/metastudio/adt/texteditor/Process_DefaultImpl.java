package nl.cwi.sen.metastudio.adt.texteditor;

abstract public class Process_DefaultImpl
extends Process
{
  protected void init(int hashCode, aterm.ATermList annos, aterm.AFun fun,	aterm.ATerm[] args) {
    super.init(hashCode, annos, fun, args);
  }
  protected void initHashCode(aterm.ATermList annos, aterm.AFun fun, aterm.ATerm[] i_args) {
  	super.initHashCode(annos, fun, i_args);
  }
  protected Process_DefaultImpl(TextEditorFactory factory) {
    super(factory);
  }
  private static int index_toChild = 0;
  private static int index_fromChild = 1;
  public shared.SharedObject duplicate() {
    Process_Default clone = new Process_Default(factory);
     clone.init(hashCode(), getAnnotations(), getAFun(), getArgumentArray());
    return clone;
  }

  public boolean equivalent(shared.SharedObject peer) {
    if (peer instanceof Process_Default) {
      return super.equivalent(peer);
    }
    return false;
  }
  protected aterm.ATermAppl make(aterm.AFun fun, aterm.ATerm[] i_args, aterm.ATermList annos) {
    return getTextEditorFactory().makeProcess_Default(fun, i_args, annos);
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

  public boolean hasToChild()
  {
    return true;
  }

  public boolean hasFromChild()
  {
    return true;
  }

  public Pipe getToChild()
  {
    return (Pipe) this.getArgument(index_toChild) ;
  }

  public Process setToChild(Pipe _toChild)
  {
    return (Process) super.setArgument(_toChild, index_toChild);
  }

  public Pipe getFromChild()
  {
    return (Pipe) this.getArgument(index_fromChild) ;
  }

  public Process setFromChild(Pipe _fromChild)
  {
    return (Process) super.setArgument(_fromChild, index_fromChild);
  }

  public aterm.ATermAppl setArgument(aterm.ATerm arg, int i) {
    switch(i) {
      case 0:
        if (! (arg instanceof Pipe)) { 
          throw new RuntimeException("Argument 0 of a Process_Default should have type Pipe");
        }
        break;
      case 1:
        if (! (arg instanceof Pipe)) { 
          throw new RuntimeException("Argument 1 of a Process_Default should have type Pipe");
        }
        break;
      default: throw new RuntimeException("Process_Default does not have an argument at " + i );
    }
    return super.setArgument(arg, i);
  }
  protected int hashFunction() {
    int c = 0 + (getAnnotations().hashCode()<<8);
    int a = 0x9e3779b9;
    int b = (getAFun().hashCode()<<8);
    a += (getArgument(1).hashCode() << 8);
    a += (getArgument(0).hashCode() << 0);

    a -= b; a -= c; a ^= (c >> 13);
    b -= c; b -= a; b ^= (a << 8);
    c -= a; c -= b; c ^= (b >> 13);
    a -= b; a -= c; a ^= (c >> 12);
    b -= c; b -= a; b ^= (a << 16);
    c -= a; c -= b; c ^= (b >> 5);
    a -= b; a -= c; a ^= (c >> 3);
    b -= c; b -= a; b ^= (a << 10);
    c -= a; c -= b; c ^= (b >> 15);

    return c;
  }
}
