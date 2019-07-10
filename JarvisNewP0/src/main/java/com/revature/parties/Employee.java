package com.revature.parties;

import java.util.Iterator;
import java.util.LinkedHashSet;

import com.revature.collections.OffersMngr;
import com.revature.exceptions.InvalidInput;
import com.revature.exceptions.InvalidMenuSelection;
import com.revature.exceptions.NoParkingAvailable;
import com.revature.things.Car;
import com.revature.things.Contract;
import com.revature.things.Offer;
import com.revature.things.logins.Account;
import com.revature.things.logins.EmployeeAccount;
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
public class Employee extends User {

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
	 * TODO - complete
	 * Displays the park car UI to get a car description from the user
	 * Exits with 
	 * 	-1 if car not found
	 * 	1 - car found, successful park
	 *  0 - car found, failed park
	 *  @deprecated 
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
			
			//TODO - where left off in dev
			//TODO - this.account.getLot();
			//carNotFound = ;
			
		}while(carNotFound);
		
		//TODO-check return value logic
		return -1;
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

//	public DSystem getEmployer(){
//		return this.employer;
//	}
	
	/**
	 * Gets the dealership that the employee is hired by to sale vehicles
	 * @return the dealership
	 */	
	public class CoreFunctionalityUI {


		
		/**
		 * 
		 * @param carToRemove
		 * @throws InvalidInput 
		 */
		public void removeCarFromLot(Car carToRemove) throws InvalidInput {					
			dSys.getLotManager().removeCar(dSys.getDealershipLot(),carToRemove);
		}
		/**
		 * 
		 * @param carToRemove
		 * @throws InvalidInput 
		 */
		public void removeCarFromLot() throws InvalidInput {
			System.out.println("Enter Car Registration ID");
			long regID = UIUtil.getLong();		
			dSys.getLotManager().searchForCarByID(dSys.getDealershipLot(),regID);
		}
		
		
		public void addCarToLot(Car carToAdd) throws NoParkingAvailable {						
			dSys.getLotManager().addCar(dSys.getDealershipLot(), carToAdd);
		}
		
		public void addCarToLot() throws InvalidInput {
			System.out.println("Enter Car Registration ID");
			long regID = UIUtil.getLong();		
			dSys.getLotManager().searchForCarByID(dSys.getDealershipLot(), regID);
			
			//Method2
			//dSys.getLotManager().addCar(dSys.getDealershipLot(), carToAdd);
		}
		

		/**
		 * Removes offer from the DSystem.
		 * @param o
		 */
		public void reject(Offer o) {
			dSys.getOffersManager().getCarsWithOffersHSet().remove(o);		
		}

		/**
		 * Creates a contract
		 * Adds the contract to the car.
		 * @param ao
		 */
		public void accept(Offer ao) {
			//create new contract based on offer				
			Contract acceptedOfferContract = new Contract(ao);
			//attach contract to the car
			ao.getDesiredCar().setContract(acceptedOfferContract);
		}	


		/**
		 * @deprecated
		 * @param car
		 */
		public void processOffers1AtATime(Car car) {
			int sel = -1;
			boolean invalidSelection = true;
			Iterator<Offer> oIt = car.getOffersHSetIterator();
			//Iterator<Offer> it = this.offers.iterator();
			LinkedHashSet<Offer> offers = car.getOffers();
			Offer o;
			
			//for(Offer o : offers) {
//			while(oIt.hasNext()) {
//				o = oIt.next();
//				o.displayRow();
//				offers.displayOfferMenu();			
//				
//				do {
//					try {
//						switch(UIUtil.getMenuSelection()) {
//							case 1:	//next offer				
//								continue;
//							case 2: //accept offer 
//								accept(o);
//								return;
//							case 3:	//reject offer	
//								reject(o);
//								continue;
//							case 4:
//								break;
//							case 0:
//								break;
//						}
//					} catch (InvalidMenuSelection e) { 
//						//don't ask to try again, that's what the menu, exit option 0, is for.
//					} 				
//				} while(invalidSelection);
//			}
		}
		
		//TODO
		public void viewPayments() {

		}
	}



	@Override
	public EmployeeAccount getAccount() {		
		return (EmployeeAccount) this.account;
	}
	
}
