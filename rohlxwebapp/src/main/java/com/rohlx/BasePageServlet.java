package com.rohlx;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tiles.TilesApplicationContext;
import org.apache.tiles.TilesContainer;
import org.apache.tiles.factory.BasicTilesContainerFactory;
import org.apache.tiles.servlet.context.ServletTilesApplicationContext;

/**
 * Servlet implementation class BasePageServlet
 */
public class BasePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected static String REQUEST_ALREADY_SUBMITTED = "requestAlreadySubmitted";
	
	protected TilesContainer createTilesContainer(HttpServletRequest request)
	{
		// Tiles 2.2.2 way of creating container
		TilesApplicationContext tac = new ServletTilesApplicationContext(request.getSession().getServletContext());
		BasicTilesContainerFactory bc = new BasicTilesContainerFactory();
		TilesContainer container = bc.createContainer(tac);
		return container;
	}
}
