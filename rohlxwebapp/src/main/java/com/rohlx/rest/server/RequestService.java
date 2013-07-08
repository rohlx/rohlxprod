package com.rohlx.rest.server;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.mule.DefaultMuleMessage;
import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.config.ConfigurationException;
import org.mule.api.context.MuleContextFactory;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.transport.PropertyScope;
import org.mule.context.DefaultMuleContextFactory;
import org.mule.module.client.MuleClient;

@Path("/request")
public class RequestService {

	@POST
	@Path("/storeRequest/{requestID}")
	public void createRequest(@PathParam("requestID") int requestID,String jsonAsString) {
		System.out.println(" The request id now in the rest service is "+requestID);
		System.out.println(" The request data "+jsonAsString);
		MuleContext muleContext = null;
		try {
			muleContext = new DefaultMuleContextFactory().createMuleContext();
		} catch (InitialisationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(" successful mule context  ");
		try {
		MuleMessage m = new DefaultMuleMessage(jsonAsString,muleContext); 
		MuleClient mc;
		m.setProperty("requestID", new Integer(requestID).toString(), PropertyScope.OUTBOUND);
		m.setOutboundProperty("requestIDs", "test");
		System.out.println("the mulemessage is "+m);
		
			 mc = new MuleClient(true);
			 mc.send("http://localhost:9000/mule" ,m, null, 5000);
		} catch (MuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
