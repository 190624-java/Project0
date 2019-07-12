package com.revature.things.logins;

import java.util.Iterator;

import com.revature.beans.parties.Customer;
import com.revature.beans.things.Car;
import com.revature.beans.things.Lot;
import com.revature.beans.things.Offer;
import com.revature.beans.things.Password;
import com.revature.services.LotMngr;
import com.revature.utilities.DSystem;
import com.revature.utilities.SpacesIterator;
import com.revature.utilities.UIUtil;
import com.revature.utilities.exceptions.InvalidInput;
import com.revature.utilities.exceptions.InvalidMenuSelection;
import com.revature.utilities.exceptions.LogOut;
import com.revature.utilities.exceptions.UserExit;

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
			DSystem.getInstance().mPrint.customer();
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
	
	private void doDealerLotMenu() throws UserExit {
		//Build menu
		boolean showNext5 = true;
		boolean choosingSelection = true;
		boolean interactWithASelection = true;
		SpacesIterator lotIt = DSystem.getInstance().getDealershipLot().getSpacesIterator();
		SpacesIterator lotItAgain = DSystem.getInstance().getDealershipLot().getSpacesIterator();
		SpacesIterator tempIt = DSystem.getInstance().getDealershipLot().getSpacesIterator();
		
		String genOptions="";
		String choice="";
		int selection = -1;
		
		
		//Loop 1------------------------------------------
		//Loop through the next 5 items, until there are no more to show.
		while(showNext5 && lotIt.hasNext()) {			
			selection = -1; //clear selection			
			//------Print Menu-------
			//Print Next Menu
			LotMngr.displayTableHeader();
			lotItAgain.returnState(lotIt);//preset the lotItAgain iterator
			tempIt.returnState(lotItAgain);
			genOptions = buildNext5Menu(lotIt,selection); //advance lotIt			
			System.out.println(genOptions);
			System.out.println("(1-5)-(Select) n-(Next Spaces) m-(Make Offer) 0-(Exit)");			
			
			
			//Loop 2------------------------------------------
			//Allow an offer to be made and return to the same place where left off.
			//Prime Loop
			interactWithASelection = true;			
			while(interactWithASelection) {
			
						
			//Loop 3------------------------------------------
			//Loops through building the same menu, but allowing the user to change the item selected.
			//The buildMenu() requires iterating, so the iterator must be reset with a prior iterator each time it loops

			//Prime Loop
			choosingSelection = true;
			while(choosingSelection) {
				//--------------
				//--Get Choice--
				//--------------
				choice = UIUtil.getString().toLowerCase();
				
				switch(choice) {
					case "0":
						System.out.println("Exiting Customer View Cars Menu");
						throw new UserExit();
					case "1": 
					case "2":
					case "3":
					case "4":
					case "5":						
						selection =  Integer.valueOf(choice)%5; //set the selection value for building the next menu with the selection.
						choosingSelection = true;
						//Check for selected empty lot spaces, and clear selection before next rebuilt menu, if so.
						if( this.hasEmptySelection(genOptions, choice)) {
							System.out.println("Invalid Selection. Try Again.\n");
							selection = -1; //reset selection
														
						}
						//------Print Menu-------
						//Print Same Menu, but with new selection
						LotMngr.displayTableHeader();
						genOptions = buildNext5Menu(tempIt,selection);
						tempIt.returnState(lotItAgain);//return the lotItAgain iterator
						System.out.println(genOptions);
						System.out.println("(1-5)-(Select) n-(Next Spaces) m-(Make Offer) 0-(Exit)");

						break;
					case "n":
						selection = -1; //reset selection to none
						choosingSelection = false; //exit choosingSelectionLoop
						interactWithASelection = false; //exit interactionLoop					
						break;
					case "m":
						selection = -1; //reset selection to none
						choosingSelection = false;						
						//TODO - show offer menu
						//Regen the last Menu without selection
						//Reason: may remove the car sometimes, or may add a car sometimes, and want to reuse the code.
						
						//------Print Menu-------
						//Print Same Menu, clearing the selection, and one of the items might have changed.
						LotMngr.displayTableHeader();
						genOptions = buildNext5Menu(tempIt,selection); //genOptions is the same for the "make offer" case
						tempIt.returnState(lotItAgain);//return the lotItAgain iterator
						System.out.println(genOptions);
						System.out.println("(1-5)-(Select) n-(Next Spaces) m-(Make Offer) 0-(Exit)");						
						break;
										
				}
			}//while - choosingSelection
			}//while - interactWithOptions
		}//while - browsing	
		
	}

	
	public boolean hasEmptySelection(String genOptions, String choice) {
		if(
				(genOptions.contains("1- empty space") && choice.equals("1")) ||
				(genOptions.contains("2- empty space") && choice.equals("2")) ||
				(genOptions.contains("3- empty space") && choice.equals("3")) ||
				(genOptions.contains("4- empty space") && choice.equals("4")) ||
				(genOptions.contains("5- empty space") && choice.equals("5"))
				) return true;
		else return false;
	}
	
	public String buildNext5Menu(SpacesIterator lotIt, int selection){
		return LotMngr.generateMenuOptions(lotIt,selection);
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
	

	public Lot getLot() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAccountType() {		
		return UserTypes.CUSTOMER;
	}
	

}
