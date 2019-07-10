package com.revature.phudspeth.project0pt1;

import java.util.HashMap;
import java.util.Map;

public class CarDB extends CarAbst
{
	/**
	 * 
	 */
	protected String make;
	protected String model;
	private int user = 0;
	private double owed;
	private double monthlyPay;
	private int monthsLeft;
	protected double purchaseCost;
	private int carID;
	private HashMap<Integer, Double> offers = new HashMap<Integer, Double>();
	
	
	public CarDB(String Make, String Model, double inCost) 
	{
		make = Make;
		model = Model;
		purchaseCost = inCost;
	}
	
	public CarDB(int carId, String Make, String Model, double inCost) 
	{
		carID = carId;
		make = Make;
		model = Model;
		purchaseCost = inCost;
	}
	
	public int getMRemaining() 
	{
		return monthsLeft;
	}
	
	public double getPurchaseCost() 
	{
		return purchaseCost;
	}
	
	public boolean checkIfNew() 
	{
		if(monthsLeft == -1) 
		{
			return true;
		}
		else return false;
	}
	
	@Override
	public void offer(int i, double k) 
	{
		offers.put(i, k);
	}
	
	public void offer(Map<Integer, Double> offers2) 
	{
		offers = (HashMap<Integer, Double>) offers2;
	}
	
	public HashMap<Integer, Double> getOffers() 
	{
		return offers;
		
	}
	
	public void sellCar(int i, double k) 
	{
		user = i;
		owed = k;
		offers.clear();
	}
	
	public void assignOwner(int own) 
	{
		user = own;
	}
	
	public int getID() 
	{
		return carID;
	}
	
	public int getOwner() 
	{
		return user;
	}

	@Override
	public double getDebt() 
	{
		return owed;
	}
	
	public void updateDebt() 
	{
		monthlyPay = owed/(double)monthsLeft;
	}
	
	public void initDebt(int m, double debt) 
	{
		monthsLeft = m;
		owed = debt;
	}

	@Override
	public void makePayment(double pay) 
	{
		if(owed-pay >= 0) {
			owed -= pay;
			updateDebt();
		}
	}
	public void makePayment() 
	{
		if(owed-monthlyPay >= 0) 
		{
			owed -= monthlyPay;
			monthsLeft --;
		}
		else 
		{
			System.out.println("You're close to paying off your car! Please enter a custom payment to reconcile the final payment.");
		}
		
	}
	
	public void showCarDetails() 
	{
		System.out.println(
				make + " " + model +"\n" +
				"Owed: " + owed + " Payments Remaining: " + monthsLeft + "\n" +
				"Monthly Payments: " + monthlyPay
				);
	}
	
	public void showCarDetailsSelling() 
	{
		System.out.println(
				make + " " + model +"\n" +
				"Base cost: " + purchaseCost + "\n"
				);
	}
	
	public double showMonthly() 
	{
		return monthlyPay;
	}
	
}
