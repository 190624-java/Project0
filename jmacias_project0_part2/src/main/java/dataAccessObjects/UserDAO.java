package dataAccessObjects;

import beans.User;

public interface UserDAO {
	public User getUser(String userName);
	public void addUser(User newUser);
	public void updateUserRole(User user);
	// TODO this replaces a method that feeds a list and overwrites all cars in the 
	// file version.  references must be updated.
}
