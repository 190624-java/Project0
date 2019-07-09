package com.revature.exceptions;

public class InvalidMenuSelection extends Exception {

	String m = "Invalid menu selection";
	
	
//	int rangeStart;
//	int rangeEnd;
//	int exitCode;
//	
//	public InvalidMenuSelection(int start, int end, int exitCode){
//		this.rangeStart = start;
//		this.rangeEnd = end;
//		this.exitCode = exitCode;
//		
//	}
//	
//	public void printMessage() {
//		System.out.println(m+".\n"+"Enter a value between "+rangeStart+"and "+rangeEnd+", or the exitCode "+exitCode);
//	}
	
	public void printError() {
		System.out.println(m);
	}
	
}
