package nl.cwi.sen.metastudio.datastructures;

import java.io.InputStream;
import java.io.IOException;

abstract public class FocusImpl extends DatastructuresConstructor
{
  public static Focus fromString(String str)
  {
    aterm.ATerm trm = getStaticDatastructuresFactory().parse(str);
    return fromTerm(trm);
  }
  public static Focus fromTextFile(InputStream stream) throws aterm.ParseError, IOException
  {
    aterm.ATerm trm = getStaticDatastructuresFactory().readFromTextFile(stream);
    return fromTerm(trm);
  }
  public boolean isEqual(Focus peer)
  {
    return term.isEqual(peer.toTerm());
  }
  public static Focus fromTerm(aterm.ATerm trm)
  {
    Focus tmp;
    if ((tmp = Focus_Empty.fromTerm(trm)) != null) {
      return tmp;
    }

    if ((tmp = Focus_NotEmpty.fromTerm(trm)) != null) {
      return tmp;
    }


    throw new RuntimeException("This is not a Focus: " + trm);
  }

  public boolean isFocus()  {
    return true;
  }

  public boolean isEmpty()
  {
    return false;
  }

  public boolean isNotEmpty()
  {
    return false;
  }

  public boolean hasPath()
  {
    return false;
  }

  public boolean hasSort()
  {
    return false;
  }

  public boolean hasArea()
  {
    return false;
  }

  public boolean hasUnparsed()
  {
    return false;
  }

  public Path getPath()
  {
     throw new RuntimeException("This Focus has no Path");
  }

  public Focus setPath(Path _path)
  {
     throw new RuntimeException("This Focus has no Path");
  }

  public String getSort()
  {
     throw new RuntimeException("This Focus has no Sort");
  }

  public Focus setSort(String _sort)
  {
     throw new RuntimeException("This Focus has no Sort");
  }

  public Area getArea()
  {
     throw new RuntimeException("This Focus has no Area");
  }

  public Focus setArea(Area _area)
  {
     throw new RuntimeException("This Focus has no Area");
  }

  public Integer getUnparsed()
  {
     throw new RuntimeException("This Focus has no Unparsed");
  }

  public Focus setUnparsed(Integer _unparsed)
  {
     throw new RuntimeException("This Focus has no Unparsed");
  }


}

