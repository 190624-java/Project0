package com.dealership.DAOinterface;

import java.util.ArrayList;

import com.dealership.Loan;

//TODO setup
public interface LoanAccessor {
	public void displayAllLoans();
	public ArrayList<Loan> getLoansForCustomer(String custId);
}
