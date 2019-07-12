package com.revature.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;

import com.revature.utilities.ConnectionFactory;
import com.revature.DAOs.UsersDAOImp;
import com.revature.beans.parties.Customer;
import com.revature.beans.parties.Employee;
import com.revature.beans.parties.User;
import com.revature.beans.parties.UserBean;
import com.revature.beans.things.Password;
import com.revature.interfaces.dao.UserDAO;
import com.revature.things.logins.Account;
import com.revature.things.logins.CustomerAccount;
import com.revature.things.logins.EmployeeAccount;
import com.revature.things.logins.UserTypes;
import com.revature.utilities.DSystem;
import com.revature.utilities.UIUtil;
import com.revature.utilities.exceptions.InvalidInput;
import com.revature.utilities.exceptions.LogOut;
import com.revature.utilities.exceptions.NewPasswordMismatch;
import com.revature.utilities.exceptions.NoUppercase;
import com.revature.utilities.exceptions.UserExit;

public class AccountsMngr {
	
	//---------------------------
	//	Fields
	//---------------------------
	
	private HashMap<Integer,Account> accountsMap;
	private EmployeeAccount ownerOfDealership; //used for creation of employee accounts
	protected DSystem dSys;
	private Account activeAccount;
	private Authenticator authenticator;
	
	//---------------------------
	//	Constructors
	//---------------------------
	
	
	public AccountsMngr(EmployeeAccount owner){
		this.accountsMap = new HashMap<>();
		this.loadAccounts();
		this.ownerOfDealership = owner;
		this.dSys = DSystem.getInstance();
		authenticator = new Authenticator();
	}

	//---------------------------
	//	Methods
	//---------------------------
	
