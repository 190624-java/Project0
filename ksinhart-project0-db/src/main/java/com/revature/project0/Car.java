package com.revature.project0;

import java.io.Serializable;

public class Car implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 170838568184456905L;
	//car will be the object that gets placed in a data structure in SystemConsole
	//possibly the car will maintain the offers made on it
	private String brand;
	private String owner;
	private String name;
	private int year;
	private double price;
	private String license;
	
	public Car(String brand, String owner, String name, int year, double price, String license) {
		super();
		this.brand = brand;
		this.owner=owner;
		this.name = name;
		this.year = year;
		this.price = price;
		this.license=license;
	}
	
	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public boolean equals(Car obj) {
		if(obj.getLicense().equals(this.license)) {
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return this.brand+" "+this.name+" "+this.year+" "+this.price+" "+this.license;
	}
}
