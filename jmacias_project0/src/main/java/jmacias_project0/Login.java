package jmacias_project0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Login {
	private static InputStreamReader iReader = new InputStreamReader(System.in);
	private static BufferedReader bReader = new BufferedReader(iReader);
	
	public static void login() throws IOException {
		System.out.print("Please enter your user name: ");
		String uName = bReader.readLine();
		System.out.print("\nPlease enter the password for your account: ");
		String uPwd = bReader.readLine();
		
		List<User> users = PersistentUser.getUsers();
		boolean loggedIn = false;
		boolean usernameExists = false;
		String userName = "";
		String userRole = "";
		for (User x : users) {
			
			if (x.name.equals(uName)) {
				usernameExists = true;
				if (x.password.equals(uPwd)) {
					loggedIn = true;
					userName = x.name;
					userRole = x.role;
				}
			}
				
		}
		
		if (loggedIn) {
			LoggedIn.startApp(userName, userRole);
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
			System.out.println("Something wierd happened");
		}
	}
	
}
