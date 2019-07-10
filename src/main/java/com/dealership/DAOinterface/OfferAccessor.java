package com.dealership.DAOinterface;

import java.util.HashSet;

import com.dealership.Car;
import com.dealership.Customer;
import com.dealership.Offer;

//TODO setup
public interface OfferAccessor {

	public Offer getOfferByInfo(Customer customer, Car car, double amount);
	public HashSet<Offer> getOfferByCustomer(Customer customer);
	public void displayOfferesByCustomer(String customerId);
	public void acceptOffer(Offer offer);
	public void rejectOffer(Offer offer);
	public HashSet<Offer> getOffers();
	public void addOffer(Offer offer);
	public void displayAllOffers();
	public Offer getOfferByCustCar(String custId, int carId);
}
