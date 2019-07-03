package com.revature.phudspeth.project0pt1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
	
	public void runFirstScreen() 
	{
		System.out.println("1: Register");
		System.out.println("2: Login");
		int nav = Integer.parseInt(readLn.nextLine());
		
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
	
	void reg() 
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
		
		Registrar register = new Registrar();
		register.register(user, pass);
	}
	
	void login() 
	{
		System.out.println("Please input username:");
		String user = readLn.nextLine();
		System.out.println("Please input password:");
		String pass = readLn.nextLine();
		Registrar login = new Registrar();
		login.login(user, pass);
		for(User u:userList) 
		{
			if(u.getID() == (user + "." + pass).hashCode()) 
			{
				curUser = u;
				System.out.println(curUser.getID());
				curUser.showSYSInter();
			}
		}
	}
	public void addUser(String s, int h) throws FileNotFoundException, IOException 
	{
		if(s.equals("Customer")) 
		{
			userList.add(new Customer(h));
		}
		else if(s.equals("Employee")) 
		{
			userList.add(new Employee(h));
		}
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("userb.ser"))))
		{
			oos.writeObject(userList);
		}
	}
	
	public void initilizeDebt(Car c) throws ClassNotFoundException, IOException
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
			String choice = readLn.nextLine();
			switch (choice) 
			{
			case "1":
				c.initDebt(36, c.getDebt());
				calculatePayments(c);
				deciding = false;
				break;
			case "2":
				c.initDebt(72, c.getDebt());
				calculatePayments(c);
				deciding = false;
				break;
			case "3":
				c.initDebt(120, c.getDebt());
				calculatePayments(c);
				deciding = false;
				break;
			default:
				System.out.println("Invalid selection, please try again.");
				break;
			}
		}
	}
	
	public void calculatePayments(Car c) 
	{
		c.updateDebt();
		System.out.println("Current monthly payments are: " + c.showMonthly());
	}

	
	public void updateCarlot(Car c) throws FileNotFoundException, IOException, ClassNotFoundException 
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
	
	public List<Car> getCarLot() throws IOException, ClassNotFoundException
	{
		List<Car> carLot = null;
		
		try (ObjectInputStream str = new ObjectInputStream(new FileInputStream(new File("clotreg.ser")))) 
		{
			carLot = (List<Car>) str.readObject();
		} 
		catch (FileNotFoundException e) 
		{
			return new ArrayList<>();
		}
		return carLot;
	}
	
	public void updateUserCars(List<Car> c) throws FileNotFoundException, IOException 
	{
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("userp.ser"))))
		{
			oos.writeObject(c);
		}
	}
	
	public void updateUserCars(Car c) throws FileNotFoundException, IOException, ClassNotFoundException 
	{
		List<Car> userCars = getUserCars();
		
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
	
	public List<Car> getUserCars() throws IOException, ClassNotFoundException
	{
		List<Car> userCars = null;
		
		try (ObjectInputStream str = new ObjectInputStream(new FileInputStream(new File("userp.ser")))) 
		{
			userCars = (List<Car>) str.readObject();
		} 
		catch (FileNotFoundException e) 
		{
			return new ArrayList<>();
		}
		return userCars;
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
