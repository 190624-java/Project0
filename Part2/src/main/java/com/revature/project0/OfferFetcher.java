package com.revature.project0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OfferFetcher {
	
	public Offers creatOffer(Offers offer) {

		//1. get a connection to the database
        try(Connection con = DbConnectionHandler.getConnection())
        {
            //2. Create a statement.
            String sql = "INSERT INTO Offers(customer_id, car_id, offer_amount )"
                    +"VALUES (?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, offer.getCustId());
            stmt.setInt(2, offer.getCarId());
            stmt.setDouble(3, offer.getOffer());
            
            //3. Executing the statement
            stmt.executeUpdate();
          
            return offer;
        } 
        catch (SQLException e) 
        {
            //would probably want to throw a custom application-specific exception to be handled elsewhere.
            System.out.println("Something went wrong while trying to create an offer in the database.");
            e.printStackTrace();
            return null;
        }
	}
	
	public ArrayList<Offers> getOffers(int carId){
		try(Connection conn = DbConnectionHandler.getConnection())
		{
			String sql = "SELECT * FROM Offers Where car_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, carId);
			
			ResultSet results = stmt.executeQuery();
			ArrayList<Offers> offs = new ArrayList<>();
			while(results.next())
			{
				int custId = results.getInt("customer_id");
				int car_Id = results.getInt("car_id");
				double offerAmount = results.getDouble("offer_amount");
				offs.add(new Offers(custId, offerAmount, car_Id));
			}
			
			return offs;
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public Offers getOffers(int carId, int custId){
		try(Connection conn = DbConnectionHandler.getConnection())
		{
			String sql = "SELECT * FROM Offers Where car_id = ? And customer_id = ? ";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, carId);
			stmt.setInt(2, custId);
			
			ResultSet results = stmt.executeQuery();
			Offers offs = null;
			while(results.next())
			{
				int cust_Id = results.getInt("customer_id");
				int car_Id = results.getInt("car_id");
				double offerAmount = results.getDouble("offer_amount");
				offs = new Offers(cust_Id, offerAmount, car_Id);
			}
			
			return offs;
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void removeOffers(int carId) {
		try(Connection conn = DbConnectionHandler.getConnection()){
			 String sql = "DELETE offers WHERE car_id = ?";
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 stmt.setInt(1, carId);
			 stmt.executeUpdate();
			 
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
