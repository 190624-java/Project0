package com.revature.utilities;

import java.util.Scanner;

/**
 * Helps with user input
 * @author Jarvis Adams
 *
 */
public class UIUtil {

	public static Scanner s = new Scanner(System.in);
	/**
	 * Asks user to try again.
	 * 
	 * @return
	 * If user answers 'y' or 'Y' then true is returned
	 * else false
	 */
	public static boolean determineContinue() {
		String answer;		
		System.out.println("Try again? y or n");
		answer = s.next();
		if(answer.equalsIgnoreCase("y")) return true;
		else return false;
	}
	
	public static void printException(Exception e) {
		System.out.println(e.getMessage());
	}

	public static void clearScreen() {
		System.out.flush();		
	}
	
	
//	public String getCredential(String credentialName) {		
//		String c;
//		while(true){ 
//			System.out.println("Enter your "+credentialName+": ");
//			c = sr.nextLine();
//			if(passwordMatchesUser(uID, credentialName.hashCode())) {
//				sr.close();
//				return  //default driversID argument
//			}
//			else {
//				System.out.println("Error: password doesn't match");
//				if(!ui.determineContinue()) { sr.close(); return null; }
//			}
//		}//end while
//	}

//	class UserNameChecker implements Callable<String>{
//		@Override
//		public String call() throws Exception {
//			passwordMatchesUser(uID, pass.hashCode())
//			return null;
//		}		
//	}
//	class PasswordChecker implements Callable<String>{
//		
//		public PasswordChecker(){
//			
//		}
//		@Override
//		public String call() throws Exception {
//			passwordMatchesUser(uID, pass.hashCode())
//			return null;
//		}		
//	}
//	class DriversLicenseChecker implements Callable<String>{
//		int licenseID;
//		public DriversLicenseChecker(int licenseID){
//			this.licenseID = licenseID;
//		}
//		@Override
//		public String call() throws Exception {
//			
//			return null;
//		}		
//	}
	

	
}
