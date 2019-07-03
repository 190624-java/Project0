package com.revature.phudspeth.project0pt1;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Customer extends User implements UserInterface, Serializable
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
	
	public Customer(int h) 
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
	public void viewCarlot() throws ClassNotFoundException, IOException 
	{
		List<Car> carLot = SYS.getCarLot();
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
						Car c = carLot.get(num);
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
						c.offer(userId, k);
						
					}
					catch(Exception e) 
					{
						System.out.println("Invalid entry, please try again.");
					}
			}
		}
		SYS.updateCarlot(carLot);
	}
	
	public void viewCarsOwned() throws ClassNotFoundException, IOException
	{
		List<Car> userCars = SYS.getUserCars();
		List<Car> owned = new ArrayList<Car>();
		for(Car c:userCars) 
		{
			if(userId == c.getOwner()) 
			{
				owned.add(c);
			}
		}
		for(Car c:owned) 
		{
			userCars.remove(c);
		}
		for(Car c:owned) 
		{
			if(c.checkIfNew()) 
			{
				SYS.initilizeDebt(c);
			}
		}
		int i = 0;
		if(owned.isEmpty()) 
		{
			System.out.println("Currently you own no vehicles. Please use the Show Lot option in the main menu\n to make offers on cars!");
			showSYSInter();
			return;
		}
		for(Car c:owned) 
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
							try 
							{
								i = Integer.parseInt(s);
								Car j = owned.get(i);
								System.out.println("Please enter payment amount, or leave it blank to pay your normal monthly payment.");
								while(temp)
								{
									s = SYS.readLn.nextLine();
									switch (s) {
									case "":
										j.makePayment();
										temp = false;
										break;

									default:
										try 
										{
											double d = Double.parseDouble(s);
											j.makePayment(d);
											System.out.println("You have paid: " + d +".");
											System.out.println("You currently have a balance of " + j.getDebt());
											temp = false;
										}
										catch(Exception e)
										{
											System.out.println("Invalid input, please try again");
										}
										break;
									}
								}
							}
							catch(Exception e) 
							{
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
			for(Car c: owned) 
			{
				userCars.add(c);
			}
			SYS.updateUserCars(userCars);
			showSYSInter();
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
		case "2":
			try {
				viewCarsOwned();
			} catch (ClassNotFoundException | IOException e) {
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
