package com.digit.javaTraining.CRSApp;

import java.util.Scanner;

import com.digit.javaTraining.helpers.CourseHelper;
import com.digit.javaTraining.helpers.ProfessorHelper;
import com.digit.javaTraining.helpers.StudentHelper;

public class Admin {
	String username;
	String password;

	public Admin() {
		this.username = "admin";
		this.password = "Admin";
	}

	public boolean checkUsernamePassword() {
		Scanner sc = new Scanner(System.in);

		System.out.println();
		System.out.println("\n---ADMIN LOGIN---");

		System.out.println("Enter Admin Username:");
		String name = sc.next();
		System.out.println("Enter Admin Password");
		String pass = sc.next();

		if (name.equals(username) && pass.equals(this.password))
			return true;
		return false;
	}

	static void printAdminMenu() {
		System.out.println();
		System.out.println("-----------------------------");
		System.out.println("        | ADMIN MENU |       ");
		System.out.println("-----------------------------");
		System.out.println("1. Add Course");
		System.out.println("2. Add Professor");
		System.out.println("3. Add Student");
		System.out.println("4. Show all Courses");
		System.out.println("5. Show all Students");
		System.out.println("6. Show all Professors");
		System.out.println("7. Goto Main Menu");
	}

	static public void adminMenu(Admin ad) {
		Scanner sc = new Scanner(System.in);
		Course c;

		printAdminMenu();
		System.out.println("Select an option:");
		int userInput = sc.nextInt();

		switch(userInput) {
		case 1:
			CourseHelper.addCourse();
			adminMenu(ad);
			break;
		case 2:
			ProfessorHelper.addProfessor();
			adminMenu(ad);
			break;
		case 3:
			StudentHelper.addStudent();
			adminMenu(ad);
			break;
		case 4:
			CourseHelper.showAllCourses();
			adminMenu(ad);
			break;
		case 5:
			StudentHelper.showAllStudent();
			adminMenu(ad);
			break;
		case 6:
			ProfessorHelper.showAllProfessor();
			adminMenu(ad);
			break;
		case 7:
			Launch.mainMenu(ad);
		default:
			System.out.println("\033[1m\033[31mInvalid Input!\033[0m\033[0m");
			System.out.println("\033[0mPlease try again...\033[1m");
			adminMenu(ad);
		}
	}

}
