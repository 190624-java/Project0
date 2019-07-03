package com.revature.project0;

import java.io.Serializable;

public class Cars implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1962371405172459223L;
	int serialNumber;
	double offer;
	String make;
	int numPayments = 6*12;
	double payments;
	boolean owned = false;
	
	public Cars(double price, String make) {
		this.make = make;
		this.offer = price;
		this.serialNumber = Project0.nextSerial;
		Project0.nextSerial+=1;
		this.payments = this.offer/this.numPayments;
	}
	
	public void setOwner(String username, double offer) {
		this.payments = offer/this.numPayments;
		this.owned = true;
	}
	
	

}
