package com.rohlx.util;

import java.util.Properties;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertiesHelper {
	public static Properties prop;
	private static Logger logger = LogManager.getLogger(PropertiesHelper.class.getName());

	public static Properties loadProperties() {
		prop = new Properties();

		try {

			logger.log(Level.ERROR, "thread info");
			logger.log(Level.ERROR, Thread.currentThread().getContextClassLoader());
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