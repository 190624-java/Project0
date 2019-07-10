package com.revature.project0;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSetUp {
	private static final String url = "jdbc:oracle:thin:"
			+ "@sinhky01-project0db.czbmyzgcpgqi.us-east-2.rds.amazonaws.com"
			+ ":1521:ORCL";
	// jdbc:oracle:thin:@hostname:1521:sid
	private static final String username = "sinhart190624";
	private static final String password = "N7u3nyauZ4MS7ZP";
	
	public static Connection getConnection() throws SQLException {
		//THIS WILL BE ON THE TEST AND IN CLIENT INTERVIEWS
		return DriverManager.getConnection(url, username, password);
	}
}
