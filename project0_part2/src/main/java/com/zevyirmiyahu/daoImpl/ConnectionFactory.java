package com.zevyirmiyahu.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static final String URL =  "jdbc:oracle:thin:"
			+ "@project0-db-zevy.c03ubl7onve3.us-east-2.rds.amazonaws.com" // end point of DB instance
			+ ":1521:ORCL"; // port number
	private static final String USERNAME = "master_zev1000";
	private static final String PASSWORD = "DBMIRRORme5971";
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	
	
}
