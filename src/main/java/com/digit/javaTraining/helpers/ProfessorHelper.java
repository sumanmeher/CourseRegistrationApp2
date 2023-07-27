package com.digit.javaTraining.helpers;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.digit.javaTraining.CRSApp.DatabaseConnection;

public class ProfessorHelper {
	static Connection con = DatabaseConnection.con;
	
	public static void addProfessor() {
		Scanner sc = new Scanner(System.in);
		System.out.println("-----------------------------");
		System.out.println("          PROFESSOR          ");
		System.out.println("-----------------------------");
		System.out.println("\n---Add Professor---");
		System.out.println("Enter the name of the Professor");
		String name = sc.next();
		System.out.println("Enter the username:");
		String userName= sc.next();
		System.out.println("Enter the Password");
		String pass = sc.next();
		System.out.println("Enter the Age:");
		int age = sc.nextInt();
		String sql = "insert into professor values(?,?,?,?)";
		 try {
			PreparedStatement pstmt= con.prepareStatement(sql);
			pstmt.setString(1,userName);
			pstmt.setString(2, name);
			pstmt.setString(3, pass);
			pstmt.setInt(4, age);
			int x = pstmt.executeUpdate();
			System.out.println("inserted");
		} catch (Exception e) {
			e.printStackTrace();
		}
		 System.out.println("Do you want to Add more Professors(Yes/No):");
			String userInp = sc.next();
			if (userInp.equalsIgnoreCase("yes")) {
				addProfessor();
			} else if (userInp.equalsIgnoreCase("no")) {
				return;
			} else {
				System.out.println("invalid Input");
			}
	}
	
	static public boolean login() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n---Professor Login---");
		System.out.println("Enter Your Username");
		String  username = sc.next();
		System.out.println("Enter Your Password");
		String pass = sc.next();	
		try {
			String sql = "select p_password from professor where p_username = ?";
			PreparedStatement pstmt  = con.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet result = pstmt.executeQuery();
			result.next();
			if(pass.equals(result.getString("p_password"))) {
				return true;
			}return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	static public void showAllProfessor() {
		System.out.println("---List of Professors--");
		String sql = "select p_username, p_name, p_age from professor";
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			int count=1;
			while(result.next()) {
				System.out.println(count+++") "+result.getString("p_username"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void showProfessorByUsername(String username) {
		String sql = "select p_username, p_name, p_age from professor where p_username = ?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet result = pstmt.executeQuery();
			result.next();
			System.out.println("Name: "+result.getString("p_name"));
			System.out.println("Username: "+result.getString("p_username"));
			System.out.println("Age: "+result.getString("p_age"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
