package com.revature.utilities;



import java.sql.Connection;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;

import com.revature.collections.AccountsMngr;
import com.revature.collections.LotMngr;
import com.revature.collections.OffersMngr;
import com.revature.exceptions.InvalidInput;
import com.revature.exceptions.InvalidMenuSelection;
import com.revature.exceptions.LogOut;
import com.revature.exceptions.NewPasswordMismatch;
import com.revature.exceptions.NoUppercase;
import com.revature.exceptions.UserExit;
import com.revature.interfaces.BasicsMenu;
import com.revature.interfaces.InputTask;
import com.revature.interfaces.Menu;
import com.revature.parties.Customer;
import com.revature.parties.Employee;
import com.revature.parties.User;
import com.revature.things.Car;
import com.revature.things.Lot;
import com.revature.things.Offer;
import com.revature.things.Password;
import com.revature.things.logins.Account;
import com.revature.things.logins.CustomerAccount;
import com.revature.things.logins.EmployeeAccount;
import com.revature.things.logins.UserTypes;


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
	public final MenuPrinter mPrint;
	public final CoreFunctionality core;
	private final EmployeeAccount dealer;
	private final Menus ms = new Menus();
	
	//Objects
	//---------------------------------------------------------
	private AccountsMngr accountsMngr;
	private OffersMngr offersMngr;
	private LotMngr dLotMngr;
	
	//Data Structures
	//---------------------------------------------------------
	public final LinkedHashSet<Car> carsWithOffers = new LinkedHashSet<>();
	public final LinkedHashSet<Car> carsWithContracts = new LinkedHashSet<>();	
	private Lot dLot;	

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
		this.dLotMngr = new LotMngr();
		this.dLot = new Lot(100,this.dealer);	
		this.offersMngr = new OffersMngr();
		this.mPrint = new MenuPrinter();
		this.core = new CoreFunctionality();
		
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
	
	
	public OffersMngr getOffersManager() {
		return this.offersMngr;
	}
	
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
		
		//Method 2
		try {		
			switch(acc.getAccountType()) {
				case UserTypes.CUSTOMER:					
					((CustomerAccount)acc).start();
				case UserTypes.EMPLOYEE:
					((EmployeeAccount)acc).start();
				case -1: //basic account
					System.out.println("Problem with account type being undefined");
					return;
			}			
		} catch (LogOut e) {
			this.accountsMngr.logOut(acc);
			UIUtil.echo("User Exited Employee Services");
		}	
		
		//Method 1
		//acc.start();
		
		UIUtil.echoCompletion("finished serving account");
	}

	
	
	/**
	 * Gets:<br/>
	 * - Username (i.e. the driversID) <br/>
	 * - Password<br/><br/>
	 * 
	 * Authenticates them as: <br/>
	 * - unique (new relative to existing accounts)<br/>
	 * - valid format<br/><br/>
	 * 
	 * Begins creation or Exits<br/><br/>
	 * 
	 * Has various checks and loops for input corrections.<br/><br/>
	 * 
	 * @throws UserExit exit to main system menu<br/><br/>
	 */
	public void startMainCreateAccount_Menu() throws UserExit {
		//		Local variables

		boolean unusableID = true;
		boolean noExit = true;
		boolean unusablePassword = true;
		
		int driversID = -1; //user ID
		String pass;
		String exitChoice = "n";
		UIUtil ui = new UIUtil();
		
		
		//		Menu Logic
		
		//------------------------------------
		//1. User ID
		//------------------------------------
		do {
			//Get UserID Attempt
			System.out.println("Enter a user ID");
			driversID = -1;
			try { 
				driversID = UIUtil.getInt();
				UIUtil.echo(String.valueOf(driversID));
			}
//			catch (InvalidInput e) {
//				e.printMessage(); //should have been integer
//				continue; //start the loop over to get a User ID
//			}
			catch(InvalidInput e) {
				System.out.println("Invalid entry. ID must be a number.");
				if(UIUtil.determineContinue()) continue; //restart do..while
				else {
					System.out.println("Exiting to main menu");
					return; //exit to main menu
				}
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
		
		

		//------------------------------------
		//2. Password
		//------------------------------------
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
			accountsMngr.createAccountType_Menu(driversID,p1);
		} catch (NoUppercase e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Account Created.\n");
		
		
		//end--------------------------------------------------------
	}

	/**
	 * Save all accounts/users, lots, offers, payments
	 * return nothing, the program is ending.
	 */
	public void saveAll() {
		// TODO Auto-generated method stub
		
	}
	
	public LotMngr getLotManager() {
		return dLotMngr;
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
			LinkedHashSet<Offer> offers = car.getOffers();
			offers.removeAll(offers);
		}
		
		public float calculateMonthlyPayments() {
			
			return 0;			
		}
	}
	
	
	/**
	 *
	 * Prints menus most directly related to the assignment.
	 * @author J
	 * @deprecated
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
		
		public void accountCreationType() {
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
	
	class Menus {
		
		private SystemMenus.AccountCreate<Account> accountCreationMenu;;
		
		class SystemMenus{
			
			public SystemMenus(){
				accountCreationMenu = new SystemMenus.AccountCreate<>();
			}
			
			class Main<T> extends BasicsMenu<T>{			
				
				AccountCreate<T> accountCreationMenu = new AccountCreate<>();
				
				public Main() {
					super("Main",2);
					
				}

				@Override
				public void display() {
					System.out.println("Hello! Welcome to the Dealership System!\n");
					System.out.println("\t\t"+"1 - Login");
					System.out.println("\t\t"+"2 - Create Account");
					System.out.println("\t\t"+"0 - Exit");			
//					System.out.println("Enter a menu number: ");
				}


				@Override
				public void react(T user) {
					switch(this.selection) {
						case 1:
							//TODO - Run LogIn InputTask
						case 2:
							//call account creation menu
							accountCreationMenu.run(null);
							break;
						case 3:
							this.exitMenu();
					}
					
				}
				
			}
		
//			class LogIn<T> extends InputTask<T>{
//	
//				public LogIn() {
//					super("Log-In");
//				}
//	
//				@Override
//				public void display() {
//					// TODO Auto-generated method stub
//					
//				}
//	
//				@Override
//				public void react(T user) {
//					switch(this.selection) {
//						case 1:
//							
//							break;
//						case 2:
//							
//							break;
//						case 3:
//							
//							break;
//					}
//					
//				}
//	
//							
//			}//menu
			
			class AccountCreate<T> extends BasicsMenu<T>{
				
				public AccountCreate() {
					super("Account Creation",2);
				}

				@Override
				public void display() {
					System.out.println("Enter Type of Account: ");
					System.out.println("1 - Employee");
					System.out.println("2 - Customer");
					System.out.println("0 - Exit");
					
				}

				@Override
				public void react(T user) {
					// TODO Auto-generated method stub
					switch(this.selection) {
						case 1:
							//TODO - DSystem.getInstance().accountsMngr.createAccount(//driversID, //pass);
							//employeeMainMenu.react(null);
							//call Employee Creation InputTask
							//employeeMainMenu.run();
							break;
						case 2:
							//TODO - call Customer Creation InputTask
							//customerMainMenu.run();
							break;
						case 3:
							this.exitMenu();
							
					}
				}
				
			}//menu
		
			
		}//System Menus
		
		
		
		class EmployeeMain<T> extends BasicsMenu<T>{

			public EmployeeMain() {
				super("Employee Main",4);
			}

			@Override
			public void display() {
				System.out.println("\t\t"+"1 - Add a car to the lot");
				System.out.println("\t\t"+"2 - Accept or Reject an Offer"); //#TODO
				System.out.println("\t\t"+"3 - Remove a car from the lot"); //#TODO
				System.out.println("\t\t"+"4 - View all payments"); //#TODO
				System.out.println("\t\t"+"0 - Exit");
				
			}

			@Override
			public void react(T user) {
				switch(this.selection) {
					case 1:
						//TODO - CarID InputTask
						//TODO - Create a CreateCar_InputTask
						//TODO - call Employee.core.addCarToLot(car); 
						break;
					case 2:
						//TODO - Get Offers
						//TODO - (For each set of 5 offers or less) || (Until ExitMenu), make a Offers Menu and call it's run
						// this will repeatably allow the core task functions of Employee:
						// - Employee.core.accept(offersList, offerToRemove) (creates a contract, calls the DSystem.core. 
						// - Employee.core.reject(offersList, offerToRemove)						
						break;
					case 3:
						//TODO - Get DealershipLot DSystem.getInstance.dLot;
						
						//Method1
						//TODO - Run the CarID InputTask
						
						//Method2
						//TODO - For each non-empty space in the lot, add the CarSpace to a list
						//TODO - (For every 5 items in the list) || (Until ExitMenu), create and run a RemoveCar Menu
						// this will repeatably allow the core task function of Employee: 
						// - Employee.core.removeCar  (car)||(carID)||(lot,car)||...; 
						break;
					case 4:
						
						break;
					case 0:
						break;
				}
				
			}

						
		}
		
		class CustomerMain<T> extends BasicsMenu<T>{

			public CustomerMain() {
				super("Customer Main",4);
			}

			@Override
			public void display() {
				System.out.println("\t\t"+"1 - View Cars on the Dealer Lot");
				System.out.println("\t\t"+"2 - Make an Offer on a car"); 
				System.out.println("\t\t"+"3 - View My Car Lot");
				System.out.println("\t\t"+"4 - View remaining payments for a car");
				System.out.println("\t\t"+"0 - Exit");
				
			}

			@Override
			public void react(T user) {
				switch(this.selection) {
					case 1: //"View Cars on the Dealer Lot"
						
						break;
					case 2: //"Make an Offer on a car"
						
						break;
					case 3: //"View My Car Lot"
						
						break;
					case 4: //"View remaining payments for a car"
					
						break;
					case 0:
						break;
				}
				
			}

						
		}
		
		
		
		
		
		
		
	}

}
