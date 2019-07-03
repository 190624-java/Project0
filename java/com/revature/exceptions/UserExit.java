package com.revature.exceptions;

public class UserExit extends Exception{
	protected String m = "User requested exit before completion of input";
	
	@Override
	public String getMessage() {
		return this.m;
	}
}
