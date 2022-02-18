package com.entlogics.schoolsystem.entity;

import java.util.Arrays;
import java.util.Set;

public class Subject {
	
	private int subjectId;
	
	private String subjectName;
	
	private Set<Student> subjectStudents;
	
	private Set<School> subjectSchools;
	
	public int[] subjectID;

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Set<Student> getSubjectStudents() {
		return subjectStudents;
	}

	public void setSubjectStudents(Set<Student> subjectStudents) {
		this.subjectStudents = subjectStudents;
	}

	public Set<School> getSubjectSchools() {
		return subjectSchools;
	}

	public void setSubjectSchools(Set<School> subjectSchools) {
		this.subjectSchools = subjectSchools;
	}
	
	

	public int[] getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(int[] subjectID) {
		this.subjectID = subjectID;
	}

	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", subjectName=" + subjectName + ", subjectID="
				+ Arrays.toString(subjectID) + "]";
	}


	
	
}
