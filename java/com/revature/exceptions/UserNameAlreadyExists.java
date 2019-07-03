package com.revature.exceptions;

public class UserNameAlreadyExists extends InvalidUserID{
	
	public UserNameAlreadyExists(){
		this.m = this.getMessage()+": Username already exists";
	}
	

}
