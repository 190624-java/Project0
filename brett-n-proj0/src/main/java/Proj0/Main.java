package Proj0;

public class Main {

	public static void main(String[] args) {

		LotSystem sys = new LotSystem();
		// sys.Menu();
		Customer cust = new Customer();
		CarLot lot = new CarLot();
		// Fill CarLot if empty
		lot.fillCarLot();
		sys.Menu();
		sys.MenuSelection();
		// CarLot view cars
		lot.getCarsOnLot();
		// cust.viewCarsOnLot();
		cust.addCarToLot();

	}

}