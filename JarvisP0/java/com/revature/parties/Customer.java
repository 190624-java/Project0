package com.revature.parties;

import com.revature.collections.lots.Lot;
import com.revature.exceptions.InvalidInput;
import com.revature.main.UserTypes;
import com.revature.things.Car;
import com.revature.things.Offer;
import com.revature.things.logins.Account;
import com.revature.utilities.DSystem;
import com.revature.utilities.UIUtil;

/**
 * A class to directly implement person capabilities
 * This class is in contrast to the User's Account which houses the data structures and displays menus.
 * 
 * As a customer, I can 
 * 	view the cars on the lot.
 * 	make an offer for a car.
 * 	view the cars that I own.
 * 	view my remaining payments for a car.
 *
 */
public class Customer extends User {

	public Customer(int driversID) {
		super(driversID);
	}


	/**
	 * Prints the list of cars and their descriptions all at once
	 * @param lotToView
	 */
	public void viewLot(Lot lotToView) {
		lotToView.display();		
	}
	
	
	


	
	/**
	 * Utility Class for generating an offer
	 * Randomly generates a number between 30 and 0.
	 * That number is used to calculate a percentage off the MSRP.
	 * The result is the offerPrice. 
	 * @param carDesired
	 * @param salesperson
	 * @return
	 */
//	protected Offer generateOffer(Car carDesired, Employee salesperson) {
//			float MSRP = carDesired.getMSRP();
//			//Offer(float amount, Car product, User offeree, Employee salesperson, DSystem dealership)
//			return new Offer(Offer.getPercentOff(generateMarkoff(), MSRP), carDesired, this, salesperson);
//	}
	
	/**
	 * Utility class for quickly creating test values.
	 * Finds a percentage that the customer may ask off the MSRP.
	 * @return a random integer from 0 - 30.
	 */
	protected int generateMarkoff() {
		return  ((int)Math.random()) % 30;
	}
	

	

	
	public void printPaymentsRemaining(float remaining, float bill) {
		String message = String.format("There are %.0f remaining bills of %.2f", remaining, bill);
		System.out.println(message);
	}
	public void printPaymentsRemaining(Car ownedCar) {		
		String message = String.format("There are %.0f remaining bills of %.2f", 
				getPaymentsRemaining(ownedCar), 
				ownedCar.getContract().getBill());
		System.out.println(message);
	}

	class CoreFunctionalityUI {
		/**
		 * Prints the list of cars and their descriptions all at once
		 * @param lotToView
		 */
		public void viewDealerLot() {		
			viewLot(DSystem.getInstance().getDealershipLot());
		}
		
		/**
		 * Displays in table form, the cars in a customer's garage/private_lot
		 * @param ownedLot
		 */
		public void viewPrivateLot(Lot ownedLot) {
			Lot g = this.account.getLot();
			
			System.out.println(
					  "-------------------\n"
					+ "		My Garage\n"
					+ "-------------------");
			//System.out.println("CarID    \tMake    \tModel");
			g.display();
		}
		
		
		/**
		 * Car will be passed in when Customer is browsing.
		 * Ask for amount
		 * Verify correct amount in loop till (exit || correctDataType_given)	 * 
		 * if no errors, return the 1 for pass
		 * else return error code
		 * @param offer
		 * @param amount offer amount
		 */
		public int makeOffer() { 
			Car carDesired;
			float amount = -1.0f;
			
			System.out.println("Enter the car registration ID of the car desired for purchase.");
			try {
				UIUtil.getLong();
			} catch (InvalidInput e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//create and add the offer to the appropriate containers
			this.getUser().createOffer(carDesired, amount);
			return 0;
		}
		
		/**
		 * 
		 * @param ownedCar
		 */
		public float getPaymentsRemaining(Car ownedCar) {			
			float bal = ownedCar.getContract().getBalance();
			float bill = ownedCar.getContract().getBill();
			//calculate
			float remaining = bal/bill;

			return remaining;
		}
	}
	
	
}
