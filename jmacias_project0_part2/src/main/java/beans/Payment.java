package beans;

import java.io.Serializable;

public class Payment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6162544525817472817L;
	
	public int paymentsLeft = 30;
	public int paid = 0;
	public int carNumber;
	public String buyerName;
	public Payment(int carNumber, String buyerName) {
		super();
		this.carNumber = carNumber;
		this.buyerName = buyerName;
	}
	
}
