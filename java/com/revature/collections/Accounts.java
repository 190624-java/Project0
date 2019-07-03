package com.revature.collections;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.exceptions.NewPasswordMismatch;
import com.revature.exceptions.UserExit;
import com.revature.main.UserTypes;
import com.revature.parties.Employee;
import com.revature.parties.User;
import com.revature.things.Account;
import com.revature.utilities.UIUtil;

public class Accounts {
	
	HashMap<Integer,Account> accounts;
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
	 * @param driversID
	 * @param passHash
	 */
	public void createAccount(int driversID, int passHash) throws UserExit{

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
				if(!ui.determineContinue()) {s.close();throw new UserExit();} //exit creation process
			}
		}while(choice>0 && choice<4);
		
		//Handle the choice
		switch(choice) {
			case 1: { //employee
					try{this.seekAuthorization();} //throws UserExit
					catch(UserExit ue) {
						s.close();
						throw ue;
					}
					this.accounts.put(driversID, new Account(
							driversID, passHash, UserTypes.EMPLOYEE
							));
					break;
			}
			case 2: { //customer
				this.accounts.put(driversID, new Account(
						driversID, passHash, UserTypes.CUSTOMER
						));
				break;
			}
			case 3: { //exit
				s.close(); 
				throw new UserExit(); 
			}
		} //end switch
		s.close();
		
		
		//Method 1
//		try {
//			
//			System.out.println("Enter a password: ");
//			p1 = s.nextLine();
//			
//			this.checkExists(p1);
//			System.out.println("Verify password: ");
//			p2 = s.nextLine();
//			
//			checkPasswords(p1,p2);
//			
//			noPassword = false;
//		}catch(NewPasswordMismatch mm) {
//			ui.printException(mm);
//			cont = ui.determineContinue();
//			//TODO test s.close();
//		}		
//		}while(cont && noPassword);
//		s.close();
		
	} //end create account
	
	/**
	 * Requires login information of an employee
	 * @return
	 * @throws UserExit
	 */
	private boolean seekAuthorization() throws UserExit{
		System.out.println("Enter a Hiring Employee Login");
		//TODO this should be the ownerOfDealership, but authorize will suffice for now.
		User hirer = authenticate();
		if(hirer==null) return false;
		
		//TODO //if(hirer)
		return true;
		
	}


	private boolean passwordsMatch(String a, String b) {
		if(a.equals(b)) return true;
		else return false;
	}
	//TODO finish
	/**
	 * Asks for owner password
	 * @param driversID
	 * @return
	 */
	public boolean authorize(int driversID) {
		System.out.println("Enter a Hiring Employee Login");
		//TODO this should be the ownerOfDealership, but authorize will suffice for now.
		
		
		return false;		
	}
	
	/**
	 * Checks that the user login info is valid for an existing
	 * account. 
	 * @return Returns that user info, if so.
	 * @throws UserExit
	 */
	public User authenticate() throws UserExit{
		boolean unusableID = true;
		int driversID = -1;
		String pass;
		
		//User ID
		do { 
			System.out.println("Enter your drivers ID");
			try {
			driversID = UIUtil.s.nextInt();
			}catch(InputMismatchException e) {
				if(UIUtil.determineContinue()) continue; //restart do..while
				else throw new UserExit();
			}
			if(hasUser(driversID))
				unusableID = false;
			else {
				System.out.println("Error: ID doesn't exist\n");
				//If user doesn't want to continue to enter username and pass
				//then, authentication fails.
				if(!UIUtil.determineContinue()) { return null; }
			}//end else
		}while(unusableID);	
		
		//check for the license ID
		unusableID = false;
		
		//Password
		while(true){ 
			System.out.println("Enter your password: ");
			pass = UIUtil.s.nextLine();
			if(passwordMatchesUser(driversID, pass.hashCode())) {
				return new User(driversID, pass.hashCode()); //default driversID argument
			}
			else {
				System.out.println("Error: password doesn't match");
				if(!UIUtil.determineContinue()) { return null; }
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

	public boolean hasUser(int licenseID) {
		return accounts.containsKey(licenseID);
	}
	
	public Account getUserAccount(int driversID) {
		return accounts.get(driversID);
	}
	
	public boolean passwordMatchesUser(int userID, int passHash) {
		if(accounts.get(userID).passwordMatches(passHash)) return true;
		else return false;
	}
	

//	/**
//	 * Get the Account 
//	 * Get the User
//	 * Start respective User's Menu and Functionality
//	 * @param uID
//	 * @param i
//	 */
//	public void logIn(int driversID, int passH) {
//		accounts.get(driversID).access();
//	}
	
	//TODO 
	//save the information to the files, exit the program
	public void logOut() {
		
	}

	

}
