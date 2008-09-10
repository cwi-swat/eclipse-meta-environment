package org.meta_environment.eclipse.focus;

import org.eclipse.core.runtime.IPath;
import org.eclipse.imp.editor.UniversalEditor;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.text.ISynchronizable;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.meta_environment.eclipse.Activator;
import org.meta_environment.eclipse.Tool;

import aterm.ATerm;
import aterm.ATermAppl;
import aterm.ATermInt;

public class SelectionTrackerTool extends Tool{
	private final static String TOOL_NAME = "selection-tracker";
	private final static String FOCUS_ANNOTATION = Activator.PLUGIN_ID+".focus-annotation";
	
	private volatile static SelectionTrackerTool instance = null;
	
	private final SelectionChangeListener selectionChangeListener;
	private volatile Annotation currentFocus;
	
	private volatile String focussedSort;
	private volatile Position focussedPosition;
	
	private SelectionTrackerTool(){
		super(TOOL_NAME);
		
		selectionChangeListener = new SelectionChangeListener(this);
		currentFocus = null;
	}
	
	private void init(){
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
		if(workbenchWindow != null){
			ISelectionService selectionService = workbenchWindow.getSelectionService();
			selectionService.addPostSelectionListener(selectionChangeListener);
		}
	}
	
	public static SelectionTrackerTool getInstance(){
		if(instance == null){
			synchronized(SelectionTrackerTool.class){
				if(instance == null){ // DCL works on volatile fields.
					instance = new SelectionTrackerTool();
					instance.connect();
					instance.init();
				}
			}
		}
		
		return instance;
	}
	
	public String getFocussedSort(){
		return focussedSort;
	}
	
	public Position getFocussedPosition(){
		return focussedPosition;
	}
	
	public void updateSelection(String sort, ATermAppl focus, UniversalEditor editor){
		clearAnnotation(editor);
		updateSort(sort, editor);
		updateAnnotation(focus, sort, editor);
	}
	
	private void setSelection(int offset, int length, String sort, UniversalEditor editor){
		setFocusAnnotation(offset, length, sort, editor);
		editor.selectAndReveal(offset, length);
	}
	
	private void clearFocusAnnotation(UniversalEditor editor){
		IDocumentProvider documentProvider = editor.getDocumentProvider();
		IAnnotationModel annotationModel = documentProvider.getAnnotationModel(editor.getEditorInput());
		
		// Lock on the annotation model
		Object lockObject = ((ISynchronizable) annotationModel).getLockObject();
		synchronized(lockObject){
			if(currentFocus != null) annotationModel.removeAnnotation(currentFocus);
		}
	}
	
	private void setFocusAnnotation(int focusOffset, int focusLength, String sort, UniversalEditor editor){
		IDocumentProvider documentProvider = editor.getDocumentProvider();
		IAnnotationModel annotationModel = documentProvider.getAnnotationModel(editor.getEditorInput());
		
		// Lock on the annotation model
		Object lockObject = ((ISynchronizable) annotationModel).getLockObject();
		synchronized(lockObject){
			if(currentFocus != null) annotationModel.removeAnnotation(currentFocus);
			
			currentFocus = new Annotation(FOCUS_ANNOTATION, false, sort);
			
			focussedSort = sort;
			focussedPosition = new Position(focusOffset, focusLength);
			
			annotationModel.addAnnotation(currentFocus, focussedPosition);
		}
	}
	
	private void updateSort(String sort, UniversalEditor editor){
		IStatusLineManager statusLine = editor.getEditorSite().getActionBars().getStatusLineManager();
		statusLine.setMessage("Sort: "+sort);
	}
	
	private void clearAnnotation(UniversalEditor editor){
		clearFocusAnnotation(editor);
	}
	
	private void updateAnnotation(ATermAppl focus, String sort, UniversalEditor editor){
		int focusOffset = ((ATermInt) focus.getArgument(4)).getInt();
		int focusLength = ((ATermInt) focus.getArgument(5)).getInt();
		
		setFocusAnnotation(focusOffset, focusLength, sort, editor);
		//if(setSelection) setSelection(focusOffset, 0, sort, editor); // Temp test
	}
	
	private static class SelectionChangeListener implements ISelectionListener{
		private final SelectionTrackerTool selectionTrackerTool;
		
		public SelectionChangeListener(SelectionTrackerTool selectionTrackerTool){
			super();
			
			this.selectionTrackerTool = selectionTrackerTool;
		}

		public void selectionChanged(final IWorkbenchPart part, ISelection selection){
			if(selection instanceof ITextSelection){
				final ITextSelection textSelection = (ITextSelection) selection;
				
				if(part instanceof UniversalEditor){
					UniversalEditor editor = (UniversalEditor) part;
					
					selectionTrackerTool.clearAnnotation(editor);
					if(textSelection.getLength() != 0) return;
					
					IParseController parseController = editor.getParseController();
					Object ast = parseController.getCurrentAst();
					if(ast instanceof ATerm){
						ATerm parseTree = (ATerm) ast;
						IPath path = parseController.getPath();
						
						int startLine = textSelection.getStartLine();
						int endLine = textSelection.getEndLine();
						int offset = textSelection.getOffset();
						int length = textSelection.getLength();
						
						ATerm selected = factory.make("selected(<term>, <str>, <int>, <int>, <int>, <int>)", parseTree, path.toOSString(), Integer.valueOf(startLine), Integer.valueOf(endLine), Integer.valueOf(offset), Integer.valueOf(length));
						
						ATermAppl selectedArea = selectionTrackerTool.sendRequest(selected);
						String sort = ((ATermAppl) selectedArea.getArgument(0)).getName();
						ATermAppl focus = (ATermAppl) selectedArea.getArgument(1);

						selectionTrackerTool.updateSelection(sort, focus, editor);
					}
				}
			}
		}
	}
}
