package com.revature.exceptions;

public class InvalidUserID extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4915502210723898705L;
	protected String m = "Invalid User ID";
	
	@Override
	public String getMessage() {
		return this.m;
	}
}
