package com.revature.collections;

import java.util.LinkedList;

import com.revature.things.Offer;
import com.revature.things.Payment;

/**
 * Once the employee deletes all the other offers, a
 * Contract instance is created and attached to the car.
 * - The accepted offer becomes the Contract 
 * 
 * A Contract class:
 * - manages the amount agreed upon, 
 *   and 
 * - houses the payment history. 
 * - is attached to the Car, so it can contain
 * the history of payments.
 * 
 * AS A PAYMENT HISTORY class
 * - attached to a car.
 * - Allows a user to make a payment on the car, 
 *   - only if the offer is accepted by an employee is this class instantiated
 *    -- so there's no need for a boolean value called accepted.
 * @author Jarvis Adams
 *
 */
public class Contract {

	private float remainingBalance;
	private LinkedList<Payment> payments;
	private Offer soldOffer;
	
	public Contract(Offer offer){
		this.payments = new LinkedList<>();
		this.remainingBalance = offer.getAmount();
		this.soldOffer = new Offer(offer);
	}
}
