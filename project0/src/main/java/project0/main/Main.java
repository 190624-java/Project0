package project0.main;

import java.util.Scanner;

import project0.systems.CarsInLot;
import project0.systems.DealershipSystem;
import project0.systems.Serializer;
import project0.users.Employee;

public class Main {
	
	public static void main(String[] args) {
		
		//new CarsInLot(); // loads car lot			
		Scanner scanner = new Scanner(System.in);
		DealershipSystem ds = Serializer.deserialize();
		
		// initialize 
		if(ds.employeesInstance.isEmpty()) {
			System.out.println("NULL");
			ds = new DealershipSystem();
			ds.employeesInstance.put("user", new Employee("user", (short)333));
			CarsInLot cl = new CarsInLot();
			ds.carsInLotInstance = CarsInLot.carsInLot;
		}
		
		// Need to re-initialize static fields, static fields are NOT serialized
		DealershipSystem.customers = ds.customersInstance; 
		DealershipSystem.employees = ds.employeesInstance;
		DealershipSystem.offers = ds.offersInstance;
		CarsInLot.carsInLot = ds.carsInLotInstance;
		
		ds.systemStart(scanner);
		ds.customersInstance = DealershipSystem.customers; // save static fields to instance fields to serialize
		ds.employeesInstance = DealershipSystem.employees;
		ds.offersInstance = DealershipSystem.offers;
		ds.carsInLotInstance = CarsInLot.carsInLot;
		Serializer.serialize(ds);
		scanner.close();
	}
}
