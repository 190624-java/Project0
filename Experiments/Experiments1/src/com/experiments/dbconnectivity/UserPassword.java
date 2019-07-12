package com.experiments.dbconnectivity;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import oracle.jdbc.*;

public class UserPassword {

	
	//----------------------------
	//	Read
	//----------------------------
	public String getUserPasswordPS_noProfile(int driversID){

		//public  
		try(Connection conn = ConnectionFactory.getConnection()){
			//Prepare Statement
			//Method 1 - works on SQL Developer, throws exception here.
			String psql = "SELECT password FROM MyAdim.Users WHERE userDriversID = ? ";
			//Method 2 - doesn't work on SQL Developer 
//			String bsql = "SELECT password FROM Users WHERE userDriversID = " + driversID;
			//Method 1
//			String[] cols = {"userDriversID"};
//			PreparedStatement ps = conn.prepareStatement(psql,cols);
			//Method 2
			PreparedStatement ps = conn.prepareStatement(psql);
//			
//			ps.setInt(1, driversID);
			Statement s = conn.createStatement();
			
			
			//Get Resulting Data
			ResultSet rs = s.executeQuery(psql);
			//Method 1
			// - clean output
//			if(rs.next()==false) {
//				System.out.println("There was no data returned");
//				return "";
//			}
//			return rs.getString("Password");
			//Method 2
			// - shows exception
			rs.next();
			return rs.getString("Password");
			
		}catch(SQLException sqle) {
			System.out.println("Couldn't use connection!");
			//sqle.printStackTrace();
			return null;
		}
//		} catch (IOException e) {
//			System.out.println("IOException happened");
//			e.printStackTrace();
//		};
//		return null;	
	}
	
	
	public String getUserPasswordBasicStatements(int driversID){
		
		//public  
		try(Connection conn = ConnectionFactory.getConnectionUsingBasicStatements()){
			//Prepare Statement
			//Method 1 - works on SQL Developer, throws exeption here.
//			String psql = "SELECT password FROM MyAdim.Users WHERE userDriversID = ? ";
			//Method 2 - doesn't work on SQL Developer 
			String bsql = "SELECT password FROM Users WHERE userDriversID = " + driversID;
			//Method 1
//			String[] cols = {"userDriversID"};
//			PreparedStatement ps = conn.prepareStatement(psql,cols);
			//Method 2
//			PreparedStatement ps = conn.prepareStatement(psql);
//			
//			ps.setInt(1, driversID);
			Statement s = conn.createStatement();
			
			
			//Get Resulting Data
			ResultSet rs = s.executeQuery(bsql);
			//Method 1
			// - clean output
//			if(rs.next()==false) {
//				System.out.println("There was no data returned");
//				return "";
//			}
//			return rs.getString("Password");
			//Method 2
			// - shows exception
			rs.next();
			return rs.getString("Password");
			
		}catch(SQLException sqle) {
			System.out.println("Couldn't use connection!");
			sqle.printStackTrace();
		}
//		} catch (IOException e) {
//			System.out.println("IOException happened");
//			e.printStackTrace();
//		};
		return null;	
	}
	
	//TODO - REVERT
	public String getUserPasswordUsingPreparedStatement(int driversID){
		
		//public  
		try(Connection conn = ConnectionFactory.getConnectionUsingProp()){
			//Prepare Statement
			//Method 1 - works on SQL Developer, throws exeption here.
//			String psql = "SELECT password FROM MyAdim.Users WHERE userDriversID = ? ";
			//Method 2 - doesn't work on SQL Developer 
			String psql = "SELECT password FROM Users WHERE userDriversID = " + driversID;
			//Method 1
//			String[] cols = {"userDriversID"};
//			PreparedStatement ps = conn.prepareStatement(psql,cols);
			//Method 2
			PreparedStatement ps = conn.prepareStatement(psql);
			
			ps.setInt(1, driversID);
			
			//Get Resulting Data
			ResultSet rs = ps.executeQuery();
			//Method 1
			// - clean output
//			if(rs.next()==false) {
//				System.out.println("There was no data returned");
//				return "";
//			}
//			return rs.getString("Password");
			//Method 2
			// - shows exception
			rs.next();
			return rs.getString("Password");
			
		}catch(SQLException sqle) {
			System.out.println("Couldn't use connection!");
			sqle.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		} catch (IOException e) {
//			System.out.println("IOException happened");
//			e.printStackTrace();
//		};
		return null;	
	}
	
	
}
