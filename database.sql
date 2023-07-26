/*Create database*/
CREATE DATABASE crs;

USE crs;

/*  create Professor table*/
CREATE TABLE Professor (
	P_Id VARCHAR(10) PRIMARY KEY, 
	P_Username VARCHAR(50), 
	P_Name VARCHAR(50), 
	P_Password INT, 
	P_Age INT 
);

/*  create Course table*/ 
CREATE TABLE Course (
	C_Id VARCHAR(10) PRIMARY KEY, 
	C_Name VARCHAR(50), 
	Price INT, 
	Duration VARCHAR(50), 
	DESCRIPTION VARCHAR(60), 
	Professor_id Varchar(10),
	FOREIGN KEY (Professor_id) REFERENCES Professor(P_Id)
	
);



/*  create Student table*/
CREATE TABLE Student (
	S_Id VARCHAR(10) PRIMARY KEY, 
	S_Username VARCHAR(50), 
	S_Name VARCHAR(50), 
	S_Password INT, 
	S_Age INT,
	Course_id Varchar(10),
	FOREIGN KEY (Course_id) REFERENCES Course(C_Id)
);

/*  create Relation table for students regestring to a course*/
/*
CREATE TABLE student_course_relation(
	Student_id Varchar(10) NOT NULL,
	Course_id Varchar(10) NOT NULL,
	FOREIGN KEY (Student_id) REFERENCES  Student(S_Id),
	FOREIGN KEY (Course_id) REFERENCES Course(C_Id)
);
*/

