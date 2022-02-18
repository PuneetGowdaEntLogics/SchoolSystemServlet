package com.entlogics.schoolsystem.entity;

import java.util.Set;

public class School {
	
	private int schoolId;
	
	private String schoolName;
	
	private String schoolAddress;
	
	private String schoolEmail;
	
	private String schoolPhone;
	
	private Set<Clazz> schoolClasses;
	
	private Set<Subject> schoolSubjects;
	
	private Set<Student> schoolStudents;
	
	private Set<Exam> schoolExams;
	
	public int getSchoolId() {
		return schoolId;
	}




	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}




	public String getSchoolName() {
		return schoolName;
	}




	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}




	public String getSchoolAddress() {
		return schoolAddress;
	}




	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}




	public String getSchoolEmail() {
		return schoolEmail;
	}




	public void setSchoolEmail(String schoolEmail) {
		this.schoolEmail = schoolEmail;
	}




	public String getSchoolPhone() {
		return schoolPhone;
	}




	public void setSchoolPhone(String schoolPhone) {
		this.schoolPhone = schoolPhone;
	}




	public Set<Clazz> getSchoolClasses() {
		return schoolClasses;
	}




	public void setSchoolClasses(Set<Clazz> schoolClasses) {
		this.schoolClasses = schoolClasses;
	}




	public Set<Subject> getSchoolSubjects() {
		return schoolSubjects;
	}




	public void setSchoolSubjects(Set<Subject> schoolSubjects) {
		this.schoolSubjects = schoolSubjects;
	}




	public Set<Student> getSchoolStudents() {
		return schoolStudents;
	}




	public void setSchoolStudents(Set<Student> schoolStudents) {
		this.schoolStudents = schoolStudents;
	}




	public Set<Exam> getSchoolExams() {
		return schoolExams;
	}




	public void setSchoolExams(Set<Exam> schoolExams) {
		this.schoolExams = schoolExams;
	}




	@Override
	public String toString() {
		return "School [schoolId=" + schoolId + ", schoolName=" + schoolName + ", schoolAddress=" + schoolAddress
				+ ", schoolEmail=" + schoolEmail + ", schoolPhone=" + schoolPhone + "]";
	}

	
	
}
