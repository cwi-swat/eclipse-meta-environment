package nl.cwi.sen.metastudio.adt.texteditor;

abstract public class Pipe_DefaultImpl
extends Pipe
{
  protected void init(int hashCode, aterm.ATermList annos, aterm.AFun fun,	aterm.ATerm[] args) {
    super.init(hashCode, annos, fun, args);
  }
  protected void initHashCode(aterm.ATermList annos, aterm.AFun fun, aterm.ATerm[] i_args) {
  	super.initHashCode(annos, fun, i_args);
  }
  protected Pipe_DefaultImpl(TextEditorFactory factory) {
    super(factory);
  }
  private static int index_read = 0;
  private static int index_write = 1;
  public shared.SharedObject duplicate() {
    Pipe_Default clone = new Pipe_Default(factory);
     clone.init(hashCode(), getAnnotations(), getAFun(), getArgumentArray());
    return clone;
  }

  public boolean equivalent(shared.SharedObject peer) {
    if (peer instanceof Pipe_Default) {
      return super.equivalent(peer);
    }
    return false;
  }
  protected aterm.ATermAppl make(aterm.AFun fun, aterm.ATerm[] i_args, aterm.ATermList annos) {
    return getTextEditorFactory().makePipe_Default(fun, i_args, annos);
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

  public boolean hasRead()
  {
    return true;
  }

  public boolean hasWrite()
  {
    return true;
  }

  public int getRead()
  {
   return ((aterm.ATermInt) this.getArgument(index_read)).getInt();
  }

  public Pipe setRead(int _read)
  {
    return (Pipe) super.setArgument(getFactory().makeInt(_read), index_read);
  }

  public int getWrite()
  {
   return ((aterm.ATermInt) this.getArgument(index_write)).getInt();
  }

  public Pipe setWrite(int _write)
  {
    return (Pipe) super.setArgument(getFactory().makeInt(_write), index_write);
  }

  public aterm.ATermAppl setArgument(aterm.ATerm arg, int i) {
    switch(i) {
      case 0:
        if (! (arg instanceof aterm.ATermInt)) { 
          throw new RuntimeException("Argument 0 of a Pipe_Default should have type int");
        }
        break;
      case 1:
        if (! (arg instanceof aterm.ATermInt)) { 
          throw new RuntimeException("Argument 1 of a Pipe_Default should have type int");
        }
        break;
      default: throw new RuntimeException("Pipe_Default does not have an argument at " + i );
    }
    return super.setArgument(arg, i);
  }
}
