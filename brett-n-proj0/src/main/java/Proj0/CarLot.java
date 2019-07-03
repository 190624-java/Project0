package Proj0;

import java.util.ArrayList;

public class CarLot {

	static ArrayList<Car> carsOnLot = new ArrayList<Car>();

	public void fillCarLot() {

		if (carsOnLot.isEmpty()) {

			Car c1 = new Car("Ford Explorer", "Black", "C1", 2013, 15_000, false);
			Car c2 = new Car("Chevrolet Corvette", "Red", "C2", 2018, 70_000, false);
			Car c3 = new Car("Monte Carlo", "White", "C3", 1987, 40_000, false);

			carsOnLot.add(c1);
			carsOnLot.add(c2);
			carsOnLot.add(c3);

		}

	}

	public void getCarsOnLot() {

		for (Car cars : carsOnLot) {
			System.out.println(cars.getName());
		}
	}
	
	public void addCarToLot() {
		
		
		
	}
	
	
	public void removeCarFromLot() {
		
		
		
	}
	
}