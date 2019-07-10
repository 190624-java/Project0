package service;

import java.io.*;
import beans.User;
import dataAccessObjects.UserDAO;
import dbControllers.UserController;

class Signup {
	private static InputStreamReader iReader = new InputStreamReader(System.in);
	private static BufferedReader bReader = new BufferedReader(iReader);
	public static UserDAO userDAO = new UserController();

	
	public static void createAccount() throws IOException {
		System.out.print("Please enter your user name: ");
		String uName = bReader.readLine();
		System.out.print("\nPlease enter the password for your account: ");
		String uPwd = bReader.readLine();
		
		User newUser = new User(uName, uPwd);
		System.out.println("New user name is: " + newUser.toString());
		
		userDAO.addUser(newUser);
		System.out.println("(Press return)");
		
		// returning to first screen
		String command = Driver.welcome();
		Driver.check(command);

	}

}
