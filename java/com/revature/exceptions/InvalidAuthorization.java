package com.revature.exceptions;

public class InvalidAuthorization extends Exception{
	protected String m = "Error: hiring manager password not accepted";
	
	public String getMessage() {
		return this.m;
	}
	
}
