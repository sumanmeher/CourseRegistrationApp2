package com.digit.javaTraining.CRSApp;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.digit.javaTraining.helpers.ProfessorHelper;
import com.digit.javaTraining.helpers.StudentHelper;

public class Launch {
	public static void main(String[] args) throws Exception {
		DatabaseConnection db = new DatabaseConnection();
		Admin ad = new Admin();
		System.out.println("Welcome to Digital Courses! ");
		mainMenu();
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

	static public void mainMenu() {
		try {
			Scanner sc = new Scanner(System.in);
			printMainMenu();
			System.out.println("Select an option: ");
			int userInput = sc.nextInt();
			if (userInput == 1) {
				adminAuth();

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

				System.out.println("\033[1mPlease try again...\033[0m");
				mainMenu();
			}
		} catch (InputMismatchException ime) {
			System.out.println("\033[1m\033[31mInvalid Input!...\033[0m\033[0m");
			System.out.println("\033[1mPlease try again...\033[0m");
			mainMenu();
		}

	}

	static void adminAuth() {
		Scanner sc = new Scanner(System.in);
		boolean isAuth = Admin.checkUsernamePassword();
		if (isAuth) {
			System.out.println("\033[32m\033[1mAuthenticated...\033[0m\033[0m");
			Admin.adminMenu();
		} else {
			System.out.println("\033[1m\033[31mAuthentication Failed!\033[0m\033[0m");
			System.out.println();
			System.out.println("Do you want to try again? Yes/No");
			String tryAgain = sc.next();
			if (tryAgain.equalsIgnoreCase("Yes")) {
				adminAuth();
			} else {
				mainMenu();
			}
		}
	}

}
