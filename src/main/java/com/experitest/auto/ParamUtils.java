package com.experitest.auto;

import java.util.Map;

public class ParamUtils {
	public static String processParams(String param, Map<String,String> parameters) {
		String currentParam = param;
		while(true) {
			int startIndex = currentParam.indexOf("${");
			if(startIndex < 0) {
				break;
			}
			int endIndex = currentParam.indexOf("}", startIndex);
			if(endIndex < 0) {
				break;
			}
			String varName = currentParam.substring(startIndex + 2, endIndex);
			if(parameters.containsKey(varName)) {
				currentParam = currentParam.substring(0, startIndex) + parameters.get(varName) + currentParam.substring(endIndex + 1);
			}
		}
		return currentParam;
	}

}
