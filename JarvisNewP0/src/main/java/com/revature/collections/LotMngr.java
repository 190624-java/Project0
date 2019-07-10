package com.revature.collections;

import java.util.ArrayList;
import java.util.Iterator;

import com.revature.exceptions.InvalidInput;
import com.revature.exceptions.NoParkingAvailable;
import com.revature.things.Car;
import com.revature.things.Lot;
import com.revature.things.logins.Account;
import com.revature.utilities.SpacesIterator;


public class LotMngr {

	
	public LotMngr(){

	}
	
	/**
	 * For populating the lot array with empty spaces
	 * @return
	 */
	private Car createEmptyCarSpace() {
		return new Car(null, null);
	}
	
	/**
	 * Gets the empty car if a space is found, otherwise throws a NoParkingAvailable exception.
	 * 
	 * @return empty car (symbolizing an address to a car space in the lot)
	 * @throws NoParkingAvailable
	 */
	public Car findSpace(Lot lot) throws NoParkingAvailable {
		for(Car space : lot.getSpaces()) {
			if(isSpace(space)) return space; //no reg means empty car 
		}
		throw new NoParkingAvailable();
	}
	
	/**
	 * Used by findSpace() to distinguish between cars and spaces. 
	 * @param space
	 * @return
	 */
	public boolean isSpace(Car space) {
		return (space==null || !space.hasRegistration());
	}
	
	
	/**
	 * 
	 * @param id
	 * @return
	 * null if car not found
	 * Car if found
	 */
	public Car searchForCarByID(Lot lot, long id) {
		Iterator<Car> it = lot.getSpaces().iterator();
		Car c;
		while(it.hasNext()) {
			c = it.next();
			if(c==null); //continue
			else if(c.getRegID()==id) return c;			
		}
		return null;
		
	}
	
//	//TODO
//	public void addCarToLot(Car car, Lot lot) {
//		//find an empty location in the lot
//		Car space = lot.findSpace();
//		if(isSpace(space))
//			lot.parkCar(car, spaceNumber)
//	}

	
	/**	 
	 * 
	 * @param car car to place in the lot at a found car space.
	 * @return
	 * if a blank space can be found on the lot, then it will be parked and 
	 * true will be returned,
	 * else false will be returned.
	 * @throws NoParkingAvailable 
	 */
	public boolean addCar(Lot lot, Car car) throws NoParkingAvailable {
		
		//if a blank space can be found on the lot, then it will be parked and 
		Car freeSpace = this.findSpace(lot);
		//true will be returned.
		if(isSpace(freeSpace)) {
			freeSpace = car;
			return true;
		}
		//else false will be returned.
		return false;
	}
	
	
	/**
	 * 
	 * @param car
	 * @param spaceNumber
	 * @return
	 */
	public boolean addCarToSpace(Car car, Car carSpace) {
		//if the car can be parked in the space, it will return true.
		if(isSpace(carSpace)) {
			carSpace = car;
			return true;
		}
		//else return's false, indicating the parking space is full or invalid
		return false;
	}
	
//	public boolean addCarToSpace(Car car, int spaceNumber) {
//		//if the car can be parked in the space, it will return true.
//		//else return's false, indicating the parking space is full or invalid
//		return true;
//	}
	
	//TODO
	/**
	 * if there is no car in the lot, return false
	 * if is a car there { remove the car. return true }
	 * @param car
	 * @param spaceNumber
	 * @return
	 */
	public boolean removeCar(Lot lot, int spaceNumber) {
		try {
			if(lot.getSpaces().get(spaceNumber)==null) return false;
		}catch(IndexOutOfBoundsException oobe) {
			System.out.println("The lot doesn't have this parking space, try another.");
			return false;
		}
		//otherwise, a car is there, and needs to be removed.
		//it can be placed in a system temporarily
		lot.getSpaces().set(spaceNumber, null);
		return true;
	}

	public static void displayTableHeader() {
		System.out.println("    Space   CarID    Make   Model   Year   MSRP");
	}
	
	/**
	 * Prints the list of cars and their descriptions all at once
	 *  				Space   CarID    Make   Model   Year
	 *  
	 *  (deprecated)	Space   CarID    Make   Model   Year   Color   MSRP
	 *  		
	 * @param lot
	 */
	public int display(Lot lot) {
		//System.out.println("Space   CarID    Make   Model   Year   Color   MSRP");
		System.out.println("Space   CarID    Make   Model   Year   ");
		int spaceNumber=1;
		for(Car space : lot.getSpaces()) {
			System.out.print((spaceNumber++)+ "\t");
			if(space==null) System.out.println("");
			else System.out.println(
//						spaceNumber +"  "+
					space.getRegID()+"  "+
					space.getMake() +"  "+ 
					space.getModel()+"  "+ 
					space.getYear() 
					//+"  "+ 
//					space.getColor()+"  "+ 
//					space.getMSRP()
					);			
		}//end for
		return spaceNumber; //number of lot spaces/cars displayed
	}//end display
	
	/**
	 * Generates 5 menu options corresponding to particular spaces.
	 * @param spaces
	 * @return
	 */
	public static String generateMenuOptions(SpacesIterator spaces, int selection) {
		//Method1 (deprecated)-it just won't choose in later check.
		//If non-valid-selection number chosen, 
		//set selection to -1;
		//String case:
		//if(!selection.matches("[1-5]")) selection = "-1";
		//int case:
		//if(selection<0 || selection>6) selection = -1;
		
		String out = "";
		String selectionBuffer = "( ) ";
		int spaceCount = 1;
		Car space;
		while(spaces.hasNext() && spaceCount<=5) {			
			space = spaces.next();
			if(spaceCount==selection) selectionBuffer="(*) ";
//			if(spaceCount==Integer.valueOf(selection)) selectionBuffer="(*) ";
			out = out + selectionBuffer + spaces.getPosition() + spaceCount + "- " + getSpaceInfo(space);			
		}
		return out;
				
	}
	
	/**
	 * Shows data in following form
	 * Space   CarID    Make   Model   Year   Color   MSRP
	 * @param space
	 * @param selection Starting at 1, the option to select
	 * @return
	 */
	public static String getSpaceInfo(Car spaceOrCar) {
		Car c = spaceOrCar;
		if(c==null) return "empty space \n";
		//TODO - Get the spacing correct
		else return 
				String.valueOf(c.getRegID())
				+" \t"+
				String.valueOf(c.getMake())
				+" \t"+
				String.valueOf(c.getModel())
				+" \t"+
				String.valueOf(c.getYear())
				+" \t"+
				String.valueOf(c.getMSRP())
				+"\n";
	}

	public boolean removeCar(Lot lot, Car carToRemove) {
		Car c = null;
		for(int i = 0; i<lot.getSpaces().size(); i++) {
			lot.getSpaces().get(i);
			if(isInSpace(lot, carToRemove, i)) {
				lot.getSpaces().remove(i);
				return true;
			}
		}		
		return false;
	}
	
	public int findSpaceLocation(Lot lot, Car parkedCar) throws InvalidInput {
		if(parkedCar==null) throw new InvalidInput();
		
		Car c = null;
		for(int i = 0; i<lot.getSpaces().size(); i++) {
			lot.getSpaces().get(i);
			if(isInSpace(lot, parkedCar, i)) return i;
		}
		return -1; //not found
	}
	
	public boolean isInSpace(Lot lot, Car parkedCar, int space) {
		Car cOrS = lot.getSpaces().get(space);
		if(cOrS==null) return false;
		else if(cOrS.hasRegistration() && cOrS.getRegID()==parkedCar.getRegID()) return true;		
		else return false;
	}

	
}
