package com.entlogics.schoolsystem.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.entlogics.schoolsystem.entity.Clazz;
import com.entlogics.schoolsystem.entity.Exam;
import com.entlogics.schoolsystem.entity.School;
import com.entlogics.schoolsystem.entity.Student;
import com.entlogics.schoolsystem.entity.Subject;
import com.entlogics.schoolsystem.util.DBConnectionUtil;

/* 
* This class gives method implementations to get list of schools, view a school, edit a school, delete a school, get set of school classes, get set of school students,
* get set of school exams, get set of school subjects, link subjects to a school of the school database
*/
public class SchoolDAO implements ISchoolDAO {

	// Add 10 methods Implementation

	// method to get the list of schools
	@Override
	public List<School> getSchools() {

		// List initialization with School as Generic Type to add schools to the list
		List<School> listOfSchools = new ArrayList<>();

		// sql query string to get the list of schools from the database
		String sql = "SELECT school_id, school_name, school_address, school_email, school_phone FROM school";

		try {

			// To get the database connection
			Connection connection = DBConnectionUtil.openConnection();

			// To create a statement object
			Statement statement = connection.createStatement();

			/* 
			 * resultset object to store the result of the executed query giving list of schools
			 */
			ResultSet resultSet = statement.executeQuery(sql);

			// looping over until resultSet object has data
			while (resultSet.next()) {

				// Creating school object to be added to the list of student
				School school = new School();
				
				// setting the properties of school object
				school.setSchoolId(resultSet.getInt("school_id"));
				school.setSchoolName(resultSet.getString("school_name"));
				school.setSchoolAddress(resultSet.getString("school_address"));
				school.setSchoolEmail(resultSet.getString("school_email"));
				school.setSchoolPhone(resultSet.getString("school_phone"));

				// adding school object to the list
				listOfSchools.add(school);

			}

			// closing the connection resources
			resultSet.close();
			statement.close();
			connection.close();
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}

		// printing the size of the school list
		System.out.println("Inside schoolDAO, Size of the list is: " + listOfSchools.size());

		// Get the list of schools from the database
		return listOfSchools;
	}

	// method to create a new school in the database
	@Override
	public Boolean createSchool(School schoolData) {

		// Boolean object to notify status of method execution
		Boolean flag = false;

		try {

			// To get the database connection
			Connection connection = DBConnectionUtil.openConnection();

			// To create a preparedstatement object
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO school(school_name, school_email, school_address, school_phone) VALUES(?, ?, ?, ?)");
			preparedStatement.setString(1, schoolData.getSchoolName());
			preparedStatement.setString(2, schoolData.getSchoolAddress());
			preparedStatement.setString(3, schoolData.getSchoolEmail());
			preparedStatement.setString(4, schoolData.getSchoolPhone());

			/* executing the sql query */
			preparedStatement.executeUpdate();
			
			// set the flag as true for successful method execution
			flag = true;

			// closing the connection resources
			preparedStatement.close();
			connection.close();

		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}

		// returning true if successful creating of school 
		return flag;
	}

	// method to update a school information
	@Override
	public Boolean updateSchool(int schoolId, School schoolData) {
		
		// boolean object to indicate the status of method execution
		Boolean flag = false;
		
		// calling the method to get the school information using schoolId
		School oldSchool = getSchoolInformation(schoolId);
		
		/*
		storing the updated value of school properties in a string variable
		if schoolData properties are available then that is stored in the string variable otherwise already available school property is stored in the string variable
		*/
		String schoolName = schoolData.getSchoolName() != null ?	schoolData.getSchoolName() : oldSchool.getSchoolName();
		
		String schoolAddress = schoolData.getSchoolAddress() != null ?	schoolData.getSchoolAddress() : oldSchool.getSchoolAddress();
		
		String schoolEmail = schoolData.getSchoolEmail() != null ?	schoolData.getSchoolEmail() : oldSchool.getSchoolEmail();
		
		String schoolPhone = schoolData.getSchoolPhone() != null ?	schoolData.getSchoolPhone() : oldSchool.getSchoolPhone();

		try {

			// To get the database connection
			Connection connection = DBConnectionUtil.openConnection();

			// To create a preparedstatement object that has sql query for updating the school in the database
			PreparedStatement preparedStatement = connection.prepareStatement(
					"UPDATE school SET school_name=?, school_address=?, school_email=?, school_phone=? WHERE school_id=?");
			
			// setting the values of sql query
			preparedStatement.setString(1, schoolName);
			preparedStatement.setString(2, schoolAddress);
			preparedStatement.setString(3, schoolEmail);
			preparedStatement.setString(4, schoolPhone);
			preparedStatement.setInt(5, schoolId);
			

			/* executing the sql query */
			preparedStatement.executeUpdate();
			
			// setting flag as true indicating sucessful method execution
			flag = true;

			// closing the connection resources
			preparedStatement.close();
			connection.close();

		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}

		// returning the flag
		return flag;
	}

	
	
