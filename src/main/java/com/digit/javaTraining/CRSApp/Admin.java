package com.digit.javaTraining.CRSApp;

import java.util.Scanner;

public class Admin {
	String username;
	String password;
	
	public Admin() {
		this.username="admin";
		this.password="Admin";
	}
	
	public boolean checkUsernamePassword() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Admin Username:");
		String name = sc.next();
		System.out.println("Enter Admin Password");
		String pass = sc.next();
		
		if(name.equals(username) && pass.equals(this.password))
			return true;
		return false;
	}
}
