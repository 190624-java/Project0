package com.dealership.DAOinterface;

import com.dealership.Customer;
import com.dealership.User;

public interface CustomerAccessor extends UserAccessor{
	public boolean addCustomer(User cust);
	public Customer findByID(String id);
}
