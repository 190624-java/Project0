package com.revature.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.exceptions.InvalidPassword;
import com.revature.exceptions.InvalidUserID;

public class ConnectionFactory {

	
	
	public ConnectionFactory(){
		
	}
	
	/**
	 * Simple connection to get started
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		String port = "1521";
		String hostname = "class-and-project0.cvriicvj5pb8.us-east-2.rds.amazonaws.com";
		String dbName = "ORCL";
		String admin_username = "abc";
		String admin_password = "abcdefghij";
		//Port 1521
		String url_ds = "jdbc:driver://"
				+hostname
				+":"+port
				+"/"+dbName
				+"?user="+admin_username
				+"&password="+admin_password;
		return DriverManager.getConnection(url_ds,admin_username,admin_password);
	}

	/**
	 * TODO implement Custom Connection class that may prevent particular funcationality for a particular
	 * user type.
	 * @param type
	 * @param user_ID
	 * @param user_password
	 * @return
	 * @throws SQLException
	 * @throws InvalidUserID
	 * @throws InvalidPassword
	 */
	public static Connection getConnection(String type, String user_ID, String user_password) throws SQLException, InvalidUserID, InvalidPassword {
		//"class-and-project0.cvriicvj5pb8.us-east-2.rds.amazonaws.com";
		//jdbc syntax:
		//"jdbc:driver://hostname:port/dbName?user=userName&password=password"
		
		String port = "1521";
		String hostname = "class-and-project0.cvriicvj5pb8.us-east-2.rds.amazonaws.com";
		String dbName = "ORCL";
		String admin_username = "abc";
		String admin_password = "abcdefghij";
		//Port 1521
		String url_ds = "jdbc:driver://"
				+hostname
				+":"+port
				+"/"+dbName
				+"?user="+user_ID
				+"&password="+user_password;
		switch(type){
		case "Login":
			//String[] userIDArray = {user_ID};
			
			Connection conn = DriverManager.getConnection(url_ds, admin_username, admin_password);
			//Get the user's credentials and verify that the user exists in the database with the
			//same password. If not, then exit the connection with an error.
			
			String[] userIDArray = {user_ID, user_password};
			PreparedStatement checkUserID = conn.prepareStatement(
					"SELECT * FROM users WHERE username='?';", 
					userIDArray);
			

			//if userID not found
			//no authentication
			ResultSet userResults = checkUserID.executeQuery();
			try{
				if(userResults.getInt(1)==Integer.valueOf(user_ID)) return conn; 
			}catch (SQLException sqle) {
				throw new InvalidUserID();				
			}
			
			
			String[] userPasswordWIDArray = {user_ID, user_password};
			PreparedStatement checkPassWUserID = conn.prepareStatement(
					"SELECT userID FROM users WHERE userID='?' AND password='?';", 
					userPasswordWIDArray);

			
			//if password doesn't match userID
			ResultSet passResults = checkPassWUserID.executeQuery();
			try{
				if(passResults.getString(1)==user_password) return conn; 
			}catch (SQLException sqle) {
				throw new InvalidPassword();
			}			
		
			return DriverManager.getConnection(url_ds, admin_username, admin_password) ;
			
		default: return null;
		}//end switch

		
	}
	
	
}
