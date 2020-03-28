package com.otc.landmark.web.Utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoadMessageUtil {
	
	private static final Log log = LogFactory.getLog(LoadMessageUtil.class);
	
	public static String getMessage(String errorCode) {
		Properties props = new Properties();
		InputStream is = LoadMessageUtil.class.getClassLoader().getResourceAsStream("message.properties");
		
		try {
			if(is != null) {
				props.load(is);
			}
		} catch (IOException e) {
			log.error("Error when read message.properties");
			e.printStackTrace();
		}finally {
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		String errorDesc = props.getProperty(errorCode);
		
		return Utility.checkNull(errorDesc); 
	}
	
}
