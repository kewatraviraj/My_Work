/**
 * 
 */
package edu.testService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.database.DatabaseService;

/**
 * @author HP
 *
 */
public class ExampleService {
	
	public int insert(String Value) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		Connection connect = DatabaseService.getConnection();
		String sql = "insert into test_table(testvalue) values(?)";
		PreparedStatement ps = connect.prepareStatement(sql);
		
		ps.setString(1,Value);

		return (ps.executeUpdate()==1 ? 1 : 0);
	}
}
