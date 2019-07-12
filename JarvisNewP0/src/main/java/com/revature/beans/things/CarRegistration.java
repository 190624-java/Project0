package com.revature.beans.things;

import java.util.Date;

public final class CarRegistration {

	private final long REG_ID; //Registration for Government ID of Vehicle
	private final String MAKE;
	private final String MODEL;
	private final int YEAR;
	/**
	 * Manufacturer Recommended Retail Price
	 * This is a common factor in users viewing and choosing a car
	 */
	private final float MSRP;
	
	
	
	CarRegistration(long registrationID, String make, String model, int year, float MSRP){
		this.REG_ID = registrationID;
		this.MAKE = make;
		this.MODEL = model;
		this.YEAR = year;
		this.MSRP = MSRP; 
	}
	
	/**
	 * Get the date and time and form a 
	 * @return
	 */
//	private void generateRegistrationID() {
//		
//	}
	
	/**
	 * @return the rEG_ID
	 */
	public long getREG_ID() {
		return REG_ID;
	}



	/**
	 * @return the mAKE
	 */
	public String getMAKE() {
		return MAKE;
	}



	/**
	 * @return the mODEL
	 */
	public String getMODEL() {
		return MODEL;
	}



	/**
	 * @return the yEAR
	 */
	public int getYEAR() {
		return YEAR;
	}



	/**
	 * @return the MSRP
	 */
	protected float getMSRP() {
		return MSRP;
	}
	
}
