package nl.cwi.sen.metastudio.datastructures;

import java.io.IOException;
import java.io.InputStream;

abstract public class StepsImpl extends DatastructuresConstructor
{
  public static Steps fromString(String str)
  {
    aterm.ATerm trm = getStaticDatastructuresFactory().parse(str);
    return fromTerm(trm);
  }
  public static Steps fromTextFile(InputStream stream) throws aterm.ParseError, IOException
  {
    aterm.ATerm trm = getStaticDatastructuresFactory().readFromTextFile(stream);
    return fromTerm(trm);
  }
  public boolean isEqual(Steps peer)
  {
    return term.isEqual(peer.toTerm());
  }
  public static Steps fromTerm(aterm.ATerm trm)
  {
    Steps tmp;
    if ((tmp = Steps_Empty.fromTerm(trm)) != null) {
      return tmp;
    }

    if ((tmp = Steps_Multi.fromTerm(trm)) != null) {
      return tmp;
    }


    throw new RuntimeException("This is not a Steps: " + trm);
  }

  public boolean isSteps()  {
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

  public Integer getHead()
  {
     throw new RuntimeException("This Steps has no Head");
  }

  public Steps setHead(Integer _head)
  {
     throw new RuntimeException("This Steps has no Head");
  }

  public Steps getTail()
  {
     throw new RuntimeException("This Steps has no Tail");
  }

  public Steps setTail(Steps _tail)
  {
     throw new RuntimeException("This Steps has no Tail");
  }


}

