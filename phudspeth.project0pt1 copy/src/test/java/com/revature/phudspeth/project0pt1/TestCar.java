package com.revature.phudspeth.project0pt1;

import java.io.IOException;
import java.lang.reflect.*;
import java.util.List;

import org.junit.Test;

public class TestCar 
{
	
	@Test
	public void test() throws ClassNotFoundException, IOException 
	{
		AppSystem SYS = AppSystem.getInstance();
		List<Car> userCars = SYS.getUserCars();
		SYS.updateUserCars(new Car("Honda","Civic", 10000));
		
	}
}
