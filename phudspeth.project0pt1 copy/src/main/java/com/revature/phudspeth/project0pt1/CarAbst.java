package com.revature.phudspeth.project0pt1;

import java.util.TreeMap;

public abstract class CarAbst 
{
//	protected String make;
//	protected String model;
	protected TreeMap<Integer,Integer> offers = new TreeMap<>();
	public abstract void offer(int j,double k);
	public abstract void assignOwner(int own);
	public abstract int getOwner();
	public abstract double getDebt();
	public abstract void makePayment(double pay);
}
