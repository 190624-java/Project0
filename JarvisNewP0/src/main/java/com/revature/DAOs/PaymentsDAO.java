package com.revature.DAOs;

public class PaymentsDAO {
	//----------------------------
	//	Create
	//----------------------------
	//addPayment	
	
	
	//----------------------------
	//	Read
	//----------------------------
	//getPayment (UserID, PaymentTime)
	//getUserPayments (UserID)
	
	
	//----------------------------
	//	Update
	//----------------------------
	//(No Update Allowed) must create new payment; 
		//(deprecated)updatePayment (PK_(CarID, UserID, PaymentTime) Amount)
	
	
	//----------------------------
	//	Delete
	//----------------------------
	//removePayment
	//(restricted) (PK_(CarID, UserID, PaymentTime)+(requiredAuthorization)EmployeeUserType )
	// just send the user, and this function will determine if it can or can't.
}
