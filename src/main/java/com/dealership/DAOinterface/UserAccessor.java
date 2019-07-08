package com.dealership.DAOinterface;

import com.dealership.User;

public interface UserAccessor {

	public boolean containsUser(User user);
	public boolean containsID(String id);
	public boolean isEmpty();
}
