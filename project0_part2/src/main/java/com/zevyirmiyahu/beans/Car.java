package com.zevyirmiyahu.beans;

public class Car {

	private String userName = null; // owner of car
	private int carId; // used to identify car, particularly when making offer etc.
	private String brand; // car brand, Ford, Nissan, etc.
	private String model; // Mustang, etc.
	private String color; // black, red, etc.
	private short year; // year manufactured
	private int price; // selling price
	private int mileage;
	private boolean hasOffer; // a customer offered
	private int currOfferAmount; // current offer amount from a customer
	//private HashMap<String, Integer> currentOffers; // all offers
	private int remainingPrice; // amount customer to owes on car

	public Car(int carId, String brand, String model, String color, short year, int price, int mileage) {
		this.carId = carId;
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.year = year;
		this.price = price;
		this.mileage = mileage;
		this.hasOffer = false;
		this.currOfferAmount = 0;
		this.remainingPrice = price;
	}

	public String getUserName() {
		return userName;
	}
	
	public int getId() {
		return carId;
	}

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public String getColor() {
		return color;
	}

	public short getYear() {
		return year;
	}

	public int getPrice() {
		return price;
	}

	public int getMileage() {
		return mileage;
	}

	public boolean getHasOffer() {
		return hasOffer;
	}

	public int getCurrOfferAmount() {
		return currOfferAmount;
	}

	public int getRemainingPrice() {
		return remainingPrice;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setId(int carId) {
		this.carId = carId;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setYear(short year) {
		this.year = year;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

	public void setHasOffer(boolean hasOffer) {
		this.hasOffer = hasOffer;
	}

	public void setCurrOfferAmount(int currOfferAmount) {
		this.currOfferAmount = currOfferAmount;
	}

	public void setRemainingPrice(int remainingPrice) {
		this.remainingPrice = remainingPrice;
	}
}
