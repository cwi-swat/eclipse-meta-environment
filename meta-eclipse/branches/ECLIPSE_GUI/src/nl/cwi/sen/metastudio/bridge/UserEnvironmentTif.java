package nl.cwi.sen.metastudio.bridge;

// Java tool interface UserEnvironmentTif
// This file is generated automatically, please do not edit!
// generation time: Aug 1, 2003 3:02:51 PM


import aterm.ATerm;

public interface UserEnvironmentTif
{
  public void getContents(ATerm t0, ATerm t1);
  public void setCursorAtLocation(ATerm t0, int i1);
  public void setFocus(ATerm t0, ATerm t1);
  public void editFile(ATerm t0, String s1, String s2);
  public void messagef(String s0, ATerm t1);
  public void killEditor(ATerm t0);
  public void displayMessage(ATerm t0, String s1);
  public void updateList(String s0, String s1);
  public void initializeUi(String s0);
  public void warningf(String s0, ATerm t1);
  public void editorToFront(ATerm t0);
  public void setCursorAtFocus(ATerm t0, ATerm t1);
  public void message(String s0);
  public void newGraph(ATerm t0);
  public void deleteModules(ATerm t0);
  public void displayGraph(String s0, ATerm t1);
  public void setActions(ATerm t0, ATerm t1);
  public void clearHistory();
  public void graphLayouted(String s0, ATerm t1);
  public void errorf(String s0, ATerm t1);
  public void warning(String s0);
  public void error(String s0);
  public void buttonsFound(ATerm t0, String s1, ATerm t2);
  public void clearFocus(ATerm t0);
  public void addStatusf(ATerm t0, String s1, ATerm t2);
  public void moduleInfo(String s0, ATerm t1);
  public void endStatus(ATerm t0);
  public void rereadContents(ATerm t0);
  public void addStatus(ATerm t0, String s1);
  public ATerm showQuestionDialog(String s0);
  public ATerm showFileDialog(String s0, String s1, String s2);
  public void recAckEvent(ATerm t0);
  public void recTerminate(ATerm t0);
}
