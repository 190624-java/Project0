package com.revature.phudspeth.project0pt1;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class RegistrarDB
{
	AppSystem SYS = AppSystem.getInstance();
	
	public void register(String username, String password) 
	{
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		try(Connection con = ConnectionManager.getConnection())
		{
			System.out.println("1: Register as customer.");
			System.out.println("2: Register as employee.");
			String choice = input.nextLine().toString();
			switch(choice) 
			{
			case "1":
				addUser(username,password, "customer");
				break;
			case "2":
				System.out.println("Please ask an administrator to put in employee registration verification code.");
				if(input.nextLine().equals("newemployee.register")) 
				{
					addUser(username,password, "employee");
				}
				else 
				{
					System.out.println("Incorrect verification code. Ending registration.");
				}
				break;
			/*case "admin":
				System.out.println("Please redirect to systems admin.");
				if(input.nextLine().equals("iwrotethiscode")) 
				{
					userBase.put(username + "." + password, "Admin");
				}
				else 
				{
					System.out.println("Incorrect verification code. Ending registration.");
				}
				break;*/
				
			}
			System.out.println("Thanks for registering!");
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("There is already a user with that username, please try again.");
		}
	}
	
	public void login(String username, String password) throws SQLException 
	{
		try(Connection con = ConnectionManager.getConnection())
		{
			String sql = "SELECT access_level, user_id FROM user_account WHERE username = ? AND user_password = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet result = stmt.executeQuery();
			if(result.next()) 
			{
				SYS.setUser(result.getString("access_level"), result.getInt("user_id"));
				System.out.println("Successful Login.");
			}
			else 
			{
				System.out.println("Login invalid.");
			}
		}
		catch(SQLException e) 
		{
			System.out.println("Login invalid, please try again.");
			SYS.login();
		}
	}
	
	void addUser(String username, String password, String accessLevel) throws SQLException 
	{
		try(Connection con = ConnectionManager.getConnection())
		{
			String SQL = "INSERT INTO user_account(username, user_password, access_level, user_id) VALUES(?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(SQL);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, accessLevel);
			stmt.setInt(4, (username + "." + password).hashCode());
			stmt.executeUpdate();
		}
	}
}
