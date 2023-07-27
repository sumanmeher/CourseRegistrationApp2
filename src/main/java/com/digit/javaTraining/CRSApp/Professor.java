package com.digit.javaTraining.CRSApp;

public class Professor extends User{
	
	Course course;
	
	public Professor(String name, String username, String pass, int age) {
		this.name=name;
		this.username=username;
		this.password=pass;
		this.age=age;
	}
}
