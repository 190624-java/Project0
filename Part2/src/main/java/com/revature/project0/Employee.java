package com.revature.project0;

import java.util.ArrayList;
import java.util.Scanner;

public class Employee extends UsersAbstract{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1853623281477660977L;
	private CarFetcher fetchcar = new CarFetcher();
	private OfferFetcher fetchoffer = new OfferFetcher();

	public Employee(int id, String username, String password) {
		
		super.username = username;
		super.password = password;
		super.id = id;
		super.employ = 1;
		
	}
	
	public Employee(String username, String password, int employ) {
		
		super.username = username;
		super.password = password;
		super.employ = employ;
		
	}
	
	public void addCar(Scanner scanner) {
		
		System.out.print("Enter car make: ");
		String make = scanner.next();
		System.out.print("Enter the car model: ");
		String model = scanner.next();
		System.out.print("Enter the intital price: ");
		double initPrice = scanner.nextDouble();
		System.out.print("Enter the total number of payments: ");
		int num_payments = scanner.nextInt();
		fetchcar.createNewCar(new Cars(initPrice, make, model, num_payments));
		System.out.println("New car created.");
		
	}
	
	public void removeCar(Scanner scanner) {
		
		System.out.print("Please enter The serial number of the car you wish to remove: ");
		int serialNumber =  scanner.nextInt();
		fetchcar.deleteCar(serialNumber);
		System.out.println("Car removed.");
	}
	
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
	
	public void reviewOffers(Scanner scanner) {
		//Printing all offers 
		System.out.println("What car would you like to view offers for?(by Identification number)");
		int carId = scanner.nextInt();
		
		ArrayList<Offers> offers = fetchoffer.getOffers(carId);
		
		System.out.println("------------------------------");
		for(Offers i : offers) {
			System.out.println("Customer ID number: " + i.getCustId());
			System.out.println("Offer Amount: $" + i.getOffer());
			System.out.println("------------------------------");
		}
		
		//getting employee input
		String temp;
		int custId;
		
		System.out.println("Would you like to accept any offers? Y/N");
		temp = scanner.next();
		if(temp.contains("Y") || temp.contains("y")) {
			System.out.println("What offer would you like to accept? \n "
					+ "Please enter the customer ID");
			custId = scanner.nextInt();
			Offers offer = fetchoffer.getOffers(carId, custId);
		    
			Cars car = fetchcar.getCar(carId);
			System.out.println(car.ownerId);
			car.ownerId = custId;
			System.out.println(car.ownerId);
			car.setPrice(offer.getOffer());
			
			fetchcar.updateCar(car);
			fetchoffer.removeOffers(carId);
		}
		
	}
	
	public void viewPayments() {
		double monthlyIncome = 0;
		ArrayList<Cars> cars = fetchcar.getCarsSold();
		for(Cars i : cars) {
			System.out.println("Car Number " + i.serialNumber +": $" + i.payments);
			monthlyIncome += i.payments;
		}
		System.out.println("Total income for this month: $" + monthlyIncome);
		
	}
}
