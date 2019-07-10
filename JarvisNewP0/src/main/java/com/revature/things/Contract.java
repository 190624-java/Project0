package com.revature.things;

import java.util.LinkedList;

public class Contract {
	private float remainingBalance;
	private LinkedList<Payment> payments;
	private Offer soldOffer;
	private float balance;
	private float bill;
	private int termInMonths;
	
	
	//--------------------------
	//	Constructor
	//--------------------------
	
	/**
	 * Records all field data. <br/>
	 * Does not set the contract on the car.
	 * @param acceptedOffer
	 */
	public Contract(Offer acceptedOffer){
		this.remainingBalance = acceptedOffer.getAmount();
		this.payments = new LinkedList<Payment>();
		this.soldOffer = new Offer(acceptedOffer);
		this.termInMonths = acceptedOffer.getTerm();		
	}


	public float getBalance() {
		return balance;
	}


	public void setBalance(float balance) {
		this.balance = balance;
	}


	public float getBill() {
		return bill;
	}


	public void setBill(float bill) {
		this.bill = bill;
	}


	public float getRemainingBalance() {
		return remainingBalance;
	}


	public LinkedList<Payment> getPayments() {
		return payments;
	}


	public Offer getSoldOffer() {
		return soldOffer;
	}


	public int getTermInMonths() {
		return termInMonths;
	}
}
