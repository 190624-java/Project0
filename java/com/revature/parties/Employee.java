package com.revature.parties;

import java.util.Iterator;

import com.revature.collections.Contract;
import com.revature.collections.Offers;
import com.revature.collections.lots.Lot;
import com.revature.exceptions.InvalidMenuSelection;
import com.revature.things.Car;
import com.revature.things.Offer;
import com.revature.things.logins.Account;
import com.revature.utilities.UIUtil;

/**
 * A class to directly implement person capabilities
 * This class is in contrast to the User's Account which houses the data structures and displays menus.
 * 
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

	//----------------------
	//	Field
	//----------------------
	
	private Employee employer;
	
	
	//----------------------
	//	Constructor
	//----------------------
		
	public Employee(int driversID, Account account, Employee dealer) {
		super(driversID, account);
		this.employer = dealer;
	}

	
	//----------------------
	//	Methods
	//----------------------
	
	//TODO
	public void addCarToLot(Car car, Lot lot) {
		//find an empty location in the lot
		Car space = findEmptyLotSpace(lot);
		if(space==null)
		lot.parkCar(car, spaceNumber)
	}
	

	public void reject(Offer o) {
		// TODO Auto-generated method stub
		this.offers.remove(o);		
	}

	
	public void accept(Offer ao) {
		//create new contract based on offer		
		Contract acceptedOfferContract = new Contract(ao);
		//attach contract to the car
	}
	
//	/**
//	 * (Deprecated)-this is a GUI user decision, not automated
//	 * @param offer
//	 * @return 
//	 * 	false = refused offer
//	 *  true = accepted offer
//	 */
//	public boolean reactToOffer(Offer offer) {
//		float MSRP = offer.getDesiredCar().getMSRP();
//		if(offer.getAmount() < Offer.getPercentOff(20, MSRP)) return false;
//		processOffer(offer);
//		return true;
//	}
	

	public void processOffers1AtATime(Car car) {
		int sel = -1;
		boolean invalidSelection = true;
		Iterator<Offer> oIt = car.getOffersHSetIterator();
		//Iterator<Offer> it = this.offers.iterator();
		Offers offers = car.getOffers();
		Offer o;
		
		//for(Offer o : offers) {
		while(oIt.hasNext()) {
			o = oIt.next();
			o.displayRow();
			offers.displayOfferMenu();			
			
			do {
				try {
					switch(UIUtil.getMenuSelection()) {
						case 1:	//next offer				
							continue;
						case 2: //accept offer 
							accept(o);
							return;
						case 3:	//reject offer	
							reject(o);
							continue;
						case 4:
							break;
						case 0:
							break;
					}
				} catch (InvalidMenuSelection e) { 
					//don't ask to try again, that's what the menu, exit option 0, is for.
				} 				
			} while(invalidSelection);
		}
	}
	
	/**
	 * Gets the dealership that the employee is hired by to sale vehicles
	 * @return the dealership
	 */
//	public DSystem getEmployer(){
//		return this.employer;
//	}

	/**
	 * 
	 * @param carToRemove
	 */
	public void removeCarFromLot(Car carToRemove) {
		System.out.println("Enter Car Registration ID");
		long regID = UIUtil.getLong();		
		this.account.
	}
	
	//TODO
	public void viewPayments() {

	}

	
}
