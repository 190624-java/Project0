package project0.main;

import java.util.Scanner;

import project0.systems.CarsInLot;
import project0.systems.DealershipSystem;

public class Main {
	
	public static void main(String[] args) {
		new CarsInLot(); // loads car lot
		Scanner scanner = new Scanner(System.in);
		DealershipSystem ds = new DealershipSystem();
		ds.systemStart(scanner);
		scanner.close();
	}
}
