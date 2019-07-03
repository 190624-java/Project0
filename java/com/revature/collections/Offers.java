package com.revature.collections;

import java.util.LinkedList;

import com.revature.things.Offer;

/**
 * A history of offers attached to a car.
 * The history is removed once the employee accepts an offer.
 * 
 * When an offer is accepted:
 * - All further offers are still not blocked.
 *   - Must change the lot of the car, so that the employee nor 
 *   other customers can not search for car again.
 *   
 * Allows the employee to delete all the other offers.
 * When this happens:
 * - The offer becomes a Contract (a Contract class manages 
 * the amount agreed upon, and houses the payment history). 
 * It is also attached to the Car.
 * 
 * @author Jarvis Adams
 *
 */
public class Offers {
	
	private LinkedList<Offer> offers;
	
	public Offers() {
		this.offers = new LinkedList<>();
	}
	
	
}
