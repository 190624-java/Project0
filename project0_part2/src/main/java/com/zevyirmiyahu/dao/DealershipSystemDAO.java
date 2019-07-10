package com.zevyirmiyahu.dao;

import java.util.Scanner;

public interface DealershipSystemDAO {
	
	public void systemStart(Scanner scanner);
	public void systemRun();
	public void systemStop();
	public void customerLoggedIn(Scanner scanner);
	public void employeeLoggedIn(Scanner scanner);
	public void rejectPendingOffers(String userName, byte carId);
}
