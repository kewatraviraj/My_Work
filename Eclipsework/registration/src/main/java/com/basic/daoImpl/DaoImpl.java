/**
 * 
 */
package com.basic.daoImpl;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.basic.database.Database;
import com.basic.dao.Dao;
import com.basic.pojo.AddressPojo;
import com.basic.pojo.FilesPojo;
import com.basic.pojo.UserPojo;

/**
 * @author HP
 *
 */
public class DaoImpl implements Dao {
	PreparedStatement ps;
	
	public boolean saveaddress(AddressPojo Value2) throws ClassNotFoundException, SQLException, IOException {
		
		ps = Database.getConnection().prepareStatement(
				"insert into address(user_id,address_line1,address_line2,city,state,country,pincode,created_time)"
						+ "values((select max(user_id) from user),?,?,?,?,?,?,Now())");
		ps.setString(1, Value2.getAddress_line1());
		ps.setString(2, Value2.getAddress_line2());
		ps.setString(3, Value2.getCity());
		ps.setString(4, Value2.getState());
		ps.setString(5, Value2.getCountry());
		ps.setInt(6, Value2.getPincode());
		
		int result = ps.executeUpdate();
		ps.close();
		Database.getConnection().close();
		return (result == 1 ? true : false);
	}
	
	public boolean savefile(FilesPojo fileValue) throws ClassNotFoundException, SQLException, IOException {
		ps = Database.getConnection().prepareStatement("insert into files(file_type,file,created_time) values(?,?,Now())");
		ps.setString(1, fileValue.getFile_type());
		ps.setBlob(2, fileValue.getFile());
		
		int result = ps.executeUpdate();
		ps.close();
		Database.getConnection().close();
		return (result == 1 ? true : false);
	}

	@Override
	public boolean insert(UserPojo Value1,AddressPojo Value2) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		//boolean result = false;
		
		ps = Database.getConnection().prepareStatement(
				"insert into userdata(role_id,firstname,lastname,email,mobile_no,password,gender,date_of_birth,created_time)"
						+ "values(?,?,?,?,?,?,?,?,Now())");
		ps.setInt(1, Value1.getRole_id());
		ps.setString(2, Value1.getFirstname());
		ps.setString(3, Value1.getLastname());
		ps.setString(4, Value1.getEmail());
		ps.setLong(5, Value1.getMobile_no());
		ps.setString(6, Value1.getPassword());
		ps.setString(7, Value1.getGender());
		ps.setString(8, Value1.getDate_of_birth());
		
		int result = ps.executeUpdate();
		/*if(ps.executeUpdate()==1) {
			result = save(Value2);
		}*/
		ps.close();
		Database.getConnection().close();
		return ( result == 1 ? true : false);
	}

	@Override
	public List<UserPojo> getAllUser() throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		List<UserPojo> users = new ArrayList<UserPojo>();
		
		Statement stat = Database.getConnection().createStatement();
		ResultSet result = stat.executeQuery("select * from userdata");
		
		UserPojo user;
		while(result.next()) {
			user = new UserPojo();
			user.setUser_id(result.getInt(1));
			user.setRole_id(result.getInt(2));
			user.setFirstname(result.getString(3));
			user.setLastname(result.getString(4));
			user.setEmail(result.getString(5));
			user.setMobile_no(result.getLong(6));
			user.setPassword(result.getString(7));
			user.setGender(result.getString(8));
			user.setDate_of_birth(result.getString(9));
			user.setCreated_time(result.getString(10));
			user.setUpdate_by(result.getInt(11));
			user.setUpdate_time(result.getString(12));
			
			users.add(user);
		}
		result.close();
		stat.close();
		Database.getConnection().close();
		return users;
	}
	
	public List<AddressPojo> getAddress() throws ClassNotFoundException, SQLException, IOException{
		List<AddressPojo> allAddress = new ArrayList<AddressPojo>();
		
		Statement stat = Database.getConnection().createStatement();
		ResultSet result = stat.executeQuery("select * from address");
		
		AddressPojo address;
		while(result.next()) {
			address = new AddressPojo();
			address.setAddress_id(result.getInt(1));
			address.setUser_id(result.getInt(2));
			address.setAddress_line1(result.getString(3));
			address.setAddress_line2(result.getString(4));
			address.setCity(result.getString(5));
			address.setState(result.getString(6));
			address.setCountry(result.getString(7));
			address.setPincode(result.getInt(8));
			address.setCreated_time(result.getString(9));
			address.setUpdate_by(result.getInt(10));
			address.setUpdate_time(result.getString(11));
		
			allAddress.add(address);
		}
		result.close();
		stat.close();
		Database.getConnection().close();
		return allAddress;	
	}
	
	@Override
	public List<FilesPojo> getFiles() throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		List<FilesPojo> allfiles = new ArrayList<FilesPojo>();
		
		Statement stat = Database.getConnection().createStatement();
		ResultSet result = stat.executeQuery("select * from files");
		
		FilesPojo file;
		while(result.next()) {
			file = new FilesPojo();
			file.setFile_id(result.getInt(1));
			file.setFile_type(result.getString(2));
					
			file.setFile(result.getBinaryStream(3));
			file.setCreated_time(result.getString(4));
			file.setUpdate_by(result.getInt(5));
			file.setUpdate_time(result.getString(6));
			
			allfiles.add(file);
		}
		return allfiles;
	}
	
	@Override
	public boolean updateUser(UserPojo userValue)
			throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		ps = Database.getConnection().prepareStatement("update userdata set firstname = ?, lastname = ?, email = ?, mobile_no = ?, password = ?, "
				+ "gender = ?, date_of_birth= ?, update_by = ?, update_time = Now() Where user_id = ");
		ps.setString(1, userValue.getFirstname());
		ps.setString(2, userValue.getLastname());
		ps.setString(3, userValue.getEmail());
		ps.setLong(4, userValue.getMobile_no());
		ps.setString(5, userValue.getPassword());
		ps.setString(6, userValue.getGender());
		ps.setString(7, userValue.getDate_of_birth());
		ps.setInt(8, userValue.getUpdate_by());
		
		int result = ps.executeUpdate();
		return ( result == 1 ? true : false);
	}
	
	@Override
	public boolean updateAddress(AddressPojo Value2)
			throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		ps = Database.getConnection().prepareStatement("update address set address_line1 = ?, address_line2 = ?,"
				     + " city = ?, state = ?, country = ?, pincode = ?, update_by = ?, updae_time = Now() where address_id = ");

		ps.setString(1, Value2.getAddress_line1());
		ps.setString(2, Value2.getAddress_line2());
		ps.setString(3, Value2.getCity());
		ps.setString(4, Value2.getState());
		ps.setString(5, Value2.getCountry());
		ps.setInt(6, Value2.getPincode());
		ps.setInt(7, Value2.getUpdate_by());
		
		int result = ps.executeUpdate();
		ps.close();
		return (result == 1 ? true : false);
	}
	
	@Override
	public boolean updateFile(FilesPojo file) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		ps = Database.getConnection().prepareStatement("update files file_type = ?, file = ?, update_by = ?, update_time = Now() where file_id = ");
		ps.setString(1, file.getFile_type());
		ps.setBlob(2, file.getFile());
		ps.setInt(3, file.getUpdate_by());
		
		int result = ps.executeUpdate(); 
		return (result == 1 ? true : false);
	}

	@Override
	public boolean del(int id) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		Statement stat = Database.getConnection().createStatement();
		int result = stat.executeUpdate("delete from userdata where user_id="+id);
		
		stat.close();
		Database.getConnection().close();
		return (result == 1 ? true : false);
	}

	
}
