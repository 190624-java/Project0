package jmacias_project0;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Car implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8077090874605331877L;
	
	public static int count = 0;
	public int number;
	public String makeAndModel;
	public int price;
	public List<String> offers;
	public Car(String makeAndModel, int price) {
		super();
		count++;
		this.number = count;
		this.makeAndModel = makeAndModel;
		this.price = price;
		this.offers = new ArrayList<String>();
	}
	
	

}
