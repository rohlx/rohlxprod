package com.rohlx.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.rohlx.util.PropertiesHelper;

public class MongoFactory {

	private static Mongo md;

	private MongoFactory() {

	}

	public static Mongo getInstance() {
		if (md == null)
			try {
				MongoCredential cred = MongoCredential.createMongoCRCredential(
						PropertiesHelper.getPropertiesFile().getProperty(
								"rohlxtestdbusername"),
						"rohlxtest",
						PropertiesHelper.getPropertiesFile().getProperty(
								"rohlxtestdbpassword").toCharArray());
				
//				MongoCredential cred = MongoCredential.createMongoCRCredential(
//						"rohlx", "rohlxtest", "rohlx123".toCharArray());
				
				List<MongoCredential> credentials = new ArrayList<MongoCredential>();
				credentials.add(cred);
							md = new MongoClient(new ServerAddress("localhost"),credentials);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return md;
	}
}
