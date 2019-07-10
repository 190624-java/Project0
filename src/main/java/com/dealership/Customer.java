package com.dealership;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

import com.dealership.DAOFileImpl.CustomerList;

public class Customer extends User implements Serializable {
	

	public HashSet<Offer> offers;
	public ArrayList<Car> ownedCars;
	public ArrayList<Loan> loans; 
	
	public Customer(String firstName, String lastName, String id) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = "" + id;
		offers = new HashSet<Offer>();
		ownedCars = new ArrayList<Car>();
		loans = new ArrayList<Loan>();
		
	}
	
	public Customer(String id, String password, String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.setPassword(password);
		offers = new HashSet<Offer>();
		ownedCars = new ArrayList<Car>();
		loans = new ArrayList<Loan>();
	}
	
	public void displayOffers() {
		offers.forEach(offer -> {
			System.out.println("Offer on: " + offer.car.toString() + "\n\tOffer amount: " + String.valueOf(offer.amount));
		});
	}
	
	public void displayRemainingLoans()
	{
		System.out.println("You have " + loans.size() + " on record");
		for(int i = 0; i < loans.size(); i++)
		{
			System.out.println("Loan " + i + ":");
			System.out.println("The monthly payment is " + loans.get(i).calculateMonthlyPayment() + 
					". There are " + loans.get(i).remainingMonths + " left of payments to make for a total of " +
					(loans.get(i).calculateRemainingPayments()));
		}
	}
	
	//TODO have scanner be a parameter rather than referencing it from the driver
	public static Customer createCustomer()
	{
		String fName = ""; String lName = ""; String id = "";
		while(true)
		{
			try{
				System.out.println("Enter your first name");
				fName = DealershipDriver.inScan.nextLine();
				System.out.println("Enter your last name");
				lName = DealershipDriver.inScan.nextLine();
				System.out.println("Enter a username. No spaces");
				id = DealershipDriver.inScan.nextLine();
			}
			finally
			{
				Customer newCust = new Customer(fName, lName, id);
				newCust.assignNewPassword();
				return newCust;
				/*
				if(!CustomerList.getInstance().containsID(id))
				{
					Customer newCust = new Customer(fName, lName, id);
					newCust.assignNewPassword();
					return newCust;
				}
				else
					System.out.println("Sorry, that username is already taken. Try again.");
					*/
			}
		}
	}
	@Override
	public String toString() {
		return "Customer [offers=" + offers + ", ownedCars=" + ownedCars + ", loans=" + loans + ", id=" + id
				+ ", password=" + getPassword() + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
}