	// This method returns a school information
	@Override
	public School getSchoolInformation(int schoolId) {
		
				// Creating school object
				School school = new School();
				
				// intializing Sets to be added inside school object
				Set<Clazz> setOfClazzez = new LinkedHashSet<>();
				Set<Subject> setOfSubjects = new LinkedHashSet<>();
				Set<Student> setOfStudents = new LinkedHashSet<>();
				Set<Exam> setOfExams = new LinkedHashSet<>();
				
				try {
			
					// To get the database connection
					Connection connection = DBConnectionUtil.openConnection();

					// To create a preparedstatement object having the sql query to get a school 
					PreparedStatement preparedStatement = connection.prepareStatement("SELECT school_id, school_name, school_address, school_email, school_phone FROM school WHERE school.school_id=?");
					
					// setting the value of the sql query
					preparedStatement.setInt(1, schoolId);
					
					// executing the sql query and storing inside resultset object
					ResultSet resultSet = preparedStatement.executeQuery();
											
					// looping over until resultSet object has data
					while (resultSet.next()) {
						
						// setting the properties of the school object from the database
						school.setSchoolId(resultSet.getInt("school_id"));
						school.setSchoolName(resultSet.getString("school_name"));
						school.setSchoolAddress(resultSet.getString("school_address"));
						school.setSchoolEmail(resultSet.getString("school_email"));
						school.setSchoolPhone(resultSet.getString("school_phone"));
					
					}
					
					// To create a preparedstatement object and store the sql query for getting classes of a school
					preparedStatement = connection.prepareStatement("SELECT class_id, class_name FROM class WHERE school_id=?");
					preparedStatement.setInt(1, schoolId);
					
					// executing the sql query and storing inside resultset object
					resultSet = preparedStatement.executeQuery();
					
					// looping over until resultSet object has data
					while(resultSet.next()) {
						
						// Creating a new class object and adding properties from the database
						Clazz clazz= new Clazz();	
						clazz.setClassId(resultSet.getInt("class_id"));
						clazz.setClassName(resultSet.getString("class_name"));
						
						// adding the class to the list of class
						setOfClazzez.add(clazz);
					}
					
					// setting the schoolclasses property of the school
					school.setSchoolClasses(setOfClazzez);
					
					// To create a preparedstatement object having the sql query to get the subjects of a school
					preparedStatement = connection.prepareStatement("SELECT _subject.subject_id, subject_name FROM _subject JOIN school_subjects ON _subject.subject_id = school_subjects.subject_id WHERE school_id=?");
					preparedStatement.setInt(1, schoolId);
					
					// executing the sql query and storing inside resultset object
					resultSet = preparedStatement.executeQuery();
					
					// looping over until resultSet object has data
					while(resultSet.next()) {
						
						// Creating a new subject object and setting its properties from the database
						Subject subject = new Subject();
						subject.setSubjectId(resultSet.getInt("subject_id"));
						subject.setSubjectName(resultSet.getString("subject_name"));
						
						// adding the class to the list of subjects
						setOfSubjects.add(subject);
					}
					
					// setting schoolsubjects property of the school
					school.setSchoolSubjects(setOfSubjects);
					
					// To create a preparedstatement object having the sql query to get the student of a school
					preparedStatement = connection.prepareStatement("SELECT student_id, student_fname FROM student JOIN class ON student.class_id = class.class_id JOIN school ON class.school_id = school.school_id WHERE school.school_id=?");
					preparedStatement.setInt(1, schoolId);
					
					// executing the sql query and store the result inside the resultset object
					resultSet = preparedStatement.executeQuery();
					
					// looping over until resultSet has data
					while(resultSet.next()) {
						
						// Creating a new student object and setting its properties from the database
						Student student = new Student();
						student.setStudentId(resultSet.getInt("student_id"));
						student.setStudentFname(resultSet.getString("student_fname"));
						
						// adding the class to the list of students
						setOfStudents.add(student);
					}
					
					// setting the schoolstudents property of school
					school.setSchoolStudents(setOfStudents);
					
					// creates a preparedstatement object and store the sql query inside the object for execution
					preparedStatement = connection.prepareStatement("SELECT exam_id, exam_name FROM exam JOIN class ON exam.class_id = class.class_id JOIN school ON school.school_id = class.school_id WHERE school.school_id=?");
					preparedStatement.setInt(1, schoolId);
					
					// executing the sql query and storing inside resultset object
					resultSet = preparedStatement.executeQuery();
					
					// looping over until resultSet object has data
					while(resultSet.next()) {
						
						// Creating a new exam object and setting its properties from the database
						Exam exam = new Exam();
						exam.setExamId(resultSet.getInt("exam_id"));
						exam.setExamName(resultSet.getString("exam_name"));
						
						// adding the class to the list of exams
						setOfExams.add(exam);
					}					
					
					// adding set of Exams to the schools set of exams
					school.setSchoolExams(setOfExams);
					
					System.out.println("SCHOOL Classes OBJECT FROM DAO "+school.getSchoolClasses());
			
					// closing the connection resources
					resultSet.close();
					preparedStatement.close();
					connection.close();
					
				} catch (IOException | SQLException e) {
					e.printStackTrace();
				}

				// returns the schools information
				return school;
	}

