package com.dealership;

import java.util.HashSet;

public abstract class UserList {
	
	protected HashSet<User> userList;
	
	
	public boolean containsUser(User user) {
		if(userList.contains(user))
			return true;
		return false;
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
}
