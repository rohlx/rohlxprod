package com.rohlx;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;

import org.apache.bval.jsr303.ApacheValidationProvider;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;

import com.rohlx.bean.RequestForm;
import com.rohlx.util.HttpSessionHelper;
import com.rohlx.util.PropertiesHelper;
import com.rohlx.util.email.EmailHelperUtil;
import com.rohlx.util.email.EmailNotification;



/**
 * Servlet implementation class HomePageServlet
 */
public class HomePageServlet extends BasePageServlet {
	private static final long serialVersionUID = 1L;
	
	Logger logger = LogManager.getLogger(HomePageServlet.class.getName());
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Tiles container 
		TilesContainer container = TilesAccess.getContainer(
		        request.getSession().getServletContext());
		container.render("homeresponse", request, response);
		
		request.getSession().setAttribute("requestDetails", new RequestForm());
		logger.log(Level.ERROR, "log4j testing here");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		mapRequestToBean(request.getParameterMap(),HttpSessionHelper.getSession(request));
		
		//
		
		String requestAlreadySubmitted = (String) request.getSession()
				.getAttribute(REQUEST_ALREADY_SUBMITTED);
		if (requestAlreadySubmitted == null) {

			// Get the body of the message
			Map<String, String[]> values = request.getParameterMap();
			
			String requestNumber = EmailHelperUtil.getGeneratedRequestNumber(); 
			
			logger.log(Level.ERROR, "generated request no"+requestNumber);
			
			
			if(!validateForm(request, response))
				return;
			
			
			
			
			// Call method to send email and other business process
			if(PropertiesHelper.getPropertiesFile().getProperty("sentMail").equals("true") && !EmailNotification.sendEmail(
					"mgmuhilan@gmail.com",
					"project@rohlx.com",
					"New Web Request : "
							+ requestNumber,
					EmailHelperUtil.buildBody(values)))
			{
				throw new RuntimeException();
			}
			else
			{
				request.getSession().setAttribute("requestNumber",requestNumber);
			}

			request.getSession().setAttribute(REQUEST_ALREADY_SUBMITTED,
					"false");
			
			
			logger.log(Level.ERROR, "requestform object"+((RequestForm)request.getSession().getAttribute("requestDetails")).getName());
			
			logger.log(Level.ERROR, "requestform object"+((RequestForm)request.getSession().getAttribute("requestDetails")).getEmail());
			logger.log(Level.ERROR, "requestform object"+((RequestForm)request.getSession().getAttribute("requestDetails")).getMessage());
			logger.log(Level.ERROR, "requestform object"+((RequestForm)request.getSession().getAttribute("requestDetails")).getPhone());
			
		} else if ("false".equals(requestAlreadySubmitted)) {
			request.getSession()
					.setAttribute(REQUEST_ALREADY_SUBMITTED, "true");
		}
		response.sendRedirect("/servicerequest");
	}

	private boolean validateForm(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ValidatorFactory vf = 
			    Validation.byProvider(ApacheValidationProvider.class).configure().buildValidatorFactory();
		
		Set<ConstraintViolation<RequestForm>> results = vf.getValidator().validate((RequestForm)HttpSessionHelper.getSession(request).getAttribute("requestDetails"), Default.class);
		
		if(results!=null && results.size() >0)
		{
			Map<String,String> error = new HashMap<String,String>(results.size());
			for(ConstraintViolation<RequestForm> result : results)
			{
				if(error.containsKey(result.getPropertyPath()))
				{
					error.put(result.getPropertyPath().toString(), error.get(result.getPropertyPath().toString())+"<br/>"+result.getMessage());
				}
				else
				{
					error.put(result.getPropertyPath().toString(),result.getMessage());
				}
			}
			
			HttpSessionHelper.getSession(request).setAttribute("error", error);
			logger.log(Level.ERROR, "results"+error);
			response.sendRedirect("/home");
			return false;
		}
		return true;
	}

	private void mapRequestToBean(Map<String, String[]> parameterMap,
			HttpSession session) {
		RequestForm rf = new RequestForm();
		rf.setName(parameterMap.get("name")[0]);
		rf.setPhone(parameterMap.get("phone")[0]);
		rf.setEmail(parameterMap.get("email")[0]);
		rf.setMessage(parameterMap.get("message")[0]);
		
		session.setAttribute("requestDetails", rf);
		
	}

}
