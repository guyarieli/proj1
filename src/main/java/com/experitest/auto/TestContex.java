package com.experitest.auto;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.remote.RemoteWebDriver;

public class TestContex {
	private RemoteWebDriver driver;
	
	private Map<String,String> parameters = new HashMap<String, String>();
	
	public String getParameter(String key) {
		String param = parameters.get(key);
		param = ParamUtils.processParams(param, parameters);
		return param;
	}
	

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public RemoteWebDriver getDriver() {
		return driver;
	}
	public void setDriver(RemoteWebDriver driver) {
		this.driver = driver;
	}
	
	
	
}
