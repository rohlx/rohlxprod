package com.rohlx;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;

/**
 * Servlet implementation class ServiceRequestHandler
 */
public class ServiceRequestHandler extends BasePageServlet {
	private static final long serialVersionUID = 1L;
       
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// getting their container
		TilesContainer container = TilesAccess.getContainer(
		        request.getSession().getServletContext());
		
		String requestAlreadySubmitted = (String)request.getSession().getAttribute(REQUEST_ALREADY_SUBMITTED);
		
		if("true".equals(requestAlreadySubmitted))
		{
			container.render("requestalreadysubmitted", request, response);
		}
		else if("false".equals(requestAlreadySubmitted))
		{
			container.render("serviceresponse", request, response);
		}
		
		
		
	}

}
