package nl.cwi.sen.metastudio.datastructures;

import java.io.IOException;
import java.io.InputStream;

abstract public class MenuImpl extends DatastructuresConstructor
{
  public static Menu fromString(String str)
  {
    aterm.ATerm trm = getStaticDatastructuresFactory().parse(str);
    return fromTerm(trm);
  }
  public static Menu fromTextFile(InputStream stream) throws aterm.ParseError, IOException
  {
    aterm.ATerm trm = getStaticDatastructuresFactory().readFromTextFile(stream);
    return fromTerm(trm);
  }
  public boolean isEqual(Menu peer)
  {
    return term.isEqual(peer.toTerm());
  }
  public static Menu fromTerm(aterm.ATerm trm)
  {
    Menu tmp;
    if ((tmp = Menu_Default.fromTerm(trm)) != null) {
      return tmp;
    }


    throw new RuntimeException("This is not a Menu: " + trm);
  }

  public boolean isMenu()  {
    return true;
  }

  public boolean isDefault()
  {
    return false;
  }

  public boolean hasMain()
  {
    return false;
  }

  public boolean hasSub()
  {
    return false;
  }

  public String getMain()
  {
     throw new RuntimeException("This Menu has no Main");
  }

  public Menu setMain(String _main)
  {
     throw new RuntimeException("This Menu has no Main");
  }

  public String getSub()
  {
     throw new RuntimeException("This Menu has no Sub");
  }

  public Menu setSub(String _sub)
  {
     throw new RuntimeException("This Menu has no Sub");
  }


}

