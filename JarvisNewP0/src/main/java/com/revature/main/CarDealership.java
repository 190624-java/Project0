package com.revature.main;

import com.revature.exceptions.InvalidInput;
import com.revature.exceptions.InvalidMenuSelection;
import com.revature.exceptions.UserExit;
import com.revature.utilities.DSystem;
import com.revature.utilities.UIUtil;

public class CarDealership {

	public static final DSystem dSys = DSystem.getInstance();
	//public static final UIUtil ui = new UIUtil();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		boolean noExit = true; //exit the program if this "noExit" equals false
//		boolean noMainMenuSelection;
		int sel = -1; //menu selection number
		
		do { //Start main menu
			//System.out.flush();
			dSys.mPrint.startUp();	
			
			//Get selection
			try {
				sel = UIUtil.getMenuSelection();
			} catch (InvalidInput e) {
				System.out.println("Error: Invalid input. Should be an number selection.");
				continue;
			}
			
			//Handle Menu Selection			
			//Check valid menu choice
			try {
				if(handleMainMenuSelection(sel)==-1) noExit=false;
			} catch (UserExit e) {
				System.out.println("Thank you for using DealerSys");
				return;
			} //user chose to exit program
		
		} while(noExit);
		

	}// end main()

	

	
	public static int handleMainMenuSelection(int sel) throws UserExit {
		//Handle Menu Selection			
		//Check valid menu choice
		if(sel<0||sel>2) {
			System.out.println("Invalid menu choice. Please try again");
			return 0; //invalid selection
		} 
		else {
			//Call associated choice
			switch(sel) {
				case 1: {
					try {
						dSys.beginLogin();
					} catch (UserExit e) {System.out.println("Returned to Main Menu");} 				
					break;
				}
				case 2: {
					try {
						dSys.startMainCreateAccount_Menu();
					} catch (UserExit e) {System.out.println("Returned to Main Menu");}
					break;
				}
				case 0: {
					//sr.close();
					dSys.saveAll();
					throw new UserExit();
					//return -1; //exit
				}
			}//end switch
		}//end else
		return 1; //made selection, and ready to restart the main menu
	}//end function
	
}
