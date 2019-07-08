package com.dealership;

import java.security.AccessControlException;
import java.util.Scanner;

import com.dealership.DAOFileImpl.CustomerList;
import com.dealership.DAOFileImpl.EmployeeList;
import com.dealership.DAOFileImpl.LoanList;
import com.dealership.DAOFileImpl.OfferList;

public class DealershipDriver {
	public static User activeAccount;
	public static Scanner inScan;
	
	
	public static void main(String[] args) {
		try(Scanner inputScanner = new Scanner(System.in))
		{
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
			CustomerList.getInstance().write();
			EmployeeList.getInstance().write();
			OfferList.getInstance().write();
			LoanList.getInstance().write();
			Lot.getInstance().write();
		}
	}
	
	public static void initialize()
	{
		System.out.println("Booting up system");
		System.out.println("Creating System employee account");
		activeAccount = Employee.SYSTEM_ACCOUNT;
		System.out.println("Attempting to retrieve employee list");
		if(EmployeeList.getInstance().isEmpty())
		{
			System.out.println("Adding system account to employee list");
			EmployeeList.getInstance().addEmployee((Employee)activeAccount, (Employee)activeAccount);
			System.out.println("Please register an admin account");
			boolean adminAcctExists = false;
			while(!adminAcctExists)
			{
				registerAccount();
				for(User emp : EmployeeList.getInstance().getUserList())
				{
					if(((Employee) emp).isAdmin())
					{
						if((Employee) emp != Employee.SYSTEM_ACCOUNT)
							adminAcctExists = true;
					}
				}
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
			if(CustomerList.getInstance().addCustomer(newCust))
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
					EmployeeList.getInstance().addEmployee(newEmp, (Employee)activeAccount);
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
		if(CustomerList.getInstance().containsID(username)) {
			if(CustomerList.getInstance().findByID(username).checkLogin(username, password))
				activeAccount = CustomerList.getInstance().findByID(username);
		}
		else
			System.out.println("Login failed");
	}
	
	public static void employeeLogin() {
		System.out.println("Enter username");
		String username = inScan.nextLine();
		System.out.println("Enter password");
		String password = inScan.nextLine();
		if(EmployeeList.getInstance().containsID(username))
		{
			if(EmployeeList.getInstance().findByID(username).checkLogin(username, password))
				activeAccount = EmployeeList.getInstance().findByID(username);
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
				case 0: logOut();
					break;
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
			case 2: ((Customer) activeAccount).displayOffers();
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
			case 2:OfferList.getInstance().displayAllOffers();
				displayOfferMenu();
				break;
			case 3: LoanList.getInstance().displayAllLoans();
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
					+ "Customer's username, Car make model and year (space separated), the amount offered, and the number 1\n" +
					"To reject an offer, end the line in 2 instead");
			input = inScan.nextLine();
			if(!input.equals("0"))
				break;
			else
			{
				String[] offerParts = input.split(";");
				if(offerParts.length != 4)
					System.out.println("Could not locate correct number of arguments to pull up a specific offer");
				else
				{
					Customer cust = CustomerList.getInstance().findByID(offerParts[0]);
					if(cust != null) {
						Offer targetOffer = null;
						for(Offer offer : cust.offers) {
							if(offer.amount == Double.parseDouble(offerParts[2]) && offer.car.getCarkey().equals(offerParts[1]))
								targetOffer = offer;
						}
						if(targetOffer != null)
						{
							if(offerParts[3] == String.valueOf(1))
								OfferList.getInstance().acceptOffer(targetOffer);
							else if(offerParts[3].equals(String.valueOf(2)))
								OfferList.getInstance().rejectOffer(targetOffer);
						}
							
						else
							System.out.println("Was unable to locate offer");
					}
					else
						System.out.println("Was unable to parse offer data from String");
				}
			}
		}
	}

	public static void  displayLot()
	{
		int input = -1;
		while(input != 0) {
			Lot.getInstance().displayLot();
			System.out.println("Enter 0 to go back\nEnter 1 to examine cars\nEnter 2 to add a car");
			input = inScan.nextInt(); inScan.nextLine();
			switch(input) {
			case 1: displayCarsOfType();
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
		Lot.getInstance().addCar(tmpCar);
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
			else if(Lot.getInstance().lotContainsType(carKey)) {
				Lot.getInstance().displayOfType(carKey);
				int carIndex = -2;
				while(carIndex != -1) {
					System.out.println("Please enter an index of a specific car you would like to interact with, or -1 to exit");
				 	carIndex = inScan.nextInt(); inScan.nextLine();
				 	if(carIndex >= 0 && carIndex < Lot.getInstance().numberOfType(carKey)) {
				 		displayCarInteractionMenu(carKey, carIndex);
				 	}
				}
			}
			else {
				System.out.println("Not valid. Please make sure you have the make, model, and year listed in order and separated by a single space");
			}
		}
	}
	
	public static void displayCarInteractionMenu(String carKey, int carIndex){
		Car tmpCar = Lot.getInstance().returnCar(carKey, carIndex);
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
					OfferList.getInstance().offers.add(newOff);
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
					Lot.getInstance().removeCar(tmpCar);
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
