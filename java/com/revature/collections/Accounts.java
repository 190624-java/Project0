package com.revature.collections;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.exceptions.InvalidInput;
import com.revature.exceptions.NewPasswordMismatch;
import com.revature.exceptions.UserExit;
import com.revature.main.UserTypes;
import com.revature.parties.Customer;
import com.revature.parties.DSystem;
import com.revature.parties.Employee;
import com.revature.parties.User;
import com.revature.things.logins.Account;
import com.revature.utilities.UIUtil;

public class Accounts {
	
	//---------------------------
	//	Fields
	//---------------------------
	
	private HashMap<Integer,Account> accounts;
	private Employee ownerOfDealership; //used for creation of employee accounts
	protected DSystem dSys;
	private Account activeAccount;
	private Authenticator authenticator;
	
	//---------------------------
	//	Constructors
	//---------------------------
	
	
	public Accounts(Employee owner){
		this.accounts = new HashMap<>();
		this.ownerOfDealership = owner;
		this.dSys = DSystem.getInstance();
		authenticator = new Authenticator();
	}

	//---------------------------
	//	Methods
	//---------------------------
	
	public Authenticator getAuthenticator() {
		return authenticator;
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
		
	} //end create account
	
	
	/**
	 * Requires login information of an employee
	 * @return
	 * @throws UserExit
	 */
	private boolean seekAuthorization() throws UserExit{
		System.out.println("Enter a Hiring Employee Login");
		//TODO this should be the ownerOfDealership, but authorize will suffice for now.
		Integer hirerID = authenticator.authenticateUser();		
		if(hirerID==null) {
			UIUtil.echoProblem("Error: null userID returned");
			return false;
		} else UIUtil.echoCompletion("Account Found");
		if(getUserAccount(hirerID).getAccountType() == UserTypes.DEALER) {
			UIUtil.echoCompletion("Success: Dealer Authority Recognized");
			return true;
		}
		//account didn't have the authority
		UIUtil.echoProblem("Problem: Account Authority Not Recognized. Must be Dealer Account");
		return false;		
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
	
	

	

	
	public Account getUserAccount(int driversID) {
		return accounts.get(driversID);
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
	/**
	 * Method 1
	 * Determine which type of account is logging in
	 * Show corresponding main account menu
	 * Method 2 
	 * call the account's login function and give it access to what it needs
	 * @param acc Account to use information from.
	 */
	public void logIn(Account acc) {
		//Determine which type of account is logging in
		
		//Show corresponding main account menu
	}
	
	//TODO 
	//save the user's information to the files,
	//unlink the user
	//exit the main menu
	public void logOut() {
		this
	}

	public class Authenticator{
		

		public boolean passwordsMatch(String a, String b) {
			if(a.equals(b)) return true;
			else return false;
		}
		
		
		public boolean passwordMatchesUser(int userID, int passHash) {
			if(accounts.get(userID).getPassword().passwordMatches(passHash)) return true;
			else return false;
		}
		
		
		public void checkPasswords(String a, String b) throws NewPasswordMismatch{
			if(passwordsMatch(a,b)) return;
			else throw new NewPasswordMismatch();
		}

		
		public boolean hasUser(int licenseID) {
			return accounts.containsKey(licenseID);
		}
		
		
		/**
		 * Allows multiple username attempts
		 * Checks that the user login info is valid for an existing
		 * account. 
		 * @return If account exists, Returns that user's username (i.e. driverID)
		 * @throws UserExit
		 */
		public Integer authenticateUser() throws UserExit{
			boolean unusableID = true;
			Integer driversID;

			//User ID
			do { 
				driversID = this.authenticateUsername(); 
				if(driversID!=null) unusableID = false; //authenticated userID, so 
			}while(unusableID);	
			
			//Password
			while(true){
					if(authenticatePassword(driversID)) 
						return new Integer(driversID);
			}//end while
		}//end method

		
		/**
		 * Only 1 username attempt per method run.
		 * @return
		 * @throws UserExit
		 */
		private Integer authenticateUsername() throws UserExit {
			System.out.println("Enter your drivers ID");
			int driversID = -1;
			try {
				driversID = UIUtil.getInt();
			}catch(InputMismatchException e) {
				if(UIUtil.determineContinue()) return null; //continue; //restart do..while
				else throw new UserExit();
			}catch(InvalidInput e) {
				e.printMessage();
			}
			if(hasUser(driversID))
				return new Integer(driversID); //authenticated
				//unusableID = false;
			else {
				System.out.println("Error: ID doesn't exist\n");
				//If user doesn't want to continue to enter username and pass
				//then, authentication fails.
				if(!UIUtil.determineContinue()) { throw new UserExit(); }
			}//end else
			return null; //continue the main authentication loop
		}
		
		
		private boolean authenticatePassword(Integer driversID) throws UserExit {
			System.out.println("Enter your password: ");
			String pass = UIUtil.s.nextLine();
			if(passwordMatchesUser(driversID, pass.hashCode())) {
				return true;
				//return new Integer(driversID);
				//return new User(driversID, pass.hashCode()); //default driversID argument
			}
			else {
				System.out.println("Error: password doesn't match");
				if(!UIUtil.determineContinue()) { throw new UserExit(); }
			}
			return false; //continue the main authentication loop
		}
		
	}

}
