package project0.automobiles;

import java.io.Serializable;
import java.util.HashMap;

public class Car implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private byte carId; // used to identify car, particularly when making offer etc.
	private String brand; // car brand, Ford, Nissan, etc.
	private String model; // Mustang, etc.
	private String color; // black, red, etc.
	private short year; // year manufactured
	private int price; // selling price
	private int mileage; 
	private boolean hasOffer; // a customer offered 
	private int currOfferAmount; // current offer amount from a customer
	private HashMap<String, Integer> currentOffers; // all offers
	private double monthlyPayment;
	private int remainingPrice; // amount customer to owes on car
	
	public Car(byte carId, String brand, String model, String color, 
						short year, int price, int mileage) {
		this.carId = carId;
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.year = year;
		this.price = price;
		this.mileage = mileage;
		this.hasOffer = false;
	}

	public byte getId() {
		return carId;
	}
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public short getYear() {
		return year;
	}

	public void setYear(short year) {
		this.year = year;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getMileage() {
		return mileage;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public boolean isHasOffer() {
		return hasOffer;
	}

	public void setHasOffer(boolean hasOffer) {
		this.hasOffer = hasOffer;
	}

	public int getCurrOfferAmount() {
		return currOfferAmount;
	}

	public void setCurrOfferAmount(int currOfferAmount) {
		this.currOfferAmount = currOfferAmount;
	}

	public HashMap<String, Integer> getCurrentOffers() {
		return currentOffers;
	}

	public void setCurrentOffers(HashMap<String, Integer> currentOffers) {
		this.currentOffers = currentOffers;
	}

	public double getMonthlyPayment() {
		return monthlyPayment;
	}

	public void setMonthlyPayment(int monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}

	public int getRemainingPrice() {
		return remainingPrice;
	}

	public void setRemainingPrice(int remainingPrice) {
		this.remainingPrice = remainingPrice;
	}
}
