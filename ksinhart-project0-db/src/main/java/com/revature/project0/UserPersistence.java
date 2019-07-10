package com.revature.project0;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class UserPersistence {
	private static String filename = "user-data.ser";
	
	public static void addNewUserDB(User user) {
		try(Connection conn = ConnectionSetUp.getConnection()){
			String sql = "INSERT INTO user_Acc VALUES (?,?,?,?,1)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getFname());
			stmt.setString(2, user.getLname());
			stmt.setString(3, user.getUsername());
			stmt.setString(4, user.getPassword());
			
			int rowsAffected = stmt.executeUpdate();
			System.out.println("Rows Inserted: " + rowsAffected);
			
			//ResultSet results = stmt.getGeneratedresults();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static User getUser (String uname) {
		try(Connection conn = ConnectionSetUp.getConnection()){
			
			String sql = "SELECT fname, lname, username, pass, u_check FROM user_Acc WHERE username = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, uname);
			
			//int rowsAffected = stmt.executeUpdate();
			//System.out.println("Rows Inserted: " + rowsAffected);
			
			ResultSet results = stmt.executeQuery();
			
			User user=null;
			while(results.next()) {
				String fname=results.getString(1);
				String lname=results.getString(2);
				String username=results.getString(3);
				String pass=results.getString(4);
				int check = results.getInt(5);
				user= new Customer(check,fname,lname,username,pass);
			}
			return user;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		//return null;
		
	}
	
	public static String getPassword(String username) {
		try(Connection conn = ConnectionSetUp.getConnection()){
			String sql = "SELECT pass FROM user_Acc WHERE username = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			
			//int rowsAffected = stmt.executeUpdate();
			//System.out.println("Rows Inserted: " + rowsAffected);
			
			ResultSet results = stmt.executeQuery();
			//System.out.println(results);
			while(results.next()) {
				String pass=results.getString("pass");
				//System.out.println("Is this hitting"+pass);
				return pass;
				//user=new Customer(check,fname,lname,username,pass);
			}
			//return (Customer) user;
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public static void removeUserDB(User user) {
		try(Connection conn = ConnectionSetUp.getConnection()){
			String sql = "DELETE FROM user_Acc WHERE username = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			
			int rowsAffected = stmt.executeUpdate();
			System.out.println("Rows Inserted: " + rowsAffected);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Set<User> getAllUsersDB() {
		try(Connection conn = ConnectionSetUp.getConnection()){
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/*
	public static void addNewUser(Employee user) throws FileNotFoundException, IOException, ClassNotFoundException {
		Set<User> records = getAllUsers();
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(filename),false))) {
			
			if (records != null) {
				//System.out.println("User found");
				records.add(user);
			} else {
				records = new HashSet<User>();
				records.add(user);
			}
			oos.writeObject(records);
			System.out.println("New set written");
		}
	}
	
	public static void addNewUser(Customer user) throws FileNotFoundException, IOException, ClassNotFoundException {
		Set<User> records = getAllUsers();
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(filename),false))) {
			
			if (records != null) {
				//System.out.println("User found");
				records.add(user);
			} else {
				records = new HashSet<User>();
				records.add(user);
			}
			oos.writeObject(records);
			System.out.println("New set written");
		}
	}
	
	public static void addUserList(Set<User> set) throws FileNotFoundException, IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(filename),false))) {
			oos.writeObject(set);
			System.out.println("Set was written");
		}
	}
	
	public static Set<User> getAllUsers() throws IOException, ClassNotFoundException {
		Set<User> records = null;
		try (ObjectInputStream str = new ObjectInputStream(new FileInputStream(new File("user-data.ser")))) {
			records = (Set<User>) str.readObject();
			//System.out.println(records);
		} catch (FileNotFoundException e) {
			return new HashSet<User>();
		}
		return records;
	}
	*/
}
