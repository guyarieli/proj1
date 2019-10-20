package com.experitest.auto;

import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@TestInfo(name = "Test for iOS", type = TestType.IOS)
public class IOSDemoTest extends TestBase{

	@Parameters({"user", "password"})
	@Test(retryAnalyzer=RetryAnalyzer.class, groups= {"all"})
	public void test(@Optional("company")String user, @Optional("company")String password) throws Exception {
		
		Steps.sendKeys("${user}", "login.usernameTextField", new StepOptions().timeout(10000).continueOnFail(true), context);
		Steps.sendKeys("${password}", "login.passwordTextField", new StepOptions().timeout(10000).continueOnFail(true), context);
		Steps.click("login.loginButton", new StepOptions().timeout(10000).continueOnFail(true), context);
		//USER_CODE_START
		context.getDriver().findElement(By.xpath("//*[@text='logoutButton']")).click();
		//USER_CODE_END
	}



}
