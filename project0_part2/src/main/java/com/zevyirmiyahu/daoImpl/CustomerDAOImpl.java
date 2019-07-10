package com.zevyirmiyahu.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.zevyirmiyahu.dao.CustomerDAO;

public class CustomerDAOImpl implements CustomerDAO {

	@Override
	public boolean registerAccount(Scanner scanner) {
		boolean registrationSuccessful = true;
		String userName;
		short loginPin; // 3 digit pin
		System.out.print("Desired user name: ");
		userName = scanner.next();

		// ensure new user
		if (isRegistered(userName)) {
			System.out.println("User already exists.");
			registrationSuccessful = false;
			return registrationSuccessful;
		}

		System.out.print("Desired 3-digit pin: ");
		loginPin = scanner.nextShort();

		// ensure proper loginPin length
		if (!isPinProperLength(loginPin)) {
			registrationSuccessful = false;
			return registrationSuccessful;
		}

		// add user to data base
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "INSERT INTO Customer(user_name, login_pin)" + "VALUES (?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			stmt.setInt(2, loginPin);
			stmt.executeUpdate();
			System.out.println("Registration Successful");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return registrationSuccessful;
	}

	private boolean isRegistered(String userName) {

		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT * FROM Customer";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				String existingUserName = result.getString("user_name");
				if (userName.equals(existingUserName))
					return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean isPinProperLength(short loginPin) {
		if (loginPin < 100 || loginPin > 999) {
			System.out.println("INVALID LENGTH, system exiting...");
			return false; // returns false
		} else
			return true;
	}

	@Override
	public boolean login(String userName, short loginPin) {
		boolean loginSuccessful = false;
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT * FROM Customer WHERE user_name = ? AND login_pin = ?";

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
	public void offerStatus(String userName) { // shows status of all offers

		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT * FROM Offer WHERE customer = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			ResultSet result = stmt.executeQuery();

			int counter = 0;
			while (result.next()) {
				counter++;
				int carId = result.getInt("car_id");
				int offerAmount = result.getInt("offer_amount");
				String offerStatus = result.getString("offer_status");
				System.out.println("----- Offer #" + counter + " -----");
				System.out.println(
						"CarId: " + carId + "\nOffer Amount: " + offerAmount + "\nOffer Status: " + offerStatus);

			}
			if (counter == 0) System.out.println("No offers found");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void makeOffer(String userName, int offerAmount, byte carId) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "INSERT INTO Offer(customer, car_id, offer_amount, offer_status)" + "VALUES (?, ?, ?, ?) ";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			stmt.setInt(2, carId);
			stmt.setInt(3, offerAmount);
			stmt.setString(4, "PENDING");
			stmt.executeUpdate();
			System.out.println("Offer Sent");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean duplicateOffer(String userName, byte carId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void viewCarsOwned(String userName) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT * FROM Car WHERE owner = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			ResultSet result = stmt.executeQuery();

			int counter = 0;
			while (result.next()) {
				int carId = result.getInt("car_id");
				String brand = result.getString("brand");
				String model = result.getString("model");
				String color = result.getString("color");
				short year = result.getShort("year");
				int price = result.getInt("price");
				int mileage = result.getInt("mileage");

				counter++;
				System.out.println("#" + counter + " --------------------------------" + "\nCarId: " + carId
						+ "\nBrand: " + brand + "\nModel: " + model + "\nColor: " + color + "\nYear: " + year
						+ "\nPrice: " + price + "\nMileage: " + mileage);
			}
			if (counter == 0) System.out.println("No Cars Owned");
		} catch (SQLException e) {
			// e.printStackTrace();
		}
	}

	@Override
	public void viewRemainingPayments(String userName) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT * FROM Car WHERE owner = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			ResultSet result = stmt.executeQuery();

			int counter = 0;
			while (result.next()) {
				int carId = result.getInt("car_id");
				String brand = result.getString("brand");
				String model = result.getString("model");
				int remainingPayment = result.getInt("remaining_price");

				counter++;
				if (remainingPayment != 0) {
					System.out.println("#" + counter + " --------------------------------" + "\nCarId: " + carId
							+ "\nBrand: " + brand + "\nModel: " + model + "\nRemaining Payment: " + remainingPayment);
				}
			}
			if (counter == 0) System.out.println("No Remaining Payments");
		} catch (SQLException e) {
			 e.printStackTrace();
		}
	}

	// uses ONLY to quickly reset data in database
	public void deleteAllCustomers() {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "DELETE FROM Customer";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
