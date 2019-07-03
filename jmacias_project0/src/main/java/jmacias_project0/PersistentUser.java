package jmacias_project0;

import java.io.*;
import java.util.*;

public class PersistentUser {
	
	public static List<User> getUsers() {
		List<User> users = null;
		try(ObjectInputStream str = new ObjectInputStream(new FileInputStream(new File(".\\src\\main\\resources\\Data.txt")))) {
			users = (List<User>) str.readObject();
		}
		catch (Exception e) {
			return new ArrayList<>();
		}
		return users;
	}
	
	public static void addUsers(User newUser) throws FileNotFoundException, IOException {
		List<User> users = getUsers();
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(".\\src\\main\\resources\\Data.txt")))) {
			if (users != null) {
				System.out.println("User data found, adding to users.");
				users.add(newUser);
			}
			else {
				System.out.println("You are the first user.");
				users = new ArrayList<>();
				users.add(newUser);
			}
			oos.writeObject(users);
		}
		System.out.println("Users updated.");
	}
	
	public static void overwriteUsers(List<User> users) throws FileNotFoundException, IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(".\\src\\main\\resources\\Data.txt")))) {
			oos.writeObject(users);
		}
	}
}
