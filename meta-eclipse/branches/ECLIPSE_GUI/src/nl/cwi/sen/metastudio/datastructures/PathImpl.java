package nl.cwi.sen.metastudio.datastructures;

import java.io.InputStream;
import java.io.IOException;

abstract public class PathImpl extends DatastructuresConstructor
{
  public static Path fromString(String str)
  {
    aterm.ATerm trm = getStaticDatastructuresFactory().parse(str);
    return fromTerm(trm);
  }
  public static Path fromTextFile(InputStream stream) throws aterm.ParseError, IOException
  {
    aterm.ATerm trm = getStaticDatastructuresFactory().readFromTextFile(stream);
    return fromTerm(trm);
  }
  public boolean isEqual(Path peer)
  {
    return term.isEqual(peer.toTerm());
  }
  public static Path fromTerm(aterm.ATerm trm)
  {
    Path tmp;
    if ((tmp = Path_Root.fromTerm(trm)) != null) {
      return tmp;
    }

    if ((tmp = Path_LeftLayout.fromTerm(trm)) != null) {
      return tmp;
    }

    if ((tmp = Path_Term.fromTerm(trm)) != null) {
      return tmp;
    }

    if ((tmp = Path_RightLayout.fromTerm(trm)) != null) {
      return tmp;
    }


    throw new RuntimeException("This is not a Path: " + trm);
  }

  public boolean isPath()  {
    return true;
  }

  public boolean isRoot()
  {
    return false;
  }

  public boolean isLeftLayout()
  {
    return false;
  }

  public boolean isTerm()
  {
    return false;
  }

  public boolean isRightLayout()
  {
    return false;
  }

  public boolean hasSteps()
  {
    return false;
  }

  public Steps getSteps()
  {
     throw new RuntimeException("This Path has no Steps");
  }

  public Path setSteps(Steps _steps)
  {
     throw new RuntimeException("This Path has no Steps");
  }


}

