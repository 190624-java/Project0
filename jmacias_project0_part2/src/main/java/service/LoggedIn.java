package service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import beans.User;
import dataAccessObjects.UserDAO;
import dbControllers.UserController;

public class LoggedIn {
	private static InputStreamReader iReader = new InputStreamReader(System.in);
	private static BufferedReader bReader = new BufferedReader(iReader);
	public static UserDAO userDAO = new UserController();
	
	public static void startApp(User thisUser) throws IOException {
		
		if (thisUser.role.equals("user")) {
			System.out.println("Welcome "  + thisUser.name + ", please enter the number of the type of account you would like to register for.");
			System.out.println("1. Customer\n2. Employee");
			String response = bReader.readLine();
			switch (response) {
			case "1":
				System.out.println("You chose \"Customer\"");
				thisUser.setRole("customer");
				break;
			case "2":
				System.out.println("You chose \"Employee\"");
				thisUser.setRole("employee");;
				break;
			default:
				System.out.println("You must choose a valid number.");
				startApp(thisUser);
			}
		}
		userDAO.updateUserRole(thisUser);

		if (thisUser.role.equals("customer")) {
			System.out.println("Welcome valued customer :-)");
			CustomerService.customerMenu(thisUser.name);
		}
		if (thisUser.role.equals("employee")) {
			System.out.println("");
			EmployeeService.employeeMenu(thisUser.name);
		}
		
		
	}
}