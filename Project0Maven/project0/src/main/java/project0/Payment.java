package project0;

import java.io.Serializable;

public class Payment implements Serializable{
private static final long serialVersionUID = 8345394853495L;
	User owner;
	String paymentOwner;
	String carPayment;
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
	Payment(String owner, String car, double amount)
	{
		super();
		this.paymentOwner = owner;
		this.carPayment = car;
		this.amount = amount;
	}
}
