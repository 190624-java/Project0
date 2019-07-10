package com.zevyirmiyahu.beans;

public class Employee {
	
	private String userName;
	private short loginPin;
	
	public Employee(String userName, short loginPin) {
		this.userName = userName;
		this.loginPin = loginPin;
	}
	
	public String getUserName() {
		return userName;
	}
	public short getLoginPin() {
		return loginPin;
	}
}
