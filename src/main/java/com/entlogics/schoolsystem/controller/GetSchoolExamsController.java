package com.entlogics.schoolsystem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entlogics.schoolsystem.dao.ISchoolDAO;
import com.entlogics.schoolsystem.dao.SchoolDAO;
import com.entlogics.schoolsystem.entity.Exam;
import com.google.gson.Gson;

/**
 * This Servlet gets set of exams of a school
 */
@WebServlet("/schools/exams/*")
public class GetSchoolExamsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This method gets the set of exams of a school
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// get the id from the request
		String id = request.getPathInfo().replace("/", "");
		
		// change the id from string to int
		int schoolId = Integer.parseInt(id);
		
		// create DAO object
		ISchoolDAO schoolDAO = new SchoolDAO();
		
		// execute the method to get the set of exams of a school using DAO object
		Set<Exam> setOfExams = schoolDAO.getSchoolExams(schoolId);
		
		// get the size of set of exams
		int size = setOfExams.size();
		
		// print the size
		System.out.println("The size of set of exams is : "+size);
		
		// create the printwriter object
		PrintWriter out = response.getWriter();
		
		// set the response content type header
		response.setContentType("application/json");
		
		// set the response status code
		response.setStatus(200);
		
		// print the json response body
		out.print(new Gson().toJson(setOfExams));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
