package com.revature.project0;

import java.io.Serializable;

public class Offers implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6174249946293935653L;
	
	private int custId;
	private double offer;
	private int carId;
	
	public Offers(int custId, double offer, int carId) {
		this.custId = custId;
		this.offer = offer;
		this.carId = carId;
	}

	public int getCustId() {
		return custId;
	}

	public double getOffer() {
		return offer;
	}

	public int getCarId() {
		return carId;
	}


	
}
