package com.revature.utilities;

import java.util.ArrayList;
import java.util.Iterator;

import com.revature.beans.things.Car;

public class SpacesIterator implements Iterator<Car>{

	private ArrayList<Car> list;
	private int listPosition;
	
	public SpacesIterator(ArrayList<Car> list) {
		this.list = list;
		listPosition = 0;
	}
	
	public SpacesIterator(SpacesIterator itToCopy) {
		this.list = itToCopy.list;
		this.listPosition = itToCopy.listPosition;
	}
	
	public ArrayList<Car> getList(){
		return this.list;
	}
	public int getPosition() {
		return this.listPosition;
	}
	
	@Override
	public boolean hasNext() {
		if(listPosition<list.size()) return true;
		return false;
	}
	
	/**
	 * Returns the pointer to the index of another iterator.
	 */
	public void returnState(SpacesIterator previousStateIt) {
		this.listPosition = previousStateIt.listPosition;
	}

	@Override
	public Car next() {
		Car x = list.get(listPosition);
		listPosition++;
		return x;
	}

	
}
