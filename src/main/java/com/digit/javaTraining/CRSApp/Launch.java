package com.digit.javaTraining.CRSApp;

import java.util.Scanner;

import com.digit.javaTraining.helpers.ProfessorHelper;
import com.digit.javaTraining.helpers.StudentHelper;

public class Launch {
	public static void main(String[] args) throws Exception {
		DatabaseConnection db = new DatabaseConnection();
//		CourseHelper.showUnassignedCourse();
//		ProfessorHelper.mainLogin()df;
//		System.exit(0);

		System.out.println("Welcome to Digital Courses! ");

		Admin ad = new Admin();
		mainMenu(ad);

	}

	static void printMainMenu() {
		System.out.println();
		System.out.println("=============================");
		System.out.println("        * MAIN MENU *        ");
		System.out.println("=============================");
		System.out.println("1. Admin Login");
		System.out.println("2. Professor Login");
		System.out.println("3. Student Login");
		System.out.println("4. Exit the Application");

	}

	static public void mainMenu(Admin ad) {
		Scanner sc = new Scanner(System.in);
		printMainMenu();
		System.out.println("Select an option: ");
		int userInput = sc.nextInt();
		if (userInput == 1) {
			adminAuth(ad);

		} else if (userInput == 2) {
			ProfessorHelper.mainLogin();

		} else if (userInput == 3) {
			StudentHelper.mainLogin();
			// StudentHelper.getUserInput(ad, );
		} else if (userInput == 4) {
			System.out.println("Exiting the Application");
			System.out.println("Thanks for visiting us..");
			System.exit(0);
		} else {
			System.out.println("\033[1m\033[31mInvalid Input!\033[0m\033[0m");

			System.out.println("Please try again...");
			mainMenu(ad);
		}

	}

	static void adminAuth(Admin ad) {
		Scanner sc = new Scanner(System.in);
		boolean isAuth = ad.checkUsernamePassword();
		if (isAuth) {
			System.out.println("\033[32m\033[1mAuthenticated...\033[0m\033[0m");
			Admin.adminMenu(ad);
		} else {
			System.out.println("\033[1m\033[31mAuthentication Failed!\033[0m\033[0m");
			System.out.println();
			System.out.println("Do you want to try again? Yes/No");
			String tryAgain = sc.next();
			if (tryAgain.equalsIgnoreCase("Yes")) {
				adminAuth(ad);
			} else {
				mainMenu(ad);
			}
		}
	}

}
