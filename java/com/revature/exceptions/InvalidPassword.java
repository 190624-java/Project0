package com.revature.exceptions;

public class InvalidPassword extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 796195437205589513L;
	protected String m = "Invalid Password";
	
	@Override
	public String getMessage() {
		return m;
	}
}
