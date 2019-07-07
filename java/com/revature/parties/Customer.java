package com.revature.parties;

import com.revature.collections.lots.Lot;
import com.revature.main.UserTypes;
import com.revature.things.Car;
import com.revature.things.Offer;
import com.revature.things.logins.Account;

/**
 * A class to directly implement person capabilities
 * This class is in contrast to the User's Account which houses the data structures and displays menus.
 * 
 * As a customer, I can 
 * 	view the cars on the lot.
 * 	make an offer for a car.
 * 	view the cars that I own.
 * 	view my remaining payments for a car.
 *
 */
public class Customer extends User {

	public Customer(int driversID, Account account) {
		super(driversID,account);
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
	 * 
	 * Car will be passed in when Customer is browsing.
	 * Ask for amount
	 * Verify correct amount in loop till (exit || correctDataType_given)
	 * Construct an offer with the car's offer's list, the price, and the Customer
	 * if no errors, return the 1 for pass
	 * else return error code
	 * @param offer
	 */
	public int makeOffer(Car carDesired ) { //, Employee salesperson) {
				
		
	}
	
//	/**
//	 * Finds a percentage that the customer may ask off the MSRP.
//	 * @return a random integer from 0 - 30.
//	 */
//	private int generateMarkoff() {
//		return  ((int)Math.random()) % 30;
//	}
	
	/**
	 * Displays in table form, the cars in a customer's garage/private_lot
	 * @param ownedLot
	 */
	public void viewPrivateLot(Lot ownedLot) {
		Lot g = this.account.getGarage();
		
		System.out.println(
				  "-------------------\n"
				+ "		My Garage\n"
				+ "-------------------");
		//System.out.println("CarID    \tMake    \tModel");
		g.display();
	}
	
	/**
	 * 
	 * @param ownedCar
	 */
	public float getPaymentsRemaining(Car ownedCar) {
		float bal = ownedCar.getContract().getBalance();
		float bill = ownedCar.getContract().getBill();
		//calculate
		float remaining = bal/bill;

		return remaining;
	}
	
	public void printPaymentsRemaining(float remaining, float bill) {
		String message = String.format("There are %.0f remaining bills of %.2f", remaining, bill);
		System.out.println(message);
	}
	public void printPaymentsRemaining(Car ownedCar) {		
		String message = String.format("There are %.0f remaining bills of %.2f", 
				getPaymentsRemaining(ownedCar), 
				ownedCar.getContract().getBill());
		System.out.println(message);
	}

	
	
}
