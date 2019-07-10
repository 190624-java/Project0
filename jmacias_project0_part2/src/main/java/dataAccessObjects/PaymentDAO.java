package dataAccessObjects;

import java.util.List;

import beans.Payment;

public interface PaymentDAO {
	public Payment getPayment(int userId);
	 
	public void addPayment(Payment newPayment);

	public List<Payment> getPayments();
}
