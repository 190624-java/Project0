package com.revature.phudspeth.project0pt1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class EmployeeDB extends User implements UserInterface, Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1028618923834044049L;
	private int userId;
	AppSystem SYS;
	{
		SYS = AppSystem.getInstance();
	}
	

	
	@Override
	public int getID() 
	{
		return userId;
	}
	
	public EmployeeDB(int h) 
	{
		this.userId = h;
	}

	public void addCar() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException 
	{
		System.out.println("Adding new car to the lot: Please enter the make of the car.");
		boolean entering = true;
		String make ="";
		String model="";
		double initCost=0;
		while(entering) 
		{
			make = SYS.readLn.nextLine();
			System.out.println("You have input: "+ make);
			System.out.println("Is this correct? (y/n)");
			boolean temp = true;
			while(temp) {
				String s = SYS.readLn.nextLine();
				switch(s) 
				{
				case"y":
					temp = false;
					entering = false;
					break;
				case"n":
					System.out.println("Please re-enter the make of the car.");
					temp = false;
					break;
				default:
					System.out.println("Invalid input, try again.");
					break;
				}
			}
		}
		entering = true;
		System.out.println("Please input the model.");
		while(entering) 
		{
			model = SYS.readLn.nextLine();
			System.out.println("You have input: "+model);
			System.out.println("Is this correct? (y/n)");
			boolean temp = true;
			while(temp) {
				String s = SYS.readLn.nextLine();
				switch(s) 
				{
				case"y":
					temp = false;
					entering = false;
					break;
				case"n":
					System.out.println("Please re-enter the model of the car.");
					temp = false;
					break;
				default:
					System.out.println("Invalid input, try again.");
					break;
				}
			}
		}
		entering = true;
		System.out.println("Please input the initial price listing for the car.");
		while(entering) 
		{
			boolean temp = true;
			while(temp)
			{
				String s = SYS.readLn.nextLine();
			    try 
			    {
			        initCost = Double.parseDouble(s);
			        temp = false;
			    } 
			    catch (NumberFormatException | NullPointerException nfe) 
			    {
			        System.out.println("Invalid input, try again.");
			    }
			}
			temp = true;
			System.out.println("You have input: " + initCost);
			System.out.println("Is this correct? (y/n)");
			while(temp) {
				String s = SYS.readLn.nextLine();
				switch(s) 
				{
				case"y":
					temp = false;
					entering = false;
					break;
				case"n":
					temp = false;
					break;
				default:
					System.out.println("Invalid input, try again.");
					break;
				}
			}
		}
		entering = true;
		System.out.println("Updating lot...");
		try(Connection con = ConnectionManager.getConnection())
		{
			String sql = "INSERT INTO car_lot(make, car_model, initial_cost) VALUES(?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, make);
			stmt.setString(2, model);
			stmt.setDouble(3, initCost);
			stmt.executeUpdate();
		}
		System.out.println("Lot Updated.");
		System.out.println("Add another car? (y/n)");
		while(entering) 
		{
			String s = SYS.readLn.nextLine();
			switch(s) 
			{
			case"y":
				this.addCar();
				break;
			case"n":
				this.showSYSInter();
				break;
			default:
				System.out.println("Invalid input, try again.");
				break;
			}
		}

	}
	
	public void removeCar() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException 
	{
		List<CarDB> carLot = SYS.getCarLot();
		if(carLot.isEmpty()) 
		{
			System.out.println("The lot is empty, there are no cars to remove.");
			showSYSInter();
		}
		else {
			for(int i = 0; i<carLot.size(); i++) 
			{
				System.out.print(i);
				displayCarInfo(carLot.get(i));
			}
		}
		boolean choice = true;
		while(choice) 
		{
			int i = 0;
			System.out.println("Enter the number of the car you wish to delete, or type esc to return to employee menu.");
				boolean temp = true;
				while(temp)
				{
					String s = SYS.readLn.nextLine();
					if(s.equals("esc")) 
					{
						showSYSInter();
					}
				    try 
				    {
				        i = Integer.parseInt(s);
				        temp = false;
				    } 
				    catch (NumberFormatException | NullPointerException nfe) 
				    {
				        System.out.println("Invalid input, try again.");
				    }
				}
				
				if(i>carLot.size()-1) 
				{
					System.out.println("Invalid input, try again.");
				}
				else 
				{
					System.out.print("You are deleting: ");
					displayCarInfo(carLot.get(i));
					System.out.println("Is this the correct choice? (y/n)");
					temp = true;
					while(temp) {
						String s = SYS.readLn.nextLine();
						switch(s) 
						{
						case "y":
							System.out.println("Updating lot...");
							try(Connection con = ConnectionManager.getConnection())
							{
								String sql = "DELETE FROM car_lot WHERE car_id = ?";
								PreparedStatement stmt = con.prepareStatement(sql);
								stmt.setInt(1, carLot.get(i).getID());
								stmt.executeUpdate();
							}
							System.out.println("Lot Updated.");
							temp = false;
							choice = false;
							break;
						case "n":
							temp = false;
							break;
						default:
							System.out.println("Invalid input, try again.");
							break;
						}
					}

					System.out.println("Remove another car? (y/n)");
					temp = true;
					while(temp) 
					{
						String s = SYS.readLn.nextLine();
						switch(s) 
						{
						case "y":
							this.removeCar();
							break;
						case "n":
							this.showSYSInter();
							break;
						}
						System.out.println("Invalid input, try again.");
					}
				}
			}
		}
	
	@SuppressWarnings("static-access")
	public void offerCar() throws ClassNotFoundException, IOException, SQLException 
	{
		System.out.println("Loading lot...");
		List<CarDB> carLot = SYS.getCarLot();
		for(int i = 0; i<carLot.size();i++) 
		{
			System.out.print(i + " ");
			displayCarInfo(carLot.get(i));
		}
		try(Connection con = ConnectionManager.getConnection()){
			System.out.println("Select the number of the car you wish to inspect, or enter esc to return to the main menu.");
			boolean temp = true;
			while(temp) 
			{
				int g = 0;
				String s = SYS.readLn.nextLine();
				switch(s) 
				{
				case"esc":
					temp = false;
					showSYSInter();
					break;
				default:
					try 
					{
						g = Integer.parseInt(s);
						CarDB c = carLot.get(g);
						Map<Integer, Double> offers = SYS.getOfferList(c.getID());
						for (Entry<Integer, Double> entry : offers.entrySet())
						{
							System.out.println("UserID: " + entry.getKey() + " has made an offer of " + entry.getValue());
						}
						if(offers.isEmpty()) 
						{
							System.out.println("No offers have been made on this car yet.");
							showSYSInter();
						}
						System.out.println("Type out the id number of the offer you would like to evaluate, or type esc to return to the main menu.");
						while(temp) 
						{
							s = SYS.readLn.nextLine();
							try 
							{
								g = Integer.parseInt(s);
								for(int i : offers.keySet()) 
								{
									if(g == i) 
									{
										System.out.println("Would you like to reject or accept this offer?");
										while(temp) 
										{
											s = SYS.readLn.nextLine();
											if(s.equals("accept")) 
											{
												c.sellCar(g, offers.get(i));
												sellCar(c);
												removeOffers(c);
												
												
												String sql = "DELETE FROM car_lot WHERE car_id = ?";
												PreparedStatement stmt = con.prepareStatement(sql);
												stmt.setInt(1, c.getID());
												System.out.println("Deleting car from the lot...");
												stmt.executeUpdate();
												temp = false;
											}
											else if(s.equals("reject")) 
											{
												String sql = "DELETE FROM offers WHERE user_id = ? AND car_id = ?";
												PreparedStatement stmt = con.prepareStatement(sql);
												stmt.setInt(1, g);
												stmt.setInt(2, c.getID());
												stmt.executeUpdate();
												temp = false;
											}
											else 
											{
												System.out.println("Invalid input, please try again.");
											}
										}
									}
								}
							}
							catch(Exception e) 
							{
								e.printStackTrace();
								System.out.println("Invalid input, please try again.");
								temp = false;
							}
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
						System.out.println("Invalid input, try again.");
					}
				}
			}
		}
		//SYS.updateCarlot(carLot);
		showSYSInter();
	}
	
	void sellCar(CarDB c) throws SQLException 
	{
		try(Connection con = ConnectionManager.getConnection()){
			String sql = "INSERT INTO cars_paying (car_id, user_id, selling_price, make, car_model, owed) VALUES(?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, c.getID());
			stmt.setInt(2, c.getOwner());
			stmt.setDouble(3, c.getPurchaseCost());
			stmt.setString(4, c.make);
			stmt.setString(5, c.model);
			stmt.setDouble(6, c.getDebt());
			System.out.println("Adding car into paying lot...");
			stmt.executeUpdate();
		}
	}
	
	void removeOffers(CarDB g) throws SQLException 
	{
		try(Connection con = ConnectionManager.getConnection())
		{
			String sql = "DELETE FROM offers WHERE car_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, g.getID());
			System.out.println("Deleting car from offers list and all offers made on it...");
			stmt.executeUpdate();
		}
	}
	
	/*public void checkUserCar() throws ClassNotFoundException, IOException 
	{
		List<CarDB> userCars = SYS.getUserCars();
		try {
		for(CarDB c: userCars) 
		{
			System.out.println("Account #:" + c.getOwner());
			c.showCarDetails();
		}
		showSYSInter();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			System.out.println("There are no cars currently being paid upon.");
		}
	}*/
	
	void displayCarInfo(CarDB carDB) 
	{
		System.out.print(carDB.getID() + " " + carDB.make + " " + carDB.model + " " + carDB.purchaseCost+ "\n");
	}
	
	@Override
	public void showSYSInter() 
	{
		SYS = AppSystem.getInstance();
		System.out.println("Displaying Beta Branch");
		System.out.println("1: Add Car");
		System.out.println("2: Remove Car");
		System.out.println("3: View Car Offers");
		/*System.out.println("4: View User and Payment Information");*/
		boolean choice = true;
		while(choice) 
		{
			String s = SYS.readLn.nextLine();
			switch(s) 
			{
			case"1":
				try {
					addCar();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case"2":
				try {
					removeCar();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case"3":
				try {
					offerCar();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			/*case"4":
				try {
					checkUserCar();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;*/
			default:
				System.out.println("Invalid input, try again.");
				break;
			}
		}
		
	}

}
