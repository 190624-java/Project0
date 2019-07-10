package com.revature.project0;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public class User implements Serializable{
	private int check;//1 for customer // 2 for employee // 3 for fail case
	private String fname, lname;
	private String username;
	private String password;
	
	public User(int check, String fname, String lname, String username, String password) {
		super();
		this.check = check;
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.password = password;
	}
	
	public User(int check) {
		super();
		this.check = check;
	}
	
	//setting the default to a customer
	public User() {
		this.check=1;
	}
	
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8088328478923916326L;
	
	@Override
	public String toString() {
		return fname + " " + lname+ " " + username+ " " + password ;
	}
	
	public User confirmUser(User conUser, HashSet<User> userList) {
		String str1 = conUser.getUsername();
		String str2 = conUser.getPassword();
		for (User login : userList) {
			if(str1.equals(login.getUsername()) && str2.equals(login.getPassword())) {
				return login;
			}
		}
		return null;
	}
	
	public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}

}
