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
import org.apache.log4j.Logger;

import com.rohlx.bean.RequestForm;
import com.rohlx.dao.MongoDBDAO;
import com.rohlx.util.HttpSessionHelper;
import com.rohlx.util.PropertiesHelper;
import com.rohlx.util.email.EmailHelperUtil;
import com.rohlx.util.email.EmailNotification;

/**
 * Servlet implementation class HomePageServlet
 */
public class HomePageServlet extends BasePageServlet {
	private static final long serialVersionUID = 1L;

	 static Logger log = Logger.getLogger(
			 HomePageServlet.class.getName());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info(" Entering method : doGet");
		
		createTilesContainer(request).render("homeresponse", request,response);

		log.info("Exiting method : doGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		log.info(" Entering method : doPost");

		mapRequestToBean(request.getParameterMap(),
				HttpSessionHelper.getSession(request));

		//

		String requestAlreadySubmitted = (String) request.getSession()
				.getAttribute(REQUEST_ALREADY_SUBMITTED);
		if (requestAlreadySubmitted == null) {

			// Get the body of the message
			Map<String, String[]> values = request.getParameterMap();

			String requestNumber = EmailHelperUtil.getGeneratedRequestNumber();

//			logger.log(Level.ERROR, "generated request no" + requestNumber);

			if (!validateForm(request, response)) {
//				logger.log(Level.ERROR, "There are validation errors");
				return;
			}

			// StoreServiceRequestRESTClient.storeRequest(requestNumber,
			// createMap(values));
			//
			// persistRecord(request.getParameterMap());
			boolean status = false;
			// Call method to send email and other business process
			if (PropertiesHelper.getPropertiesFile().getProperty("sendMail")
					.equals("true")) {
				status = EmailNotification.sendEmail("mgmuhilan@gmail.com",
						"project@rohlx.com", "New Web Request : "
								+ requestNumber,
						EmailHelperUtil.buildBody(values));
			}

			if (!status) {
				throw new RuntimeException();
			} else {
				request.getSession().setAttribute("requestNumber",
						requestNumber);
			}

			request.getSession().setAttribute(REQUEST_ALREADY_SUBMITTED,
					"false");

		} else if ("false".equals(requestAlreadySubmitted)) {
			request.getSession()
					.setAttribute(REQUEST_ALREADY_SUBMITTED, "true");
		}
		response.sendRedirect("/servicerequest");
		log.info("Exiting method : doPost");
	}

	private void persistRecord(Map inputs) {
		MongoDBDAO.insertRequestData(inputs);

	}

	private boolean validateForm(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ValidatorFactory vf = Validation
				.byProvider(ApacheValidationProvider.class).configure()
				.buildValidatorFactory();

		Set<ConstraintViolation<RequestForm>> results = vf.getValidator()
				.validate(
						(RequestForm) HttpSessionHelper.getSession(request)
								.getAttribute("requestDetails"), Default.class);

		if (results != null && results.size() > 0) {
			Map<String, String> error = new HashMap<String, String>(
					results.size());
			for (ConstraintViolation<RequestForm> result : results) {
				if (error.containsKey(result.getPropertyPath())) {
					error.put(result.getPropertyPath().toString(),
							error.get(result.getPropertyPath().toString())
									+ "<br/>" + result.getMessage());
				} else {
					error.put(result.getPropertyPath().toString(),
							result.getMessage());
				}
			}

			HttpSessionHelper.getSession(request).setAttribute("error", error);
//			logger.log(Level.ERROR, "results" + error);
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

	private Map<String, String> createMap(Map<String, String[]> parameterMap) {
		Map<String, String> map = new HashMap<String, String>();
		for (String key : parameterMap.keySet()) {
			map.put(key, parameterMap.get(key)[0]);
		}
		return map;
	}
}
