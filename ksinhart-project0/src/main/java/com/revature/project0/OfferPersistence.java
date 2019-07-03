package com.revature.project0;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OfferPersistence {
public static String filename = "offer-data.ser";
	
	public static void addNewOffer(Offer offer) throws FileNotFoundException, IOException, ClassNotFoundException {
		List<Offer> records = getAllOffers();
		//Having this inside of the try was causing issues,
		//because you can't have an input stream and an output stream
		//on the same file at the same time.
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(filename),false))) {
			
			if (records != null) {
				//System.out.println("User found");
				records.add(offer);
			} else {
				records = new ArrayList<Offer>();
				records.add(offer);
			}
			oos.writeObject(records);
			System.out.println("New set written");
		}
	}
	//this breaks
	public static void removeOffer(int offerI) throws FileNotFoundException, IOException, ClassNotFoundException {
		List<Offer> records = getAllOffers();
		//Having this inside of the try was causing issues,
		//because you can't have an input stream and an output stream
		//on the same file at the same time.
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(filename),false))) {
			
			if(records.remove(offerI)!=null) {
				oos.writeObject(records);
				System.out.println("New list written");
			}
			else {
				System.out.println(records.toString());
				System.out.println("Something wrong");
			}
			/*
			if(records.remove(offer)) {
				oos.writeObject(records);
				System.out.println("New set written");
			}
			else {
				System.out.println("Something wrong");
			}
			*/
		}
	}
	
	public static void addOfferList(List<Offer> set) throws FileNotFoundException, IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename,false))) {
			oos.writeObject(set);
			System.out.println("List was rewritten");
		}
	}
	
	public static List<Offer> getAllOffers() throws IOException, ClassNotFoundException {
		List<Offer> records = null;
		try (ObjectInputStream str = new ObjectInputStream(new FileInputStream(new File("offer-data.ser")))) {
			records = (List<Offer>) str.readObject();
			//System.out.println(records);
		} catch (FileNotFoundException e) {
			return new ArrayList<Offer>();
		}
		return records;
	}
}
