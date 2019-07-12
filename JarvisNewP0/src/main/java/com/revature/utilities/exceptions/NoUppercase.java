package com.revature.utilities.exceptions;

public class NoUppercase extends InvalidPassword{
	
	public NoUppercase(){
		this.m = this.getMessage()+": Missing an uppercase";
	}
	
	@Override
	public String getMessage() {
		return this.m;
	}
}
