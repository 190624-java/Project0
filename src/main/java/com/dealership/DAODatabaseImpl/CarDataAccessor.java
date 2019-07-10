package com.dealership.DAODatabaseImpl;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import com.dealership.Car;
import com.dealership.DAOinterface.CarAccessor;

public class CarDataAccessor implements CarAccessor {

	@Override
	public boolean lotContainsType(String carKey) {
		String[] carkeyParts = carKey.split(" ");
		if(carkeyParts.length != 3)
			return false;
		try(Connection conn = ConnectionFactory.getConnection())
		{
			String sql = "SELECT * FROM cars WHERE car_make = ? AND car_model = ? AND car_year = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, carkeyParts[0]);
			stmt.setString(2, carkeyParts[1]);
			stmt.setString(3, carkeyParts[2]);
			ResultSet results = stmt.executeQuery();
			if(results.next() == false)
			{
				return false;
			}
			else
			{
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void addCar(Car newCar) {
		try(Connection conn = ConnectionFactory.getConnection()) {
			String sql = "INSERT INTO cars (car_id, car_make, car_model, car_year, car_color, car_price, owner_id)"
					+ " VALUES (?,?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, newCar.getId());
			stmt.setString(2, newCar.getMake());
			stmt.setString(3, newCar.getModel());
			stmt.setInt(4, newCar.getYear());
			stmt.setString(5, newCar.getColor());
			stmt.setDouble(6, newCar.getPrice());
			stmt.setString(7, null);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void removeCar(Car newCar) {	
		try(Connection conn = ConnectionFactory.getConnection()) {
			String sql = "DELETE FROM cars WHERE car_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1,newCar.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Car returnCar(String carKey, int carIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void displayLot() {
		try(Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT car_id, car_make, car_model, car_year, car_color, car_price, owner_id FROM cars";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet results = stmt.executeQuery();
			while(results.next()) {
				System.out.println("ID: " + results.getInt("car_id")
						+ "MAKE: " + results.getString("car_make")
						+ "MODEL: " + results.getString("car_model")
						+ "YEAR: " + results.getInt("car_year")
						+ "COLOR: " + results.getString("car_color")
						+ "PRICE: " + results.getDouble("car_price")
						+ "OWNER: " + results.getString("owner_id"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void displayOfType(String carKey) {
		// TODO Auto-generated method stub

	}

	@Override
	public int numberOfType(String carKey) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public HashSet<Integer> getAllIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Car> retrunCarsOwnedBy(String custId) {
		try(Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT car_id, car_make, car_model, car_year, car_color, car_price, owner_id FROM cars "
					+ "WHERE owner_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, custId);
			ResultSet results = stmt.executeQuery();
			ArrayList<Car> tmpList = new ArrayList<>();
			while(results.next()) {
				tmpList.add(
						new Car(results.getString("car_make"), results.getString("car_model"), results.getInt("car_year"),
						results.getString("car_color"), results.getDouble("car_price"), results.getInt("car_id"))
				);
			}
			return tmpList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Car getById(int id) {
		try(Connection conn = ConnectionFactory.getConnection())
		{
			String sql = "SELECT car_id, car_make, car_model, car_year, car_color, car_price, owner_id"
					+ " FROM cars WHERE car_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet results = stmt.executeQuery();
			Car tmpCar = null;
			while(results.next()) {
				tmpCar = new Car(results.getString("car_make"), results.getString("car_model"), results.getInt("car_year"),
						results.getString("car_color"), results.getDouble("car_price"), results.getInt("car_id"));
			}
		return tmpCar;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
