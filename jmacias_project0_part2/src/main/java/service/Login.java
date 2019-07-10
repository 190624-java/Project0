package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import beans.User;
import dataAccessObjects.UserDAO;
import dbControllers.*;

class Login {
	private static InputStreamReader iReader = new InputStreamReader(System.in);
	private static BufferedReader bReader = new BufferedReader(iReader);
	public static UserDAO userDAO = new UserController();
	
	public static void login() throws IOException {
		System.out.print("Please enter your user name: ");
		String uName = bReader.readLine();
		System.out.print("\nPlease enter the password for your account: ");
		String uPwd = bReader.readLine();
		
		User user = userDAO.getUser(uName);
		
		boolean loggedIn = false;
		boolean usernameExists = false;
			if (null == user) {
				System.out.println("object null");
			}
			else if (user.name.equals(uName)) {
				usernameExists = true;
				if (user.password.equals(uPwd)) {
					loggedIn = true;
				}
			}
		
		if (loggedIn) {
			LoggedIn.startApp(user);
		} 
		else if (usernameExists == false) {
			System.out.println("Sorry, that user name does not exist."
					+ "\nPress return to continue");
			bReader.readLine();
			String command = Driver.welcome();
			Driver.check(command);
		}
		else if(!loggedIn) {
			System.out.println("Sorry, that password is not correct."
					+ "\nPress return to continue");
			bReader.readLine();
			String command = Driver.welcome();
			Driver.check(command);
		}
		else {
			System.out.println("Something wierd happened at login");
		}
	}
	
}
