package com.entlogics.schoolsystem.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import com.entlogics.schoolsystem.entity.Clazz;
import com.entlogics.schoolsystem.entity.Exam;
import com.entlogics.schoolsystem.entity.School;
import com.entlogics.schoolsystem.entity.Student;
import com.entlogics.schoolsystem.entity.Subject;
import com.entlogics.schoolsystem.util.DBConnectionUtil;

/*
 * This class has implentation to add a class, edit a class, delete a class, view  a class, view students of  a class, view subjects of  a class, and view exams of a class 
 */
public class ClassDAO implements IClassDAO {

	// This method adds a class
	@Override
	public Boolean createClass(School schoolData, Clazz classData) {
		
	// create boolean object to indicate the status of method execution
		Boolean flag = false;
	
		try {
		
			// get the database connection
			Connection connection = DBConnectionUtil.openConnection();
			
			// create the preparedstatement object with sql query to add a class to the database
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO class(school_id, class_name) VALUES(?, ?)");
			preparedStatement.setInt(1, schoolData.getSchoolId());
			preparedStatement.setString(2, classData.getClassName());
			
			// execute the sql query
			preparedStatement.executeUpdate();
			
			// set flag as true to indicate successful method execution
			flag = true;
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		
		// return the flag
		return flag;
	}

	// This method get a class information
	@Override
	public Clazz getClassInformation(int classId) {
		
		// create class object
		Clazz clazz = new Clazz();
		
		try {
			
			// get the database connection
			Connection connection = DBConnectionUtil.openConnection();
			
			// create a preparedstatement object with sql query to get class information
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT class_id, class_name, class.school_id, school_name FROM class JOIN school ON school.school_id = class.school_id WHERE class_id=?");
			preparedStatement.setInt(1, classId);
			
			// execute the sql query and store the result in the resultset object
			ResultSet resultSet = preparedStatement.executeQuery();
			
			// loop over until resultset object has value
			while (resultSet.next()) {
				
				// set the property of class object
				clazz.setClassId(resultSet.getInt("class_id"));
				clazz.setClassName(resultSet.getString("class_name"));
				
				// create a school object
				School school = new School();
				
				// set the property of school object
				school.setSchoolId(resultSet.getInt("school_id"));
				school.setSchoolName(resultSet.getString("school_name"));
				
				// set the school property of class object
				clazz.setSchool(school);
				
				// create a preparedstatement object with sql query to get students of a class
				PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT student_id, student_fname FROM student WHERE class_id=?");
				preparedStatement2.setInt(1, classId);
				
				// execute the preparedstatement object and store in resultset object
				ResultSet resultSet2 = preparedStatement2.executeQuery();
				
				// create a linkedhashset object for set of students
				Set<Student> setOfStudents = new LinkedHashSet<>();
				
				// loop over until resultset object has value
				while (resultSet2.next()) {
					
					// create the student object
					Student student = new Student();
					
					// set the properties of student object
					student.setStudentId(resultSet2.getInt("student_id"));
					student.setStudentFname(resultSet2.getString("student_fname"));
					
					// add the student object to the set of students
					setOfStudents.add(student);
					
				}
				
				// set the classStudents property of class object
				clazz.setClassStudents(setOfStudents);
				
				// create a preparedstatement object with sql query to get subjects of a class
				PreparedStatement preparedStatement3 = connection.prepareStatement("SELECT _subject.subject_id, subject_name FROM _subject JOIN exam ON _subject.subject_id = exam.subject_id JOIN class ON class.class_id = exam.class_id WHERE class.class_id=?");
				preparedStatement3.setInt(1, classId);
				
				// execute the sql query and store the result in the resultset object
				ResultSet resultSet3 = preparedStatement3.executeQuery();
				
				// create a linkedhashset object for set of subjects
				Set<Subject> setOfSubjects = new LinkedHashSet<>();
				
				// loop over until resulset has value
				while(resultSet3.next()) {
					
					// create a subject object
					Subject subject = new Subject();
					
					// set the properties of subject object
					subject.setSubjectId(resultSet3.getInt("subject_id"));
					subject.setSubjectName(resultSet3.getString("subject_name"));
					
					// add the subject object to the set of subjects
					setOfSubjects.add(subject);
					
				}
				
				// set the classSubjects property of class object
				clazz.setClassSubjects(setOfSubjects);
				
				// create a preparedstatement object with sql query to get exams of a class
				PreparedStatement preparedStatement4 = connection.prepareStatement("SELECT exam_id, exam_name FROM exam WHERE class_id=?");
				preparedStatement4.setInt(1, classId);
				
				// execute the sql query and store the result in resultset object
				ResultSet resultSet4 = preparedStatement4.executeQuery();
				
				// create set object for set of exams
				Set<Exam> setOfExams = new LinkedHashSet<>();
				
				// loop over until resulset object has value
				while (resultSet4.next()) {
					
					// create a exam object
					Exam exam = new Exam();
					
					// set the properties of exam object
					exam.setExamId(resultSet4.getInt("exam_id"));
					exam.setExamName(resultSet4.getString("exam_name"));
					
					// add the exam object to the set of exams
					setOfExams.add(exam);
					
				}
				
				// set the classExams property of class
				clazz.setClassExams(setOfExams);
				
			}
			
		} catch (IOException | SQLException e ) {
			e.printStackTrace();
		}
		
		// return the class
		return clazz;
	}

	// this method edits a class of a school
	@Override
	public Boolean updateClass(int classId, Clazz classData) {
	
		// create boolean object to indicate the status of method execution
		Boolean flag = false;
		
		// use the id to get the old class
		Clazz oldClass = getClassInformation(classId);
		
		System.out.println("INSIDE DAO of Class");
		
		// if the properties in new classData is not empty then add from classData other wise add the properties of old class data	
		int schoolId = classData.getSchool().getSchoolId() != 0 ? classData.getSchool().getSchoolId() : oldClass.getSchool().getSchoolId();
		
//		System.out.println("SCHOOL ID "+schoolId);
		
		String className = classData.getClassName() != null ? classData.getClassName() : oldClass.getClassName();
		
//		System.out.println("CLASS NAME "+className);
		
		try {
			
			// get the database connection
			Connection connection = DBConnectionUtil.openConnection();
			
			// create the preparestatement object with sql query to update the class
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE class SET school_id=?, class_name=? WHERE class_id=?");
			preparedStatement.setInt(1, schoolId);
			preparedStatement.setString(2, className);
			preparedStatement.setInt(3, classId);
			
			// execute the sql query
			preparedStatement.executeUpdate();
			
			//  set the flag to true for successful execution
			flag = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// return the flag
		return flag;
	}

	// this method deletes a class
	@Override
	public Boolean deleteClass(int classId) {
		
		// create boolean variable to indicate the status of method execution
		Boolean flag = false;
		
		try {
			
			// get the database connection
			Connection connection = DBConnectionUtil.openConnection();
			
			// create the preparedstatement object with sql query to delete a class
			PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM class WHERE class_id=?");
			preparedStatement.setInt(1, classId);
			
			// execute the sql query
			preparedStatement.executeUpdate();
			
			// set the flag to true
			flag = true;
			
		} catch (Exception e ) { 
			e.printStackTrace();
		}
		
		// return the flag
		return flag;
	}

	// this method get set of class subjects
	@Override
	public Set<Subject> getClassSubjects(int classId) {
		
		// Create a linkedhashset object to add set of class subjects
		Set<Subject> setOfSubjects = new LinkedHashSet<>();
		
		try {
			
			// get the database connection
			Connection connection = DBConnectionUtil.openConnection();
			
			// create preparedstatement object with sql query to get class subjects
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT _subject.subject_id, subject_name FROM _subject JOIN exam ON exam.subject_id =_subject.subject_id JOIN class ON class.class_id = exam.class_id WHERE class.class_id = ?");
			preparedStatement.setInt(1, classId);
			
			// execute the query and store it in resultset object
			ResultSet resultSet = preparedStatement.executeQuery();
			
			// loop over until resultset object has value
			while (resultSet.next()) {
				
				// create subject object
				Subject subject = new Subject();
				
				// set the properties of subject object
				subject.setSubjectId(resultSet.getInt("subject_id"));
				subject.setSubjectName(resultSet.getString("subject_name"));
				
				// add the subject object to set of class subjects
				setOfSubjects.add(subject);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// return the set of class subjects
		return setOfSubjects;
	}

	// this method returns the set of class students
	@Override
	public Set<Student> getClassStudents(int classId) {
		
		// create linkedhashset to add students of a class
		Set<Student> setOfStudents = new LinkedHashSet<>();
		
		try {
			
			// get the database connection
			Connection connection = DBConnectionUtil.openConnection();
			
			// create the preparedstatement object with sql query to get the students of a class
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT student_id, student_fname, student_lname, student_roll_no, student_enrollment_no, student_birth_date, student_address, student_phone,\r\n"
					+ "student_date_joined, school.school_id, school_name FROM student JOIN class ON student.class_id=class.class_id JOIN school ON\r\n"
					+ "school.school_id=class.school_id WHERE class.class_id= ?");
			preparedStatement.setInt(1, classId);
			
			// execute the sql query and store the result in resultset object
			ResultSet resultSet = preparedStatement.executeQuery();
			
			// loop over until resultset object has value
			while (resultSet.next()) {
				
				// create student object
				Student student = new Student();
				
				// set the properties of student object
				student.setStudentId(resultSet.getInt("student_id"));
				student.setStudentFname(resultSet.getString("student_fname"));
				student.setStudentLname(resultSet.getString("student_lname"));
				student.setStudentRollNo(resultSet.getString("student_roll_no"));
				student.setStudentEnrollmentNo(resultSet.getString("student_enrollment_no"));
				student.setStudentBirthDate(resultSet.getDate("student_birth_date"));
				student.setStudentAddress(resultSet.getString("student_address"));
				student.setStudentPhone(resultSet.getString("student_phone"));
				student.setStudentDateJoined(resultSet.getDate("student_date_joined"));
				
				// create the school object
				School school = new School();
				
				// set the properties of school object
				school.setSchoolId(resultSet.getInt("school_id"));
				school.setSchoolName(resultSet.getString("school_name"));
				
				// set the school property of student object
				student.setSchool(school);
				
				// create preparedstatement object with sql query to get subjects of a student
				PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT _subject.subject_id, subject_name FROM _subject JOIN student_subjects ON student_subjects.subject_id = _subject.subject_id WHERE student_id=?");
				preparedStatement2.setInt(1, resultSet.getInt("student_id"));
				
				// execute the sql query and store the result in resultset object
				ResultSet resultSet2 = preparedStatement2.executeQuery();
				
				// create linkedhashset object for set of student subjects
				Set<Subject> setOfSubjects = new LinkedHashSet<>();
				
				// loop over until the resultset object has value
				while (resultSet2.next()) {
					
					// create subject object
					Subject subject = new Subject();
					
					// set the properties of subject object
					subject.setSubjectId(resultSet2.getInt("subject_id"));
					subject.setSubjectName(resultSet2.getString("subject_name"));
					
					// add the subject object to the set of class subjects
					setOfSubjects.add(subject);
					
				}
				
				// set the studentSubjects property of student
				student.setStudentSubjects(setOfSubjects);
				
				// create preparedstatement object with sql query to get the exams of  a class
				PreparedStatement preparedStatement3 = connection.prepareStatement("SELECT exam.exam_id, exam_name FROM exam JOIN student_exams ON student_exams.exam_id = exam.exam_id WHERE student_id=?");
				preparedStatement3.setInt(1, resultSet.getInt("student_id"));
				
				// execute the sql query and store the result in resultset object
				ResultSet resultSet3 = preparedStatement3.executeQuery();
				
				// create linkedhashset object for set of student exams
				Set<Exam> setOfExams = new LinkedHashSet<>();
				
				// loop over until resultset object has value
				while (resultSet3.next()) {
					
					// create exam object
					Exam exam = new Exam();
					
					// set the properties of exam object
					exam.setExamId(resultSet3.getInt("exam_id"));
					exam.setExamName(resultSet3.getString("exam_name"));
					
					// add the exam object to the set of student exams
					setOfExams.add(exam);
					
				}
				
				// set the studentExams property of student
				student.setStudentExams(setOfExams);
				
				
				// add the student to the set of class students
				setOfStudents.add(student);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// return the set of class students
		return setOfStudents;
	}

	@Override
	public Set<Exam> getClassExams(int classId) {
		
		// create linkedhashset object for set of class exams
		Set<Exam> setOfExams = new LinkedHashSet<>();
		
		try {
			
			// get the database connection
			Connection connection = DBConnectionUtil.openConnection();
			
			// create preparedstatement object with sql query to get the exams of a class
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT exam_id, exam_name, school.school_id, school_name, _subject.subject_id, subject_name, exam_date, exam_time_from, exam_time_to,\r\n"
					+ " exam_venue, exam_status FROM exam JOIN class ON class.class_id=exam.class_id JOIN _subject ON _subject.subject_id=exam.subject_id \r\n"
					+ "JOIN school ON class.school_id=school.school_id WHERE class.class_id=?");
			preparedStatement.setInt(1, classId);
			
			// execute the sql query and store the result in the resultset object
			ResultSet resultSet = preparedStatement.executeQuery();
			
			// loop over until the resultset object has the value
			while (resultSet.next()) {
				
				// create the exam object
				Exam exam = new Exam();
				
				// set the properties of exam object
				exam.setExamId(resultSet.getInt("exam_id"));
				exam.setExamName(resultSet.getString("exam_name"));
				
				// create school object
				School school = new School();
				
				// set the properties of school object
				school.setSchoolId(resultSet.getInt("school_id"));
				school.setSchoolName(resultSet.getString("school_name"));
				
				// set the school property of exam
				exam.setSchool(school);
				
				// create subject object
				Subject subject = new Subject();
				
				// set the properties of subject object
				subject.setSubjectId(resultSet.getInt("subject_id"));
				subject.setSubjectName(resultSet.getString("subject_name"));
				
				// set the subject property of exam
				exam.setSubject(subject);
				
				// set the remaining properties of exam
				exam.setExamDate(resultSet.getDate("exam_date"));
				exam.setExamTimeFrom(resultSet.getTime("exam_time_from"));
				exam.setExamTimeTo(resultSet.getTime("exam_time_to"));
				exam.setExamVenue(resultSet.getString("exam_venue"));
				exam.setExamStatus(resultSet.getString("exam_status"));
				
				// add the exam object to the set of class exams
				setOfExams.add(exam);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// return the set of class exams
		return setOfExams;
	}

}
