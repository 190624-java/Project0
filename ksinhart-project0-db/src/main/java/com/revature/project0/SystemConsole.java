package com.revature.project0;

//import java.io.IOException;
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
		//dataSetup();
		userSet=new HashSet<User>();
		offerList = new ArrayList<Offer>();
		carList = new ArrayList<Car>();
	}
	/*
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
	*/
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
		User user = null;

		//either logs in the user or registers and logins the new user
		//this should be put in the login method
		boolean passLogReg = false;
		do {
			option=startUp();
			if(option==1) {
				user=login();

				if(user.getCheck()==1) {
					state=1;
					cust = (Customer) user;//user=login();
					passLogReg=true;
				}
				else if(user.getCheck()==2) {
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
				System.out.println("Please pick 1 or 2");
				//return;
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
		//dataSetup();
	}
	public User login(){
		System.out.println("Please enter your username: ");
		String str1=scanner.next();
		System.out.println("Please enter your password: ");
		String str2=scanner.next();
		User u = null;
		if(checkPassword(str1,str2)) {
			u=UserPersistence.getUser(str1);
			if(u.getCheck()==1) {
				return (Customer) u;
			}
			else if(u.getCheck()==2) {
				return new Employee(u.getCheck(),u.getFname(),u.getLname(),u.getUsername(),u.getPassword());
			}
			else {
				System.out.println("This user should not be possible");
				return null;
			}
		}
		else {
			System.out.println("Incorrect login");
			return null;
		}
		/*
		for (User login : userSet) {
			if(str1.equals(login.getUsername()) && str2.equals(login.getPassword())) {
				return login;
			}
		}
		System.out.println("That is an inccorect login. Please try again or register.");

		return new User(3);
		*/
	}

	public boolean checkPassword(String s1, String s2) {
		String pass = UserPersistence.getPassword(s1);
		//System.out.println(pass);
		if(s2.equals(pass)) {
			return true;
		}
		return false;
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
		
		Customer user = new Customer(1,str1,str2,str3,str4);
		UserPersistence.addNewUserDB(user);
		/*
		Customer user=new Customer(1,str1,str2,str3,str4);
		try {
			UserPersistence.addNewUser(user);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Couldn't add the new user");
			e.printStackTrace();
		}
		*/
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
			//dataSetup();
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
				cust.viewLot(this);
				System.out.println("____________________________________________________________");
				break;
			case 2:
				cust.offer(this);
				System.out.println("____________________________________________________________");
				break;
			case 3:
				cust.viewOwned(this);
				System.out.println("____________________________________________________________");
				break;
			case 4:
				cust.viewMyPayments(this);
				System.out.println("____________________________________________________________");
				break;
			case 5:
				this.active=false;
				System.out.println("____________________________________________________________");
				break;

			default:
				System.out.println("____________________________________________________________");
				System.out.println("Pick an option");
				System.out.println("____________________________________________________________");
			}
		}while(this.active);

	}

	public void employeeMenu() {

		do {
			//dataSetup();
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
				System.out.println("____________________________________________________________");
				break;
			case 2:
				emp.acceptOffer(this);
				System.out.println("____________________________________________________________");
				break;
			case 3:
				emp.rejectOffer(this);
				System.out.println("____________________________________________________________");
				break;
			case 4:
				emp.removeCar(this);
				System.out.println("____________________________________________________________");
				break;
			case 5:
				emp.viewAllPayments(this);
				System.out.println("____________________________________________________________");
				break;
			case 6:
				this.active=false;
				System.out.println("____________________________________________________________");
				break;
			default:
				System.out.println("____________________________________________________________");
				System.out.println("Pick an option");
				System.out.println("____________________________________________________________");
			}
		}while(this.active);
	}

	
	public void finish() {
		scanner.close();
	}
	 
	public double monthlyPayment(Car c) {
		return c.getPrice()/60;
	}

	public void resetOffer(String lic) {
		/* originally used Offer o
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
				carReplace.add(new Car(c.getBrand(),o.getCustomer().getUsername(),c.getName(),c.getYear(),o.getAmt(),c.getLicense()));
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
		*/
		//String lic = c.getLicense();
		OfferPersistence.removeOffersAfterAccept(lic);
		
	}

	public ArrayList<Car> carLot(){
		return this.carList;
	}

	public int offerChoice() {
		offerList = (ArrayList<Offer>) OfferPersistence.getAllOffersDB();
		if(offerList.isEmpty()) {
			System.out.println("No offers to Accept or Delete");
			return 0;
		}
		System.out.println("These are the current offers yet to be accepted;");
		System.out.println("____________________________________________________________");
		//offerList = (ArrayList<Offer>) OfferPersistence.getAllOffersDB();
		int count = 1;
		for(Offer o:offerList) {
			System.out.println("("+count+")"+o.toString());
			count++;
		}
		System.out.println("Please enter the offer that you wish to accept/reject");
		//return this.scanner.nextInt();
		//number will be one above the position
		int check = this.scanner.nextInt();
		if(checkInRange(check,offerList)) {
			return check;
		}
		else {
			return -1;
		}
		
	}

	public int displayCarLot() {
		carList = (ArrayList<Car>) CarPersistence.getCarList();
		if(carList.isEmpty()) {
			System.out.println("There are currently no cars in the lot");
			return 0;
		}
		System.out.println("These are the cars currently on the lot!");
		System.out.println("____________________________________________________________");
		/*
		int count = 1;
		for(Car c : carList) {
			System.out.println("("+count+")"+c.toString());
			count++;
		}
		*/
		//carList = (ArrayList<Car>) CarPersistence.getCarList();
		int count = 1;
		for(Car c : carList) {
			System.out.println("("+count+")"+c.toString());
			count++;
		}
		
		System.out.println("_____________________________________________________________");
		int check=this.scanner.nextInt();
		if(checkInRange(check,carList)) {
			return check;
		}
		else {
			return -1;
		}
	}
	
	public int displayPurchasable() {
		carList = (ArrayList<Car>) CarPersistence.getAvailableCars();
		if(carList.isEmpty()) {
			return 0;
		}
		System.out.println("These are the cars currently up for sale!");
		System.out.println("____________________________________________________________");
		//carList = (ArrayList<Car>) CarPersistence.getAvailableCars();
		int count = 1;
		for(Car c : carList) {
			System.out.println("("+count+")"+c.toString());
			count++;
		}
		System.out.println("_____________________________________________________________");
		int check = this.scanner.nextInt();
		if(checkInRange(check,carList)) {
			return check;
		}
		else {
			return -1;
		}
	}

	public HashSet<User> getUsers(){
		return this.userSet;
	}
	
	@SuppressWarnings("rawtypes")
	public boolean checkInRange(int a, ArrayList b) {
		
		if(a<=b.size() && a>0) {
			return true;
		}
		else {
			return false;
		}
	}

}
