package basicpackage;

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
	int year;
	boolean lotOrNot;
	User owner;
	List<Offer> offerList = new LinkedList<Offer>();
	public Car()
	{
		
	}
	public Car(double p, String ma, String mo, String c, int y, boolean lON, User u)
	{
		super();
		this.price = p;
		this.make = ma;
		this.model = mo;
		this.color = c;
		this.year = y;
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
