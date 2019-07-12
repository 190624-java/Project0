package com.revature.utilities.exceptions;

public class UserExit extends Exception{
	protected String m = "User requested exit before completion of input";
	
	public String printMessage() {
		return this.m;
	}
}
