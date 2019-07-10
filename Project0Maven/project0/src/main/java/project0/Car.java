package project0;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Car implements Serializable{

	private static final long serialVersionUID = 738479298793749324L;
	double price;
	String make;
	String model;
	String color;
	String carOwner;
	int id;
	int year;
	boolean lotOrNot;
	User owner;
	List<Offer> offerList = new LinkedList<Offer>();
	public Car()
	{
		
	}
	public Car(String ma, String mo, double p, String owner)
	{
		super();
		this.make = ma;
		this.model = mo;
		this.price = p;
		this.carOwner = owner;
	}
	public Car(int id, String ma, String mo, double p, String owner)
	{
		super();
		this.id = id;
		this.make = ma;
		this.model = mo;
		this.price = p;
		this.carOwner = owner;
	}
	public Car(double p, String ma, String mo, boolean lON, User u)
	{
		super();
		this.price = p;
		this.make = ma;
		this.model = mo;
		this.lotOrNot = lON;
		if(lON)
		{
			this.owner = null;
		}
		else
		{
			this.owner = u;
		}
	}
	public void addOffer(double price, User offerer)
	{
		this.offerList.add(new Offer(price, offerer));
	}
}
