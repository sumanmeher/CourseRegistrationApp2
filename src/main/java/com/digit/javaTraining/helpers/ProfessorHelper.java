package com.digit.javaTraining.helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.digit.javaTraining.CRSApp.Admin;
import com.digit.javaTraining.CRSApp.DatabaseConnection;
import com.digit.javaTraining.CRSApp.Launch;
import com.digit.javaTraining.CRSApp.Professor;

public class ProfessorHelper {
	static Connection con = DatabaseConnection.con;

	public static void addProfessor() {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println();
			System.out.println("\n---ADD PROFESSOR---");
			System.out.println("Enter the name of the Professor");
			String name = sc.nextLine();
			System.out.println("Enter the username:");
			String userName = sc.next();
			System.out.println("Enter the Password");
			String pass = sc.next();
			System.out.println("Enter the Age:");
			int age = sc.nextInt();
			String sql = "insert into professor (p_username, p_name, p_password, p_age) values(?,?,?,?)";

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, name);
			pstmt.setString(3, pass);
			pstmt.setInt(4, age);
			int x = pstmt.executeUpdate();

			System.out.println("\033[32m\033[1mProfessor Added Successfully...\033[0m\033[0m");
			assignProfessorMain(userName);
			System.out.println("Do you want to add more Professor (yes/no)");
			String tryAgain = sc.next();

			if (tryAgain.equalsIgnoreCase("Yes")) {
				addProfessor();
			} else {
				Admin.adminMenu();
			}
		} catch (InputMismatchException e) {
			System.out.println("\033[1m\033[31mWrong Data Inserted!...\033[0m\033[0m");

			System.out.println("\033[0mPlease try again...\033[1m");
			addProfessor();
		} catch (SQLException e) {
			System.out.println("\033[1m\033[31mDatabase Error Occured!...\033[0m\033[0m");
			System.out.println("Redirecting to Main Menu...");
			Admin.adminMenu();
		} catch (Exception e) {
			System.out.println("\033[1m\033[31mSomething Went Wrong!...\033[0m\033[0m");
			Launch.mainMenu();
		}
	}

	static public Professor login() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n---PROFESSOR LOGIN---");
		System.out.println("Enter Your Username");
		String username = sc.next();
		System.out.println("Enter Your Password");
		String pass = sc.next();
		try {
			String sql = "select * from professor where p_username = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet result = pstmt.executeQuery();
			if (!result.next()) {
				return null;
			}
			if (pass.equals(result.getString("p_password"))) {
				Professor pf = new Professor(result.getString("p_name"), result.getString("p_username"),
						result.getString("p_password"), result.getInt("p_age"), result.getString("course_id"));
				return pf;
			}
			return null;
		} catch (SQLException e) {
			System.out.println("\033[1m\033[31mDatabase Error Occured!...\033[0m\033[0m");
			System.out.println("Redirecting to Main Menu...");
			Admin.adminMenu();
		}
		return null;
	}

	public static void mainLogin() {
		Scanner sc = new Scanner(System.in);
		Professor pr = login();
		if (pr != null) {
			System.out.println("\033[32m\033[1mAuthenticated...\033[0m\033[0m");
			try {
				if (pr.getCourse_id() == null) {
					System.out.println("You are not assigned to any Course yet.");
					assignProfessorMain(pr.getUsername());

					System.out.println("Successfully Assigned the Course.");
					System.out.println("First teach the students then Login again to mark them.");
					Launch.mainMenu();
					System.exit(0);
				}

				String sql = "select * from course where c_id = ?";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, pr.getCourse_id());
				ResultSet course = pstmt.executeQuery();
				course.next();
				System.out.println("You are teaching " + course.getString("c_name") + "\n");

				System.out.println("-----------------------------");
				System.out.println("      | PROFESSOR MENU |     ");
				System.out.println("-----------------------------");

				String sql2 = "select * from student where course_id = ?";
				PreparedStatement pstmt2 = con.prepareStatement(sql2);
				pstmt2.setString(1, course.getString("c_id"));
				ResultSet studentList = pstmt2.executeQuery();
				ArrayList<String> stuIdList = new ArrayList();
				while (studentList.next()) {
					stuIdList.add(studentList.getString("s_username"));
				}

				addMarks(stuIdList);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("\033[1m\033[31mAuthentication Failed!...\033[0m\033[0m");
			System.out.println();
			System.out.println("Do you want to try again? Yes/No");
			String tryAgain = sc.next();
			if (tryAgain.equalsIgnoreCase("Yes")) {
				mainLogin();
			} else {
				Launch.mainMenu();
			}
		}

	}

	static void addMarks(ArrayList<String> stuIdList) {
		Scanner sc = new Scanner(System.in);

		if (stuIdList.size() <= 0) {
			System.out.println("\033[1m\033[31mNo student found\033[0m\033[0m");
			System.out.println("Login with another user? Yes/No");
			String tryAgain = sc.next();
			if (tryAgain.equalsIgnoreCase("Yes")) {
				mainLogin();
			} else {
				Launch.mainMenu();
			}
			return;
		}

		System.out.println("Select a student to give marks:");
		int count = 1;
		while (count <= stuIdList.size()) {
			System.out.println(count + ") " + stuIdList.get(count - 1));
			count++;
		}

		System.out.println(count + ") Goto Main Menu");

		System.out.println("select a student to mark:");
		int studentNo = sc.nextInt();

		if (studentNo == stuIdList.size() + 1) {
			Launch.mainMenu();
			System.exit(0);
		}

		String studentId = stuIdList.get(studentNo - 1);
		System.out.println("Give marks to " + studentId + ":");
		int marks = sc.nextInt();

		try {
			String sql3 = "update student set s_marks = ? where s_username = ?";
			PreparedStatement pstmt3 = con.prepareStatement(sql3);
			pstmt3.setInt(1, marks);
			pstmt3.setString(2, studentId);
			pstmt3.executeUpdate();
			System.out.println("\033[32m\033[1mMarks Updated\033[31m\033[0m");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Do you want to give mark to other student? Yes/No");
		String tryAgain = sc.next();
		if (tryAgain.equalsIgnoreCase("Yes")) {
			addMarks(stuIdList);
		} else {
			Launch.mainMenu();
		}
	}

	static public void showAllProfessor() {
		System.out.println("\033[1m---List of Professors--\033[0m");
		String sql = "select p_username, p_name, p_age from professor";
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			int count = 1;
			while (result.next()) {
				System.out.println(count++ + ") " + result.getString("p_name"));
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
			System.out.println("Name: " + result.getString("p_name"));
			System.out.println("Username: " + result.getString("p_username"));
			System.out.println("Age: " + result.getString("p_age"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static void assignProfessor(String professorId, String courseId) {
		try {
			String sql = "update course set professor_username = ? where c_id = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, professorId);
			pstmt.setString(2, courseId);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			String sql = "update professor set course_id = ? where p_username = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, courseId);
			pstmt.setString(2, professorId);
			pstmt.executeUpdate();
			System.out.println("\033[32m\033[1mCourse Assigned Successfully...\033[0m\033[0m");
			

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	static void assignProfessorMain(String professorId) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nSelect a course to Teach");

		ArrayList<String> arrList = CourseHelper.showUnassignedCourse();
		if (arrList.size() <= 0) {
			System.out.println("No course is left to assign");
			Admin.adminMenu();
		}
		System.out.println("Select a option:");
		int inp = sc.nextInt();
		assignProfessor(professorId, arrList.get(inp - 1));
	}

}
