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

	//--------------------------
	//	Fields
	//--------------------------
	
	private float remainingBalance;
	private LinkedList<Payment> payments;
	private Offer soldOffer;
	private float balance;
	private float bill;
	private int termInMonths;
	
	
	//--------------------------
	//	Constructor
	//--------------------------
	
	public Contract(Offer offer){
		this.remainingBalance = offer.getAmount();
		this.payments = new LinkedList<Payment>();
		this.soldOffer = new Offer(offer);
		this.termInMonths = offer.getTerm();
	}

	
	//--------------------------
	//	Methods
	//--------------------------	

	
	public void displayPaymentsHeading() {
		System.out.println("-----------------");
		System.out.println("	Payments"	  );
		System.out.println("-----------------");
		System.out.println("ID     \tAmount      \tDate");
	}
	
	public void displayPaymentRows() {
		for(Payment p : payments) {
			System.out.println(p.getID()+"    \t"+p.getAmount()+"    \t"+p.getDate());
		}
	}

	public void makePayment(Payment payment) {
		if(payments==null) {
			System.out.println("Error: can't make payment, null list");
			return;
		}
		this.payments.add(payment);
		this.balance+=payment.getAmount();
	}

	public float getBalance() {
		return balance;
	}

	public float getBill() {
		return this.bill;
	}
}
