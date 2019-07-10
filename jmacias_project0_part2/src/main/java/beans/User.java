package beans;


import java.io.Serializable;

public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 994585512118634100L;
	public String name;
	public String password;
	public String role;
	public User(String name, String password) {
		super();
		this.name = name;
		this.password = password;
		this.role = "user";
	}
	@Override
	public String toString() {
		
		return this.name;
		
	}
	
	public String getName() {
		return this.name;
	}
	public String getRole() {
		return this.role;
	}
	public String getPassword() {
		return this.password;
	}
	public void setRole(String newRole) {
		this.role = newRole;
	}
	
}
