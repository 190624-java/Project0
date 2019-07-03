package com.revature.things;

import java.util.Iterator;
import java.util.LinkedList;

import com.revature.collections.Contract;
import com.revature.collections.Offers;
import com.revature.parties.DSystem;
import com.revature.parties.User;

public class Car {

	//Industrial/Government
	//---------------------
	private CarRegistration reg;
	
	//Common Buyer Description
	//------------------------	
	//private String color;	
	
	//Containers
	//----------
	private Offers offers;
	
	
	/**
	 * To verify that the car belongs to the correct user when using the system
	 * static function to calculate monthly payments.
	 */
	private User owner;
	
	public Car(CarRegistration reg, User owner){
		//if(reg==null) new Registration(); //empty due
		this.reg = reg;
		this.owner = owner;
		//this.color = color;
		this.offers = new Offers();
		//contract is not created till a sale is made; it needs to be null
		// till then to know the state of the car ownership
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
//	public Iterator<Offer> getOffersIterator(DSystem s) {
//		return offers.iterator();
//	}

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

//	public String getColor() {
//		// TODO Auto-generated method stub
//		return this.color;
//	}
}
