package com.revature.beans.things;

import java.util.Iterator;
import java.util.LinkedHashSet;

import com.revature.services.OffersMngr;
import com.revature.things.logins.Account;
import com.revature.utilities.DSystem;
import com.revature.utilities.UIUtil;

public class Car {

	//Industrial/Government
	//---------------------
	private CarRegistration reg;
	
	//Common Buyer Description
	//------------------------	
	//private String color;	
	
	//Containers
	//----------
	private Contract contract;
	private LinkedHashSet<Offer> offers;
	
	
	/**
	 * To verify that the car belongs to the correct user when using the system
	 * static function to calculate monthly payments.
	 */
	private Account owner;
	
	
	public Car(CarRegistration reg, Account owner){
		//if(reg==null) new Registration(); //empty due
		this.reg = reg;
		this.setOwner(owner);
		//this.color = color;
		
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
	
	public void setContract(Contract c) {
		this.contract = c;
	}
	/**
	 * Method1: Return iterator and compare each offer
	 * Method2: Store accepted offer into new offer, delete the list, assign new list.
	 * @return
	 */
//	public Iterator<Offer> getOffersIterator(DSystem s) {
//		return offers.iterator();
//	}

	public OffersMngr getOffersMngr() {
		return DSystem.getInstance().getOffersManager();
	}
	
	public LinkedHashSet<Offer> getOffers(){
		return this.offers;
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
	
	/**
	 * Needed for a test in searching for empty spaces on lots.
	 * @return
	 */
	public boolean hasRegistration() {
		if (this.reg == null) return false;
		else return true;
	}
	
	public long getRegID() {
		return this.reg.getREG_ID();
	}

	public Account getOwner() {
		return this.owner;
	}

	public void setOwner(Account owner) {
		this.owner = owner;
	}

	public Contract getContract() {
		return contract;
	}

	/**
	 * Called by the Employee who accepts an offer.
	 * Employee passes that accepted offer to this function.
	 * @param acceptedOffer
	 */
	public void makeContract(Offer acceptedOffer) {
		if(this.contract!=null)
			UIUtil.echoProblem("Warning: contract already exists for this car");		
			//throw new Exception();
		this.contract = new Contract(acceptedOffer);

	}

	public Iterator<Offer> getOffersHSetIterator() {		
		return offers.iterator();
	}




//	public String getColor() {
//		// TODO Auto-generated method stub
//		return this.color;
//	}
}
