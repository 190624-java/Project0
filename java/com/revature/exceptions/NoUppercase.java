package com.revature.exceptions;

public class NoUppercase extends InvalidPassword{
	
	public NoUppercase(){
		this.m = this.getMessage()+": Missing an uppercase";
	}
}
