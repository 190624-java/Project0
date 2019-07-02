package jmacias_project0;

import java.io.*;

public class Signup {
	private static InputStreamReader iReader = new InputStreamReader(System.in);
	private static BufferedReader bReader = new BufferedReader(iReader);
//	private static File data = new File(".\\src\\main\\resources\\Data.txt");
	
	public static void createAccount() throws IOException {
		System.out.print("Please enter your user name: ");
		String uName = bReader.readLine();
		System.out.print("\nPlease enter the password for your account: ");
		String uPwd = bReader.readLine();
		
		User newUser = new User(uName, uPwd);
		System.out.println("New user name is: " + newUser.toString());
		
		PersistentUser.addUsers(newUser);
		
		// returning to first screen
		Driver.welcome();

	}

}
