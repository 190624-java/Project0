package com.revature.project0;

import java.io.Serializable;

public class UsersAbstract implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7081581350960988329L;
	
	protected String username;
	protected String password;
	protected int id;
	protected int employ;
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public int getId() {
		return this.id;
	}
	public int getEmploy() {
		return this.employ;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
