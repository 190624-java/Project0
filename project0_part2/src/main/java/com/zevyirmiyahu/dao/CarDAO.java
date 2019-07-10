package com.zevyirmiyahu.dao;

import java.util.HashMap;
import java.util.List;

import com.zevyirmiyahu.beans.Car;

public interface CarDAO {

	public Car createCar(Car car);
	public Car getCar(int carId);
	public Car getCar(String userName);
	public List<Car> getAllCars();
	public HashMap<String, Integer> getCurrentOffers();
	public void updateCar(Car car);
	public void deleteCar(Car car);
	public void deleteCar(int carId);
}
