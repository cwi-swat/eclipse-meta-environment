package nl.cwi.sen.metastudio.datastructures;

import java.io.IOException;
import java.io.InputStream;

abstract public class NodeListImpl extends DatastructuresConstructor
{
  public static NodeList fromString(String str)
  {
    aterm.ATerm trm = getStaticDatastructuresFactory().parse(str);
    return fromTerm(trm);
  }
  public static NodeList fromTextFile(InputStream stream) throws aterm.ParseError, IOException
  {
    aterm.ATerm trm = getStaticDatastructuresFactory().readFromTextFile(stream);
    return fromTerm(trm);
  }
  public boolean isEqual(NodeList peer)
  {
    return term.isEqual(peer.toTerm());
  }
  public static NodeList fromTerm(aterm.ATerm trm)
  {
    NodeList tmp;
    if ((tmp = NodeList_Empty.fromTerm(trm)) != null) {
      return tmp;
    }

    if ((tmp = NodeList_Multi.fromTerm(trm)) != null) {
      return tmp;
    }


    throw new RuntimeException("This is not a NodeList: " + trm);
  }

  public boolean isNodeList()  {
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

  public Node getHead()
  {
     throw new RuntimeException("This NodeList has no Head");
  }

  public NodeList setHead(Node _head)
  {
     throw new RuntimeException("This NodeList has no Head");
  }

  public NodeList getTail()
  {
     throw new RuntimeException("This NodeList has no Tail");
  }

  public NodeList setTail(NodeList _tail)
  {
     throw new RuntimeException("This NodeList has no Tail");
  }


}

