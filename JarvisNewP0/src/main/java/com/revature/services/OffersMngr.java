package com.revature.services;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import com.revature.beans.things.Car;
import com.revature.beans.things.Offer;
import com.revature.utilities.DSystem;
import com.revature.utilities.UIUtil;
import com.revature.utilities.exceptions.InvalidMenuSelection;

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
public class OffersMngr {
	
	//--------------------------
	//	Fields
	//--------------------------
	
	//Car carNeedingManagement;
	
	//--------------------------
	//	Containers
	//--------------------------
	
	//private LinkedHashSet<Offer> offersHSet;

	
	//--------------------------
	//	Constructor
	//--------------------------

	public OffersMngr() {
		//this.offersHSet = new LinkedHashSet<>();
	}
	
	
	//--------------------------
	//	Methods
	//--------------------------
	
	public LinkedHashSet<Car> getCarsWithOffersHSet() {
		//return offersHSet;
		return DSystem.getInstance().carsWithOffers;
	}
	
	
	/**
	 * -Adds the offer to the current car's offers hash set.
	 * -Tries to add the car to the DSys's carsWithOffers container 
	 * (but it won't if it is already there) 
	 * @param offer
	 */
	public void addOffer(Offer offer) {
		this.getCarsWithOffersHSet().add(offer.getDesiredCar());
		//Add the car to the cars with Offers container 
		DSystem.getInstance().carsWithOffers.add(offer.getDesiredCar());
	}
	
	/**
	 * -Removes the offer from the current car's offers hash set
	 * -Checks to see if the offers container is empty, and if so, removes the associated car from DSys's carsWithOffers 
	 * @param offer
	 */
	public void removeOffer(Offer offer) {
		this.getCarsWithOffersHSet().remove(offer);
		//if no more offers, then remove the car from the DSystem's offers container
		if(this.getCarsWithOffersHSet().isEmpty()) 
			DSystem.getInstance().carsWithOffers.remove(offer.getDesiredCar());		
	}
	
	
	/**
	 * Show the offers on a car.
	 * 	float amount
	 *	Car product
	 *	User offeree
	 */
	public void displayOffers(Car car) {
		System.out.println(
				  "----------------------"
				+ "       Offers"
				+ "----------------------");
		displayAllCarsOfferColumns();		
		displayOfferRows(car);
		
	}
	
	
	public void displayAllCarsOfferColumns() {
		System.out.println("OfferID\t\t"+"CAR\t\t"+"CUSTOMER\t\t"+"AMOUNT\t\t");
	}
	
	public void displayOneCarsOfferColumns() {
		System.out.println("OfferID\t\t"+"CUSTOMER\t\t"+"AMOUNT\t\t");
	}
	
	public void displayOfferMenu(){
		System.out.println(
			"Menu: 1 next   2 accept   3 reject   0 exit");
	}
	
	
	public void displayOfferRows(Car car) {
		Iterator<Offer> offers = car.getOffersHSetIterator();
		while(offers.hasNext()) {
			offers.next().displayOneCarsRow();
		}
//		for(Offer o : ) {
//			o.displayOneCarsRow();
//		}
	};
	

	
	
	
}
