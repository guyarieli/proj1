package com.experitest.auto;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class DriverHelper {

	public static void initDriver(ITestContext testContext, TestBase test) throws Exception {
		Properties cloudProperties = initCloudProperties();
		String userCapabilities = testContext.getCurrentXmlTest().getParameter("userCapabilities");
		TestInfo testInfo = test.getClass().getAnnotation(TestInfo.class);
		DesiredCapabilities dc =  init(userCapabilities, cloudProperties);
		TestType type = null;
		if(testInfo != null) {
			String testName = testInfo.name();
			//testContext.getCurrentXmlTest().setName(testName);
			type = testInfo.type();
			dc.setCapability("testName", testName);
		}
		
		TestContex context = new TestContex();
		URL url = new URL(getProperty("url",cloudProperties) + "/wd/hub");
		if(type == TestType.IOS) {
			context.setDriver(new IOSDriver<IOSElement>(url, dc));
		} else if(type == TestType.Android) {
			context.setDriver(new AndroidDriver<AndroidElement>(url, dc));
		} else {
			context.setDriver(new RemoteWebDriver(url, dc));
		}
		context.setParameters(testContext.getCurrentXmlTest().getAllParameters());
		test.setContext(context);
	}

	
	private static DesiredCapabilities init(String userCapabilities, Properties cloudProperties) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = mapper.readValue(userCapabilities, Map.class);

		DesiredCapabilities dc = new DesiredCapabilities(map);
		dc.setCapability("reportDirectory", "reports");
		dc.setCapability("reportFormat", "xml");
		String accessKey = getProperty("accessKey", cloudProperties);
		dc.setCapability("accessKey", accessKey);
		// In case your user is assign to a single project leave empty,
		// otherwise please specify the project name
		dc.setCapability("project", getProperty("project", cloudProperties));
		return dc;
	}

	protected static String getProperty(String property, Properties props) {
		if (System.getProperty(property) != null) {
			return System.getProperty(property);
		} else if (System.getenv().containsKey(property)) {
			return System.getenv(property);
		} else if (props != null) {
			return props.getProperty(property);
		}
		return null;
	}

	private static Properties initCloudProperties() throws FileNotFoundException, IOException {
		FileReader fr = new FileReader("cloud.properties");
		Properties cloudProperties = new Properties();
		cloudProperties.load(fr);
		fr.close();
		return cloudProperties;
	}

}
