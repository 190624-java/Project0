package com.revature.DAOs;

public class CarsDAO {

	//----------------------------
	//	Create
	//----------------------------
	//addCar	
		//(final)PaymentsTableID
		//(final)RegistrationID
		//OwnerID (not necessary, because could get from PaymentsTable, but owners can change independent of payments)
		//Registration info: regID, make, model, year, color, msrp
		//lotSpaceNumber (nullable - to change owner / facilitate the remove/add car project menu requirements)

	
	
	//----------------------------
	//	Read
	//----------------------------
	//getCar (RegistrationID)
	//getUserCars (UserID)
	//getCarPayments (PK_RegID, FK_PaymentsTableID)	
	
	
	//----------------------------
	//	Update
	//----------------------------
	//updateCarInfo (PK_RegistrationID, make, model, year, color, msrp)
	//(deprecated)updateCarLot/Owner (PK_RegistrationID, LotType+UserID) //to change lots/owner of car
	// - owner/userID is sufficient for finding lots, because it is obvious that all owners would have 
	
	//(update/change)Owner 	(PK_RegID, Current(OwnerID/UserID), new(OwnerID/UserID) 
	// must change lotSpaceNumber to null since not placed in userlot yet
	// program must have way to see vehicles in limbo/transition (i.e. null lotSpaceNumber) and allow user to choose to 
	// would be good to automatically call removeCar_Soft to do so, but that is the employee's responsibility or I could make it 
	// happen automatically
	// must call addCar to 
	// - Note: this is essentially the same as changing the lot of the car, because the "remove car from lot" project requirement doesn't
	// stipulate that it is required in order to change owner.
	// - - because a car has a space in the lot
	//Note: can't update payments table, just do one at a time through the PaymentsDAO
	//park() (PK_(CarID, ownerID), newSpace)
	//(required)changeOwner() (PK_(CarID, ownerID), newSpace)
	// the only reason an employee or a customer would remove a car would be for repositioning, not for changing owner.
	// the offer would contain the spaceID of the user's lot, and if accepted would just do the swap automatically for now. 

 
	
	
	//----------------------------
	//	Delete
	//----------------------------
	//removeCar_Hard (RegID)
	//removeCar_Soft/ParkInNewLot (RegID) // would just set the lotSpaceNumber to null, so that it may be removed from lot / added to customer lot. 
}
