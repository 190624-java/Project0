package com.revature.project0;

public class TestConnection {
	public static void main(String[] args) {
		User u=new User(1,"Kyle","Sinhart","sinhky01","qwerty");
		User t=UserPersistence.getUser("sinhky01");
		System.out.println(u.toString());
		System.out.println(t.toString());
	}
}
