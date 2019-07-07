package com.revature.parties;

import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.exceptions.UserExit;

public class DSystemTest {

	public String acceptibleUserIDandPasswords =
			"282727\n"
			+ "testPass1\n"
			+ "testPass1\n"
			;
	
//	@Before
//	public void setUp() throws Exception {
//	}
//
	@After
	public void tearDown() throws Exception {
		separateTestOutput();
	}
	
	public void tryCreateAccount_test(String testInputSequence) {
		ByteArrayInputStream bIn = new ByteArrayInputStream( 
				(testInputSequence).getBytes() 
				);
		System.setIn(bIn);
		
		try {
			DSystem.getInstance().tryCreateAccount();
		} catch (UserExit e) {
			e.printStackTrace();
		}
		
		fail("Not yet implemented");

	}


	public void separateTestOutput() {
		separateWithNewLines();
	}
	
	public void clearScreen() {
		System.out.flush();
	}

	
	public void separateWithNewLines() {
		System.out.println("\n\n");
	}

	
	//---------------------------------------------	
	//	Login Tests
	//---------------------------------------------
//	@Test
//	public void testBeginLogin() {
//		fail("Not yet implemented");
//	}

	
	//---------------------------------------------	
	//	Entering User Type Tests
	//---------------------------------------------
	

//	@Test
	public void tryCreateAccount_test_UID_employee() {
		this.tryCreateAccount_test(acceptibleUserIDandPasswords+
				"1\n");		
	}
//	@Test
	public void tryCreateAccount_test_UID_customer() {
		this.tryCreateAccount_test(acceptibleUserIDandPasswords+
				"2\n");		
	}
	@Test
	public void tryCreateAccount_test_UID_exit() {
		this.tryCreateAccount_test(acceptibleUserIDandPasswords+
				"0\n");		
	}
	
	
	//---------------------------------------------	
	//	Entering Passwords Tests
	//---------------------------------------------
		
//	@Test
	//v0 = validity is false; v1 = true
	public void tryCreateAccount_test_PWds_P1v1P2v1() {
		this.tryCreateAccount_test(acceptibleUserIDandPasswords);		
	}
	
//	@Test
	//v0 = validity is false; v1 = true
	public void tryCreateAccount_test_PWds_P1v1P2v0() {
		this.tryCreateAccount_test("282727\n"
				+ "testPass1\n"
				+ "testPass2\n");		
	}

	
//	@Test
	public void tryCreateAccount_testWValidUnPs1_nPs2() {
		this.tryCreateAccount_test("282727\n"
				+ "testPass\n"
				+ "testP\n");		
	}

	
	//---------------------------------------------	
	//	Entering User ID Tests
	//---------------------------------------------
	
//	@Test
	public void tryCreateAccount_testWValidUsername() {
		this.tryCreateAccount_test("282727\n");		
	}

//	@Test
	public void tryCreateAccount_testWInvalidUsername() {
		//clearScreen();
		this.tryCreateAccount_test("This is it\n"+"y\n");	
	}
	
}
