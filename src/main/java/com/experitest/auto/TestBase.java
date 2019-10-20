package com.experitest.auto;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
	protected TestContex context = null;
	public TestContex getContext() {
		return context;
	}

	public void setContext(TestContex context) {
		this.context = context;
	}

	
	@BeforeMethod
	public void setUp(ITestContext testContext) throws Exception{
		DriverHelper.initDriver(testContext, this);
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		if(context != null) {
			context.getDriver().quit();
		}
	}

}
