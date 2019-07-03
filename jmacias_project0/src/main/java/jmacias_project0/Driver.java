package jmacias_project0;

import java.io.*;

public class Driver {
	
	private static InputStreamReader iReader = new InputStreamReader(System.in);
	private static BufferedReader bReader = new BufferedReader(iReader);
	
	static String welcome() {
		System.out.println("     Please enter the number of your command");
		System.out.println("1. Login\t\t\t\t2. Signup");
		
		try {
			String command = bReader.readLine();
			return command;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	static void check(String command) throws IOException {
		if (command.equals("1")) {
			Login.login();
		}
		else if (command.equals("2")) {
			Signup.createAccount();
		}
		else {
			System.out.println("   Please enter a valid command");
			System.out.println("Press return to continue");
			try {
				bReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			command = welcome();
			check(command);
		}
	}

	public static void main(String[] args) throws IOException {
		// setting up readers
		System.out.println("===========  Welcome to the Dealership  =============\n\n");
		String command1 = welcome();
		check(command1);
		
	}

}
