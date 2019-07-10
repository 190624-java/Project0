package com.revature.project0;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Customer extends UsersAbstract{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6455540303853973319L;
	private CarFetcher fetchcar = new CarFetcher();
	private OfferFetcher fetchoffer = new OfferFetcher();
	
	//Creates a Customer and sets username and password
	public Customer(int id, String username, String password) {

		super.username = username;
		super.password = password;
		super.id = id;
		super.employ = 0;
	}
	public Customer(String username, String password, int employ) {

		super.username = username;
		super.password = password;
		super.employ = employ;
		
	}
	
	//Displays all cars currently in stock
	public void viewCars() {
		System.out.println("------------------------------");
		ArrayList<Cars> lotcars = fetchcar.getCarsOnLot();
		for(Cars i : lotcars) {
			System.out.println("Make: " + i.make);
			System.out.println("Model: " + i.model);
			System.out.println("Price: " + i.price);
			System.out.println("Serial Number: " + i.serialNumber);
		}
	}
	
	//Displays a car based on its identification number
	public void viewCar(Scanner scanner) {
		
		System.out.println("Please enter the identification number of the car you wish to view.");
		int serial = scanner.nextInt();
		Cars car = fetchcar.getCar(serial);
		System.out.println("------------------------------");
		if(car != null) {
			System.out.println("Make: " + car.make);
			System.out.println("Model: " + car.model);
			System.out.println("Starting Price: " + car.price);
			System.out.println("Identification Number: " + car.serialNumber);
			System.out.println("------------------------------");
		}
		else {
			System.out.println("No car found.");
		}
	}
	
	
	//Allows customer to make an offer on a car
	public void makeOffer(Scanner scanner) {
		boolean exist = false;
		boolean offered = false;
		
		System.out.println("Please enter the identification number of the car you wish to make an offer on.");
		int serial = scanner.nextInt();
		
		//checks to see if the entered car exist and grabs its make
		if(fetchcar.getCar(serial) != null)
			exist = true;
		
		//checks to see if the customer has already made an offer
		if(fetchoffer.getOffers(serial, id) != null)
			offered = true;
		
		if(!offered && exist) {
			System.out.println("What is your offer?");
			double offer = scanner.nextDouble();
			fetchoffer.creatOffer(new Offers(id, offer, serial));
		}else {
			System.out.println("You have already made an offer on this car.");
		}
	}
	
	//Prints out all cars 
	public void viewMyCars() {
		ArrayList<Cars> myCars = fetchcar.getMyCars(id);
		System.out.println("------------------------------");
		for(Cars i : myCars) {
			System.out.println("Make: " + i.make);
			System.out.println("Model: " + i.model);
			System.out.println("Identification number: " + i.serialNumber);
			System.out.println("------------------------------");
		}
	}
	
	//Prints out all Payments and number of payments left
	public void viewMyPayments() {
		System.out.println("------------------------------");
		ArrayList<Cars> myCars = fetchcar.getMyCars(id);
		for(Cars i : myCars) {
			if(i.num_payments > 0) {
				System.out.println("Make: " + i.make);
				System.out.println("Model: " + i.model);
				System.out.println("Current Payments: " + i.payments);
				System.out.println("Current Payments Remaining: " + i.num_payments);
				System.out.println("------------------------------");
			}
		}
	}
	
	//ToDo: will implement if i have time
	public void makePayment() {
		
	}

}
