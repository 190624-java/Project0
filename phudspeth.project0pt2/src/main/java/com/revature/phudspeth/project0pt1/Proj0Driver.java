package com.revature.phudspeth.project0pt1;

import java.sql.SQLException;

public class Proj0Driver {

	public static void main(String[] args) throws SQLException 
	{
		AppSystem SYS = AppSystem.getInstance();
		SYS.runFirstScreen();
	}
}
