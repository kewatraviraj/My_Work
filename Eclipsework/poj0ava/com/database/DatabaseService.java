/**
 * 
 */
package com.database;

import java.sql.*;


/**
 * @author HP
 *
 */
public class DatabaseService {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");  
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testing","root","root");
		return connection;  
	}
}
