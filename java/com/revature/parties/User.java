package com.revature.parties;

import com.revature.collections.lots.Lot;
import com.revature.things.logins.Account;

/**
 * Users can:
 *	login.
 * 	register for a customer account. 
 * @author Jarvis Adams
 *
 */
public class User {

	Account account;
	protected int driversID;

//	/**
//	 * not needed since the system just requires the unique identifier, driversID.
//	 */
//	protected String username; 
	
	public User(int identification, Account account){
		this.driversID = identification;
		this.account = account;
	}
	
//	public User(int driversID, int passHash) {
//		this(driversID);
//		this.password = passHash;
//		this.garage = new Lot(10,this);
//		this.account = account;
//	}

	
	
//	public String getName() {
//		return this.name;
//	}



	public int getDriversID() {
		return this.driversID;
	}
	
	
}
