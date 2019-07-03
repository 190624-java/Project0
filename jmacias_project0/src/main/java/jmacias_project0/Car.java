package jmacias_project0;

import java.io.Serializable;

public class Car implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8077090874605331877L;
	
	public static int count = 0;
	public int number;
	public String MakeAndModel;
	public int price;
	public List<User> offers;
	public Car(String makeAndModel, int price) {
		super();
		count++;
		this.number = count;
		MakeAndModel = makeAndModel;
		this.price = price;
	}
	
	

}
