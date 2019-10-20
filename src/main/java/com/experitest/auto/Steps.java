package com.experitest.auto;

public class Steps {

	public static void click(String obj, StepOptions options, TestContex context) {
		context.getDriver().findElement(in.Repo.obj(obj)).click();
		
	}

	public static void sendKeys(Object text, String obj, StepOptions options, TestContex context) {
		context.getDriver().findElement(in.Repo.obj(obj)).sendKeys(ParamUtils.processParams(String.valueOf(text), context.getParameters()));
	}
	
	public static void waitFor(String obj, StepOptions options, TestContex context) {
		
	}
	
	public static void verifyExist(String[] objs, StepOptions options, TestContex context) {
		
	}

}
