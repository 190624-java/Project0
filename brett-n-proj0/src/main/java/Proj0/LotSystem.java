package Proj0;

import java.util.Scanner;

public class LotSystem {
	
	Scanner input = new Scanner(System.in);
	public int choice;
	
	CarLot cL = new CarLot();
	
	public void Menu() {
		
		
		
		System.out.println("Welcome to the car lot\n");
		System.out.println("Press the number corresponding to what you would like to do\n");
		System.out.println("1 - Log in");
		System.out.println("2 - Add a car to the lot" );
		System.out.println("3 - View cars on the lot");
		System.out.println("4 - Make an offer on a car in the lot");
		System.out.println("5 - Accept or reject an offer on a car");
		System.out.println("6 - Remove a car from the lot");
		System.out.println("7 - View the cars that I own");
		System.out.println("8 - View my remaining payments for a car");
		System.out.println("9 - View all payments");
		System.out.println("10 - To exit");
		MenuSelection();
		
	}
	
	public void MenuSelection() {
		
		while(input.hasNext()) {
			
			choice = input.nextInt();
			
			switch(choice) {
			
			case 1 : {
					
			}
			
			case 2 : {
				
				
			}
			
			case 3: {
				
				cL.getCarsOnLot();
				
			}
			
			case 4: {
			
			}
			
			case 5: {
				
			}
			
			case 6: {
				
			}
			
			case 7: {
				
			}
			
			case 8: {
				
			}
			
			case 9: {
				
			}
			
			case 10: {
				
			}
			
			case 11: {
				System.exit(0);
			}
			
			
			}
		}
		
	}
	
	public void rejectOffers() {
		
		
		
	}
	
	public double calculateMonthlyPayment(int flatPrice, int numOfMonths, double interestRate) {
		
		double monthlyPayment = flatPrice + (numOfMonths * interestRate);
		
		return monthlyPayment;
	}
	
} 