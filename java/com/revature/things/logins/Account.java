package com.revature.things.logins;

import java.util.LinkedList;

import com.revature.collections.Contract;
import com.revature.collections.lots.Garage;
import com.revature.collections.lots.Lot;
import com.revature.exceptions.LogOut;
import com.revature.exceptions.NoUppercase;
import com.revature.main.UserTypes;
import com.revature.parties.Customer;
import com.revature.parties.DSystem;
import com.revature.parties.Employee;
import com.revature.parties.User;
import com.revature.things.Password;
import com.revature.things.Payment;

public class Account {

	//------------------------
	//	Fields
	//------------------------
	
	private int loginID;	
	private boolean loggedIn = false;
	private int type;
	
	//------------------------
	//	Objects
	//------------------------
	
	private User user;
	protected Lot lot; //TODO construct in other classes; make abstract
	protected DSystem dSys = DSystem.getInstance();
	private Password password;
	
	//------------------------
	//	Data Structures
	//------------------------
	
	private LinkedList<Payment> paymentHistory;
	
	//------------------------
	//	Constructors
	//------------------------
	
	
	public Account() {
		this.loginID = -1;
		try {
			password = new Password("1AaaaBbbb");
		} catch (NoUppercase e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		this.paymentHistory = new LinkedList<>();
		this.type = -1; //unknown
		this.loggedIn = false;
	}
	
	
	/**
	 * Meant to be overridden
	 * @param userID
	 * @param password
	 * @param type
	 * @throws NoUppercase 
	 */
	public Account(int userID, String password, int type) throws NoUppercase {		
		this.loginID = userID;
		this.password = new Password(password);
	}
	
	//------------------------
	//	Methods
	//------------------------		
	
	public boolean isLoggedIn() {
		return loggedIn;
	}
	
	/**
	 * Starts the corresponding account menu and actions
	 * This method is to be overriden by the particular account types.
	 * @throws LogOut 
	 */
	public int start() throws LogOut {
		System.out.println("Error: this function should be overriden");
		return -1;
	}
	
	//------------------------
	//	Getters and Setters
	//------------------------
	
	
	/**
	 * For credentials and main menu capabilities
	 * @return
	 */
	protected User getUser() {
		return this.user;
	}
	

	public void setLoggedIn(boolean loginStatus) throws LogOut {
		if(loginStatus==false) throw new LogOut();
		else this.loggedIn = true;		
	}

	public Lot getLot() {
		return this.lot;
	}

	public LinkedList<Payment> getPaymentHistory() {
		return paymentHistory;
	}

	
	public int getUserID() {
		return this.loginID;
	}


	public int getAccountType() {
		return this.type;
	}
		

	public Password getPassword() {
		return this.password;
	}
	


}
