package nl.cwi.sen.metastudio.datastructures;

import java.io.InputStream;
import java.io.IOException;

abstract public class FocusListImpl extends DatastructuresConstructor
{
  public static FocusList fromString(String str)
  {
    aterm.ATerm trm = getStaticDatastructuresFactory().parse(str);
    return fromTerm(trm);
  }
  public static FocusList fromTextFile(InputStream stream) throws aterm.ParseError, IOException
  {
    aterm.ATerm trm = getStaticDatastructuresFactory().readFromTextFile(stream);
    return fromTerm(trm);
  }
  public boolean isEqual(FocusList peer)
  {
    return term.isEqual(peer.toTerm());
  }
  public static FocusList fromTerm(aterm.ATerm trm)
  {
    FocusList tmp;
    if ((tmp = FocusList_Empty.fromTerm(trm)) != null) {
      return tmp;
    }

    if ((tmp = FocusList_Multi.fromTerm(trm)) != null) {
      return tmp;
    }


    throw new RuntimeException("This is not a FocusList: " + trm);
  }

  public boolean isFocusList()  {
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

  public Focus getHead()
  {
     throw new RuntimeException("This FocusList has no Head");
  }

  public FocusList setHead(Focus _head)
  {
     throw new RuntimeException("This FocusList has no Head");
  }

  public FocusList getTail()
  {
     throw new RuntimeException("This FocusList has no Tail");
  }

  public FocusList setTail(FocusList _tail)
  {
     throw new RuntimeException("This FocusList has no Tail");
  }


}

