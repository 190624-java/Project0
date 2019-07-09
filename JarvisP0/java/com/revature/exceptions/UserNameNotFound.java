package com.revature.exceptions;

public class UserNameNotFound extends Exception{
	private String m = "Username not found";
	
	@Override
	public String getMessage() {
		return this.m;
	}
	
}
