package com.digit.javaTraining.helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.digit.javaTraining.CRSApp.DatabaseConnection;

public class CourseHelper {
	DatabaseConnection db = new DatabaseConnection();
	static Connection con = DatabaseConnection.con;
	static PreparedStatement pstmt;

	public static void addCourse() {
		Scanner sc = new Scanner(System.in);
		System.out.println("-----------------------------");
		System.out.println("           COURSES           ");
		System.out.println("-----------------------------");
		System.out.println("Enter ID of the course: ");
		String id = sc.nextLine();

		System.out.println("Enter the Name of the course: ");
		String name = sc.nextLine();

		System.out.println("Enter the Duration of the course:  ");
		String duration = sc.nextLine();

		System.out.println("Enter the Content of the course using comma (,): ");
		String description = sc.nextLine();

		System.out.println("Enter the Price of the course: ");
		int price = sc.nextInt();

		String sql = "insert into course values(?,?,?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setInt(3, price);
			pstmt.setString(4, duration);
			pstmt.setString(5, description);

			int x = pstmt.executeUpdate();
			System.out.println("inserted");
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Id:             " + id);
		System.out.println("Name:           " + name);
		System.out.println("Price:          Rs." + price);
		System.out.println("Duration:       " + duration);
		System.out.println("Course Content: " +description);

		System.out.println();
		
		System.out.println("Do you want to Create more Courses(Yes/No):");
		String userInput = sc.next();
		if (userInput.equalsIgnoreCase("yes")) {
			addCourse();
		} else if (userInput.equalsIgnoreCase("no")) {
			return;
		} else {
			System.out.println("invalid Input");
		}
		
		

	}
	static public void showAllCourses() {
		System.out.println("---List of Courses--");
		String sql = "select C_id, C_name, Price, Duration, Description from professor";
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			int count=1;
			while(result.next()) {
				System.out.println(result.getString("c_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static void displaySpecificCourseById(String id) {
		String sql = "select C_name, Price , Duration, Description from professor where p_username = ?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet result = pstmt.executeQuery();
			result.next();
			System.out.println("Course Name: "+result.getString("C_name"));
			System.out.println("Price: "+result.getInt("Price"));
			System.out.println("Duration: "+result.getString("Duration"));
			System.out.println("Course Content: "+result.getString("Description"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
