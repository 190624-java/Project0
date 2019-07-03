package com.revature.parties;



import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import com.revature.collections.Accounts;
import com.revature.exceptions.NewPasswordMismatch;
import com.revature.things.Car;
import com.revature.things.Offer;
import com.revature.things.Password;
import com.revature.utilities.UIUtil;

/*
 * As a user, I can login.
* As an employee, I can add a car to the lot.
* As a customer, I can view the cars on the lot.
* As a customer, I can make an offer for a car.
* As an employee, I can accept or reject an offer for a car.
* As the system, I reject all other pending offers for a car when an offer is accepted.
* As a user, I can register for a customer account.
* As an employee, I can remove a car from the lot.
* As a customer, I can view the cars that I own.
* As a customer, I can view my remaining payments for a car.
* As an employee, I can view all payments.
* As the system, I can calculate the monthly payment.
*/

/* organized
* As a user, I can 
* 	login.
* 	register for a customer account.
* 
* 
* 
* 
* As an employee, I can 
* 	add a car to the lot.
* 	accept or reject an offer for a car.
* 	remove a car from the lot.
* 	view all payments.
* 
* 
* 
* As a customer, I can 
* 	view the cars on the lot.
* 	make an offer for a car.
* 	view the cars that I own.
* 	view my remaining payments for a car.
* 
* 
* 
* As the system, I 
* 	reject all other pending offers for a car when an offer is accepted.
* 	calculate the monthly payment.
*/



/**
 * 
 * As the system, I 
 * 	reject all other pending offers for a car when an offer is accepted.
 * 	calculate the monthly payment.
 */
public class DSystem {
	
	private HashSet<User> online;
	private Accounts accounts;
	private String dealershipName;
	private Customer companyOwner;
	
	
	public DSystem(String dealershipName, Customer companyOwner) {
		this.online = new HashSet();
		this.dealershipName = dealershipName;
		this.accounts = new Accounts();
	}

	public static float calMonthlyPayment(User carOwner, Car ownedCar) {
		
		return 0;
		
	}
	
	/**
	 * Go through all the offers on the car.
	 * @param car
	 */
	public void rejectPendingOffers(Offer chosenOffer) {
		Offer keptOffer = new Offer(chosenOffer);
		Car dCar = chosenOffer.getDesiredCar();
		Iterator<Offer> it = dCar.getOffersIterator(this);
	}
	

	
	private boolean checkPasswordStrength(String pass) {
		return Password.hasUppercase(pass);
	}
	

	public void giveStartUpMenu() {
		System.out.println("Hello! Welcome to the System!\n");
		System.out.println("\t\t"+"1 - Login");
		System.out.println("\t\t"+"2 - Create Account");
		System.out.println("\t\t"+"0 - Exit");
	}
	

	/**
	 * Gets user ID and Password
	 * Checks them for system compatibility
	 * If so, Logs into the accounts object
	 * Else, returns to main menu.
	 */
	public void beginLogin() {
		boolean unusableID = true;
		boolean noExit = true;
		boolean unusablePassword = true;
		Scanner sr = new Scanner(System.in);
		String uID; //user ID
		String pass;
		String exitChoice = "n";
		
		//User ID
		do { 
			System.out.println("Enter your user ID");
			uID = sr.nextLine();
			if(accounts.hasUser(uID))
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
		while(noExit && unusablePassword){ 
			System.out.println("Enter your password: ");
			pass = sr.nextLine();
			if(accounts.passwordMatchesUser(uID, pass.hashCode())) {
				accounts.logIn(uID, pass.hashCode());
				unusablePassword = false;
			}else {
				System.out.println("Error: password doesn't match");
				System.out.println("Do you want to continue? y or n");
				exitChoice = sr.next();
				if(!exitChoice.equalsIgnoreCase("y"))
					noExit = false;
			}
		}
		
		sr.close();
		
	}
	
	//TODO
	public void logOut() {
		
	}
	
	//TODO
	public void createAccount() {
		boolean unusableID = true;
		boolean noExit = true;
		boolean unusablePassword = true;
		Scanner sr = new Scanner(System.in);
		String uID; //user ID
		String pass;
		String exitChoice = "n";
		
		//User ID
		do {
			//Get UserID Attempt
			System.out.println("Enter a user ID");
			uID = sr.nextLine();
			//Check UserID
			if(accounts.hasUser(uID)) {				
				System.out.println("Error: Username Already Exists\n");
				System.out.println("Try Again? y or n \n");
				exitChoice = sr.next();
				if(!exitChoice.equalsIgnoreCase("y"))
					noExit = false;
			}
			//break from the enter-username-loop
			else { 
				unusableID = false;				
			}//end else
		}while(unusableID && noExit);	
		
		//Password
		//Method 1
//		while(noExit && unusablePassword){ 
//			System.out.println("Enter your password: ");
//			pass = sr.nextLine();
//			unusablePassword = false; //unconditionally exit for now; //TODO add password integrity assurance
//			accounts.createAccount(uID,pass.hashCode());
//		}
		
		//Method 2
		//------------------------------------------------------
		UIUtil ui = new UIUtil();
		boolean needPassword = true;
		String p1 = ""; String p2 = ""; //password double checking
		
		do {
		try {	
			System.out.println("Enter a password: ");
			p1 = sr.nextLine();
			
			this.checkExists(p1);
			System.out.println("Verify password: ");
			p2 = sr.nextLine();
			
			accounts.checkPasswords(p1,p2);			
			needPassword = false;
		}catch(NewPasswordMismatch mm) {
			ui.printException(mm);
			needPassword = ui.determineContinue();
			//TODO test s.close();
		}		
		}while(needPassword);
		if(!needPassword) accounts.createAccount(uID,p1.hashCode());
		//end--------------------------------------------------------
		
		sr.close();
	}

	private void checkExists(String p1) {
		// TODO Auto-generated method stub
		
	}

	public void exit() {
		// TODO Auto-generated method stub
		
	}


}
