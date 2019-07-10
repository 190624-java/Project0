package com.zevyirmiyahu.beans;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	
	private String userName;
	private short loginPin;
	private List<Offer> offers = new ArrayList<>();
	
	public Customer(String userName, short loginPin) {
		this.userName = userName;
		this.loginPin = loginPin;
	}
	
	public String getUserName() {
		return userName;
	}

	public short getLoginPin() {
		return loginPin;
	}
	
	public List<Offer> getOffers() {
		return offers;
	}
	
	public void addOffer(Offer offer) {
		offers.add(offer);
	}
}
