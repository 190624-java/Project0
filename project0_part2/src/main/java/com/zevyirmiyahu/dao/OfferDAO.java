package com.zevyirmiyahu.dao;

public interface OfferDAO {
	
	public static enum OFFERSTATUS {
		ACCEPTED, 
		DECLINED, 
		PENDING
	};
	public String getCustomerUserName();
	public byte getCarId();
	public int getOfferAmount();
	public OFFERSTATUS getOfferStatus();
	public void setOfferStatus(String offerStatus, String userName, byte carId);
}
