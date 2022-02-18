package com.entlogics.schoolsystem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entlogics.schoolsystem.dao.ISchoolDAO;
import com.entlogics.schoolsystem.dao.SchoolDAO;
import com.entlogics.schoolsystem.entity.School;
import com.entlogics.schoolsystem.entity.Subject;
import com.google.gson.Gson;

/**
 * This Servlet get the set subjects of a school, links the subjects to the school
 */
@WebServlet("/schools/subjects/*")
public class ManageSchoolSubjectsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * This method get the set of subjects of a school
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get the Id from the request
		String id = request.getPathInfo().replace("/", "");
		
		// change the Id from String it int
		int schoolId = Integer.parseInt(id);
		
		// Get the DAO object
		ISchoolDAO schoolDAO = new SchoolDAO();
		
		// call the method to get subjects of a school using DAO object and store in a setOfSubjects
		Set<Subject> setOfSubjects = schoolDAO.getSchoolSubjects(schoolId);
		
		// storing the size of the set of subjects in a string
		int size = setOfSubjects.size();
		
		// printing the size
		System.out.println("The size of the set of subjects is "+size);
		
		// creating the printwriter object
		PrintWriter out = response.getWriter();
		
		// setting the response content type header to json
		response.setContentType("application/json");
		
		// print the data in JSON format
		out.print(new Gson().toJson(setOfSubjects));
		
	}

	/**
	 * This method links the subjects to the school
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String JSON = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        
		// get the id from the request
		String id = request.getPathInfo().replace("/", "");
		
		// change the id from string to int
		int schoolId = Integer.parseInt(id);
		
		// get the JSON data from the request body and store it in subject object
		Subject subjectData = new Gson().fromJson(JSON, Subject.class);
		
		// create DAO object
		ISchoolDAO schoolDAO = new SchoolDAO();
		
		// execute the method to link subjects to school using DAO object
		Boolean result = schoolDAO.linkSchoolSubjects(schoolId, subjectData);
		
		// create printwriter object
		PrintWriter out = response.getWriter();
		
		// set the response content type header
		response.setContentType("text/html");
		
		// set the status code of the response
		response.setStatus(200);
		
		// print the message
		out.print("Sucessfully added subjects to the school");
		
	}

}
