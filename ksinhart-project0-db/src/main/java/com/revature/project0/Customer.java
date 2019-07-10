package com.revature.project0;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class Customer extends User implements Serializable{
	
	private int check;//1 for customer // 2 for employee // 3 for fail case
	private String fname, lname;
	private String username;
	private String password;

	public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}
	
	
	public Customer() {
		this.check=1;
	}
	
	public Customer(int check, String fname, String lname, String username, String password) {
		super(check, fname, lname, username, password);
		this.check=check;
		this.fname = fname;
		this.lname = lname;
		if(!username.equals("DLR")) {
			this.username = username;
		}
		this.password = password;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = -2806762180776685828L;
	//customers will be stored in a set in SystemConsole 
	//to make sure that there are no repeated usernames
	
	
	public void viewLot(SystemConsole sc) {
		sc.carList = (ArrayList<Car>) CarPersistence.getAvailableCars();
		if(sc.carList.isEmpty()) {
			System.out.println("There are no cars");
		}
		else {
			for(Car c:sc.carList) {
				System.out.println(c.toString());
			}
		}
	}
	
	public void offer(SystemConsole sc) {
		int a= sc.displayPurchasable();
		if(a==-1) {
			return;
		}
		else if(a==0) {
			return;
		}
		else {
			a=a-1;
			Car c = sc.carList.get(a);
			double d;
			System.out.println("How much do you want to offer?");
			do {
				System.out.println("Needs to be higher than: "+c.getPrice());
				d=sc.scanner.nextDouble();
			}while(d<c.getPrice());
			Offer o = new Offer(this.username,c.getLicense(),d);
			OfferPersistence.addNewOfferDB(o);
		}
		/*
		sc.displayPurchasable();
		int a = sc.scanner.nextInt();
		a=a-1;
		Car c = sc.carList.get(a);
		System.out.println("How much do you want to offer?");
		double d = sc.scanner.nextDouble();
		try {
			//CarPersistence.addNewCar(new Car(a,"DLR",b,c,d));
			if(d>c.getPrice()) {
				OfferPersistence.addNewOffer(new Offer(this,c,d));
			}
			else {
				System.out.println("Sorry you did not offer enough to place a bid");
			}
			//sc.forward();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
	public ArrayList<Car> getOwned(SystemConsole sc) {
		return (ArrayList<Car>) CarPersistence.getOwnedCars(this.username);
		/*
		ArrayList<Car> ownedList = new ArrayList<>();
		for(Car c:sc.carList) {
			if(c.getOwner().equals(this.username)) {
				ownedList.add(c);
			}
		}
		return ownedList;
		*/
	}
	public void viewOwned(SystemConsole sc) {
		sc.carList = (ArrayList<Car>) CarPersistence.getOwnedCars(this.username);
		if(sc.carList.isEmpty()) {
			System.out.println("You own no cars");
		}
		else {
			System.out.println("These are your owned cars");
			for(Car c: sc.carList) {
				System.out.println(c.toString());
			}
		}
	}
	public void viewMyPayments(SystemConsole sc) {
		ArrayList<Car> ownedList=getOwned(sc);
		for(Car c:ownedList) {
			System.out.println(c.toString() + " monthly payment of: $"+ sc.monthlyPayment(c));
		}
		
	}
}
