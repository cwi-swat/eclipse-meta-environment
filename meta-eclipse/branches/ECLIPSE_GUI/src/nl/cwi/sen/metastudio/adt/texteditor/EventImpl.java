package nl.cwi.sen.metastudio.adt.texteditor;


abstract public class EventImpl extends TextEditorConstructor
{
  protected EventImpl(TextEditorFactory factory) {
     super(factory);
  }
  protected void init(int hashCode, aterm.ATermList annos, aterm.AFun fun,	aterm.ATerm[] args) {
    super.init(hashCode, annos, fun, args);
  }
  protected void initHashCode(aterm.ATermList annos, aterm.AFun fun, aterm.ATerm[] i_args) {
  	super.initHashCode(annos, fun, i_args);
  }
  public boolean isEqual(Event peer)
  {
    return super.isEqual(peer);
  }
  public boolean isSortEvent()  {
    return true;
  }

  public boolean isMenu()
  {
    return false;
  }

  public boolean isMouse()
  {
    return false;
  }

  public boolean isContents()
  {
    return false;
  }

  public boolean isModified()
  {
    return false;
  }

  public boolean hasMenu()
  {
    return false;
  }

  public boolean hasLocation()
  {
    return false;
  }

  public boolean hasText()
  {
    return false;
  }

  public Menu getMenu()
  {
     throw new UnsupportedOperationException("This Event has no Menu");
  }

  public Event setMenu(Menu _menu)
  {
     throw new IllegalArgumentException("Illegal argument: " + _menu);
  }

  public int getLocation()
  {
     throw new UnsupportedOperationException("This Event has no Location");
  }

  public Event setLocation(int _location)
  {
     throw new IllegalArgumentException("Illegal argument: " + _location);
  }

  public String getText()
  {
     throw new UnsupportedOperationException("This Event has no Text");
  }

  public Event setText(String _text)
  {
     throw new IllegalArgumentException("Illegal argument: " + _text);
  }

}

