package com.revature.project0;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class UserPersistence {
	private static String filename = "user-data.ser";
	
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
	
}
