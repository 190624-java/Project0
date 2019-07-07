package com.revature.things;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PasswordTest {


	@Test
	public void testHasLowercase_oneLower() {
		String p = "ABcDEFGH";
		assertTrue(Password.hasLowercase(p));		 
	}
	@Test
	public void testHasLowercase_allUppers() {
		String p = "ABCDEF";
		assertFalse(Password.hasLowercase(p));		 
	}
	
	@Test
	public void testHasLowercase_noUppers() {
		String p = "abcdef";
		assertTrue(Password.hasLowercase(p));
	}
	

	@Test
	public void testHasUppercase_allUppers() {
		String p = "ABCDEF";
		assertTrue(Password.hasUppercase(p));		
	}
	
	@Test
	public void testHasUppercase_noUppers() {
		String p = "abcdef";
		assertFalse(Password.hasUppercase(p));
	}
	@Test
	public void testHasUppercase_oneUpper() {
		String p = "abcdEfg";
		assertTrue(Password.hasUppercase(p));
	}
	

}
