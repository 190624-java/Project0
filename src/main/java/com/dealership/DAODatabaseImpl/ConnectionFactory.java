package com.dealership.DAODatabaseImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	// jdbc:oracle:thin:@hostname:1521:sid
	private static final String url = "jdbc:oracle:thin:"
			+ "@jd-proj0-db.clhqmcqu3mvx.us-east-2.rds.amazonaws.com"
			+ ":1521:ORCL";
	private static final String username = "sys_acct";
	private static final String password = "r_bW4,Zl&9)*";
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
	
	/*public static Connection getConnectionFromProp() throws SQLException, IOException
	{
		Properties prop = new Properties();
		prop.load(ConnectionFactory.class.getClassLoader().getResourceAsStream("src/main/resources/db.properties"));
		return DriverManager.getConnection(url, prop);
	}
	*/
}
