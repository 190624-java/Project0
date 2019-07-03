package com.revature.project0;

import java.util.Scanner;

public class UserTemp extends UsersAbstract {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7938699844461100699L;

	public int register(Scanner scanner) {
		
		System.out.print("Username: ");
		String username = scanner.next();
		System.out.print("Password: ");
		String password = scanner.next();
		System.out.print("Are you an Employee? Y/N ");
		String isEmployee =  scanner.next();
		System.out.println("------------------------------");
		System.out.println();
		
		boolean employee = false;
		
		if(isEmployee.contains("Y") || isEmployee.contains("y")) {
			employee = true;
		}
		
		boolean taken = false;
		
		for(UsersAbstract i : Project0.userData) {
			if(i.username.equals(username)) {
				System.out.println("Username taken...");
				taken = true;
				return 2;
			}
		}
		
		if(!taken) {
			if(employee) {
				Project0.userData.add(new Employee(username, password));
			}
			else {
				Project0.userData.add(new Customer(username, password));
			}
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
		
		boolean pass = false;
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
		}
		
		System.out.println("Username is incorrect please try again.");
		
		return null;
	}

}
