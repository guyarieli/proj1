package com.experitest.auto;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EnvReaderListener implements ISuiteListener, ITestListener {

    @Override
    public void onStart(ISuite suite) {
    	File configFile = new File("config.properties");
    	if(configFile.exists()) {
    		Properties prop = new Properties();
    		FileReader reader = null;
    		try {
	    		reader = new FileReader(configFile);
	    		prop.load(reader);
    		} catch (Exception e) {
    			e.printStackTrace();
    		} finally {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
    		for(Object key: prop.keySet()) {
    			System.setProperty(String.valueOf(key), prop.getProperty(String.valueOf(key)));
    		}
    	}
    }

    @Override
    public void onFinish(ISuite suite) {

    }

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		
        Map<String, String> suiteParameters = context.getSuite().getXmlSuite().getParameters();
        Map<String, String> localParameters = context.getCurrentXmlTest().getLocalParameters();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = new HashMap<>();
        
        for (Map.Entry<String, String> parameter : suiteParameters.entrySet()) {
            String env = System.getenv(parameter.getKey());
            if (env != null && ! env.trim().isEmpty()) {
                parameter.setValue(env);
            }
            if(parameter.getKey().startsWith("cap.")) {
            	map.put(parameter.getKey().substring("cap.".length()), parameter.getValue());
            }
        }
        for (Map.Entry<String, String> parameter : localParameters.entrySet()) {
            String env = System.getenv(parameter.getKey());
            if (env != null && ! env.trim().isEmpty()) {
                parameter.setValue(env);
            }
            if(parameter.getKey().startsWith("cap.")) {
            	map.put(parameter.getKey().substring("cap.".length()), parameter.getValue());
            }
        }
//        map.put("testName", context.getCurrentXmlTest().getName());
        try {
			String json = mapper.writeValueAsString(map);
			localParameters.put("userCapabilities", json);
			context.getCurrentXmlTest().setParameters(localParameters);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestStart(ITestResult testResult) {
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}
	
}