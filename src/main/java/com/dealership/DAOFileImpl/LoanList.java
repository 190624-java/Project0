package com.dealership.DAOFileImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.dealership.Loan;

public class LoanList {
	private static LoanList instance;
	private static final String LOAN_FILE = "Loan_Data.txt";
	public ArrayList<Loan> loans;
	
	private LoanList() {
		loans = new ArrayList<Loan>();
	}
	
	public static LoanList getInstance() {
		if(instance == null)
		{
			instance = new LoanList();
			try {
				instance.read();
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	public void displayAllLoans() {
		for(Loan loan : loans) {
			System.out.println("Loan holder: " + loan.loanHolder.getName() +
					" Amount Payed: " + loan.pastPayments + " Amount left: " + loan.calculateRemainingPayments());
		}
	}
	

	public void write() {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(LOAN_FILE)))) {
			for(Loan loan : loans)
			{
				oos.writeObject(loan);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void read() {
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(LOAN_FILE)))) {
			while(true) {
				Loan obj = (Loan)ois.readObject();
				loans.add(obj);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
