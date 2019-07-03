package com.revature.things;

public class Payment {

	private Car carOnLoan;
	private float amount;
	
	public Payment(Car carOnLoan) {
		this.carOnLoan = carOnLoan; 
	}
	
	/**
	 * TODO can check for:
	 * - amount over remaining balance
	 * - negative
	 * - above required
	 * 
	 * @param amount
	 */
	public void setPayment(float amount) {
		this.amount = amount;
	}
}
