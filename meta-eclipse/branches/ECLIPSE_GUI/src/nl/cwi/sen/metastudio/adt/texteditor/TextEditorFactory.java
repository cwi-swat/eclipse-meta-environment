package nl.cwi.sen.metastudio.adt.texteditor;

import aterm.pure.PureFactory;
public class TextEditorFactory
{
  private PureFactory factory;
  private aterm.AFun funAction_ToFront;
  private Action protoAction_ToFront;
  private aterm.ATerm patternAction_ToFront;
  private aterm.AFun funAction_RereadContents;
  private Action protoAction_RereadContents;
  private aterm.ATerm patternAction_RereadContents;
  private aterm.AFun funAction_DisplayMessage;
  private Action protoAction_DisplayMessage;
  private aterm.ATerm patternAction_DisplayMessage;
  private aterm.AFun funAction_SetCursorAtLocation;
  private Action protoAction_SetCursorAtLocation;
  private aterm.ATerm patternAction_SetCursorAtLocation;
  private aterm.AFun funAction_SetCursorAtFocus;
  private Action protoAction_SetCursorAtFocus;
  private aterm.ATerm patternAction_SetCursorAtFocus;
  private aterm.AFun funAction_ClearFocus;
  private Action protoAction_ClearFocus;
  private aterm.ATerm patternAction_ClearFocus;
  private aterm.AFun funAction_SetFocus;
  private Action protoAction_SetFocus;
  private aterm.ATerm patternAction_SetFocus;
  private aterm.AFun funAction_GetContents;
  private Action protoAction_GetContents;
  private aterm.ATerm patternAction_GetContents;
  private aterm.AFun funAction_SetActions;
  private Action protoAction_SetActions;
  private aterm.ATerm patternAction_SetActions;
  private aterm.AFun funActionList_Empty;
  private ActionList protoActionList_Empty;
  private aterm.ATerm patternActionList_Empty;
  private aterm.AFun funActionList_Multi;
  private ActionList protoActionList_Multi;
  private aterm.ATerm patternActionList_Multi;
  private aterm.AFun funMenu_Default;
  private Menu protoMenu_Default;
  private aterm.ATerm patternMenu_Default;
  private aterm.AFun funEvent_Menu;
  private Event protoEvent_Menu;
  private aterm.ATerm patternEvent_Menu;
  private aterm.AFun funEvent_Mouse;
  private Event protoEvent_Mouse;
  private aterm.ATerm patternEvent_Mouse;
  private aterm.AFun funEvent_Contents;
  private Event protoEvent_Contents;
  private aterm.ATerm patternEvent_Contents;
  private aterm.AFun funEvent_Modified;
  private Event protoEvent_Modified;
  private aterm.ATerm patternEvent_Modified;
  private aterm.AFun funProcess_Default;
  private Process protoProcess_Default;
  private aterm.ATerm patternProcess_Default;
  private aterm.AFun funPipe_Default;
  private Pipe protoPipe_Default;
  private aterm.ATerm patternPipe_Default;
  public TextEditorFactory(PureFactory factory)
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

    patternAction_ToFront = factory.parse("action(to-front)");
    funAction_ToFront = factory.makeAFun("_Action_to-front", 0, false);
    protoAction_ToFront = new Action_ToFront(this);

    patternAction_RereadContents = factory.parse("action(reread-contents)");
    funAction_RereadContents = factory.makeAFun("_Action_reread-contents", 0, false);
    protoAction_RereadContents = new Action_RereadContents(this);

    patternAction_DisplayMessage = factory.parse("action(display-message(<str>))");
    funAction_DisplayMessage = factory.makeAFun("_Action_display-message", 1, false);
    protoAction_DisplayMessage = new Action_DisplayMessage(this);

    patternAction_SetCursorAtLocation = factory.parse("action(set-cursor-at-location(<int>))");
    funAction_SetCursorAtLocation = factory.makeAFun("_Action_set-cursor-at-location", 1, false);
    protoAction_SetCursorAtLocation = new Action_SetCursorAtLocation(this);

    patternAction_SetCursorAtFocus = factory.parse("action(set-cursor-at-focus(<term>))");
    funAction_SetCursorAtFocus = factory.makeAFun("_Action_set-cursor-at-focus", 1, false);
    protoAction_SetCursorAtFocus = new Action_SetCursorAtFocus(this);

    patternAction_ClearFocus = factory.parse("action(clear-focus)");
    funAction_ClearFocus = factory.makeAFun("_Action_clear-focus", 0, false);
    protoAction_ClearFocus = new Action_ClearFocus(this);

