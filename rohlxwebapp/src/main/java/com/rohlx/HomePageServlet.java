package com.rohlx;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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

	static Logger log = Logger.getLogger(HomePageServlet.class.getName());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info(" Entering method : doGet");

		createTilesContainer(request).render("homeresponse", request, response);
		request.getSession().setAttribute("requestDetails", new RequestForm());

		log.info("Exiting method : doGet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

			log.info(" Entering method : doPost");

			Map<String, String> parameterMap = createMapFromRequestParameterMap(request);

			String requestNumber = EmailHelperUtil.getGeneratedRequestNumber();

			log.info("generated request no" + requestNumber);

			mapRequestToBean(parameterMap,
					HttpSessionHelper.getSession(request), requestNumber);

			if (!validateForm(request, response)) {
				log.warn("There are validation errors");
				createTilesContainer(request).render("homeresponse", request, response);
				log.info("Exiting method : before forward");

			} else {

				String requestAlreadySubmitted = (String) request.getSession()
						.getAttribute(REQUEST_ALREADY_SUBMITTED);

				if (requestAlreadySubmitted == null) {

					boolean status = false;
					// Call method to send email and other business process
					if (PropertiesHelper.getPropertiesFile()
							.getProperty("sendMail").equals("true")) {
						status = EmailNotification.sendEmail(
								"project@rohlx.com", "project@rohlx.com",
								"New Web Request : " + requestNumber,
								EmailHelperUtil.buildBody(parameterMap));
					}

					if (!status) {
						throw new RuntimeException();
					} else {
						request.getSession().setAttribute("requestNumber",
								requestNumber);
					}

					request.getSession().setAttribute(
							REQUEST_ALREADY_SUBMITTED, "false");
				}

				else if ("false".equals(requestAlreadySubmitted)) {
					request.getSession().setAttribute(
							REQUEST_ALREADY_SUBMITTED, "true");
				}
				response.sendRedirect("/servicerequest");
				log.info("Exiting method : before redirect");
			}
		
	}

	private Map<String, String> createMapFromRequestParameterMap(
			HttpServletRequest request) {
		Map<String, String[]> map = request.getParameterMap();
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("email", map.get("email")[0]);
		parameterMap.put("phone", map.get("phone")[0]);
		parameterMap.put("name", map.get("name")[0]);
		parameterMap.put("message", map.get("message")[0]);
		return parameterMap;
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

			request.setAttribute("error", error);

			return false;
		}
		return true;
	}

	private void mapRequestToBean(Map<String, String> parameterMap,
			HttpSession session, String requestNumber) {
		RequestForm rf = new RequestForm();

		rf.setName(parameterMap.get("name"));
		rf.setPhone(parameterMap.get("phone"));
		rf.setEmail(parameterMap.get("email"));
		rf.setMessage(parameterMap.get("message"));
		rf.setRequestNumber(requestNumber);

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
