package project0;

import java.io.EOFException;
import java.io.File;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;

import java.io.IOException;

import java.io.ObjectInputStream;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class Driver{
	public static List<User> allUserList = new ArrayList<User>();
	public static List<Car> carsOnLot = new LinkedList<Car>();
	public static List<Payment> allPayments = new LinkedList<Payment>();
	public static final String userfile = "user-data.ser";
	public static final String carfile = "car-data.ser";
	public static final String paymentfile = "payment-data.ser";
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException{
		Scanner userInput = new Scanner(System.in);
		allUserList = updateUserList();
		//printLotCars();
		//printAllCars();
		carsOnLot = updateCarLot();
		//printAllCars();
		int choice;
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
				//updateUserRecords(new User(newUsername, newPassword, "customer"));
				//allUserList = loadUsers();
				//addNewUser(newUsername, newPassword, "customer");
				addUserToTable(newUsername, newPassword, "customer");
				allUserList = updateUserList();
			case 3:
				//allUserList = loadUsers();
				for(User u : allUserList)
				{
					System.out.println(u.getUsername() + " ");
				}
			}
		} while (choice != 0);
		userInput.close();
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

		for(int i = 0; i < allUserList.size(); i++)
		{
			System.out.println(allUserList.get(i).getUsername());
		}
	}
	public static void loginExistingUser(String username, String password)
	{
		User currentUser = null;
		boolean isInSystem = true;
		for(User u : allUserList)
		{
			System.out.println(username + " " + u.getUsername());
			if(username.equals(u.getUsername()))
			{
				System.out.println(password + " " + password);
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
			printLotCars();
			do {
			System.out.println("Press 1 to add a car to the lot. Press 2 to view offers. Press 3 to remove a car from the lot. Press 4 to view all payments. Press 5 to log out: ");
			empChoice = ui.nextInt();
			if(empChoice == 1)
			{
				System.out.println("Please enter the car's make: ");
				String make = ui.next();
				System.out.println("Please enter the car's model: ");
				String model = ui.next();
				System.out.println("Please enter the car's price: ");
				double price = ui.nextDouble();
				addCarToLot(make, model, price);
				//carsOnLot.add(new Car(price, make, model, color, null));				
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
				System.out.println("Are you sure you wish to remove this car? Enter yes/no: ");
				confirm = ui.next();
				if(confirm.equals("yes"))
				{
					deleteCar(toRemove);
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
				printLotCars();
				System.out.println("Press 1 to make an offer on a car. Press 0 to go back to previous menu: ");
				custChoice = ui.nextInt();
				if(custChoice == 1)
				{
					int carIndex;
					String correct;
					do {
					System.out.println("Please enter the index of the car you wish to make an offer for: ");
					carIndex = ui.nextInt();
					System.out.println(carsOnLot.get(carIndex).make + " " + carsOnLot.get(carIndex).model + " " + carsOnLot.get(carIndex).year + " " + carsOnLot.get(carIndex).price);
					System.out.println("Is this the correct car? Answer yes/no: ");
					correct = ui.next();
					}while(correct == "no");
					System.out.println("Please enter the amount you wish to offer: ");
					double offer = ui.nextDouble();
					addOffer(carsOnLot.get(carIndex), u, offer);
					//carsOnLot.get(carIndex).addOffer(offer, u);
				}
			}
				if (custChoice == 2) {
					List<Car> userCars = getCarsByOwner(u.getUsername());
					for(Car c : userCars)
					{
						System.out.println(c.make + " " + c.model + " ");
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
		updateCarOwner(c, o);
		addPayment(o.getUsername(), c.price, c);
	}
	public static void rejectOffer(Car c, Offer o)
	{
		c.offerList.remove(o);
	}
	public void passLotArrayToEmployee(User u)
	{
		u.giveArray(carsOnLot);
	}
	public static void addUserToTable(String username, String password, String userType)
	{
		try(Connection conn = ConnectionFactory.getConnection())
		{
			String sql = "INSERT INTO Users(username, password, usertype)"
						+ "VALUES(?, ?, ?)";
			String[] strPrimaryKeys = {"user_id"};
			PreparedStatement stmt = conn.prepareStatement(sql, strPrimaryKeys);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, userType);
			
			int rowsAffected = stmt.executeUpdate();
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static void updateCarOwner(Car c, User o)
	{
		try(Connection conn = ConnectionFactory.getConnection())
		{
			String sql = "UPDATE Cars" +
					"SET owner = " + o.getUsername()
					+ "WHERE make = " + c.make
					+ "AND model = " + c.model;
		Statement stmt = conn.createStatement();
		stmt.execute(sql);
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static void addOffer(Car c, User o, double d)
	{
		try(Connection conn = ConnectionFactory.getConnection())
		{
			String sql = "INSERT INTO Payments(amount, offerer, car_make, car_model)"
						+ "VALUES(?, ?, ?, ?)";
			String[] strPrimaryKeys = {"offer_id"};
			PreparedStatement stmt = conn.prepareStatement(sql, strPrimaryKeys);
			stmt.setDouble(1, d);
			stmt.setString(2, o.getUsername());
			stmt.setString(3, c.make);
			stmt.setString(4, c.model);
			
			int rowsAffected = stmt.executeUpdate();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static void addPayment(String paymentOwner, double amountOwed, Car c)
	{
		try(Connection conn = ConnectionFactory.getConnection())
		{
			String sql = "INSERT INTO Payments(owner, amount, car_make, car_model)"
						+ "VALUES(?, ?, ?, ?)";
			String[] strPrimaryKeys = {"payment_id"};
			PreparedStatement stmt = conn.prepareStatement(sql, strPrimaryKeys);
			stmt.setString(1, paymentOwner);
			stmt.setDouble(2, amountOwed);
			stmt.setString(3, c.make);
			stmt.setString(4, c.model);
			
			int rowsAffected = stmt.executeUpdate();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static void addCarToLot(String make, String model, double price)
	{
		try(Connection conn = ConnectionFactory.getConnection())
		{
			String sql = "INSERT INTO Cars(make, model, owner, price)"
						+ "VALUES(?, ?, ?, ?)";
			String[] strPrimaryKeys = {"car_id"};
			PreparedStatement stmt = conn.prepareStatement(sql, strPrimaryKeys);
			stmt.setString(1, make);
			stmt.setString(2, model);
			stmt.setString(3, "lot");
			stmt.setDouble(4, price);
			
			int rowsAffected = stmt.executeUpdate();
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static List<User> updateUserList()
	{
		List<User> userList = new ArrayList<User>();
		try(Connection conn = ConnectionFactory.getConnection())
		{
			Statement stmt = conn.createStatement();
			String sql = "SELECT username, password, usertype"
					+ " FROM Users";
			ResultSet results = stmt.executeQuery(sql);
			
			while(results.next())
			{
				String username = results.getString("username");
				String password = results.getString("password");
				String userType = results.getString("usertype");
				User user = new User(username, password, userType);
				userList.add(user);
			}
			
			return userList;
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return userList;
	}
	public static User getUserByLogin(String username, String password)
	{
		User u = null;
		
		return u;
	}
	public static List<Car> updateCarLot()
	{
		List<Car> car = new LinkedList<Car>();
		try(Connection conn = ConnectionFactory.getConnection())
		{
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM Cars";
			ResultSet results = stmt.executeQuery(sql);
			
			while (results.next())
			{
				double carPrice = results.getDouble("price");
				String carMake = results.getString("make");
				String carModel = results.getString("model");
				String carOwner = results.getString("owner");
				car.add(new Car(carMake, carModel, carPrice, carOwner));
			}
			return car;
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
		return car;
	}
	public static void printAllCars()
	{
		try(Connection conn = ConnectionFactory.getConnection())
		{
		Statement stmt = conn.createStatement();
		String sql = "SELECT * " + "FROM Cars";
		ResultSet results = stmt.executeQuery(sql);
		
		while(results.next())
		{
			String make = results.getString("make");
			String model = results.getString("model");
			double price = results.getDouble("price");
			String owner = results.getString("owner");
			System.out.println(make + " " + model + " $" + price);
		}
	}catch(SQLException e)
	{
		e.printStackTrace();
	}
	}
	public static void printLotCars()
	{
		try(Connection conn = ConnectionFactory.getConnection())
		{
			Statement stmt = conn.createStatement();
			String sql = "SELECT * " + "FROM Cars" + " WHERE owner = 'lot' ";
			ResultSet results = stmt.executeQuery(sql);
			
			while(results.next())
			{
				int id = results.getInt("car_id");
				String make = results.getString("make");
				String model = results.getString("model");
				double price = results.getDouble("price");
				String owner = results.getString("owner");
				System.out.println(id + " " + make + " " + model + " $" + price);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public List<Offer> getOffersByCar(String make, String model)
	{
		List<Offer> offers = null;
		try(Connection conn = ConnectionFactory.getConnection())
		{
			Statement stmt = conn.createStatement();
			String sql = "SELECT amount, offerer"
						+ "FROM Offers"
						+ "WHERE make = " + make
						+ "AND model = " + model;
			ResultSet results = stmt.executeQuery(sql);
			
			while(results.next())
			{
				double amount = results.getDouble(1);
				String offerer = results.getString(2);
				offers.add(new Offer(amount, offerer));
			}
			return offers;
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return offers;
	}
	public static void deleteCar(int carID)
	{
		try(Connection conn = ConnectionFactory.getConnection())
		{
			//Statement stmt = conn.createStatement();
			String sql = "DELETE FROM Cars"
						+ " WHERE car_id = ? ";
			String[] strPrimaryKeys = {"car_id"};
			PreparedStatement stmt = conn.prepareStatement(sql, strPrimaryKeys);
			stmt.setInt(1, carID);
			
			int rowsAffected = stmt.executeUpdate();
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static List<Car> getCarsByOwner(String username)
	{
		
		List<Car> car = new ArrayList<>();
		try(Connection conn = ConnectionFactory.getConnection())
		{
			Statement stmt = conn.createStatement();
			String sql = "SELECT make, model, price"
						+ " FROM Cars"
						+ " WHERE owner = '" + username + "' ";
			
			ResultSet results = stmt.executeQuery(sql);
			
			while(results.next())
			{
				String carMake = results.getString(1);
				String carModel = results.getString(2);
				double carPrice = results.getDouble(3);
				car.add(new Car(carMake, carModel, carPrice, username));
			}
			return car;
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
		return car;
	}
	public List<Payment> getPaymentsByOwner(User username)
	{
		List<Payment> payments = null;
		try(Connection conn = ConnectionFactory.getConnection())
		{
			Statement stmt = conn.createStatement();
			String sql = "SELECT owner, amount, car"
						+ "FROM Payments"
						+ "WHERE owner = " + username.getUsername();
			ResultSet results = stmt.executeQuery(sql);
			
			while(results.next())
			{
				String user = results.getString(1);
				double amount = results.getDouble(2);
				String car = results.getString(3);
				
				payments.add(new Payment(user, car, amount));
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return payments;
	}
	public static void populateData()
	{
		allUserList.get(1).carArray.add(carsOnLot.get(0));
		carsOnLot.get(0).addOffer(2400, allUserList.get(1));
		allUserList.get(1).userPayments.add(new Payment(allUserList.get(1), carsOnLot.get(0), 1500, 4));
		allUserList.get(1).viewPayments();
	}
}
