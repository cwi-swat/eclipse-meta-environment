package nl.cwi.sen.metastudio.datastructures;

import java.io.IOException;
import java.io.InputStream;

abstract public class AreaImpl extends DatastructuresConstructor
{
  public static Area fromString(String str)
  {
    aterm.ATerm trm = getStaticDatastructuresFactory().parse(str);
    return fromTerm(trm);
  }
  public static Area fromTextFile(InputStream stream) throws aterm.ParseError, IOException
  {
    aterm.ATerm trm = getStaticDatastructuresFactory().readFromTextFile(stream);
    return fromTerm(trm);
  }
  public boolean isEqual(Area peer)
  {
    return term.isEqual(peer.toTerm());
  }
  public static Area fromTerm(aterm.ATerm trm)
  {
    Area tmp;
    if ((tmp = Area_Default.fromTerm(trm)) != null) {
      return tmp;
    }


    throw new RuntimeException("This is not a Area: " + trm);
  }

  public boolean isArea()  {
    return true;
  }

  public boolean isDefault()
  {
    return false;
  }

  public boolean hasStart()
  {
    return false;
  }

  public boolean hasLength()
  {
    return false;
  }

  public Integer getStart()
  {
     throw new RuntimeException("This Area has no Start");
  }

  public Area setStart(Integer _start)
  {
     throw new RuntimeException("This Area has no Start");
  }

  public Integer getLength()
  {
     throw new RuntimeException("This Area has no Length");
  }

  public Area setLength(Integer _length)
  {
     throw new RuntimeException("This Area has no Length");
  }


}