    patternAction_SetFocus = factory.parse("action(set-focus(<term>))");
    funAction_SetFocus = factory.makeAFun("_Action_set-focus", 1, false);
    protoAction_SetFocus = new Action_SetFocus(this);

    patternAction_GetContents = factory.parse("action(get-contents(<term>))");
    funAction_GetContents = factory.makeAFun("_Action_get-contents", 1, false);
    protoAction_GetContents = new Action_GetContents(this);

    patternAction_SetActions = factory.parse("action(set-actions(<term>))");
    funAction_SetActions = factory.makeAFun("_Action_set-actions", 1, false);
    protoAction_SetActions = new Action_SetActions(this);


    patternActionList_Empty = factory.parse("[]");
    funActionList_Empty = factory.makeAFun("_ActionList_empty", 0, false);
    protoActionList_Empty = new ActionList_Empty(this);

    patternActionList_Multi = factory.parse("[<term>,<list>]");
    funActionList_Multi = factory.makeAFun("_ActionList_multi", 2, false);
    protoActionList_Multi = new ActionList_Multi(this);


    patternMenu_Default = factory.parse("menu([<str>,<str>])");
    funMenu_Default = factory.makeAFun("_Menu_default", 2, false);
    protoMenu_Default = new Menu_Default(this);


    patternEvent_Menu = factory.parse("menu-event(<term>)");
    funEvent_Menu = factory.makeAFun("_Event_menu", 1, false);
    protoEvent_Menu = new Event_Menu(this);

    patternEvent_Mouse = factory.parse("mouse-event(<int>)");
    funEvent_Mouse = factory.makeAFun("_Event_mouse", 1, false);
    protoEvent_Mouse = new Event_Mouse(this);

    patternEvent_Contents = factory.parse("contents(<str>)");
    funEvent_Contents = factory.makeAFun("_Event_contents", 1, false);
    protoEvent_Contents = new Event_Contents(this);

    patternEvent_Modified = factory.parse("modified");
    funEvent_Modified = factory.makeAFun("_Event_modified", 0, false);
    protoEvent_Modified = new Event_Modified(this);


    patternProcess_Default = factory.parse("process(<term>,<term>)");
    funProcess_Default = factory.makeAFun("_Process_default", 2, false);
    protoProcess_Default = new Process_Default(this);


