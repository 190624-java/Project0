package com.revature.project0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserFetcher {
	public UsersAbstract createUser(UsersAbstract user) {
		//1. get a connection to the database
        try(Connection con = DbConnectionHandler.getConnection())
        {
            //2. Create a statement.
            String sql = "INSERT INTO Users(username, user_password, is_employ)"
                    +"VALUES (?,?,?)";
            String[] primaryKeyValues = {"user_id"};
            PreparedStatement stmt = con.prepareStatement(sql, primaryKeyValues);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getEmploy());
            
            //3. Executing the statement
            stmt.executeUpdate();
            
            //4. get Player_id
            ResultSet keys = stmt.getGeneratedKeys();
            while(keys.next()) 
            {
                int userId = keys.getInt(1);
                user.setId(userId);
            }
            return user;
        } 
        catch (SQLException e) 
        {
            //would probably want to throw a custom application-specific exception to be handled elsewhere.
            System.out.println("Something went wrong while trying to create a user in the database.");
            e.printStackTrace();
            return null;
        }
	}
	
	public UsersAbstract getUser(String username, String password) {
		//1. get a connection
		try(Connection conn = DbConnectionHandler.getConnection()){
			//2. create a statement
			String sql = "Select user_id, username, user_password, is_employ "
					+ "From users "
					+ "Where username = ? And user_password = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			//3. execute query
			ResultSet results = stmt.executeQuery();
			
			//4. iterate through results and mat to object
			UsersAbstract user = null;
			while(results.next()) {
				System.out.println("Found user...");
				int userId = results.getInt("user_id");
				String uname = results.getString("username");
				String pword = results.getString("user_password");
				int employ = results.getInt("is_employ");
				
				if(employ == 1) {
					user = new Employee(userId,uname,pword);
					System.out.println("Welcome employee.");
				}
				else {
					user = new Customer(userId, uname, pword);
					System.out.println("Welcome customer");
				}
			}
			if(user == null) {
				System.out.println("Try agian");
			}
			return user;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
}
