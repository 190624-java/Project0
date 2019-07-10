package com.dealership.DAODatabaseImpl;

import java.security.AccessControlException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import com.dealership.Employee;
import com.dealership.User;
import com.dealership.DAOinterface.EmployeeAccessor;

public class EmployeeDataAccessor implements EmployeeAccessor {

	@Override
	public boolean containsUser(User user) {
		try(Connection conn = ConnectionFactory.getConnection())
		{
			String sql = "SELECT * FROM employees WHERE emp_id = ?";
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
			String sql = "SELECT * FROM employees WHERE emp_id = ?";
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
			String sql = "SELECT COUNT(*) AS total FROM employees";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet results = stmt.executeQuery();
			results.next();
			System.out.println(results.getInt("total"));
			if(results.getInt("total") > 0)
			{
				return false;
			}
			else
			{
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}

	@Override
	public void addEmployee(Employee newbie, Employee verifier) throws AccessControlException {
		if(verifier.getAccessLevel() == 'M' || verifier.getAccessLevel() == 'A')
		{
			if(verifier.getAccessLevel() == 'M' && (newbie.getAccessLevel() == 'M' || newbie.getAccessLevel() == 'A'))
					throw new AccessControlException("Manager may not add employees above sales level");
			else {
				try(Connection conn = ConnectionFactory.getConnection())
				{
					String sql = "INSERT INTO employees (emp_id, emp_password, first_name, last_name, access_level) "
							+ "VALUES(?,?,?,?,?)";
					PreparedStatement stmt = conn.prepareStatement(sql);
					stmt.setString(1, newbie.getId());
					stmt.setString(2, newbie.getPassword());
					stmt.setString(3, newbie.getFirstName());
					stmt.setString(4, newbie.getLastName());
					stmt.setString(5, String.valueOf(newbie.getAccessLevel()));
					stmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public boolean verifyRestrictedAction(Set<Employee> endorsers) throws AccessControlException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Employee findByID(String id) {
		try(Connection conn = ConnectionFactory.getConnection())
		{
			String sql = "SELECT emp_id, emp_password, first_name, last_name, access_level FROM employees WHERE emp_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			ResultSet results = stmt.executeQuery();
			Employee emp = null;
			while(results.next()) {
				emp = new Employee(results.getString("first_name"),results.getString("last_name"),
						results.getString("access_level").charAt(0), results.getString("emp_id"));
				emp.setPassword(results.getString("emp_password"));
				
			}
			return emp;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean nonSystemAdminExists() {
		try(Connection conn = ConnectionFactory.getConnection())
		{
			String sql = "SELECT COUNT(*) AS total FROM employees WHERE access_level = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "A");
			ResultSet results = stmt.executeQuery();
			results.next();
			if(results.getInt("total") > 1)
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

}
