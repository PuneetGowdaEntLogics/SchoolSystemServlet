package com.entlogics.schoolsystem.dao;

import java.util.List;
import java.util.Set;

import com.entlogics.schoolsystem.entity.Clazz;
import com.entlogics.schoolsystem.entity.Exam;
import com.entlogics.schoolsystem.entity.School;
import com.entlogics.schoolsystem.entity.Student;
import com.entlogics.schoolsystem.entity.Subject;

public interface ISchoolDAO {

	// Add 10 method declarations
	
	List<School> getSchools();

	Boolean createSchool(School schoolData);

	Boolean updateSchool(int schoolId, School schoolData);

	School getSchoolInformation(int schoolId);

	Boolean deleteSchool(int schoolId);

	Set<Clazz> getSchoolClasses(int schoolId);

	Set<Student> getSchoolStudents(int schoolId);

	Boolean linkSchoolSubjects(int schoolId, Subject subjectData);

	Set<Subject> getSchoolSubjects(int schoolId);
	
	Set<Exam> getSchoolExams(int schoolId);
}
