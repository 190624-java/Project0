package com.revature.exceptions;

public class InvalidInput extends Exception {
	String m; //message
	
	public InvalidInput(String message) {
		this.m = message;
	}


	public InvalidInput() {
		this.m = "Invalid Input";
	}


	public void printMessage() {
		System.out.println(m);
	}
}
