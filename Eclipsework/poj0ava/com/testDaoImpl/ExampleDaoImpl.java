/**
 * 
 */
package com.testDaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.database.DatabaseService;
import com.testDao.ExampleDao;
import com.testPojo.ExamplePojo;

/**
 * @author HP
 *
 */
public class ExampleDaoImpl implements ExampleDao {

	@Override
	public int insert(String Value) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		Connection connect = DatabaseService.getConnection();
		String sql = "insert into test_table(testvalue) values(?)";
		PreparedStatement ps = connect.prepareStatement(sql);
		
		ps.setString(1,Value);

		return (ps.executeUpdate()==1 ? 1 : 0);
	}

}
