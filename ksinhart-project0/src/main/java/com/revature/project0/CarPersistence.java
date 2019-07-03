package com.revature.project0;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class CarPersistence {
	public static File filename = new File("car-data.ser");
	
	public static void addNewCar(Car car) throws FileNotFoundException, IOException, ClassNotFoundException {
		List<Car> records = getAllCars();
		//Having this inside of the try was causing issues,
		//because you can't have an input stream and an output stream
		//on the same file at the same time.
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
			
			if (records != null) {
				//System.out.println("User found");
				records.add(car);
			} else {
				records = new ArrayList<Car>();
				records.add(car);
			}
			oos.writeObject(records);
			System.out.println("New list written");
		}
	}
	public static void removeCar( int carI) throws FileNotFoundException, IOException, ClassNotFoundException {
		List<Car> records = getAllCars();
		System.out.println(records.get(carI).toString());
		//Having this inside of the try was causing issues,
		//because you can't have an input stream and an output stream
		//on the same file at the same time.
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
			//records.remove(carI);
			//oos.writeObject(records);
			//System.out.println(records.indexOf(car));
			
			if(records.remove(carI)!=null) {
				System.out.println(records.toString());
				oos.writeObject(records);
				System.out.println("New list written");
			}
			else {
				System.out.println(records.toString());
				System.out.println("Something wrong");
			}
			
		}
	}
	
	
	public static void addCarList(List<Car> list) throws FileNotFoundException, IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
			oos.writeObject(list);
			System.out.println("List was rewritten");
		}
	}
	
	public static List<Car> getAllCars() throws IOException, ClassNotFoundException {
		List<Car> lot = null;
		try (ObjectInputStream str = new ObjectInputStream(new FileInputStream(filename))) {
			
			lot = (List<Car>) str.readObject();
			//System.out.println(lot);
		} catch (FileNotFoundException e) {
			return new ArrayList<>();
		}
		return lot;
	}
}
