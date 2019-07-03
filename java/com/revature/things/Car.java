package com.revature.things;

import java.util.Iterator;
import java.util.LinkedList;

import com.revature.parties.DSystem;
import com.revature.parties.User;

public class Car {

	//Industrial/Government
	//---------------------
	private CarRegistration reg;
	
	//Common Buyer Description
	//------------------------	
	private String color;	
	
	//Containers
	//----------
	private LinkedList<Offer> offers;
	
	/**
	 * To verify that the car belongs to the correct user when using the system
	 * static function to calculate monthly payments.
	 */
	private User owner;
	
	public Car(CarRegistration reg, String color){
		//if(reg==null) new Registration(); //empty due
		this.reg = reg;
		this.color = color;
		
		this.offers = new LinkedList();
	}
	
	/**
	 * For viewing the Manufacture's recommended retail price
	 * @return
	 */
	public float getMSRP() {
		return this.reg.getMSRP();
	}
	
	/**
	 * Method1: Return iterator and compare each offer
	 * Method2: Store accepted offer into new offer, delete the list, assign new list.
	 * @return
	 */
	public Iterator<Offer> getOffersIterator(DSystem s) {
		return offers.iterator();
	}

	public String getMake() {
		// TODO Auto-generated method stub
		return this.reg.getMAKE();
	}

	public String getModel() {
		// TODO Auto-generated method stub
		return this.reg.getMODEL();
	}

	public int getYear() {
		// TODO Auto-generated method stub
		return this.reg.getYEAR();
	}

	public String getColor() {
		// TODO Auto-generated method stub
		return this.color;
	}
}
