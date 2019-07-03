package com.revature.main;

import java.util.Scanner;

import com.revature.exceptions.UserExit;
import com.revature.parties.Customer;
import com.revature.parties.DSystem;
import com.revature.parties.Employee;

public class CarDealership {

	public static final DSystem dSys = new DSystem();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		Scanner sr = new Scanner(System.in);
		boolean noExit = true; //exit the program if this "noExit" equals false
		boolean noMainMenuSelection;
		int sel; //menu selection number
		
		do { //Start main menu
		System.out.flush();
		dSys.giveStartUpMenu();				
		sel = sr.nextInt(); // get the menu selection		
		noMainMenuSelection = true;
		
		do { //Handle Menu Selection			
		//Check valid menu choice
		if(sel<0||sel>2) {
			System.out.println("Invalid menu choice. Please try again");
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
				dSys.exit();
				noExit = false;
				break;
			}
			}
		}
		
		} while(noMainMenuSelection);	
		} while(noExit);
		
		sr.close();
		
	}// end main()

	
	
}
