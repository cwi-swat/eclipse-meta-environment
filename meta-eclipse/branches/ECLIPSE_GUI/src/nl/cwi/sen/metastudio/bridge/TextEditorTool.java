package nl.cwi.sen.metastudio.bridge;

// Java tool interface class TextEditorTool
// This file is generated automatically, please do not edit!
// generation time: May 26, 2003 2:57:43 PM


import aterm.*;
import toolbus.*;
import java.util.*;

abstract public class TextEditorTool
  extends AbstractTool
  implements TextEditorTif
{
  // This table will hold the complete input signature
  private Map sigTable = new HashMap();

  //{{{  Patterns that are used to match against incoming terms

  private ATerm PreloadFile0;
  private ATerm PtbSetCharPos0;
  private ATerm PtbAddMenus0;
  private ATerm PtbSetMsg0;
  private ATerm PtbUnsetFocus0;
  private ATerm PtbSetFocus0;
  private ATerm PmoveEditorToFront0;
  private ATerm PtbGetFocusText0;
  private ATerm PeditFile0;
  private ATerm PrecAckEvent0;
  private ATerm PrecTerminate0;

  //}}}

  //{{{  protected TextEditorTool(ATermFactory factory)

  // Mimic the constructor from the AbstractTool class
  protected TextEditorTool(ATermFactory factory)
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
    sigTable.put(factory.parse("rec-do(<text-editor>,tb-add-menus(<list>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-eval(<text-editor>,edit-file(<str>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<text-editor>,tb-set-focus(<str>,<str>,<int>,<int>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<text-editor>,tb-unset-focus(<str>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-eval(<text-editor>,tb-get-focus-text(<str>,<int>,<int>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<text-editor>,tb-set-char-pos(<str>,<int>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<text-editor>,tb-set-msg(<str>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<text-editor>,move-editor-to-front(<str>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-do(<text-editor>,reload-file(<str>))"), new Boolean(true));
    sigTable.put(factory.parse("rec-ack-event(<text-editor>,<term>)"), new Boolean(true));
    sigTable.put(factory.parse("rec-terminate(<text-editor>,<term>)"), new Boolean(true));
  }

  //}}}
  //{{{  private void initPatterns()

  // Initialize the patterns that are used to match against incoming terms
  private void initPatterns()
  {
    PreloadFile0 = factory.parse("rec-do(reload-file(<str>))");
    PtbSetCharPos0 = factory.parse("rec-do(tb-set-char-pos(<str>,<int>))");
    PtbAddMenus0 = factory.parse("rec-do(tb-add-menus(<term>))");
    PtbSetMsg0 = factory.parse("rec-do(tb-set-msg(<str>))");
    PtbUnsetFocus0 = factory.parse("rec-do(tb-unset-focus(<str>))");
    PtbSetFocus0 = factory.parse("rec-do(tb-set-focus(<str>,<str>,<int>,<int>))");
    PmoveEditorToFront0 = factory.parse("rec-do(move-editor-to-front(<str>))");
    PtbGetFocusText0 = factory.parse("rec-eval(tb-get-focus-text(<str>,<int>,<int>))");
    PeditFile0 = factory.parse("rec-eval(edit-file(<str>))");
    PrecAckEvent0 = factory.parse("rec-ack-event(<term>)");
    PrecTerminate0 = factory.parse("rec-terminate(<term>)");
  }

  //}}}

  //{{{  public ATerm handler(ATerm term)

  // The generic handler calls the specific handlers
  public ATerm handler(ATerm term)
  {
    List result;

    result = term.match(PreloadFile0);
    if (result != null) {
      reloadFile((String)result.get(0));
      return null;
    }
    result = term.match(PtbSetCharPos0);
    if (result != null) {
      tbSetCharPos((String)result.get(0), ((Integer)result.get(1)).intValue());
      return null;
    }
    result = term.match(PtbAddMenus0);
    if (result != null) {
      tbAddMenus((ATerm)result.get(0));
      return null;
    }
    result = term.match(PtbSetMsg0);
    if (result != null) {
      tbSetMsg((String)result.get(0));
      return null;
    }
    result = term.match(PtbUnsetFocus0);
    if (result != null) {
      tbUnsetFocus((String)result.get(0));
      return null;
    }
    result = term.match(PtbSetFocus0);
    if (result != null) {
      tbSetFocus((String)result.get(0), (String)result.get(1), ((Integer)result.get(2)).intValue(), ((Integer)result.get(3)).intValue());
      return null;
    }
    result = term.match(PmoveEditorToFront0);
    if (result != null) {
      moveEditorToFront((String)result.get(0));
      return null;
    }
    result = term.match(PtbGetFocusText0);
    if (result != null) {
      return tbGetFocusText((String)result.get(0), ((Integer)result.get(1)).intValue(), ((Integer)result.get(2)).intValue());
    }
    result = term.match(PeditFile0);
    if (result != null) {
      return editFile((String)result.get(0));
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
