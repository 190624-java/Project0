package com.zevyirmiyahu.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.zevyirmiyahu.dao.OfferDAO;

public class OfferDAOImpl implements OfferDAO {

	@Override
	public String getCustomerUserName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte getCarId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getOfferAmount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OFFERSTATUS getOfferStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOfferStatus(String offerStatus, String userName, byte carId) {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "UPDATE Offer SET offer_status = ? WHERE customer = ? AND car_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, offerStatus);
			stmt.setString(2, userName);
			stmt.setByte(3, carId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// uses ONLY to quickly reset data in database
	public void deleteAllOffers() {
		try (Connection conn = ConnectionFactory.getConnection()) {
			String sql = "DELETE FROM Offer";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
