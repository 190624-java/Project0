package com.revature.phudspeth.project0pt1;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class AppSystem implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3534409674236085418L;
	private static final AppSystem INSTANCE = new AppSystem();
	public static Scanner readLn = new Scanner(System.in);
	private Set<User> userList = new HashSet<>();
	private User curUser;
	
	{
		try(ObjectInputStream ois = new ObjectInputStream( new FileInputStream(new File("userb.ser"))))
		{
			userList = (HashSet<User>)ois.readObject();
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void runFirstScreen() throws SQLException 
	{
		System.out.println("1: Register");
		System.out.println("2: Login");
		System.out.println("");
		int nav = 0;
		String s = readLn.nextLine();
		boolean temp = true;
		while(temp) 
		{
			try {
				
				nav = Integer.parseInt(s);
				temp = false;
				}
			catch(Exception e) {System.out.println("Invalid input, please try again.");}
		}
		if(nav == 1) 
		{
			reg();
		}
		if(nav == 2) 
		{
			login();
		}
	}
	
	public static AppSystem getInstance() 
	{
		return INSTANCE;
	}
	
	void reg() throws SQLException 
	{

		System.out.println("Please input a username for registration, or type esc to exit registration and return to the main screen.");
		String user = readLn.nextLine();
		if(user.equals("esc")) 
		{
			runFirstScreen();
		}

		System.out.println("Please input a password to complete registration, or type esc to exit registration and return to the main screen.");
			
		String pass = readLn.nextLine();
		if(user.equals("esc")) 
		{
			runFirstScreen();
		}
		
		RegistrarDB register = new RegistrarDB();
		register.register(user, pass);
		runFirstScreen();
	}
	
	void login() throws SQLException 
	{
		System.out.println("Please input username:");
		String user = readLn.nextLine();
		System.out.println("Please input password:");
		String pass = readLn.nextLine();
		RegistrarDB login = new RegistrarDB();
		login.login(user, pass);
		if(curUser != null) 
		{
			curUser.showSYSInter();
		}
	}
	public void addUser(String s, int h) throws FileNotFoundException, IOException 
	{
		if(s.equals("Customer")) 
		{
			userList.add(new CustomerDB(h));
		}
		else if(s.equals("Employee")) 
		{
			userList.add(new EmployeeDB(h));
		}
		/*try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("userb.ser"))))
		{
			oos.writeObject(userList);
		}*/
	}
	
	public void setUser(String s, int h) 
	{
		if(s.equals("customer")) 
		{
			curUser = new CustomerDB(h);
		}
		else if(s.equals("employee")) 
		{
			curUser = new EmployeeDB(h);
		}
	}
	
	public void initilizeDebt(CarDB c) throws ClassNotFoundException, IOException, SQLException
	{
		System.out.println("Congratulations on purchasing a car!");
		System.out.println("As part of purchasing a vehicle from our lot, we allow three long-term purchasing options:");
		System.out.println("1: A 3 year plan");
		System.out.println("2: A 6 year plan");
		System.out.println("3: A 10 year plan");
		System.out.println("Please input a number in the console and press enter to confirm your choice.");
		boolean deciding = true;
		while(deciding) 
		{
			try(Connection con = ConnectionManager.getConnection())
			{
				String choice = readLn.nextLine();
				switch (choice) 
				{
				case "1":
					startDebt(36, c.getDebt(), c.getID());
					deciding = false;
					break;
				case "2":
					startDebt(72, c.getDebt(), c.getID());
					deciding = false;
					break;
				case "3":
					startDebt(120, c.getDebt(), c.getID());
					deciding = false;
					break;
				default:
					System.out.println("Invalid selection, please try again.");
					break;
				}
				
			}
		}
	}
	
	public void startDebt(int i, double d, int id) throws SQLException 
	{
		try(Connection con = ConnectionManager.getConnection())
		{
			String sql = "UPDATE cars_paying SET pay_period = ?, owed = ? WHERE car_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, i);
			System.out.println(i);
			stmt.setDouble(2, d);
			System.out.println(d);
			stmt.setInt(3, id);
			System.out.println(id);
			stmt.executeUpdate();
		}
	}
	
	public void calculatePayments(CarDB c) 
	{
		c.updateDebt();
		System.out.println("Current monthly payments are: " + c.showMonthly());
	}

	/*
	public void updateCarlot(Car c) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException 
	{
		List<Car> carLot = getCarLot();
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("clotreg.ser"))))
		{
			if(carLot != null) 
			{

				carLot.add(c);
			}
			else 
			{
				carLot = new ArrayList<Car>();
				carLot.add(c);
			}
			oos.writeObject(carLot);
		}
	}
	
	public void updateCarlot(List<Car> c) throws FileNotFoundException, IOException 
	{
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("clotreg.ser"))))
		{
			oos.writeObject(c);
		}
	}
	*/
	public List<CarDB> getCarLot() throws IOException, ClassNotFoundException, SQLException
	{
		List<CarDB> carLot = new ArrayList<>();
		
		/*try (ObjectInputStream str = new ObjectInputStream(new FileInputStream(new File("clotreg.ser")))) 
		{
			carLot = (List<Car>) str.readObject();
		} 
		catch (FileNotFoundException e) 
		{
			return new ArrayList<>();
		}*/
		
		try(Connection con = ConnectionManager.getConnection())
		{
			String sql = "SELECT car_id, make, car_model, initial_cost FROM car_lot";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet results = stmt.executeQuery();
			while(results.next()) 
			{
				int carid = results.getInt("car_id");
				String make = results.getString("make");
				String model = results.getString("car_model");
				double initPrice = results.getDouble(4);
				carLot.add(new CarDB(carid, make, model, initPrice));
			}
		}
		return carLot;
	}
	
	/*public void updateUserCars(List<CarDB> c) throws FileNotFoundException, IOException 
	{
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("userp.ser"))))
		{
			oos.writeObject(c);
		}
	}
	
	public void updateUserCars(CarDB c) throws FileNotFoundException, IOException, ClassNotFoundException 
	{
		List<CarDB> userCars = getUserCars();
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("userp.ser"))))
		{
			if(userCars != null) 
			{

				userCars.add(c);
			}
			else 
			{
				userCars = new ArrayList<Car>();
				userCars.add(c);
			}
			oos.writeObject(userCars);
		}
	}
	*/
	public List<CarDB> getUserCars() throws IOException, ClassNotFoundException
	{
		List<CarDB> userCars = null;
		
		/*try (ObjectInputStream str = new ObjectInputStream(new FileInputStream(new File("userp.ser")))) 
		{
			userCars = (List<Car>) str.readObject();
		} 
		catch (FileNotFoundException e) 
		{
			return new ArrayList<>();
		}*/
		return userCars;
	}

	public Map<Integer, Double> getOfferList(int carID) throws SQLException 
	{
		try(Connection con = ConnectionManager.getConnection())
		{
			Map<Integer, Double> offers = new HashMap<Integer, Double>();
			String sql = "SELECT user_id, offer_value FROM offers WHERE car_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, carID);
			ResultSet results = stmt.executeQuery();
			while(results.next()) 
			{
				offers.put(results.getInt(1), results.getDouble(2));
			}
			return offers;
		}
		
		
	}
	
	public void updateUserList() throws FileNotFoundException, IOException 
	{
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("userb.ser"))))
		{
			userList.remove(curUser);
			userList.add(curUser);
			oos.writeObject(userList);
		}
	}
	
}