	// this method deletes a school
	@Override
	public Boolean deleteSchool(int schoolId) {
		
		// boolean variable indicating the status of the method execution
		Boolean flag = false;
		try {
			
			// To get the database connection
			Connection connection = DBConnectionUtil.openConnection();

			// To create a preparedstatement object
			PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM school WHERE school_id=?");
			preparedStatement.setInt(1, schoolId);
			
			// executing the sql query and storing inside resultset object
			preparedStatement.executeUpdate();
			
		
			// closing the connection
			preparedStatement.close();
			connection.close();
			
			// setting flag as true for successful execution
			flag = true;
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}

		// returning the boolean value
		return flag;
	}

	// this method returns a set of school classes
	@Override
	public Set<Clazz> getSchoolClasses(int schoolId) {
		
		// intializing the Set to be added inside school object
		Set<Clazz> setOfClazzez = new LinkedHashSet<>();
		
		try {
	
			// To get the database connection
			Connection connection = DBConnectionUtil.openConnection();
		
			// To create a preparedstatement object and store the sql query inside the object for execution
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT class_id, class_name FROM class WHERE school_id=?");
			preparedStatement.setInt(1, schoolId);
		
			// executing the sql query and storing inside resultset object
			ResultSet resultSet = preparedStatement.executeQuery();
			
			// looping over until resultSet object has data
			while(resultSet.next()) {
			
				// Creating a new class object and adding properties from the database
				Clazz clazz= new Clazz();	
				clazz.setClassId(resultSet.getInt("class_id"));
				clazz.setClassName(resultSet.getString("class_name"));
				
				// adding the class to the list of class
				setOfClazzez.add(clazz);
			}
			
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		
		// returning the set of classes
		return setOfClazzez;
	}

	// This method returns the set of students of a school 
	@Override
	public Set<Student> getSchoolStudents(int schoolId) {
		
		// creating a set object
		Set<Student> setOfStudents = new LinkedHashSet<>();
		
		try {
			
			// To get the database connection
			Connection connection = DBConnectionUtil.openConnection();
			
			// creating preparedStatement object having sql query to get students of a school
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT student_id, student_fname, student_lname, student_roll_no, student_enrollment_no, student_birth_date, student_address, student_phone, student_date_joined, class.class_id, class_name FROM student JOIN class ON student.class_id = class.class_id JOIN school ON school.school_id = class.school_id WHERE school.school_id=?");
			preparedStatement.setInt(1, schoolId);
						
			// executing the sql query and storing the result inside resultSet
			ResultSet resultSet = preparedStatement.executeQuery();
		
			// looping over the resultset until it has data
			while(resultSet.next()) {
				
				// creating a new student object and setting its id, fname property
				Student student = new Student();
				student.setStudentId(resultSet.getInt("student_id"));
				student.setStudentFname(resultSet.getString("student_fname"));
				student.setStudentLname(resultSet.getString("student_lname"));
				student.setStudentRollNo(resultSet.getString("student_roll_no"));
				student.setStudentEnrollmentNo(resultSet.getString("student_enrollment_no"));
				student.setStudentBirthDate(resultSet.getDate("student_birth_date"));
				student.setStudentAddress(resultSet.getString("student_address"));
				student.setStudentPhone(resultSet.getString("student_phone"));
				student.setStudentDateJoined(resultSet.getDate("student_date_joined"));
				
				// create clazz object
				Clazz clazz = new Clazz();
				
				// set the properties of clazz object
				clazz.setClassId(resultSet.getInt("class_id"));
				clazz.setClassName(resultSet.getString("class_name"));
				
				// set the clazz property of student object
				student.setClazz(clazz);
				
				// create preparedstatement object with sql query to get the subjects of a student
				PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT _subject.subject_id, subject_name FROM _subject JOIN student_subjects ON student_subjects.subject_id = _subject.subject_id WHERE student_id=?");
				preparedStatement2.setInt(1, resultSet.getInt("student_id"));
				
				// execute the query and store the result in resultset object
				ResultSet resultSet2 = preparedStatement2.executeQuery();
				
				// create set of subjects object
				Set<Subject> setOfSubjects = new LinkedHashSet<>();
				
				// loop over until resulset has value
				while (resultSet2.next()) {
					// create subject object
					Subject subject = new Subject();
					
					// set the properties of subject object
					subject.setSubjectId(resultSet2.getInt("subject_id"));
					subject.setSubjectName(resultSet2.getString("subject_name"));
			
					// add the subject object to set of subjects
					setOfSubjects.add(subject);
				}
				

				// set the subject property of student object
				student.setStudentSubjects(setOfSubjects);	
				
				// create preparedstatement object with sql query to get exams of a student
				PreparedStatement preparedStatement3 = connection.prepareStatement("SELECT exam.exam_id, exam_name FROM exam JOIN student_exams ON student_exams.exam_id = exam.exam_id WHERE student_id=?");
				preparedStatement3.setInt(1, resultSet.getInt("student_id"));
				
				// execute the query and store the result in the resultset object
				ResultSet resultSet3 = preparedStatement3.executeQuery();
				
				// create set of exams object
				Set<Exam> setOfExams = new LinkedHashSet<>();
				
				// loop over until resultset object has value
				while (resultSet3.next()) {
					// create exam object
					Exam exam = new Exam();
					
					// set the properties of exam object
					exam.setExamId(resultSet3.getInt("exam_id"));
					exam.setExamName(resultSet3.getString("exam_name"));
			
					// add the subject object to set of subjects
					setOfExams.add(exam);
				}
				

				// set the subject property of student object
				student.setStudentExams(setOfExams);	
				
				// adding the student object to the set of students
				setOfStudents.add(student);
			}
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		
		// returning the set of students
		return setOfStudents;
	}

	// This method returns set of subjects of a school
	@Override
	public Set<Subject> getSchoolSubjects(int schoolId) {
		
		// create a linkedhashset for subject 
		Set<Subject>setOfSubjects = new LinkedHashSet<>();
		
		try {
			
			// get the database connection
			Connection connection = DBConnectionUtil.openConnection();
			
			// create the preparedstatement object having sql query to get subjects of a school
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT _subject.subject_id, subject_name FROM _subject JOIN school_subjects ON school_subjects.subject_id = _subject.subject_id WHERE school_id=?");  
			preparedStatement.setInt(1, schoolId);
			
			// execute the sql query and store the result in resultset object
			ResultSet resultSet = preparedStatement.executeQuery();
			
			// loop over until resultset has data
			while(resultSet.next()) {
				
				// create a subject object
				Subject subject = new Subject();
				
				// set the id, name properties of the subject
				subject.setSubjectId(resultSet.getInt("subject_id"));
				subject.setSubjectName(resultSet.getString("subject_name"));
				
				// add the subject object to the set of subjects
				setOfSubjects.add(subject);
				
			}
			
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		
		// return set of subjects of a school
		return setOfSubjects;
	}

	// This method assigns subjects to a school
	@Override
	public Boolean linkSchoolSubjects(int schoolId, Subject subjectData) {
		
		// boolean variable to indicate the status of method execution
		Boolean flag = false;
		
		// get the length of the array of subject ids for the condition of loop
		int length = subjectData.getSubjectID().length;
		
		// get the array of subject ids
		int[] subjectId = subjectData.getSubjectID();
		
		// create index for incrementing array of ids
		int index = 0;
		
		try {
			
			// get the database connection
			Connection connection = DBConnectionUtil.openConnection();
			
			// create loop to execute queries for all subject ids  
			while (length !=0 ) {
				
				System.out.println("LENGTH "+length+", AND INDEX "+index);
			
				// create preparedstatement object having sql query to link subject to school
				PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO school_subjects(school_id, subject_id) VALUES(?, ?)");
				preparedStatement.setInt(1, schoolId);
				preparedStatement.setInt(2, subjectId[index++]);
				
				// execute the sql queries
				preparedStatement.executeUpdate();
				
				// decrement the length
				length--;
				
			}
			
			// set flag as true for successful method execution
			flag = true;
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		
		// return the flag
		return flag;
	}

	// this method returns a set of school exams
	@Override
	public Set<Exam> getSchoolExams(int schoolId) {
		
		// create linkedhashset object
		Set<Exam> setOfExams = new LinkedHashSet<>();
		
		try {
			
			// get the database connection
			Connection connection = DBConnectionUtil.openConnection();
			
			// create the preparedstatement object with sql query to get the exams of a school
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT exam.exam_id, exam_name, class.class_id, class_name, _subject.subject_id, subject_name, exam_date, exam_time_from, \r\n"
					+ "exam_time_to, exam_venue, exam_status FROM exam \r\n"
					+ "INNER JOIN class ON exam.class_id = class.class_id INNER JOIN _subject ON _subject.subject_id = exam.subject_id \r\n"
					+ "WHERE school_id =? ORDER BY exam.exam_id;");
			preparedStatement.setInt(1, schoolId);
			
			// execute the sql queries and store the result in resultset
			ResultSet resultSet = preparedStatement.executeQuery();
			
			// loop over until resultset has data
			while (resultSet.next()) {
				
				// create exam object
				Exam exam = new Exam();
				
				// set the properties of exam object
				exam.setExamId(resultSet.getInt("exam_id"));
				exam.setExamName(resultSet.getString("exam_name"));
				
				// create a class object
				Clazz clazz = new Clazz();
				
				// set the properties of class object
				clazz.setClassId(resultSet.getInt("class_id"));
				clazz.setClassName(resultSet.getString("class_name"));
				
				// set the clazz property of exam object
				exam.setClazz(clazz);
				
				// create a subject object
				Subject subject = new Subject();
				
				// set the properties of subject object
				subject.setSubjectId(resultSet.getInt("subject_id"));
				subject.setSubjectName(resultSet.getString("subject_name"));
				
				// set the subject property of exam object
				exam.setSubject(subject);
				
				// set the remaining properties of exam
				exam.setExamDate(resultSet.getDate("exam_date"));
				exam.setExamTimeFrom(resultSet.getTime("exam_time_from"));
				exam.setExamTimeTo(resultSet.getTime("exam_time_to"));
				exam.setExamVenue(resultSet.getString("exam_venue"));
				exam.setExamStatus(resultSet.getString("exam_status"));
				
				
				// add the exam object to the set of exams
				setOfExams.add(exam);
				
			}
			
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		
		// return the set of exams
		return setOfExams;
	}

}
