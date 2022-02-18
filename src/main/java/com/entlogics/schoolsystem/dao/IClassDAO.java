package com.entlogics.schoolsystem.dao;

import java.util.Set;

import com.entlogics.schoolsystem.entity.Clazz;
import com.entlogics.schoolsystem.entity.Exam;
import com.entlogics.schoolsystem.entity.School;
import com.entlogics.schoolsystem.entity.Student;
import com.entlogics.schoolsystem.entity.Subject;

public interface IClassDAO {

	Boolean createClass(School schoolData, Clazz classData);

	Clazz getClassInformation(int classId);

	Boolean updateClass(int classId, Clazz classData);

	Boolean deleteClass(int classId);

	Set<Subject> getClassSubjects(int classId);

	Set<Student> getClassStudents(int classId);

	Set<Exam> getClassExams(int classId);

}
