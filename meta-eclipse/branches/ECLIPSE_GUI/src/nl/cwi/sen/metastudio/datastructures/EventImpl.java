package nl.cwi.sen.metastudio.datastructures;

import java.io.IOException;
import java.io.InputStream;

abstract public class EventImpl extends DatastructuresConstructor
{
  public static Event fromString(String str)
  {
    aterm.ATerm trm = getStaticDatastructuresFactory().parse(str);
    return fromTerm(trm);
  }
  public static Event fromTextFile(InputStream stream) throws aterm.ParseError, IOException
  {
    aterm.ATerm trm = getStaticDatastructuresFactory().readFromTextFile(stream);
    return fromTerm(trm);
  }
  public boolean isEqual(Event peer)
  {
    return term.isEqual(peer.toTerm());
  }
  public static Event fromTerm(aterm.ATerm trm)
  {
    Event tmp;
    if ((tmp = Event_Menu.fromTerm(trm)) != null) {
      return tmp;
    }

    if ((tmp = Event_Mouse.fromTerm(trm)) != null) {
      return tmp;
    }

    if ((tmp = Event_Contents.fromTerm(trm)) != null) {
      return tmp;
    }

    if ((tmp = Event_Modified.fromTerm(trm)) != null) {
      return tmp;
    }


    throw new RuntimeException("This is not a Event: " + trm);
  }

  public boolean isEvent()  {
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
     throw new RuntimeException("This Event has no Menu");
  }

  public Event setMenu(Menu _menu)
  {
     throw new RuntimeException("This Event has no Menu");
  }

  public Integer getLocation()
  {
     throw new RuntimeException("This Event has no Location");
  }

  public Event setLocation(Integer _location)
  {
     throw new RuntimeException("This Event has no Location");
  }

  public String getText()
  {
     throw new RuntimeException("This Event has no Text");
  }

  public Event setText(String _text)
  {
     throw new RuntimeException("This Event has no Text");
  }


}

