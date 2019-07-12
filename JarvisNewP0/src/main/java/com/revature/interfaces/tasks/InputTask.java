package com.revature.interfaces.tasks;

import java.util.LinkedList;

import com.revature.utilities.exceptions.InvalidInput;
import com.revature.utilities.exceptions.InvalidMenuSelection;

/**
 * A potentially recursive procedure that gets input from the user, and 
 * outputs a 
 * 
 *
 * @param <T>
 */
public interface InputTask<T> {
	
	
	public void display();

	/**
	 * An infinite loop only broken with input (completion or problem) events 
	 * For feedback and repetition.
	 * Calls other functions to get the input, react to it, and reset
	 */
	public void run(T user);

	/**
	 * Gets the user's input.<br/>
	 * Ensures the input is valid int<br/>
	 * (May or may not) allow user to try multiple attempts at the input task<br/>
	 * Sets the object's "selection" field value, so 
	 * it can be used later by the react() and reset() functions.<br/>
	 * @throws InvalidMenuSelection 
	 * @throws InvalidInput 
	 */
	public void getInput() throws InvalidMenuSelection, InvalidInput;

	/**
	 * A recursive method that can calls subtasks, 
	 * until an exitMainInputTask is requested.
	 * @param user
	 */
	public void react(T user);

	
	/**
	 * Empties input dependencies and output lists 
	 */
	public void reset();

	
	/**
	 * Gets the output list formed from the task (potentially several valid inputs from the user). 
	 * @return
	 */
	public LinkedList<TaskOutput> exitTask();

}
