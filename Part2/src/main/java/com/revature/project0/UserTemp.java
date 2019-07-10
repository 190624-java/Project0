package com.revature.project0;

import java.util.Scanner;

public class UserTemp extends UsersAbstract {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7938699844461100699L;
	private UserFetcher userMaker = new UserFetcher();

	public int register(Scanner scanner) {
		
		System.out.print("Username: ");
		String username = scanner.next();
		System.out.print("Password: ");
		String password = scanner.next();
		System.out.print("Are you an Employee? Y/N ");
		String isEmployee =  scanner.next();
		System.out.println("------------------------------");
		System.out.println();
		
		int employee = 0;
		
		if(isEmployee.contains("Y") || isEmployee.contains("y")) {
			employee = 1;
		}
		
		boolean taken = false;
		
		/*for(UsersAbstract i : Project0.userData) {
			if(i.username.equals(username)) {
				System.out.println("Username taken...");
				taken = true;
				return 2;
			}
		}*/
		
		if(employee == 1) {
			//Project0.userData.add(new Employee(username, password, employee));
			userMaker.createUser(new Employee(username, password, employee));
		}
		else {
			//Project0.userData.add(new Customer(username, password));
			userMaker.createUser(new Customer(username, password, employee));
			System.out.println("done");
		}
		return 3;
		
	}
	
	public UsersAbstract logIn(Scanner scanner) {
		
		System.out.print("Username: ");
		username = scanner.next();
		System.out.print("Password: ");
		password = scanner.next();
		System.out.println("------------------------------");
		System.out.println();
		
		/*boolean pass = false;
		boolean name = false;
		
		for(UsersAbstract i : Project0.userData) {
			if(i.username.equals(username)){
				name = true;
			}
			if(i.password.equals(password)) {
				pass = true;
			}
			if(name && pass) {
				System.out.println("Log in sucessful. Welcome " + i.username);
				return i;
			}
		}*/
		
		return userMaker.getUser(username, password);
	}

}
