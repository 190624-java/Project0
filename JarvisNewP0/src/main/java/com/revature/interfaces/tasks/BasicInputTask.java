package com.revature.interfaces.tasks;

import java.util.LinkedList;

import com.revature.utilities.exceptions.InvalidInput;
import com.revature.utilities.exceptions.InvalidMenuSelection;

class BasicInputTask implements InputTask{
	protected final LinkedList<InputTask> tasks;
	/**
	 * A list of taskOutput that can be different types, but used differently.
	 */
	protected LinkedList<TaskOutput> taskInputDependencies;
	/**
	 * A list of task output that can be different types.
	 * This may be used by another task as a TaskInput Dependency
	 */
	protected LinkedList<TaskOutput> taskOutputList;
	protected final String taskInstruction;
	protected final String taskName;
	
	//Constructor
	public BasicInputTask(String taskName, String instruction, LinkedList<InputTask> subtasks){
		this.taskName=taskName;		
		this.taskInstruction = instruction;
		this.tasks = subtasks;
	}
	
	@Override
	public void display() {
		System.out.println(taskInstruction);
		
	}

	@Override
	public void run(Object user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getInput() throws InvalidMenuSelection, InvalidInput {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void react(Object user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LinkedList exitTask() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
