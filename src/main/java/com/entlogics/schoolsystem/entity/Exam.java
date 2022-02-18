package com.entlogics.schoolsystem.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class Exam {

	private int examId;
	
	private String examName;
	
	private Clazz clazz;
	
	private School school;
	
	private Subject subject;
	
	private Date examDate;
	
	private Time examTimeFrom;
	
	private Time examTimeTo;
	
	private String examVenue;
	
	private String examStatus;
	
	private List<Student> examStudents;

	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}
	
	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public Time getExamTimeFrom() {
		return examTimeFrom;
	}

	public void setExamTimeFrom(Time examTimeFrom) {
		this.examTimeFrom = examTimeFrom;
	}

	public Time getExamTimeTo() {
		return examTimeTo;
	}

	public void setExamTimeTo(Time examTimeTo) {
		this.examTimeTo = examTimeTo;
	}

	public String getExamVenue() {
		return examVenue;
	}

	public void setExamVenue(String examVenue) {
		this.examVenue = examVenue;
	}

	public String getExamStatus() {
		return examStatus;
	}

	public void setExamStatus(String examStatus) {
		this.examStatus = examStatus;
	}

	public List<Student> getExamStudents() {
		return examStudents;
	}

	public void setExamStudents(List<Student> examStudents) {
		this.examStudents = examStudents;
	}

	@Override
	public String toString() {
		return "Exam [examId=" + examId + ", examName=" + examName + ", clazz=" + clazz + ", school=" + school
				+ ", subject=" + subject + ", examDate=" + examDate + ", examTimeFrom=" + examTimeFrom + ", examTimeTo="
				+ examTimeTo + ", examVenue=" + examVenue + ", examStatus=" + examStatus + "]";
	}

	





}
//
//enum Status {
//	COMPLETED,
//	POSTPONED,
//	CANCELLED
//}


