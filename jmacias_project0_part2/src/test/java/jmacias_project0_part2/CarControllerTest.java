package jmacias_project0_part2;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import beans.Car;
import dataAccessObjects.CarDAO;
import dbControllers.CarController;

public class CarControllerTest {

	

	@Test
	public void testGetCar() throws IOException {
		CarDAO carDAO = new CarController();
		Car newCar = new Car("Chevy Spectrum", 150);
		carDAO.addCar(newCar);
		Car car = carDAO.getCar(1);
		assertEquals(newCar.getMakeAndModel(), car.getMakeAndModel());
	}


	

}
