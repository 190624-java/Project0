package basicpackage;

import java.io.Serializable;

public class Offer implements Serializable{
private static final long serialVersionUID = 3570348503243L;
	public double offerValue;
	public User offerer;
	Offer(double o, User u)
	{
		super();
		this.offerValue = o;
		this.offerer = u;
	}
}
