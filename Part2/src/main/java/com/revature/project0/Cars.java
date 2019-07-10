package com.revature.project0;

import java.io.Serializable;

public class Cars implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1962371405172459223L;
	int serialNumber;
	double price;
	String make;
	String model;
	int num_payments;
	double payments;
	int ownerId;
	
	public Cars(int id, double price, String make,
			String model, int num_payments, double monthlyPayments, int owner_id) {
		this.serialNumber = id;
		this.price = price;
		this.make = make;
		this.model = model;
		this.num_payments = num_payments;
		this.payments = monthlyPayments;
		this.ownerId = owner_id;
	}
	
	public Cars(double price, String make, String model,
			int num_payments) {
		this.price = price;
		this.make = make;
		this.model = model;
		this.num_payments = num_payments;
		this.payments = price/num_payments;
		ownerId = 1;
	}

	public void setOwner(int ownerId, double offer) {
		this.payments = offer/this.num_payments;
		this.ownerId = ownerId;
	}


	public int getSerialNumber() {
		return serialNumber;
	}

	public double getPrice() {
		return price;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public int getNum_payments() {
		return num_payments;
	}

	public double getPayments() {
		return payments;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public void setPrice(double price) {
		this.price = price;
		this.payments = price/this.num_payments;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setNum_payments(int num_payments) {
		this.num_payments = num_payments;
	}

	public void setPayments(double payments) {
		this.payments = payments;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	
	
	

}
