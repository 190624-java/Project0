package jmacias_project0;

import java.io.Serializable;

public class Payment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -119184162722322046L;
	
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
