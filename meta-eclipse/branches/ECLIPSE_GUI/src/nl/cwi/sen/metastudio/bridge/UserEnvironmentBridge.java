package nl.cwi.sen.metastudio.bridge;

// Java tool bridge UserEnvironmentBridge
// This file is generated automatically, please do not edit!
// generation time: Jul 31, 2003 12:03:39 PM


import aterm.*;

public class UserEnvironmentBridge
  extends UserEnvironmentTool
{
  private UserEnvironmentTif tool;

  //{{{  public UserEnvironmentBridge(ATermFactory factory, UserEnvironmentTif tool)

  public UserEnvironmentBridge(ATermFactory factory, UserEnvironmentTif tool)
  {
    super(factory);
    this.tool = tool;
  }

  //}}}

  //{{{  public void getContents(ATerm t0, ATerm t1)

  public void getContents(ATerm t0, ATerm t1)
  {
    if (tool != null) {
      tool.getContents(t0, t1);
    }
    else {
      throw new UnsupportedOperationException("method `getContents' not supported.");
    }
  }

  //}}}
  //{{{  public void setCursorAtLocation(ATerm t0, int i1)

  public void setCursorAtLocation(ATerm t0, int i1)
  {
    if (tool != null) {
      tool.setCursorAtLocation(t0, i1);
    }
    else {
      throw new UnsupportedOperationException("method `setCursorAtLocation' not supported.");
    }
  }

  //}}}
  //{{{  public void setFocus(ATerm t0, ATerm t1)

  public void setFocus(ATerm t0, ATerm t1)
  {
    if (tool != null) {
      tool.setFocus(t0, t1);
    }
    else {
      throw new UnsupportedOperationException("method `setFocus' not supported.");
    }
  }

  //}}}
  //{{{  public void editFile(ATerm t0, String s1, String s2)

  public void editFile(ATerm t0, String s1, String s2)
  {
    if (tool != null) {
      tool.editFile(t0, s1, s2);
    }
    else {
      throw new UnsupportedOperationException("method `editFile' not supported.");
    }
  }

  //}}}
  //{{{  public void messagef(String s0, ATerm t1)

  public void messagef(String s0, ATerm t1)
  {
    if (tool != null) {
      tool.messagef(s0, t1);
    }
    else {
      throw new UnsupportedOperationException("method `messagef' not supported.");
    }
  }

  //}}}
  //{{{  public void killEditor(ATerm t0)

  public void killEditor(ATerm t0)
  {
    if (tool != null) {
      tool.killEditor(t0);
    }
    else {
      throw new UnsupportedOperationException("method `killEditor' not supported.");
    }
  }

  //}}}
  //{{{  public void displayMessage(ATerm t0, String s1)

  public void displayMessage(ATerm t0, String s1)
  {
    if (tool != null) {
      tool.displayMessage(t0, s1);
    }
    else {
      throw new UnsupportedOperationException("method `displayMessage' not supported.");
    }
  }

  //}}}
  //{{{  public void updateList(String s0, String s1)

  public void updateList(String s0, String s1)
  {
    if (tool != null) {
      tool.updateList(s0, s1);
    }
    else {
      throw new UnsupportedOperationException("method `updateList' not supported.");
    }
  }

  //}}}
  //{{{  public void initializeUi(String s0)

  public void initializeUi(String s0)
  {
    if (tool != null) {
      tool.initializeUi(s0);
    }
    else {
      throw new UnsupportedOperationException("method `initializeUi' not supported.");
    }
  }

  //}}}
  //{{{  public void warningf(String s0, ATerm t1)

  public void warningf(String s0, ATerm t1)
  {
    if (tool != null) {
      tool.warningf(s0, t1);
    }
    else {
      throw new UnsupportedOperationException("method `warningf' not supported.");
    }
  }

  //}}}
  //{{{  public void editorToFront(ATerm t0)

  public void editorToFront(ATerm t0)
  {
    if (tool != null) {
      tool.editorToFront(t0);
    }
    else {
      throw new UnsupportedOperationException("method `editorToFront' not supported.");
    }
  }

  //}}}
  //{{{  public void setCursorAtFocus(ATerm t0, ATerm t1)

  public void setCursorAtFocus(ATerm t0, ATerm t1)
  {
    if (tool != null) {
      tool.setCursorAtFocus(t0, t1);
    }
    else {
      throw new UnsupportedOperationException("method `setCursorAtFocus' not supported.");
    }
  }

  //}}}
  //{{{  public void message(String s0)

  public void message(String s0)
  {
    if (tool != null) {
      tool.message(s0);
    }
    else {
      throw new UnsupportedOperationException("method `message' not supported.");
    }
  }

  //}}}
  //{{{  public void newGraph(ATerm t0)

  public void newGraph(ATerm t0)
  {
    if (tool != null) {
      tool.newGraph(t0);
    }
    else {
      throw new UnsupportedOperationException("method `newGraph' not supported.");
    }
  }

  //}}}
  //{{{  public void deleteModules(ATerm t0)

  public void deleteModules(ATerm t0)
  {
    if (tool != null) {
      tool.deleteModules(t0);
    }
    else {
      throw new UnsupportedOperationException("method `deleteModules' not supported.");
    }
  }

  //}}}
  //{{{  public void displayGraph(String s0, ATerm t1)

  public void displayGraph(String s0, ATerm t1)
  {
    if (tool != null) {
      tool.displayGraph(s0, t1);
    }
    else {
      throw new UnsupportedOperationException("method `displayGraph' not supported.");
    }
  }

  //}}}
  //{{{  public void setActions(ATerm t0, ATerm t1)

  public void setActions(ATerm t0, ATerm t1)
  {
    if (tool != null) {
      tool.setActions(t0, t1);
    }
    else {
      throw new UnsupportedOperationException("method `setActions' not supported.");
    }
  }

  //}}}
  //{{{  public void clearHistory()

  public void clearHistory()
  {
    if (tool != null) {
      tool.clearHistory();
    }
    else {
      throw new UnsupportedOperationException("method `clearHistory' not supported.");
    }
  }

  //}}}
  //{{{  public void graphLayouted(String s0, ATerm t1)

  public void graphLayouted(String s0, ATerm t1)
  {
    if (tool != null) {
      tool.graphLayouted(s0, t1);
    }
    else {
      throw new UnsupportedOperationException("method `graphLayouted' not supported.");
    }
  }

  //}}}
  //{{{  public void errorf(String s0, ATerm t1)

  public void errorf(String s0, ATerm t1)
  {
    if (tool != null) {
      tool.errorf(s0, t1);
    }
    else {
      throw new UnsupportedOperationException("method `errorf' not supported.");
    }
  }

  //}}}
  //{{{  public void warning(String s0)

  public void warning(String s0)
  {
    if (tool != null) {
      tool.warning(s0);
    }
    else {
      throw new UnsupportedOperationException("method `warning' not supported.");
    }
  }

  //}}}
  //{{{  public void error(String s0)

  public void error(String s0)
  {
    if (tool != null) {
      tool.error(s0);
    }
    else {
      throw new UnsupportedOperationException("method `error' not supported.");
    }
  }

  //}}}
  //{{{  public void buttonsFound(ATerm t0, String s1, ATerm t2)

  public void buttonsFound(ATerm t0, String s1, ATerm t2)
  {
    if (tool != null) {
      tool.buttonsFound(t0, s1, t2);
    }
    else {
      throw new UnsupportedOperationException("method `buttonsFound' not supported.");
    }
  }

  //}}}
  //{{{  public void clearFocus(ATerm t0)

  public void clearFocus(ATerm t0)
  {
    if (tool != null) {
      tool.clearFocus(t0);
    }
    else {
      throw new UnsupportedOperationException("method `clearFocus' not supported.");
    }
  }

  //}}}
  //{{{  public void addStatusf(ATerm t0, String s1, ATerm t2)

  public void addStatusf(ATerm t0, String s1, ATerm t2)
  {
    if (tool != null) {
      tool.addStatusf(t0, s1, t2);
    }
    else {
      throw new UnsupportedOperationException("method `addStatusf' not supported.");
    }
  }

  //}}}
  //{{{  public void moduleInfo(String s0, ATerm t1)

  public void moduleInfo(String s0, ATerm t1)
  {
    if (tool != null) {
      tool.moduleInfo(s0, t1);
    }
    else {
      throw new UnsupportedOperationException("method `moduleInfo' not supported.");
    }
  }

  //}}}
  //{{{  public void endStatus(ATerm t0)

  public void endStatus(ATerm t0)
  {
    if (tool != null) {
      tool.endStatus(t0);
    }
    else {
      throw new UnsupportedOperationException("method `endStatus' not supported.");
    }
  }

  //}}}
  //{{{  public void rereadContents(ATerm t0)

  public void rereadContents(ATerm t0)
  {
    if (tool != null) {
      tool.rereadContents(t0);
    }
    else {
      throw new UnsupportedOperationException("method `rereadContents' not supported.");
    }
  }

  //}}}
  //{{{  public void addStatus(ATerm t0, String s1)

  public void addStatus(ATerm t0, String s1)
  {
    if (tool != null) {
      tool.addStatus(t0, s1);
    }
    else {
      throw new UnsupportedOperationException("method `addStatus' not supported.");
    }
  }

  //}}}
  //{{{  public ATerm deconsFilename(String s0, String s1)

  public ATerm deconsFilename(String s0, String s1)
  {
    if (tool != null) {
      return tool.deconsFilename(s0, s1);
    }
    else {
      throw new UnsupportedOperationException("method `deconsFilename' not supported.");
    }
  }

  //}}}
  //{{{  public ATerm showQuestionDialog(String s0)

  public ATerm showQuestionDialog(String s0)
  {
    if (tool != null) {
      return tool.showQuestionDialog(s0);
    }
    else {
      throw new UnsupportedOperationException("method `showQuestionDialog' not supported.");
    }
  }

  //}}}
  //{{{  public ATerm showFileDialog(String s0, String s1, String s2)

  public ATerm showFileDialog(String s0, String s1, String s2)
  {
    if (tool != null) {
      return tool.showFileDialog(s0, s1, s2);
    }
    else {
      throw new UnsupportedOperationException("method `showFileDialog' not supported.");
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
