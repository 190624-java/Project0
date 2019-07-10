package com.revature.project0;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnectionHandler {
	private static final String url = "jdbc:oracle:thin:"+
			"@hardin-sql-db.ct04uaais2l7.us-east-2.rds.amazonaws.com"+
			":1521:ORCL";
	private static final String username = "t_hardin_rev_db";
	private static final String password = "190624rev_DB";
	
	public static Connection getConnection() throws SQLException {
		//THIS WILL BE ON THE TEST AND IN CLIENT INTERVIEWS
		return DriverManager.getConnection(url, username, password);
	}
	
	public static Connection getConnectionUsingProp() throws SQLException, IOException {
		Properties prop = new Properties();
		prop.load(DbConnectionHandler.class.getClassLoader().getResourceAsStream("db.properties"));
		return DriverManager.getConnection(url, prop);
	}

}
