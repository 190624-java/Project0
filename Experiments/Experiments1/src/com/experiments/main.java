package com.experiments;

import java.util.Scanner;

import com.experiments.dbconnectivity.UserPassword;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserPassword up = new UserPassword();
		
		System.out.print("Enter User DriversID: ");
		Scanner kin = new Scanner(System.in);
		int uID = kin.nextInt();
		kin.nextLine();
		
		//get password input
		System.out.print("Enter the password: ");
		String enteredPassword = "";
		enteredPassword = kin.nextLine();
		
		//get the password
		String returnedPassword = up.getUserPasswordPS_noProfile(uID);
//		String returnedPassword = up.getUserPasswordUsingPreparedStatements(uID);
//		String returnedPassword = up.getUserPasswordUsingBasicStatements(uID);
		
		//-----------------
		//Verify and Report
		//-----------------
		//check user exists
		
		//if so, continue
		//else, break with message "UserID not found"
		//
		if(returnedPassword == null) {
			System.out.println("Error: Null String");
			return;
		}
		if(returnedPassword.equals(enteredPassword)) 
			System.out.println("Passwords Match!");
		else System.out.println("No Match!");
		
	}

}
