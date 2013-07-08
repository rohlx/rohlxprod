package com.rohlx;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.tiles.TilesContainer;
import org.apache.tiles.factory.BasicTilesContainerFactory;
import org.apache.tiles.servlet.context.ServletTilesApplicationContext;

/**
 * Servlet implementation class ServiceRequestHandler
 */
public class ServiceRequestHandler extends BasePageServlet {
	private static final long serialVersionUID = 1L;

	static Logger log = Logger.getLogger(ServiceRequestHandler.class.getName());

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.info(" Entering method : doGet");

		String requestAlreadySubmitted = (String) request.getSession()
				.getAttribute(REQUEST_ALREADY_SUBMITTED);

		if ("true".equals(requestAlreadySubmitted)) {
			createTilesContainer(request).render("requestalreadysubmitted",
					request, response);
		} else if ("false".equals(requestAlreadySubmitted)) {
			createTilesContainer(request).render("serviceresponse", request,
					response);
		}
		log.info("Exiting method : doGet");

	}

}
