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
import com.entlogics.schoolsystem.entity.Student;
import com.google.gson.Gson;

/**
 * This Servlet gets the set of students of a school
 */
@WebServlet("/schools/students/*")
public class GetSchoolStudentsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * This method get the set of students of a school
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// getting the id from the url
		String id = request.getPathInfo().replace("/","");
		
		// changing id from string to int
		int schoolId = Integer.parseInt(id);
		
		// Create schoolDAO object to call SchoolDAO getSchools method
		ISchoolDAO schoolDAO = new SchoolDAO();
		

		// Call SchoolDAO getSchools method
		Set<Student> setOfStudents = schoolDAO.getSchoolStudents(schoolId);
		
		// storing the size of the school list in a string
		String size = String.valueOf(setOfStudents.size());
		
		// printing the size of the list
		System.out.println(size);
        
		// Create Printwriter object 
        PrintWriter out = response.getWriter();
        
        // Set the content type header of the response to JSON
        response.setContentType("application/json");
        
        // print the data in JSON format
        out.print(new Gson().toJson(setOfStudents));	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
