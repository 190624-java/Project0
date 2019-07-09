package com.revature.things;

import java.sql.Date;

public class Payment {

	private Car carOnLoan;
	private float amount;
	private int id;
	private java.util.Date date;
	
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

	public int getID() {
		// TODO Auto-generated method stub
		return this.id;
	}

	/**
	 * Gets the amount that the payment was for,
	 * and puts it in 2 decimal places precision
	 * @return
	 */
	public float getAmount() {
		// TODO Auto-generated method stub
		return this.amount;
	}
	/**
	 * Gets the amount that the payment was for,
	 * and puts it in 2 decimal places precision
	 * @return
	 */
	public String printFormatedAmount() {
		// TODO Auto-generated method stub
		return String.format("%.2f", amount);
	}

	/**
	 * Returns the time format from java.util.date
	 * DayOfWeek Month date_2 hh:mm:ss timeZone year_4  
	 * @return
	 */
	public String getDate() {
		// TODO Auto-generated method stub		
		return date.toString();
	}
}
