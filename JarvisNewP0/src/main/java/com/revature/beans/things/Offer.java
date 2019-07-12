package com.revature.beans.things;

import com.revature.beans.parties.Employee;
import com.revature.beans.parties.User;
import com.revature.things.logins.Account;
import com.revature.utilities.DSystem;

/**
 * 
 * @author Jarvis Adams
 *
 */
public class Offer {

	//primary
	private Car product;  //primary: carID
	private Account offeree; //primary: userID
	
	private float amount;
	//private Employee salesperson; //(deprecated) would need if organizing data by salesperson, but not required for this assignment
	private int offerID; //TODO 
	private int termInMonths = 48;
	private float rateAPR = 0.072f; 

	public Offer(Offer offer) {
		this.amount = offer.amount;
		this.product = offer.product;
		/**
		 * Note: Need the "offeree" User when constructing.
		 * Reason: need to know who made the offer
		 * and which account to later set the contract to.
		 */
		this.offeree = offer.offeree;		
	}
	
	public Offer(Car product, Account offeree, float amount) { //, Employee salesperson){
		this.amount = amount;
		this.product = product;
		this.offeree = offeree;
		//this.salesperson = salesperson;		
	}

	
	/**
	 * Note: Need the "offeree" user.
	 * Reason: need to know who made the offer
	 * and which account to later set the contract to.
	 */
//	public Offer(Car carDesired, float amount2) {
//		this.product = carDesired;		
//	}

	/**
	 * Returns give % off the MSRP
	 */
	public static float getPercentOff(int percentOff, float mmrp) {
		return mmrp - (mmrp * percentOff);
	}
	
	public float getAmount() {
		return this.amount;
	}
	
	public Car getDesiredCar() {
		return this.product;
	}
	
	/**
	 * Displays the data only, so it can be output as rows in the Offers class.
	 * Format: 
	 * OfferID		CustomerID		OfferAmount 
	 */
	public void displayOneCarsRow() {		
		System.out.println(getOfferID()+"\t\t"+getOfferee().getUserID()+"\t\t"+this.getAmount());
	}
	
	/**
	 * Displays the data only, so it can be output as rows in the Offers class.
	 * Format: 
	 * OfferID		CarID   	CustomerID  	OfferAmount 
	 */
	public void displayAllCarsRow() {		
		System.out.println(getOfferID()+"\t\t"+getOfferee().getUserID()+"\t\t"+this.getAmount());
	}

	
	
	public int getOfferID() {
		return offerID;
	}

	public void setOfferID(int offerID) {
		this.offerID = offerID;
	}
	
	public Account getOfferee() {
		return offeree;
	}

	public void setOfferee(Account offeree) {
		this.offeree = offeree;
	}

	public int getTerm() {		
		return this.termInMonths;
	}
	
	/**
	 * Annual Percentage Rate
	 * Compounded annually on the remaining balance
	 * @return
	 */
	public float getRate() {
		return this.rateAPR;
	}
	
	
}
