package com.revature.utilities.exceptions;

public class NotLoggedIn extends Exception{
	private String m = "Not logged in";
	
	@Override
	public String getMessage() {
		return this.m;
	}
}
