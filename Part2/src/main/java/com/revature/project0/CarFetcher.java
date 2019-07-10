package com.revature.project0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarFetcher {
	public Cars createNewCar(Cars car) {
		//1. get a connection to the database
        try(Connection con = DbConnectionHandler.getConnection())
        {
            //2. Create a statement.
            String sql = "INSERT INTO Cars(car_make, car_model, num_payments, monthly_payment, owner_id, price)"
                    +"VALUES (?,?,?,?,?,?)";
            String[] primaryKeyValues = {"car_id"};
            PreparedStatement stmt = con.prepareStatement(sql, primaryKeyValues);
            stmt.setString(1, car.getMake());
            stmt.setString(2, car.getModel());
            stmt.setInt(3, car.getNum_payments());
            stmt.setDouble(4, car.getPayments());
            stmt.setInt(5, car.getOwnerId());
            stmt.setDouble(6, car.getPrice());
            
            //3. Executing the statement
            stmt.executeUpdate();
            
            //4. get Player_id
            ResultSet keys = stmt.getGeneratedKeys();
            while(keys.next()) 
            {
                int userId = keys.getInt(1);
                car.setSerialNumber(userId);
            }
            return car;
        } 
        catch (SQLException e) 
        {
            //would probably want to throw a custom application-specific exception to be handled elsewhere.
            System.out.println("Something went wrong while trying to create a car in the database.");
            e.printStackTrace();
            return null;
        }
	}
	
	public Cars getCar(int carId) {
		//1. get a connection
		try(Connection conn = DbConnectionHandler.getConnection()){
			//2. create a statement
			String sql = "Select car_id, car_make, car_model, num_payments, monthly_payment, price, owner_id "
					+ "From cars "
					+ "Where car_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, carId);
			//3. execute query
			ResultSet results = stmt.executeQuery();
			
			//4. iterate through results and mat to object
			Cars car = null;
			while(results.next()) {
				System.out.println("Found car...");
				int car_id = results.getInt("car_id");
				double price = results.getDouble("price");
				String make = results.getString("car_make");
				String model = results.getString("car_model");
				int numPayments = results.getInt("num_payments");
				double monthlyPayments = results.getDouble("monthly_payment");
				int ownerId = results.getInt("owner_id");
				
				car = new Cars(car_id, price, make, model, numPayments, monthlyPayments, ownerId);
				
			}
			if(car == null) {
				System.out.println("Try agian");
			}
			return car;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public ArrayList<Cars> getCarsOnLot() {
		try(Connection conn = DbConnectionHandler.getConnection())
		{
			String sql = "SELECT * FROM Cars Where owner_id = 1";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet results = stmt.executeQuery();
			ArrayList<Cars> lot = new ArrayList<>();
			while(results.next())
			{
				int car_id = results.getInt("car_id");
				double price = results.getDouble("price");
				String make = results.getString("car_make");
				String model = results.getString("car_model");
				int numPayments = results.getInt("num_payments");
				double monthlyPayments = results.getDouble("monthly_payment");
				int ownerId = results.getInt("owner_id");
				
				lot.add(new Cars(car_id, price, make, model, numPayments, monthlyPayments, ownerId));
			}
			
			return lot;
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Cars> getCarsSold() {
		try(Connection conn = DbConnectionHandler.getConnection())
		{
			String sql = "SELECT * FROM Cars Where owner_id != 1";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet results = stmt.executeQuery();
			ArrayList<Cars> lot = new ArrayList<>();
			while(results.next())
			{
				int car_id = results.getInt("car_id");
				double price = results.getDouble("price");
				String make = results.getString("car_make");
				String model = results.getString("car_model");
				int numPayments = results.getInt("num_payments");
				double monthlyPayments = results.getDouble("monthly_payment");
				int ownerId = results.getInt("owner_id");
				
				lot.add(new Cars(car_id, price, make, model, numPayments, monthlyPayments, ownerId));
			}
			
			return lot;
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Cars> getMyCars(int custId) {
		try(Connection conn = DbConnectionHandler.getConnection())
		{
			String sql = "SELECT * FROM Cars Where owner_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, custId);
			
			ResultSet results = stmt.executeQuery();
			ArrayList<Cars> lot = new ArrayList<>();
			while(results.next())
			{
				int car_id = results.getInt("car_id");
				double price = results.getDouble("price");
				String make = results.getString("car_make");
				String model = results.getString("car_model");
				int numPayments = results.getInt("num_payments");
				double monthlyPayments = results.getDouble("monthly_payment");
				int ownerId = results.getInt("owner_id");
				
				lot.add(new Cars(car_id, price, make, model, numPayments, monthlyPayments, ownerId));
			}
			
			return lot;
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void deleteCar(int carId) {
		try(Connection conn = DbConnectionHandler.getConnection()){
			 String sql = "DELETE cars WHERE car_id = ?";
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 stmt.setInt(1, carId);
			 stmt.executeUpdate();
			 
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateCar(Cars car) {
		try(Connection conn = DbConnectionHandler.getConnection()){
			 String sql = "UPDATE cars SET price = ?, num_payments = ?, monthly_payment = ?, owner_id = ? WHERE car_id = ?";
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 stmt.setDouble(1, car.price);
			 stmt.setInt(2, car.num_payments);
			 stmt.setDouble(3, car.payments);
			 stmt.setInt(4, car.ownerId);
			 stmt.setInt(5, car.serialNumber);
			
			 stmt.executeUpdate();
			 
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
