package com.dealership.DAODatabaseImpl;

import java.util.HashSet;

import com.dealership.Car;
import com.dealership.Customer;
import com.dealership.Loan;
import com.dealership.Offer;
import com.dealership.DAOinterface.OfferAccessor;
import java.sql.*;

public class OfferDataAccessor implements OfferAccessor {

	@Override
	public Offer getOfferByInfo(Customer customer, Car car, double amount) {
		return new Offer(customer , car, amount);
	}

	@Override
	public void acceptOffer(Offer offer) {
		double principal = offer.car.getPrice() - offer.amount;
		Loan loan = new Loan(principal, offer.customer);
		
		try(Connection conn = ConnectionFactory.getConnection())
		{
			String sql1 = "UPDATE cars SET owner_id WHERE car_id = ?";
			PreparedStatement stmt1 = conn.prepareStatement(sql1);
			stmt1.setInt(1, offer.car.getId());
			stmt1.executeUpdate();
			
			String sql2 = "DELETE FROM offers WHERE car_id = ?";
			PreparedStatement stmt2 = conn.prepareStatement(sql2);
			stmt2.setInt(1, offer.car.getId());
			stmt2.executeUpdate();
			
			String sql3 = "INSERT INTO loans (customer_id, loan_num, total_months, remaining_months,"
					+ "annual_interest_rate, principal, amount_payed) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement stmt3 = conn.prepareStatement(sql3);
			stmt3.setString(1, offer.customer.getId());
			stmt3.setInt(2, 0);
			stmt3.setInt(3, loan.getTotalMonths());
			stmt3.setInt(4, loan.remainingMonths);
			stmt3.setDouble(5, loan.annualInterestRate);
			stmt3.setDouble(6, loan.principal);
			stmt3.setDouble(7, loan.pastPayments);
			stmt3.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void rejectOffer(Offer offer) {
		try(Connection conn = ConnectionFactory.getConnection())
		{
			String sql = "DELETE FROM offers WHERE customer_id=? AND car_id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, offer.customer.getId());
			stmt.setInt(2, offer.car.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public HashSet<Offer> getOffers() {
		return null;
	}

	@Override
	public void addOffer(Offer offer) {
		try(Connection conn = ConnectionFactory.getConnection())
		{
			String sql = "INSERT INTO offers (customer_id, car_id, offer_amount) VALUES (?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, offer.customer.getId());
			stmt.setInt(2, offer.car.getId());
			stmt.setDouble(3, offer.amount);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void displayAllOffers() {
		try(Connection conn = ConnectionFactory.getConnection())
		{
			String sql = "Select customer_id, car_id, offer_amount FROM offers";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet results = stmt.executeQuery();
			while(results.next())
			{
				System.out.println("Customer: " + results.getString("customer_id") + 
						 " Car: " + results.getInt("car_id") + " Offering: " + results.getDouble("offer_amount"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public HashSet<Offer> getOfferByCustomer(Customer customer) {
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void displayOfferesByCustomer(String customerId) {
		try(Connection conn = ConnectionFactory.getConnection())
		{
			String sql = "Select customer_id, car_id, offer_amount FROM offers WHERE customer_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, customerId);
			ResultSet results = stmt.executeQuery();
			while(results.next())
			{
				System.out.println("Car: " + results.getInt("car_id") + " Offering: " + results.getDouble("offer_amount"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Offer getOfferByCustCar(String custId, int carId) {
		// TODO Auto-generated method stub
		return null;
	}

}
