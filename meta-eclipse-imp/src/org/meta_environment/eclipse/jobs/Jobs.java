package org.meta_environment.eclipse.jobs;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.meta_environment.eclipse.Tool;

public class Jobs extends Tool {
	private static final String TOOL_NAME = "jobs-manager";

	private Map<String, ToolBusJob> jobs = new HashMap<String,ToolBusJob>();

	private static class InstanceKeeper {
		private static Jobs sInstance = new Jobs();
		static {
			sInstance.connect();
		}
	}

	private Jobs() {
		super(TOOL_NAME);
	}

	public static Jobs getInstance() {
		return InstanceKeeper.sInstance;
	}

	public String getName() {
		return TOOL_NAME;
	}

	
	
	public void startJob(String message) {
		ToolBusJob job = new ToolBusJob(message);	
		job.schedule();
		jobs.put(message, job);
	}
	
	public void endJob(String message) {
		ToolBusJob job = jobs.get(message);
		if (job != null) {
			job.done(Status.OK_STATUS);
			jobs.remove(message);
		}
	}
	
	private class ToolBusJob extends Job {
		public ToolBusJob(String name) {
			super(name);
		}

		@Override
		protected IStatus run(IProgressMonitor monitor) {
			return ASYNC_FINISH;
		}
		
		@Override
		protected void canceling() {
			done(Status.CANCEL_STATUS);
		}
	}
}
