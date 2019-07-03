package com.revature.project0;

import java.io.Serializable;

public class Offers implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6174249946293935653L;

	public static int offerID = 1;
	
	private String customer;
	private double offer;
	private int serial;
	private String make;
	public int ID;
	
	public Offers(String customer, double offer, int serial, String make) {
		this.customer = customer;
		this.offer = offer;
		this.serial = serial;
		this.make =  make;
		this.ID =  offerID;
		offerID += 1;
		
	}

	public String getCustomer() {
		return customer;
	}

	public double getOffer() {
		return offer;
	}

	public int getSerial() {
		return serial;
	}
	
	public String getMake() {
		return make;
	}

	
}
