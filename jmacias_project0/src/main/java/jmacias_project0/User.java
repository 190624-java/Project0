package jmacias_project0;

import java.io.Serializable;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6291060296141556051L;
	public String name;
	public String password;
	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
		
	}
	
}
