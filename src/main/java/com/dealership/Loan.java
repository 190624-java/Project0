package com.dealership;

import java.io.Serializable;

public class Loan implements Serializable {

	public Customer loanHolder;
	private int totalMonths;
	public int remainingMonths;
	public double annualInterestRate;
	public double principal;
	public double pastPayments;
	
	//Default loan given
	public Loan(double principal, Customer loanHolder)
	{
		new Loan(principal, 0.1, 24, loanHolder);
	}
	
	public Loan(double principal, double annualInterestRate, int totalMonths, Customer loanHolder)
	{
		this.principal = principal;
		this.annualInterestRate = annualInterestRate;
		this.totalMonths = totalMonths;
		this.remainingMonths = totalMonths;
		this.pastPayments = 0.0;
		this.loanHolder = loanHolder;
	}
	
	public void pay()
	{
		double monthly = calculateMonthlyPayment();
		pastPayments += monthly;
		remainingMonths--;
	}
	
	public double calculateMonthlyPayment()
	{
		double monthlyInterestRate = annualInterestRate/12.0;
		return principal* 
				( (monthlyInterestRate * Math.pow((1+monthlyInterestRate), totalMonths)) / 
						(Math.pow((1+monthlyInterestRate), totalMonths) - 1)
				);
	}
	
	public double calculateRemainingPayments()
	{
		return remainingMonths * calculateMonthlyPayment();
	}

	@Override
	public String toString() {
		return "Loan [loanHolder=" + loanHolder + ", totalMonths=" + totalMonths + ", remainingMonths="
				+ remainingMonths + ", annualInterestRate=" + annualInterestRate + ", principal=" + principal
				+ ", pastPayments=" + pastPayments + "]";
	}
	
	
}
