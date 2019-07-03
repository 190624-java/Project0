package com.dealership;

import java.io.Serializable;
import java.util.Scanner;

abstract class User implements Serializable {
	protected String id;
	protected String password;
	protected String firstName;
	protected String lastName;

	
	public boolean checkLogin(String id, String password)
	{
		if(id == this.id && password == this.password)
			return true;
		return false;
	}
	
	public String getId()
	{
		return id;
	}
	
	public void assignNewPassword()
	{
		if(this.password == null)
		{
			System.out.println("Enter a password for this user");
			try
			{
				this.password = DealershipDriver.inScan.nextLine();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		else if(this.password.isEmpty())
		{
			System.out.println("Enter a password for this user");
			try
			{
				this.password = DealershipDriver.inScan.nextLine();
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
}
