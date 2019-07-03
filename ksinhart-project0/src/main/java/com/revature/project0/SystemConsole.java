package com.revature.project0;

import java.io.IOException;
import java.util.*;

public class SystemConsole {
	protected int state;//state will say if the system is 
	//being made/used for a user/customer/employee
	Scanner scanner;

	boolean active;
	User currLog;
	Employee emp;
	Customer cust;

	HashSet<User> userSet;//has both customers and employees
	ArrayList<Offer> offerList;//has all the offers
	ArrayList<Car> carList;//has all the cars

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Scanner getScanner() {
		return this.scanner;
	}

	public SystemConsole(Scanner scanner) {
		this.scanner=scanner;
		dataSetup();
		for(User u: userSet) {
			System.out.println(u.toString());
		}
	}

	public void dataSetup() {
		//userSet = null;
		//carList = null;
		//offerList=null;
		try {
			userSet = (HashSet<User>) UserPersistence.getAllUsers();
			offerList = (ArrayList<Offer>) OfferPersistence.getAllOffers();
			carList = (ArrayList<Car>) CarPersistence.getAllCars();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//this will be the default setup for the console to start interacting with it
	public int startUp() {
		//try having a check valuable to see which type of menu comes up?
		System.out.println("Welcome to Kyle's Car Dealership!");
		System.out.println("_________________________________________");
		System.out.println("(1) Login");
		System.out.println("(2) Register");
		//System.out.println("_________________________________________");
		return scanner.nextInt();
	}


	//need to check for employee or customer
	public void start() {
		//active = true;
		int option;
		/*
		option=startUp();

		if(option==1) {

		}
		 */
		User user = null;

		//either logs in the user or registers and logins the new user
		//this should be put in the login method
		boolean passLogReg = false;
		do {
			option=startUp();
			if(option==1) {
				user=login();

				if(user instanceof Customer) {
					state=1;
					cust = (Customer) user;//user=login();
					passLogReg=true;
				}
				else if(user instanceof Employee) {
					state=2;
					emp = (Employee) user;//user=login();
					passLogReg=true;
				}
				else {
					System.out.println("Something went wrong");
				}
			}
			else if(option==2) {
				//emp =(Employee) register();
				cust=(Customer) register();
				state=1;
				passLogReg=true;
			}
			else {
				//error
				System.out.println("This shouldn't happen");
				return;
			}
		}while(!passLogReg);

		if(option==1||option==2) {
			setCurrLog(cust);
			menu();
		}
		else {
			setCurrLog(emp);
			menu();
		}

	}



	public User getCurrLog() {
		return currLog;
	}

	public void setCurrLog(User currLog) {
		this.currLog = currLog;
	}


	public void resetConsole() {
		currLog=null;
		dataSetup();
	}
	public User login(){
		System.out.println("Please enter your username: ");
		String str1=scanner.next();
		System.out.println("Please enter your password: ");
		String str2=scanner.next();

		for (User login : userSet) {
			if(str1.equals(login.getUsername()) && str2.equals(login.getPassword())) {
				return login;
			}
		}
		System.out.println("That is an inccorect login. Please try again or register.");

		return new User(3);
	}



	public User register() {
		System.out.println("Please enter your first name: ");
		String str1=scanner.next();
		System.out.println("Please enter your last name: ");
		String str2=scanner.next();
		System.out.println("Please enter your desired username: (must be unique)");
		String str3=scanner.next();
		System.out.println("Please enter your password: ");
		String str4=scanner.next();

		Customer user=new Customer(1,str1,str2,str3,str4);
		try {
			UserPersistence.addNewUser(user);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Couldn't add the new user");
			e.printStackTrace();
		}

		return user;
	}

	public void menu() {
		active=true;
		System.out.println("Account Page");
		System.out.println("_________________________________________");
		if(state==1) {
			customerMenu();
		}
		else if(state==2) {
			employeeMenu();
		}
		else {
			//user is not an employee or customer and should not be here so the program just ends
			System.out.println("How did you get here?");
			active=false;
		}

	}

	public void customerMenu() {
		do {
			dataSetup();
			System.out.println("(1) View cars on the Lot");
			//maybe set this after you view all cars?
			System.out.println("(2) Make an offer on a car");
			System.out.println("(3) View owned cars");
			//maybe set this specific to after viewing owned cars
			System.out.println("(4) View payments");
			System.out.println("(5) Exit");
			int a = scanner.nextInt();
			switch(a) {
			case 1:
				this.displayCarLot();
				System.out.println();
				break;
			case 2:
				cust.offer(this);
				break;
			case 3:
				System.out.println("These are the cars you own:");
				System.out.println("____________________________________________________________");
				for(Car c: cust.viewOwned(this)) {
					System.out.println(c.toString());
				}
				System.out.println();
				System.out.println("____________________________________________________________");
				break;
			case 4:
				cust.viewMyPayments(this);
				break;
			case 5:
				this.active=false;
				break;

			default:

			}
		}while(this.active);

	}

	public void employeeMenu() {

		do {
			dataSetup();
			System.out.println("(1) Add car to the lot");
			System.out.println("(2) Accept Current Offers");
			System.out.println("(3) Reject Current Offers");
			System.out.println("(4) Remove car from the lot");
			System.out.println("(5) View all Payments");
			System.out.println("(6) Exit");
			//Employee e = (Employee) this.currLog;
			int a = scanner.nextInt();
			switch(a) {
			case 1:
				emp.addCar(this);
				break;
			case 2:
				emp.acceptOffer(this);
				break;
			case 3:
				emp.rejectOffer(this);
				break;
			case 4:
				emp.removeCar(this);
				break;
			case 5:
				emp.viewAllPayments(this);
				break;
			case 6:
				this.active=false;
				break;
			default:

			}
		}while(this.active);
	}

	
	public void finish() {
		scanner.close();
	}
	 
	public double monthlyPayment(Car c) {
		return c.getPrice()/60;
	}

	public void resetOffer(Offer o) {
		ArrayList<Offer> listReplace=new ArrayList<>();
		for(Offer offer: offerList) {
			if(!offer.getCar().equals(o.getCar())) {
				listReplace.add(offer);
			}
		}
		offerList=listReplace;
		
		ArrayList<Car> carReplace=new ArrayList<>();
		for(Car c: carList) {
			if(!c.equals(o.getCar())) {
				carReplace.add(c);
			}
			else {
				carReplace.add(new Car(c.getBrand(),o.getCustomer().getUsername(),c.getName(),c.getYear(),o.getAmt()));
			}
		}
		carList=carReplace;
		
		try {
			OfferPersistence.addOfferList(offerList);
			CarPersistence.addCarList(carList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataSetup();
	}

	public ArrayList<Car> carLot(){
		return this.carList;
	}

	public int offerChoice() {
		System.out.println("These are the current offers yet to be accepted;");
		System.out.println("____________________________________________________________");
		int count = 1;
		for(Offer o:offerList) {
			System.out.println("("+count+")"+o.toString());
			count++;
		}
		System.out.println("Please enter the offer that you wish to accept/reject");
		return this.scanner.nextInt();
		//number will be one above the position
	}

	public void displayCarLot() {
		System.out.println("These are the cars currently on the lot!");
		System.out.println("____________________________________________________________");
		int count = 1;
		for(Car c : carList) {
			System.out.println("("+count+")"+c.toString());
			count++;
		}
		System.out.println("_____________________________________________________________");
	}
	
	public void displayPurchasable() {
		System.out.println("These are the cars currently up for sale!");
		System.out.println("____________________________________________________________");
		int count = 1;
		for(Car c : carList) {
			if(c.getOwner().equals("DLR")) {
				System.out.println("("+count+")"+c.toString());
				count++;
			}
		}
		System.out.println("_____________________________________________________________");
	}

	public HashSet<User> getUsers(){
		return this.userSet;
	}

}
