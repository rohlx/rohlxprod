package com.rohlx.util.email;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

public class EmailHelperUtil {

	public static String buildBody(Map<String, String[]> values) {
		String email = values.get("email")[0];
		String phone = values.get("phone")[0];
		String name = values.get("name")[0];
		String message = values.get("message")[0];

		return "Hi, Please find the message from customer " + name + "\n"
				+ message + "\n" + "Phone Number : " + phone + "\n"
				+ " Email  : " + email;
	}

	public static String getGeneratedRequestNumber() {
		StringBuilder sb = new StringBuilder();
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		sb.append(prependZeros(gc.get(Calendar.MONTH) + 1))
				.append(prependZeros(gc.get(Calendar.DATE)))
				.append(gc.get(Calendar.HOUR)).append(gc.get(Calendar.MINUTE))
				.append(gc.get(Calendar.SECOND)).append((int)Math.floor(Math.random()*10));
		System.out.println(sb.toString());
		return sb.toString();
	}

	private static String prependZeros(int i) {
		String s = new Integer(i).toString();
		return s.length() == 1 ? "0" + s : s;
	}
}
