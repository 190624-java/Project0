package com.revature.things;

import com.revature.exceptions.NoUppercase;

public class Password {

	private int acceptedHashedPassword;
	
	//TODO throw NoLowercase, NoNumeral
	public Password(String attemptedName) throws NoUppercase {
		if(!hasUppercase(attemptedName)) throw new NoUppercase();
	}
	
	public static boolean hasLowercase(String pass) {
		return pass.matches("[.]*[a-z][.]*");
	}
	
	public static boolean hasUppercase(String pass) {
		return pass.matches("[.]*[A-Z][.]*");
	}
	
	private static int hashPassword(String pass) {
		return pass.hashCode();
	}
}
