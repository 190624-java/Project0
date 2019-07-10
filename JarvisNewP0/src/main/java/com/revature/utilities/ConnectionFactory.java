package com.revature.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public ConnectionFactory(){
		
	}
	

	public Connection getConnection(String name) throws SQLException {
		//"class-and-project0.cvriicvj5pb8.us-east-2.rds.amazonaws.com";
		//jdbc syntax:
		//"jdbc:driver://hostname:port/dbName?user=userName&password=password"
		String port = "1521";
		String hostname = "class-and-project0.cvriicvj5pb8.us-east-2.rds.amazonaws.com";
		String dbName = "ORCL";
		String username = "abc";
		String password = "abcdefghij";
		//Port 1521
		String url_ds = "jdbc:driver://"
				+hostname
				+":"+port
				+"/"+dbName
				+"?user="+username
				+"&password="+password;
		if(name.equals("Dealership"));
		return DriverManager.getConnection(url_ds, "abc", "abcdefghij");
	}
	
	
}
