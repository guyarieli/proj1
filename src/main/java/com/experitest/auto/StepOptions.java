package com.experitest.auto;

public class StepOptions {
	private int findTimeout = -1;
	private boolean continueOnFail = false;
	public StepOptions() {
		if(System.getProperties().contains("find.timeout")) {
			try {
				findTimeout = Integer.valueOf(System.getProperty("find.timeout"));
			} catch (Exception e) {
			}
		}
		if(System.getProperties().contains("test.find.timeout")) {
			try {
				findTimeout = Integer.valueOf(System.getProperty("test.find.timeout"));
			} catch (Exception e) {
			}
		}
	}
	
	public StepOptions timeout(int timeout) {
		this.findTimeout = timeout;
		return this;
	}

	public StepOptions continueOnFail(boolean b) {
		this.continueOnFail = b;
		return this;
	}

	public int getFindTimeout() {
		return findTimeout;
	}

	public boolean isContinueOnFail() {
		return continueOnFail;
	}
	
	

}
