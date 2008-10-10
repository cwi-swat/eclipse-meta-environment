package org.meta_environment.eclipse.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import nl.cwi.sen1.configapi.types.Property;
import nl.cwi.sen1.configapi.types.PropertyList;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.imp.runtime.RuntimePlugin;

import toolbus.adapter.eclipse.EclipseTool;
import aterm.ATerm;
import aterm.ATermList;
import errorapi.types.ErrorList;
import errorapi.types.Location;
import errorapi.types.Subject;
import errorapi.types.SubjectList;
import errorapi.types.Summary;

public class IOJ extends EclipseTool{
	private final static String TOOL_NAME = "ioj";

	private final static errorapi.Factory errorFactory = errorapi.Factory.getInstance(factory);
	private final static nl.cwi.sen1.configapi.Factory configFactory = nl.cwi.sen1.configapi.Factory.getInstance(factory);

	private static class InstanceKeeper{
		private final static IOJ sInstance = new IOJ();
		static{
			sInstance.connect();
		}
	}
	
	private IOJ(){
		super(TOOL_NAME);
	}

	public static IOJ getInstance(){
		return InstanceKeeper.sInstance;
	}
	
	private String readFileContents(String filename) throws IOException{
		File f = new Path(filename).toFile();
		byte[] content = new byte[(int) f.length()];
		FileInputStream fis = null;
		try{
			fis = new FileInputStream(f);
			fis.read(content);
		}finally{
			if(fis != null){
				try{
					fis.close();
				}catch(IOException ioex){
					RuntimePlugin.getInstance().logException(ioex.getMessage(), ioex);
				}
			}
		}
		
		return new String(content);
	}
	
	private ATerm makeResultMessage(Summary summary){
		if(summary != null){
			return factory.make("failure(<term>)", summary);
		}
		return factory.make("success");
	}

	private Summary createSummary(String msg, String path){
		Location location = errorFactory.makeLocation_File(path);
		Subject subject = errorFactory.makeSubject_Localized(msg, location);// TODO is this right?
		SubjectList subjects = errorFactory.makeSubjectList(subject);
		errorapi.types.error.Error error = errorFactory.makeError_Error(msg, subjects);
		ErrorList errors = errorFactory.makeErrorList(error);
		
		return errorFactory.makeSummary_Summary(TOOL_NAME, path, errors);
	}
	
	private ATerm createFileNotFoundMessage(){
		return factory.make("file-not-found");
	}
	
	private ATerm createFileFoundMessage(ATermList directories){
		return factory.make("file-found(<term>)", directories);
	}
	
	private ATerm createFilesEqualMessage(){
		return factory.make("equal");
	}
	
	private ATerm createFilesDifferentMessage(){
		return factory.make("different");
	}
	
	public ATerm removeFile(String path){
		File f = new Path(path).toFile();
		
		boolean removed = f.delete();
		if(removed){
			return makeResultMessage(null);
		}
		return createSummary("Failed to remove file", path);
	}
	
	public ATerm packTerm(ATerm term){
		return factory.make("term(<term>)", pack(term));
	}
	
	public ATerm unpackTerm(ATerm term){
		return factory.make("term(<term>)", unpack(term));
	}
	
	public ATerm readTextFile(String path){
		String contents;
		try{
			contents = readFileContents(new Path(path).toOSString());
		}catch(IOException ioex){
			return makeResultMessage(createSummary("Failed to read text file", path));
		}
		
		return factory.make("file-contents(<str>)", contents);
	}
	
	public ATerm readAndPackTerm(String path){
		ATerm content;
		try{
			content = factory.readFromFile(new Path(path).toOSString());
		}catch(IOException ioex){
			return makeResultMessage(createSummary("Failed to read packed file", path));
		}catch(RuntimeException rex){
			return makeResultMessage(createSummary("Failed to read packed file", path));
		}
		return factory.make("packed-term(<term>)", pack(content));
	}
	
	public ATerm existsFile(String path){
		File f = new Path(path).toFile();
		if(f.exists()){
			return makeResultMessage(null);
		}
		return createSummary("No such file", path);
	}

