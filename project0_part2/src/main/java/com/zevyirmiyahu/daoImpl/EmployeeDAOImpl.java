package com.zevyirmiyahu.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.zevyirmiyahu.beans.Employee;
import com.zevyirmiyahu.beans.Offer.OFFERSTATUS;
import com.zevyirmiyahu.dao.EmployeeDAO;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public boolean registerAccount(Scanner scanner) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean login(String userName, short loginPin) {
		boolean loginSuccessful = false;
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT * FROM Employee WHERE user_name = ? AND login_pin = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			stmt.setShort(2, loginPin);
			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				loginSuccessful = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loginSuccessful;
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public short getLoginPin() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addCarToLot(byte carId, String brand, String model, String color, short year, int price, int mileage) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "INSERT INTO Car(car_id, brand, model, color, year, price, mileage)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setByte(1, carId);
			stmt.setString(2, brand);
			stmt.setString(3, model);
			stmt.setString(4, color);
			stmt.setShort(5, year);
			stmt.setInt(6, price);
			stmt.setInt(7, mileage);
			stmt.executeUpdate();
			System.out.println("Car added to lot");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// used only for unit tests
	@Override
	public String viewCarInLot(byte carId) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT * FROM Car WHERE car_id =" + carId;
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			String brand = null;
			String model = null;
			String color = null;
			while (result.next()) {
				brand = result.getString("brand");
				model = result.getString("model");
				color = result.getString("color");
			}
			if(brand == null && model == null && color == null) return null;
			else return brand + ", " + model + ", " + color;
			} catch(SQLException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	// Uses Stored Procedure to remove car from lot
	public void removeCarFromLot2(byte carId) {
		try(Connection conn = ConnectionFactory.getConnection()) {
			String sql = "{CALL DeleteCar(?)}";
			CallableStatement ct = conn.prepareCall(sql);
			ct.setByte(1, carId);
			int rowsAffected = ct.executeUpdate();
			System.out.println(rowsAffected + " car(s) removed from lot.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeCarFromLot(byte carId) {
		// 1 get connection
		try (Connection conn = ConnectionFactory.getConnection()) {
			// 2. create statement
			String sql = "DELETE FROM Car WHERE car_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, carId);

			// 3. execute the update
			int rowsAffected = stmt.executeUpdate(); // for INSERT, UPDATE, and DELETE
			System.out.println(rowsAffected + " car(s) removed from lot.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void acceptOrReject(Scanner scanner) {
		List<Byte> acceptedCarIds = new ArrayList<>(); // keeps track of accepted offers
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT * FROM Offer WHERE offer_status = 'PENDING'";

			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			int counter = 0;
			while (result.next()) {
				String userName = result.getString("customer");
				int offerAmount = result.getInt("offer_amount");
				byte carId = result.getByte("car_id");

				// ensures an offer that's accepted doesn't get replaced by next offer
				if (!acceptedCarIds.contains(carId)) {
					OFFERSTATUS offerStatus = null;
					counter++;
					System.out.println("----- Offer #" + counter + " -----");
					System.out.println("User Name: " + userName);
					System.out.println("Offer Amount: " + offerAmount);
					System.out.println("Car ID: " + carId);
					System.out.println("Accept offer (y / n) ? ");
					String answer = scanner.next();

					if (answer.equals("Y") || answer.equals("y")) {
						offerStatus = OFFERSTATUS.ACCEPTED;
						acceptedCarIds.add(carId);
						setOfferStatus(offerStatus, userName, carId);
						setRemainingPayment(userName, carId, offerAmount);
					} else if (answer.equals("N") || answer.equals("n")) {
						offerStatus = OFFERSTATUS.DECLINED;
						setOfferStatus(offerStatus, userName, carId);
					}
				}
			}
			if (counter == 0)
				System.out.println("No offers found");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void setRemainingPayment(String userName, byte carId, int offerAmount) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT * FROM Car WHERE owner = ? AND car_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			stmt.setByte(2, carId);
			ResultSet result = stmt.executeQuery();

			while (result.next()) { // only one
				int price = result.getInt("price");
				int remainingPayment = offerAmount > price ? 0 : price - offerAmount;
				DealershipSystemDAOImpl dsi = new DealershipSystemDAOImpl();
				dsi.remainingPayments(userName, carId, remainingPayment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void setOfferStatus(OFFERSTATUS offerStatus, String userName, byte carId) {
		if (offerStatus.equals(OFFERSTATUS.ACCEPTED)) {
			DealershipSystemDAOImpl ddi = new DealershipSystemDAOImpl();
			CarDAOImpl carDaoImpl = new CarDAOImpl();
			OfferDAOImpl offerDaoImpl = new OfferDAOImpl();
			carDaoImpl.updateCarOwner(userName, carId);
			offerDaoImpl.setOfferStatus("ACCEPTED", userName, carId);
			ddi.rejectPendingOffers(userName, carId); // system rejects all other pending offers
		} else if (offerStatus.equals(OFFERSTATUS.DECLINED)) {
			OfferDAOImpl offerDaoImpl = new OfferDAOImpl();
			offerDaoImpl.setOfferStatus("DECLINED", userName, carId);
		}
	}

	@Override
	public void viewAllPayments() {
		// TODO Auto-generated method stub

	}

	// used only for testing
	public List<Employee> getAllEmployees() {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT * FROM Employee";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			List<Employee> employees = new ArrayList<>();
			Employee employee = null;
			while (result.next()) {
				String userName = result.getString("user_name");
				short loginPin = result.getShort("login_pin");
				employee = new Employee(userName, loginPin);
				employees.add(employee);
			}
			return employees;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void viewAllRemainingPayments(String userName) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT * FROM Car WHERE owner IS NOT NULL";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();

			int counter = 0;
			while (result.next()) {
				String owner = result.getString("owner");
				int carId = result.getInt("car_id");
				int remainingPayment = result.getInt("remaining_price");

				counter++;
				if (remainingPayment != 0) {
					System.out.println("#" + counter + " --------------------------------" 
							+ "\n Owner: " + owner
							+ "\nCarId: " + carId
							+ "\nRemaining Payment: " + remainingPayment);
				}
			}
			if (counter == 0)
				System.out.println("No Payments");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
