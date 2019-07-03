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
			if(fromList.getId() == id)
				return true;
		}
		return false;
	}
	
	public boolean isEmpty()
	{
		return userList.isEmpty();
	}
}
