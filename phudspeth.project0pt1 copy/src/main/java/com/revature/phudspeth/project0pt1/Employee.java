package com.revature.phudspeth.project0pt1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Employee extends User implements UserInterface, Serializable
{
 	private static final long serialVersionUID = 6931876977325542755L;
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
	
	public Employee(int h) 
	{
		this.userId = h;
	}

	public void addCar() throws FileNotFoundException, IOException, ClassNotFoundException 
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
		SYS.updateCarlot(new Car(make, model, initCost));
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
	
	public void removeCar() throws FileNotFoundException, IOException, ClassNotFoundException 
	{
		List<Car> carLot = SYS.getCarLot();
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
							carLot.remove(i);
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
					System.out.println("Updating lot...");
					SYS.updateCarlot(carLot);
					System.out.println("Lot Updated.");
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
	
	public void offerCar() throws ClassNotFoundException, IOException 
	{
		List<Car> carLot = SYS.getCarLot();
		for(int i = 0; i<carLot.size();i++) 
		{
			System.out.print(i + " ");
			displayCarInfo(carLot.get(i));
		}
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
					Car c = carLot.get(g);
					Map<Integer, Double> offers = carLot.get(g).getOffers();
					if(offers.isEmpty()) 
					{
						System.out.println("No offers have been made on this car yet.");
						showSYSInter();
					}
					for (Entry<Integer, Double> entry : offers.entrySet())
					{
						System.out.println("UserID: " + entry.getKey() + " has made an offer of " + entry.getValue());
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
											c.sellCar(g, offers.get(g));
											SYS.updateUserCars(c);
											temp = false;
										}
										else if(s.equals("reject")) 
										{
											offers.remove(g);
											c.offer(offers);
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
							System.out.println("Invalid input, please try again.");
						}
					}
				}
				catch(Exception e)
				{
					System.out.println("Invalid input, try again.");
				}
			}
		}
		SYS.updateCarlot(carLot);
		showSYSInter();
	}
	
	public void checkUserCar() throws ClassNotFoundException, IOException 
	{
		List<Car> userCars = SYS.getUserCars();
		for(Car c: userCars) 
		{
			System.out.println("Account #:" + c.getOwner());
			c.showCarDetails();
		}
		showSYSInter();
	}
	
	void displayCarInfo(Car c) 
	{
		System.out.print(c.make + " " + c.model + " " + c.purchaseCost+ "\n");
	}
	
	@Override
	public void showSYSInter() 
	{
		SYS = AppSystem.getInstance();
		System.out.println("1: Add Car");
		System.out.println("2: Remove Car");
		System.out.println("3: View Car Offers");
		System.out.println("4: View User and Payment Information");
		boolean choice = true;
		while(choice) 
		{
			String s = SYS.readLn.nextLine();
			switch(s) 
			{
			case"1":
				try {
					addCar();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case"2":
				try {
					removeCar();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case"3":
				try {
					offerCar();
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			case"4":
				try {
					checkUserCar();
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			default:
				System.out.println("Invalid input, try again.");
				break;
			}
		}
		
	}

}
