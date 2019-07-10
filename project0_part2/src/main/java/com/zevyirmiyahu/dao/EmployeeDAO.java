package com.zevyirmiyahu.dao;

import java.util.Scanner;

public interface EmployeeDAO extends UserDAO {

	public String getUserName();
	public short getLoginPin();
	public void addCarToLot(byte carId, String brand, String model, 
			String color, short year, int price, int mileage);
	public String viewCarInLot(byte carId);
	public void removeCarFromLot(byte carId);
	public void acceptOrReject(Scanner scanner);
	public void viewAllPayments();
}
