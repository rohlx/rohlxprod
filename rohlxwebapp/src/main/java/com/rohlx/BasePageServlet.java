package com.rohlx;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BasePageServlet
 */
public class BasePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected static String REQUEST_ALREADY_SUBMITTED = "requestAlreadySubmitted";
}
