package com.revature.collections;

import java.util.LinkedHashSet;
import java.util.LinkedList;

import com.revature.exceptions.InvalidMenuSelection;
import com.revature.things.Offer;
import com.revature.utilities.UIUtil;

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
public class Offers {
	
	private LinkedHashSet<Offer> offersHSet;
	
	public LinkedHashSet<Offer> getOffersHSet() {
		return offersHSet;
	}


	public Offers() {
		this.offersHSet = new LinkedHashSet<>();
	}
	
	/**
	 * Show the offers on a car.
	 * 	float amount
	 *	Car product
	 *	User offeree
	 */
	public void displayOffers() {
		System.out.println(
				  "----------------------"
				+ "       Offers"
				+ "----------------------");
		displayOfferColumns();		
		displayOfferRows();
		
	}
	
	
	public void displayOfferColumns() {
		System.out.println("OfferID\t\t"+"CUSTOMER\t\t"+"AMOUNT\t\t");
	}
	
	public void displayOfferMenu(){
		System.out.println(
			"Menu: 1 next   2 accept   3 reject   0 exit");
	}
	
	
	public void displayOfferRows() {
		for(Offer o : offersHSet) {
			o.displayRow();
		}
	};
	

	
	
	
}
