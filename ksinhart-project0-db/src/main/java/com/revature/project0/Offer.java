package com.revature.project0;

import java.io.Serializable;

public class Offer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5912732723815892002L;
	
	//private boolean accepted;
	private String username;
	private String license;
	//private Customer customer;
	//private Car car;
	private double amt;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public double getAmt() {
		return amt;
	}
	public void setAmt(int amt) {
		this.amt = amt;
	}
	
	public Offer(String customer, String car, double amt) {
		super();
		//this.accepted=false;
		this.username = customer;
		this.license = car;
		this.amt = amt;
	}
	
	@Override
	public String toString() {
		return this.getUsername()+" "+this.getLicense()+" "+this.getAmt();
		
	}
	
	
}
