package toolbus_ide;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.imp.builder.BuilderBase;
import org.eclipse.imp.language.Language;
import org.eclipse.imp.language.LanguageRegistry;
import org.eclipse.imp.runtime.PluginBase;

import toolbus.AtomSet;
import toolbus.ToolBus;
import toolbus.atom.Atom;
import toolbus.atom.msg.RecMsg;
import toolbus.atom.msg.SndMsg;
import toolbus.atom.note.SndNote;
import toolbus.atom.note.Subscribe;
import toolbus.exceptions.SyntaxErrorException;
import toolbus.exceptions.ToolBusException;
import toolbus.exceptions.ToolBusExecutionException;
import toolbus.matching.MatchStore;
import toolbus.parsercup.PositionInformation;
import toolbus.parsercup.parser;
import toolbus.parsercup.parser.UndeclaredVariableException;
import toolbus.process.ProcessDefinition;
import toolbus.process.ProcessExpression;
import toolbus_ide.editor.ParseController;
import aterm.ATerm;

public class Builder extends BuilderBase {
    public static final String BUILDER_ID = Activator.kPluginID + ".builder";

    public static final String LANGUAGE_NAME = Activator.kLanguageName;

    public static final Language LANGUAGE = LanguageRegistry.findLanguage(LANGUAGE_NAME);

    public Builder() {
    	super();
	}
    
    protected String getErrorMarkerID(){
        return IMarker.PROBLEM;
    }

    protected String getWarningMarkerID(){
        return IMarker.PROBLEM;
    }

    protected String getInfoMarkerID(){
        return IMarker.PROBLEM;
    }

    protected boolean isSourceFile(IFile file){
        IPath path = file.getRawLocation();
        if (path == null) return false;

        String pathString = path.toString();
        if(pathString.indexOf("/bin/") != -1) return false;

        return LANGUAGE.hasExtension(path.getFileExtension());
    }

    protected boolean isNonRootSourceFile(IFile resource){
        return false;
    }

    protected void collectDependencies(IFile file){
    }

    protected boolean isOutputFolder(IResource resource){
        return resource.getFullPath().lastSegment().equals("bin");
    }
    
    protected void compile(final IFile file, IProgressMonitor monitor){
    	// Clear the old markers for this file.
    	ErrorHandler.clearMarkers(file);
	    
    	String filename = file.getLocation().toOSString();
    	String[] includePath = ParseController.buildIncludePath();
    	ToolBus toolbus = new ToolBus(includePath);
    	
		try{
			parser parser_obj = new parser(new HashSet<String>(), new ArrayList<ATerm>(), filename, new FileReader(filename), toolbus);
			parser_obj.parse();
			
			findDeadCommunicationAtoms(file, toolbus);
		}catch(SyntaxErrorException see){ // Parser.
			ErrorHandler.addProblemMarker(file, see.position, see.line, see.column, "Syntax error");
		}catch(UndeclaredVariableException uvex){ // Parser.
			ErrorHandler.addProblemMarker(file, uvex.position, uvex.line, uvex.column, "Undeclared variable");
		}catch(ToolBusExecutionException e){
			PositionInformation p = e.getPositionInformation();
			ErrorHandler.addProblemMarker(file, p.getOffset(), 0, 0, e.getMessage());
		}catch(ToolBusException e){
			ErrorHandler.addProblemMarker(file, 0, 0, 0, e.getMessage());
		}catch(Error e){ // Scanner.
			ErrorHandler.addProblemMarker(file, 0, 0, 0, e.getMessage());
		}catch(Exception ex){ // Something else.
			ErrorHandler.addProblemMarker(file, 0, 0, 0, ex.getMessage());
		}
    }
    
    private void findDeadCommunicationAtoms(IFile file, ToolBus toolbus){
    	final List<Atom> atomSignature = new ArrayList<Atom>();
		List<ProcessDefinition> processDefinitions = toolbus.getProcessDefinitions();
		Iterator<ProcessDefinition> processDefinitionsIterator = processDefinitions.iterator();
		while(processDefinitionsIterator.hasNext()){
			ProcessDefinition processDefinition = processDefinitionsIterator.next();
			
			ProcessExpression originalProcessExpression = processDefinition.getOriginalProcessExpression();
			AtomSet atoms = originalProcessExpression.getAtoms();
			Iterator<Atom> atomSetIterator = atoms.iterator();
			while(atomSetIterator.hasNext()){
				Atom a = atomSetIterator.next();
				atomSignature.add(a);
			}
		}
		
		MatchStore matchStore = new MatchStore(toolbus.getTBTermFactory());
		matchStore.initialize(atomSignature);
		
		List<RecMsg> partnerlessReceiveMessages = matchStore.findPartnerLessReceiveMessageAtoms();
		Iterator<RecMsg> partnerlessReceiveMessagesIterator = partnerlessReceiveMessages.iterator();
		while(partnerlessReceiveMessagesIterator.hasNext()){
			RecMsg recMsg = partnerlessReceiveMessagesIterator.next();
			PositionInformation posInfo = recMsg.getPosInfo();
			ErrorHandler.addWarningMarker(file, 0, posInfo.getBeginLine(), posInfo.getBeginColumn(), "Potentially partnerless communication atom");
		}
		
		List<SndMsg> partnerlessSendMessages = matchStore.findPartnerlessSendMessageAtoms();
		Iterator<SndMsg> partnerlessSendMessagesIterator = partnerlessSendMessages.iterator();
		while(partnerlessSendMessagesIterator.hasNext()){
			SndMsg sndMsg = partnerlessSendMessagesIterator.next();
			PositionInformation posInfo = sndMsg.getPosInfo();
			ErrorHandler.addWarningMarker(file, 0, posInfo.getBeginLine(), posInfo.getBeginColumn(), "Potentially partnerless communication atom");
		}
		
		List<SndNote> partnerlessSendNotes = matchStore.findPartnerlessSendNoteAtoms();
		Iterator<SndNote> partnerlessSendNotesIterator = partnerlessSendNotes.iterator();
		while(partnerlessSendNotesIterator.hasNext()){
			SndNote sndNote = partnerlessSendNotesIterator.next();
			PositionInformation posInfo = sndNote.getPosInfo();
			ErrorHandler.addWarningMarker(file, 0, posInfo.getBeginLine(), posInfo.getBeginColumn(), "Potentially partnerless communication atom");
		}
		
		List<Subscribe> partnerlessSubscribes = matchStore.findPartnerlessSubscribeAtoms();
		Iterator<Subscribe> partnerlessSubscribesIterator = partnerlessSubscribes.iterator();
		while(partnerlessSubscribesIterator.hasNext()){
			Subscribe subscribe = partnerlessSubscribesIterator.next();
			PositionInformation posInfo = subscribe.getPosInfo();
			ErrorHandler.addWarningMarker(file, 0, posInfo.getBeginLine(), posInfo.getBeginColumn(), "Potentially partnerless communication atom");
		}
    }
    
	protected PluginBase getPlugin(){
		return Activator.getInstance();
	}
}
