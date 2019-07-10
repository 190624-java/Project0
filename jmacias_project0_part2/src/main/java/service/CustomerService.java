package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import beans.Car;
import beans.Payment;
import dataAccessObjects.CarDAO;
import dataAccessObjects.PaymentDAO;
import dbControllers.CarController;
import dbControllers.PaymentController;



class CustomerService {
	private static InputStreamReader iReader = new InputStreamReader(System.in);
	private static BufferedReader bReader = new BufferedReader(iReader);
	public static CarDAO carDAO = new CarController();
	public static PaymentDAO paymentDAO = new PaymentController();
	
	public static void customerMenu(String userName) throws IOException {
		System.out.println("=============  Customer Menu =============");
		System.out.println("  Please select the number of your choice:");
		System.out.println("1. View Cars\t\t"
				+ "2. Make an offer on a car\n"
				+ "3. View my cars\t\t"
				+ "4. View remaining payments\n"
				+ "5. Exit");
		String response = bReader.readLine();
		switch(response) {
		case "1":
			List<Car> cars = carDAO.getCars();
			for (Car x : cars) {
				System.out.println(x.number + ". " + x.makeAndModel + ": $" + x.price);
			}
			System.out.println("(press return)");
			bReader.readLine();
			break;
		case "2":
			System.out.println("Choose the number of a car to make an offer on:");
			List<Car> cars2 = carDAO.getCars();
			for (Car x : cars2) {
				System.out.println(x.number + ". " + x.makeAndModel + ": $" + x.price);
			}
			String response2 = bReader.readLine();
			for (Car x : cars2) {
				if (x.number == Integer.parseInt(response2)) {
					x.offers.add(userName);
					System.out.println("Your offer has been added to car number " + x.number);
				}
			}
			for (Car x : cars2) {
				carDAO.createOffer(x);
			}
			
			System.out.println("(press return)");
			bReader.readLine();
			break;
		case "3":
			List<Payment> payments = paymentDAO.getPayments();
			List<Car> cars3 = carDAO.getCars();
			System.out.println("Your cars:");
			for (Payment x : payments) {
				if (x.buyerName.equals(userName)) {
					for (Car y : cars3) {
						if (x.carNumber == y.number) {
							System.out.println(y.makeAndModel);
						}
					}
				}
			}
			System.out.println("\n(press return)");
			bReader.readLine();
			break;
		case "4":
			List<Payment> payments2 = paymentDAO.getPayments();
			List<Car> cars4 = carDAO.getCars();
			System.out.println("Your remaining payments:");
			for (Payment x : payments2) {
				if (x.buyerName.equals(userName)) {
					System.out.print(x.paymentsLeft + " payments remaining on ");
					for (Car y : cars4) {
						if (x.carNumber == y.number) {
							System.out.println(y.makeAndModel + " at $" + (y.price / 30) + " per payment.");
						}
					}
				}
			}
			System.out.println("\n(press return)");
			bReader.readLine();
			break;
		case "5":
			System.out.println("Goodbye.");
			System.exit(0);
		default:
			System.out.println("Please enter a valid number (press return to continue)");
			bReader.readLine();
			customerMenu(userName);
		}
		customerMenu(userName);
	}
}
