package com.revature.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.parties.User;
import com.revature.things.Car;
import com.revature.things.logins.Account;
import com.revature.utilities.ConnectionFactory;

public class UsersDAO {
	//----------------------------
	//	Create
	//----------------------------
	//addUser	
	public void addUser(Account user) {
		try(Connection conn = ConnectionFactory.getConnection()){
			//lotID is in the Cars table, so I can then get other data about a car, 
			//and then contractID and offerID
//			String sql = "INSERT INTO Users (userID, userPassword, userType, fk_lotID) VALUES (?, ?, ?, ?, ?)";
			String sql = "INSERT INTO Users (userID, userPassword, userType) VALUES (?, ?, ?);";
			PreparedStatement ps_addUser = conn.prepareStatement(sql);
			ps_addUser.setInt(1, user.getUserID());
			ps_addUser.setInt(2, user.getPassword().hashCode());
			ps_addUser.setInt(3, user.getAccountType());
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		};
	
	//----------------------------
	//	Read
	//----------------------------
	//getUser (DriversID)
	/**
	 * Required by program to get the associated lot, payments, contracts
	 * Login is done in the ConnectionFactory
	 */
		//TODO
		//public  
		try(Connection conn = ConnectionFactory.getConnection()){
			//lotID is in the Cars table, so I can then get other data about a car, 
			//and then contractID and offerID
			String sql = "SELECT * FROM Users";
			Statement s = conn.createStatement();
			PreparedStatement ps_addUser = conn.prepareStatement(sql);
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		};	
		
	//getAllUsers ()
	/**
	 * 
	 */
	
		
	
	//(deprecated)- not core requirement of project
	//----------------------------
	//	Update
	//----------------------------
	//updateUser (DriversID, Password)
//	public resetPassword(int driversID, int pwHash) {
//		
//	}
		
	
	//(deprecated)- not core requirement of project
	//----------------------------
	//	Delete
	//----------------------------
	//removeUser
	
		
	}
}
