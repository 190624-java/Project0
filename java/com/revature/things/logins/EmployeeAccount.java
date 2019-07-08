package com.revature.things.logins;

import com.revature.collections.lots.Lot;
import com.revature.exceptions.InvalidMenuSelection;
import com.revature.exceptions.LogOut;
import com.revature.exceptions.UserExit;
import com.revature.main.UserTypes;
import com.revature.collections.ContractMngr;
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
		this.lot = new Lot(100, this.getUser());
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
				
				//Test Selection
				switch(serveEmployee(sel)) {
					case 0: return 0; //user chose menu option "exit"
					case -1: break; //something went wrong
					case 1: break; //task complete
				}				
			} catch (UserExit e) { 
				UIUtil.echo("User Exited Employee Services");
				this.setLoggedIn(false); //throws LogOut
				//return 1;
			} catch (InvalidMenuSelection e) {
				UIUtil.echoProblem("Invalid Selection");
				//if(!UIUtil.determineContinue()) return -1; //something went wrong
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
				dSys.getDealershipLot().parkCar();
				//ask what car ID is to be added
				//react to user input in loop till correct or exit
				//find the car by ID
				//if not found, say so and exit to previous menu
				//else found, so say so and add the car to the lot
				break;
			case 2: //Accept or Reject an Offer
				//go to menu to display offers or find offers associated to a car
				((Employee)this.getUser()).processOffers1AtATime(car);		
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
			System.out.println("Enter Car Registration ID");
			long regID = UIUtil.getLong();		
			//EmployeeAccount.this.account
		}
		
		/**
		 * TODO
		 * @param carToAdd
		 */
		public void addCarToLot(Car carToAdd) {
			System.out.println("Enter Car Registration ID");
			long regID = UIUtil.getLong();		
			this.account.
		}
		
		
		/**
		 * TODO
		 * @param o
		 */
		public void reject(Offer o) {
			// TODO Auto-generated method stub
			offers.remove(o);		
		}

		/**
		 * TODO
		 * @param ao
		 */
		public void accept(Offer ao) {
			//create new contract based on offer		
			Contract acceptedOfferContract = new Contract(ao);
			//attach contract to the car
		}	

		
		//TODO
		public void viewPayments() {

		}
	
	
}
