package com.revature.things;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Test;

public class AccountTest {

	String s;
	String m_noLC = "No Lower Case";
	String m_noUC = "No Upper Case";
	Account acc = new Account();
	
	
	@Test
	public void testHasLowerCase_Mix() {
		s = "skwiiKSLlsl";
		assertTrue(m_noLC, acc.hasLowerCase(s));
	}
	@Test
	public void testHasLowerCase_AllUpper() {
		s = "DJWNOWKLWIWIOKLQ";
		assertTrue(m_noLC, acc.hasLowerCase(s));
	}
	@Test
	public void testAllLowerCase_AllLower() {
		s = "skwiiwjosl";
		assertTrue(m_noLC, acc.hasLowerCase(s));
	}

	@Test
	public void testHasUpperCase_Mix() {
		s = "skwiiKSLlsl";
		assertTrue(m_noUC, acc.hasUpperCase(s));
	}
	@Test
	public void testHasUpperCase_AllUpper() {
		s = "DJWNOWKLWIWIOKLQ";
		assertTrue(m_noUC, acc.hasUpperCase(s));
	}
	@Test
	public void testAllUpperCase_AllLower() {
		s = "skwiiwjosl";
		assertTrue(m_noUC, !acc.hasUpperCase(s));
	}

	

}
