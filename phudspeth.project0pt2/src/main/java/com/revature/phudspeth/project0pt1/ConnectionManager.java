package com.revature.phudspeth.project0pt1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager 
{
	private static final String url = "jdbc:oracle:thin:"//static for initilizing this.
			+ "@phud-sql-demo-db.cn3lqnky3avv.us-east-1.rds.amazonaws.com\n" + //this is the endpoint for your database specifically.
			":1521:ORCL";//this is specifically for setting up the port.
			//jdb:oracle:thin:@hostname:1521:sid
	private static final String username = "phud020719";
	private static final String password = "summarypass";//master username and password
	
	public static Connection getConnection() throws SQLException 
	//this will be on the test and in client interviews
	{
		return DriverManager.getConnection(url,username,password);
		
	}
//	private static final String URL = "jdbc:oracle:thin:@phud-sql-demo-db.cn3lqnky3avv.us-east-1.rds.amazonaws.com" + 
//			":1521:ORCL";
//	private static final String USERNAME = "phud020719";
//	private static final String PASSWORD = "summarypass";
//	
//	public static Connection getConnection() throws SQLException 
//	{
//		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
//		
//	}
}
