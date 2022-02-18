package com.entlogics.schoolsystem.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionUtil {

	// Define database properties
	private static Properties properties;
	
	private static String URL;
	
	private static String DRIVER;
	
	private static String USERNAME;
	
	private static String PASSWORD;
	
	private static Connection connection;
	
	// This method returns database connection
	public static Connection openConnection() throws IOException {
		properties = new Properties();
		
		// reading the properties file
		InputStream fileInput = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("SchoolDB.properties");
		
//		fileInput = new FileInputStream("./WEB-INF/classes/SchoolDB.properties");
		
		// loading the file
		properties.load(fileInput);
		
		// url of the database
		URL = properties.getProperty("url");
		
		// MySQL Driver
		DRIVER = properties.getProperty("driver");
		
		// Name of the database user
		USERNAME = properties.getProperty("username");
		
		// password of the database user
		PASSWORD = properties.getProperty("password");
		
		
		try {
			
			// Loading the driver
			Class.forName(DRIVER);
			
			// get the connection 
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		// return the connection
		return connection;
	}
}
