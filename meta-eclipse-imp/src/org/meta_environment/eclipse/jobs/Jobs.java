package org.meta_environment.eclipse.jobs;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.meta_environment.eclipse.Tool;

import aterm.ATerm;

public class Jobs extends Tool {
	private static final String TOOL_NAME = "jobs-manager";

	private Map<String, ToolBusJob> jobs = new HashMap<String, ToolBusJob>();

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

	public void startJob(String message, ATerm aborter) {
		ToolBusJob job = jobs.get(message);

		if (job == null) {
			job = new ToolBusJob(message, aborter);
			job.schedule();
			jobs.put(message, job);
		} else {
			job.schedule();
		}
	}

	public void endJob(String message) {
		ToolBusJob job = jobs.get(message);
		if (job != null) {
			job.done(Status.OK_STATUS);
			job.setJobDone(true);
		}
	}

	private class ToolBusJob extends Job {
		ATerm aborter;

		public ToolBusJob(String name, ATerm aborter) {
			super(name);
			this.aborter = aborter;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof ToolBusJob) {
				return ((ToolBusJob) obj).getName().equals(this.getName());
			}
			return false;
		}

		private boolean jobDone = false;

		protected synchronized void setJobDone(boolean done) {
			jobDone = done;
		}

		protected synchronized boolean getJobDone() {
			return jobDone;
		}

		@Override
		protected IStatus run(final IProgressMonitor monitor) {

			Thread monitorPoller = new Thread(getName() + "Thread") {
				public void run() {
					try {
						while (!monitor.isCanceled() && !getJobDone()) {
							Thread.sleep(1000);
						}
						if (monitor.isCanceled()) {
							sendEvent(factory
									.make("abort-job(<term>)", aborter));
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};

			monitorPoller.start();
			return ASYNC_FINISH;
		}
	}
}
