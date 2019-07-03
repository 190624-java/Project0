package com.revature.project0;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Employee extends User{
	private int check;//1 for customer // 2 for employee // 3 for fail case
	private String fname, lname;
	private String username;
	private String password;
	
	public Employee(int check, String fname, String lname, String username, String password) {
		super(check, fname, lname, username, password);
		this.check = check;
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.password = password;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4197176263679295292L;

	public void addCar(SystemConsole sc)  {
		System.out.println("Please enter the brand name;");
		String a=sc.scanner.next();
		System.out.println("Enter the model name;");
		String b=sc.scanner.next();
		System.out.println("Enter model year;");
		int c=sc.scanner.nextInt();
		System.out.println("Enter starting offer;");
		double d=sc.scanner.nextDouble();
		
		
		try {
			CarPersistence.addNewCar(new Car(a,"DLR",b,c,d));
			//sc.forward();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void acceptOffer(SystemConsole sc)  {
		int a=sc.offerChoice();//is one more than the index
		a=a-1;
		ArrayList<Offer> ofList=sc.offerList;
		Offer accept = sc.offerList.remove(a);
		sc.resetOffer(accept);
		//Offer accept = ofList.get(a);
		/*
		accept.setAccepted(true);
		System.out.println("You just accepted this offer:"+accept.toString());
		
		ArrayList<Offer> listReplace=new ArrayList<>();
		for(Offer o: ofList) {
			if(!o.getCar().equals(accept.getCar())) {
				listReplace.add(o);
			}
		}
		sc.offerList=listReplace;
		
		ArrayList<Car> carReplace=new ArrayList<>();
		for(Car c: sc.carList) {
			if(!c.equals(accept.getCar())) {
				carReplace.add(c);
			}
			else {
				carReplace.add(new Car(c.getBrand(),accept.getCustomer().getUsername(),c.getName(),c.getYear(),accept.getAmt()));
			}
		}
		sc.carList=carReplace;
		sc.dataSetup();
		
		*/
	}
	public void rejectOffer(SystemConsole sc) {
		int a=sc.offerChoice();//is one more than the index
		a=a-1;
		Offer accept =sc.offerList.get(a);
		try {
			OfferPersistence.removeOffer(a);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("You just deleted this offer:\n"+accept.toString());
		
		//sc.offerList.remove(a-1);
		
		
	}
	
	public void removeCar(SystemConsole sc) {
		sc.displayCarLot();
		int a=sc.scanner.nextInt();
		ArrayList<Car> carL = sc.carLot();
		System.out.println(carL.toString());
		a=a-1;
		//Car c=carL.get(a);
		//System.out.println(c.toString());
		try {
			CarPersistence.removeCar(a);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("You just deleted that");
	}
	
	public void viewAllPayments(SystemConsole sc) {
		for(Car c : sc.carLot()) {
			if(!c.getOwner().equals("DLR")) {
				System.out.println(c.toString()+ " "+sc.monthlyPayment(c));
			}
		}
		System.out.println("____________________________________________________________");
	}
	
	
}
