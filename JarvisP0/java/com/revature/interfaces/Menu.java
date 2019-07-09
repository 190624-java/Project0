package com.revature.interfaces;

import com.revature.exceptions.InvalidInput;
import com.revature.exceptions.InvalidMenuSelection;

public interface Menu<T> {


	
	//print the menu of selections
	public void display();
	
	/**
	 * An infinite loop only broken with exception events like:<br/>
	 * - ExitProgram<br/>
	 * - ExitMenu <br/>
	 */
	public void run(T user);
	
	/**
	 * Gets the user's input.<br/>
	 * Ensures the input is valid int<br/>
	 * Ensure the input is one of the menu options<br/>
	 * Sets the object's "selection" field value, so 
	 * it can be used later by the react() and reset() functions.<br/>
	 * @throws InvalidMenuSelection 
	 * @throws InvalidInput 
	 */
	public void getInput() throws InvalidMenuSelection, InvalidInput;
	
	/**
	 * Implements a switch structure that has a reaction for each valid selection.<br/>
	 * If a case of the switch is a goal behavior of a user,
	 * Call the user object's goal procedure. 
	 * @param user
	 */
	public void react(T user);
	

	/**
	 * The run loop is broken by the exitMenu function.<br/>
	 * After exiting the run() while loop, the loop condition <br/>
	 * needs to be reset to true, so the next run will be successful.<br/>
	 * <br/>
	 * The selection value may need to be reset to an invalid value as well.
	 */
	public void reset();


	/**
	 * Can say that the menu is being exited.<br/>
	 * Calls reset() to prepare for another menu run loop.
	 */
	public void exitMenu();
	//
}
