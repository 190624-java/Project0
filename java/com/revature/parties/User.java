package com.revature.parties;


/**
 * Users can:
 *	login.
 * 	register for a customer account. 
 * @author Jarvis Adams
 *
 */
public class User {

	protected String name;
	protected int driversID;
	
	User(String name, int identification){
		this.name = name;
		this.driversID = identification;
	}
	
	public String getName() {
		return this.name;
	}
	
	//TODO
	public void logIn() {
		
	}
	//TODO
	public void logOut() {
		
	}
	//TODO
	public void createAccount() {
		
	}
	
}
