package nl.cwi.sen.metastudio.datastructures;

import java.io.InputStream;
import java.io.IOException;

abstract public class ActionListImpl extends DatastructuresConstructor
{
  public static ActionList fromString(String str)
  {
    aterm.ATerm trm = getStaticDatastructuresFactory().parse(str);
    return fromTerm(trm);
  }
  public static ActionList fromTextFile(InputStream stream) throws aterm.ParseError, IOException
  {
    aterm.ATerm trm = getStaticDatastructuresFactory().readFromTextFile(stream);
    return fromTerm(trm);
  }
  public boolean isEqual(ActionList peer)
  {
    return term.isEqual(peer.toTerm());
  }
  public static ActionList fromTerm(aterm.ATerm trm)
  {
    ActionList tmp;
    if ((tmp = ActionList_Empty.fromTerm(trm)) != null) {
      return tmp;
    }

    if ((tmp = ActionList_Multi.fromTerm(trm)) != null) {
      return tmp;
    }


    throw new RuntimeException("This is not a ActionList: " + trm);
  }

  public boolean isActionList()  {
    return true;
  }

  public boolean isEmpty()
  {
    return false;
  }

  public boolean isMulti()
  {
    return false;
  }

  public boolean hasHead()
  {
    return false;
  }

  public boolean hasTail()
  {
    return false;
  }

  public Menu getHead()
  {
     throw new RuntimeException("This ActionList has no Head");
  }

  public ActionList setHead(Menu _head)
  {
     throw new RuntimeException("This ActionList has no Head");
  }

  public ActionList getTail()
  {
     throw new RuntimeException("This ActionList has no Tail");
  }

  public ActionList setTail(ActionList _tail)
  {
     throw new RuntimeException("This ActionList has no Tail");
  }


}

