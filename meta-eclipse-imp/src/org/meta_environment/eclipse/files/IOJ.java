package org.meta_environment.eclipse.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.imp.utils.StreamUtils;
import org.meta_environment.eclipse.Tool;

import aterm.ATerm;
import errorapi.Factory;
import errorapi.types.ErrorList;
import errorapi.types.SubjectList;
import errorapi.types.subject.Subject;
import errorapi.types.summary.Summary;

public class IOJ extends Tool {

	private static class InstanceKeeper {
		private static IOJ sInstance = new IOJ();
		static {
			sInstance.connect();
		}
	}

	private Factory errorFactory = errorapi.Factory.getInstance(factory);

	private IOJ() {
		super("ioj");
	}

	static public IOJ getInstance() {
		return InstanceKeeper.sInstance;
	}

	public ATerm readTextFile(String filename) {

		IFile file = ResourcesPlugin.getWorkspace().getRoot()
				.getFileForLocation(new Path(filename));
		
		try {
			InputStream fileContents = null;
			
			if (file == null) {
				/* TODO getFileContentsFromOS should be made obsolete */
				fileContents = getFileContentsFromOS(filename);
			}
			else {
				fileContents = file.getContents();
			}
			
			if (fileContents != null) {
				String text = StreamUtils.readStreamContents(fileContents);
				fileContents.close();
				return factory.make("file-contents(<str>)", text);
			} else {
				return factory.make("failure(<term>)", getErrorSummary(
						"Could not read file", filename));
			}

		} catch (CoreException e) {
			return factory.make("failure(<term>)", getErrorSummary(
					"Could not read file", filename));
		} catch (IOException e) {
			return factory.make("failure(<term>)", getErrorSummary(
					"Could not read file", filename));
		}
	};

	private InputStream getFileContentsFromOS(String filename) {
		File f = new File(filename);
		FileInputStream fis = null;
		try {
			 fis = new FileInputStream(f);
			 System.err.println("\nTODO: legacy absolute OS path used: " + filename);
		} catch (FileNotFoundException e) {
			
		}
		return fis;
	}

	private ATerm getErrorSummary(String _description, String _subject) {
		Subject subject = errorFactory.makeSubject_Subject(_subject);
		SubjectList subjects = errorFactory.makeSubjectList(subject);
		errorapi.types.error.Error error = errorFactory.makeError_Error(
				_description, subjects);
		ErrorList errors = errorFactory.makeErrorList(error);
		Summary summary = errorFactory.makeSummary_Summary("IO tool", "ioj",
				errors);
		return summary.toTerm();
	}

}
