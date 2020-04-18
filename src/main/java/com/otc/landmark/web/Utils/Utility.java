package com.otc.landmark.web.Utils;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class Utility {
	
	public static String checkNull(String obj) {
		return null == obj ? "" : obj;
	}
	
	public static boolean checkString(String obj) {
		return (obj != null && !obj.isEmpty()) ? true : false;
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
	
	public static boolean isValidEmail(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    } 
}
