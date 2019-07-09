package com.revature.things.logins;

import com.revature.exceptions.InvalidInput;
import com.revature.exceptions.InvalidMenuSelection;
import com.revature.exceptions.LogOut;
import com.revature.exceptions.UserExit;
import com.revature.parties.Customer;
import com.revature.things.Car;
import com.revature.things.Offer;
import com.revature.things.Password;
import com.revature.utilities.UIUtil;

public class CustomerAccount extends Account{

	
	public CustomerAccount(Customer driverWithID, Password password) {
		super(driverWithID,password);
	}

	@Override
	public int start() throws LogOut {
		//-------------------------------------------
		//Login
		try {
			this.setLoggedIn(true);
		} catch (LogOut e1) {
			e1.printStackTrace();
		}
		//-------------------------------------------
		//Display Menu, Get Selection, Test Selection
		int sel = -1;
		while(isLoggedIn()){
			//Display Menu
			dSys.mPrint.customer();
			try {
				//Get Selection
				sel = UIUtil.getMenuSelection();
				//Test Selection
				switch(serveCustomer(sel)) {
					case 0: return 0; //user chose menu option "exit"
					case -1: break; //something went wrong
					case 1: break; //task complete
				}				
			} catch (UserExit e) { 
				this.logout();
				//return 1;
			} catch (InvalidMenuSelection e) {
				UIUtil.echoProblem("Invalid Selection");
				//if(!UIUtil.determineContinue()) return -1; //something went wrong
			}
		}
		
		//-------------------------------------------
		//React to selection
		try {
			this.serveCustomer(sel);
		} catch (UserExit e) {
			this.logout();
		}
		return 1;
		
	}

	private void logout() throws LogOut{		
		this.setLoggedIn(false);		
	}

	//TODO
	/**
	 * Perform the menu interaction
	 * @throws UserExit 
	 */
	public int serveCustomer(int selection) throws UserExit {			
		
		switch(selection) {
			case 1: //Add a car to the lot				
				dSys.getDealershipLot().parkCar();
				break;
			case 2: //Accept or Reject an Offer
				//go to menu to display offers or find offers associated to a car
				
				break;
			case 3: //Remove a car from the lot
				
				break;
			case 4: //View all payments for a car
				break;
			case 0: //Exit
				throw new UserExit(); //return 0; //exit
		}	
		return -1;
	}
	
	/**
	 * Car will be passed in when Customer is browsing.
	 * Ask for amount
	 * Verify correct amount in loop till (exit || correctDataType_given)
	 * Construct an offer with the car's offer's list, the price, and the Customer
	 * if no errors, return the 1 for pass
	 * else return error code
	 * @param offer
	 * @param amount offer amount
	 */
	public int createOffer(Car carDesired, float amount) { //, Employee salesperson) {
		
		Offer newOffer = new Offer(carDesired, this, amount);
		
		//Add the offer to the carDesired's offers container
		carDesired.getOffersMngr().addOffer(newOffer);
		return 0;
	}
	

	
	@Override
	public Customer getUser() {
		return (Customer)super.getUser();
	}
	
	class CoreFunctionality {
		
	}
	

}
