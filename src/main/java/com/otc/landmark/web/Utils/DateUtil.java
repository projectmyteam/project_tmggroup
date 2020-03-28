package com.otc.landmark.web.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	private static final String DATE_PATTERN = "yyyyMMdd";
	
	public static Date getSystemDateTime() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}
	
	public static String getCreateDate(Date createdDate) {
		return formatDate(createdDate, DATE_PATTERN);
	}

	private static String formatDate(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}
	
}
