/**********************************************
Workshop 10
Course:CPP, Semester - 4
Last Name: Anand
First Name: Ashwin
ID: 152042206
Section: NDD
This assignment represents my own work in accordance with Seneca Academic Policy.
Ashwin Anand
Date: 12 August 2022
**********************************************/

package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Student{
	
	private int id, yearEnrolled;
	private String name, email, address, program;
	
	Student(){
		this.id = 0;
		this.name = "";
		this.email = "";
		this.address = "";
		this.program = "";
		this.yearEnrolled = 0;
	}
	
	Student(int id, String name, String email, String address, String program, int yearEnrolled){
		
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
		this.program = program;
		this.yearEnrolled = yearEnrolled;
		
	}
	
	void setId(int id) {
		this.id = id;
	}
	
	void setEnrollmentYear(int yearEnrolled) {
		this.yearEnrolled = yearEnrolled;
	}
	
	void setName(String name) {
		this.name = name;
	}
	void setEmail(String email) {
		this.email = email;
	}
	
	void setAddress(String address) {
		this.address = address;
	}
	
	void setProgram(String program) {
		this.program = program;
	}
	
	int getId() {
		return id;
	}
	
	int getEnrollmentYear() {
		return yearEnrolled;
	}
	
	String getName() {
		return name;
	}
	

	String getEmail() {
		return email;
	}
	

	String getAddress() {
		return address;
	}
	

	String getProgram() {
		return program;
	}
		
	public void insertStudentData(Student student, Statement statement)throws Exception {
		statement.execute("Insert into student values(" + student.id + ", '" + student.name + "', '" + student.email + "', '" + student.address + "', " + student.yearEnrolled + ", '" + student.program + "')");			
	};
	
	public void updateStudentData(Student student, Statement statement)throws Exception {	
		statement.execute("Update student SET name = '" + student.name + "', email = '" + student.email + "', mailingAddress = '" + student.address + "', yearEnrolled = '" + student.yearEnrolled + "', program = '" + student.program +"' where studentId = " + student.id);
	}
	
	public static void deleteStudent(int id, Statement statement)throws Exception {
		statement.execute("Delete from student where studentId = " + id);
	}
	
	public static Statement getStatement() {
		Connection conn = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_info", "root", "P@$$W0rd4Route");
			
			statement = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return statement;	
	}
	
	public void closeResources(Statement statement, Connection conn) {
		
		try {
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
