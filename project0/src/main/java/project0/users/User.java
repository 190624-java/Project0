package project0.users;

import java.util.HashMap;

public abstract class User {

	// HashMap<userName, Object>
	public static HashMap<String, Customer> customers = new HashMap<String, Customer>();
	public static HashMap<String, Employee> employees = new HashMap<String, Employee>();

	//public abstract boolean registerAccount(Scanner scanner);
	public abstract boolean login(String userName, short loginPin);

}
