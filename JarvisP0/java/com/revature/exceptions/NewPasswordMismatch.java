package com.revature.exceptions;

public class NewPasswordMismatch extends InvalidPassword{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6779713795387489416L;

	
	public NewPasswordMismatch(){
		this.m += "Password Mismatch";
	}
	
	@Override
	public String getMessage() {
		return this.m;
	}
	
}
