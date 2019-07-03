package Proj0;

import java.util.HashMap;

public class Car {

	public String name;
	public String color;
	public String ID;
	public int year;
	public double flatPrice;
	public double customerOfferAmt;
	private HashMap<String, Integer> currentOffers;
	public boolean hasOffer;

	public Car(String name, String color, String iD, int year, double flatPrice, boolean hasOffer) {
		super();
		this.name = name;
		this.color = color;
		ID = iD;
		this.year = year;
		this.flatPrice = flatPrice;
		this.hasOffer = hasOffer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getFlatPrice() {
		return flatPrice;
	}

	public void setFlatPrice(double flatPrice) {
		this.flatPrice = flatPrice;
	}

	public double getCustomerOfferAmt() {
		return customerOfferAmt;
	}

	public void setCustomerOfferAmt(double customerOfferAmt) {
		this.customerOfferAmt = customerOfferAmt;
	}

	public HashMap<String, Integer> getCurrentOffers() {
		return currentOffers;
	}

	public void setCurrentOffers(HashMap<String, Integer> currentOffers) {
		this.currentOffers = currentOffers;
	}

	public boolean isHasOffer() {
		return hasOffer;
	}

	public void setHasOffer(boolean hasOffer) {
		this.hasOffer = hasOffer;
	}
}