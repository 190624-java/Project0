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
		// TODO check list of users for username, and whether it matches
		// password
	}
	
}
