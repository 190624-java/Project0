package com.revature.collections;

import static org.junit.Assert.fail;

import java.util.LinkedHashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.things.Offer;

public class OffersTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDisplayOffers1AtATime() {
		fail("Not yet implemented");
	}

	@Test
	public void testReject_noSkippedObject() {
		LinkedHashSet<Offer> offers = new LinkedHashSet<>();
		//populate offers
		offers.add(new Offer());
		
		for(Offer o : offers) {
			
		}
		
		fail("Not yet implemented");
	}

	@Test
	public void testAccept() {
		fail("Not yet implemented");
	}

}
