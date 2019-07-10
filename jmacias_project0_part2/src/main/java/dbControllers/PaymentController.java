package dbControllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Payment;
import dataAccessObjects.PaymentDAO;
import service.ConnectionFactory;

public class PaymentController implements PaymentDAO {

	@Override
	public Payment getPayment(int userId) {
		// TODO Auto-generated method stub
		System.out.println("Getting payment");
		return null;
	}

	@Override
	public void addPayment(Payment newPayment) {
		// 1. connection
		try (Connection conn = ConnectionFactory.getConnectionUsingProp()) {
			// 2. create the statement
			String sql = "INSERT INTO CarPayment(payments_left, no_paid, car_number, buyer_name)"
					+ "VALUES (?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, newPayment.paymentsLeft);
			stmt.setInt(2, newPayment.paid);
			stmt.setInt(3, newPayment.carNumber);
			stmt.setString(4, newPayment.buyerName);
			
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
	public List<Payment> getPayments() {
		// connection
		try (Connection conn = ConnectionFactory.getConnectionUsingProp()) {
			// statement
			String sql = "SELECT payments_left, no_paid, car_number, buyer_name"
					+ " FROM CarPayment";
			Statement stmt = conn.createStatement();
			
			// execute query
			ResultSet results = stmt.executeQuery(sql);
			System.out.println(results);
			
			// iterate through results and return 
			List<Payment> payments = new ArrayList<>();
			while (results.next()) {
				int paymentsLeft = results.getInt("payments_left");
				int amountPaid = results.getInt("no_paid");
				int carNumber = results.getInt("car_number");
				String buyerName = results.getString("buyer_name");
				Payment payment = new Payment(carNumber, buyerName);
				payments.add(payment);
			}
			
			return payments;
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Something went wrong with retrieving the payments from the db.");
			return null;
		}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("Problem with getting prop for connection.");
			return null;
		}
	}

}
