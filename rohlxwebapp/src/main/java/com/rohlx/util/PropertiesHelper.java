package com.rohlx.util;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PropertiesHelper {
	public static Properties prop;
	static Logger log = LogManager.getLogger(PropertiesHelper.class.getName());

	public static Properties loadProperties() {
		prop = new Properties();

		try {
			log.info("thread info"
					+ Thread.currentThread().getContextClassLoader());
			prop.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("/config.properties"));
			return prop;

		} catch (Exception e) {

			log.info("###" + e.getMessage());
			log.warn("second attempt to load  the properties file from system class loader"
					+ Thread.currentThread().getContextClassLoader());
			try {
				prop.load(Thread.currentThread().getContextClassLoader()
						.getResourceAsStream("config.properties"));
				return prop;
			} catch (IOException e1) {
				log.fatal(" attempt to load  the properties file failed", e1);
			}
			throw new RuntimeException();

		}

	}

	public static Properties getPropertiesFile() {
		return (prop != null) ? prop : loadProperties();
	}
}