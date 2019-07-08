package com.dealership.DAOFileImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;

import com.dealership.Customer;
import com.dealership.User;
import com.dealership.DAOinterface.CustomerAccessor;

public class CustomerList extends UserList implements CustomerAccessor{

	private static final String CUSTOMER_FILE = "Customer_Data.txt";
	private static CustomerList INSTANCE;
	
	public static synchronized CustomerList getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new CustomerList();
			try{
				INSTANCE.read();
			} 
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return INSTANCE;
	}
	
	private CustomerList() {
		userList = new HashSet<User>();
	}

	public boolean addCustomer(User cust) {
		if(getInstance().containsID(cust.getId()))
		{
			return false;
		}
		else
		{
			getInstance().userList.add(cust);
			return true;
		}
	}
	
	public Customer findByID(String id) {
		for(User user: userList) {
			if(((Customer) user).getId().equals(id))
				return (Customer) user;
		}
		return null;
	}
	
	public void write() {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(CUSTOMER_FILE)))) {
			for(User customer : userList)
			{
				oos.writeObject((Customer) customer);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void read() {
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(CUSTOMER_FILE)))) {
			while(true) {
				Customer obj = (Customer)ois.readObject();
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
}
