package nl.cwi.sen.metastudio.adt.editordata;

import aterm.pure.PureFactory;
public class EditorDataFactory
{
  private PureFactory factory;
  private aterm.AFun funFocus_Empty;
  private Focus protoFocus_Empty;
  private aterm.ATerm patternFocus_Empty;
  private aterm.AFun funFocus_NotEmpty;
  private Focus protoFocus_NotEmpty;
  private aterm.ATerm patternFocus_NotEmpty;
  private aterm.AFun funArea_Default;
  private Area protoArea_Default;
  private aterm.ATerm patternArea_Default;
  private aterm.AFun funFocusList_Empty;
  private FocusList protoFocusList_Empty;
  private aterm.ATerm patternFocusList_Empty;
  private aterm.AFun funFocusList_Multi;
  private FocusList protoFocusList_Multi;
  private aterm.ATerm patternFocusList_Multi;
  private aterm.AFun funSymbolList_Empty;
  private SymbolList protoSymbolList_Empty;
  private aterm.ATerm patternSymbolList_Empty;
  private aterm.AFun funSymbolList_Multi;
  private SymbolList protoSymbolList_Multi;
  private aterm.ATerm patternSymbolList_Multi;
  private aterm.AFun funPath_Root;
  private Path protoPath_Root;
  private aterm.ATerm patternPath_Root;
  private aterm.AFun funPath_LeftLayout;
  private Path protoPath_LeftLayout;
  private aterm.ATerm patternPath_LeftLayout;
  private aterm.AFun funPath_Tree;
  private Path protoPath_Tree;
  private aterm.ATerm patternPath_Tree;
  private aterm.AFun funPath_RightLayout;
  private Path protoPath_RightLayout;
  private aterm.ATerm patternPath_RightLayout;
  private aterm.AFun funSteps_Empty;
  private Steps protoSteps_Empty;
  private aterm.ATerm patternSteps_Empty;
  private aterm.AFun funSteps_Multi;
  private Steps protoSteps_Multi;
  private aterm.ATerm patternSteps_Multi;
  private aterm.AFun funMove_Left;
  private Move protoMove_Left;
  private aterm.ATerm patternMove_Left;
  private aterm.AFun funMove_Right;
  private Move protoMove_Right;
  private aterm.ATerm patternMove_Right;
  private aterm.AFun funMove_Up;
  private Move protoMove_Up;
  private aterm.ATerm patternMove_Up;
  private aterm.AFun funMove_Down;
  private Move protoMove_Down;
  private aterm.ATerm patternMove_Down;
  public EditorDataFactory(PureFactory factory)
  {
     this.factory = factory;
     initialize();
  }
  public PureFactory getPureFactory()
  {
    return factory;
  }
  private void initialize()
  {

    patternFocus_Empty = factory.parse("empty-focus");
    funFocus_Empty = factory.makeAFun("_Focus_empty", 0, false);
    protoFocus_Empty = new Focus_Empty(this);

    patternFocus_NotEmpty = factory.parse("focus(<term>,<str>,<term>,<int>)");
    funFocus_NotEmpty = factory.makeAFun("_Focus_not-empty", 4, false);
    protoFocus_NotEmpty = new Focus_NotEmpty(this);


    patternArea_Default = factory.parse("area(<int>,<int>)");
    funArea_Default = factory.makeAFun("_Area_Default", 2, false);
    protoArea_Default = new Area_Default(this);


    patternFocusList_Empty = factory.parse("[]");
    funFocusList_Empty = factory.makeAFun("_FocusList_empty", 0, false);
    protoFocusList_Empty = new FocusList_Empty(this);

    patternFocusList_Multi = factory.parse("[<term>,<list>]");
    funFocusList_Multi = factory.makeAFun("_FocusList_multi", 2, false);
    protoFocusList_Multi = new FocusList_Multi(this);


    patternSymbolList_Empty = factory.parse("[]");
    funSymbolList_Empty = factory.makeAFun("_SymbolList_empty", 0, false);
    protoSymbolList_Empty = new SymbolList_Empty(this);

    patternSymbolList_Multi = factory.parse("[<str>,<list>]");
    funSymbolList_Multi = factory.makeAFun("_SymbolList_multi", 2, false);
    protoSymbolList_Multi = new SymbolList_Multi(this);


    patternPath_Root = factory.parse("root");
    funPath_Root = factory.makeAFun("_Path_root", 0, false);
    protoPath_Root = new Path_Root(this);

    patternPath_LeftLayout = factory.parse("left-layout");
    funPath_LeftLayout = factory.makeAFun("_Path_left-layout", 0, false);
    protoPath_LeftLayout = new Path_LeftLayout(this);

    patternPath_Tree = factory.parse("term(<term>)");
    funPath_Tree = factory.makeAFun("_Path_tree", 1, false);
    protoPath_Tree = new Path_Tree(this);

    patternPath_RightLayout = factory.parse("right-layout");
    funPath_RightLayout = factory.makeAFun("_Path_right-layout", 0, false);
    protoPath_RightLayout = new Path_RightLayout(this);


    patternSteps_Empty = factory.parse("[]");
    funSteps_Empty = factory.makeAFun("_Steps_empty", 0, false);
    protoSteps_Empty = new Steps_Empty(this);

    patternSteps_Multi = factory.parse("[<int>,<list>]");
    funSteps_Multi = factory.makeAFun("_Steps_multi", 2, false);
    protoSteps_Multi = new Steps_Multi(this);


    patternMove_Left = factory.parse("\"Left\"");
    funMove_Left = factory.makeAFun("_Move_left", 0, false);
    protoMove_Left = new Move_Left(this);

    patternMove_Right = factory.parse("\"Right\"");
    funMove_Right = factory.makeAFun("_Move_right", 0, false);
    protoMove_Right = new Move_Right(this);

    patternMove_Up = factory.parse("\"Up\"");
    funMove_Up = factory.makeAFun("_Move_up", 0, false);
    protoMove_Up = new Move_Up(this);

    patternMove_Down = factory.parse("\"Down\"");
    funMove_Down = factory.makeAFun("_Move_down", 0, false);
    protoMove_Down = new Move_Down(this);

  }
  protected Focus_Empty makeFocus_Empty(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoFocus_Empty) {
      protoFocus_Empty.initHashCode(annos,fun,args);
      return (Focus_Empty) factory.build(protoFocus_Empty);
    }
  }

  public Focus_Empty makeFocus_Empty() {
    aterm.ATerm[] args = new aterm.ATerm[] {};
    return makeFocus_Empty(funFocus_Empty, args, factory.getEmpty());
  }

  public Focus Focus_EmptyFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternFocus_Empty);

    if (children != null) {
      Focus tmp = makeFocus_Empty();
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(Focus_EmptyImpl arg) {
    java.util.List args = new java.util.LinkedList();
    return factory.make(patternFocus_Empty, args);
  }

  protected Focus_NotEmpty makeFocus_NotEmpty(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoFocus_NotEmpty) {
      protoFocus_NotEmpty.initHashCode(annos,fun,args);
      return (Focus_NotEmpty) factory.build(protoFocus_NotEmpty);
    }
  }

  public Focus_NotEmpty makeFocus_NotEmpty(Path _path, String _sort, Area _area, int _unparsed) {
    aterm.ATerm[] args = new aterm.ATerm[] {_path, factory.makeAppl(factory.makeAFun(_sort, 0, true)), _area, factory.makeInt(_unparsed)};
    return makeFocus_NotEmpty(funFocus_NotEmpty, args, factory.getEmpty());
  }

  public Focus Focus_NotEmptyFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternFocus_NotEmpty);

    if (children != null) {
      Focus tmp = makeFocus_NotEmpty(PathFromTerm( (aterm.ATerm) children.get(0)), (String) children.get(1), AreaFromTerm( (aterm.ATerm) children.get(2)), ((Integer) children.get(3)).intValue());
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(Focus_NotEmptyImpl arg) {
    java.util.List args = new java.util.LinkedList();
    args.add((arg.getPath()).toTerm());    args.add(arg.getSort());    args.add((arg.getArea()).toTerm());    args.add(new Integer(arg.getUnparsed()));    return factory.make(patternFocus_NotEmpty, args);
  }

  protected Area_Default makeArea_Default(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoArea_Default) {
      protoArea_Default.initHashCode(annos,fun,args);
      return (Area_Default) factory.build(protoArea_Default);
    }
  }

  public Area_Default makeArea_Default(int _start, int _length) {
    aterm.ATerm[] args = new aterm.ATerm[] {factory.makeInt(_start), factory.makeInt(_length)};
    return makeArea_Default(funArea_Default, args, factory.getEmpty());
  }

  public Area Area_DefaultFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternArea_Default);

    if (children != null) {
      Area tmp = makeArea_Default(((Integer) children.get(0)).intValue(), ((Integer) children.get(1)).intValue());
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(Area_DefaultImpl arg) {
    java.util.List args = new java.util.LinkedList();
    args.add(new Integer(arg.getStart()));    args.add(new Integer(arg.getLength()));    return factory.make(patternArea_Default, args);
  }

  protected FocusList_Empty makeFocusList_Empty(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoFocusList_Empty) {
      protoFocusList_Empty.initHashCode(annos,fun,args);
      return (FocusList_Empty) factory.build(protoFocusList_Empty);
    }
  }

  public FocusList_Empty makeFocusList_Empty() {
    aterm.ATerm[] args = new aterm.ATerm[] {};
    return makeFocusList_Empty(funFocusList_Empty, args, factory.getEmpty());
  }

  public FocusList FocusList_EmptyFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternFocusList_Empty);

    if (children != null) {
      FocusList tmp = makeFocusList_Empty();
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(FocusList_EmptyImpl arg) {
    java.util.List args = new java.util.LinkedList();
    return factory.make(patternFocusList_Empty, args);
  }

  protected FocusList_Multi makeFocusList_Multi(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoFocusList_Multi) {
      protoFocusList_Multi.initHashCode(annos,fun,args);
      return (FocusList_Multi) factory.build(protoFocusList_Multi);
    }
  }

  public FocusList_Multi makeFocusList_Multi(Focus _head, FocusList _tail) {
    aterm.ATerm[] args = new aterm.ATerm[] {_head, _tail};
    return makeFocusList_Multi(funFocusList_Multi, args, factory.getEmpty());
  }

  public FocusList FocusList_MultiFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternFocusList_Multi);

    if (children != null) {
      FocusList tmp = makeFocusList_Multi(FocusFromTerm( (aterm.ATerm) children.get(0)), FocusListFromTerm( (aterm.ATerm) children.get(1)));
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(FocusList_MultiImpl arg) {
    java.util.List args = new java.util.LinkedList();
    args.add((arg.getHead()).toTerm());    args.add((arg.getTail()).toTerm());    return factory.make(patternFocusList_Multi, args);
  }

  protected SymbolList_Empty makeSymbolList_Empty(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoSymbolList_Empty) {
      protoSymbolList_Empty.initHashCode(annos,fun,args);
      return (SymbolList_Empty) factory.build(protoSymbolList_Empty);
    }
  }

  public SymbolList_Empty makeSymbolList_Empty() {
    aterm.ATerm[] args = new aterm.ATerm[] {};
    return makeSymbolList_Empty(funSymbolList_Empty, args, factory.getEmpty());
  }

  public SymbolList SymbolList_EmptyFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternSymbolList_Empty);

    if (children != null) {
      SymbolList tmp = makeSymbolList_Empty();
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(SymbolList_EmptyImpl arg) {
    java.util.List args = new java.util.LinkedList();
    return factory.make(patternSymbolList_Empty, args);
  }

  protected SymbolList_Multi makeSymbolList_Multi(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoSymbolList_Multi) {
      protoSymbolList_Multi.initHashCode(annos,fun,args);
      return (SymbolList_Multi) factory.build(protoSymbolList_Multi);
    }
  }

  public SymbolList_Multi makeSymbolList_Multi(String _head, SymbolList _tail) {
    aterm.ATerm[] args = new aterm.ATerm[] {factory.makeAppl(factory.makeAFun(_head, 0, true)), _tail};
    return makeSymbolList_Multi(funSymbolList_Multi, args, factory.getEmpty());
  }

  public SymbolList SymbolList_MultiFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternSymbolList_Multi);

    if (children != null) {
      SymbolList tmp = makeSymbolList_Multi((String) children.get(0), SymbolListFromTerm( (aterm.ATerm) children.get(1)));
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(SymbolList_MultiImpl arg) {
    java.util.List args = new java.util.LinkedList();
    args.add(arg.getHead());    args.add((arg.getTail()).toTerm());    return factory.make(patternSymbolList_Multi, args);
  }

  protected Path_Root makePath_Root(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoPath_Root) {
      protoPath_Root.initHashCode(annos,fun,args);
      return (Path_Root) factory.build(protoPath_Root);
    }
  }

  public Path_Root makePath_Root() {
    aterm.ATerm[] args = new aterm.ATerm[] {};
    return makePath_Root(funPath_Root, args, factory.getEmpty());
  }

  public Path Path_RootFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternPath_Root);

    if (children != null) {
      Path tmp = makePath_Root();
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(Path_RootImpl arg) {
    java.util.List args = new java.util.LinkedList();
    return factory.make(patternPath_Root, args);
  }

  protected Path_LeftLayout makePath_LeftLayout(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoPath_LeftLayout) {
      protoPath_LeftLayout.initHashCode(annos,fun,args);
      return (Path_LeftLayout) factory.build(protoPath_LeftLayout);
    }
  }

  public Path_LeftLayout makePath_LeftLayout() {
    aterm.ATerm[] args = new aterm.ATerm[] {};
    return makePath_LeftLayout(funPath_LeftLayout, args, factory.getEmpty());
  }

  public Path Path_LeftLayoutFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternPath_LeftLayout);

    if (children != null) {
      Path tmp = makePath_LeftLayout();
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(Path_LeftLayoutImpl arg) {
    java.util.List args = new java.util.LinkedList();
    return factory.make(patternPath_LeftLayout, args);
  }

  protected Path_Tree makePath_Tree(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoPath_Tree) {
      protoPath_Tree.initHashCode(annos,fun,args);
      return (Path_Tree) factory.build(protoPath_Tree);
    }
  }

  public Path_Tree makePath_Tree(Steps _steps) {
    aterm.ATerm[] args = new aterm.ATerm[] {_steps};
    return makePath_Tree(funPath_Tree, args, factory.getEmpty());
  }

  public Path Path_TreeFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternPath_Tree);

    if (children != null) {
      Path tmp = makePath_Tree(StepsFromTerm( (aterm.ATerm) children.get(0)));
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(Path_TreeImpl arg) {
    java.util.List args = new java.util.LinkedList();
    args.add((arg.getSteps()).toTerm());    return factory.make(patternPath_Tree, args);
  }

  protected Path_RightLayout makePath_RightLayout(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoPath_RightLayout) {
      protoPath_RightLayout.initHashCode(annos,fun,args);
      return (Path_RightLayout) factory.build(protoPath_RightLayout);
    }
  }

  public Path_RightLayout makePath_RightLayout() {
    aterm.ATerm[] args = new aterm.ATerm[] {};
    return makePath_RightLayout(funPath_RightLayout, args, factory.getEmpty());
  }

  public Path Path_RightLayoutFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternPath_RightLayout);

    if (children != null) {
      Path tmp = makePath_RightLayout();
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(Path_RightLayoutImpl arg) {
    java.util.List args = new java.util.LinkedList();
    return factory.make(patternPath_RightLayout, args);
  }

  protected Steps_Empty makeSteps_Empty(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoSteps_Empty) {
      protoSteps_Empty.initHashCode(annos,fun,args);
      return (Steps_Empty) factory.build(protoSteps_Empty);
    }
  }

  public Steps_Empty makeSteps_Empty() {
    aterm.ATerm[] args = new aterm.ATerm[] {};
    return makeSteps_Empty(funSteps_Empty, args, factory.getEmpty());
  }

  public Steps Steps_EmptyFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternSteps_Empty);

    if (children != null) {
      Steps tmp = makeSteps_Empty();
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(Steps_EmptyImpl arg) {
    java.util.List args = new java.util.LinkedList();
    return factory.make(patternSteps_Empty, args);
  }

  protected Steps_Multi makeSteps_Multi(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoSteps_Multi) {
      protoSteps_Multi.initHashCode(annos,fun,args);
      return (Steps_Multi) factory.build(protoSteps_Multi);
    }
  }

  public Steps_Multi makeSteps_Multi(int _head, Steps _tail) {
    aterm.ATerm[] args = new aterm.ATerm[] {factory.makeInt(_head), _tail};
    return makeSteps_Multi(funSteps_Multi, args, factory.getEmpty());
  }

  public Steps Steps_MultiFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternSteps_Multi);

    if (children != null) {
      Steps tmp = makeSteps_Multi(((Integer) children.get(0)).intValue(), StepsFromTerm( (aterm.ATerm) children.get(1)));
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(Steps_MultiImpl arg) {
    java.util.List args = new java.util.LinkedList();
    args.add(new Integer(arg.getHead()));    args.add((arg.getTail()).toTerm());    return factory.make(patternSteps_Multi, args);
  }

  protected Move_Left makeMove_Left(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoMove_Left) {
      protoMove_Left.initHashCode(annos,fun,args);
      return (Move_Left) factory.build(protoMove_Left);
    }
  }

  public Move_Left makeMove_Left() {
    aterm.ATerm[] args = new aterm.ATerm[] {};
    return makeMove_Left(funMove_Left, args, factory.getEmpty());
  }

  public Move Move_LeftFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternMove_Left);

    if (children != null) {
      Move tmp = makeMove_Left();
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(Move_LeftImpl arg) {
    java.util.List args = new java.util.LinkedList();
    return factory.make(patternMove_Left, args);
  }

  protected Move_Right makeMove_Right(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoMove_Right) {
      protoMove_Right.initHashCode(annos,fun,args);
      return (Move_Right) factory.build(protoMove_Right);
    }
  }

  public Move_Right makeMove_Right() {
    aterm.ATerm[] args = new aterm.ATerm[] {};
    return makeMove_Right(funMove_Right, args, factory.getEmpty());
  }

  public Move Move_RightFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternMove_Right);

    if (children != null) {
      Move tmp = makeMove_Right();
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(Move_RightImpl arg) {
    java.util.List args = new java.util.LinkedList();
    return factory.make(patternMove_Right, args);
  }

  protected Move_Up makeMove_Up(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoMove_Up) {
      protoMove_Up.initHashCode(annos,fun,args);
      return (Move_Up) factory.build(protoMove_Up);
    }
  }

  public Move_Up makeMove_Up() {
    aterm.ATerm[] args = new aterm.ATerm[] {};
    return makeMove_Up(funMove_Up, args, factory.getEmpty());
  }

  public Move Move_UpFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternMove_Up);

    if (children != null) {
      Move tmp = makeMove_Up();
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(Move_UpImpl arg) {
    java.util.List args = new java.util.LinkedList();
    return factory.make(patternMove_Up, args);
  }

  protected Move_Down makeMove_Down(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoMove_Down) {
      protoMove_Down.initHashCode(annos,fun,args);
      return (Move_Down) factory.build(protoMove_Down);
    }
  }

  public Move_Down makeMove_Down() {
    aterm.ATerm[] args = new aterm.ATerm[] {};
    return makeMove_Down(funMove_Down, args, factory.getEmpty());
  }

  public Move Move_DownFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternMove_Down);

    if (children != null) {
      Move tmp = makeMove_Down();
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(Move_DownImpl arg) {
    java.util.List args = new java.util.LinkedList();
    return factory.make(patternMove_Down, args);
  }

  public Focus FocusFromTerm(aterm.ATerm trm)
  {
    Focus tmp;
    tmp = Focus_EmptyFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }

    tmp = Focus_NotEmptyFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }


    throw new RuntimeException("This is not a Focus: " + trm);
  }
  public Area AreaFromTerm(aterm.ATerm trm)
  {
    Area tmp;
    tmp = Area_DefaultFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }


    throw new RuntimeException("This is not a Area: " + trm);
  }
  public FocusList FocusListFromTerm(aterm.ATerm trm)
  {
    FocusList tmp;
    tmp = FocusList_EmptyFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }

    tmp = FocusList_MultiFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }


    throw new RuntimeException("This is not a FocusList: " + trm);
  }
  public SymbolList SymbolListFromTerm(aterm.ATerm trm)
  {
    SymbolList tmp;
    tmp = SymbolList_EmptyFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }

    tmp = SymbolList_MultiFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }


    throw new RuntimeException("This is not a SymbolList: " + trm);
  }
  public Path PathFromTerm(aterm.ATerm trm)
  {
    Path tmp;
    tmp = Path_RootFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }

    tmp = Path_LeftLayoutFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }

    tmp = Path_TreeFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }

    tmp = Path_RightLayoutFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }


    throw new RuntimeException("This is not a Path: " + trm);
  }
  public Steps StepsFromTerm(aterm.ATerm trm)
  {
    Steps tmp;
    tmp = Steps_EmptyFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }

    tmp = Steps_MultiFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }


    throw new RuntimeException("This is not a Steps: " + trm);
  }
  public Move MoveFromTerm(aterm.ATerm trm)
  {
    Move tmp;
    tmp = Move_LeftFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }

    tmp = Move_RightFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }

    tmp = Move_UpFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }

    tmp = Move_DownFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }


    throw new RuntimeException("This is not a Move: " + trm);
  }
  public Focus FocusFromString(String str)
  {
    aterm.ATerm trm = factory.parse(str);
    return FocusFromTerm(trm);
  }
  public Focus FocusFromFile(java.io.InputStream stream) throws java.io.IOException {
    return FocusFromTerm(factory.readFromFile(stream));
  }
  public Area AreaFromString(String str)
  {
    aterm.ATerm trm = factory.parse(str);
    return AreaFromTerm(trm);
  }
  public Area AreaFromFile(java.io.InputStream stream) throws java.io.IOException {
    return AreaFromTerm(factory.readFromFile(stream));
  }
  public FocusList FocusListFromString(String str)
  {
    aterm.ATerm trm = factory.parse(str);
    return FocusListFromTerm(trm);
  }
  public FocusList FocusListFromFile(java.io.InputStream stream) throws java.io.IOException {
    return FocusListFromTerm(factory.readFromFile(stream));
  }
  public SymbolList SymbolListFromString(String str)
  {
    aterm.ATerm trm = factory.parse(str);
    return SymbolListFromTerm(trm);
  }
  public SymbolList SymbolListFromFile(java.io.InputStream stream) throws java.io.IOException {
    return SymbolListFromTerm(factory.readFromFile(stream));
  }
  public Path PathFromString(String str)
  {
    aterm.ATerm trm = factory.parse(str);
    return PathFromTerm(trm);
  }
  public Path PathFromFile(java.io.InputStream stream) throws java.io.IOException {
    return PathFromTerm(factory.readFromFile(stream));
  }
  public Steps StepsFromString(String str)
  {
    aterm.ATerm trm = factory.parse(str);
    return StepsFromTerm(trm);
  }
  public Steps StepsFromFile(java.io.InputStream stream) throws java.io.IOException {
    return StepsFromTerm(factory.readFromFile(stream));
  }
  public Move MoveFromString(String str)
  {
    aterm.ATerm trm = factory.parse(str);
    return MoveFromTerm(trm);
  }
  public Move MoveFromFile(java.io.InputStream stream) throws java.io.IOException {
    return MoveFromTerm(factory.readFromFile(stream));
  }
}
