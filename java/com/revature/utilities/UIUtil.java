package com.revature.utilities;

import java.util.Scanner;

/**
 * Helps with user input
 * @author Jarvis Adams
 *
 */
public class UIUtil {

	/**
	 * Asks user to try again.
	 * 
	 * @return
	 * If user answers 'y' or 'Y' then true is returned
	 * else false
	 */
	public boolean determineContinue() {
		Scanner s = new Scanner(System.in);
		String answer;
		System.out.println("Try again? y or n");
		answer = s.next();
		s.close();
		if(answer.equalsIgnoreCase("y")) return true;
		else return false;
	}
	
	public void printException(Exception e) {
		System.out.println(e.getMessage());
	}

	public void clearScreen() {
		System.out.flush();		
	}
}
