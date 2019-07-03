package com.revature.collections;

import java.util.HashMap;
import java.util.Scanner;

import com.revature.exceptions.NewPasswordMismatch;
import com.revature.parties.Employee;
import com.revature.parties.User;
import com.revature.things.Account;
import com.revature.utilities.UIUtil;

public class Accounts {
	
	HashMap<String,Account> accounts;
	Employee ownerOfDealership; //used for creation of employee accounts
	
	public Accounts(Employee owner){
		this.accounts = new HashMap<>();
		this.ownerOfDealership = owner;
	}

	
	public void showCreationMenu() {
		System.out.println("Enter Type of Account: ");
		System.out.println("1 - Employee");
		System.out.println("2 - Customer");
		System.out.println("0 - Exit");
	}
	/**
	 * Additionally checks for type of user.
	 * If choosing Employee, dealership owner user password 
	 * must be entered as a form of admin approval to become
	 * an employee.
	 * @param userID
	 * @param passHash
	 */
	public void createAccount(String userID, int passHash) {

		Scanner s = new Scanner(System.in);
		int choice = -1;
		
		boolean needAuthorization = true;
		
		UIUtil ui = new UIUtil();
		boolean cont = true;
		
	
		do {
			ui.clearScreen();
			showCreationMenu();
			try {
			choice = s.nextInt();
			}catch(Exception e) {
				ui.printException(e);
				if(!ui.determineContinue()) return; //exit creation process
			}
		while(choice>0 && choice<4);
		
		//Handle the choice
		switch(choice) {
			case 1: { //employee
				try {
					this.authorize(userID);
				}catch() {
					
				}
				
			}
			case 2: //customer
			case 3: return; //exit
		}
		try {
			
			System.out.println("Enter a password: ");
			p1 = s.nextLine();
			
			this.checkExists(p1);
			System.out.println("Verify password: ");
			p2 = s.nextLine();
			
			checkPasswords(p1,p2);
			
			noPassword = false;
		}catch(NewPasswordMismatch mm) {
			ui.printException(mm);
			cont = ui.determineContinue();
			//TODO test s.close();
		}		
		}while(cont && noPassword);
		
		
		s.close();
	}
	
	
	private boolean passwordsMatch(String a, String b) {
		if(a.equals(b)) return true;
		else return false;
	}
	//TODO finish
	/**
	 * Asks for owner password
	 * @param userID
	 * @return
	 */
	public boolean authorize(String userID) {
		
		System.out.println("Error: admin password not accepted");
		return false;		
	}
	
	public boolean authenticate() {
		boolean unusableID = true;
		boolean noExit = true;
		Scanner sr = new Scanner(System.in);
		String uID; //user ID
		String pass;
		String exitChoice = "n";
		
		//User ID
		do { 
			System.out.println("Enter your user ID");
			uID = sr.nextLine();
			if(hasUser(uID))
				unusableID = false;
			else {
				System.out.println("Error: Username doesn't exist\n");
				System.out.println("Try Again? y or n \n");
				exitChoice = sr.next();
				if(!exitChoice.equalsIgnoreCase("y"))
					noExit = false;
			}//end else
		}while(unusableID && noExit);	
		
		//Password
		while(true){ 
			System.out.println("Enter your password: ");
			pass = sr.nextLine();
			if(passwordMatchesUser(uID, pass.hashCode())) {
				sr.close();
				return true;
			}
			else {
				System.out.println("Error: password doesn't match");
				System.out.println("Do you want to continue? y or n");
				exitChoice = sr.next();
				if(exitChoice.equalsIgnoreCase("y"))
					sr.close();
					return false; 
			}
		}//end while
	}//end method

	
	private void checkExists(String p1) {
		// TODO Auto-generated method stub
		
	}
	
	public void checkPasswords(String a, String b) throws NewPasswordMismatch{
		if(passwordsMatch(a,b)) return;
		else throw new NewPasswordMismatch();
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
