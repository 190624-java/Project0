package com.revature.things;

import java.util.LinkedList;

import com.revature.main.UserTypes;
import com.revature.parties.Customer;
import com.revature.parties.Employee;
import com.revature.parties.User;

public class Account {

	private String loginID;
	private int password_hashed;
	private User user;
	
	private LinkedList<Payment> paymentHistory;
	
	public Account() {
		this.loginID = "";
		this.password_hashed = 0;
		
		this.paymentHistory = new LinkedList<>();		
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
		
		//Method 1
		//password requires
			//at least 4 characters
			//at least 1 Uppercase
			//at least 1 Lowercase
			//at least 1 number
		
//		boolean go = true;
//		do{
//			try {
//				
//			}catch {
//				
//			}
//			Password.hasUppercase(pass);
//		}while(go);
//		
//		if(password.length()<4) throw new Exception("Not long enough");
//		if(password.matches("([.[^\\s]]*[[a-z]+[A-Z]+[.[^\\s]]*)||([.[^\\s]]*[A-Z]+[a-z]+[.[^\\s]]*)"));
//		//anyNumberOfNonWhitespaceChars* 
//		//(at least 1 lowercase* followed by at least one uppercase )
//		// || 
//		//(at least 1 uppercase, followed by at least 1 lowercase ) 
//		//anyNumberOfNonWhitespaceChars*
		
		
	}
	
	public String getUserID() {
		return this.loginID;
	}
	
	public boolean passwordMatches(int passHash) {
		if(this.password_hashed==passHash) return true;
		else return false;
	}

	
	public void access() {
		// TODO Auto-generated method stub
		
	}
	
	
	//TODO
	public void logIn() {
		
	}
	//TODO
	public void logOut() {
		
	}
}
