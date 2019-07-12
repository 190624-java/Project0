package com.revature.beans.things;

import java.util.ArrayList;

import com.revature.things.logins.Account;
import com.revature.utilities.SpacesIterator;

public class Lot {

	private ArrayList<Car> spaces;
	private int cap; //capacity of space available to hold cars
	private Account owner;
	
	public Lot(int capacity, Account owner){
		this.cap = capacity;
		this.owner = owner;
		this.spaces = new ArrayList<>(capacity);
//		for(i=0;i<capacity;i++) {
//			
//		}
	}

	public ArrayList<Car> getSpaces() {
		return spaces;
	}
	
	public SpacesIterator getSpacesIterator(){
		return new SpacesIterator(spaces);
	}

	public int getCap() {
		return cap;
	}

	public Account getOwner() {
		return owner;
	}

}
