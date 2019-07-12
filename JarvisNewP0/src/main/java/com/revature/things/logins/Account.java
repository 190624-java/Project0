package com.revature.things.logins;

import java.util.LinkedList;

import com.revature.beans.parties.User;
import com.revature.beans.things.Password;
import com.revature.beans.things.Payment;
import com.revature.utilities.DSystem;
import com.revature.utilities.exceptions.InvalidInput;
import com.revature.utilities.exceptions.InvalidUserID;
import com.revature.utilities.exceptions.LogOut;
import com.revature.utilities.exceptions.NoUppercase;

public abstract class Account {

	//------------------------
	//	Fields
	//------------------------
	
	protected boolean loggedIn = false;
	/**
	 * So checks can be done with authorization.
	 */
	protected int type; //override
	
	//------------------------
	//	Objects
	//------------------------
	
	protected final User user; //set user's account		
	protected Password password;	
	//protected LotMngr lotManager; //TODO construct in other classes; make abstract
	
	
	
	//------------------------
	//	Data Structures
	//------------------------
	
	private LinkedList<Payment> userPaymentHistory;
	
	//------------------------
	//	Constructors
	//------------------------
	
	
	/**
	 * Meant to be overridden
	 * @param userID
	 * @param password //requires creator to have valid password
	 * @param type
	 * @throws NoUppercase 
	 */
	public Account(User userWithID, Password password){		
		this.user = userWithID;
		this.password = password;
		//TODO - test. It might not be constructed yet, and fail the null check
		try {
			this.user.setAccount(this);
		} catch (InvalidUserID | InvalidInput e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.userPaymentHistory = new LinkedList<>();
		this.loggedIn = false;
		

		//---------
		//Overrides
		//---------
		this.type = -1;
		
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


	public LinkedList<Payment> getPaymentHistory() {
		return userPaymentHistory;
	}

	
	public int getUserID() {
		return this.user.getDriversID();
	}



	public abstract int getAccountType();
	
		

	public Password getPassword() {
		return this.password;
	}
	


}
