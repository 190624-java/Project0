package project0.systems;

import java.util.ArrayList;

import project0.automobiles.Car;

public class CarsInLot {
	
	private static ArrayList<Car> carsInLot = new ArrayList<Car>();
	
	public CarsInLot() {
		initialize();
	}
	
	private static void initialize() {
		if(carsInLot.isEmpty()) {
			Car car1 = new Car((byte)1, "Ford", "Mustang", "Black", (short)1966, 
					70_000, 40_000);
			Car car2 = new Car((byte)2, "Honda", "Civic", "White", (short)2001, 
					7_000, 80_000);
			Car car3 = new Car((byte)3, "VolksWagen", "Beetle", "Grey", (short)2019, 
					31_000, 100);
			carsInLot.add(car1);
			carsInLot.add(car2);
			carsInLot.add(car3);	
		}
	}
	
	public static ArrayList<Car> getCarsInLot() {
		return carsInLot;
	}
	
	public static void printCarsInLot() {
		if(carsInLot.isEmpty()) {
			System.out.println("No Cars in Lot");
			return;
		}
		int counter = 0;
		for(Car c : carsInLot) {
			counter++;
			System.out.println("#" + counter +": " 
								+ "\nCarID: " + c.getId()
								+ "\nBrand: " + c.getBrand() 
								+ "\nModel: " + c.getModel() 
								+ "\nColor: " + c.getColor() 
								+ "\nYear: " + c.getYear() 
								+ "\nPrice: " + c.getPrice()
								+ "\nMileage: " + c.getMileage());
		}
	}
	
}
