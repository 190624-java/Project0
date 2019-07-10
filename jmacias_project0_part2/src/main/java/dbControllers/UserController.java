package dbControllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Car;
import beans.User;
import dataAccessObjects.UserDAO;
import service.ConnectionFactory;

public class UserController implements UserDAO {

	@Override
	public User getUser(String userName) {
		// connection
				try (Connection conn = ConnectionFactory.getConnectionUsingProp()) {
					// statement
					String sql = "SELECT u_name, u_password, u_role"
							+ " FROM AppUser"
							+ " Where u_name = ?";
					PreparedStatement stmt = conn.prepareStatement(sql);
					stmt.setString(1, userName);
					
					// execute query
					ResultSet results = stmt.executeQuery();
					System.out.println(results);
					
					// iterate through results and return 
					User user = null;
					while (results.next()) {
						String name = results.getString("u_name");
						String password = results.getString("u_password");
						String role = results.getString("u_role");
						user = new User(name, password);
						user.role = role;
						// TODO add getter in User and use that above
					}
					return user;
				}
				catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Something went wrong with retrieving the user from the db.");
					return null;
				}
				catch(IOException e) {
					e.printStackTrace();
					System.out.println("Problem with getting prop for connection.");
					return null;
				}
	}

	@Override
	public void addUser(User newUser) {
		// 1. connection
				try (Connection conn = ConnectionFactory.getConnectionUsingProp()) {
					// 2. create the statement
					String sql = "INSERT INTO AppUser(u_name, u_password, u_role)"
							+ " VALUES (?, ?, ?)";
					// TODO also need to add table offers with fk to hold values in car object arrLst
					PreparedStatement stmt = conn.prepareStatement(sql);
					stmt.setString(1, newUser.getName());
					stmt.setString(2, newUser.getPassword());
					stmt.setString(3, newUser.getRole());
					
					// 3. Execute
					int rowsAffected = stmt.executeUpdate();
					System.out.println("Rows insterted: " + rowsAffected);
					
					// Maybe this should return the car object?
					
				}
				catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Something went wrong with creating car in db.");
					
				}
				catch (IOException e) {
					e.printStackTrace();
					System.out.println("Problem with getting prop for connection.");
				}

	}

	@Override
	public void updateUserRole(User thisUser) {
		try (Connection conn = ConnectionFactory.getConnectionUsingProp()) {
			// 2. create the statement
			String sql = "Update AppUser "
					+ "SET u_role = ?"
					+ " Where u_name = ?";
			// TODO also need to add table offers with fk to hold values in car object arrLst
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, thisUser.getRole());
			stmt.setString(2, thisUser.getName());
			
			// 3. Execute
			int rowsAffected = stmt.executeUpdate();
			System.out.println("Rows insterted: " + rowsAffected);
			
			// Maybe this should return the car object?
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Something went wrong with creating car in db.");
			
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Problem with getting prop for connection.");
		}

	}

}
