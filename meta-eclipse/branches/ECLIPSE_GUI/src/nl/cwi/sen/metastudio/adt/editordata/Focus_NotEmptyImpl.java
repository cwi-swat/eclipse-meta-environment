package nl.cwi.sen.metastudio.adt.editordata;

abstract public class Focus_NotEmptyImpl
extends Focus
{
  protected void init(int hashCode, aterm.ATermList annos, aterm.AFun fun,	aterm.ATerm[] args) {
    super.init(hashCode, annos, fun, args);
  }
  protected void initHashCode(aterm.ATermList annos, aterm.AFun fun, aterm.ATerm[] i_args) {
  	super.initHashCode(annos, fun, i_args);
  }
  protected Focus_NotEmptyImpl(EditorDataFactory factory) {
    super(factory);
  }
  private static int index_path = 0;
  private static int index_sort = 1;
  private static int index_area = 2;
  private static int index_unparsed = 3;
  public shared.SharedObject duplicate() {
    Focus_NotEmpty clone = new Focus_NotEmpty(factory);
     clone.init(hashCode(), getAnnotations(), getAFun(), getArgumentArray());
    return clone;
  }

  public boolean equivalent(shared.SharedObject peer) {
    if (peer instanceof Focus_NotEmpty) {
      return super.equivalent(peer);
    }
    return false;
  }
  protected aterm.ATermAppl make(aterm.AFun fun, aterm.ATerm[] i_args, aterm.ATermList annos) {
    return getEditorDataFactory().makeFocus_NotEmpty(fun, i_args, annos);
  }
  public aterm.ATerm toTerm() {
    if (term == null) {
      term = getEditorDataFactory().toTerm(this);
    }
    return term;
  }

  public boolean isNotEmpty()
  {
    return true;
  }

  public boolean hasPath()
  {
    return true;
  }

  public boolean hasSort()
  {
    return true;
  }

  public boolean hasArea()
  {
    return true;
  }

  public boolean hasUnparsed()
  {
    return true;
  }

  public Path getPath()
  {
    return (Path) this.getArgument(index_path) ;
  }

  public Focus setPath(Path _path)
  {
    return (Focus) super.setArgument(_path, index_path);
  }

  public String getSort()
  {
   return ((aterm.ATermAppl) this.getArgument(index_sort)).getAFun().getName();
  }

  public Focus setSort(String _sort)
  {
    return (Focus) super.setArgument(getFactory().makeAppl(getFactory().makeAFun(_sort, 0, true)), index_sort);
  }

  public Area getArea()
  {
    return (Area) this.getArgument(index_area) ;
  }

  public Focus setArea(Area _area)
  {
    return (Focus) super.setArgument(_area, index_area);
  }

  public int getUnparsed()
  {
   return ((aterm.ATermInt) this.getArgument(index_unparsed)).getInt();
  }

  public Focus setUnparsed(int _unparsed)
  {
    return (Focus) super.setArgument(getFactory().makeInt(_unparsed), index_unparsed);
  }

  public aterm.ATermAppl setArgument(aterm.ATerm arg, int i) {
    switch(i) {
      case 0:
        if (! (arg instanceof Path)) { 
          throw new RuntimeException("Argument 0 of a Focus_NotEmpty should have type Path");
        }
        break;
      case 1:
        if (! (arg instanceof aterm.ATermAppl)) { 
          throw new RuntimeException("Argument 1 of a Focus_NotEmpty should have type str");
        }
        break;
      case 2:
        if (! (arg instanceof Area)) { 
          throw new RuntimeException("Argument 2 of a Focus_NotEmpty should have type Area");
        }
        break;
      case 3:
        if (! (arg instanceof aterm.ATermInt)) { 
          throw new RuntimeException("Argument 3 of a Focus_NotEmpty should have type int");
        }
        break;
      default: throw new RuntimeException("Focus_NotEmpty does not have an argument at " + i );
    }
    return super.setArgument(arg, i);
  }
}
