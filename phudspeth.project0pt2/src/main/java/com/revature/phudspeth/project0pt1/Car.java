package com.revature.phudspeth.project0pt1;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Car extends CarAbst implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6024366737200721706L;
	protected String make;
	protected String model;
	private boolean isNew = false;
	private int user = 0;
	private double owed;
	private double monthlyPay;
	private int monthsLeft;
	protected double purchaseCost;
	private HashMap<Integer, Double> offers = new HashMap<Integer, Double>();
	
	
	public Car(String Make, String Model, double inCost) 
	{
		make = Make;
		model = Model;
		purchaseCost = inCost;
		System.out.println(make);
		System.out.println(model);
	}
	
	public boolean checkIfNew() 
	{
		if(isNew) 
		{
			isNew =false;
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
		isNew = true;
		offers.clear();
	}
	
	public void assignOwner(int own) 
	{
		user = own;
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
		purchaseCost = debt;
	}

	@Override
	public void makePayment(double pay) 
	{
		owed -= pay;
		updateDebt();
		
	}
	public void makePayment() 
	{
		owed -= monthlyPay;
		monthsLeft --;
		
	}
	
	public void showCarDetails() 
	{
		System.out.println(
				make + " " + model +"\n" +
				"Owed: " + owed + "\n" +
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
