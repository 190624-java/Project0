package project0;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	private static final String url = "jdbc:oracle:thin:@wlachdb.csnxeuhcdr1s.us-east-2.rds.amazonaws.com:1521:ORCL";
	private static final String username = "willLach";
	private static final String password = "Database64";
	
	public static Connection getConnection() throws SQLException
	{
		//THIS WILL BE ON THE TEST AND IN CLIENT INTERVIEWS
		return DriverManager.getConnection(url, username, password);
	}
	
	public static Connection getConnectionUsingProp() throws SQLException, IOException
	{
		Properties prop = new Properties();
		prop.load(ConnectionFactory.class.getClassLoader().getResourceAsStream("db.properties"));
		return DriverManager.getConnection(url, prop);
	}
}
