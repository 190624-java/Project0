package project0_part2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zevyirmiyahu.beans.Employee;
import com.zevyirmiyahu.daoImpl.EmployeeDAOImpl;

public class UnitTests {
	boolean running;
	// DealershipSystemDAOImpl
	@Test
	public void testRun() {
		

	}
	
	// CustomerDAOImpl
	
	// EmployeeDAOImpl
	EmployeeDAOImpl edi = new EmployeeDAOImpl();
	@Test
	public void testGetEmployees() {
		assertEquals(true, edi.getAllEmployees().get(0) instanceof Employee);
	}
	
	@Test
	public void testAddCarToLot() {
		edi.addCarToLot((byte)4, "Subaru", "WRX", "White", (short)2019, 27000, 0);
		String actual = edi.viewCarInLot((byte)4);
		assertEquals("Subaru, WRX, White", actual);
	}
	
	@Test
	public void testRemoveCar() {
		edi.removeCarFromLot((byte)4);
		String actual = edi.viewCarInLot((byte)4);
		assertEquals(null, actual);
	}
}
