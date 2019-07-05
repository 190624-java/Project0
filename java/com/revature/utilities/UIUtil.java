package com.revature.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Helps with user input
 * @author Jarvis Adams
 *
 */
public class UIUtil {

	public static final Scanner s = new Scanner(System.in);
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
	
	private boolean makeFileScanner(Scanner fileScanner, Scanner keyboardScanner) {
		String path;
		boolean fileNotFound = true;
		boolean tryAgain = true;
		
		do {
			path = this.getFilename(fileScanner);
			try {
				fileScanner = new Scanner(new File(path));
				if(fileScanner==null) {
					System.out.println("Error: Something went wrong with that entry.");
					tryAgain = this.willRepeat(keyboardScanner);
				}
				else return true;
			} catch (FileNotFoundException e) {
				//e.printStackTrace();
				System.out.println("Error: File not found");
				tryAgain = this.willRepeat(keyboardScanner);
			}
		}while(fileNotFound && tryAgain);
		return false;
	}
	
	/**
	 * Asks user to try again.
	 * 
	 * @return
	 * If user answers 'y' or 'Y' then true is returned
	 * else false
	 */
	public static boolean willRepeat(Scanner is) {
		String answer;		
		boolean invalidResponse = true;
		System.out.println("Try again? y or n");
		
		while(invalidResponse) {
		
			try{
				answer = is.nextLine();
				
				if(answer==null) 
					System.out.println("Invalid Response: must be y or n");//invalidResponse = true;
				else if(answer.equalsIgnoreCase("y")) 
					return true; 
					//invalideResponse = false;
				else if(answer.equalsIgnoreCase("n"))
					return false; //user wants to exit
					//invalideResponse = false;
				else System.out.println("Invalid response: Enter y or n.");
			}
			catch(Exception e) {
				System.out.println("Error: Exception Occurred: Should have been y or n.");
				//return true;
			}
		}//end while
		return true;
	}
	/**
	 * Get file path from user.
	 * 
	 * @return
	 * If user answers 'y' or 'Y' then true is returned
	 * else false
	 */
	public String getFilename(Scanner is) {
		System.out.println("Enter a filename: ");
		return ".\\src\\main\\resources\\"+is.nextLine();
		
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
