package com.digit.javaTraining.helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.digit.javaTraining.CRSApp.DatabaseConnection;

public class CourseHelper {
	DatabaseConnection db = new DatabaseConnection();
	static Connection con = DatabaseConnection.con;
	static PreparedStatement pstmt;
	void addCourse() {
		Scanner sc = new Scanner(System.in);
		System.out.println("-----------------------------");
		System.out.println("           COURSES           ");
		System.out.println("-----------------------------");
		System.out.println("Enter the Name of the course: ");
		String name = sc.nextLine();
		
		System.out.println("Enter the Duration of the course:  ");
		String duration = sc.nextLine();

		System.out.println("Enter the Content of the course using comma (,): ");
		String description = sc.nextLine();

		System.out.println("Enter the Price of the course: ");
		int price = sc.nextInt();

	}

}
