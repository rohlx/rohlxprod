package com.rohlx.rest;

import java.util.Map;

import org.apache.cxf.jaxrs.client.WebClient;
import org.json.simple.JSONValue;

public class StoreServiceRequestRESTClient {
	static final String REST_URI = "http://localhost:9999/request";
	static final String ADD_PATH = "/storeRequest/";
	static final String ACCEPT_TYPE = "application/json";

	public static void storeRequest(String requestID, Map<String, String> data) {
		String jsonText = JSONValue.toJSONString(data);
		System.out.print("json as string"+jsonText);
		WebClient createReqestClient = WebClient.create(REST_URI);
		createReqestClient.path(ADD_PATH).path(requestID).accept(ACCEPT_TYPE).post(jsonText);
		System.out.print("after ws call"+createReqestClient.toString());
		
	}
}
