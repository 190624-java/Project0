package service;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.User;
import dataAccessObjects.CarDAO;
import dataAccessObjects.UserDAO;
import dbControllers.CarController;
import dbControllers.UserController;
import dbControllers.DatabaseUtils;

public class Driver {
	
	private static InputStreamReader iReader = new InputStreamReader(System.in);
	private static BufferedReader bReader = new BufferedReader(iReader);
	public static CarDAO carDAO = new CarController();
	public static UserDAO userDAO = new UserController();
	
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
			System.out.println("(Press return to continue)");
			try {
				bReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			command = welcome();
			check(command);
		}
	}

	public static void main(String[] args) throws IOException, SQLException {
		// setting up readers
		System.out.println("                     _______");
		System.out.println("                    //  ||\\ \\");
		System.out.println("              _____//___||_\\ \\___");
		System.out.println("              )  _          _    \\");
		System.out.println("              |_/ \\________/ \\___|");
		System.out.println("             ___\\_/________\\_/______");
		System.out.println("");
		System.out.println("==========  Welcome to the Dealership  ===========\n\n");
		// Temporary testing calls
//		Car testCar = new Car("Hoopdie", 20);
//		carDAO.addCar(testCar);
//		User newUser = new User("Test User2", "password");
//		newUser.setRole("Customer");
//		userDAO.addUser(newUser);
//		int results = DatabaseUtils.getPayments();
//		System.out.println(results);
		
		
		
		String command1 = welcome();
		check(command1);
		
	}

}