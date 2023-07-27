package com.digit.javaTraining.helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.digit.javaTraining.CRSApp.DatabaseConnection;

public class StudentHelper {
	static Connection con = DatabaseConnection.con;

	public static void addStudent() {
		Scanner sc = new Scanner(System.in);
		System.out.println("-----------------------------");
		System.out.println("           STUDENT           ");
		System.out.println("-----------------------------");
		System.out.println("\n---Add Student---");
		System.out.println("Enter the name of the Student");
		String name = sc.next();
		System.out.println("Enter the username:");
		String userName = sc.next();
		System.out.println("Enter the Password");
		String pass = sc.next();
		System.out.println("Enter the Age:");
		int age = sc.nextInt();
		String sql = "insert into student (s_username, s_name, s_password, s_age) values(?,?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, name);
			pstmt.setString(3, pass);
			pstmt.setInt(4, age);
			int x = pstmt.executeUpdate();
			System.out.println("inserted");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static public boolean login() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n---Student Login---");
		System.out.println("Enter Your Username");
		String  username = sc.next();
		System.out.println("Enter Your Password");
		String pass = sc.next();	
		try {
			String sql = "select s_password from student where s_username = ?";
			PreparedStatement pstmt  = con.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet result = pstmt.executeQuery();
			result.next();
			if(pass.equals(result.getString("s_password"))) {
				return true;
			}return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	static public void asignCourse(String student_id, String course_id) {
		try {
			String sql = "update student set course_id = ? where s_username = ?";
			PreparedStatement pstmt  = con.prepareStatement(sql);
			pstmt.setString(1, course_id);
			pstmt.setString(2, student_id);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static public void allStudentInCourse(String course_id) {
		System.out.println("---All Student inside course--");
		
		try {
			String sql = "select s_name from student where course_id = ?";
			PreparedStatement pstmt  = con.prepareStatement(sql);
			pstmt.setString(1, course_id);
			ResultSet result = pstmt.executeQuery();
			int count=1;
			while(result.next()) {
				System.out.println(count+++") "+result.getString("s_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
