package basicpackage;

import java.io.EOFException;
import java.io.File;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;

import java.io.IOException;

import java.io.ObjectInputStream;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;

import java.io.IOException;

import java.io.ObjectInputStream;

import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;
public class CarSystem{
	public static List<User> allUserList = new ArrayList<User>();
	public static List<Car> carsOnLot = new LinkedList<Car>();
	public static List<Payment> allPayments = new LinkedList<Payment>();
	public static final String userfile = "user-data.ser";
	public static final String carfile = "car-data.ser";
	public static final String paymentfile = "payment-data.ser";
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException{
		Scanner userInput = new Scanner(System.in);
		allUserList = pleaseLetThisWork();
		//carsOnLot.add(new Car(3500.00,"Chrystler", "PT Cruiser", "Black", 2006, true, null));
		//carsOnLot.add(new Car(4500.00, "Dodge", "Ram", "Blue", 2016, false, allUserList.get(1)));
		//updateCarsOnLot();
		//updateCarsOnLot(new Car(3500.00, "Chrystler", "PT Cruiser", "Black", 2006));
		carsOnLot = loadCarsOnLot();
		sortCars();
		allPayments = loadPaymentRecords();
		sortPayments();
		//System.out.println(carsOnLot.get(1).owner.username);
		//System.out.println(allUserList.get(1).carArray.get(0).make);
		//allPayments.add(new Payment(allUserList.get(1), carsOnLot.get(1), 3500, 3));
		//updatePaymentRecords();
		int choice;
		//updateUserRecords(new User("jimmy", "whetzel", "employee"));
		//updateUserRecords(new User("frankie", "muniz", "customer"));
		//allUserList = pleaseLetThisWork();
		//populateData();
		//updateUserRecords();
		//System.out.println(carsOnLot.get(1).owner.username);
		do {

			//updateUserRecords(new User("jeff", "bibs", "employee"));
			//allUserList = protoLoadUsers();
			System.out.println("Welcome! Press 1 to login. Press 2 to make a new account, or 0 to exit: ");
			choice = userInput.nextInt();
			switch (choice) {
			case 0:
				break;
			case 1:
				System.out.println("Please enter your username: ");
				String userName = userInput.next();
				userInput.nextLine();
				// search user list for entered username
				System.out.println("Please enter your password: ");
				String password = userInput.next();
				userInput.nextLine();
				// check if password matches username
				// use username and password to get usertype
				loginExistingUser(userName, password);
				break;
			case 2:
				System.out.println("Please enter your username: ");
				String newUsername = userInput.next();
				userInput.nextLine();
				//String newUsername = userInput.nextLine();
				System.out.println("Please enter your new password: ");
				String newPassword = userInput.next();
				userInput.nextLine();
				//String newPassword = userInput.nextLine();
				System.out.println(newUsername + " " + newPassword);
				//addNewUser(newUsername, newPassword, "customer");
				updateUserRecords(new User(newUsername, newPassword, "customer"));
				//allUserList = loadUsers();
				//addNewUser(newUsername, newPassword, "customer");
			case 3:
				//allUserList = loadUsers();
				for(User u : allUserList)
				{
					System.out.println(u.getUsername() + " ");
				}
			}
		} while (choice != 0);
		updateCarsOnLot();
		updateUserRecords();
		updatePaymentRecords();
		userInput.close();
	}
	public static void sortCars()
	{
		for(Car c : carsOnLot)
		{
			if(c.lotOrNot == true)
			{
				
			}
			else
			{
				for(User u : allUserList)
				{
					//System.out.print(u.username + " " + c.owner.username);
					if(u.username.equals(c.owner.username))
					{
						
						u.carArray.add(c);
						//System.out.println(u.carArray.get(0).make);
					}
				}
			}
		}
	}
	public static List<User> pleaseLetThisWork() throws FileNotFoundException, ClassNotFoundException, IOException
	{
		List<User> users = null;
		try (ObjectInputStream str = new ObjectInputStream(new FileInputStream(new File("user-data.ser")))) {

			List<User> readObject = (List<User>)str.readObject();
			users = readObject;

			//System.out.println(users);

		} catch (FileNotFoundException e) {

			return new ArrayList<>();

		}
		return users;
	}
	public static List<User> protoLoadUsers() 
	{
		ArrayList<User> al = new ArrayList<User>();
	    boolean cont = true;
	        try {
	            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userfile));
	            while(ois.available() > 0){
	                  User obj=null;
	                try {
	                    obj = (User)ois.readObject();
	                } catch (ClassNotFoundException e) {
	                    e.printStackTrace();
	                } catch (EOFException e)
	                {
	                	e.printStackTrace();
	                }
	                  if(obj != null)
	                  {
	                     al.add(obj);
	                  }
	                  
	               }
	            ois.close();
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	return al;
	}
	public static List<User> loadUsers() throws IOException, ClassNotFoundException
	{
		boolean cont = true;
		List<User> load = new ArrayList<User>();
		try (ObjectInputStream str = new ObjectInputStream(new FileInputStream(userfile))) {
			while(cont) {
				User obj = null;
			try{ obj = (User)(str.readObject());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (EOFException e)
			{
				str.close();
				e.printStackTrace();
			}
			if(obj != null)
			{
				load.add(obj);
			}
			else
			{
				cont = false;
			}

			}
			str.close();
		} catch (FileNotFoundException e) {

			return new ArrayList<>();

		}
		//str.close();
		return load;
	}
	public static void addNewUser(String username, String password, String userType) throws FileNotFoundException, IOException, ClassNotFoundException{
		boolean doesUserExist;
		do {
			doesUserExist = false;
			for (User o : allUserList) {
				//System.out.println(o.getUsername() + " " + username);
				if (o.getUsername().contains(username) && o.getUsername()!= null) {
					doesUserExist = true;
					break;
				}
			}
			if (doesUserExist) {
				//System.out.println(doesUserExist);
				System.out.println("That username is taken. Please enter another: ");
				Scanner userCheck = new Scanner(System.in);
				username = userCheck.next();
			}
		} while (doesUserExist);
		//allUserList.add(new User(username, password, userType));
		updateUserRecords(new User(username, password, userType));
		for(int i = 0; i < allUserList.size(); i++)
		{
			System.out.println(allUserList.get(i).getUsername());
		}
	}
	public static void updatePaymentRecords() throws FileNotFoundException, IOException, ClassNotFoundException
	{
		try (ObjectOutputStream oos = new ObjectOutputStream(

				new FileOutputStream(paymentfile))) {
			if (allPayments != null) {

				//System.out.println("Heroes found");

				//allUserList.add(newUser);

			} else {

				allPayments = new ArrayList<Payment>();

				//allUserList.add(newUser);

			}
			//User obj = newUser;
			List<Payment> use = allPayments;
			oos.writeObject(use);
			oos.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}
	public static void updateUserRecords() throws FileNotFoundException, IOException, ClassNotFoundException{
		//allUserList = loadUsers();
		try (ObjectOutputStream oos = new ObjectOutputStream(

				new FileOutputStream(userfile))) {
			if (allUserList != null) {

				//System.out.println("Heroes found");

				//allUserList.add(newUser);

			} else {

				allUserList = new ArrayList<User>();

				//allUserList.add(newUser);

			}
			//User obj = newUser;
			List<User> use = allUserList;
			oos.writeObject(use);
			oos.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
	public static void updateUserRecords(User newUser) throws FileNotFoundException, IOException, ClassNotFoundException{
		//allUserList = loadUsers();
		try (ObjectOutputStream oos = new ObjectOutputStream(

				new FileOutputStream(userfile))) {
			if (allUserList != null) {

				//System.out.println("Heroes found");

				allUserList.add(newUser);

			} else {

				allUserList = new ArrayList<User>();

				allUserList.add(newUser);

			}
			User obj = newUser;
			List<User> use = allUserList;
			oos.writeObject(use);
			oos.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
	public static List<Car> loadCarsOnLot() throws IOException, ClassNotFoundException
	{
		List<Car> cars = null;
		try (ObjectInputStream carInput = new ObjectInputStream(new FileInputStream(new File("car-data.ser")))) {
			List<Car> obj = (List<Car>)(carInput.readObject()); 
			cars = obj;

		} catch (FileNotFoundException e) {

			return new ArrayList<>();

		} catch (EOFException e) {
			e.printStackTrace();
		}
		return cars;
	}
	public static List<Payment> loadPaymentRecords() throws IOException, ClassNotFoundException
	{
		List<Payment> pay = null;
		try (ObjectInputStream payInput = new ObjectInputStream(new FileInputStream(new File("payment-data.ser")))) {
			List<Payment> obj = (List<Payment>)(payInput.readObject()); 
			pay = obj;

		} catch (FileNotFoundException e) {

			return new ArrayList<>();

		} catch (EOFException e) {
			e.printStackTrace();
		}
		return pay;
	}
	public static void sortPayments()
	{
		for(Payment p : allPayments)
		{
			for(User o : allUserList)
			{
				if(p.owner.username.equals(o.username))
				{
					o.userPayments.add(p);
				}
			}
		}
	}
	public static void removeCarFromLot(Car removed)
	{
		
	}
	public static void updateCarsOnLot() throws FileNotFoundException, IOException, ClassNotFoundException
	{
		//carsOnLot = loadCarsOnLot();
		try (ObjectOutputStream oos = new ObjectOutputStream(

				new FileOutputStream(carfile))) {
			if (carsOnLot != null) {

				//System.out.println("Heroes found");

				//carsOnLot.add(newCar);

			} else {

				carsOnLot = new ArrayList<Car>();

				//carsOnLot.add(newCar);

			}
			List<Car> obj = carsOnLot;

			oos.writeObject(obj);
			oos.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}
	public static void loginExistingUser(String username, String password)
	{
		User currentUser = null;
		boolean isInSystem = true;
		for(User u : allUserList)
		{
			//System.out.println(username + " " + u.getUsername());
			if(username.equals(u.getUsername()))
			{
				//System.out.println(password + " " + password);
				if(password.equals(u.getPassword()))
				{
					isInSystem = true;
					currentUser = u;
					break;
				}
				else
				{
					isInSystem = false;
				}
			}
			else
			{
				isInSystem = false;
			}
		}
		if(isInSystem && currentUser != null)
		{
			System.out.println("Welcome back, " + username + "!");
			presentOptionsBasedOnUserType(currentUser);
		}
		else
		{
			System.out.println("Your username/password are not in our system.");
		}
	}
	public static void presentOptionsBasedOnUserType(User u)
	{
		Scanner ui = new Scanner(System.in);
		switch(u.getUserType())
		{
		case "employee":
			int empChoice;
			System.out.println("Here are all cars currently on lot: ");
			printCarArray(carsOnLot);
			do {
			System.out.println("Press 1 to add a car to the lot. Press 2 to view offers. Press 3 to remove a car from the lot. Press 4 to view all payments. Press 5 to log out: ");
			empChoice = ui.nextInt();
			if(empChoice == 1)
			{
				System.out.println("Please enter the car's make: ");
				String make = ui.next();
				System.out.println("Please enter the car's model: ");
				String model = ui.next();
				System.out.println("Please enter the car's year" );
				int year = ui.nextInt();
				System.out.println("Please enter the car's color: ");
				String color = ui.next();
				System.out.println("Please enter the car's price: ");
				double price = ui.nextDouble();
				carsOnLot.add(new Car(price, make, model, color, year, true, null));				
			}
				if (empChoice == 2) {
					System.out.println("Please enter the index of the car you wish to view offers for: ");
					int carIndex = ui.nextInt();
					if (!carsOnLot.get(carIndex).offerList.isEmpty()) {
						u.viewOffers(carsOnLot.get(carIndex));
						System.out.println(
								"Press 1 if you would like to accept an offer. Press 2 if you would like to reject an offer: ");
						int acceptVsReject = ui.nextInt();
						if (acceptVsReject == 1) {
							System.out.println("Please enter the index of the offer you wish to accept: ");
							offerAccepted(carsOnLot.get(carIndex), carsOnLot.get(carIndex).offerList.get(ui.nextInt()).offerer);
							sortPayments();
							sortCars();
						}
						if (acceptVsReject == 2) {
							System.out.println("Please enter the index of the offer you wish to reject: ");
							int removalIndex = ui.nextInt();
							rejectOffer(carsOnLot.get(carIndex), carsOnLot.get(carIndex).offerList.get(removalIndex));
						}
					}
					else
					{
						System.out.println("No offers are available for this car.");
					}
				}
			if(empChoice == 3)
			{
				String confirm;
				do {
				System.out.println("Please enter the index of the car you wish to remove from the lot: ");
				int toRemove = ui.nextInt();
				System.out.println(carsOnLot.get(toRemove).make + " " + carsOnLot.get(toRemove).model + " " + carsOnLot.get(toRemove).price);
				System.out.println("Are you sure you wish to remove this car? Enter yes/no: ");
				confirm = ui.next();
				if(confirm.equals("yes"))
				{
					carsOnLot.get(toRemove).lotOrNot = false;
				}
				}while(confirm.equals("no"));
			}
			if(empChoice == 4)
			{
				for(User user : allUserList)
				{
					if(!user.userPayments.isEmpty())
					{
						for(Payment p : user.userPayments)
						{
							System.out.println(p.car.make + " " + p.car.model + " " + p.amount + " " + p.paymentsRemaining + " " + p.owner.username);
						}
					}
				}
			}
			}while (empChoice != 5);
			break;
		case "customer":
			int custChoice;
			do
			{
			System.out.println("Press 1 to view car lot. Press 2 to view personal cars. Press 3 to view payments. Press 4 to log out: ");
			custChoice = ui.nextInt();
			if(custChoice == 1)
			{
				printCarArray(carsOnLot);
				System.out.println("Press 1 to make an offer on a car. Press 0 to go back to previous menu: ");
				custChoice = ui.nextInt();
				if(custChoice == 1)
				{
					int carIndex;
					String correct;
					do {
					System.out.println("Please enter the index of the car you wish to make an offer for: ");
					carIndex = ui.nextInt();
					while(carsOnLot.get(carIndex).lotOrNot == false && carIndex < carsOnLot.size())
					{
						carIndex++;
					}
					System.out.println(carsOnLot.get(carIndex).make + " " + carsOnLot.get(carIndex).model + " " + carsOnLot.get(carIndex).year + " " + carsOnLot.get(carIndex).price);
					System.out.println("Is this the correct car? Answer yes/no: ");
					correct = ui.next();
					}while(correct == "no");
					System.out.println("Please enter the amount you wish to offer: ");
					double offer = ui.nextDouble();
					carsOnLot.get(carIndex).addOffer(offer, u);
				}
			}
				if (custChoice == 2) {
					for (Car car : carsOnLot) {
						if (car.lotOrNot == false) {
							if (car.owner.username.equals(u.username)) {
								System.out.println(car.make + " " + car.model);
							}
						}
					}
				}
			if(custChoice == 3)
			{
				u.viewPayments();
			}
			}while(custChoice != 4);
			break;
		}
	}
	public static void printCarLot()
	{
		for(Car c : carsOnLot)
		{
			if(c.lotOrNot)
			{
			System.out.println(c.make + " " + c.model + " " + c.year);
			}
		}
	}
	public static void printCarArray(List<Car> c)
	{
		for(Car car : c)
		{
			if(car.lotOrNot)
			{
			System.out.println(car.make + " " + car.model + " " + car.year);
			}
		}
	}
	public static void offerAccepted(Car c, User o)
	{
		o.carArray.add(c);
		c.offerList.clear();
		c.owner = o;
		c.lotOrNot = false;
		Payment e = new Payment(o, c, c.price, 12);
		//o.userPayments.add(e);
		allPayments.add(e);
		carsOnLot.remove(c);
	}
	public static void rejectOffer(Car c, Offer o)
	{
		c.offerList.remove(o);
	}
	public void passLotArrayToEmployee(User u)
	{
		u.giveArray(carsOnLot);
	}
	public static void populateData()
	{
		allUserList.get(1).carArray.add(carsOnLot.get(0));
		carsOnLot.get(0).addOffer(2400, allUserList.get(1));
		allUserList.get(1).userPayments.add(new Payment(allUserList.get(1), carsOnLot.get(0), 1500, 4));
		allUserList.get(1).viewPayments();
	}
}
