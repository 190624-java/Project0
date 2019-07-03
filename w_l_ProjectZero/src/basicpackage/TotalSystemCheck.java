package basicpackage;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

class TotalSystemCheck {
CarSystem tester = new CarSystem();
	@Test
	void LoadFromFileTest() throws FileNotFoundException, ClassNotFoundException, IOException{
		List<Car> test = tester.loadCarsOnLot();
		List<User> userTest = tester.pleaseLetThisWork();
		List<Payment> paymentTest = tester.loadPaymentRecords();
	}
	
	@Test
	void NewCarAddTest() throws FileNotFoundException, ClassNotFoundException, IOException
	{
		tester.carsOnLot.add(new Car(3600, "Dodge", "Mustang", "Blue", 1998, true, null));
		tester.updateCarsOnLot();
		tester.loadCarsOnLot();
	}

	

}
