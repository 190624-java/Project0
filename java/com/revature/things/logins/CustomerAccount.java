package com.revature.things.logins;

import com.revature.exceptions.InvalidMenuSelection;
import com.revature.exceptions.LogOut;
import com.revature.exceptions.UserExit;
import com.revature.utilities.UIUtil;

public class CustomerAccount extends Account{

	
	@Override
	public int start() throws LogOut {
		try {
			this.setLoggedIn(true);
		} catch (LogOut e1) {
			e1.printStackTrace();
		}
		int sel;
		while(isLoggedIn()){
			dSys.mPrint.customer();
			try {
				sel = UIUtil.getMenuSelection();				
				switch(serveCustomer(sel)) {
					case 0: return 0; //user chose menu option "exit"
					case -1: break; //something went wrong
					case 1: break; //task complete
				}				
			} catch (UserExit e) { 
				UIUtil.echo("User Exited Employee Services");
				this.setLoggedIn(false); //throws LogOut
				//return 1;
			} catch (InvalidMenuSelection e) {
				UIUtil.echoProblem("Invalid Selection");
				//if(!UIUtil.determineContinue()) return -1; //something went wrong
			}
		}
		return 1;
		
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
	
}
