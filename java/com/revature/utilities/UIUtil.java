package com.revature.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.revature.exceptions.InvalidInput;
import com.revature.exceptions.InvalidMenuSelection;

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
		do {
			System.out.println("Try again? y or n");
			answer = s.nextLine();
			if(answer.equalsIgnoreCase("y")) return true;
			if(answer.equalsIgnoreCase("n")) return false;
			System.out.println("Sorry, invalid selection");
		} while(true);
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
	
	
	public static int getMenuSelection() throws InvalidMenuSelection{
		int sel;
		try {
			sel = UIUtil.s.nextInt(); // get the menu selection
			UIUtil.s.nextLine(); //to get the newline character
			return sel;
		}
		catch(Exception e) {
			UIUtil.echoProblem("Invalid Selection");
			throw new InvalidMenuSelection();
		}
	}
	
	
	public static long getLong() throws InvalidInput {
		if(UIUtil.s.hasNextLong()) {
			long val = s.nextLong(); // get the menu selection
			s.nextLine(); //removed the newline character
			return val;
		}			
		else throw new InvalidInput("Invalid Input: should have been a long number value");
	}
	
	
//	/**
//	 * 
//	 * @param lowRange
//	 * @param highRange
//	 * @param acc
//	 * @return
//	 * @throws InvalidMenuSelection
//	 */
//	public static int getMenuSelectionUntil(int lowRange, int highRange, Account acc) throws InvalidMenuSelection{
//		try {
//			return UIUtil.s.nextInt(); // get the menu selection	
//		}
//		catch(Exception e) {
//			throw new InvalidMenuSelection();
//		}
//	}

	
	/**
	 * Gets an integer input from the user.
	 */
	public static int getInt() throws InvalidInput{
		Integer integer;
		try{
			integer = UIUtil.s.nextInt();
			UIUtil.s.nextLine(); //clear newline character from integer input
		}catch(Exception e) {
			throw new InvalidInput("Error: must be integer input");
		}
		
//		if(integer==null) throw new InvalidInput("Invalid Input: null found, must be integer value");		
		
		return integer.intValue();
	}

	
	public static void echoCompletion(String s) {
		System.out.println("Completion: " +s);
	}
	
	
	public static void echo(String s) {
		System.out.println("User input was: " + s);
	}
	

	public static void echoProblem(String string) {
		System.out.println(string);
		
	}
	
}
