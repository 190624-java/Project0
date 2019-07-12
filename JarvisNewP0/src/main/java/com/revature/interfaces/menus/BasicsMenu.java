package com.revature.interfaces.menus;

import com.revature.utilities.UIUtil;
import com.revature.utilities.exceptions.InvalidInput;
import com.revature.utilities.exceptions.InvalidMenuSelection;

public abstract class BasicsMenu<T> implements Menu<T>{	
	protected boolean allowLoop = true;
	protected int selection = -1;
	protected final String menuName;
	private final int maxMenuSelection;
	
	public BasicsMenu(String menuName, int maxMenuSelection){
		this.menuName=menuName;
		this.maxMenuSelection = maxMenuSelection;
	}
	
	@Override
	public void run(T user) {
		while(allowLoop) {
			display();
			try {
				getInput();
			} catch (InvalidMenuSelection | InvalidInput e) {
				((InvalidMenuSelection) e).printError();
				continue;
			} 
			react(user);
		}
		reset();
		exitMenu();
		
	}

	@Override
	public void getInput() throws InvalidMenuSelection, InvalidInput {
		selection = UIUtil.getMenuSelection();
		if(selection<0 || selection >maxMenuSelection) throw new InvalidMenuSelection();
		
	}
	
	@Override
	public void reset() {
		this.allowLoop = true;
		this.selection = -1;
	}

	@Override
	public void exitMenu() {
		System.out.println("Exiting "+menuName+" menu...");
		reset();
	}
}
