package com.dealership.DAOinterface;

import com.dealership.Car;

import java.util.ArrayList;
import java.util.HashSet;

public interface CarAccessor {
	public boolean lotContainsType(String carKey);
	public void addCar(Car newCar);
	public void removeCar(Car newCar);
	public Car returnCar(String carKey, int carIndex);
	public void displayLot();
	public void displayOfType(String carKey);
	public int numberOfType(String carKey);
	public HashSet<Integer> getAllIds();
	public ArrayList<Car> retrunCarsOwnedBy(String custId);
	public Car getById(int id);
}
