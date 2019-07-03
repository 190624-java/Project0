package com.revature.project0;

import java.util.HashSet;
import java.util.Scanner;

public class Customer extends UsersAbstract{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6455540303853973319L;
	
	//An arraylist of all cars the customer has currently
	HashSet<Cars> myCars = new HashSet<>();
	
	//Creates a Customer and sets username and password
	public Customer(String username, String password) {

		super.username = username;
		super.password = password;
	}
	
	//Displays all cars currently in stock
	public void viewCars() {
		System.out.println("------------------------------");
		for(Cars i : Project0.cars) {
			System.out.println("Make: " + i.make);
			System.out.println("Starting Price: " + i.offer);
			System.out.println("Identification Number: " + i.serialNumber);
			System.out.println("------------------------------");
		}
	}
	
	//Displays a car based on its identification number
	public void viewCar(Scanner scanner) {
		
		System.out.println("Please enter the identification number of the car you wish to view.");
		int serial = scanner.nextInt();
		System.out.println("------------------------------");
		for(Cars i : Project0.cars) {
			if(i.serialNumber == serial) {
				System.out.println("Make: " + i.make);
				System.out.println("Starting Price: " + i.offer);
				System.out.println("Identification Number: " + i.serialNumber);
				System.out.println("------------------------------");
			}
		}
	}
	
	
	//Allows customer to make an offer on a car
	public void makeOffer(Scanner scanner) {
		boolean madeOffer = false;
		boolean exist = false;
		String make = "";
		
		System.out.println("Please enter the identification number of the car you wish to make an offer on.");
		int serial = scanner.nextInt();
		
		//checks to see if the entered car exist and grabs its make
		for(Cars i : Project0.cars) {
			if(i.serialNumber == serial) {
				make = i.make;
				exist =  true;
			}
		}
		
		//checks to see if the customer has already made an offer
		for(Offers i : Project0.offers) {
			if(i.getCustomer().equals(username) && i.getSerial() == serial) {
				madeOffer = true;
			}
		}
		
		if(!madeOffer && exist) {
			System.out.println("What is your offer?");
			double offer = scanner.nextDouble();
			Project0.offers.add(new Offers(this.username, offer, serial,make));
		}else {
			System.out.println("You have already made an offer on this car.");
		}
	}
	
	//Prints out all cars 
	public void viewMyCars() {
		System.out.println("------------------------------");
		for(Cars i : myCars) {
			System.out.println("Make: " + i.make);
			System.out.println("Identification number: " + i.serialNumber);
			System.out.println("------------------------------");
		}
	}
	
	//Prints out all Payments and number of payments left
	public void viewMyPayments() {
		System.out.println("------------------------------");
		for(Cars i : myCars) {
			if(i.numPayments > 0) {
				System.out.println("Make: " + i.make);
				System.out.println("Current Payments: " + i.payments);
				System.out.println("Current Payments Remaining: " + i.numPayments);
				System.out.println("------------------------------");
			}
		}
	}
	
	//ToDo: will implement if i have time
	public void makePayment() {
		
	}

}
