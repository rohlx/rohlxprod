package com.rohlx.util.email;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

public class EmailHelperUtil {

	public static String buildBody(Map<String, String> values) {
		String email = values.get("email");
		String phone = values.get("phone");
		String name = values.get("name");
		String message = values.get("message");

		StringBuilder sb = new StringBuilder();
		sb.append("Dear Rohlx Service Engineers,").append("\n\n")
				.append("Please find the message from customer : ")
				.append(name).append(".").append("\n\n");

		sb.append("Message :").append(message).append("\n\n");
		sb.append("Phone Number :").append(phone).append("\n\n");

		if (email != null)
			sb.append("Email :").append(email).append("\n\n");

		sb.append("Thanks and regards,").append("\n");
		sb.append("Rohlx Technologies");
		return sb.toString();
	}

	public static String getGeneratedRequestNumber() {
		StringBuilder sb = new StringBuilder();
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		sb.append(prependZeros(gc.get(Calendar.MONTH) + 1))
				.append(prependZeros(gc.get(Calendar.DATE)))
				.append(prependZeros(gc.get(Calendar.HOUR)))
				.append(prependZeros(gc.get(Calendar.MINUTE)))
				.append(prependZeros(gc.get(Calendar.SECOND)))
				.append((int) Math.floor(Math.random() * 10));
		return sb.toString();
	}

	private static String prependZeros(int i) {
		String s = new Integer(i).toString();
		return s.length() == 1 ? "0" + s : s;
	}
}
