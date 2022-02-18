package com.entlogics.schoolsystem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entlogics.schoolsystem.dao.ISchoolDAO;
import com.entlogics.schoolsystem.dao.SchoolDAO;
import com.entlogics.schoolsystem.entity.School;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 *		This Servlet gets a school information, updates a school, and deletes a school from the database
 */
public class ManageSchoolInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This method returns a school information
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

				// getting the id from the url
				String id = request.getPathInfo().replace("/","");
				
				// changing id from string to int
				int school_id = Integer.parseInt(id);
			

				// Printing the ID to the logs
				System.out.println("The ID "+school_id);
				
				// Create schoolDAO object to call SchoolDAO getSchoolInformation method
				ISchoolDAO schoolDAO = new SchoolDAO();				

				// Call SchoolDAO getSchools method
				School school = schoolDAO.getSchoolInformation(school_id);
				
				// Create Printwriter object 
		        PrintWriter out = response.getWriter();
		        
		        // Set the content type header of the response to JSON
		        response.setContentType("application/json");
		        
		        // print the data in JSON format
		        out.print(new Gson().toJson(school));		
	}

	/**
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * 	This Servlet updates the school
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			// getting the id from the url
			String id = request.getPathInfo().replace("/","");
			
			// changing id from string to int
			int school_id = Integer.parseInt(id);
		
			// get the JSON data from the request in a String
			String jsonData = request.getReader().lines().collect(Collectors.joining(System.lineSeparator())); 
				
			// Change the JSON String from json to School class and save it into a school object 
			School schoolData = new Gson().fromJson(jsonData, School.class);
					
			// Create schoolDAO object to call SchoolDAO getSchools method
			ISchoolDAO schoolDAO = new SchoolDAO();
						
			// Call SchoolDAO createSchool method
			Boolean result = schoolDAO.updateSchool(school_id, schoolData);
		
	}

	/**
	 * 	This Servlet deletes the school
	 */
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
					// getting the id from the url
					String id = request.getPathInfo().replace("/","");
					
					// changing id from string to int
					int school_id = Integer.parseInt(id);
							
					// get the JSON data from the request in a String
					String jsonData = request.getReader().lines().collect(Collectors.joining(System.lineSeparator())); 
						
					// Change the JSON String from json to School class and save it into a school object 
					School schoolData = new Gson().fromJson(jsonData, School.class);
							
					// Create schoolDAO object to call SchoolDAO getSchools method
					ISchoolDAO schoolDAO = new SchoolDAO();
								
					// Call SchoolDAO createSchool method
					Boolean result = schoolDAO.deleteSchool(school_id);
				
					// Create Printwriter object 
			        PrintWriter out = response.getWriter();
			        
			        // Set the content type header of the response
			        response.setContentType("application/json");
			        
			        if (result) {
				        // Setting response status code to 200 OK
				        response.setStatus(200);
				        
				        // create string message 
				        String json = "{\"message\":\"Successfully Deleted School\"}";
				        
						// print the message
						out.print(new Gson().fromJson(json, JsonObject.class));
				        
				        // print the message
//				        out.print("Successfully Deleted the School");		
			        } else {
			        	// Setting response status code to 404  Not Found
				        response.setStatus(404);
				        
				        // create string message 
				        String json = "{\"message\":\"record not found\"}";
				        
						// print the message
						out.print(new Gson().fromJson(json, JsonObject.class));       
			        }
	}
	
}
