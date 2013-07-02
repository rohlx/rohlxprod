package com.rohlx.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HttpSessionHelper {
	
	public static HttpSession getSession(HttpServletRequest request)
	{
		return request.getSession();
	}

}
