package com.revature.utilities;



import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;

import com.revature.collections.AccountsMngr;
import com.revature.collections.lots.Lot;
import com.revature.exceptions.InvalidInput;
import com.revature.exceptions.LogOut;
import com.revature.exceptions.NewPasswordMismatch;
import com.revature.exceptions.NoUppercase;
import com.revature.exceptions.UserExit;
import com.revature.main.UserTypes;
import com.revature.parties.Employee;
import com.revature.things.Car;
import com.revature.things.Offer;
import com.revature.things.Password;
import com.revature.things.logins.Account;
import com.revature.things.logins.EmployeeAccount;


/*--------------------------------
 * Project0 Assignment verbatim
 * -------------------------------
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

/* 
* ------------------------------------
* Organized from Project0 Assignment:
* ------------------------------------
* As a USER, I can 
* 	login.
* 	register for a customer account.
* 
* 
* As an EMPLOYEE, I can 
* 	add a car to the lot.
* 	accept or reject an offer for a car.
* 	remove a car from the lot.
* 	view all payments.
* 
* 
* 
* As a CUSTOMER, I can 
* 	view the cars on the lot.
* 	make an offer for a car.
* 	view the cars that I own.
* 	view my remaining payments for a car.
* 
* 
* 
* As the SYSTEM, I 
* 	reject all other pending offers for a car when an offer is accepted.
* 	calculate the monthly payment.
*/



/**
 * 
 * As the system, I 
 * 	reject all other pending offers for a car when an offer is accepted.
 * 	calculate the monthly payment.
 * 
 */
public class DSystem {
	//---------------------------------------------------------
	//	Fields
	//---------------------------------------------------------
	
	//Local Class Objects
	//---------------------------------------------------------
	public final MenuPrinter mPrint = new MenuPrinter();
	public final CoreFunctionality core = new CoreFunctionality();
	private final EmployeeAccount dealer;
	
	//Objects
	//---------------------------------------------------------
	private Lot dLot;	
	private AccountsMngr accountsMngr;
	
	//Data Structures
	//---------------------------------------------------------
	public final LinkedHashSet<Car> carsWithOffers = new LinkedHashSet<>();
	public final LinkedHashSet<Car> carsWithPayments = new LinkedHashSet<>();

	//---------------------------------------------------------
	//	Singleton
	//---------------------------------------------------------
	
	private static DSystem dealerSystem = new DSystem();	
	
	//Singleton Constructor
	//---------------------------------------------------------
	private DSystem() {
		Password dPass = null;
		try {
			dPass = new Password("1AaaaBbbb");
		} catch (NoUppercase e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		this.dealer = new EmployeeAccount(new Employee(-10000),dPass);		
		this.accountsMngr = new AccountsMngr(dealer);
		this.dLot = new Lot(100,this.dealer);	
		
	}	
	
	//Singleton Instance Method
	//---------------------------------------------------------
	public static DSystem getInstance() {
		return dealerSystem;
	};
	

	
//	/**
//	 * Go through all the offers on the car.
//	 * @param car
//	 */
//	public void rejectPendingOffers(Offer chosenOffer) {
//		Offer keptOffer = new Offer(chosenOffer);
//		Car dCar = chosenOffer.getDesiredCar();
//		Iterator<Offer> it = dCar.getOffersIterator(this);
//	}
	


	//---------------------------------------------------------
	//	Methods
	//---------------------------------------------------------
	
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
		Integer userID = accountsMngr.getAuthenticator().authenticateUser();
		//authenticate will throw an exception if problem occurs and
		//user cancels, otherwise it will not be null.		
		if(userID==null) {
			System.out.println("Error: null authentication");
			return; //authentication cancelled by user
		}
		
		//is already a particular type upon account creation
		Account acc = accountsMngr.getUserAccount(userID.intValue());
		
		//test which type of employee it is.		
		// if it is a Customer, then display customer menu		
		// if it is an employee, then display the employee menu		
		//start that particular menu until logout		
		try {			
			acc.start();
		} catch (LogOut e) {
			this.accountsMngr.logOut(acc);
		}		
		UIUtil.echoCompletion("finished serving account");
	}

	
	
	/**
	 * Gets:
	 * - Username (i.e. the driversID) 
	 * - Password
	 * 
	 * Authenticates them as: 
	 * - unique (new relative to existing accounts)
	 * - valid format
	 * 
	 * Begins creation or Exits
	 * 
	 * Has various checks and loops for input corrections.
	 * 
	 * @throws UserExit exit to main system menu
	 */
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
			driversID = -1;
			try { 
				driversID = UIUtil.getInt();
				UIUtil.echo(String.valueOf(driversID));
			}
			catch(InputMismatchException e) {
				System.out.println("Invalid entry. ID must be a number.");
				if(UIUtil.determineContinue()) continue; //restart do..while
				else {
					System.out.println("Exiting to main menu");
					return; //exit to main menu
				}
			} catch (InvalidInput e) {
				e.printMessage(); //should have been integer
				continue; //start the loop over to get a User ID
			}
			//Check UserID
			if(accountsMngr.getAuthenticator().hasUser(driversID)) {				
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
				
				if(this.accountsMngr.getAuthenticator().checkPasswordStrength(p1)<2) {
					System.out.println("Invalid Password: must have at least 1 capital and 1 lowercase");
					continue;
				}
				
				System.out.println("Verify password: ");
				p2 = UIUtil.s.nextLine();
				UIUtil.echo(p2);
				
				accountsMngr.getAuthenticator().checkPasswords(p1,p2);
				System.out.println("Password Accepted");
				//no exception, then passwords check.
				needPassword = false; //continue, break from while loop
			}catch(NewPasswordMismatch mm) {
				UIUtil.printException(mm);
				needPassword = UIUtil.determineContinue();
			}		
		}while(needPassword);
		
		try {
			accountsMngr.createAccount(driversID,p1);
		} catch (NoUppercase e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public Lot getDealershipLot() {
		return dLot;
	}

	

	//---------------------------------------------------------
	//	Classes
	//---------------------------------------------------------	
	
	public class CoreFunctionality{
		
		/**
		 * TODO - test if the removeAll operation will remove all the offers from the same set.
		 * 
		 * From Project0:
		 * "reject all other pending offers for a car"+
		 *  "when an offer is accepted."
		 */
		public void rejectOtherOffers(Car car) {
			LinkedHashSet<Offer> offers = car.getOffersMngr().getOffersHSet();
			offers.removeAll(offers);
		}
		
		public float calculateMonthlyPayments() {
			
			return 0;			
		}
	}
	
	
	/**
	 * Prints menus most directly related to the assignment.
	 * @author J
	 *
	 */
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
f		 * 	- remove a car from the lot.
		 * 	- view all payments.
		 */
		public void employee() {
			System.out.println("\t\t"+"1 - Add a car to the lot");
			System.out.println("\t\t"+"2 - Accept or Reject an Offer"); //#TODO
			System.out.println("\t\t"+"3 - Remove a car from the lot"); //#TODO
			System.out.println("\t\t"+"4 - View all payments"); //#TODO
			System.out.println("\t\t"+"0 - Exit");
		}
		
		public void accountCreation() {
			System.out.println("Enter Type of Account: ");
			System.out.println("1 - Employee");
			System.out.println("2 - Customer");
			System.out.println("0 - Exit");
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

	class FileSaver {
		
	}
	
	class FileLoader{
		
	}

}
