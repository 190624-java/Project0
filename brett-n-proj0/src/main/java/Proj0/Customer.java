package Proj0;

public class Customer implements User {

	CarLot custLot = new CarLot();
	LotSystem sysLot = new LotSystem();

	public String cUserName;
	private String cPassword;

	public void viewCarsOnLot() {

	}

	public void makeOffer() {

	}

	public void viewOwnedCars() {

	}

	public void viewRemainingPayments() {

	}

	public void registerCustomerAccount() {

	}

	public void viewAllPayments() {

	}

	@Override
	public void addCarToLot() {

		System.out.println("===Please log in with your employee ID to access this field===\n");
		sysLot.Menu();
		sysLot.MenuSelection();
	}

	public void acceptOffer() {

		System.out.println("===Please log in with your employee ID to access this field===\n");

		sysLot.Menu();
		sysLot.MenuSelection();
	}

	public void rejectOffer() {

		System.out.println("===Please log in with your employee ID to access this field===\n");
		sysLot.Menu();
		sysLot.MenuSelection();
	}

	public void removeCarFromLot() {

		System.out.println("===Please log in with your employee ID to access this field===\n");
		sysLot.Menu();
		sysLot.MenuSelection();

	}

}