	public ATerm findFile(ATerm paths, String name, String extension){
		PropertyList searchPaths = configFactory.PropertyListFromTerm(paths);
		ATermList directories = factory.makeList();
		
		while(!searchPaths.isEmpty()){
			Property path = searchPaths.getHead();
			searchPaths = searchPaths.getTail();
			
			String pathString = path.getPath();
			File f = new Path(pathString+"/"+name+"."+extension).toFile();
			if(f.exists()){
				directories = directories.insert(factory.makeAppl(factory.makeAFun(pathString, 0, true)));
			}
		}
		
		if(!directories.isEmpty()){
			return createFileFoundMessage(directories.reverse());
		}
		return createFileNotFoundMessage();
	}
	
	public ATerm compareFiles(String p1, String p2){
		File file1 = new Path(p1).toFile();
		if(!file1.exists()){
			return makeResultMessage(createSummary("Cannot stat", p1));
		}
		
		File file2 = new Path(p2).toFile();
		if(!file2.exists()){
		    return makeResultMessage(createSummary("Cannot stat", p2));
		}
		
		if(!file1.equals(file2)){
			return createFilesDifferentMessage();
		}
		return createFilesEqualMessage();
	}
	
	public ATerm getFilename(String directory, String name, String extension){
		IPath path = new Path(directory);
		path = path.append(name + extension);
		String fileName = path.toString();
		return factory.make("filename(<str>)", fileName);
	}
	
	public ATerm getPathDirectory(String dir){
		IPath path = new Path(dir);
		int numberOfSegments = path.segmentCount();

		String directory = "";
		if (numberOfSegments > 1) {
			directory = path.removeLastSegments(1).toString();
		}
		return factory.make("directory(<str>)", directory);
	}

	public ATerm getPathFilename(String filename){
		IPath path = new Path(filename);
		String fileName = path.removeFileExtension().lastSegment();
		
		if(fileName == null) fileName = "";
		
		return factory.make("filename(<str>)", fileName);
	}
	
	public ATerm getPathExtension(String path){
		String extension = new Path(path).getFileExtension();
		
		if(extension == null) return factory.make("extension(\"\")");
		
		return factory.make("extension(<str>)", extension);
	}
	
	private String normalizePath(String path){
		IPath p = new Path(path);
		return p.toFile().getAbsolutePath();
	}
	
	public ATerm getRelativeFilename(ATerm paths, String path){
		ATerm result = null;
		PropertyList searchPaths = configFactory.PropertyListFromTerm(paths);
		
		while(!searchPaths.isEmpty() && result == null){
			Property searchPath = searchPaths.getHead();
			String pathString = searchPath.getPath();
			String normalizedSearchPath = normalizePath(pathString);
			String normalizedPath = normalizePath(path);
			
			if(normalizedSearchPath.indexOf(normalizedPath) == 0){
				String copy = normalizedPath.substring(normalizedSearchPath.length() + 1);
				result = factory.make("filename(<str>,<str>)", normalizedSearchPath, copy);
			}
			
			searchPaths = searchPaths.getTail();
		}
		
		if(result == null){
			result = factory.make("filename(<str>,<str>)", "", "");
		}
		return result;
	}
	
	private void writeToFile(String filename, String content) throws IOException{
		File f = new Path(filename).toFile();
		FileOutputStream fos = null;
		try{
			fos = new FileOutputStream(f);
			fos.write(content.getBytes());
		}finally{
			if(fos != null){
				try{
					fos.close();
				}catch(IOException ioex){
					RuntimePlugin.getInstance().logException(ioex.getMessage(), ioex);
				}
			}
		}
	}
	
	private String constructTextList(ATermList content){
		StringBuilder contentBuilder = new StringBuilder();
		
		ATermList list = content;
		while(!list.isEmpty()){
			String str = list.getFirst().toString();
			contentBuilder.append(str);
			list = list.getNext();
		}
		
		return contentBuilder.toString();
	}
	
	public ATerm writeTextList(String path, ATermList content){
		try{
			writeToFile(path, constructTextList(content));
		}catch(IOException ioex){
			return makeResultMessage(createSummary("Failed to write", path));
		}
		
		return makeResultMessage(null);
	}
}