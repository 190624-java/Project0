package com.revature.collections;

import java.util.ArrayList;

import com.revature.parties.User;
import com.revature.things.Car;


public class Lot {

	private ArrayList<Car> spaces;
	private int cap; //capacity of space available to hold cars
	private User owner;
	
	public Lot(int capacity, User owner){
		this.cap = capacity;
		this.owner = owner;
		this.spaces = new ArrayList(capacity);
//		for(i=0;i<capacity;i++) {
//			
//		}
	}
	
	/**
	 * For populating the lot array with empty spaces
	 * @return
	 */
	private Car createEmptyCar() {
		return new Car(null, null);
	}
	
	
	public void setOwner(User user) {
		this.owner = user;
	}
	
	
	//TODO
	public boolean parkCar(Car car) {
		
		//if a blank space can be found on the lot, then it will be parked and 
		//true will be returned.
		//else false will be returned.
		return false;
	}
	
	//TODO
	public boolean parkCar(Car car, int spaceNumber) {
		//if the car can be parked in the space, it will return true.
		//else return's false, indicating the parking space is full or invalid
		return true;
	}
	
	//TODO
	/**
	 * if there is no car in the lot, return false
	 * if is a car there { remove the car. return true }
	 * @param car
	 * @param spaceNumber
	 * @return
	 */
	public boolean removeCar(int spaceNumber) {
		try {
			if(spaces.get(spaceNumber)==null) return false;
		}catch(IndexOutOfBoundsException oobe) {
			System.out.println("The lot doesn't have this parking space, try another.");
			return false;
		}
		//otherwise, a car is there, and needs to be removed.
		//it can be placed in a system temporarilly
		spaces.set(spaceNumber, null);
		return true;
	}

	/**
	 * Prints the list of cars and their descriptions all at once
	 * @param lot
	 */
	public int display() {
		System.out.println("Space   Make   Model   Year    Color   MSRP");
		int i=1;
		for(Car space : this.spaces) {
			System.out.print((i++)+ "\t");
			if(space==null) System.out.println("");
			else System.out.println(
					space.getMake()+"  "+ 
					space.getModel()+"  "+ 
					space.getYear()+"  "+ 
//					space.getColor()+"  "+ 
					space.getMSRP()
					);			
		}//end for
		return i; //number of lot spaces/cars displayed
	}//end display
	
}
