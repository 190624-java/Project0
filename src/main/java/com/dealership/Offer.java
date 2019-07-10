package com.dealership;

import java.io.Serializable;

public class Offer implements Serializable {
	
	public Customer customer;
	public Car car;
	public double amount;
	
	
	public Offer(Customer customer, Car car, double amount) {
		this.customer = customer;
		this.car = car;
		this.amount = amount;
	}
	
	public Offer(String custId, int carId, double amount) {
		this.customer = new Customer("","",custId);
		this.car = new Car("","", 0, "", 0, carId);
		this.amount = amount;
	}


	@Override
	public String toString() {
		return "Offer [customer=" + customer + ", car=" + car + ", amount=" + amount + "]";
	}
	
	

}
