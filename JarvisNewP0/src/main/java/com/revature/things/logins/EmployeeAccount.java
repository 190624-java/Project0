package com.revature.things.logins;

import com.revature.exceptions.InvalidInput;
import com.revature.exceptions.InvalidMenuSelection;
import com.revature.exceptions.LogOut;
import com.revature.exceptions.UserExit;
import com.revature.main.UserTypes;

import java.util.Iterator;

import com.revature.collections.ContractMngr;
import com.revature.collections.LotMngr;
import com.revature.parties.Employee;
import com.revature.parties.User;
import com.revature.things.Car;
import com.revature.things.Offer;
import com.revature.things.Password;
import com.revature.utilities.DSystem;
import com.revature.utilities.UIUtil;

public class EmployeeAccount extends Account{

	public CoreFunctionality core;
	

	//-----------------------------
	//	Constructors
	//-----------------------------


	public EmployeeAccount(User userWithID, Password password) {
//		public EmployeeAccount(User userID, String password, Employee authorizer) {
		super(userWithID, password);
		core = new CoreFunctionality(this);
		this.type = UserTypes.EMPLOYEE;
		
	}
	
	
	//-----------------------------
	//	Methods
	//-----------------------------
	
//	private boolean isAuthorizerTheDealer(User authorizer) {
//		if(authorizer.getAccount().getAccountType()==UserTypes.DEALER);
//	}
	
	
	/**
	 * 
	 */
	@Override
	public int start() throws LogOut {
		//-------------------------------------------
		//Login
		try {
			this.setLoggedIn(true);
		} catch (LogOut e1) {
			e1.printStackTrace();
		}
		
		//-------------------------------------------
		//Display Menu, Get Selection, Test Selection
		int sel;
		while(isLoggedIn()){
			
			//Display Menu
			dSys.mPrint.employee();
			try {				
				//Get Selection
				sel = UIUtil.getMenuSelection();
				
				//Test Selection and Perform Choice
				switch(serveEmployee(sel)) {
					case 0: return 0; //user chose menu option "exit"
					case -1: break; //something went wrong
					case 1: break; //task complete
				}				
			} catch (UserExit e) { 
				UIUtil.echo("User Exited Employee Services");
				this.setLoggedIn(false); //throws LogOut
				//return 1;			
			} catch (InvalidInput e) {
				System.out.println("Invalid menu selection");
			}
		}//end while
		return 1;
		
	}
	
	
	//TODO
	/**
	 * Perform the menu interaction
	 * @throws UserExit 
	 */
	public int serveEmployee(int selection) throws UserExit {			
		
		switch(selection) {
			case 1: //Add a car to the lot	
				//Method 2 - Have User class Handle UI
				this.getUser().parkCar();
				//Method 1 - dSys.getDealershipLot().parkCar();
				
				//Method 3 - handle here
				//ask what car ID is to be added
				//react to user input in loop till correct or exit
				//find the car by ID
				//if not found, say so and exit to previous menu
				//else found, so say so and add the car to the lot
				break;
			case 2: //Accept or Reject an Offer
				//go to menu to display offers or find offers associated to a car
				//for all cars:
				Iterator<Car> carsIt = dSys.carsWithOffers.iterator();
				Car c;
				while(carsIt.hasNext()) {
					c = carsIt.next();
					((Employee)this.getUser()).coreUI.processOffers1AtATime(c);
				}
				break;
			case 3: //Remove a car from the lot
				
				break;
			case 4: //View all payments for a car
				break;
			case 0: //Exit
				throw new UserExit(); //return 0; //exit
		}	
		return -1;
		
	}
	
	@Override
	/**
	 * For credentials and main menu capabilities
	 * @return
	 */
	protected Employee getUser() {
		return (Employee)this.user;
	}
	
	
	class CoreFunctionality {	
		EmployeeAccount acc;
		public CoreFunctionality(EmployeeAccount employeeAccount) {
			this.acc = employeeAccount;
		}

	
		/**
		 * TODO
		 * @param carToRemove
		 */
		public void removeCarFromLot(Car carToRemove) {
//			System.out.println("Enter Car Registration ID");
//			long regID = UIUtil.getLong();			
			dSys.getLotManager().removeCar(dSys.getDealershipLot(),carToRemove);
		}
		
		/**
		 * TODO
		 * implemented in the Employee class
		 * @deprecated
		 * @param carToAdd
		 */
		public void addCarToLot(Car carToAdd) {
//			dSys.getLotManager().addCar(dSys.getDealershipLot(), carToAdd);
//			System.out.println("Enter Car Registration ID");
//			long regID = UIUtil.getLong();		
//			dSys.getLotManager().addCar(dSys.getDealershipLot(),carToAdd);
		}
		
		
		/**
		 * TODO
		 * @param o
		 */
		public void reject(Offer o) {
			// TODO Auto-generated method stub
			dSys.getOffersManager().removeOffer(o);		
		}

		/**
		 * TODO
		 * @param ao
		 */
		public void accept(Offer ao) {
			
			
			//create new contract based on offer			
			//Contract acceptedOfferContract = new Contract(ao);
			//attach contract to the car
		}	

		
		//TODO
		public void viewPayments() {

		}
	
	
	}//end Core class
}//end Employee Account class
