package com.revature.things;

public class Payment {

	private Car carOnLoan;
	private float amount;
	
	/**
	 * User is an implied parameter.
	 * It is associated with the car instance, as the owner of the car.
	 * @param carOnLoan
	 */
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
