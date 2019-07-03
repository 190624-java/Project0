package com.revature.parties;

import com.revature.collections.Lot;
import com.revature.things.Account;

/**
 * Users can:
 *	login.
 * 	register for a customer account. 
 * @author Jarvis Adams
 *
 */
public class User {

	Account account;
	Lot garage;
	
//	protected String name;
	protected int driversID;
	private int password;
	int type;
	
	public User(int identification){
		//this.name = name;
		this.driversID = identification;
		this.password = -1;
		this.type=-1;
	}
	
	public User(int driversID, int passHash) {
		this(driversID);
		this.password = passHash;
		this.garage = new Lot();
		this.account = account;
	}

	
	
//	public String getName() {
//		return this.name;
//	}

	
	public void setPassword(int passHash) {
		this.password = passHash;
	}

	public int getPassword() {
		return this.password;
	}

	public int getDriversID() {
		return this.driversID;
	}
	
}
