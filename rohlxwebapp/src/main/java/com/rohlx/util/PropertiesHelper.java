package com.rohlx.util;

import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PropertiesHelper {
	public static Properties prop;
	static Logger log = LogManager.getLogger(PropertiesHelper.class.getName());
	
	public static Properties loadProperties() {
		prop = new Properties();

		try {

			log.info("thread info");
			log.info(Thread.currentThread().getContextClassLoader());
			prop.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("/config.properties"));
			

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("###" + e.getMessage());
			throw new RuntimeException();

		}
		return prop;
	}
	
	public static Properties getPropertiesFile()
	{
		return (prop!=null) ? prop : loadProperties();
	}
}