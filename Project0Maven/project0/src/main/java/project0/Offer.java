package project0;

import java.io.Serializable;

public class Offer implements Serializable{
private static final long serialVersionUID = 3570348503243L;
	public double offerValue;
	public User offerer;
	public String offeree;
	Offer(double o, User u)
	{
		super();
		this.offerValue = o;
		this.offerer = u;
	}
	Offer(double o, String offeree)
	{
		super();
		this.offerValue = o;
		this.offeree = offeree;
	}
}