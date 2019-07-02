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
}
