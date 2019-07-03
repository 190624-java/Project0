package basicpackage;

import java.io.Serializable;

public class Payment implements Serializable{
private static final long serialVersionUID = 8345394853495L;
	User owner;
	Car car;
	double amount;
	int period;
	int paymentsRemaining;
	Payment(User u, Car c, double a, int p)
	{
		super();
		this.owner = u;
		this.car = c;
		this.amount = a;
		this.paymentsRemaining = p;
	}
}
