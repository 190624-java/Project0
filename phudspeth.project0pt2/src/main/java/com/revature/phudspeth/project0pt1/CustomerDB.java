package com.revature.phudspeth.project0pt1;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CustomerDB extends User implements UserInterface, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -858427200710670784L;
	private int userId;
	AppSystem SYS = AppSystem.getInstance();
	
	
	@Override
	public int getID() 
	{
		return userId;
	}
	
	public CustomerDB(int h) 
	{
		this.userId = h;
	}

	@Override
	public void addCar(Car c) 
	{
	}

	@Override
	public void offerCar(int i, double j) 
	{
		
	}
	
	public void viewCarlot() throws ClassNotFoundException, IOException, SQLException 
	{
		List<CarDB> carLot = SYS.getCarLot();
		if(carLot.isEmpty()) 
		{
			System.out.println("We're fresh out of cars! Check back later.");
		}
		for(int i = 0; i < carLot.size();i++) 
		{
			System.out.print(i + " ");
			carLot.get(i).showCarDetailsSelling();
		}
		boolean temp = true;
		double k = 0;
		System.out.println("Please input the number of a car you would like to make an offer on, or enter esc\nto return to the previous menu.");
		while(temp) 
		{
			String s = SYS.readLn.nextLine();
			switch(s)
			{
			case "esc":
				showSYSInter();
				break;
				default:
					try 
					{
						int num = Integer.parseInt(s);
						CarDB c = carLot.get(num);
						System.out.println("Please input the amount of dollars you would like to offer.");
						while(temp) 
						{
							s = SYS.readLn.nextLine();
							try 
							{
								k = Double.parseDouble(s);
								System.out.println(k);
								temp = false;
							}
							catch(Exception e)
							{
								System.out.println("Invalid input, please try again.");
							}
						}
						try(Connection con = ConnectionManager.getConnection())
						{
							String sql = "INSERT INTO offers(user_id, car_id, offer_value) VALUES (?,?,?)";
							PreparedStatement stmt = con.prepareStatement(sql);
							stmt.setInt(1, userId);
							stmt.setInt(2, c.getID());
							stmt.setDouble(3, k);
							stmt.executeUpdate();
							System.out.println("Offer made, please check back later to see if your bid was successful!");
						}
						
					}
					catch(Exception e) 
					{
						System.out.println("Invalid entry, please try again.");
					}
			}
		}
		//SYS.updateCarlot(carLot);
	}
	
	public void viewCarsOwned() throws ClassNotFoundException, IOException, SQLException
	{
		List<CarDB> owned = new ArrayList<>();
		try(Connection con = ConnectionManager.getConnection())
		{
			String sql = "SELECT car_id, make, car_model, selling_price, owed, pay_period FROM cars_paying WHERE user_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, userId);
			ResultSet results = stmt.executeQuery();
			while(results.next()) 
			{
				String make = results.getString("make");
				String model = results.getString("car_model");
				double owed = results.getDouble("owed");
				int period = results.getInt("pay_period");
				double initPrice = results.getDouble("selling_price");
				int carID = results.getInt("car_id");
				CarDB c = new CarDB(carID,make, model, initPrice);
				c.initDebt(period, owed);
				c.updateDebt();
				owned.add(c);
			}
			for(CarDB c:owned) 
			{
				if(c.checkIfNew()) 
				{
					SYS.initilizeDebt(c);
					c.updateDebt();
				}
			}
		}
		int i = 0;
		if(owned.isEmpty()) 
		{
			System.out.println("Currently you own no vehicles. Please use the Show Lot option in the main menu\n to make offers on cars!");
			showSYSInter();
			return;
		}
		for(CarDB c:owned) 
		{
			System.out.print(i + " ");
			c.showCarDetails();
			i++;
		}
			System.out.println("Would you like to make a payment? (y/n)");
			boolean temp = true;
			while(temp) 
			{
				String s = SYS.readLn.nextLine();
				switch(s) 
				{
				case "y":
						System.out.println("Please enter the number of the car you would like to make a payment on.");
						while(temp) 
						{
							s = SYS.readLn.nextLine();
							try(Connection con = ConnectionManager.getConnection()) 
							{
								i = Integer.parseInt(s);
								CarDB j = owned.get(i);
								System.out.println("Please enter payment amount, or leave it blank to pay your normal monthly payment.");
								while(temp)
								{
									s = SYS.readLn.nextLine();
									switch (s) {
									case "":
										j.makePayment();
										makePayment(j);
										temp = false;
										break;

									default:
										try 
										{
											double d = Double.parseDouble(s);
											j.makePayment(d);
											System.out.println("You have paid: " + d +".");
											System.out.println("You currently have a balance of " + j.getDebt());
											makePayment(j);
											temp = false;
										}
										catch(Exception e)
										{
											e.printStackTrace();
											System.out.println("Invalid input, please try again");
										}
										break;
									}
								}

							}
							catch(Exception e) 
							{
								e.printStackTrace();
								System.out.println("Invalid input, please try again.");
							}
						}
					break;
				case "n":
					temp = false;
					break;
				default:
					System.out.println("Invalid entry, please try again.");
					break;
				}
			}
			//SYS.updateUserCars(userCars);
			showSYSInter();
		}
	
	public void makePayment(CarDB j) throws SQLException 
	{
		try(Connection con = ConnectionManager.getConnection())
		{
			String sql = "UPDATE cars_paying SET owed = ?, pay_period = ? WHERE car_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setDouble(1, j.getDebt());
			stmt.setInt(2, j.getMRemaining());
			//stmt.setDate(3, (java.sql.Date) new Date());
			stmt.setInt(3, j.getID());
			stmt.executeUpdate();
		}	
	}
	
	@Override
	public void showSYSInter() 
	{
		System.out.println("1: View Car Lot");
		System.out.println("2: View Cars Owned");
		String s = SYS.readLn.nextLine();
		switch(s) 
		{
		case "1":
		try {
			viewCarlot();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
		case "2":
			try {
				viewCarsOwned();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		break;
		default:
			showSYSInter();
			break;
		}
	}
}
