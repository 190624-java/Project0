package com.zevyirmiyahu.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zevyirmiyahu.beans.Car;
import com.zevyirmiyahu.dao.CarDAO;

public class CarDAOImpl implements CarDAO {

	@Override
	public Car createCar(Car car) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "INSERT INTO Car(car_id, brand, model, color, year, price, "
					+ "mileage, has_offer, current_offer, remaining_price, owner)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, car.getId());
			stmt.setString(2, car.getBrand());
			stmt.setString(3, car.getModel());
			stmt.setString(4, car.getColor());
			stmt.setShort(5, car.getYear());
			stmt.setInt(6, car.getPrice());
			stmt.setInt(7, car.getMileage());
			stmt.setBoolean(8, car.getHasOffer());
			stmt.setInt(9, car.getCurrOfferAmount());
			stmt.setInt(10, car.getRemainingPrice());
			stmt.setString(11, car.getUserName()); // not userName is Owner

			stmt.executeUpdate();
			return car;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Car getCar(int carId) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT * FROM Car WHERE car_id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, carId);
			ResultSet result = stmt.executeQuery();
			Car car = null;
			while (result.next()) {
				String brand = result.getString("brand");
				String model = result.getString("model");
				String color = result.getString("color");
				short year = result.getShort("year");
				int price = result.getInt("price");
				int mileage = result.getInt("mileage");
				boolean hasOffer = result.getBoolean("has_offer");
				int currOfferAmount = result.getInt("current_offer");
				int remainingPrice = result.getInt("remaining_price");
				String userName = result.getString("owner");
				
				car = new Car(carId, brand, model, color, (short) year, price, mileage);
				car.setHasOffer(hasOffer);
				car.setCurrOfferAmount(currOfferAmount);
				car.setRemainingPrice(remainingPrice);
				car.setUserName(userName);
			}
			return car;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Car getCar(String userName) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT * FROM Car WHERE owner = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			ResultSet result = stmt.executeQuery();
			Car car = null;
			while (result.next()) {
				int carId = result.getInt("car_id");
				String brand = result.getString("brand");
				String model = result.getString("model");
				String color = result.getString("color");
				short year = result.getShort("year");
				int price = result.getInt("price");
				int mileage = result.getInt("mileage");
				boolean hasOffer = result.getBoolean("has_offer");
				int currOfferAmount = result.getInt("current_offer");
				int remainingPrice = result.getInt("remaining_price");
				
				car = new Car(carId, brand, model, color, (short) year, price, mileage); 
				car.setHasOffer(hasOffer);
				car.setCurrOfferAmount(currOfferAmount);
				car.setRemainingPrice(remainingPrice);
				car.setUserName(userName);
			}
			return car;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Car> getAllCars() { // returns all cars that are still on lot
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT * FROM Car WHERE owner IS NULL";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			List<Car> cars = new ArrayList<>();
			Car car = null;
			while (result.next()) {
				int carId = result.getInt("car_id");
				String brand = result.getString("brand");
				String model = result.getString("model");
				String color = result.getString("color");
				short year = result.getShort("year");
				int price = result.getInt("price");
				int mileage = result.getInt("mileage");
				boolean hasOffer = result.getBoolean("has_offer");
				int currOfferAmount = result.getInt("current_offer");
				int remainingPrice = result.getInt("remaining_price");
				String userName = result.getString("owner");
				
				car = new Car(carId, brand, model, color, (short) year, price, mileage); 
				car.setHasOffer(hasOffer);
				car.setCurrOfferAmount(currOfferAmount);
				car.setRemainingPrice(remainingPrice);
				car.setUserName(userName);
				cars.add(car);
			}
			return cars;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public HashMap<String, Integer> getCurrentOffers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCar(Car car) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "UPDATE Car"
					+ "SET car_id = ?, brand = ?, model = ?, color = ?, year = ?, price = ?, mileage = ?"
					+ "WHERE car_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, car.getId());
			stmt.setString(2, car.getBrand());
			stmt.setString(3, car.getModel());
			stmt.setString(4, car.getColor());
			stmt.setShort(5, car.getYear());
			stmt.setInt(6, car.getPrice());
			stmt.setInt(7, car.getMileage());
			stmt.setBoolean(8, car.getHasOffer());
			stmt.setInt(9, car.getCurrOfferAmount());
			stmt.setInt(10, car.getRemainingPrice());
			stmt.setString(11, car.getUserName()); // not userName is Owner
			
			int rowsAffected = stmt.executeUpdate();
			System.out.println("Rows affected: " + rowsAffected);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCar(Car car) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "DELETE FROM Car WHERE car_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, car.getId());

			int rowsAffected = stmt.executeUpdate();
			System.out.println("Rows deleted: " + rowsAffected);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void deleteCar(int carId) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "DELETE FROM Car WHERE car_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, carId);

			int rowsAffected = stmt.executeUpdate();
			System.out.println("Rows deleted: " + rowsAffected);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateCarOwner(String userName, byte carId) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "UPDATE Car SET owner = ? WHERE car_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			stmt.setInt(2, carId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// uses ONLY to quickly reset data in database
		public void deleteAllCars() {
			try (Connection conn = ConnectionFactory.getConnection()) {
				String sql = "DELETE FROM Car";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}
