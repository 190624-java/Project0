package com.revature.services;

import java.util.LinkedList;

import com.revature.beans.things.Contract;
import com.revature.beans.things.Payment;

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
public class ContractMngr {

	//--------------------------
	//	Fields
	//--------------------------
	
	
	//--------------------------
	//	Constructor
	//--------------------------
	
	public ContractMngr(){
		
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
	
	public void displayPaymentRows(Contract c) {
		for(Payment p : c.getPayments()) {
			System.out.println(p.getID()+"    \t"+p.getAmount()+"    \t"+p.getDate());
		}
	}

	public void makePayment(Contract c, Payment payment) {
		LinkedList<Payment> payments = c.getPayments();
		if(payments==null) {
			System.out.println("Error: can't make payment, null list");
			return;
		}
		payments.add(payment);
		c.setBalance(c.getBalance()+payment.getAmount());
	}

	public float getBalance(Contract c) {
		return c.getBalance();
	}

	public float getBill(Contract c) {
		return c.getBill();
	}
}
