package nl.cwi.sen.metastudio.datastructures;

import java.io.IOException;
import java.io.InputStream;

abstract public class EdgeImpl extends DatastructuresConstructor
{
  public static Edge fromString(String str)
  {
    aterm.ATerm trm = getStaticDatastructuresFactory().parse(str);
    return fromTerm(trm);
  }
  public static Edge fromTextFile(InputStream stream) throws aterm.ParseError, IOException
  {
    aterm.ATerm trm = getStaticDatastructuresFactory().readFromTextFile(stream);
    return fromTerm(trm);
  }
  public boolean isEqual(Edge peer)
  {
    return term.isEqual(peer.toTerm());
  }
  public static Edge fromTerm(aterm.ATerm trm)
  {
    Edge tmp;
    if ((tmp = Edge_Default.fromTerm(trm)) != null) {
      return tmp;
    }


    throw new RuntimeException("This is not a Edge: " + trm);
  }

  public boolean isEdge()  {
    return true;
  }

  public boolean isDefault()
  {
    return false;
  }

  public boolean hasFrom()
  {
    return false;
  }

  public boolean hasTo()
  {
    return false;
  }

  public boolean hasAttributes()
  {
    return false;
  }

  public NodeId getFrom()
  {
     throw new RuntimeException("This Edge has no From");
  }

  public Edge setFrom(NodeId _from)
  {
     throw new RuntimeException("This Edge has no From");
  }

  public NodeId getTo()
  {
     throw new RuntimeException("This Edge has no To");
  }

  public Edge setTo(NodeId _to)
  {
     throw new RuntimeException("This Edge has no To");
  }

  public AttributeList getAttributes()
  {
     throw new RuntimeException("This Edge has no Attributes");
  }

  public Edge setAttributes(AttributeList _attributes)
  {
     throw new RuntimeException("This Edge has no Attributes");
  }


}

