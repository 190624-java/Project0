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

	private float amount;
	private Car product;
	private User offeree;
	private Employee salesperson;
	private DSystem dealership;

	public Offer(Offer offer) {
		this.amount = offer.amount;
		this.product = offer.product;
		this.offeree = offer.offeree;
		this.salesperson = offer.salesperson;
		this.dealership = offer.dealership;
	}
	
	public Offer(float amount, Car product, User offeree, Employee salesperson){
		this.amount = amount;
		this.product = product;
		this.offeree = offeree;
		this.salesperson = salesperson;
		this.dealership = salesperson.getEmployer();
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
	
	
	
	
	
}
