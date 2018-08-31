/**
 * 
 */
package com.basic.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


/**
 * @author HP
 *
 */
public class Database {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
		
		 Properties prop=new Properties();
	     InputStream input = Database.class.getClassLoader().getResourceAsStream("dbConnection.properties");
	     prop.load(input);
	     input.close();
	     
		Class.forName( prop.getProperty("driverClassName") );  
		Connection connection = DriverManager.getConnection( prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
		return connection;  
	}
}
