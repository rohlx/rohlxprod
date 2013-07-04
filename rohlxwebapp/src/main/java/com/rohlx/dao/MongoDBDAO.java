package com.rohlx.dao;

import java.net.UnknownHostException;
import java.util.GregorianCalendar;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

public class MongoDBDAO {

	Logger logger = LogManager.getLogger(MongoDBDAO.class.getName());

	public static Mongo connection;

	public Mongo getConnection() {

		try {
			connection = new MongoClient();
			logger.log(Level.ERROR, connection.getVersion());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("after connection failed");
			return null;
		}
		return connection;
	}

	public static DB getDB() {
		return MongoFactory.getInstance().getDB("rohlxtest");
	}

	public static void insertRequestData(Map<String, String> inputs) {
		DBCollection collection = getDB().getCollection("requests");
		BasicDBObject request = new BasicDBObject(inputs);
		request.put("requesttime", GregorianCalendar.getInstance().getTime());
		collection.insert(request);
	}

	public long getCollectionSize() {
		DBCollection collection = getDB().getCollection("requests");
		return collection.count();
	}
}
