package jmacias_project0;

import java.io.*;

public class Driver {
	
	private static InputStreamReader iReader = new InputStreamReader(System.in);
	private static BufferedReader bReader = new BufferedReader(iReader);
	
	private static String welcome() {
		System.out.println("     Please enter the number of your command");
		System.out.println("1. Login\n2. Signup");
		
		try {
			String command = bReader.readLine();
			return command;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	private static void check(String command) {
		if (command.equals("1")) {
			System.out.println(1);
		}
		else if (command.equals("2")) {
			System.out.println(2);
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

	public static void main(String[] args) {
		// setting up readers
		System.out.println("===========  Welcome to the Dealership  =============\n\n");
		String command1 = welcome();
		check(command1);
		
	}

}
