package com.entlogics.schoolsystem.dao;

import java.util.Set;

import com.entlogics.schoolsystem.entity.Student;

// this interface declares method to get set of students
public interface IStudentDAO {

	// this method gets set of students
	Set<Student> getStudents();
	
}
