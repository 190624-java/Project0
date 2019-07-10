package com.revature.project0;

import java.util.Scanner;

public class Project0 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		
		MenuHandler.startMenue(scanner);
		MenuHandler.userHandler(scanner);
		
		
		scanner.close();

	}

}
