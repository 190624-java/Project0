package com.revature.project0;

import static org.junit.Assert.*;
import java.util.HashSet;
import org.junit.Test;

public class TestCarDealership {
	
	
	@Test
	public void testGetCustomer() {
		Offers offer = new Offers("Jeff", 15000, 1, "Kia");
		String result = offer.getCustomer();
		assertEquals("Jeff", result);
	}
	
	@Test
	public void testGetOffer() {
		Offers offer = new Offers("Jeff", 15000, 1, "Kia");
		double result = offer.getOffer();
		assertEquals(15000.00, result, 0.005);
		
	}
	
	@Test
	public void testGetSerial() {
		Offers offer = new Offers("Jeff", 15000, 1, "Kia");
		int result = offer.getSerial();
		assertEquals(1, result);
		
	}
	
	@Test
	public void testGetMake() {
		Offers offer = new Offers("Jeff", 15000, 1, "Kia");
		String result = offer.getMake();
		assertEquals("Kia", result);
		
	}

}
