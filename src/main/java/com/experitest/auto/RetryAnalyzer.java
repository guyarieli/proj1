package com.experitest.auto;

import java.util.Map;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	public RetryAnalyzer(){
		// init limit from project/test config
	}
	int counter = 0;
	int retryLimit = 0;
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.IRetryAnalyzer#retry(org.testng.ITestResult)
	 * 
	 * This method decides how many times a test needs to be rerun. TestNg will call
	 * this method every time a test fails. So we can put some code in here to
	 * decide when to rerun the test.
	 * 
	 * Note: This method will return true if a tests needs to be retried and false
	 * it not.
	 *
	 */

	@Override
	public boolean retry(ITestResult result) {
		Map<String, String> map = result.getTestContext().getCurrentXmlTest().getLocalParameters();
		if (counter < retryLimit) {
			counter++;
			map.put("retry", "true");
			return true;
		}
		return false;
	}
}