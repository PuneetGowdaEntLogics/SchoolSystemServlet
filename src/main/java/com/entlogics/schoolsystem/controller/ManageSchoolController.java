package com.entlogics.schoolsystem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entlogics.schoolsystem.dao.ISchoolDAO;
import com.entlogics.schoolsystem.dao.SchoolDAO;
import com.entlogics.schoolsystem.entity.School;
import com.google.gson.Gson;

/**
 * This Servlet gets the list of schools, adds a new school
 */
@WebServlet("/schools")
public class ManageSchoolController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	/**
	 * This method gets the list of schools
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("HERE NOW");
		
		// Create schoolDAO object to call SchoolDAO getSchools method
		ISchoolDAO schoolDAO = new SchoolDAO();
		

		// Call SchoolDAO getSchools method
		List<School> schoolList = schoolDAO.getSchools();
		
		// storing the size of the school list in a string
		String size = String.valueOf(schoolList.size());
		
		// printing the size of the list
		System.out.println(size);
        
		// Create Printwriter object 
        PrintWriter out = response.getWriter();
        
        // Set the content type header of the response to JSON
        response.setContentType("application/json");
        
        // print the data in JSON format
        out.print(new Gson().toJson(schoolList));		

	}

	/**
		This method creates a new school
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			String JSON = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			
			System.out.println("IN FIRST CONTROLLER "+JSON);
				
			// Get the JSON data from the request body and store it in school object
			School schoolData = new Gson().fromJson(JSON, School.class);
			
			System.out.println("The data "+schoolData);
			
			// Create schoolDAO object to call SchoolDAO getSchools method
			ISchoolDAO schoolDAO = new SchoolDAO();
				
			// Call SchoolDAO createSchool method
			Boolean result = schoolDAO.createSchool(schoolData);
			
			// Create Printwriter object 
	        PrintWriter out = response.getWriter();
	        
	        // Set the content type header of the response
	        response.setContentType("text/html");
	        
	        // set the status code of the response
	        response.setStatus(201);
	        
	        // print the message
	        out.print("Successfully Created a new School");		
	}

	
	
}
