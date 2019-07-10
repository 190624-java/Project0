package com.dealership.DAOFileImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.AccessControlException;
import java.util.HashSet;
import java.util.Set;

import com.dealership.Employee;
import com.dealership.User;
import com.dealership.DAOinterface.EmployeeAccessor;

/**
 * A hashset-wrapping record of registered employees.
 * Modifying the hashset requires employees with mid to high level access
 * If no data is loaded from external source, a system employee is created
 * and used to create first user
 *
 */
public class EmployeeList extends UserList implements EmployeeAccessor{
	private static final float RESTRICTED_ACTION_RATIO = 0.5f; 
	private static final String EMPLOYEE_FILE = "Employee_Data.txt";
	private static EmployeeList INSTANCE;
	private Employee lastModifier;

	
	public static synchronized EmployeeList getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new EmployeeList();
			try{
				INSTANCE.read();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		return INSTANCE;
	}
	
	private EmployeeList() {
		userList = new HashSet<User>();
	}
	
	public Employee findByID(String id) {
		for(User emp : userList) {
			if(emp.getId() == id)
				return (Employee)emp;
		}
		return null;
	}
	/**
	 * Adds a new employee to list. Must be verified by a manager or admin.
	 * Managers may only add employees at the sales level.
	 * @param newbie The employee to add to the system.
	 * @param verifier The employee to verify addition to the system.
	 * @throws AccessControlException when verifier does not have permission for action
	 */
	public void addEmployee(Employee newbie, Employee verifier) throws AccessControlException
	{
		System.out.println("Verifier access level: " + verifier.getAccessLevel());
		if(userList.isEmpty())
		{
			userList.add(newbie); // Should only be called by system user on first startup.
			System.out.println("System added as admin");
		}
		else
		{
			if(verifier.isAdmin() || verifier.isManager()) {
				if(verifier.isManager() && newbie.getAccessLevel() != 'S')
				{
					throw new AccessControlException("Provided user (" + verifier.getId() + 
							") does not have authorization to add user (" + newbie.getId() +
							") at or above manager level.\nPlease add as sales and then promote if needed.");
				}
				if(containsUser(newbie))
					System.out.println("Cannot add employee as they are already in system");
				else
				{
					newbie.validateAndUpdateID(this);
					userList.add(newbie);
					System.out.println("Successfully added employee. ID is " + newbie.getId() +
							" and password is " + newbie.getPassword() + ". These will be needed to login, please remember them.");
				}
				
			}
			else
				throw new AccessControlException("Provided user (" + verifier.getId() + 
						") does not have authorization to add user (" + newbie.getId() + ")");
		}
	}
	
	public boolean verifyRestrictedAction(Set<Employee> endorsers) throws AccessControlException
	{
		boolean verified = false;
		short endorserCount = 0;
		float managerCount = 0f;
		for(Employee emp : endorsers)
		{
			if(!userList.contains(emp))
			{
				throw new AccessControlException("Warning: An endorser to a restricted action (" + emp.getId() + 
						") is not within the list of employees attempted to modify");
			}
			
			if(emp.isAdmin())
			{
				System.out.println("An admin has endorsed a restricted action");
				return true;
			}
			else if(emp.isManager())
			{
				endorserCount++;
			}
			else
			{
				System.out.println("Warning: A low-level employee was included as an endorser to a restricted action.");
			}
		}
		
		for(User emp : userList)
		{
			if(((Employee) emp).isManager())
			{
				managerCount++;
			}
		}
		
		if(endorserCount/managerCount > RESTRICTED_ACTION_RATIO)
			return true;
		else
			return false;
	}
	
	public void write() {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(EMPLOYEE_FILE)))) {
			for(User employee : userList)
			{
				oos.writeObject((Employee) employee);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void read() {
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(EMPLOYEE_FILE)))) {
			while(true) {
				Employee obj = (Employee)ois.readObject();
				userList.add(obj);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean nonSystemAdminExists() {
		for(User emp : userList)
		{
			if(((Employee) emp).isAdmin())
			{
				if((Employee) emp != Employee.SYSTEM_ACCOUNT)
					return true;
			}
		}
		return false;
	}
}
