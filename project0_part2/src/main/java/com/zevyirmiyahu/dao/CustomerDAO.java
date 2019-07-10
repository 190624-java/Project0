package com.zevyirmiyahu.dao;

public interface CustomerDAO extends UserDAO{
	
	public boolean login(String userName, short loginPin);	
	public void offerStatus(String userName);
	public void makeOffer(String userName, int offerAmount, byte carId);
	public boolean duplicateOffer(String userName, byte carId); // ensures no duplicate offers on a pending offer
	public void viewCarsOwned(String userName);
	public void viewRemainingPayments(String userName);
}
