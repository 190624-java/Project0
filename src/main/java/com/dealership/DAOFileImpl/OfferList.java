package com.dealership.DAOFileImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;

import com.dealership.Car;
import com.dealership.Customer;
import com.dealership.Loan;
import com.dealership.Offer;

public class OfferList {
	private static OfferList instance; 
	private static final String OFFER_FILE = "Offer_Data.txt";
	public HashSet<Offer> offers;
	
	private OfferList()
	{
		//TODO attempt to read offers from file
		offers = new HashSet<Offer>();
	}
	
	public static OfferList getInstance()
	{
		if(instance == null)
		{
			instance = new OfferList();
			try {
				instance.read();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return instance;
	}

	public Offer getOfferByInfo(Customer customer, Car car, double amount)
	{
		for(Offer offer: offers) {
			if(offer.customer == customer && offer.car == car && offer.amount == amount)
				return offer;
		}
		return null;
	}
	
	public void acceptOffer(Offer offer) {
		double principal = offer.car.getPrice() - offer.amount;
		Loan loan = new Loan(principal, offer.customer);
		offer.customer.loans.add(loan);
		LoanList.getInstance().loans.add(loan);
		
		offer.car.setOwner(offer.customer);
		offer.customer.ownedCars.add(offer.car);
		offer.car.setSold(true);
		
		for(Offer carsOffer : offer.car.offers) {
			rejectOffer(carsOffer);
		}
	}
	
	public void rejectOffer(Offer offer) {
		offer.car.offers.remove(offer);
		offer.customer.offers.remove(offer);
		offers.remove(offer);
	}
	
	// TODO allow a sorted collection type to be used for convenience of employees viewing offers.
	// Include comparator for Offer
	public void displayAllOffers() {
		for(Offer offer : offers) {
			System.out.println(offer.toString());
		}
	}
	
	public void write() {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(OFFER_FILE)))) {
			for(Offer offer : offers)
			{
				oos.writeObject(offer);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void read() {
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(OFFER_FILE)))) {
			while(true) {
				Offer obj = (Offer)ois.readObject();
				offers.add(obj);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
