package com.revature.things.logins;

import java.util.Iterator;

import com.revature.collections.lots.Lot;
import com.revature.exceptions.InvalidInput;
import com.revature.exceptions.InvalidMenuSelection;
import com.revature.exceptions.LogOut;
import com.revature.exceptions.UserExit;
import com.revature.parties.Customer;
import com.revature.things.Car;
import com.revature.things.Offer;
import com.revature.things.Password;
import com.revature.utilities.SpacesIterator;
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
			} catch (InvalidInput e) {
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
			case 1: //"View Cars on the Dealer Lot"
				//TODO - get the dealer lot
				//TODO - collect all non-empty spaces
				//TODO - call "show 5 cars at a time" Menu, of basic next/off/exit options.
				doDealerLotMenu();
				
				break;
			case 2: //"Make an Offer on a car"
				
				break;
			case 3: //"View My Car Lot"
				
				break;
			case 4: //"View remaining payments for a car"
			
				break;
			case 0:
				throw new UserExit();
		}	
		return -1;
	}
	
	private void doDealerLotMenu() {
		//Build menu
		boolean showNext5 = true;
		boolean choosingSelection = true;
		boolean interactWithOptions = true;
		SpacesIterator lotIt = this.dSys.getDealershipLot().getSpacesIterator();
		SpacesIterator lotItAgain;
		String genOptions="";
		String choice="";
		int index = -1;
		int selection = -1;
		
		
		while(showNext5 && lotIt.hasNext()) {
			
			//Prime Loop
			interactWithOptions = true;
			while(interactWithOptions) {
			
			
			
			//Prime Loop
			choosingSelection = true;
			while(choosingSelection) {
				genOptions = buildNext5Menu(lotIt);
				System.out.println(genOptions);
				System.out.println("(1-5)-(Select) n-(Next Spaces) m-(Make Offer) 0-(Exit)");
				choice = UIUtil.getString().toLowerCase();
				//TODO - check for empty lot spaces and escape if empty.
				index=lotIt.getPosition()-5;
				switch(choice) {
					case "1": 
					case "2":
					case "3":
					case "4":
					case "5":
						selection =  Integer.valueOf(choice)%5;
						choosingSelection = true;
						break;
					case "n": 
						choosingSelection = false;
					case "m":
						choosingSelection = false;
						interactWithOptions = false;
						//TODO - show offer menu
					case "0":
						return;
					
				}
			}//while - interactWithOptions
			}//while - choosingSelection
		}//while	
		
	}

	public String buildNext5Menu(SpacesIterator lotIt){
		return Lot.generateMenuOptions(lotIt,-1);
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
//		return (Customer)super.getUser();
		return (Customer)this.user;
	}
	
	class CoreFunctionality {
		
	}
	

}
