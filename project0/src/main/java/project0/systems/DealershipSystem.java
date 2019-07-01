package project0.systems;

import java.util.ArrayList;
import java.util.Scanner;

import project0.automobiles.Car;
import project0.users.Customer;
import project0.users.Employee;
import project0.users.User;

/*
 * Contains all business log for the DealershipSystem app
 */

public class DealershipSystem {

	public String currUser; // the current user using the system
	public boolean isEmployee = false;
	public boolean isCustomer = false;
	public static ArrayList<Offer> offers = new ArrayList<Offer>();
	private Customer customer;
	private Employee employee;

	public DealershipSystem() {
		if (User.employees.isEmpty()) {
			User.employees.put("user", new Employee("user", (short) 333));
		}
	}

	public void setCurrUser(String currUser) {
		this.currUser = currUser;
	}

	public void setIsEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}

	public void setIsCustomer(boolean isCustomer) {
		this.isCustomer = isCustomer;
	}

	private static String startMessage = "----- Welcome to Dealership Systems -----" + "\nPress 1: For Registration"
			+ "\nPress 2: Login" + "\nPress 3: Exit System";

	// users initial login selection
	public void systemStart(Scanner scanner) {

		System.out.println(startMessage);
		byte input = scanner.nextByte();

		switch (input) {

		// REGISTRATION
		case 1:
			System.out.println("----- Registration Menu -----");
			boolean isRegistered = Customer.registerAccount(scanner);
			if(!isRegistered) break;
			systemStart(scanner);
			break;

			// USER LOGIN
		case 2:
			loginMenu(scanner);
			if (isEmployee)
				employeeLoggedIn(scanner, this.employee); // determine who the user is, employee OR customer
			if (this.isCustomer)
				customerLoggedIn(scanner, this.customer);
			break;

		// EXIT SYSTEM
		case 3:
			System.out.println("Good bye! ...exiting");
			return; // exit system
		default:
			System.out.println("Invalid selection, please press 1, 2, or 3");
			System.out.println("Exiting system...");
			return; // exit system
		}
	}

	// used to update the customers cars owned after employee accept, and removes from lot
	public static void updateCustomersOwnedCars() {
		for (Offer offer : offers) {
			if (offer.getOfferStatus().equals(Offer.OFFERSTATUS.ACCEPTED)) {
				byte carId = offer.getCarId();
				for (int i = 0; i < CarsInLot.getCarsInLot().size(); i++) { // get car
					Car car = CarsInLot.getCarsInLot().get(i);
					if (car.getId() == carId) {
						String userName = offer.getCustomerUserName();
						User.customers.get(userName).getCarsOwned().add(car); // add to customer
						CarsInLot.getCarsInLot().remove(i); // remove from lot
						return; // avoid nullpointer
					}
				}
			}
		}
	}

	// helper method handles both customer and employee login
	private void loginMenu(Scanner scanner) {
		System.out.println("----- Login Menu -----");

		System.out.print("User Name: ");
		String userName = scanner.next();

		System.out.print("Pin: ");
		short loginPin = scanner.nextShort();
		if (Customer.customers.containsKey(userName)) { // check to see if this customer is registered
			this.customer = Customer.customers.get(userName); // get that customer
			customer.login(userName, loginPin); // login, checks loginPin
			this.isCustomer = true; // set current user to customer
			this.isEmployee = false;
		}
		else if (Employee.employees.containsKey(userName)) {
			this.employee = Employee.employees.get(userName); // get that customer
			employee.login(userName, loginPin); // login, checks loginPin
			this.isCustomer = false; 
			this.isEmployee = true; // set current user to employee
		}
		else {
			System.out.println("No user found...exiting system");
			return;
		}
	}

	private void customerLoggedIn(Scanner scanner, Customer customer) {

		boolean running = true;
		while (running) {
			System.out.println("----------------------------");
			System.out.println("Press 1: View Cars in Lot");
			System.out.println("Press 2: Make Offer on Car");
			System.out.println("Press 3: View Cars Owned");
			System.out.println("Press 4: View Offers");
			System.out.println("Press 5: View Remaining Payments");
			System.out.println("Press 6: Return to Menu");
			System.out.println("Press 7: Exit System");
			System.out.println("----------------------------");

			byte input = scanner.nextByte();
			switch (input) {
			case 1:
				customer.printCarsInLot();
				break;
			case 2:
				System.out.print("Car ID: ");
				byte carId = scanner.nextByte();
				System.out.print("Offer (in USD): ");
				int offerAmount = scanner.nextInt(); // amount
				customer.makeOffer(offerAmount, carId);
				// determine if offer is pending/accepted/declined
				break;
			case 3:
				customer.viewCarsOwned();
				break;
			case 4:
				customer.offerStatus();
				break;
			case 5:
				customer.viewRemainingPayments();
				break;
			case 6:
				// reset booleans
				isCustomer = false;
				isEmployee = false;
				systemStart(scanner);
				break;
			case 7:
				System.out.println("Exiting system...");
				return;
			default:
				System.out.println("Invalid selection, please press 1, 2, 3, 4, 5 or 6");
				System.out.println("Exiting system...");
			}

		}
	}

	private void employeeLoggedIn(Scanner scanner, Employee employee) {
		boolean running = true;
		while (running) {
			System.out.println("----------------------------");
			System.out.println("Press 1: View Cars in Lot");
			System.out.println("Press 2: Add Car to Lot");
			System.out.println("Press 3: Remove Car from Lot");
			System.out.println("Press 4: Accept or Reject Offer");
			System.out.println("Press 5: View All Payments");
			System.out.println("Press 6: Return to Menu");
			System.out.println("Press 7: Exit System");
			System.out.println("----------------------------");

			byte input = scanner.nextByte();
			switch (input) {
			case 1:
				employee.viewCarsInLot(); // prints cars on lot
				break;
			case 2:
				System.out.println("Please specify the following information about the car.");
				System.out.print("carId: ");
				byte carId = scanner.nextByte();
				System.out.print("Brand: ");
				String brand = scanner.next();
				System.out.print("Model: ");
				String model = scanner.next();
				System.out.print("Color: ");
				String color = scanner.next();
				System.out.print("Year: ");
				short year = scanner.nextShort();
				System.out.print("Price: ");
				int price = scanner.nextInt();
				System.out.print("Mileage: ");
				int mileage = scanner.nextInt();
				employee.addCarToLot(carId, brand, model, color, year, price, mileage);
				System.out.println("Car successfully added");
				break;
			case 3:
				System.out.println("What is the carId of the car to be removed?");
				System.out.print("carId: ");
				carId = scanner.nextByte();
				employee.removeCarFromLot(carId);
				break;
			case 4:
				// accept or reject offer
				employee.acceptOrReject(scanner);
			case 5:
				// view all payments
				break;
			case 6:
				// reset booleans
				this.isCustomer = false;
				this.isEmployee = false;
				systemStart(scanner);
				break;
			case 7:
				System.out.println("Exiting system...");		
				return;
			default:
				System.out.println("Invalid selection, please press 1, 2, 3, 4, 5 or 6");
				System.out.println("Exiting system...");
			}
		}
	}

	public static void calculateMonthlyPayment() {

	}
}
