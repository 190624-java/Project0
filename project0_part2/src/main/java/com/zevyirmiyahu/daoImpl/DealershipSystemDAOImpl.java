package com.zevyirmiyahu.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.zevyirmiyahu.beans.Car;
import com.zevyirmiyahu.dao.DealershipSystemDAO;

public class DealershipSystemDAOImpl implements DealershipSystemDAO {

	private boolean running = false;
	private CustomerDAOImpl customerDAO = new CustomerDAOImpl();
	private EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
	private CarDAOImpl carDAO = new CarDAOImpl();
	private String userName;
	private short loginPin;

	private String startMessage = "----- Welcome to Dealership Systems -----" + "\nPress 1: For Registration"
			+ "\nPress 2: Login" + "\nPress 3: Exit System";

	public DealershipSystemDAOImpl() {
	}

	public DealershipSystemDAOImpl(Scanner scanner) {
		systemRun();
		systemStart(scanner);
	}

	@Override
	public void systemStart(Scanner scanner) {
		while (running) {

			System.out.println(startMessage);
			byte input = scanner.nextByte();

			switch (input) {

			// REGISTRATION
			case 1:
				System.out.println("----- Registration Menu -----");
				boolean registrationSuccessful = customerDAO.registerAccount(scanner);
				break;

			// USER LOGIN
			case 2:
				System.out.println("----- Login Menu -----");
				// Customer id
				System.out.print("User Name: ");
				this.userName = scanner.next();

				System.out.print("Pin: ");
				this.loginPin = scanner.nextShort();

				boolean employeeLoginSuccess = employeeDAO.login(userName, loginPin);
				boolean customerLoginSuccess = customerDAO.login(userName, loginPin);

				if (customerLoginSuccess)
					customerLoggedIn(scanner);
				else if (employeeLoginSuccess)
					employeeLoggedIn(scanner);
				else if (!customerLoginSuccess && !employeeLoginSuccess)
					System.out.println("No user found, check spelling or pin entered");
				break;

			// EXIT SYSTEM
			case 3:
				System.out.println("Good bye! ...exiting");
				running = false;
				break;
			default:
				System.out.println("Invalid selection, please press 1, 2, or 3");
				break;
			}
		}
	}

	@Override
	public void systemRun() {
		this.running = true;
	}

	@Override
	public void systemStop() {
		running = false;
	}

	@Override
	public void customerLoggedIn(Scanner scanner) {
		while (true) {
			System.out.println("-----Welcome Customer-----");
			System.out.println("-------------------------------");
			System.out.println("Press 1: View Cars in Lot");
			System.out.println("Press 2: Make Offer on Car");
			System.out.println("Press 3: View Cars Owned");
			System.out.println("Press 4: View Offers");
			System.out.println("Press 5: View Remaining Payments");
			System.out.println("Press 6: Return to Menu");
			System.out.println("Press 7: Exit System");
			System.out.println("-------------------------------");

			byte input = scanner.nextByte();
			switch (input) {
			case 1:
				printAllCars(); // all cars in lot
				break;
			case 2:
				System.out.print("Car ID: ");
				byte carId = scanner.nextByte();
				System.out.print("Offer (in USD): ");
				int offerAmount = scanner.nextInt(); // amount
				customerDAO.makeOffer(userName, offerAmount, carId);
				break;
			case 3:
				customerDAO.viewCarsOwned(userName);
				break;
			case 4:
				customerDAO.offerStatus(userName);
				break;
			case 5:
				customerDAO.viewRemainingPayments(userName);
				break;
			case 6:
				return; // return to main menu
			case 7:
				System.out.println("Exiting system...");
				running = false;
				return;
			default:
				System.out.println("Invalid selection, please press 1, 2, 3, 4, 5, 6 or 7");
				break;
			}
		}
	}

	@Override
	public void employeeLoggedIn(Scanner scanner) {
		byte carId;
		String brand;
		String model;
		String color;
		short year;
		int price;
		int mileage;
		while (true) {
			System.out.println("-----Welcome Employee-----");
			System.out.println("-------------------------------");
			System.out.println("Press 1: View Cars in Lot");
			System.out.println("Press 2: Add Car to Lot");
			System.out.println("Press 3: Remove Car from Lot");
			System.out.println("Press 4: Accept or Reject Offer");
			System.out.println("Press 5: View All Payments");
			System.out.println("Press 6: Return to Menu");
			System.out.println("Press 7: Exit System");
			System.out.println("----------------------------");

			byte input = scanner.nextByte();
			switch (input) {
			case 1:
				printAllCars(); // all cars in lot
				break;
			case 2:
				System.out.println("Please specify the following information about the car.");
				System.out.print("carId: ");
				carId = scanner.nextByte();
				System.out.print("Brand: ");
				brand = scanner.next();
				System.out.print("Model: ");
				model = scanner.next();
				System.out.print("Color: ");
				color = scanner.next();
				System.out.print("Year: ");
				year = scanner.nextShort();
				System.out.print("Price: ");
				price = scanner.nextInt();
				System.out.print("Mileage: ");
				mileage = scanner.nextInt();
				employeeDAO.addCarToLot(carId, brand, model, color, year, price, mileage);
				break;
			case 3:
				System.out.println("Please specify the information about the car.");
				System.out.print("carId: ");
				carId = scanner.nextByte();
				//employeeDAO.removeCarFromLot(carId);
				employeeDAO.removeCarFromLot2(carId); //uses store procedure
				break;
			case 4:
				employeeDAO.acceptOrReject(scanner);
				break;
			case 5:
				employeeDAO.viewAllRemainingPayments(userName);
				break;
			case 6:
				return; // return to main menu
			case 7:
				System.out.println("Exiting system...");
				running = false;
				return;
			default:
				System.out.println("Invalid selection, please press 1, 2, 3, 4, 5, 6 or 7");
				break;
			}
		}
	}

	private void printAllCars() {
		List<Car> cars = carDAO.getAllCars();
		int counter = 0;
		for (Car car : cars) {
			counter++;
			System.out.println("#" + counter + ": " + "\nCarID: " + car.getId() + "\nBrand: " + car.getBrand()
					+ "\nModel: " + car.getModel() + "\nColor: " + car.getColor() + "\nYear: " + car.getYear()
					+ "\nPrice: " + car.getPrice() + "\nMileage: " + car.getMileage());
		}
		if (counter == 0)
			System.out.println("No Cars in Lot");
	}

	@Override
	public void rejectPendingOffers(String userName, byte carId) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "UPDATE Offer SET offer_status = ? WHERE customer != ? AND car_id = ? AND offer_status = 'PENDING'";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "DECLINED");
			stmt.setString(2, userName);
			stmt.setByte(3, carId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void remainingPayments(String userName, byte carId, int remainingPayment) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "UPDATE Car SET remaining_price = ? WHERE owner = ? AND car_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, remainingPayment);
			stmt.setString(2, userName);
			stmt.setByte(3, carId);
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
