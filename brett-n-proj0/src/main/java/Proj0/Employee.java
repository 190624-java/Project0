package Proj0;

public class Employee implements User {

	CarLot cL = new CarLot();

	public String empUserName;
	private String empPassword;
	private String empID;

	public Employee(String empUserName, String empPassword, String empID) {
		super();
		this.empUserName = empUserName;
		this.empPassword = empPassword;
		this.empID = empID;
	}

	public void viewCarsOnLot() {

	}

	public void addCarToLot() {
		
		
		
	}

	public void removeCarFromLot() {

	}

	public void acceptOffer() {

	}

	public void rejectOffer() {

	}

	public void registerCustomerAccount() {

	}

	public void viewOwnedCars() {

	}

	public void viewAllPayments() {

	}

	public void viewRemainingPayments() {

	}

	public String getEmpUserName() {
		return empUserName;
	}

	public void setEmpUserName(String empUserName) {
		this.empUserName = empUserName;
	}

	public String getEmpPassword() {
		return empPassword;
	}

	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

}