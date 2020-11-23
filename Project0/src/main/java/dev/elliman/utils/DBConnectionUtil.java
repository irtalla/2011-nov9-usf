package dev.elliman.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnectionUtil {
	private static DBConnectionUtil dbcu = null;
	private static Properties properties;
	
	//Singleton design pattern
	private DBConnectionUtil() {
		properties = new Properties();
		
		try {
			InputStream dbProperties = DBConnectionUtil.class.getClassLoader()
					.getResourceAsStream("database.properties");
			properties.load(dbProperties);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static synchronized DBConnectionUtil getDBConnectionUtil() {
		if(dbcu == null) {
			dbcu = new DBConnectionUtil();
		}
		return dbcu;
	}
	
	public Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName(properties.getProperty("drv"));
			conn = DriverManager.getConnection(
					properties.getProperty("url"),
					properties.getProperty("user"),
					properties.getProperty("password"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