	public void loadAccounts() {
		LinkedList<UserBean> beans;
		try {
			beans = (new UsersDAOImp()).getAllUsers();
			Iterator<UserBean> it = beans.iterator();
			UserBean ubean;
			
			while(it.hasNext()) {
				ubean = it.next();
				switch(ubean.getType()) {
				case UserTypes.EMPLOYEE:
					Employee eUser = new Employee(ubean.getUserDriversID());
					try {
						EmployeeAccount eAcc = new EmployeeAccount(eUser, new Password(ubean.getPassword()));
						this.accountsMap.put(ubean.getUserDriversID(), eAcc);
					} catch (NoUppercase e) {
						System.out.println(
								"Warning: password from database user "+
						ubean.getUserDriversID() +"not strong");
						e.printStackTrace();					
					}
					break;
				case UserTypes.CUSTOMER:
					Customer cUser = new Customer(ubean.getUserDriversID());
					try {
						CustomerAccount cAcc = new CustomerAccount(cUser, new Password(ubean.getPassword()));
						this.accountsMap.put(ubean.getUserDriversID(), cAcc);
					} catch (NoUppercase e) {
						System.out.println(
								"Warning: password from database user "+
						ubean.getUserDriversID() +"not strong");
						e.printStackTrace();					
					}
					break;
				}
			}
		} catch (SQLException e1) {
			System.out.println("Couldn't load the accounts.");
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	
	public Authenticator getAuthenticator() {
		return authenticator;
	}

	

	
	
	/**
	 * Additionally checks for type of user.
	 * If choosing Employee, 
	 * - dealership owner's user password must be entered as 
	 *   a form of admin approval to become an employee.
	 * If 
	 * @param driversID
	 * @param passHash
	 */
	public void createAccountType_Menu(int driversID, String pass) throws UserExit, NoUppercase{		
		int choice = -1;		
		boolean needAuthorization = true;
		
		UIUtil ui = new UIUtil();
		boolean doMenu = true;
		
	
		//Display Menu and Get Menu Choice
		do {			
			DSystem.getInstance().mPrint.accountCreationType();			
			try {
				choice = UIUtil.getMenuSelection();
				//if user input/choice is within range of menu options, exit menu with success
				if(choice>0 && choice<4) doMenu = false;
				else {UIUtil.echoProblem("Invalid Choice. Must be a menu number");}
			} catch (InvalidInput e) {}
			
		}while(doMenu);
		
		Password password = new Password(pass);
		
		//Handle the Menu Choice to Create a Particular Account
		switch(choice) {
			case 1: { //employee
					try{this.seekAuthorization();} //throws UserExit
					catch(UserExit ue) {
						throw ue;
					}					
					//------Construct Account
//					Employee employeeWithID = new Employee(driversID);
					this.addEmployeeAccount(driversID, password);
//					this.accountsMap.put(driversID, 
//							new EmployeeAccount(
//									employeeWithID, password
//							)
//							);
					System.out.println("Success! Employee Account Created.");
					break;
			}
			case 2: { //customer
				//------Construct Account
				Customer customerWithID = new Customer(driversID);
				this.accountsMap.put(driversID, new CustomerAccount(
						customerWithID, password
						));
				System.out.println("Success! Customer Account Created.");
				break;
			}
			case 3: { //exit
				throw new UserExit(); 
			}
		} //end switch
		//At this point, the user has accomplished the goal of Account Creation.
		//Next, 
		//Method 1: the user would return to the Main Menu, and login to the account
		//Method 2: the user would continue to the particular account menu of choice
	} //end create account
	
	/**
	 * Add to remote storage
	 * If succeed, Add to local data structure
	 * @param driversID
	 * @param password
	 */
	public void addEmployeeAccount(int driversID, Password password) {
		// TODO Auto-generated method stub
		UsersDAOImp.addUser(driversID, password.getString(), UserTypes.EMPLOYEE);
		Employee employeeWithID = new Employee(driversID);
		this.accountsMap.put(driversID, 
				new EmployeeAccount(
						employeeWithID, password
				)
				);
	}

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
		if(getUserAccount(hirerID).getAccountType() == UserTypes.EMPLOYEE) {
//			if(getUserAccount(hirerID).getAccountType() == UserTypes.DEALER) {
			UIUtil.echoCompletion("Success: Dealer Authority Recognized");
			return true;
		}
		//account didn't have the authority
		UIUtil.echoProblem("Problem: Account Authority Not Recognized. Must be Dealer Account");
		return false;		
	}
//	private boolean seekAuthorization_wDAO(int driversID) {
//		getUserPasswordPS_noProfile(int driversID);
//	}

	/**
	 * Add to remote storage
	 * If succeed, Add to local data structure
	 * @param driversID
	 * @param password
	 */
	public void addCustomerAccount(int driversID, Password password) {
		UsersDAOImp.addUser(driversID, password.getString(), UserTypes.CUSTOMER);
		
		Customer customerWithID = new Customer(driversID);
		this.accountsMap.put(driversID, 
				new CustomerAccount(
						customerWithID, password
				)
				);
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
		return accountsMap.get(driversID);
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
		//Method 2
		//Have the particular account start it's own main and following menus
		try {
			acc.start();
		} catch (LogOut e) {
			save();			
		}
		return;
		//Method 1
		//Determine which type of account is logging in
		//Show corresponding main account menu
//		switch(acc.getAccountType()) {
//			case UserTypes.CUSTOMER:
//				this.dSys.mPrint.customer();
//				
//				break;
//			case UserTypes.EMPLOYEE:
//				this.dSys.mPrint.employee();
//				
//		}
		
	}
	
	//TODO 
	//Save the user's information to the files,
	//Unlink the user
	//Exit the main menu
	public void logOut(Account acc) {
		try {
			this.save();
			acc.setLoggedIn(false);
		} catch (LogOut e) {
			System.out.println("Information saved. Logged out.\n");
		}
	}

	//TODO
	/**
	 * Save data to file
	 */
	private void save() {		
		
	}

	public class Authenticator{
		

		public boolean passwordsMatch(String a, String b) {
			if(a.equals(b)) return true;
			else return false;
		}
		
		
		public boolean passwordMatchesUser(int userID, int passHash) {
			if(accountsMap.get(userID).getPassword().passwordMatches(passHash)) return true;
			else return false;
		}
		
		
		public void checkPasswords(String a, String b) throws NewPasswordMismatch{
			if(passwordsMatch(a,b)) return;
			else throw new NewPasswordMismatch();
		}

		
//		public boolean hasUser(int licenseID, String givenPassword) {
//			String s = UsersDAOImp.getUserPasswordPS_noProfile(licenseID);
//			if(s==null) {
//				System.out.println("Nu");
//			}
//		}
		/**
		 * @deprecated
		 * @param licenseID
		 * @return
		 */
		public boolean hasUser(int licenseID) {
			return accountsMap.containsKey(licenseID);
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

		public String getUserPasswordPS_noProfile(int driversID){

			//public  
			try(Connection conn = ConnectionFactory.getConnection()){
				//Prepare Statement
				//Method 1 - works on SQL Developer, throws exception here.
				String psql = "SELECT password FROM Users WHERE userDriversID = ? ";
				//Method 2 - doesn't work on SQL Developer 
//				String bsql = "SELECT password FROM Users WHERE userDriversID = " + driversID;
				//Method 1
//				String[] cols = {"userDriversID"};
//				PreparedStatement ps = conn.prepareStatement(psql,cols);
				//Method 2
				PreparedStatement ps = conn.prepareStatement(psql);//			
				ps.setInt(1, driversID);
				
				
				//Get Resulting Data
				ResultSet rs = ps.executeQuery();
				//Method 1
				// - clean output
//				if(rs.next()==false) {
//					System.out.println("There was no data returned");
//					return "";
//				}
//				return rs.getString("Password");
				//Method 2
				// - shows exception
				rs.next();
				return rs.getString("password");
				
			}catch(SQLException sqle) {
				System.out.println("Couldn't use connection!");
				//sqle.printStackTrace();
				return null;
			}
//			} catch (IOException e) {
//				System.out.println("IOException happened");
//				e.printStackTrace();
//			};
//			return null;	
		}
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
			}catch(InvalidInput e) {} //error printed in getInt()
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


		public int checkPasswordStrength(String p1) {
			int strength = 0;
			if(Password.hasUppercase(p1)) strength++;
			if(Password.hasLowercase(p1)) strength++;
			return strength;
		}
		
	}

}
