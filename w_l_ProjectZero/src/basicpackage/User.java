package basicpackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class User implements Serializable {
	private static final long serialVersionUID = 7580202342359340L;
	String username;
	String password;
	String userType;
	public static List<Car> carArray = new ArrayList<Car>();
	public static List<Payment> userPayments = new LinkedList<Payment>();
	public User() {

	}

	public User(String name, String pass, String type) {
		super();
		this.username = name;
		this.password = pass;
		this.userType = type;
	}

	public List<Car> getArray() {
		return carArray;
	}

	public void giveArray(List<Car> carList) {
		carArray = carList;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getUserType() {
		return userType;
	}

	public void makeOffer(Car car) {
		if (this.getUserType().equals("employee")) {
			System.out.println("You do not have permissions to perform this action");
		} else {
			car.offerList.add(new Offer(200.00, this));
		}
	}

	public void viewOffers(Car car) {
		if (this.getUserType().equals("employee")) {
			if (car.offerList.isEmpty()) {
				System.out.println("No offers available for this car.");
			} else {
				for (Offer o : car.offerList) {
					if (o == null) {
						System.out.println("No available offers for this car.");
					}
					System.out.print(car.offerList.indexOf(o) + " " + o.offerValue + " " + o.offerer.getUsername() + " ");
				}
			}
		} else {
			System.out.println("You do not have permissions to perform this action");
		}
	}
	public void viewPayments()
	{
		for(Payment p : userPayments)
		{
			System.out.println(p.car.make + " " + p.car.model + " " + p.amount + " " + p.paymentsRemaining);
		}
	}
	public void viewAllPayments()
	{
		for(Payment p : userPayments)
		{
			System.out.println(p.owner.username + " " + p.car.make + " " + p.car.model + " " + p.amount + " " + p.paymentsRemaining);
		}
	}
}
