package com.dealership;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/*
 * Can add or remove cars from a lot, accept or reject offers in the system, view all payments in system
 */
public class Employee extends User {
	public static final Employee SYSTEM_ACCOUNT = new Employee();
	private static transient final String SYSTEM_PW = "CtrlAltDel"; // Super secret, don't share
	private char accessLevel;
	
	/*
	 * An employee is given an id on creation. They are also given an access level
	 * 
	 * On start, System will attempt to read an employee list.
	 * If none is found, it will create an admin account for the system and prompt for a user to create an account.
	 * 
	 * Employees may be promoted from any level if they have approval by an admin or a majority of managers.
	 * 
	 */
	
	public static Employee createEmployee()
	{
		String fName = "";
		String lName = "";
		char accessLevel = 'u';
		while(true)
		{
			try{
				System.out.println("Enter your first name");
				fName = DealershipDriver.inScan.nextLine();
				System.out.println("Scanning last name");
				System.out.println("Enter your last name");
				lName = DealershipDriver.inScan.nextLine();
				System.out.println("Enter your access level. Enter A for admin, M for manager, and S for sales");
				accessLevel = DealershipDriver.inScan.next().charAt(0);
				DealershipDriver.inScan.nextLine();
			}
			finally
			{
				if(accessLevel == 'S' || accessLevel == 'M' || accessLevel == 'A')
				{
					Employee newEmp = new Employee(fName, lName, accessLevel);
					newEmp.assignNewPassword();
					return newEmp;
				}
				else
					System.out.println("Invalid access level provided");
			}
		}
	}
	
	public Employee(String firstName, String lastName, char accessLevel)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.accessLevel = accessLevel;
		this.id = generateID();
	}
	
	/**
	 * Constructor specifically for generating system account
	 */
	private Employee()
	{
		this.firstName = "System";
		this.lastName = "Account";
		this.id = "sys_acct";
		this.password = SYSTEM_PW;
		this.accessLevel = 'A';
	}
	
	/**
	 * Generates an id, not guaranteed to be unique. Must be verified when adding to an employee list
	 * @return id
	 */
	private String generateID() {
		String id = "";
		id += String.valueOf(firstName.charAt(0)) + String.valueOf(lastName.charAt(0));
		Random rand = new Random((java.time.LocalDateTime.now()).hashCode());
		for(int i = 0; i < 5; i++)
		{
			id += Integer.toString(rand.nextInt(10));
		}
		
		id += "-" + Integer.toString(1);
		System.out.println("Generated employee id: " + id);
		return id;
	}
	
	/**
	 * Given an employee list, recursively increments a unique int at end of id until a unique id exists
	 * @param theList
	 */
	public void validateAndUpdateID(EmployeeList theList, long remainingIters) {
		if(remainingIters <= 0)
		{
			throw new RuntimeException("Failed to validate and update ID after 3 billion iterations");
		}
		if(!theList.containsUser(this))
		{
			if(theList.containsID(this.getId()))
			{
				//TODO change ID as another employee already has it.
				String[] idParts = id.split("-");
				int uniquenessNum = Integer.parseInt(idParts[1]);
				uniquenessNum++;
				id = idParts[0] + "-" + uniquenessNum;
				
				validateAndUpdateID(theList, remainingIters);
			}
		}
	}
	
	/**
	 * Runs validateAndUpdateID if up to 3 billion of the base id already exist
	 * @param theList
	 */
	public void validateAndUpdateID(EmployeeList theList)
	{
		validateAndUpdateID(theList, 3_000_000_000L);
	}
	
	/**
	 * Promotes a user to the next access level
	 * @param endorsers A set of endorsing employees
	 * @param empList The employee list containing endorsers
	 */
	public final void promote(Set<Employee> endorsers, EmployeeList empList)
	{
		//System.out.println("");
		if(empList.verifyRestrictedAction(endorsers))
		{
			switch(this.accessLevel) {
			case('S'): 
				this.accessLevel = 'M';
				break;
			case('M'):
				this.accessLevel = 'A';
				break;
			default:
				System.out.println("Could not determine what to promote to");
			}
		}
	}
	
	public char getAccessLevel()
	{
		return accessLevel;
	}
	
	public boolean isManager()
	{
		if(this.getAccessLevel() == 'M')
			return true;
		else
			return false;
	}
	
	public boolean isAdmin()
	{
		if(this.getAccessLevel() == 'A')
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		return "Employee [accessLevel=" + accessLevel + ", id=" + id + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + "]";
	}

}
