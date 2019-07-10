package com.dealership;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Hashtable;

public class Car implements Serializable {
	private String make;
	private String model;
	private int year;
	private String color;
	private double price;
	private int paymentsRemaining;
	private boolean sold = false;
	private Customer owner;
	private int id;
	public HashSet<Offer> offers;
	
	
	public Car(String make, String model, int year, String color, double price)
	{
		this.make = make;
		this.model = model;
		this.year = year;
		this.color = color;
		this.price = price;
		offers = new HashSet<Offer>();
		generateId();
	}
	
	public Car(String make, String model, int year, String color, double price, int id)
	{
		this.make = make;
		this.model = model;
		this.year = year;
		this.color = color;
		this.price = price;
		offers = new HashSet<Offer>();
		this.id = id;
	}
	
	//TODO write a trigger in the database that makes this obsolete
	private void generateId() {
		int tmp = 0;
		HashSet<Integer> idList = DealershipDriver.carAccessor.getAllIds();
	}
	
	@Override
	public String toString() {
		return "Car [make=" + make + ", model=" + model + ", year=" + year + ", color=" + color + ", price=" + price
				+ ", paymentsRemaining=" + paymentsRemaining + ", sold=" + sold + ", owner=" + owner + ", offers="
				+ offers + "]";
	}



	public String getCarkey() {
		return make + " " + model + " " + year;
	}
	
	
	/*
	 * Getters
	 */
	public String getMake() {
		return make;
	}
	public String getModel() {
		return model;
	}
	public int getYear() {
		return year;
	}
	public String getColor() {
		return color;
	}
	public double getPrice() {
		return price;
	}
	public boolean getSold() {
		return sold;
	}
	public Customer getOwner() {
		return owner;
	}
	
	public int getId() {
		return this.id;
	}
	/*
	 * Setters
	 */
	public void setMake(String make) {
		this.make = make;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setSold(boolean sold) {
		this.sold = sold;
	}
	public void setOwner(Customer owner) {
		this.owner = owner;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
