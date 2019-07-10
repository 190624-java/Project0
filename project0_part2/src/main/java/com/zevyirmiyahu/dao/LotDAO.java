package com.zevyirmiyahu.dao;

import java.util.ArrayList;

import com.zevyirmiyahu.beans.Car;

public interface LotDAO {
	
	public ArrayList<Car> getCarsInLot();	
	public void printCarsInLot();
}
