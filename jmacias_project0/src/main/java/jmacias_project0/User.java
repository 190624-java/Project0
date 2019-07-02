package jmacias_project0;

public class User {
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
