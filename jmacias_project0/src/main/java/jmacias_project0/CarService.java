package jmacias_project0;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CarService {

	public static List<Car> getCars() {
		List<Car> cars = null;
		try(ObjectInputStream str = new ObjectInputStream(new FileInputStream(new File(".\\src\\main\\resources\\cars.txt")))) {
			cars = (List<Car>) str.readObject();
		}
		catch (Exception e) {
			return new ArrayList<>();
		}
		return cars;
	}
	
	public static void addCar(Car newCar) throws FileNotFoundException, IOException {
		List<Car> cars = getCars();
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(".\\src\\main\\resources\\cars.txt")))) {
			if (cars != null) {
				System.out.println("Car data found, adding to cars.");
				cars.add(newCar);
			}
			else {
				System.out.println("You are the first user.");
				cars = new ArrayList<>();
				cars.add(newCar);
			}
			oos.writeObject(cars);
		}
		System.out.println("Cars updated.");
	}
	
	public static void overwriteCars(List<Car> cars) throws FileNotFoundException, IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(".\\src\\main\\resources\\cars.txt")))) {
			oos.writeObject(cars);
		}
	}
}
