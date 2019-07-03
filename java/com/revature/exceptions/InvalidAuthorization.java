package com.revature.exceptions;

public class InvalidAuthorization {
	protected String m = "Error: hiring manager password not accepted";
	
	public String getMessage() {
		return this.m;
	}
	
}
