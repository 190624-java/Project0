package jmacias_project0;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentService {
	
	public static List<Payment> getPayments() {
		List<Payment> payments = null;
		try(ObjectInputStream str = new ObjectInputStream(new FileInputStream(new File(".\\src\\main\\resources\\payments.txt")))) {
			payments = (List<Payment>) str.readObject();
		}
		catch (Exception e) {
			return new ArrayList<>();
		}
		return payments;
	}
	
	public static void addPayment(Payment newPayment) throws FileNotFoundException, IOException {
		List<Payment> payments = getPayments();
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(".\\src\\main\\resources\\payments.txt")))) {
			if (payments != null) {
				System.out.println("Payment data found, adding to payments.");
				payments.add(newPayment);
			}
			else {
				System.out.println("This is the first payment.");
				payments = new ArrayList<>();
				payments.add(newPayment);
			}
			oos.writeObject(payments);
		}
		System.out.println("Payments updated.");
	}
	
	public static void overwritePayments(List<Payment> payments) throws FileNotFoundException, IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(".\\src\\main\\resources\\payments.txt")))) {
			oos.writeObject(payments);
		}
	}
	
	public static void removePayment(int paymentCarNumber) throws FileNotFoundException, IOException {
		List<Payment> payments = getPayments();
		for (Payment x : payments) {
			if (x.carNumber == paymentCarNumber) {
				payments.remove(x);
				break;
			}
		}
		overwritePayments(payments);
	}
	

}
