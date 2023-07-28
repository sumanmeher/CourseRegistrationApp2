package com.digit.javaTraining.CRSApp;

public class Professor extends User{
	
	String course_id;
	
	public String getCourse_id() {
		return course_id;
	}

	public Professor(String name, String username, String pass, int age, String course_id) {
		this.name=name;
		this.username=username;
		this.password=pass;
		this.age=age;
		this.course_id = course_id;
	}
}
