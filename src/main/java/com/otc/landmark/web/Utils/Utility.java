package com.otc.landmark.web.Utils;

import org.apache.commons.lang.StringUtils;

public class Utility {
	public static String checkNull(String obj) {
		return null == obj ? "" : obj;
	}
	
	public static String limitBody(String body) {
		String limitBody = body;
		if(checkNull(limitBody).length() > 100) {
			return limitBody.substring(0, 100).concat("...");
		}
		return limitBody;
	}
	
	public static int calculateOffsetSQL(int page, int pageSize) {
		return (page - 1) * pageSize;
	}
}
