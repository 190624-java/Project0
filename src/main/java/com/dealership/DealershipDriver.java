package com.dealership;

import java.security.AccessControlException;
import java.util.Scanner;

import com.dealership.DAOFileImpl.CustomerList;
import com.dealership.DAOFileImpl.EmployeeList;
import com.dealership.DAOFileImpl.LoanList;
import com.dealership.DAOFileImpl.OfferList;
import com.dealership.DAOinterface.*;
import com.dealership.DAODatabaseImpl.*;

public class DealershipDriver {
	public static User activeAccount;
	public static Scanner inScan;
	public static CustomerAccessor custAccessor;
	public static EmployeeAccessor empAccessor;
	public static LoanAccessor loanAccessor;
	public static OfferAccessor offerAccessor;
	public static CarAccessor carAccessor;
	
	
	public static void main(String[] args) {
		try(Scanner inputScanner = new Scanner(System.in))
		{
			// Initializing DAOs
			custAccessor = new CustomerDataAccessor();
			empAccessor = new EmployeeDataAccessor();
			loanAccessor = new LoanDataAccessor();
			offerAccessor = new OfferDataAccessor();
			carAccessor = new CarDataAccessor();
			
			inScan = inputScanner;
			initialize();
			boolean done = false;
			while(!done)
			{
				if(activeAccount == null)
				{
					done = displayLoginOrRegisterMenu();
				}
				else if(activeAccount instanceof Customer)
				{
					displayCustomerMainMenu();
				}
				else if(activeAccount instanceof Employee)
				{
					displayEmployeeMainMenu();
				}
					
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			
			if(custAccessor instanceof CustomerList)
			{
				((CustomerList)custAccessor).write();
			}
			if(empAccessor instanceof EmployeeList)
			{
				((EmployeeList)empAccessor).write();
			}
			if(offerAccessor instanceof OfferList)
			{
				((OfferList)offerAccessor).write();
			}
			if(loanAccessor instanceof OfferList)
			{
				((LoanList)loanAccessor).write();
			}
			if(carAccessor instanceof Lot)
			{
				((Lot)carAccessor).write();
			}
		}
	}
	
	public static void initialize()
	{
		System.out.println("Booting up system");
		System.out.println("Creating System employee account");
		activeAccount = Employee.SYSTEM_ACCOUNT;
		System.out.println("Attempting to retrieve employee list");
		if(empAccessor.isEmpty())
		{
			System.out.println("Adding system account to employee list");
			empAccessor.addEmployee((Employee)activeAccount, (Employee)activeAccount);
			System.out.println("Please register an admin account");
			boolean adminAcctExists = false;
			while(!adminAcctExists)
			{
				registerAccount();
				adminAcctExists = empAccessor.nonSystemAdminExists();
			}
		}	
		System.out.println("System initialized");
		System.out.println("Logging out of system user");
		logOut();
	}
	
	public static boolean registerAccount()
	{
		if(activeAccount == null)
		{
			Customer newCust = Customer.createCustomer();
			if(custAccessor.addCustomer(newCust))
			{
				System.out.println("Successfully registered your account!");
				return true;
			}
				
			else
			{
				System.out.println("hmm... failed to register account.");
				return false;
			}
		}
		else if(activeAccount instanceof Employee)
		{
			Employee newEmp = Employee.createEmployee();
			try {
				if(activeAccount instanceof Employee)
				{
					empAccessor.addEmployee(newEmp, (Employee)activeAccount);
					return true;
				}
				else
					System.out.println("Cannot register an employee by a non-employee");
				return false;
			}
			catch(AccessControlException e)
			{
				System.out.println("Failed registering employee. Current user does not have permissions to do so.");
				System.out.println("Exception message: " + e.getMessage());
				return false;
			}
		}
		else
			System.out.println("Entered code is not for a customer. No active account detected, which is required to register employee");
		return false;
	}

	public static void logOut()
	{
		activeAccount = null;
	}

	public static void customerLogin() {
		System.out.println("Enter username");
		String username = inScan.nextLine();
		System.out.println("Enter password");
		String password = inScan.nextLine();
		if(custAccessor.containsID(username)) {
			Customer tmpCust = custAccessor.findByID(username);
			if(tmpCust.checkLogin(username, password))
			{
				activeAccount = tmpCust;
				((Customer)activeAccount).loans = loanAccessor.getLoansForCustomer(activeAccount.getId());
				((Customer)activeAccount).ownedCars = carAccessor.retrunCarsOwnedBy(activeAccount.getId());
			}
		}
		else
			System.out.println("Login failed");
	}
	
	
	public static void employeeLogin() {
		System.out.println("Enter username");
		String username = inScan.nextLine();
		System.out.println("Enter password");
		String password = inScan.nextLine();
		if(empAccessor.containsID(username))
		{
			Employee tmpEmp = (Employee)empAccessor.findByID(username);
			if(tmpEmp.checkLogin(username, password))
				activeAccount = tmpEmp;
		}
		else
			System.out.println("Username not found");
	}
	
	/**
	 * Lowest level menu, requests sign in, account creation, and shutdown of system
	 * @return true when shutdown
	 */
	public static boolean displayLoginOrRegisterMenu()
	{
		int choice = -1;
		while(activeAccount == null)
		{
			System.out.println("No user currently logged in. Select an option:\n"
					+ "Enter 0 to shutdown\nEnter 1 to login as a customer\nEnter 2 to register as a customer\n"
					+ "Enter 3 to login as an employee");
			choice = inScan.nextInt(); inScan.nextLine();
			switch(choice) {
				case 0: return true;
				case 1: customerLogin();
					break;
				case 2: registerAccount();
					break;
				case 3: employeeLogin();
					break;
			}
		}
		if(choice == 0)
			return true;
		return false;
	}
	
	public static void displayCustomerMainMenu() {
		System.out.println("Welcome " + activeAccount.getName() + "!");
		int optionSelect = -1;
		while(optionSelect != 0)
		{
			System.out.println("Select an option:\n"
					+ "Enter 0 to exit\nEnter 1 to view available cars\nEnter 2 to view offers you have placed\n"
					+ "Enter 3 view cars you own\nEnter 4 to view your remaining payments");
			optionSelect = inScan.nextInt(); inScan.nextLine();
			switch(optionSelect) {
			case 1: displayLot();
				break;
			case 2: offerAccessor.displayOfferesByCustomer(activeAccount.id);
				break;
			case 3: displayOwnedCars();
				break;
			case 4: ((Customer) activeAccount).displayRemainingLoans();
			}
		}
		logOut();
	}
	
	public static void displayEmployeeMainMenu() {
		System.out.println("Logged in as " + activeAccount.getName());
		int optionSelect = -1;
		while(optionSelect != 0) {
			System.out.println("Select an option:\n"
					+ "Enter 0 to exit\nEnter 1 to view cars in the lot\nEnter 2 to view all offers\n"
					+ "Enter 3 to view all payments\nEnter 4 to register a new employee");
			optionSelect = inScan.nextInt(); inScan.nextLine();
			switch(optionSelect) {
			case 1: displayLot();
				break;
			case 2:offerAccessor.displayAllOffers();
				displayOfferMenu();
				break;
			case 3: loanAccessor.displayAllLoans();
				break;
			case 4: registerAccount();
				break;
			
			}
		}
		logOut();
	}
	
	public static void displayOfferMenu() {
		String input = "";
		
		while(!input.equals("0"))
		{
			System.out.println("Enter 0 to exit\nEnter the following separated by a semicolon to accept an offer:\n"
					+ "Customer's username, Car id, and the number 1\n" +
					"To reject an offer, end the line in 2 instead");
			input = inScan.nextLine();
			if(!input.equals("0"))
				break;
			else
			{
				String[] offerParts = input.split(";");
				if(offerParts.length != 3)
					System.out.println("Could not locate correct number of arguments to pull up a specific offer");
				else
				{
					Offer offerSkel = offerAccessor.getOfferByCustCar(offerParts[0], Integer.parseInt(offerParts[1]));
					if(Integer.parseInt(offerParts[2])==1)
					{
						offerAccessor.acceptOffer(offerSkel);
					}
					else if(Integer.parseInt(offerParts[2])==2)
					{
						offerAccessor.rejectOffer(offerSkel);
					}
					/*
					Customer cust = custAccessor.findByID(offerParts[0]);
					if(cust != null) {
						Offer targetOffer = null;
						for(Offer offer : cust.offers) {
							if(offer.amount == Double.parseDouble(offerParts[2]) && offer.car.getCarkey().equals(offerParts[1]))
								targetOffer = offer;
						}
						if(targetOffer != null)
						{
							if(offerParts[3] == String.valueOf(1))
								offerAccessor.acceptOffer(targetOffer);
							else if(offerParts[3].equals(String.valueOf(2)))
								offerAccessor.rejectOffer(targetOffer);
						}
							
						else
							System.out.println("Was unable to locate offer");
					}
					else
						System.out.println("Was unable to parse offer data from String");
					*/
				}
			}
		}
	}

	public static void  displayLot()
	{
		int input = -1;
		while(input != 0) {
			carAccessor.displayLot();
			System.out.println("Enter 0 to go back\nEnter 1 to examine cars\nEnter 2 to add a car");
			input = inScan.nextInt(); inScan.nextLine();
			switch(input) {
			case 1: //displayCarsOfType();
				displayCarInteractionMenu();
				break;
			case 2: displayAddCar();
				break;
			}
				
		}
	}
	
	
	public static void displayAddCar() 
	{
		System.out.println("Enter the make");
		String make = inScan.nextLine();
		System.out.println("Enter the model");
		String model = inScan.nextLine();
		System.out.println("Enter the year");
		int year = inScan.nextInt(); inScan.nextLine();
		System.out.println("Enter the color");
		String color = inScan.nextLine();
		System.out.println("Enter the price");
		double price = inScan.nextDouble();inScan.nextLine();
		Car tmpCar = new Car(make, model, year, color, price);
		carAccessor.addCar(tmpCar);
	}
	
	public static void displayCarsOfType()
	{
		String carKey = "";
		while(carKey != "0")
		{
			System.out.println("Please enter the make, model, and year to examine cars of that type. Enter 0 to exit");
			carKey = inScan.nextLine();
			if(carKey.equals("0"))
				break;
			else if(carAccessor.lotContainsType(carKey)) {
				carAccessor.displayOfType(carKey);
				int carIndex = -2;
				while(carIndex != -1) {
					System.out.println("Please enter an index of a specific car you would like to interact with, or -1 to exit");
				 	carIndex = inScan.nextInt(); inScan.nextLine();
				 	if(carIndex >= 0 && carIndex < carAccessor.numberOfType(carKey)) {
				 		displayCarInteractionMenu(carKey, carIndex);
				 	}
				}
			}
			else {
				System.out.println("Not valid. Please make sure you have the make, model, and year listed in order and separated by a single space");
			}
		}
	}
	
	public static void displayCarInteractionMenu() {
		System.out.println("Enter the id of the car you would like to examine. Enter -1 to exit");
		int choice = -1;
		Car tmpCar = null;
		while(tmpCar == null)
		{
			choice = inScan.nextInt(); inScan.nextLine();
			if(choice == -1)
			{
				break;
			}
			tmpCar = carAccessor.getById(choice);
		}
		
		if(tmpCar!= null) {
			displayCarInteractionMenu(tmpCar);
		}
	}
	
	public static void displayCarInteractionMenu(Car car) {
		if(activeAccount instanceof Customer)
		{
			int choice = -1;
			while(choice != 0)
			{
				System.out.println("Enter 0 to exit\nEnter 1 to make an offer on this car");
				choice = inScan.nextInt(); inScan.nextLine();
				if(choice == 1)
				{
					System.out.println("Enter an offer");
					double offerAmount = inScan.nextDouble(); inScan.nextLine();
					Offer newOff = new Offer((Customer)activeAccount, car, offerAmount);
					//car.offers.add(newOff);
					//((Customer) activeAccount).offers.add(newOff);
					offerAccessor.addOffer(newOff);
				}
			}
		}
		else if(activeAccount instanceof Employee)
		{
			int choice = -1;
			while(choice != 0)
			{
				System.out.println("Enter 0 to exit\nEnter 1 to remove this car");
				choice = inScan.nextInt(); inScan.nextLine();
				if(choice == 1)
				{
					carAccessor.removeCar(car);
					choice = 0;
				}
			}
		}
	}
	
	public static void displayCarInteractionMenu(String carKey, int carIndex){
		Car tmpCar = carAccessor.returnCar(carKey, carIndex);
		// Customer behavior
		if(activeAccount instanceof Customer)
		{
			int choice = -1;
			while(choice != 0)
			{
				System.out.println("Enter 0 to exit\nEnter 1 to make an offer on this car");
				choice = inScan.nextInt(); inScan.nextLine();
				if(choice == 1)
				{
					System.out.println("Enter an offer");
					double offerAmount = inScan.nextDouble(); inScan.nextLine();
					Offer newOff = new Offer((Customer)activeAccount, tmpCar, offerAmount);
					tmpCar.offers.add(newOff);
					((Customer) activeAccount).offers.add(newOff);
					offerAccessor.addOffer(newOff);
				}
			}
		}
		else if(activeAccount instanceof Employee)
		{
			int choice = -1;
			while(choice != 0)
			{
				System.out.println("Enter 0 to exit\nEnter 1 to remove this car");
				choice = inScan.nextInt(); inScan.nextLine();
				if(choice == 1)
				{
					carAccessor.removeCar(tmpCar);
					choice = 0;
				}
			}
		}
	}
	
	public static void displayOwnedCars() {
		System.out.println("These are the cars you own");
		for(Car c : ((Customer) activeAccount).ownedCars) {
			System.out.println(c.toString());
		}
	}
	
	

}
