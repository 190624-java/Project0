package com.zevyirmiyahu.service;

import java.util.Scanner;

import com.zevyirmiyahu.beans.Car;
import com.zevyirmiyahu.daoImpl.CarDAOImpl;
import com.zevyirmiyahu.daoImpl.CustomerDAOImpl;
import com.zevyirmiyahu.daoImpl.DealershipSystemDAOImpl;
import com.zevyirmiyahu.daoImpl.OfferDAOImpl;

public class Driver {
	
	// clears and resets data in database
	private static void resetAndInitializeCarLot() {
		// clear offers
		OfferDAOImpl odi = new OfferDAOImpl();
		odi.deleteAllOffers();
		
		// clear cars and create new cars
		CarDAOImpl cdi = new CarDAOImpl();
		cdi.deleteAllCars();
		Car car = new Car(1, "Volkswagen", "Beetle", "Black", (short)2019, 35_000, 100);
		Car car2 = new Car(2, "Ford", "Mustang", "Blue", (short)1970, 70_000, 55_000);
		Car car3 = new Car(3, "Honda", "Civic", "White", (short)2013, 11_000, 4000);
		cdi.createCar(car3);
		cdi.createCar(car2);
		cdi.createCar(car);
		
		// clear all customers
		CustomerDAOImpl cudi = new CustomerDAOImpl();
		cudi.deleteAllCustomers();
		System.out.println("**** System Reset ****");
	}

	public static void main(String[] args) {
		resetAndInitializeCarLot(); // only needed to quickly reset all data in database
		Scanner scanner = new Scanner(System.in);
		new DealershipSystemDAOImpl(scanner);
	}

}
