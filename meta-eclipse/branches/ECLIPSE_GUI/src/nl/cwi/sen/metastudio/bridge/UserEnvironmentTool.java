package nl.cwi.sen.metastudio.bridge;

// Java tool interface class UserEnvironmentTool
// This file is generated automatically, please do not edit!
// generation time: Aug 1, 2003 3:02:51 PM


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import toolbus.AbstractTool;
import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermFactory;
import aterm.ATermList;

abstract public class UserEnvironmentTool
  extends AbstractTool
  implements UserEnvironmentTif
{
  // This table will hold the complete input signature
  private Map sigTable = new HashMap();

  //{{{  Patterns that are used to match against incoming terms

  private ATerm PgetContents0;
  private ATerm PsetCursorAtLocation0;
  private ATerm PsetFocus0;
  private ATerm PeditFile0;
  private ATerm Pmessagef0;
  private ATerm PkillEditor0;
  private ATerm PdisplayMessage0;
  private ATerm PupdateList0;
  private ATerm PinitializeUi0;
  private ATerm Pwarningf0;
  private ATerm PeditorToFront0;
  private ATerm PsetCursorAtFocus0;
  private ATerm Pmessage0;
  private ATerm PnewGraph0;
  private ATerm PdeleteModules0;
  private ATerm PdisplayGraph0;
  private ATerm PsetActions0;
  private ATerm PclearHistory0;
  private ATerm PgraphLayouted0;
  private ATerm Perrorf0;
  private ATerm Pwarning0;
  private ATerm Perror0;
  private ATerm PbuttonsFound0;
  private ATerm PclearFocus0;
  private ATerm PaddStatusf0;
  private ATerm PmoduleInfo0;
  private ATerm PendStatus0;
  private ATerm PrereadContents0;
  private ATerm PaddStatus0;
  private ATerm PshowQuestionDialog0;
  private ATerm PshowFileDialog0;
  private ATerm PrecAckEvent0;
  private ATerm PrecTerminate0;

  //}}}

  //{{{  protected UserEnvironmentTool(ATermFactory factory)

  // Mimic the constructor from the AbstractTool class
  protected UserEnvironmentTool(ATermFactory factory)
  {
    super(factory);
    initSigTable();
    initPatterns();
  }

  //}}}

  //{{{  private void initSigTable()

  // This method initializes the table with input signatures
  private void initSigTable()
  {
    sigTable.put(factory.parse("rec-ack-event(<p2>,<term>)"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<p2>,fun(<term>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-terminate(<p2>,<term>)"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<user-environment>,initialize-ui(<str>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<user-environment>,add-status(<term>,<str>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<user-environment>,add-statusf(<term>,<str>,<list>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<user-environment>,end-status(<term>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<user-environment>,error(<str>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<user-environment>,errorf(<str>,<list>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<user-environment>,warning(<str>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<user-environment>,warningf(<str>,<list>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<user-environment>,message(<str>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<user-environment>,messagef(<str>,<list>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<user-environment>,display-graph(<str>,<term>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<user-environment>,delete-modules(<list>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<user-environment>,module-info(<str>,<list>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<user-environment>,new-graph(<list>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<user-environment>,graph-layouted(<str>,<term>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<user-environment>,buttons-found(<term>,<str>,<list>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-eval(<user-environment>,show-file-dialog(<str>,<str>,<str>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-eval(<user-environment>,show-question-dialog(<str>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<user-environment>,clear-history)"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<user-environment>,update-list(<str>,<str>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<user-environment>,edit-file(<term>,<str>,<str>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<user-environment>,set-actions(<term>,<list>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<user-environment>,reread-contents(<term>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<user-environment>,set-focus(<term>,<term>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<user-environment>,get-contents(<term>,<term>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<user-environment>,clear-focus(<term>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<user-environment>,display-message(<term>,<str>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<user-environment>,set-cursor-at-location(<term>,<int>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<user-environment>,set-cursor-at-focus(<term>,<term>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<user-environment>,editor-to-front(<term>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<user-environment>,kill-editor(<term>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-ack-event(<user-environment>,<term>)"), new Boolean(true));
    sigTable.put(factory.parse("rec-terminate(<user-environment>,<term>)"), new Boolean(true));
  }

  //}}}
  //{{{  private void initPatterns()

  // Initialize the patterns that are used to match against incoming terms
  private void initPatterns()
  {
    PgetContents0 = factory.parse("rec-do(get-contents(<term>,<term>))");
    PsetCursorAtLocation0 = factory.parse("rec-do(set-cursor-at-location(<term>,<int>))");
    PsetFocus0 = factory.parse("rec-do(set-focus(<term>,<term>))");
    PeditFile0 = factory.parse("rec-do(edit-file(<term>,<str>,<str>))");
    Pmessagef0 = factory.parse("rec-do(messagef(<str>,<term>))");
    PkillEditor0 = factory.parse("rec-do(kill-editor(<term>))");
    PdisplayMessage0 = factory.parse("rec-do(display-message(<term>,<str>))");
    PupdateList0 = factory.parse("rec-do(update-list(<str>,<str>))");
    PinitializeUi0 = factory.parse("rec-do(initialize-ui(<str>))");
    Pwarningf0 = factory.parse("rec-do(warningf(<str>,<term>))");
    PeditorToFront0 = factory.parse("rec-do(editor-to-front(<term>))");
    PsetCursorAtFocus0 = factory.parse("rec-do(set-cursor-at-focus(<term>,<term>))");
    Pmessage0 = factory.parse("rec-do(message(<str>))");
    PnewGraph0 = factory.parse("rec-do(new-graph(<term>))");
    PdeleteModules0 = factory.parse("rec-do(delete-modules(<term>))");
    PdisplayGraph0 = factory.parse("rec-do(display-graph(<str>,<term>))");
    PsetActions0 = factory.parse("rec-do(set-actions(<term>,<term>))");
    PclearHistory0 = factory.parse("rec-do(clear-history)");
    PgraphLayouted0 = factory.parse("rec-do(graph-layouted(<str>,<term>))");
    Perrorf0 = factory.parse("rec-do(errorf(<str>,<term>))");
    Pwarning0 = factory.parse("rec-do(warning(<str>))");
    Perror0 = factory.parse("rec-do(error(<str>))");
    PbuttonsFound0 = factory.parse("rec-do(buttons-found(<term>,<str>,<term>))");
    PclearFocus0 = factory.parse("rec-do(clear-focus(<term>))");
    PaddStatusf0 = factory.parse("rec-do(add-statusf(<term>,<str>,<term>))");
    PmoduleInfo0 = factory.parse("rec-do(module-info(<str>,<term>))");
    PendStatus0 = factory.parse("rec-do(end-status(<term>))");
    PrereadContents0 = factory.parse("rec-do(reread-contents(<term>))");
    PaddStatus0 = factory.parse("rec-do(add-status(<term>,<str>))");
    PshowQuestionDialog0 = factory.parse("rec-eval(show-question-dialog(<str>))");
    PshowFileDialog0 = factory.parse("rec-eval(show-file-dialog(<str>,<str>,<str>))");
    PrecAckEvent0 = factory.parse("rec-ack-event(<term>)");
    PrecTerminate0 = factory.parse("rec-terminate(<term>)");
  }

  //}}}

  //{{{  public ATerm handler(ATerm term)

  // The generic handler calls the specific handlers
  public ATerm handler(ATerm term)
  {
    List result;

    result = term.match(PgetContents0);
    if (result != null) {
      getContents((ATerm)result.get(0), (ATerm)result.get(1));
      return null;
    }
    result = term.match(PsetCursorAtLocation0);
    if (result != null) {
      setCursorAtLocation((ATerm)result.get(0), ((Integer)result.get(1)).intValue());
      return null;
    }
    result = term.match(PsetFocus0);
    if (result != null) {
      setFocus((ATerm)result.get(0), (ATerm)result.get(1));
      return null;
    }
    result = term.match(PeditFile0);
    if (result != null) {
      editFile((ATerm)result.get(0), (String)result.get(1), (String)result.get(2));
      return null;
    }
    result = term.match(Pmessagef0);
    if (result != null) {
      messagef((String)result.get(0), (ATerm)result.get(1));
      return null;
    }
    result = term.match(PkillEditor0);
    if (result != null) {
      killEditor((ATerm)result.get(0));
      return null;
    }
    result = term.match(PdisplayMessage0);
    if (result != null) {
      displayMessage((ATerm)result.get(0), (String)result.get(1));
      return null;
    }
    result = term.match(PupdateList0);
    if (result != null) {
      updateList((String)result.get(0), (String)result.get(1));
      return null;
    }
    result = term.match(PinitializeUi0);
    if (result != null) {
      initializeUi((String)result.get(0));
      return null;
    }
    result = term.match(Pwarningf0);
    if (result != null) {
      warningf((String)result.get(0), (ATerm)result.get(1));
      return null;
    }
    result = term.match(PeditorToFront0);
    if (result != null) {
      editorToFront((ATerm)result.get(0));
      return null;
    }
    result = term.match(PsetCursorAtFocus0);
    if (result != null) {
      setCursorAtFocus((ATerm)result.get(0), (ATerm)result.get(1));
      return null;
    }
    result = term.match(Pmessage0);
    if (result != null) {
      message((String)result.get(0));
      return null;
    }
    result = term.match(PnewGraph0);
    if (result != null) {
      newGraph((ATerm)result.get(0));
      return null;
    }
    result = term.match(PdeleteModules0);
    if (result != null) {
      deleteModules((ATerm)result.get(0));
      return null;
    }
    result = term.match(PdisplayGraph0);
    if (result != null) {
      displayGraph((String)result.get(0), (ATerm)result.get(1));
      return null;
    }
    result = term.match(PsetActions0);
    if (result != null) {
      setActions((ATerm)result.get(0), (ATerm)result.get(1));
      return null;
    }
    result = term.match(PclearHistory0);
    if (result != null) {
      clearHistory();
      return null;
    }
    result = term.match(PgraphLayouted0);
    if (result != null) {
      graphLayouted((String)result.get(0), (ATerm)result.get(1));
      return null;
    }
    result = term.match(Perrorf0);
    if (result != null) {
      errorf((String)result.get(0), (ATerm)result.get(1));
      return null;
    }
    result = term.match(Pwarning0);
    if (result != null) {
      warning((String)result.get(0));
      return null;
    }
    result = term.match(Perror0);
    if (result != null) {
      error((String)result.get(0));
      return null;
    }
    result = term.match(PbuttonsFound0);
    if (result != null) {
      buttonsFound((ATerm)result.get(0), (String)result.get(1), (ATerm)result.get(2));
      return null;
    }
    result = term.match(PclearFocus0);
    if (result != null) {
      clearFocus((ATerm)result.get(0));
      return null;
    }
    result = term.match(PaddStatusf0);
    if (result != null) {
      addStatusf((ATerm)result.get(0), (String)result.get(1), (ATerm)result.get(2));
      return null;
    }
    result = term.match(PmoduleInfo0);
    if (result != null) {
      moduleInfo((String)result.get(0), (ATerm)result.get(1));
      return null;
    }
    result = term.match(PendStatus0);
    if (result != null) {
      endStatus((ATerm)result.get(0));
      return null;
    }
    result = term.match(PrereadContents0);
    if (result != null) {
      rereadContents((ATerm)result.get(0));
      return null;
    }
    result = term.match(PaddStatus0);
    if (result != null) {
      addStatus((ATerm)result.get(0), (String)result.get(1));
      return null;
    }
    result = term.match(PshowQuestionDialog0);
    if (result != null) {
      return showQuestionDialog((String)result.get(0));
    }
    result = term.match(PshowFileDialog0);
    if (result != null) {
      return showFileDialog((String)result.get(0), (String)result.get(1), (String)result.get(2));
    }
    result = term.match(PrecAckEvent0);
    if (result != null) {
      recAckEvent((ATerm)result.get(0));
      return null;
    }
    result = term.match(PrecTerminate0);
    if (result != null) {
      recTerminate((ATerm)result.get(0));
      return null;
    }

      notInInputSignature(term);
    return null;
  }

  //}}}
  //{{{  public void checkInputSignature(ATermList sigs)

  // Check the input signature
  public void checkInputSignature(ATermList sigs)
  {
    while(!sigs.isEmpty()) {
      ATermAppl sig = (ATermAppl)sigs.getFirst();
      sigs = sigs.getNext();
      if (!sigTable.containsKey(sig)) {
        // Sorry, but the term is not in the input signature!
        notInInputSignature(sig);
      }
    }
  }

  //}}}
  //{{{  void notInInputSignature(ATerm t)

  // This function is called when an input term
  // was not in the input signature.
  void notInInputSignature(ATerm t)
  {
    throw new RuntimeException("term not in input signature: "+t);
  }

  //}}}
}
