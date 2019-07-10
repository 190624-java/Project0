package beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dbControllers.DatabaseUtils;

public class Car implements Serializable {
	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = 3987990158017823409L;
	
	public int number; // TODO this is the id, will go through and change in all classes later
	public String makeAndModel;
	public int price;
	public List<String> offers;
	public Car(String makeAndModel, int price) throws IOException {
		super();
		int count = DatabaseUtils.getMaxCarId();
		count++;
		this.number = count;
		this.makeAndModel = makeAndModel;
		this.price = price;
		this.offers = new ArrayList<String>();
	}
	public Car(String makeAndModel, int price, int number) {
		super();
		this.number = number;
		this.makeAndModel = makeAndModel;
		this.price = price;
		this.offers = new ArrayList<String>(); // TODO  populate this with offers from database after method is added in databaseutils
	}
	public int getNumber() {
		return this.number;
	}
	public String getMakeAndModel() {
		return this.makeAndModel;
	}
	public int getPrice() {
		return this.price;
	}
	

}
