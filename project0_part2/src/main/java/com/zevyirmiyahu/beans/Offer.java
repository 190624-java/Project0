package com.zevyirmiyahu.beans;

public class Offer {
	private int offerAmount;
	private String customerUserName;
	private byte carId;

	public static enum OFFERSTATUS {
		ACCEPTED, 
		DECLINED, 
		PENDING
	};

	private OFFERSTATUS offerStatus = null;

	public Offer(String customerUserName, int offerAmount, byte carId) {
		this.customerUserName = customerUserName;
		this.offerAmount = offerAmount;
		this.carId = carId;
		offerStatus = OFFERSTATUS.PENDING; // default for all new offers
	}

	public String getCustomerUserName() {
		return customerUserName;
	}

	public byte getCarId() {
		return carId;
	}

	public int getOfferAmount() {
		return offerAmount;
	}

	public OFFERSTATUS getOfferStatus() {
		return offerStatus;
	}

	public void setOfferStatus(OFFERSTATUS offerStatus) {
		this.offerStatus = offerStatus;
	}
}
