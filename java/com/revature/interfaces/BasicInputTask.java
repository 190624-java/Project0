package com.revature.interfaces;

import java.util.LinkedList;

public class BasicInputTask implements InputTask{
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
	public InputTask(String taskName, String instruction, LinkedList<InputTask> subtasks){
		this.taskName=taskName;		
		this.taskInstruction = instruction;
		this.tasks = subtasks;
	}
	
	@Override
	public void display() {
		System.out.println(taskInstruction);
		
	}
	
}
