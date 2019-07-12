package com.revature.DAOs;

import java.beans.Beans;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;

import com.revature.beans.parties.User;
import com.revature.beans.parties.UserBean;
import com.revature.beans.things.Car;
import com.revature.things.logins.Account;
import com.revature.utilities.ConnectionFactory;

public class UsersDAOImp {
	//----------------------------
	//	Create
	//----------------------------
	//addUser	
	public static void addUser(int userDriversID, String password, int accountType) {
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "INSERT INTO Users (userDriversID, password, type) VALUES (?, ?, ?);";
			PreparedStatement ps_addUser = conn.prepareStatement(sql);
			ps_addUser.setInt(1, userDriversID);
			ps_addUser.setString(2, password);
			ps_addUser.setInt(3, accountType);
			
			ps_addUser.execute();
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		};
	}
	
	//----------------------------
	//	Read
	//----------------------------
	//getUser (DriversID)
	/**
	 * @deprecated
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
	
	public static LinkedList<UserBean> getAllUsers() throws SQLException{
		UserBean user_bean;
		LinkedList<UserBean> beans = new LinkedList<>();
		
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "SELECT * FROM Users";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			
			while(rs.next()) {
				user_bean = new UserBean();
				user_bean.setPassword(rs.getString("password"));
				user_bean.setType(rs.getInt("type"));
				user_bean.setUserDriversID(rs.getInt("userdriversid"));
				beans.add(user_bean);				
			}			
		}
		return beans;
	}
	
	public static String getUserPasswordPS_noProfile(int driversID){

		//public  
		try(Connection conn = ConnectionFactory.getConnection()){
			//Prepare Statement
			//Method 1 - works on SQL Developer, throws exception here.
			String psql = "SELECT password FROM Users WHERE userDriversID = ? ";
			//Method 2 - doesn't work on SQL Developer 
//			String bsql = "SELECT password FROM Users WHERE userDriversID = " + driversID;
			//Method 1
//			String[] cols = {"userDriversID"};
//			PreparedStatement ps = conn.prepareStatement(psql,cols);
			//Method 2
			PreparedStatement ps = conn.prepareStatement(psql);//			
			ps.setInt(1, driversID);
			
			
			//Get Resulting Data
			ResultSet rs = ps.executeQuery();
			//Method 1
			// - clean output
//			if(rs.next()==false) {
//				System.out.println("There was no data returned");
//				return "";
//			}
//			return rs.getString("Password");
			//Method 2
			// - shows exception
			rs.next();
			return rs.getString("password");
			
		}catch(SQLException sqle) {
			System.out.println("Couldn't use connection!");
			//sqle.printStackTrace();
			return null;
		}
//		} catch (IOException e) {
//			System.out.println("IOException happened");
//			e.printStackTrace();
//		};
//		return null;	
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

