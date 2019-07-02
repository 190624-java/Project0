package project0.users;

import java.util.Scanner;

import project0.automobiles.Car;
import project0.systems.CarsInLot;
import project0.systems.DealershipSystem;
import project0.systems.Offer;

public class Employee extends User {
	
	private static final long serialVersionUID = -1998507940139170601L;
	private String userName;
	private short loginPin;
	
	public Employee(String userName, short loginPin) {
		this.userName = userName;
		this.loginPin = loginPin;
	}
	
	public String getUserName() {
		return userName;
	}
	public short getLoginPin() {
		return loginPin;
	}
	
	public void addCarToLot(byte carId, String brand, String model, String color, short year, int price, int mileage) {
		CarsInLot.getCarsInLot().add(new Car(carId, brand, model, color, year, price, mileage));;
	}
	
	public void viewCarsInLot() {
		CarsInLot.printCarsInLot(); // prints cars on lot
	}
	
	public void removeCarFromLot(byte carId) {
		if(CarsInLot.getCarsInLot().isEmpty()) System.out.println("No Cars in Lot.");
		for(int i = 0; i < CarsInLot.getCarsInLot().size(); i++) {
			if(CarsInLot.getCarsInLot().get(i).getId() == (byte)carId) {
				CarsInLot.getCarsInLot().remove(i);
				return;
			}
		}
	}
	
	public void acceptOrReject(Scanner scanner) {
		System.out.println("----- Current Offers -----");
		//list out offers
		if(DealershipSystem.offers.isEmpty()) {
			System.out.println("No offers");
			return;
		}
		for(int i = 0; i < DealershipSystem.offers.size(); i++) {
			System.out.println();
			Offer offer = DealershipSystem.offers.get(i);
			
			if(offer.getOfferStatus().equals(Offer.OFFERSTATUS.PENDING)) {
				System.out.println("#" + (i + 1) +": " 
						+ "\nCarID: " +  offer.getCarId()
						+ "\nOffer Amount: " + offer.getOfferAmount()
						+ "\nOffer Status: " + offer.getOfferStatus());
				System.out.print("Accept offer? (y/n): ");
				String answer = scanner.next();
				if(answer.equals("y") || answer.equals("Y")) {
					offer.setOfferStatus(Offer.OFFERSTATUS.ACCEPTED);
					DealershipSystem.updateCustomersOwnedCars(); // update lot and customer
					return; // finished
				} else if(answer.equals("n") || answer.equals("N")) {
					offer.setOfferStatus(Offer.OFFERSTATUS.DECLINED);
				} else {
					System.out.println("Invalid answer...no action taken. Moving to next offer");
				}
			}
		}
	}
	
	public void viewAllPayments() {
		
	}

	@Override
	public boolean login(String userName, short loginPin) {
			// employee login
			boolean loginSuccessful = true;
			if(DealershipSystem.employees.containsKey(userName)  // check user existence
					&& DealershipSystem.employees.get(userName).getLoginPin() == loginPin) { // check correct pin
				System.out.println("----- Welcome Employee -----");
				
				return loginSuccessful;
			}
			else {
				System.out.println("Invalid userName OR login pin");
				System.out.println("Exiting system...");
				loginSuccessful = false; // login failed
				return loginSuccessful;
			}
	}

	@Override
	public boolean registerAccount(Scanner scanner) { // employee is already registered in system
		// TODO Auto-generated method stub
		return false;
	}
}
