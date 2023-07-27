package com.digit.javaTraining.CRSApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	public static Connection con;

	public DatabaseConnection() {
		
		try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver Loaded");
		
		String url= "jdbc:mysql://localhost:3306/crs";
		String user = "root";
		String pwd = "Dhoni$1234";
		
		
		try {
			con = DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(con!=null) {
			System.out.println("Connection Established");
		}
		else {
			System.out.println("Connection Failed");
		}
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	}
}
