package com.revature.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.beans.parties.User;
import com.revature.beans.things.Car;
import com.revature.things.logins.Account;
import com.revature.utilities.ConnectionFactory;

public class UsersDAOImp {
	//----------------------------
	//	Create
	//----------------------------
	//addUser	
	public void addUser(int userDriversID, String password, int accountType) {
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "INSERT INTO Users (userDriversID, password, type) VALUES (?, ?, ?);";
			PreparedStatement ps_addUser = conn.prepareStatement(sql);
			ps_addUser.setInt(1, userDriversID);
			ps_addUser.setString(2, password);
			ps_addUser.setInt(3, accountType);
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		};
	}
	
	//----------------------------
	//	Read
	//----------------------------
	//getUser (DriversID)
	/**
	 * Required by program to get the associated lot, payments, contracts
	 * Login is done in the ConnectionFactory
	 */
	public String getUserPassword(int driversID){
		//TODO
		//public  
		try(Connection conn = ConnectionFactory.getConnection()){
			//Prepare Statement
			String psql = "SELECT * FROM Users WHERE password=?";
			PreparedStatement ps = conn.prepareStatement(psql);
			ps.setInt(1, driversID);
			//Get Resulting Data
			ResultSet rs = ps.executeQuery();
			rs.first();
			return rs.getString("Password");
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		};
		return null;	
	}
	
		
	
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

