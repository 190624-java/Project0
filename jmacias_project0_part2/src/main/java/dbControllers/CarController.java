package dbControllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Car;
import beans.Offer;
import dataAccessObjects.CarDAO;

import service.ConnectionFactory;

public class CarController implements CarDAO {

	@Override
	public void addCar(Car car) {
		// 1. connection
		try (Connection conn = ConnectionFactory.getConnectionUsingProp()) {
			// 2. create the statement
			String sql = "INSERT INTO Car(car_id, make_and_model, price)"
					+ "VALUES (?, ?, ?)";
			// TODO also need to add table offers with fk to hold values in car object arrLst
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, car.getNumber());
			stmt.setString(2, car.getMakeAndModel());
			stmt.setInt(3, car.getPrice());
			
			// 3. Execute
			int rowsAffected = stmt.executeUpdate();
			System.out.println("Rows insterted: " + rowsAffected);
			
			// Maybe this should return the car object?
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Something went wrong with creating car in db.");
			
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Problem with getting prop for connection.");
		}

	}

	@Override
	public Car getCar(int carId) {
		// connection
		try (Connection conn = ConnectionFactory.getConnectionUsingProp()) {
			// statement
			String sql = "SELECT car_id, make_and_model, price"
					+ " FROM Car"
					+ " Where car_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, carId);
			
			// execute query
			ResultSet results = stmt.executeQuery();
			System.out.println(results);
			
			// iterate through results and return 
			Car car = null;
			while (results.next()) {
				int carId2 = results.getInt("car_id");
				String makeAndModel = results.getString(2);
				int price = results.getInt("price");
				car = new Car(makeAndModel, price, carId2);
			}
			return car;
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Something went wrong with retrieving the car from the db.");
			return null;
		}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("Problem with getting prop for connection.");
			return null;
		}
	}


	@Override
	public void removeCar(int carId) {
		// 1. connection
				try (Connection conn = ConnectionFactory.getConnectionUsingProp()) {
					// 2. create the statement
					String sql = "DELETE FROM Car"
							+ " WHERE car_id = ?";
					
					PreparedStatement stmt = conn.prepareStatement(sql);
					stmt.setInt(1, carId);
					
					
					// 3. Execute
					int rowsAffected = stmt.executeUpdate();
					System.out.println("Car deleted: " + rowsAffected);
					
					
				}
				catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Something went wrong with creating car in db.");
					
				}
				catch (IOException e) {
					e.printStackTrace();
					System.out.println("Problem with getting prop for connection.");
				}
		
	}

	@Override
	public Car overwriteCar(Car car) {
		// TODO This method is just used for writing to files, not needed w/ db
		return car;
	}

	@Override
	public List<Car> getCars() {
		// connection
				try (Connection conn = ConnectionFactory.getConnectionUsingProp()) {
					// statement
					String sql = "SELECT car_id, make_and_model, price"
							+ " FROM Car";
					Statement stmt = conn.createStatement();
					
					// execute query
					ResultSet results = stmt.executeQuery(sql);
					System.out.println(results);
					
					// iterate through results and return 
					List<Car> cars = new ArrayList<>();
					while (results.next()) {
						int carId = results.getInt("car_id");
						String makeAndModel = results.getString(2);
						int price = results.getInt("price");
						Car car = new Car(makeAndModel, price, carId);
						cars.add(car);
					}
					
					return cars;
				}
				catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Something went wrong with retrieving the cars from the db.");
					return null;
				}
				catch(IOException e) {
					e.printStackTrace();
					System.out.println("Problem with getting prop for connection.");
					return null;
				}
	}

	@Override
	public void createOffer(Car car) {
		// 1. connection
				try (Connection conn = ConnectionFactory.getConnectionUsingProp()) {
					// 2. create the statement
					String sql = "INSERT INTO CarOffers(car_id, u_name)"
							+ "VALUES (?, ?)";
					// TODO also need to add table offers with fk to hold values in car object arrLst
					PreparedStatement stmt = conn.prepareStatement(sql);
					stmt.setInt(1, car.getNumber());
					stmt.setString(2, car.offers.isEmpty() ? null : car.offers.get(0));
					
					// 3. Execute
					int rowsAffected = stmt.executeUpdate();
					System.out.println("Rows insterted: " + rowsAffected);
					
					// Maybe this should return the car object?
					
				}
				catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Something went wrong with creating car in db.");
					
				}
				catch (IOException e) {
					e.printStackTrace();
					System.out.println("Problem with getting prop for connection.");
				}
	}

	@Override
	public List<Offer> getOffers() {
		// connection
		try (Connection conn = ConnectionFactory.getConnectionUsingProp()) {
			// statement
			String sql = "SELECT car_id, u_name"
					+ " FROM CarOffers";
			Statement stmt = conn.createStatement();
			
			// execute query
			ResultSet results = stmt.executeQuery(sql);
			System.out.println(results);
			
			// iterate through results and return 
			List<Offer> offers = new ArrayList<>();
			while (results.next()) {
				int carId = results.getInt("car_id");
				String userName = results.getString(2);
				Offer offer = new Offer(carId, userName);
				offers.add(offer);
			}
			
			return offers;
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Something went wrong with retrieving the offers from the db.");
			return null;
		}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("Problem with getting prop for connection.");
			return null;
		}
	}

}
