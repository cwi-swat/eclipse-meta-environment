package nl.cwi.sen.metastudio.bridge;

// Java tool bridge TextEditorBridge
// This file is generated automatically, please do not edit!
// generation time: May 26, 2003 2:57:43 PM


import aterm.*;

public class TextEditorBridge
  extends TextEditorTool
{
  private TextEditorTif tool;

  //{{{  public TextEditorBridge(ATermFactory factory, TextEditorTif tool)

  public TextEditorBridge(ATermFactory factory, TextEditorTif tool)
  {
    super(factory);
    this.tool = tool;
  }

  //}}}

  //{{{  public void reloadFile(String s0)

  public void reloadFile(String s0)
  {
    if (tool != null) {
      tool.reloadFile(s0);
    }
    else {
      throw new UnsupportedOperationException("method `reloadFile' not supported.");
    }
  }

  //}}}
  //{{{  public void tbSetCharPos(String s0, int i1)

  public void tbSetCharPos(String s0, int i1)
  {
    if (tool != null) {
      tool.tbSetCharPos(s0, i1);
    }
    else {
      throw new UnsupportedOperationException("method `tbSetCharPos' not supported.");
    }
  }

  //}}}
  //{{{  public void tbAddMenus(ATerm t0)

  public void tbAddMenus(ATerm t0)
  {
    if (tool != null) {
      tool.tbAddMenus(t0);
    }
    else {
      throw new UnsupportedOperationException("method `tbAddMenus' not supported.");
    }
  }

  //}}}
  //{{{  public void tbSetMsg(String s0)

  public void tbSetMsg(String s0)
  {
    if (tool != null) {
      tool.tbSetMsg(s0);
    }
    else {
      throw new UnsupportedOperationException("method `tbSetMsg' not supported.");
    }
  }

  //}}}
  //{{{  public void tbUnsetFocus(String s0)

  public void tbUnsetFocus(String s0)
  {
    if (tool != null) {
      tool.tbUnsetFocus(s0);
    }
    else {
      throw new UnsupportedOperationException("method `tbUnsetFocus' not supported.");
    }
  }

  //}}}
  //{{{  public void tbSetFocus(String s0, String s1, int i2, int i3)

  public void tbSetFocus(String s0, String s1, int i2, int i3)
  {
    if (tool != null) {
      tool.tbSetFocus(s0, s1, i2, i3);
    }
    else {
      throw new UnsupportedOperationException("method `tbSetFocus' not supported.");
    }
  }

  //}}}
  //{{{  public void moveEditorToFront(String s0)

  public void moveEditorToFront(String s0)
  {
    if (tool != null) {
      tool.moveEditorToFront(s0);
    }
    else {
      throw new UnsupportedOperationException("method `moveEditorToFront' not supported.");
    }
  }

  //}}}
  //{{{  public ATerm tbGetFocusText(String s0, int i1, int i2)

  public ATerm tbGetFocusText(String s0, int i1, int i2)
  {
    if (tool != null) {
      return tool.tbGetFocusText(s0, i1, i2);
    }
    else {
      throw new UnsupportedOperationException("method `tbGetFocusText' not supported.");
    }
  }

  //}}}
  //{{{  public ATerm editFile(String s0)

  public ATerm editFile(String s0)
  {
    if (tool != null) {
      return tool.editFile(s0);
    }
    else {
      throw new UnsupportedOperationException("method `editFile' not supported.");
    }
  }

  //}}}
  //{{{  public void recAckEvent(ATerm t0)

  public void recAckEvent(ATerm t0)
  {
    if (tool != null) {
      tool.recAckEvent(t0);
    }
    else {
      throw new UnsupportedOperationException("method `recAckEvent' not supported.");
    }
  }

  //}}}
  //{{{  public void recTerminate(ATerm t0)

  public void recTerminate(ATerm t0)
  {
    if (tool != null) {
      tool.recTerminate(t0);
    }
    else {
      throw new UnsupportedOperationException("method `recTerminate' not supported.");
    }
  }

  //}}}
}
