package com.revature.things;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import com.revature.collections.ContractMngr;
import com.revature.collections.OffersMngr;
import com.revature.parties.User;
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
	private OffersMngr offersMngr;
	private ContractMngr contract;
	
	
	/**
	 * To verify that the car belongs to the correct user when using the system
	 * static function to calculate monthly payments.
	 */
	private User owner;
	
	
	public Car(CarRegistration reg, User owner){
		//if(reg==null) new Registration(); //empty due
		this.reg = reg;
		this.setOwner(owner);
		//this.color = color;
		this.addOffer(new OffersMngr());
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

	public OffersMngr getOffersMngr() {
		return offersMngr;
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

	public User getOwner() {
		return this.owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public ContractMngr getContract() {
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
		this.contract = new ContractMngr(acceptedOffer);

	}

	public Iterator<Offer> getOffersHSetIterator() {		
		return offersMngr.getOffersHSet().iterator();
	}




//	public String getColor() {
//		// TODO Auto-generated method stub
//		return this.color;
//	}
}
