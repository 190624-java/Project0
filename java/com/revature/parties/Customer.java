package com.revature.parties;

import com.revature.collections.Lot;
import com.revature.things.Car;
import com.revature.things.Offer;

/*
 * As a customer, I can 
 * 	view the cars on the lot.
 * 	make an offer for a car.
 * 	view the cars that I own.
 * 	view my remaining payments for a car.
 *
 */
public class Customer extends User {

	Lot ownedLot;
	
	public Customer(String name, int identification) {
		super(name, identification);
	}

	/**
	 * Prints the list of cars and their descriptions all at once
	 * @param lotToView
	 */
	public void viewLot(Lot lotToView) {
		lotToView.display();
	}
	
	
	/**
	 * Randomly generates a number between 30 and 0.
	 * That number is used to calculate a percentage off the MSRP.
	 * The result is the offerPrice.
	 * @param offer
	 */
	public Offer makeOffer(Car carDesired, Employee salesperson) {
		float MSRP = carDesired.getMSRP();
		//Offer(float amount, Car product, User offeree, Employee salesperson, DSystem dealership)
		return new Offer(Offer.getPercentOff(generateMarkoff(), MSRP), carDesired, this, salesperson);
	}
	
	/**
	 * Finds a percentage that the customer may ask off the MSRP.
	 * @return a random integer from 0 - 30.
	 */
	private int generateMarkoff() {
		return  ((int)Math.random()) % 30;
	}
	
	//TODO
	public void viewPrivateLot(Lot ownedLot) {
		
	}
	
	//TODO
	public void getPaymentsRemaining(Car ownedCar) {
		
	}
	
	
}
