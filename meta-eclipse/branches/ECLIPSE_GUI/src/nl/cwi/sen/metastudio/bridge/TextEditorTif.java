package nl.cwi.sen.metastudio.bridge;

// Java tool interface TextEditorTif
// This file is generated automatically, please do not edit!
// generation time: May 26, 2003 2:57:43 PM


import aterm.*;

public interface TextEditorTif
{
  public void reloadFile(String s0);
  public void tbSetCharPos(String s0, int i1);
  public void tbAddMenus(ATerm t0);
  public void tbSetMsg(String s0);
  public void tbUnsetFocus(String s0);
  public void tbSetFocus(String s0, String s1, int i2, int i3);
  public void moveEditorToFront(String s0);
  public ATerm tbGetFocusText(String s0, int i1, int i2);
  public ATerm editFile(String s0);
  public void recAckEvent(ATerm t0);
  public void recTerminate(ATerm t0);
}
