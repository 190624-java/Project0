package com.revature.parties;

import java.util.Iterator;

import com.revature.collections.Contract;
import com.revature.collections.Offers;
import com.revature.exceptions.InvalidInput;
import com.revature.exceptions.InvalidMenuSelection;
import com.revature.things.Car;
import com.revature.things.Offer;
import com.revature.things.logins.Account;
import com.revature.things.logins.EmployeeAccount.CoreFunctionalityUI;
import com.revature.utilities.DSystem;
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
	//	Fields
	//----------------------
	
	//private Employee employer;
	public CoreFunctionalityUI coreUI = new CoreFunctionalityUI();
	
	
	
	//----------------------
	//	Constructor
	//----------------------
		
	public Employee(int driversID) {
		super(driversID);		
	}

	
	//----------------------
	//	Methods
	//----------------------
	

	
	/**
	 * Displays the park car UI to get a car description from the user
	 * Exits with 
	 * 	-1 if car not found
	 * 	1 - car found, successful park
	 *  0 - car found, failed park
	 * 
	 * 
	 */
	public int parkCar() {		
		long carRegID = -1;
		boolean carNotFound= true;
		
		do {
			System.out.println("Enter the Car Registration ID");
			try {
				carRegID = UIUtil.getLong();
			} catch (InvalidInput e) {
				e.printMessage();				
				//keep checking for Registration ID
				continue;
				//return -1; //go back to the dSys's account menu
			}
			this.account.getLot();
			carNotFound = ;
			
		}while(carNotFound);		
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
	
	
	/**
	 * Gets the dealership that the employee is hired by to sale vehicles
	 * @return the dealership
	 */
//	public DSystem getEmployer(){
//		return this.employer;
//	}


	

	class CoreFunctionalityUI {
		/**
		 * 
		 * @param carToRemove
		 */
		public void removeCarFromLot(Car carToRemove) {
			System.out.println("Enter Car Registration ID");
			long regID = UIUtil.getLong();		
			this.account.
		}
		
		
		public void addCarToLot(Car carToAdd) {
			System.out.println("Enter Car Registration ID");
			long regID = UIUtil.getLong();		
			this.account.
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
		
		//TODO
		public void viewPayments() {

		}
	}
	
}
