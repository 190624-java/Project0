package jmacias_project0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class LoggedIn {
	private static InputStreamReader iReader = new InputStreamReader(System.in);
	private static BufferedReader bReader = new BufferedReader(iReader);
	
	public static void startApp(String userName, String userRole) throws IOException {
		List<User> users = PersistentUser.getUsers();
		if (userRole.equals("user")) {
			System.out.println("Welcome "  + userName + ", please enter the number of the type of account you would like to register for.");
			System.out.println("1. Customer\n2. Employee");
			String response = bReader.readLine();
			switch (response) {
			case "1":
				System.out.println("You chose \"Customer\"");
				for (User x : users) {
					if (x.name.equals(userName)) {
						x.role = "customer";
						userRole = x.role;
					}
				}
				break;
			case "2":
				System.out.println("You chose \"Employee\"");
				for (User x : users) {
					if (x.name.equals(userName)) {
						x.role = "employee";
						userRole = x.role;
					}
				}
			default:
				System.out.println("You must choose a valid number.");
				startApp(userName, userRole);
			}
		}
		PersistentUser.overwriteUsers(users);
		if (userRole.equals("customer")) {
			System.out.println("Welcome valued customer :-)");
			CustomerService.customerMenu(userName);
		}
		if (userRole.equals("employee")) {
			System.out.println("");
			EmployeeService.employeeMenu(userName);
		}
		
		
	}
}
