package com.entlogics.schoolsystem.entity;

import java.util.Set;

public class Clazz {

	private int classId;
		
	private String className;
	
	private School school;
	
	private Set<Student> classStudents;
	
	private Set<Subject> classSubjects;
	
	private Set<Exam> classExams;

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public Set<Student> getClassStudents() {
		return classStudents;
	}

	public void setClassStudents(Set<Student> classStudents) {
		this.classStudents = classStudents;
	}
	
	public Set<Subject> getClassSubjects() {
		return classSubjects;
	}

	public void setClassSubjects(Set<Subject> classSubjects) {
		this.classSubjects = classSubjects;
	}

	public Set<Exam> getClassExams() {
		return classExams;
	}

	public void setClassExams(Set<Exam> classExams) {
		this.classExams = classExams;
	}

	@Override
	public String toString() {
		return "Clazz [classId=" + classId + ", className=" + className + ", school=" + school + "]";
	}
	
	

	
		
}
