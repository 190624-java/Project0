package com.dealership;

import java.io.Serializable;

abstract public class User implements Serializable {
	protected String id;
	private String password;
	protected String firstName;
	protected String lastName;

	
	public boolean checkLogin(String id, String password)
	{
		if(id.equals(this.id) && password.equals(this.getPassword()))
			return true;
		return false;
	}
	
	public String getId()
	{
		return id;
	}
	
	public void assignNewPassword()
	{
		if(this.getPassword() == null)
		{
			System.out.println("Enter a password for this user");
			try
			{
				this.setPassword(DealershipDriver.inScan.nextLine());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		else if(this.getPassword().isEmpty())
		{
			System.out.println("Enter a password for this user");
			try
			{
				this.setPassword(DealershipDriver.inScan.nextLine());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Could not assign new password as one already exists");
		}
	}
	
	public String getName() {
		return (firstName + " " + lastName);
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public void setId(String id) {
		this.id = id;
	}
}
