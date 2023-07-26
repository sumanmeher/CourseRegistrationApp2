package com.digit.javaTraining.CRSApp;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	public static Connection con;

	public static void main(String[] args) throws Exception {
		try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver Loaded");
		
		String url= "jdbc:mysql://localhost:3306/crs";
		String user = "root";
		String pwd = "admin@12345";
		
		
		con = DriverManager.getConnection(url, user, pwd);
		if(con!=null) {
			System.out.println("Connection Established");
			
		}
		else {
			System.out.println("Connection Failed");
		}
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}finally {
		//Step:6
		con.close();

	}
	}
}
