package com.dealership.DAODatabaseImpl;

import java.security.AccessControlException;
import java.util.Set;

import com.dealership.Employee;
import com.dealership.User;
import com.dealership.DAOinterface.EmployeeAccessor;

public class EmployeeDataAccessor implements EmployeeAccessor {

	@Override
	public boolean containsUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsID(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addEmployee(Employee newbie, Employee verifier) throws AccessControlException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean verifyRestrictedAction(Set<Employee> endorsers) throws AccessControlException {
		// TODO Auto-generated method stub
		return false;
	}

}
