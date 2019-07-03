package com.revature.main;

import java.util.Scanner;

import com.revature.parties.Customer;
import com.revature.parties.DSystem;

public class CarDealership {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DSystem sys = new DSystem("Buck-a-Roos Car Place", new Customer("Dealer Buck", -100));		
		Scanner sr = new Scanner(System.in);
		boolean noExit = true; //exit the program if this "noExit" equals false
		boolean noMainMenuSelection;
		int sel; //menu selection number
		
		do { //Start main menu
		System.out.flush();
		sys.giveStartUpMenu();				
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
				sys.beginLogin();				
				break;
			}
			case 2: {
				sys.createAccount();
				break;
			}
			case 0: {
				sys.exit();
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
