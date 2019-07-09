package com.revature.parties;

import com.revature.collections.lots.Lot;
import com.revature.exceptions.InvalidInput;
import com.revature.exceptions.InvalidUserID;
import com.revature.things.logins.Account;

/**
 * Users can:
 *	login.
 * 	register for a customer account. 
 * @author Jarvis Adams
 *
 */
public class User {

	protected Account account;
	protected int driversID;

//	/**
//	 * not needed since the system just requires the unique identifier, driversID.
//	 */
//	protected String username; 
	
	public User(int identification){
		this.driversID = identification;		
	}
	
	/**
	 * When a user creates an account, this is assigned.
	 * @param account
	 * @return
	 * @throws InvalidUserID 
	 * @throws InvalidInput if null account
	 */
	public void setAccount(Account account) throws InvalidUserID, InvalidInput {
		if(account == null) throw new InvalidInput();
		if(account.getUserID()!=this.driversID) throw new InvalidUserID(); 
		else 
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
	
	public Account getAccount() {
		return this.account;
	}
}
