package nl.cwi.sen.metastudio.adt.texteditor;


abstract public class ActionImpl extends TextEditorConstructor
{
  protected ActionImpl(TextEditorFactory factory) {
     super(factory);
  }
  protected void init(int hashCode, aterm.ATermList annos, aterm.AFun fun,	aterm.ATerm[] args) {
    super.init(hashCode, annos, fun, args);
  }
  protected void initHashCode(aterm.ATermList annos, aterm.AFun fun, aterm.ATerm[] i_args) {
  	super.initHashCode(annos, fun, i_args);
  }
  public boolean isEqual(Action peer)
  {
    return super.isEqual(peer);
  }
  public boolean isSortAction()  {
    return true;
  }

  public boolean isToFront()
  {
    return false;
  }

  public boolean isRereadContents()
  {
    return false;
  }

  public boolean isDisplayMessage()
  {
    return false;
  }

  public boolean isSetCursorAtLocation()
  {
    return false;
  }

  public boolean isSetCursorAtFocus()
  {
    return false;
  }

  public boolean isClearFocus()
  {
    return false;
  }

  public boolean isSetFocus()
  {
    return false;
  }

  public boolean isGetContents()
  {
    return false;
  }

  public boolean isSetActions()
  {
    return false;
  }

  public boolean hasMessage()
  {
    return false;
  }

  public boolean hasLocation()
  {
    return false;
  }

  public boolean hasFocus()
  {
    return false;
  }

  public boolean hasActions()
  {
    return false;
  }

  public String getMessage()
  {
     throw new UnsupportedOperationException("This Action has no Message");
  }

  public Action setMessage(String _message)
  {
     throw new IllegalArgumentException("Illegal argument: " + _message);
  }

  public int getLocation()
  {
     throw new UnsupportedOperationException("This Action has no Location");
  }

  public Action setLocation(int _location)
  {
     throw new IllegalArgumentException("Illegal argument: " + _location);
  }

  public aterm.ATerm getFocus()
  {
     throw new UnsupportedOperationException("This Action has no Focus");
  }

  public Action setFocus(aterm.ATerm _focus)
  {
     throw new IllegalArgumentException("Illegal argument: " + _focus);
  }

  public ActionList getActions()
  {
     throw new UnsupportedOperationException("This Action has no Actions");
  }

  public Action setActions(ActionList _actions)
  {
     throw new IllegalArgumentException("Illegal argument: " + _actions);
  }

}

