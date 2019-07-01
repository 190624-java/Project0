package project0.users;

import java.util.ArrayList;
import java.util.Scanner;

import project0.automobiles.Car;
import project0.systems.CarsInLot;
import project0.systems.DealershipSystem;
import project0.systems.Offer;

public class Customer extends User {
	
	private String userName;
	private short loginPin;
	private ArrayList<Car> carsOwned = new ArrayList<Car>();
	
	public Customer(String userName, short loginPin) {
		this.userName = userName;
		this.loginPin = loginPin;
	}
	
	public String getUserName() {
		return userName;
	}
	public short getLoginPin() {
		return loginPin;
	}
	
	public ArrayList<Car> getCarsOwned() {
		return carsOwned;
	}
	
	public static boolean registerAccount(Scanner scanner) {
		boolean isRegistered = false; // assume failure
		String userName;
		short loginPin; // 3 digit pin
		System.out.print("Desired user name: ");
		userName = scanner.next();
		
		// check that user name isn't taken. Note employees CAN'T register
		if(customers.containsKey(userName) || employees.containsKey(userName)) {
			System.out.println("User name taken. registration exiting...");
			return isRegistered; // returns false
		}
		System.out.print("Desired 3-digit pin: ");
		loginPin = scanner.nextShort();
		
		// check pin requirements
		if(loginPin < 100 || loginPin > 999) {
			System.out.println("INVALID LENGTH, system exiting...");
			return isRegistered; // returns false
		}
		else {
			customers.put(userName, new Customer(userName, loginPin));
			isRegistered = true;
			return isRegistered; // returns true, everything worked
		}
	}
	
	@Override
	public boolean login(String userName, short loginPin) {
		// customer login
		boolean loginSuccessful = true;
		if(customers.containsKey(userName)  // check user existence
				&& customers.get(userName).getLoginPin() == loginPin) { // check correct pin
			System.out.println("----- Welcome Customer -----");
			
			return loginSuccessful;
		}
		else {
			System.out.println("Invalid userName OR login pin");
			System.out.println("Exiting system...");
			loginSuccessful = false; // login failed
			return loginSuccessful;
		}
	}
	
	public void printCarsInLot() {
		CarsInLot.printCarsInLot(); // prints cars on lot
	}
	
	public void offerStatus() {
		for(Offer offer : DealershipSystem.offers) {
			if(this.userName.equals(offer.getCustomerUserName())) {
				System.out.println("CarID: " + offer.getCarId()
												+ "\nOffer Amount: " + offer.getOfferAmount()
												+ "\nOffer Status: " + offer.getOfferStatus());
			}
		}
	}
	
	public void makeOffer(int offerAmount, byte carId) {
		boolean isOfferDuplicate = duplicateOffer(this.userName, (byte)carId);
		if(!isOfferDuplicate) {
			Offer offer = new Offer(this.userName, offerAmount, (byte)carId);
			DealershipSystem.offers.add(offer);
			System.out.println("sending...");
		} else {
			System.out.println("Offer not sent. Duplicate offers on a pending offer is NOT allow");
		}
	}
	
	// checks that customer isn't making a duplicate offer on a pending offer
	private boolean duplicateOffer(String userName, byte carId) {
		for(Offer offer : DealershipSystem.offers) {
			if(offer.getCustomerUserName().equals(userName) && offer.getCarId() == carId) return true;
		}
		return false;
	}
	
	public void viewCarsOwned() {
		if(carsOwned.isEmpty()) System.out.println("No Cars Owned");
		else {
			int counter = 0;
			for(Car c : carsOwned) {
				counter++;
				System.out.println("#" + counter +": " 
									+ "\nCarID: " + c.getId()
									+ "\nBrand: " + c.getBrand() 
									+ "\nModel: " + c.getModel() 
									+ "\nColor: " + c.getColor() 
									+ "\nYear: " + c.getYear() 
									+ "\nPrice: " + c.getPrice()
									+ "\nMileage: " + c.getMileage());
			}
		}
	}
	
	// view remaining payments on an owned car
	public void viewRemainingPayments() {
		if(carsOwned.isEmpty()) System.out.println("No Payments (You don't own any cars)");
		else {
			// get all payments
		}
	}
}
