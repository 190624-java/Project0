package project0;

import static org.junit.Assert.assertEquals;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import project0.automobiles.Car;
import project0.systems.CarsInLot;
import project0.users.Customer;
import project0.users.Employee;

public class UnitTests {

	static CarsInLot carsInLot;
	Employee employee;
	Customer customer = new Customer("zev", (short)123);
	Car car;
	Scanner scanner;
	
	@Before
	public void Init() {
		scanner = new Scanner(System.in);
		carsInLot = new CarsInLot();
		employee = new Employee("user", (short)333);
		customer = new Customer("zev", (short)123);
		car = new Car((byte)4, "Ford", "Model-T", "Black", (short)1910, 200, 0);
		employee.addCarToLot((byte)4, "Ford", "Model-T", "Black", (short)1910, 200, 0);
	}

	// Employee class tests
	@Test
	public void testAddCarsToLot() {
		String actual = carsInLot.getCarsInLot().get(0).getBrand();
		assertEquals("Ford", actual);
	}
	
	@After
	public void end() {
		employee.removeCarFromLot((byte)4);
	}
	
	@Test 
	public void testRemoveCarFromLot() {
		employee.removeCarFromLot((byte)4);
		assertEquals(true, carsInLot.getCarsInLot().size() < 4);
	}
	
	// Customer class
	@Test
	public void testLogin() {
		boolean actual = customer.login("zev", (short)123);
		assertEquals(false, actual); // can't login without registering
	}
	
	
	
	
	
	

}
