package com.digit.javaTraining.CRSApp;

import java.util.Scanner;

public class Launch {
	public static void main(String[] args) {
		System.out.println("Welcome to Digital Courses!");
		System.out.println();
		
		Admin ad = new Admin();

	}
	
	static void printMainMenu() {
		System.out.println("-----------------------------");
		System.out.println("          MAIN MENU          ");
		System.out.println("-----------------------------");
		System.out.println("1. Admin Login");
		System.out.println("2. Professor Login");
		System.out.println("3. Student Login");
		
	}
	
	static void mainMenu(Admin ad) {
		Scanner sc = new Scanner(System.in);
		printMainMenu();
		System.out.println("Select an option: ");
		int userInput = sc.nextInt();
		if(userInput==1) {
			boolean isAuth = ad.checkUsernamePassword();
		}
		
	}
}
