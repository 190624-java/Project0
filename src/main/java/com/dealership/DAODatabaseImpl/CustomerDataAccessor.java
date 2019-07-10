package com.dealership.DAODatabaseImpl;

import java.sql.*;

import com.dealership.Customer;
import com.dealership.User;
import com.dealership.DAOinterface.CustomerAccessor;

public class CustomerDataAccessor implements CustomerAccessor {

	@Override
	public boolean containsUser(User user) {
		try(Connection conn = ConnectionFactory.getConnection())
		{
			String sql = "SELECT * FROM customers WHERE cust_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getId());
			ResultSet results = stmt.executeQuery();
			if(results.next() == false)
			{
				return false;
			}
			else
			{
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean containsID(String id) {
		try(Connection conn = ConnectionFactory.getConnection())
		{
			String sql = "SELECT * FROM customers WHERE cust_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet results = stmt.executeQuery();
			if(results.next() == false)
			{
				return false;
			}
			else
			{
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean isEmpty() {
		try(Connection conn = ConnectionFactory.getConnection())
		{
			String sql = "SELECT COUNT(*) AS total FROM customers";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet results = stmt.executeQuery();
			if(results.getInt("total") > 0)
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addCustomer(User cust) {
		try(Connection conn = ConnectionFactory.getConnection())
		{
			String sql = "INSERT INTO customers(cust_id, cust_password, first_name, last_name) VALUES(?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, cust.getId());
			stmt.setString(2, cust.getPassword());
			stmt.setString(3, cust.getFirstName());
			stmt.setString(4, cust.getLastName());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Customer findByID(String id) {
		try(Connection conn = ConnectionFactory.getConnection())
		{
			String sql = "SELECT cust_id, cust_password, first_name, last_name FROM customers WHERE cust_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet results = stmt.executeQuery();
			Customer cust = null;
			while(results.next()) {
				cust = new Customer(results.getString("cust_id"),
						results.getString("cust_password"), results.getString("first_name"), results.getString("last_name"));
				
			}
			return cust;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
