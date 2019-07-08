package com.dealership.DAOinterface;

import java.security.AccessControlException;
import java.util.Set;

import com.dealership.Employee;

public interface EmployeeAccessor extends UserAccessor {
	public void addEmployee(Employee newbie, Employee verifier) throws AccessControlException;
	public boolean verifyRestrictedAction(Set<Employee> endorsers) throws AccessControlException;
	
}
