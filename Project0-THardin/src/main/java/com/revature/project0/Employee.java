package com.revature.project0;

import java.util.Iterator;
import java.util.Scanner;

public class Employee extends UsersAbstract{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1853623281477660977L;

	public Employee(String username, String password) {
		
		super.username = username;
		super.password = password;
		
	}
	
	public void addCar(Scanner scanner) {
		
		System.out.print("Enter car make: ");
		String make = scanner.next();
		System.out.print("Enter the intital price: ");
		double initPrice = scanner.nextDouble();
		Project0.cars.add(new Cars(initPrice, make));
		System.out.println("New car created.");
		
	}
	
	public void removeCar(Scanner scanner) {
		
		System.out.print("Please enter The serial number of the car you wish to remove: ");
		int serialNumber =  scanner.nextInt();
		
		for(Cars i : Project0.cars) {
			if(i.serialNumber == serialNumber) {
				Project0.cars.remove(i);
				System.out.println("Car Removed");
				break;
			}
		}
	}
	
	public void viewCars() {
		System.out.println("------------------------------");
		for(Cars i : Project0.cars) {
			if(!i.owned) {
				System.out.println("Make: " + i.make);
				System.out.println("Starting Price: " + i.offer);
				System.out.println("Identification Number: " + i.serialNumber);
				System.out.println("------------------------------");
			}
		}
	}
	
	public void reviewOffers(Scanner scanner) {
		//Printing all offers 
		System.out.println("------------------------------");
		for(Offers i : Project0.offers) {
			System.out.println("Car Make: " + i.getMake());
			System.out.println("Identification Number: " + i.getSerial());
			System.out.println("Customer Username" + i.getCustomer());
			System.out.println("Offer Amount: $" + i.getOffer());
			System.out.println("Offer ID: " + i.ID);
			System.out.println("------------------------------");
		}
		
		//getting employee input
		String temp;
		int id;
		int serial = -1;
		
		System.out.println("Would you like to accept any offers? Y/N");
		temp = scanner.next();
		if(temp.contains("Y") || temp.contains("y")) {
			System.out.println("What offer would you like to accept? \n "
					+ "Please enter the offer ID");
			temp =  scanner.next();
		    
			id = Integer.parseInt(temp);
		    
			//adds the car to the Customers myCars Hash set and sets the payments up
		    for(Offers i : Project0.offers) {//loop 1
		    	
		    	if(i.ID == id) {
		    		
		    		serial = i.getSerial();
		    		System.out.println("matched Ids");
		    		for(Cars j : Project0.cars) {//loop 2
		    			
		    			if(i.getSerial()==j.serialNumber) {
		    				System.out.println("found the car");
		    				j.setOwner(i.getCustomer(), i.getOffer());
		    				
		    				for(UsersAbstract k : Project0.userData) {// loop 3
		    					
		    					if(i.getCustomer().equals(k.username)) {
		    						Customer tempCust = (Customer) k;
		    						tempCust.myCars.add(j);
		    						System.out.println("Offer accepeted");
		    						Project0.offers.remove(i);
		    						break;//break out of loop 3
		    					}
		    				}
		    				break;//break out of loop 2
		    			}
		    		}
		    		break;//break out of loop 1
		    	}
		    }
		    
		    for(Iterator<Offers> iterator = Project0.offers.iterator(); iterator.hasNext();) {
		    	Offers tempOffer = iterator.next();
		    	if(tempOffer.getSerial() == serial) {
		    		iterator.remove();
		    		System.out.println("Removed offer " + tempOffer.ID);
		    	}
		    }
		    
		}
		
	}
	
	public void viewPayments() {
		double monthlyIncome = 0;
		
		for(Cars i : Project0.cars) {
			if(i.owned) {
				System.out.println("Car Number " + i.serialNumber +": $" + i.payments);
				monthlyIncome += i.payments;
			}
		}
		System.out.println("Total income for this month: $" + monthlyIncome);
		
	}
}