    patternPipe_Default = factory.parse("pipe(<int>,<int>)");
    funPipe_Default = factory.makeAFun("_Pipe_default", 2, false);
    protoPipe_Default = new Pipe_Default(this);

  }
  protected Action_ToFront makeAction_ToFront(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoAction_ToFront) {
      protoAction_ToFront.initHashCode(annos,fun,args);
      return (Action_ToFront) factory.build(protoAction_ToFront);
    }
  }

  public Action_ToFront makeAction_ToFront() {
    aterm.ATerm[] args = new aterm.ATerm[] {};
    return makeAction_ToFront(funAction_ToFront, args, factory.getEmpty());
  }

  public Action Action_ToFrontFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternAction_ToFront);

    if (children != null) {
      Action tmp = makeAction_ToFront();
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(Action_ToFrontImpl arg) {
    java.util.List args = new java.util.LinkedList();
    return factory.make(patternAction_ToFront, args);
  }

  protected Action_RereadContents makeAction_RereadContents(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoAction_RereadContents) {
      protoAction_RereadContents.initHashCode(annos,fun,args);
      return (Action_RereadContents) factory.build(protoAction_RereadContents);
    }
  }

  public Action_RereadContents makeAction_RereadContents() {
    aterm.ATerm[] args = new aterm.ATerm[] {};
    return makeAction_RereadContents(funAction_RereadContents, args, factory.getEmpty());
  }

  public Action Action_RereadContentsFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternAction_RereadContents);

    if (children != null) {
      Action tmp = makeAction_RereadContents();
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(Action_RereadContentsImpl arg) {
    java.util.List args = new java.util.LinkedList();
    return factory.make(patternAction_RereadContents, args);
  }

  protected Action_DisplayMessage makeAction_DisplayMessage(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoAction_DisplayMessage) {
      protoAction_DisplayMessage.initHashCode(annos,fun,args);
      return (Action_DisplayMessage) factory.build(protoAction_DisplayMessage);
    }
  }

  public Action_DisplayMessage makeAction_DisplayMessage(String _message) {
    aterm.ATerm[] args = new aterm.ATerm[] {factory.makeAppl(factory.makeAFun(_message, 0, true))};
    return makeAction_DisplayMessage(funAction_DisplayMessage, args, factory.getEmpty());
  }

  public Action Action_DisplayMessageFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternAction_DisplayMessage);

    if (children != null) {
      Action tmp = makeAction_DisplayMessage((String) children.get(0));
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(Action_DisplayMessageImpl arg) {
    java.util.List args = new java.util.LinkedList();
    args.add(arg.getMessage());    return factory.make(patternAction_DisplayMessage, args);
  }

  protected Action_SetCursorAtLocation makeAction_SetCursorAtLocation(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoAction_SetCursorAtLocation) {
      protoAction_SetCursorAtLocation.initHashCode(annos,fun,args);
      return (Action_SetCursorAtLocation) factory.build(protoAction_SetCursorAtLocation);
    }
  }

  public Action_SetCursorAtLocation makeAction_SetCursorAtLocation(int _location) {
    aterm.ATerm[] args = new aterm.ATerm[] {factory.makeInt(_location)};
    return makeAction_SetCursorAtLocation(funAction_SetCursorAtLocation, args, factory.getEmpty());
  }

  public Action Action_SetCursorAtLocationFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternAction_SetCursorAtLocation);

    if (children != null) {
      Action tmp = makeAction_SetCursorAtLocation(((Integer) children.get(0)).intValue());
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(Action_SetCursorAtLocationImpl arg) {
    java.util.List args = new java.util.LinkedList();
    args.add(new Integer(arg.getLocation()));    return factory.make(patternAction_SetCursorAtLocation, args);
  }

  protected Action_SetCursorAtFocus makeAction_SetCursorAtFocus(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoAction_SetCursorAtFocus) {
      protoAction_SetCursorAtFocus.initHashCode(annos,fun,args);
      return (Action_SetCursorAtFocus) factory.build(protoAction_SetCursorAtFocus);
    }
  }

  public Action_SetCursorAtFocus makeAction_SetCursorAtFocus(aterm.ATerm _focus) {
    aterm.ATerm[] args = new aterm.ATerm[] {_focus};
    return makeAction_SetCursorAtFocus(funAction_SetCursorAtFocus, args, factory.getEmpty());
  }

  public Action Action_SetCursorAtFocusFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternAction_SetCursorAtFocus);

    if (children != null) {
      Action tmp = makeAction_SetCursorAtFocus((aterm.ATerm) children.get(0));
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(Action_SetCursorAtFocusImpl arg) {
    java.util.List args = new java.util.LinkedList();
    args.add((aterm.ATerm)arg.getFocus());    return factory.make(patternAction_SetCursorAtFocus, args);
  }

  protected Action_ClearFocus makeAction_ClearFocus(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoAction_ClearFocus) {
      protoAction_ClearFocus.initHashCode(annos,fun,args);
      return (Action_ClearFocus) factory.build(protoAction_ClearFocus);
    }
  }

  public Action_ClearFocus makeAction_ClearFocus() {
    aterm.ATerm[] args = new aterm.ATerm[] {};
    return makeAction_ClearFocus(funAction_ClearFocus, args, factory.getEmpty());
  }

  public Action Action_ClearFocusFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternAction_ClearFocus);

    if (children != null) {
      Action tmp = makeAction_ClearFocus();
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(Action_ClearFocusImpl arg) {
    java.util.List args = new java.util.LinkedList();
    return factory.make(patternAction_ClearFocus, args);
  }

  protected Action_SetFocus makeAction_SetFocus(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoAction_SetFocus) {
      protoAction_SetFocus.initHashCode(annos,fun,args);
      return (Action_SetFocus) factory.build(protoAction_SetFocus);
    }
  }

  public Action_SetFocus makeAction_SetFocus(aterm.ATerm _focus) {
    aterm.ATerm[] args = new aterm.ATerm[] {_focus};
    return makeAction_SetFocus(funAction_SetFocus, args, factory.getEmpty());
  }

  public Action Action_SetFocusFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternAction_SetFocus);

    if (children != null) {
      Action tmp = makeAction_SetFocus((aterm.ATerm) children.get(0));
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(Action_SetFocusImpl arg) {
    java.util.List args = new java.util.LinkedList();
    args.add((aterm.ATerm)arg.getFocus());    return factory.make(patternAction_SetFocus, args);
  }

  protected Action_GetContents makeAction_GetContents(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoAction_GetContents) {
      protoAction_GetContents.initHashCode(annos,fun,args);
      return (Action_GetContents) factory.build(protoAction_GetContents);
    }
  }

  public Action_GetContents makeAction_GetContents(aterm.ATerm _focus) {
    aterm.ATerm[] args = new aterm.ATerm[] {_focus};
    return makeAction_GetContents(funAction_GetContents, args, factory.getEmpty());
  }

  public Action Action_GetContentsFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternAction_GetContents);

    if (children != null) {
      Action tmp = makeAction_GetContents((aterm.ATerm) children.get(0));
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(Action_GetContentsImpl arg) {
    java.util.List args = new java.util.LinkedList();
    args.add((aterm.ATerm)arg.getFocus());    return factory.make(patternAction_GetContents, args);
  }

  protected Action_SetActions makeAction_SetActions(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoAction_SetActions) {
      protoAction_SetActions.initHashCode(annos,fun,args);
      return (Action_SetActions) factory.build(protoAction_SetActions);
    }
  }

  public Action_SetActions makeAction_SetActions(ActionList _actions) {
    aterm.ATerm[] args = new aterm.ATerm[] {_actions};
    return makeAction_SetActions(funAction_SetActions, args, factory.getEmpty());
  }

  public Action Action_SetActionsFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternAction_SetActions);

    if (children != null) {
      Action tmp = makeAction_SetActions(ActionListFromTerm( (aterm.ATerm) children.get(0)));
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(Action_SetActionsImpl arg) {
    java.util.List args = new java.util.LinkedList();
    args.add((arg.getActions()).toTerm());    return factory.make(patternAction_SetActions, args);
  }

  protected ActionList_Empty makeActionList_Empty(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoActionList_Empty) {
      protoActionList_Empty.initHashCode(annos,fun,args);
      return (ActionList_Empty) factory.build(protoActionList_Empty);
    }
  }

  public ActionList_Empty makeActionList_Empty() {
    aterm.ATerm[] args = new aterm.ATerm[] {};
    return makeActionList_Empty(funActionList_Empty, args, factory.getEmpty());
  }

  public ActionList ActionList_EmptyFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternActionList_Empty);

    if (children != null) {
      ActionList tmp = makeActionList_Empty();
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(ActionList_EmptyImpl arg) {
    java.util.List args = new java.util.LinkedList();
    return factory.make(patternActionList_Empty, args);
  }

  protected ActionList_Multi makeActionList_Multi(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoActionList_Multi) {
      protoActionList_Multi.initHashCode(annos,fun,args);
      return (ActionList_Multi) factory.build(protoActionList_Multi);
    }
  }

  public ActionList_Multi makeActionList_Multi(Menu _head, ActionList _tail) {
    aterm.ATerm[] args = new aterm.ATerm[] {_head, _tail};
    return makeActionList_Multi(funActionList_Multi, args, factory.getEmpty());
  }

  public ActionList ActionList_MultiFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternActionList_Multi);

    if (children != null) {
      ActionList tmp = makeActionList_Multi(MenuFromTerm( (aterm.ATerm) children.get(0)), ActionListFromTerm( (aterm.ATerm) children.get(1)));
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(ActionList_MultiImpl arg) {
    java.util.List args = new java.util.LinkedList();
    args.add((arg.getHead()).toTerm());    args.add((arg.getTail()).toTerm());    return factory.make(patternActionList_Multi, args);
  }

  protected Menu_Default makeMenu_Default(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoMenu_Default) {
      protoMenu_Default.initHashCode(annos,fun,args);
      return (Menu_Default) factory.build(protoMenu_Default);
    }
  }

  public Menu_Default makeMenu_Default(String _main, String _sub) {
    aterm.ATerm[] args = new aterm.ATerm[] {factory.makeAppl(factory.makeAFun(_main, 0, true)), factory.makeAppl(factory.makeAFun(_sub, 0, true))};
    return makeMenu_Default(funMenu_Default, args, factory.getEmpty());
  }

  public Menu Menu_DefaultFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternMenu_Default);

    if (children != null) {
      Menu tmp = makeMenu_Default((String) children.get(0), (String) children.get(1));
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(Menu_DefaultImpl arg) {
    java.util.List args = new java.util.LinkedList();
    args.add(arg.getMain());    args.add(arg.getSub());    return factory.make(patternMenu_Default, args);
  }

  protected Event_Menu makeEvent_Menu(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoEvent_Menu) {
      protoEvent_Menu.initHashCode(annos,fun,args);
      return (Event_Menu) factory.build(protoEvent_Menu);
    }
  }

  public Event_Menu makeEvent_Menu(Menu _menu) {
    aterm.ATerm[] args = new aterm.ATerm[] {_menu};
    return makeEvent_Menu(funEvent_Menu, args, factory.getEmpty());
  }

  public Event Event_MenuFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternEvent_Menu);

    if (children != null) {
      Event tmp = makeEvent_Menu(MenuFromTerm( (aterm.ATerm) children.get(0)));
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(Event_MenuImpl arg) {
    java.util.List args = new java.util.LinkedList();
    args.add((arg.getMenu()).toTerm());    return factory.make(patternEvent_Menu, args);
  }

  protected Event_Mouse makeEvent_Mouse(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoEvent_Mouse) {
      protoEvent_Mouse.initHashCode(annos,fun,args);
      return (Event_Mouse) factory.build(protoEvent_Mouse);
    }
  }

  public Event_Mouse makeEvent_Mouse(int _location) {
    aterm.ATerm[] args = new aterm.ATerm[] {factory.makeInt(_location)};
    return makeEvent_Mouse(funEvent_Mouse, args, factory.getEmpty());
  }

  public Event Event_MouseFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternEvent_Mouse);

    if (children != null) {
      Event tmp = makeEvent_Mouse(((Integer) children.get(0)).intValue());
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(Event_MouseImpl arg) {
    java.util.List args = new java.util.LinkedList();
    args.add(new Integer(arg.getLocation()));    return factory.make(patternEvent_Mouse, args);
  }

  protected Event_Contents makeEvent_Contents(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoEvent_Contents) {
      protoEvent_Contents.initHashCode(annos,fun,args);
      return (Event_Contents) factory.build(protoEvent_Contents);
    }
  }

  public Event_Contents makeEvent_Contents(String _text) {
    aterm.ATerm[] args = new aterm.ATerm[] {factory.makeAppl(factory.makeAFun(_text, 0, true))};
    return makeEvent_Contents(funEvent_Contents, args, factory.getEmpty());
  }

  public Event Event_ContentsFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternEvent_Contents);

    if (children != null) {
      Event tmp = makeEvent_Contents((String) children.get(0));
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(Event_ContentsImpl arg) {
    java.util.List args = new java.util.LinkedList();
    args.add(arg.getText());    return factory.make(patternEvent_Contents, args);
  }

  protected Event_Modified makeEvent_Modified(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoEvent_Modified) {
      protoEvent_Modified.initHashCode(annos,fun,args);
      return (Event_Modified) factory.build(protoEvent_Modified);
    }
  }

  public Event_Modified makeEvent_Modified() {
    aterm.ATerm[] args = new aterm.ATerm[] {};
    return makeEvent_Modified(funEvent_Modified, args, factory.getEmpty());
  }

  public Event Event_ModifiedFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternEvent_Modified);

    if (children != null) {
      Event tmp = makeEvent_Modified();
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(Event_ModifiedImpl arg) {
    java.util.List args = new java.util.LinkedList();
    return factory.make(patternEvent_Modified, args);
  }

  protected Process_Default makeProcess_Default(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoProcess_Default) {
      protoProcess_Default.initHashCode(annos,fun,args);
      return (Process_Default) factory.build(protoProcess_Default);
    }
  }

  public Process_Default makeProcess_Default(Pipe _toChild, Pipe _fromChild) {
    aterm.ATerm[] args = new aterm.ATerm[] {_toChild, _fromChild};
    return makeProcess_Default(funProcess_Default, args, factory.getEmpty());
  }

  public Process Process_DefaultFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternProcess_Default);

    if (children != null) {
      Process tmp = makeProcess_Default(PipeFromTerm( (aterm.ATerm) children.get(0)), PipeFromTerm( (aterm.ATerm) children.get(1)));
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(Process_DefaultImpl arg) {
    java.util.List args = new java.util.LinkedList();
    args.add((arg.getToChild()).toTerm());    args.add((arg.getFromChild()).toTerm());    return factory.make(patternProcess_Default, args);
  }

  protected Pipe_Default makePipe_Default(aterm.AFun fun, aterm.ATerm[] args, aterm.ATermList annos) {
    synchronized (protoPipe_Default) {
      protoPipe_Default.initHashCode(annos,fun,args);
      return (Pipe_Default) factory.build(protoPipe_Default);
    }
  }

  public Pipe_Default makePipe_Default(int _read, int _write) {
    aterm.ATerm[] args = new aterm.ATerm[] {factory.makeInt(_read), factory.makeInt(_write)};
    return makePipe_Default(funPipe_Default, args, factory.getEmpty());
  }

  public Pipe Pipe_DefaultFromTerm(aterm.ATerm trm)
  {
    java.util.List children = trm.match(patternPipe_Default);

    if (children != null) {
      Pipe tmp = makePipe_Default(((Integer) children.get(0)).intValue(), ((Integer) children.get(1)).intValue());
      return tmp;
    }
    else {
      return null;
    }
  }
  protected aterm.ATerm toTerm(Pipe_DefaultImpl arg) {
    java.util.List args = new java.util.LinkedList();
    args.add(new Integer(arg.getRead()));    args.add(new Integer(arg.getWrite()));    return factory.make(patternPipe_Default, args);
  }

  public Action ActionFromTerm(aterm.ATerm trm)
  {
    Action tmp;
    tmp = Action_ToFrontFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }

    tmp = Action_RereadContentsFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }

    tmp = Action_DisplayMessageFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }

    tmp = Action_SetCursorAtLocationFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }

    tmp = Action_SetCursorAtFocusFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }

    tmp = Action_ClearFocusFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }

    tmp = Action_SetFocusFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }

    tmp = Action_GetContentsFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }

    tmp = Action_SetActionsFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }


    throw new RuntimeException("This is not a Action: " + trm);
  }
  public ActionList ActionListFromTerm(aterm.ATerm trm)
  {
    ActionList tmp;
    tmp = ActionList_EmptyFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }

    tmp = ActionList_MultiFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }


    throw new RuntimeException("This is not a ActionList: " + trm);
  }
  public Menu MenuFromTerm(aterm.ATerm trm)
  {
    Menu tmp;
    tmp = Menu_DefaultFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }


    throw new RuntimeException("This is not a Menu: " + trm);
  }
  public Event EventFromTerm(aterm.ATerm trm)
  {
    Event tmp;
    tmp = Event_MenuFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }

    tmp = Event_MouseFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }

    tmp = Event_ContentsFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }

    tmp = Event_ModifiedFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }


    throw new RuntimeException("This is not a Event: " + trm);
  }
  public Process ProcessFromTerm(aterm.ATerm trm)
  {
    Process tmp;
    tmp = Process_DefaultFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }


    throw new RuntimeException("This is not a Process: " + trm);
  }
  public Pipe PipeFromTerm(aterm.ATerm trm)
  {
    Pipe tmp;
    tmp = Pipe_DefaultFromTerm(trm);
    if (tmp != null) {
      return tmp;
    }


    throw new RuntimeException("This is not a Pipe: " + trm);
  }
  public Action ActionFromString(String str)
  {
    aterm.ATerm trm = factory.parse(str);
    return ActionFromTerm(trm);
  }
  public Action ActionFromFile(java.io.InputStream stream) throws java.io.IOException {
    return ActionFromTerm(factory.readFromFile(stream));
  }
  public ActionList ActionListFromString(String str)
  {
    aterm.ATerm trm = factory.parse(str);
    return ActionListFromTerm(trm);
  }
  public ActionList ActionListFromFile(java.io.InputStream stream) throws java.io.IOException {
    return ActionListFromTerm(factory.readFromFile(stream));
  }
  public Menu MenuFromString(String str)
  {
    aterm.ATerm trm = factory.parse(str);
    return MenuFromTerm(trm);
  }
  public Menu MenuFromFile(java.io.InputStream stream) throws java.io.IOException {
    return MenuFromTerm(factory.readFromFile(stream));
  }
  public Event EventFromString(String str)
  {
    aterm.ATerm trm = factory.parse(str);
    return EventFromTerm(trm);
  }
  public Event EventFromFile(java.io.InputStream stream) throws java.io.IOException {
    return EventFromTerm(factory.readFromFile(stream));
  }
  public Process ProcessFromString(String str)
  {
    aterm.ATerm trm = factory.parse(str);
    return ProcessFromTerm(trm);
  }
  public Process ProcessFromFile(java.io.InputStream stream) throws java.io.IOException {
    return ProcessFromTerm(factory.readFromFile(stream));
  }
  public Pipe PipeFromString(String str)
  {
    aterm.ATerm trm = factory.parse(str);
    return PipeFromTerm(trm);
  }
  public Pipe PipeFromFile(java.io.InputStream stream) throws java.io.IOException {
    return PipeFromTerm(factory.readFromFile(stream));
  }
}
