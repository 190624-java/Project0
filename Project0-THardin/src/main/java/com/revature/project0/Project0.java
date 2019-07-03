package com.revature.project0;

import java.util.HashSet;
import java.util.Scanner;

public class Project0 {
	
	static int nextSerial = 1;
	
	static HashSet<UsersAbstract> userData = new HashSet<>();
	static HashSet<Offers> offers = new HashSet<>();
	static HashSet<Cars> cars = new HashSet<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileWriterReader.readUserData();
		FileWriterReader.readOfferData();
		FileWriterReader.readCarData();
		
//		cars.add(new Cars(12500, "Firebird"));
//		cars.add(new Cars(10200, "Camero"));
//		cars.add(new Cars(10000, "Mustang"));
//		cars.add(new Cars(70300, "GTO"));
//		
//		offers.add(new Offers("Joe", 11000, 2, "Camero"));
//		offers.add(new Offers("Stan", 10050, 2, "Camero"));
//		offers.add(new Offers("Mike", 11123, 2, "Camero"));
//		offers.add(new Offers("Henry", 11678, 2, "Camero"));
//		
//		userData.add(new Employee("Trey", "123"));
//		userData.add(new Customer("Henry", "456"));
//		userData.add(new Customer("Joe", "789"));
//		userData.add(new Customer("Stan", "abc"));
//		userData.add(new Customer("Mike", "xyz"));
		
		Scanner scanner = new Scanner(System.in);
		
		MenuHandler.startMenue(scanner);
		MenuHandler.userHandler(scanner);
		
		FileWriterReader.writeUserData();
		FileWriterReader.writeOfferData();
		FileWriterReader.writeCarData();
		
		scanner.close();

	}

}
