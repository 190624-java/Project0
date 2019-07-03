package com.revature.collections;

import java.util.HashMap;
import java.util.Scanner;

import com.revature.parties.User;
import com.revature.things.Account;

public class Accounts {
	

	HashMap<String,Account> accounts;
	
	public Accounts(){
		this.accounts = new HashMap<>();
	}

	public void createAccount(String userID, int passHash) {
		System.out.println("Enter User ID: ");
		Scanner s = new Scanner(System.in);
		String ID = s.nextLine();
		
		String p1, p2;
		System.out.println("Enter a password: ");
		p1 = s.nextLine();
		
		this.checkExists(p1);
		System.out.println("Verify password: ");
		p2 = s.nextLine();
		
		s.close();
	}
	
	private void checkExists(String p1) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasUser(String userID) {
		return accounts.containsKey(userID);
	}
	
	public Account getUserAccount(String userID) {
		return accounts.get(userID);
	}
	
	public boolean passwordMatchesUser(String userID, int passHash) {
		if(accounts.get(userID).passwordMatches(passHash)) return true;
		else return false;
	}
	
	//TODO
	/**
	 * Get the Account 
	 * Get the User
	 * Start respective User's Menu and Functionality
	 * @param uID
	 * @param i
	 */
	public void logIn(String uID, int i) {
		accounts.get(uID).access();
	}
	
	//TODO 
	//save the information to the files, exit the program
	public void logOut() {
		
	}

	

}
