package com.entlogics.schoolsystem.entity;

import java.sql.Date;
import java.util.Set;

public class Student {

	private int studentId;
	
	private String studentFname;
	
	private Clazz clazz;
	
	private School school;
	
	private String studentLname;
	
	private String studentRollNo;
	
	private String studentEnrollmentNo;
	
	private Date StudentBirthDate;
	
	private String studentAddress;
	
	private String studentPhone;

	private Date StudentDateJoined;
	
	private Set<Exam> studentExams;
	
	private Set<Subject> studentSubjects;

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentFname() {
		return studentFname;
	}

	public void setStudentFname(String studentFname) {
		this.studentFname = studentFname;
	}

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}
	
	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public String getStudentLname() {
		return studentLname;
	}

	public void setStudentLname(String studentLname) {
		this.studentLname = studentLname;
	}

	public String getStudentRollNo() {
		return studentRollNo;
	}

	public void setStudentRollNo(String studentRollNo) {
		this.studentRollNo = studentRollNo;
	}

	public String getStudentEnrollmentNo() {
		return studentEnrollmentNo;
	}

	public void setStudentEnrollmentNo(String studentEnrollmentNo) {
		this.studentEnrollmentNo = studentEnrollmentNo;
	}

	public Date getStudentBirthDate() {
		return StudentBirthDate;
	}

	public void setStudentBirthDate(Date studentBirthDate) {
		StudentBirthDate = studentBirthDate;
	}

	public String getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}

	public String getStudentPhone() {
		return studentPhone;
	}

	public void setStudentPhone(String studentPhone) {
		this.studentPhone = studentPhone;
	}

	public Date getStudentDateJoined() {
		return StudentDateJoined;
	}

	public void setStudentDateJoined(Date studentDateJoined) {
		StudentDateJoined = studentDateJoined;
	}

	public Set<Exam> getStudentExams() {
		return studentExams;
	}

	public void setStudentExams(Set<Exam> studentExams) {
		this.studentExams = studentExams;
	}

	public Set<Subject> getStudentSubjects() {
		return studentSubjects;
	}

	public void setStudentSubjects(Set<Subject> studentSubjects) {
		this.studentSubjects = studentSubjects;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentFname=" + studentFname + ", clazz=" + clazz
				+ ", studentLname=" + studentLname + ", studentRollNo=" + studentRollNo + ", studentEnrollmentNo="
				+ studentEnrollmentNo + ", StudentBirthDate=" + StudentBirthDate + ", studentAddress=" + studentAddress
				+ ", studentPhone=" + studentPhone + ", StudentDateJoined=" + StudentDateJoined + "]";
	}

	

	
}
