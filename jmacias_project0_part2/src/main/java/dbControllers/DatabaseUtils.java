package dbControllers;

import java.io.IOException;
import java.sql.*;

import beans.Car;
import service.ConnectionFactory;

public class DatabaseUtils {
	// TODO place here miscellaneous methods to interact with the db
	// specifically:
	
	// 1. method to get highest id for cars object to allow persistent incrementing in
	//	  Car constructor
	public static int getMaxCarId() throws IOException {
		try (Connection conn = ConnectionFactory.getConnectionUsingProp()) {
		// statement
		String sql = "SELECT MAX(car_id)"
				+ " FROM Car";
		Statement stmt = conn.createStatement();
		
		// execute query
		ResultSet results = stmt.executeQuery(sql);
		System.out.println(results);
		
		// iterate through results and return 
		int carId = 0;
		while (results.next()) {
			carId = results.getInt(1);
		}
		return carId;
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Something went wrong with retrieving the id from the db.");
			return 0;
		}
	}
	// I duplicated this :-/ TODO check to see if it is used anywhere, if not delete
	public static int getPayments() throws IOException {
		try (Connection conn = ConnectionFactory.getConnectionUsingProp()) {
			// statement
			Statement stmt = null;
			String sql = "SELECT * from CarPayment";
			stmt = conn.createStatement();
			
			// execute query
			ResultSet results = stmt.executeQuery(sql);
//			System.out.println(results);
			
			
			// iterate through results and return 
			
//			try {
			while (results.next()) {
			int paymentsLeft = results.getInt("payments_left");
			int paid = results.getInt("no_paid");
			int carNumber = results.getInt("car_number");
			String buyer = results.getString("buyer_name");
			System.out.println("payments left " + paymentsLeft + " paid: " + paid + " carNumber " + carNumber + " buyer name: " + buyer);
			}
			return 0;
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Something went wrong with retrieving the payments from the db.");
			return 0;
		}
}
	
	// 2. method to do join and return offers associated with given car, to be put in
	//    arraylist within car object.
}
