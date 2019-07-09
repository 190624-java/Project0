package com.revature.exceptions;

public class NoParkingAvailable extends Exception{
	private String m = "No parking space was available";
	
	@Override
	public String getMessage() {
		return this.m;
	}
}
