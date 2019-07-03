package com.revature.project0;

import java.io.Serializable;

public class Car implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 170838568184456905L;
	//car will be the object that gets placed in a data structure in SystemConsole
	//possibly the car will maintain the offers made on it
	protected String brand;
	protected String owner;
	protected String name;
	protected int year;
	protected double price;
	
	public Car(String brand, String owner, String name, int year, double price) {
		super();
		this.brand = brand;
		this.owner=owner;
		this.name = name;
		this.year = year;
		this.price = price;
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
		if(this.brand.equals(obj.brand)) {
			if(this.name.equals(obj.name)) {
				if(this.year==obj.year) {
					if(this.owner.equals(obj.owner)) {
						return true;
					}
				}
			}
			
		}
		return false;
	}
	@Override
	public String toString() {
		return this.brand+" "+this.name+" "+this.year+" "+this.price;
	}
}
