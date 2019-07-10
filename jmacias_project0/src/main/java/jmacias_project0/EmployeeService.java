package jmacias_project0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
	private static InputStreamReader iReader = new InputStreamReader(System.in);
	private static BufferedReader bReader = new BufferedReader(iReader);
	
	public static void employeeMenu(String userName) throws IOException {
		System.out.println("==========  Employee Menu  =============");
		System.out.println("  Please select the number of your choice:");
		System.out.println("1. Add a car to the lot\t\t\t"
				+ "2. Remove a car from the lot\n"
				+ "3. Review customer offers\t\t"
				+ "4. View payments\n"
				+ "5. Exit");
		String response = bReader.readLine();
		
		switch (response) {
		case "1":
			System.out.println("Enter make and model of car to add (e.g. \"AMC Gremlin\")");
			String makeModel = bReader.readLine();
			System.out.println("Enter the asking price for this " + makeModel);
			String priceStr = bReader.readLine();
			boolean intCheck;
			int price = 0;
			try {
		        Integer.parseInt(priceStr);
		        intCheck = true;
		    }
		    catch( Exception e ) {
		        intCheck = false;
		    }
			if (intCheck && priceStr != "0") {
				price = Integer.parseInt(priceStr);
			}
			else {
				System.out.println("You must enter a valid integer price (not zero)\nLet's try this again (press return)");
				bReader.readLine();
				employeeMenu(userName);
			}
			Car newCar = new Car(makeModel, price);
			CarService.addCar(newCar);
			break;
		case "2":
			System.out.println("Choose the model number of the car to remove:");
			List<Car> cars = CarService.getCars();
			for (Car x : cars) {
				System.out.println(x.number + ". " + x.makeAndModel);
			}
			String response2 = bReader.readLine();
			boolean removed = false;
			for (Car x : cars) {
				if (x.number == Integer.parseInt(response2)) {
					CarService.removeCars(x.number);
					removed = true;
					System.out.println("car " + x.number + " removed (press return)");
					bReader.readLine();
					break;
				}
			}
			if (removed) {
				
				employeeMenu(userName);
			}
			System.out.println("Please enter a valid number (press return).");
			bReader.readLine();
			employeeMenu(userName);
			break;
		case "3":
			System.out.println("Current offers on our cars:");
			List<Car> cars2 = CarService.getCars();
			boolean hasOffers = false;
			List<Integer> carsOffered = new ArrayList<>();
			for (Car x : cars2) {
				if (x.offers.isEmpty()) {
					continue;
				}
				else {
					hasOffers = true;
					System.out.println("Offer on car number " + x.number + " from:");
					carsOffered.add(x.number);
					for (String y : x.offers) {
						System.out.println(y);
						System.out.println("Would you like to accept this offer? (y or n)");
						String answer = bReader.readLine();
						if (answer.equals("y") || answer.equals("Y")) {
							Payment newPayment = new Payment(x.number, y);
							System.out.println("Car number " + x.number + " now belongs to " + y);
							PaymentService.addPayment(newPayment);
							System.out.println("(press return)");
							bReader.readLine();
							break;
						}
					}
				}
			}
			
			bReader.readLine();
			break;
		case "4":
			List<Payment> payments = PaymentService.getPayments();
			List<Car> cars3 = CarService.getCars();
			for (Payment x : payments) {
				System.out.print("Payments for car number " + x.carNumber + " made by " + x.buyerName + ": " + (30 - x.paymentsLeft));
				for (Car y : cars3) {
					if (y.number == x.carNumber) {
						System.out.println(" at " + (y.price / 30) + "$ each.");
					}
				}
			}
			System.out.println("(press return)");
			bReader.readLine();
			break;
		case "5":
			System.out.println("Goodbye.");
			System.exit(0);
		default:
			System.out.println("Please enter a valid number (press return to continue)");
			bReader.readLine();
			employeeMenu(userName);
		}
		employeeMenu(userName);
		
	}
}
