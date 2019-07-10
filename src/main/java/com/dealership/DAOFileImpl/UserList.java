package com.dealership.DAOFileImpl;

import java.util.HashSet;

import com.dealership.Employee;
import com.dealership.User;

public abstract class UserList {
	
	protected HashSet<User> userList;
	
	
	public boolean containsUser(User user) {
		if(userList.contains(user))
			return true;
		return false;
	}
	
	public User findByID(String id) {
		for(User user: userList) {
			if(user.getId().equals(id))
				return user;
		}
		return null;
	}
	
	public boolean containsID(String id) {
		for(User fromList: userList) {
			System.out.println("Checking if " + fromList.getId() + " is equal to " + id);
			if(fromList.getId().equals(id))
				return true;
		}
		return false;
	}
	
	public boolean isEmpty()
	{
		return userList.isEmpty();
	}
	
	public HashSet<User> getUserList() {
		return userList;
	}
}
