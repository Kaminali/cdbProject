package com.excilys.cdb.controler.validator;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.context.i18n.LocaleContextHolder;

public class DateConverter {

	public static Date stringToDate(String sDate) {
		Date convertedCurrentDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (sDate == null) {
			sDate = "0000-00-00";
			return null;
		}
		if (!sDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
			sDate = "0000-00-00";
		}
		try {
			convertedCurrentDate = sdf.parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return convertedCurrentDate;
	}

	public static String localDate(String date) {
		if (LocaleContextHolder.getLocale().toString().equals("fr")) {
			String[] splittedDate = date.split("-");
			String convertedDate = splittedDate[2] + "-" + splittedDate[1]
					+ "-" + splittedDate[0];
			return convertedDate;
		}
		return date;
	}

	public static boolean isDate(String inputString, String regex) {
		regex = "\\d{4}-\\d{2}-\\d{2}";
		if (inputString == null || inputString.isEmpty())
			return true;
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(inputString);
		if (m.find()) {
			return true;
		}
		return false;
	}
}