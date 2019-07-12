package com.revature.beans.parties;

import com.revature.services.LotMngr;
import com.revature.things.logins.Account;
import com.revature.utilities.DSystem;
import com.revature.utilities.exceptions.InvalidInput;
import com.revature.utilities.exceptions.InvalidUserID;

/**
 * Users can:
 *	login.
 * 	register for a customer account. 
 * @author Jarvis Adams
 *
 */
public abstract class User {

	protected Account account;
	protected int driversID;
	protected DSystem dSys = DSystem.getInstance();
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
	

	/**
	 * Gets the respective Account Type 
	 * @return
	 */
	abstract public Account getAccount();
//	public Account getAccount() {
//		return this.account;
//	}

	public int getDriversID() {
		return this.driversID;
	}
	

}
