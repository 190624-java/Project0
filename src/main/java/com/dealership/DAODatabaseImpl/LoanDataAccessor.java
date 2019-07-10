package com.dealership.DAODatabaseImpl;

import java.sql.*;
import java.util.ArrayList;

import com.dealership.Customer;
import com.dealership.DealershipDriver;
import com.dealership.Loan;
import com.dealership.DAOinterface.LoanAccessor;

public class LoanDataAccessor implements LoanAccessor {

	@Override
	public void displayAllLoans() {
		try(Connection conn = ConnectionFactory.getConnection())
		{
			String sql = "SELECT loans.customer_id, loans.loan_num, "
					+ "loans.total_months, loans.remaining_months, "
					+ "loans.annual_interest_rate, loans.principal, loans.amount_payed, customers.cust_password, customers.first_name, customers.last_name"
					+ " FROM loans"
					+ " JOIN customers ON loans.customer_id = customers.cust_id";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet results = stmt.executeQuery();
			while(results.next())
			{
				Loan tmpLoan = new Loan(results.getDouble("l.principal"), results.getDouble("l.annual_interest_rate"),
						results.getInt("l.total_months"), results.getInt("l.remaining_months"), results.getDouble("l.amount_payed"),
						new Customer(results.getString("l.customer_id"), results.getString("c.cust_password"),
								results.getString("c.first_name"), results.getString("c.last_name"))
								);
				
				System.out.println("Loan holder: " + tmpLoan.loanHolder.getName() +
						" Amount Payed: " + tmpLoan.pastPayments + " Amount left: " + tmpLoan.calculateRemainingPayments());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<Loan> getLoansForCustomer(String custId) {
		try(Connection conn = ConnectionFactory.getConnection()) {
			String sql = "SELECT customer_id, loan_num, "
					+ "total_months, remaining_months, "
					+ "annual_interest_rate, principal, amount_payed"
					+ " FROM loans WHERE customer_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, custId);
			ResultSet results = stmt.executeQuery();
			ArrayList<Loan> tmpList = new ArrayList<>();
			while(results.next())
			{
				tmpList.add(
				new Loan(results.getDouble("principal"), results.getDouble("annual_interest_rate"), 
						results.getInt("total_months"), results.getInt("remaining_months"), 
						results.getDouble("amount_payed"), (Customer)DealershipDriver.activeAccount)
				);
			}
			return tmpList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
