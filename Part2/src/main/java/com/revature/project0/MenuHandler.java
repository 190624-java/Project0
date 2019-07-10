package com.revature.project0;

import java.util.Scanner;

public class MenuHandler {
	
	public static UsersAbstract user;
	
	public static void startMenue(Scanner scanner) {
		int caseid = -1;
		UserTemp temp = new UserTemp();
		
		loginScreen();
	
		caseid = scanner.nextInt();
		
		do {
			if(caseid == -1) {
				loginScreen();
		
				caseid = scanner.nextInt();
			}
		
			switch(caseid){
			
			case 1: 
				UsersAbstract tempuser = temp.logIn(scanner);
				user = tempuser;
				
				if(user == null) {
					loginScreen();
			
					caseid = scanner.nextInt();
				}else {
					caseid = 3;
				}
				
				break;
				
			case 2:
				caseid = temp.register(scanner);
				user = temp.logIn(scanner);
				break;
			
			default: 
				caseid = -1;
				System.out.println("Invalid response. Please try again.");
				break;
			
		
			}
		}while(caseid == -1 || caseid == 1 || caseid == 2);
		
	}
	
	public static void userHandler(Scanner scanner) {
		int userChoice = 0;
		if(user instanceof Employee) {
			Employee tempEmploy = (Employee) user;
			do {
				employeeView();
				
				userChoice = scanner.nextInt();
				
				switch(userChoice) {
				
				case 1:
					tempEmploy.viewCars();
					break;
				
				case 2:
					tempEmploy.addCar(scanner);
					break;
				
				case 3:
					tempEmploy.removeCar(scanner);
					break;
					
				case 4:
					tempEmploy.reviewOffers(scanner);
					break;
				
				case 5:
					tempEmploy.viewPayments();
					break;
				
				case 6:
					System.out.println("Loging off. Good bye.");
					break;
					
				default:
					
					System.out.println("Invalid Input please try again.");
					userChoice = -1;
					
					break;
				}
			
			}while(userChoice != 6);
			
		}else if(user instanceof Customer) {
			Customer tempCustomer = (Customer) user;
			do {
				customerView();
				
				userChoice = scanner.nextInt();
				
				switch(userChoice) {
				case 1:
					tempCustomer.viewCars();
					break;
				
				case 2:
					tempCustomer.viewCar(scanner);
					break;
				
				case 3:
					tempCustomer.makeOffer(scanner);
					break;
					
				case 4:
					tempCustomer.viewMyCars();
					break;
				
				case 5:
					tempCustomer.viewMyPayments();
					break;
				
				case 6:
					System.out.println("Loging off. Good bye.");
					break;
					
				default:
					
					System.out.println("Invalid Input please try again.");
					userChoice = -1;
					
					break;
				}
				
			}while(userChoice != 6);
			
			
		}else {
			System.out.println("WHAT AM I!!!!!!");
		}
	}
	
	private static void employeeView() {
		System.out.println("------------------------------");
		System.out.println("1: Veiw all cars for sale");
		System.out.println("2: Add new cars");
		System.out.println("3: Remove a car");
		System.out.println("4: Review current offers");
		System.out.println("5: View all current payment");
		System.out.println("6: Exit");
		
	}
	
	private static void customerView() {
		System.out.println("------------------------------");
		System.out.println("1: Veiw all cars for sale");
		System.out.println("2: View a specific car");
		System.out.println("3: Make an offer");
		System.out.println("4: Veiw my cars");
		System.out.println("5: View my payments");
		System.out.println("6: Exit");
	}

	private static void loginScreen() {
		System.out.println("------------------------------");
		System.out.println("1: Log in");
		System.out.println("2: Register");
	}
	

}
