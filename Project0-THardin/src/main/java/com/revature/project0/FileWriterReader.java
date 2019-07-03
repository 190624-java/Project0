package com.revature.project0;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;

public class FileWriterReader {
	
	public static void writeUserData() {
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("UserData.txt"));
			oos.writeObject(Project0.userData);
			oos.close();
		}catch (IOException e) {
			e.getStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void readUserData() {
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream("UserData.txt"));
			Project0.userData = (HashSet<UsersAbstract>) ois.readObject();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void writeOfferData() {
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("OfferData.txt"));
			oos.writeObject(Project0.offers);
			oos.close();
		}catch (IOException e) {
			e.getStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void readOfferData() {
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream("OfferData.txt"));
			Project0.offers = (HashSet<Offers>) ois.readObject();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void writeCarData() {
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("CarData.txt"));
			oos.writeObject(Project0.cars);
			oos.close();
		}catch (IOException e) {
			e.getStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void readCarData() {
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream("CarData.txt"));
			Project0.cars = (HashSet<Cars>) ois.readObject();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void writeSerialData() {
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("SerialData.txt"));
			oos.write(Project0.nextSerial);
			oos.write(Offers.offerID);
			oos.close();
		}catch (IOException e) {
			e.getStackTrace();
		}
	}
	
	public static void readSerialData() {
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream("SerialData.txt"));
			Project0.nextSerial = ois.read();
			Offers.offerID = ois.read();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
