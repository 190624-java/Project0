package com.revature.project0;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OfferPersistence {
public static String filename = "offer-data.ser";

	public static void addNewOfferDB(Offer offer) {
		try(Connection conn = ConnectionSetUp.getConnection()){
			String sql = "INSERT INTO offer_tab VALUES (?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, offer.getUsername());
			stmt.setString(2, offer.getLicense());
			stmt.setDouble(3, offer.getAmt());
			
			int rowsAffected = stmt.executeUpdate();
			System.out.println("Rows Inserted: " + rowsAffected);
			
			//ResultSet keys = stmt.getGeneratedKeys();
			//while (keys.next()) {
				
			//}
			//return player;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("You have already made an offer on that car");
		}
	}
	
	public static void removeOfferDB(Offer offer) {
		try(Connection conn = ConnectionSetUp.getConnection()){
			String sql = "DELETE FROM offer_tab WHERE customer = ? AND license = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, offer.getUsername());
			stmt.setString(2, offer.getLicense());
			
			int rowsAffected = stmt.executeUpdate();
			System.out.println("Rows Removed: " + rowsAffected);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Offer getOffer(String username, String license) {
		try(Connection conn = ConnectionSetUp.getConnection()){
			String sql = "SELECT * FROM offer_tab WHERE customer = ? AND license = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, license);
			
			//int rowsAffected = stmt.executeUpdate();
			//System.out.println("Rows Inserted: " + rowsAffected);
			
			ResultSet keys = stmt.executeQuery();
			double amt = 0;
			while(keys.next()) {
				amt = keys.getDouble(3);
				//return new Offer(user,lic,amt);
			}
			return new Offer(username,license,amt);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static List<Offer> getAllOffersDB(){
		try(Connection conn = ConnectionSetUp.getConnection()){
			String sql = "SELECT customer, license, amount FROM offer_tab";
			PreparedStatement stmt=conn.prepareStatement(sql);
			
			ResultSet results = stmt.executeQuery();
			List<Offer> offerList = new ArrayList<Offer>();
			while(results.next()) {
				String cust = results.getString(1);
				String lic= results.getString(2);
				double d= results.getDouble(3);
				offerList.add(new Offer(cust,lic,d));
			}
			return offerList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public static void removeOffersAfterAccept(String license){
		try(Connection conn = ConnectionSetUp.getConnection()){
			String sql = "DELETE FROM offer_tab WHERE license = ?";
			PreparedStatement stmt= conn.prepareStatement(sql);
			stmt.setString(1, license);
			
			int rowsAffected = stmt.executeUpdate();
			System.out.println("Rows Deleted: " + rowsAffected);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return null;
		
	}
	/*
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
	*/
}
