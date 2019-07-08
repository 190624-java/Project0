package com.revature.things;

import com.revature.parties.DSystem;
import com.revature.parties.Employee;
import com.revature.parties.User;

/**
 * 
 * @author Jarvis Adams
 *
 */
public class Offer {

	//primary
	private Car product;  //primary: carID
	private User offeree; //primary: userID
	
	private float amount;
	private Employee salesperson;
	private int offerID;
	private int termInMonths = 48;
	private float rateAPR = 0.072f; 

	public Offer(Offer offer) {
		this.amount = offer.amount;
		this.product = offer.product;
		this.offeree = offer.offeree;
		this.salesperson = offer.salesperson;
	}
	
	public Offer(float amount, Car product, User offeree, Employee salesperson){
		this.amount = amount;
		this.product = product;
		this.offeree = offeree;
		this.salesperson = salesperson;
		
	}

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
		System.out.println(getOfferID()+"\t\t"+getOfferee().getDriversID()+"\t\t"+this.getAmount());
	}
	
	/**
	 * Displays the data only, so it can be output as rows in the Offers class.
	 * Format: 
	 * OfferID		CarID   	CustomerID  	OfferAmount 
	 */
	public void displayAllCarsRow() {		
		System.out.println(getOfferID()+"\t\t"+getOfferee().getDriversID()+"\t\t"+this.getAmount());
	}

	
	
	public int getOfferID() {
		return offerID;
	}

	public void setOfferID(int offerID) {
		this.offerID = offerID;
	}
	
	public User getOfferee() {
		return offeree;
	}

	public void setOfferee(User offeree) {
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
