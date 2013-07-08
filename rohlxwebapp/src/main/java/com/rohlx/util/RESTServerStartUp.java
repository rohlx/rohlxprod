package com.rohlx.util;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.rohlx.rest.server.RequestService;

public class RESTServerStartUp extends Thread{
	static Logger log = LogManager.getLogger(RESTServerStartUp.class.getName());    
	
	public RESTServerStartUp(String name)
	{
		super(name);
	}

	public void run()
	{
		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
	    sf.setResourceClasses(RequestService.class);
	    sf.setAddress("http://localhost:9999/");
	    Server server = sf.create();
	    
	    log.info("before server wait");
//	    try {
//			Thread.currentThread().wait(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	    log.info("after server wait");
//	    server.stop();
	}
	
	public static void main(String s[])
	{
		new RESTServerStartUp("stand alone").start();
	}
}
