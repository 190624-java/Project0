package com.revature.project0;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class CarPersistence {
	public static File filename = new File("car-data.ser");
	
	public static void addNewCarDB(Car car) {
		try(Connection conn = ConnectionSetUp.getConnection()){
			String sql = "INSERT INTO car_lot VALUES (?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, car.getBrand());
			stmt.setString(2, car.getName());
			stmt.setString(3, car.getOwner());
			stmt.setInt(4, car.getYear());
			stmt.setDouble(5, car.getPrice());
			stmt.setString(6, car.getLicense());
			
			int rowsAffected = stmt.executeUpdate();
			System.out.println("Rows Inserted: " + rowsAffected);
			
			//ResultSet keys = stmt.getGeneratedKeys();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void removeCarDB(Car car) {
		try(Connection conn = ConnectionSetUp.getConnection()){
			String sql = "DELETE FROM car_lot WHERE license = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, car.getLicense());
			
			int rowsAffected = stmt.executeUpdate();
			System.out.println("Rows Inserted: " + rowsAffected);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Car getCar(String license) {
		try(Connection conn = ConnectionSetUp.getConnection()){
			Car car = null;
			User user = null;
			String sql = "SELECT brand, c_name, c_owner, c_year, price FROM car_lot WHERE license = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, license);
			
			ResultSet keys = stmt.executeQuery();
			//System.out.println("Rows Retrieved: " + rowsAffected);
			
			//ResultSet keys = stmt.getGeneratedKeys();
			while(keys.next()) {
				String brand = keys.getString(1);
				String name= keys.getString(2);
				String owner= keys.getString(3);
				int year= keys.getInt(4);
				double price= keys.getDouble(5);
				car = new Car(brand,name,owner,year,price,license);
			}
			//return (Customer) user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return null;
		
	}
	
	public static void changeOwner(String username, String license, double amt) {
		try(Connection conn = ConnectionSetUp.getConnection()){
			String sql = "UPDATE car_lot SET c_owner = ? AND price = ? WHERE license = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1,username);
			stmt.setDouble(2, amt);
			stmt.setString(3, license);
			
			int rowsAffected = stmt.executeUpdate();
			System.out.println("Rows Changed: " + rowsAffected);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<Car> getCarList() {
		try(Connection conn = ConnectionSetUp.getConnection()){
			String sql = "SELECT brand, c_name, c_owner, c_year, price, license FROM car_lot";
			PreparedStatement stmt = conn.prepareStatement(sql);
			//stmt.setString(1,username);
			
			ResultSet keys = stmt.executeQuery();
			//System.out.println("Rows Retrieved: " + rowsAffected);
			
			//ResultSet keys = stmt.getGeneratedKeys();
			List<Car> carList = new ArrayList<Car>();
			while(keys.next()) {
				String brand = keys.getString(1);
				String name= keys.getString(2);
				String owner= keys.getString(3);
				int year= keys.getInt(4);
				double price= keys.getDouble(5);
				String license=keys.getString(6);
				carList.add(new Car(brand,name,owner,year,price,license));
			}
			return carList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Car> getOwnedCars(String username) {
		try(Connection conn = ConnectionSetUp.getConnection()){
			String sql = "SELECT brand, c_name, c_owner, c_year, price, license FROM car_lot WHERE c_owner = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1,username);
			
			ResultSet keys = stmt.executeQuery();
			//System.out.println("Rows Retrieved: " + rowsAffected);
			
			//ResultSet keys = stmt.getGeneratedKeys();
			List<Car> carList = new ArrayList<Car>();
			while(keys.next()) {
				String brand = keys.getString(1);
				String name= keys.getString(2);
				String owner= keys.getString(3);
				int year= keys.getInt(4);
				double price= keys.getDouble(5);
				String license=keys.getString(6);
				carList.add(new Car(brand,name,owner,year,price,license));
			}
			return carList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Car> getAvailableCars() {
		try(Connection conn = ConnectionSetUp.getConnection()){
			String sql = "SELECT brand, c_name, c_owner, c_year, price, license FROM car_lot WHERE c_owner = 'DLR'";
			PreparedStatement stmt = conn.prepareStatement(sql);
			//stmt.setString(1,username);
			
			ResultSet keys = stmt.executeQuery();
			//System.out.println("Rows Retrieved: " + rowsAffected);
			
			//ResultSet keys = stmt.getGeneratedKeys();
			List<Car> carList = new ArrayList<Car>();
			while(keys.next()) {
				String brand = keys.getString(1);
				String name= keys.getString(2);
				String owner= keys.getString(3);
				int year= keys.getInt(4);
				double price= keys.getDouble(5);
				String license=keys.getString(6);
				carList.add(new Car(brand,name,owner,year,price,license));
			}
			return carList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static List<Car> getPaymentList() {
		try(Connection conn = ConnectionSetUp.getConnection()){
			String sql = "SELECT brand, c_name, c_owner, c_year, price, license FROM car_lot WHERE c_owner != 'DLR'";
			PreparedStatement stmt = conn.prepareStatement(sql);
			//stmt.setString(1,username);
			
			ResultSet keys = stmt.executeQuery();
			//System.out.println("Rows Retrieved: " + rowsAffected);
			
			//ResultSet keys = stmt.getGeneratedKeys();
			List<Car> carList = new ArrayList<Car>();
			while(keys.next()) {
				String brand = keys.getString(1);
				String name= keys.getString(2);
				String owner= keys.getString(3);
				int year= keys.getInt(4);
				double price= keys.getDouble(5);
				String license=keys.getString(6);
				carList.add(new Car(brand,name,owner,year,price,license));
			}
			return carList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/*
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
	*/
}
