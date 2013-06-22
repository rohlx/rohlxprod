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
 * Servlet implementation class HomePageServlet
 */
public class HomePageServlet extends BasePageServlet {
	private static final long serialVersionUID = 1L;
	

       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		TilesContainer container = TilesAccess.getContainer(
		        request.getSession().getServletContext());
		
		container.render("homeresponse", request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("recieved reqeust");
		String requestAlreadySubmitted = (String) request.getSession()
				.getAttribute(REQUEST_ALREADY_SUBMITTED);
		if (requestAlreadySubmitted == null) {
			// Need to call method to send email and other business process

			request.getSession().setAttribute(REQUEST_ALREADY_SUBMITTED,
					"false");
		} else if ("false".equals(requestAlreadySubmitted)) {
			request.getSession()
					.setAttribute(REQUEST_ALREADY_SUBMITTED, "true");
		}
		response.sendRedirect("/servicerequest");
	}

}
