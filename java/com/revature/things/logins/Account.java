package com.revature.things.logins;

import java.util.LinkedList;

import com.revature.collections.Contract;
import com.revature.collections.lots.Garage;
import com.revature.exceptions.NoUppercase;
import com.revature.main.UserTypes;
import com.revature.parties.Customer;
import com.revature.parties.Employee;
import com.revature.parties.User;
import com.revature.things.Password;
import com.revature.things.Payment;

public class Account {

	private String loginID;
	Password password; 
	//private int password_hashed;
	private User user;
	private Contract contract;
	private Garage garage;
	
	private LinkedList<Payment> paymentHistory;
	
	public Account() {
		this.loginID = "";
		try {
			password = new Password("-1A");
		} catch (NoUppercase e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		this.paymentHistory = new LinkedList<>();
		this.garage = new Garage(10,null);
	}
	
	//TODO
	public Account(int userID, int password, int type) {
		//Method 2
		switch(type) {
		case UserTypes.CUSTOMER:
			this.user = new Customer(userID, password);
			break;
		case UserTypes.EMPLOYEE:
			this.user = new Employee(userID, password);
			break;
		case UserTypes.DEALER:
			this.user = new Employee(userID, password);
		}	
	}
	
	public String getUserID() {
		return this.loginID;
	}


	public int getAccountType() {
		return user.getType();
	}
	

}
