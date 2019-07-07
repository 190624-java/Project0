package com.revature.parties;



import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

import com.revature.collections.Accounts;
import com.revature.collections.lots.Lot;
import com.revature.exceptions.InvalidInput;
import com.revature.exceptions.NewPasswordMismatch;
import com.revature.exceptions.UserExit;
import com.revature.main.UserTypes;
import com.revature.things.Car;
import com.revature.things.Offer;
import com.revature.things.Password;
import com.revature.things.logins.Account;
import com.revature.things.logins.EmployeeAccount;
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
	
	private static DSystem dealerSystem = new DSystem(); 
	public final MenuPrinter mPrint = new MenuPrinter();
	private Lot dLot;
	
	private Accounts accounts;
	//private String dealershipName;
	private EmployeeAccount dealer;
//	private Scanner scanner;
		
	private DSystem() {
		this.dealer = new EmployeeAccount(-1000000, "admin".hashCode());
		this.accounts = new Accounts(dealer);
		this.dLot = new Lot(100,this.dealer);	
		
	}
	
	public static DSystem getInstance() {
		return dealerSystem;
	};
	

	public static float calMonthlyPayment(User carOwner, Car ownedCar) {
		
		return 0;
		
	}
	
//	/**
//	 * Go through all the offers on the car.
//	 * @param car
//	 */
//	public void rejectPendingOffers(Offer chosenOffer) {
//		Offer keptOffer = new Offer(chosenOffer);
//		Car dCar = chosenOffer.getDesiredCar();
//		Iterator<Offer> it = dCar.getOffersIterator(this);
//	}
	


	
	/**
	 * TODO
	 * Gets user/drivers ID and Password from user input
	 * Checks them for system compatibility
	 * If so, Logs into the accounts object (i.e. continues menus)
	 * Else, returns to main menu.
	 * @throws UserExit 
	 */
	public void beginLogin() throws UserExit {
	//Method 2
		Integer userID = accounts.getAuthenticator().authenticateUser();
		//authenticate will throw an exception if problem occurs and
		//user cancels, otherwise it will not be null.		
		if(userID==null) {
			System.out.println("Error: null authentication");
			return; //authentication cancelled by user
		}
		
		//is already a particular type upon account creation
		Account acc = accounts.getUserAccount(userID.intValue());
		
		//test which type of employee it is.		
		// if it is a Customer, then display customer menu		
		// if it is an employee, then display the employee menu		
		//start that particular menu until logout		
		switch(acc.getAccountType()) {
			case UserTypes.CUSTOMER:
				Logins.serveCustomer();
				break;
			case UserTypes.EMPLOYEE:
				Logins.serveEmployee();
				break;
			case UserTypes.DEALER:
				Logins.serveEmployee();
				break;
		}
		UIUtil.echoCompletion("finished serving account");
	}

	
	
	//TODO
	public void tryCreateAccount() throws UserExit {
		//local variables
		//------------------------------------
		boolean unusableID = true;
		boolean noExit = true;
		boolean unusablePassword = true;
		
		int driversID = -1; //user ID
		String pass;
		String exitChoice = "n";
		UIUtil ui = new UIUtil();
		
		
		//Menu Logic
		//------------------------------------
		//User ID
		do {
			//Get UserID Attempt
			System.out.println("Enter a user ID");
			try { 
				driversID = UIUtil.getInt();
				UIUtil.echo(String.valueOf(driversID));
			}
			catch(InputMismatchException e) {
				System.out.println("Invalid entry. ID must be a number.");
				if(UIUtil.determineContinue()) continue; //restart do..while
				else return;
			} catch (InvalidInput e) {
				e.printMessage(); //should have been integer
			}
			//Check UserID
			if(accounts.hasUser(driversID)) {				
				System.out.println("Error: Username Already Exists\n");
				noExit = UIUtil.determineContinue();
			}
			//userID is valid. 
			else { 
				unusableID = false; //Break from the enter-username-loop 				
			}//end else
		}while(unusableID && noExit);	

		//Password
		boolean needPassword = true;
		String p1 = ""; String p2 = ""; //password double checking
		
		do {
			try {				
				System.out.println("Enter a password: ");
				p1 = UIUtil.s.nextLine();
				UIUtil.echo(p1);				
				
				System.out.println("Verify password: ");
				p2 = UIUtil.s.nextLine();
				UIUtil.echo(p2);
				
				accounts.checkPasswords(p1,p2);
				System.out.println("Password Accepted");
				//no exception, then passwords check.
				needPassword = false; //continue, break from while loop
			}catch(NewPasswordMismatch mm) {
				UIUtil.printException(mm);
				needPassword = UIUtil.determineContinue();
			}		
		}while(needPassword);
		
		if(!needPassword) accounts.createAccount(driversID,p1.hashCode());
		System.out.println("Account Created.\n");
		//end--------------------------------------------------------
	}

	/**
	 * Save all accounts, lots, users
	 * return nothing, the program is ending.
	 */
	public void exit() {
		// TODO Auto-generated method stub
		
	}
	
	public Lot getdLot() {
		return dLot;
	}

	public class MenuPrinter {
		
		public void startUp() {
			System.out.println("Hello! Welcome to the System!\n");
			System.out.println("\t\t"+"1 - Login");
			System.out.println("\t\t"+"2 - Create Account");
			System.out.println("\t\t"+"0 - Exit");
		}		
		
		/**
		 *  view the cars on the lot.
		 * 	make an offer for a car.
		 * 	view the cars that I own.
		 * 	view my remaining payments for a car.
		 */
		public void customer() {
			System.out.println("\t\t"+"1 - View Cars on the Dealer Lot");
			System.out.println("\t\t"+"2 - Make an Offer on a car");
			System.out.println("\t\t"+"3 - View My Car Lot");
			System.out.println("\t\t"+"4 - View remaining payments for a car");
			System.out.println("\t\t"+"0 - Exit");
		}
		
		/**
		 * 	- add a car to the lot.
		 * 	- accept or reject an offer for a car.
		 * 	- remove a car from the lot.
		 * 	- view all payments.
		 * 
		 * @author Jarvis Adams
		 *
		 */
		public void employee() {
			System.out.println("\t\t"+"1 - Add a car to the lot");
			System.out.println("\t\t"+"2 - Accept or Reject an Offer");
			System.out.println("\t\t"+"3 - Remove a car from the lot");
			System.out.println("\t\t"+"4 - View all payments for a car");
			System.out.println("\t\t"+"0 - Exit");
		}
		
		
		
//		/**
//		 * 
//		 * @author Jarvis Adams
//		 *
//		 */
//		public void parkCar() {
//			System.out.println("\t\t"+"1 - ");
//			System.out.println("\t\t"+"2 - ");
//			System.out.println("\t\t"+"3 - ");
//			System.out.println("\t\t"+"4 - ");
//			System.out.println("\t\t"+"0 - Exit");
//		}

		

	}

	

}
