package com.revature.parties;

import com.revature.collections.Lot;
import com.revature.main.UserTypes;
import com.revature.things.Car;
import com.revature.things.Offer;

/**
 * 	As an employee, I can 
 * 	- add a car to the lot.
 * 	- accept or reject an offer for a car.
 * 	- remove a car from the lot.
 * 	- view all payments.
 * 
 * @author Jarvis Adams
 *
 */
public class Employee extends User{

	private Employee employer;
	
	public Employee(int driversID,int passH, Employee dealer) {
		super(driversID, passH);
		this.employer = dealer;
		this.type = UserTypes.EMPLOYEE;
	}

	public Employee(int driversID, int passH) {
		super(driversID, passH);
		this.type = UserTypes.EMPLOYEE;
	}

	//TODO
	public void addCarToLot(Car car, Lot lot) {
		//find an empty location in the lot
	}
	
	/**
	 * 
	 * @param offer
	 * @return 
	 * 	false = refused offer
	 *  true = accepted offer
	 */
	public boolean reactToOffer(Offer offer) {
		float MSRP = offer.getDesiredCar().getMSRP();
		if(offer.getAmount() < Offer.getPercentOff(20, MSRP)) return false;
		processOffer(offer);
		return true;
	}
	
	/**
	 * TODO
	 * Send the offer to the system's list of offers on the car
	 * @param offer
	 */
	private void processOffer(Offer offer) {
		
		
	}
	
	/**
	 * Gets the dealership that the employee is hired by to sale vehicles
	 * @return the dealership
	 */
//	public DSystem getEmployer(){
//		return this.employer;
//	}

	//TODO
	public void removeCarFromLot(Car carToRemove) {
		
	}
	
	//TODO
	public void viewPayments() {

	}
	
}
