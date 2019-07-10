package com.revature.phudspeth.project0pt1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;


@SuppressWarnings("unchecked")
public class Registrar implements Serializable
{
	HashMap<String, String> userBase = new HashMap<>();
	
	public static final String file = "mdp.txt";
	
	{

		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(file))))
		{
			userBase = (HashMap<String, String>)ois.readObject();
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
	
	public void register(String username, String password) 
	{
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(file))))
		{
			Scanner input = new Scanner(System.in);
			if(userBase.containsKey(username + "." + password)) 
			{
				
				System.out.println("I'm sorry, a user already exists with that username.");
			}
			else if(!userBase.containsKey(username + "." + password)) 
			{
				System.out.println("1: Register as customer.");
				System.out.println("2: Register as employee.");
				String choice = input.nextLine().toString();
				switch(choice) 
				{
				case "1":
					System.out.println("Thanks for registering!");
					userBase.put(username + "." + password, "Customer");
					break;
				case "2":
					System.out.println("Please ask an administrator to put in employee registration verification code.");
					if(input.nextLine().equals("newemployee.register")) 
					{
						userBase.put(username + "." + password, "Employee");
					}
					else 
					{
						System.out.println("Incorrect verification code. Ending registration.");
					}
					break;
				case "admin":
					System.out.println("Please redirect to systems admin.");
					if(input.nextLine().equals("iwrotethiscode")) 
					{
						userBase.put(username + "." + password, "Admin");
					}
					else 
					{
						System.out.println("Incorrect verification code. Ending registration.");
					}
					break;
				}
				//userBase.put((username + "." + password) , "Admin");
			}
			oos.writeObject(userBase);
			AppSystem SYS = AppSystem.getInstance();
			SYS.addUser(userBase.get(username + "." + password), (username + "." + password).hashCode());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void login(String username, String password) 
	{
		if(userBase.containsKey(username + "." + password)) 
		{
			System.out.println("Successful Login.");
			System.out.println("Logged in as " + userBase.get((username + "." + password)));
		}
		else 
		{
			System.out.println("Login invalid.");
		}
	}
}
