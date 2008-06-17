package org.meta_environment.eclipse.errors;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.meta_environment.eclipse.Tool;

import aterm.ATerm;
import errorapi.Factory;
import errorapi.types.Area;
import errorapi.types.Error;
import errorapi.types.ErrorList;
import errorapi.types.Location;
import errorapi.types.Subject;
import errorapi.types.SubjectList;
import errorapi.types.Summary;

public class ErrorViewer extends Tool {
	private static final String TOOL_NAME = "error-viewer";
	private static final String PRODUCER = TOOL_NAME + ".producer";
	private static final String ERROR_ID = TOOL_NAME + ".errorid";

	private Factory eFactory;

	private static class InstanceKeeper {
		private static ErrorViewer sInstance = new ErrorViewer();
		static {
			sInstance.connect();
		}
	}

	private ErrorViewer() {
		super(TOOL_NAME);
		eFactory = Factory.getInstance(factory);

	}

	public static ErrorViewer getInstance() {
		return InstanceKeeper.sInstance;
	}

	public String getName() {
		return TOOL_NAME;
	}

	public void showFeedbackSummary(String panelId, ATerm summaryTerm) {
		Summary summary = eFactory.SummaryFromTerm(summaryTerm);
		ErrorList errors = summary.getList();

		try {
			for (; !errors.isEmpty(); errors = errors.getTail()) {
				Error error = errors.getHead();
				showError(summary, error, getSeverity(error));
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	private int getSeverity(Error error) {
		if (error.isInfo()) {
			return IMarker.SEVERITY_INFO;
		} else if (error.isWarning()) {
			return IMarker.SEVERITY_WARNING;
		} else if (error.isError()) {
			return IMarker.SEVERITY_ERROR;
		} else if (error.isFatal()) {
			return IMarker.SEVERITY_ERROR;
		}

		return IMarker.SEVERITY_INFO;
	}

	private void showError(Summary summary, Error error, int severity)
			throws CoreException {
		String desc = error.getDescription();
		SubjectList subjects = error.getList();

		for (; !subjects.isEmpty(); subjects = subjects.getTail()) {
			Subject subject = subjects.getHead();
			showSubject(summary, desc, subject, severity);
		}
	}

	private void showSubject(Summary summary, String desc, Subject subject,
			int severity) throws CoreException {
		String msg = subject.getDescription();
		Location loc = subject.getLocation();
		Area area = loc.getArea();
		String filename = loc.getFilename();

		IFile file = ResourcesPlugin.getWorkspace().getRoot()
				.getFileForLocation(new Path(filename));

		if (file != null) {
			IMarker m = file.createMarker(IMarker.PROBLEM);

			m.setAttribute(PRODUCER, summary.getProducer());
			m.setAttribute(ERROR_ID, summary.getId());

			m.setAttribute(IMarker.TRANSIENT, true);
			m.setAttribute(IMarker.CHAR_START, area.getOffset());
			m.setAttribute(IMarker.CHAR_END, area.getOffset()
					+ area.getLength());
			m.setAttribute(IMarker.MESSAGE, desc + ": " + msg);
			m.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
			m.setAttribute(IMarker.SEVERITY, severity);
			m.setAttribute(IMarker.LOCATION, "line: " + area.getBeginLine()
					+ ", column: " + area.getBeginColumn());
		}
	}

	public void refreshFeedbackSummary(String panelId, ATerm summaryTerm) {
		Summary summary = eFactory.SummaryFromTerm(summaryTerm);
		removeFeedbackSummary(summary.getId());
		showFeedbackSummary(panelId, summaryTerm);
	}

	public void removeFeedbackSummary(String panelId, String producer, String id) {
		removeFeedbackSummary(id);
	}

	public void removeFeedbackSummary(final String theId) {
		IFile file = ResourcesPlugin.getWorkspace().getRoot()
				.getFileForLocation(new Path(theId));

		// TODO: this oversimplifies the identification of summaries, and
		// assumes all summaries are identified with a file path
		// This breaks in many places, but is good enough for demo purposes
		if (file != null && file.exists()) {
			try {
				file.deleteMarkers(IMarker.PROBLEM, false, 1);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}

	public void removeFeedbackSummary(String panelId, String path) {
		removeFeedbackSummary(path);
	}
}
