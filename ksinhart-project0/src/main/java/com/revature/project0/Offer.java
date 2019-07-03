package com.revature.project0;

import java.io.Serializable;

public class Offer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5912732723815892002L;
	
	private boolean accepted;
	private Customer customer;
	private Car car;
	private double amt;
	
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public double getAmt() {
		return amt;
	}
	public void setAmt(int amt) {
		this.amt = amt;
	}
	
	
	public boolean isAccepted() {
		return accepted;
	}
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	public Offer(Customer customer, Car car, double amt) {
		super();
		this.accepted=false;
		this.customer = customer;
		this.car = car;
		this.amt = amt;
	}
	
	@Override
	public String toString() {
		return this.customer.getUsername()+" "+this.getCar().getBrand()+this.getCar().getName()+" "+this.getAmt();
		
	}
	
	
}
