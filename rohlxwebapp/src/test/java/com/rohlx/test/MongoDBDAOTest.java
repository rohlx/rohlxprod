package com.rohlx.test;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.rohlx.dao.MongoDBDAO;

public class MongoDBDAOTest {
	private static MongoDBDAO database;
	private Map<String, String> data;

	@Before
	public void setUp() throws Exception {
		 database = new MongoDBDAO();
		 data= new HashMap<String, String>();
		 populateRequestMap(data);
		 
	}

	private void populateRequestMap(Map request)
	{
		request.put("name", "muhilan");
		request.put("email", "test@gmail.com");
		request.put("phone", "1234567890");
		request.put("message", "printer not working");
		request.put("requesttime", GregorianCalendar.getInstance().getTime());
		
	}
	
	@Test
	public void testSuccessfulConnection() {
		
//		fail("Not yet implemented");
		assertNotNull(database.getConnection());
	}
	
	@Test
	public void testUnknowHost(){
		assertNull(database.getConnection());
	}
	
	@Test
	public void testinsertData()
	{
		long beforeInsert = database.getCollectionSize();
		database.insertRequestData(data);
		long afterInsert = database.getCollectionSize();
		assertEquals(1,afterInsert-beforeInsert);
		
	}

}
