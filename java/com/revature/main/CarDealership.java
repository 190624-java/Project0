package com.revature.main;

import com.revature.exceptions.UserExit;
import com.revature.parties.DSystem;
import com.revature.utilities.UIUtil;

public class CarDealership {

	public static final DSystem dSys = new DSystem();
	//public static final UIUtil ui = new UIUtil();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		boolean noExit = true; //exit the program if this "noExit" equals false
		boolean noMainMenuSelection;
		int sel = -1; //menu selection number
		
		do { //Start main menu
		//System.out.flush();
		dSys.giveStartUpMenu();	
		try {
			sel = UIUtil.s.nextInt(); // get the menu selection	
		}
		catch(Exception e) {
			System.out.println("Error: Invalid input.");
			continue;
		}
		noMainMenuSelection = true;
		
		//Handle Menu Selection			
		//Check valid menu choice
		if(sel<0||sel>2) {
			System.out.println("Invalid menu choice. Please try again");
			continue; //noMainMenuSelection = false;
		} else {
		//Call associated choice
			noMainMenuSelection = false;
			switch(sel) {
			case 1: {
				try {
					dSys.beginLogin();
				} catch (UserExit e) {} 				
				break;
			}
			case 2: {
				try {
					dSys.createAccount();
				} catch (UserExit e) {}
				break;
			}
			case 0: {
				//sr.close();
				dSys.exit();
				noExit = false;
				break;
			}
			}
		}
		
		} while(noExit);
		
		//UIUtil.s.close();
		
	}// end main()

	
	
}
