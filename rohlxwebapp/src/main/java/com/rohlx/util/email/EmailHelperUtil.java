package com.rohlx.util.email;

import java.util.Calendar;
import java.util.Map;

public class EmailHelperUtil {
	
	public static String buildBody(Map<String, String[]> values)
	{
		String email = values.get("email")[0];
		String phone = values.get("phone")[0];
		String name =  values.get("name")[0];
		String message = values.get("message")[0];
		
		return "Hi, Please find the message from customer "+ name +"\n" + message + "\n" + "Phone Number : "+ phone + "\n"+ " Email  : "+email;
	}
	
	public static String getGeneratedRequestNumber()
	{
		return new Integer(Calendar.getInstance().MILLISECOND).toString();
	}

}
