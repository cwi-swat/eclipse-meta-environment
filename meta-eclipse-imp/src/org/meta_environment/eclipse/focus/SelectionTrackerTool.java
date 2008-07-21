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
	
	private SelectionTrackerTool(){
		super(TOOL_NAME);
	}
	
	public void init(){
		final SelectionTrackerTool stt = this;
		final IWorkbench workbench = PlatformUI.getWorkbench();
		// Call this stuff from a UI thread, for thread-safety reasons (yuk).
		workbench.getDisplay().asyncExec(new Runnable(){
			public void run(){
				IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
				if(workbenchWindow != null){
					ISelectionService selectionService = workbenchWindow.getSelectionService();
					selectionService.addPostSelectionListener(new SelectionChangeListener(stt));
				}
			}
		});
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
	
	private static class SelectionChangeListener implements ISelectionListener{
		private final SelectionTrackerTool selectionTrackerTool;
		
		private volatile Annotation currentFocus;
		
		public SelectionChangeListener(SelectionTrackerTool selectionTrackerTool){
			super();
			
			this.selectionTrackerTool = selectionTrackerTool;
			
			currentFocus = null;
		}

		public void selectionChanged(IWorkbenchPart part, ISelection selection){
			if(selection instanceof ITextSelection){
				ITextSelection textSelection = (ITextSelection) selection;
				
				if(part instanceof UniversalEditor){
					UniversalEditor editor = (UniversalEditor) part;
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

						// Sort
						updateSort(sort, editor);
						
						// Focus
						updateAnnotation(focus, sort, editor);
					}
				}
			}
		}
		
		private void updateSort(String sort, UniversalEditor editor){
			IStatusLineManager statusLine = editor.getEditorSite().getActionBars().getStatusLineManager();
			statusLine.setMessage("Sort: "+sort);
		}
		
		private void updateAnnotation(ATermAppl focus, String sort, UniversalEditor editor){
			int focusOffset = ((ATermInt) focus.getArgument(4)).getInt();
			int focusLength = ((ATermInt) focus.getArgument(5)).getInt();
			
			IDocumentProvider documentProvider = editor.getDocumentProvider();
			IAnnotationModel annotationModel = documentProvider.getAnnotationModel(editor.getEditorInput());
			
			// Lock on the annotation model
			Object lockObject = ((ISynchronizable) annotationModel).getLockObject();
			synchronized(lockObject){
				if(currentFocus != null) annotationModel.removeAnnotation(currentFocus);
				
				currentFocus = new Annotation(FOCUS_ANNOTATION, false, sort);
				
				annotationModel.addAnnotation(currentFocus, new Position(focusOffset, focusLength));
			}
		}
	}
}
