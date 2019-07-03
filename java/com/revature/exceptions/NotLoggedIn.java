package com.revature.exceptions;

public class NotLoggedIn extends Exception{
	private String m = "Not logged in";
	
	@Override
	public String getMessage() {
		return this.m;
	}
}
