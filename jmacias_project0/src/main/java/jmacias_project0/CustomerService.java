package jmacias_project0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CustomerService {
	private static InputStreamReader iReader = new InputStreamReader(System.in);
	private static BufferedReader bReader = new BufferedReader(iReader);
	
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
			List<Car> cars = CarService.getCars();
			for (Car x : cars) {
				System.out.println(x.number + ". " + x.MakeAndModel + ": $" + x.price);
			}
			System.out.println("(press return)");
			bReader.readLine();
			break;
		case "2":
			break;
		case "3":
			break;
		case "4":
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
