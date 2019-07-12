package com.revature.beans.things;

import com.revature.utilities.exceptions.NoUppercase;

public class Password {
	//----------------------
	//	Variables
	//----------------------
	private String pText;
	private int pHash;	
	private int acceptedHashedPassword;
	
	//----------------------
	//	Constructor
	//----------------------
	//TODO throw NoLowercase, NoNumeral
	/**
	 * //TODO password requires
	 * at least 
	 * - 4 characters
	 * - 1 Uppercase
	 * - 1 Lowercase
	 * - 1 number
	 * @param attemptedName
	 * @throws NoUppercase
	 */
	public Password(String attemptedName) throws NoUppercase {
		if(!hasUppercase(attemptedName)) throw new NoUppercase();
		//if(!hasLowercase(attemptedName)) throw new NoLowercase();		
		
		else this.setPassword(attemptedName);
	}
	
	
	
	private static int encode(String pass) {
		return pass.hashCode();
	}
	
	
	//----------------------
	//	Getters and Setters
	//----------------------
	
	public void setPassword(String s) {
		this.pText = new String(s);
		this.pHash = s.hashCode();
	}
	
	
	public int getPasscode() {
		return this.pHash;
	}
	
	
	
	
	
	//----------------------
	//	Tests
	//----------------------
	
	public static boolean hasLowercase(String pass) {
		return pass.matches(".*[a-z].*");
	}
	
	
	public static boolean hasUppercase(String pass) {
		return pass.matches("(.*)[A-Z](.*)");
	}
	
	
	public boolean passwordMatches(int passHash) {
		if(this.pHash == passHash) return true;
		else return false;
	}
		
	private boolean checkPasswordStrength(String pass) {
		return Password.hasUppercase(pass);
	}
}
