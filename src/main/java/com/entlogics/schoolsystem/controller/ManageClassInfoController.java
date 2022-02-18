package com.entlogics.schoolsystem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entlogics.schoolsystem.dao.ClassDAO;
import com.entlogics.schoolsystem.dao.IClassDAO;
import com.entlogics.schoolsystem.entity.Clazz;
import com.entlogics.schoolsystem.entity.School;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * This Servlet gets a class information, edits a class, and deletes a class
 */
@WebServlet("/classes/*")
public class ManageClassInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get the id from the request
		String id = request.getPathInfo().replace("/", "");		
		
		// change the id from string to int
		int classId = Integer.parseInt(id);
		
		// create the DAO object
		IClassDAO classDAO = new ClassDAO();
		
		// call the method to get the class information using DAO object
		Clazz clazz = classDAO.getClassInformation(classId);
		
		//create printwriter object
		PrintWriter out = response.getWriter();
		
		// set the response content type header
		response.setContentType("application/json");
		
		// set the response status code
		response.setStatus(200);
		
		// print the json response body
		out.print(new Gson().toJson(clazz));
		
	}
	
	
	// this method edits a class of a school
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get the id from the request
		String id = request.getPathInfo().replace("/", "");
		
		// change the id from string to int
		int classId = Integer.parseInt(id);
		
		// save the JSON request in a string for reuse
		String JSON = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		
		// get the json response body from request and change it the class object
		Clazz classData = new Gson().fromJson(JSON, Clazz.class);
		
//		System.out.println("CLASSDATA CONTROLLER "+classData);
		
		School schoolData = new Gson().fromJson(JSON, School.class);
		
//		System.out.println("SCHOOLDATA CONTROLLER "+schoolData);
		
		classData.setSchool(schoolData);
		
//		System.out.println("NEW CLASSDATA CONTROLLER "+classData);
		
		// create dao object
		IClassDAO classDAO = new ClassDAO();
		
		// call the method to update a class using dao object
		Boolean result = classDAO.updateClass(classId, classData);
		
		// create printwriter object
		PrintWriter out = response.getWriter();
		
		if (result) {
	        // Setting response status code to 200 OK
	        response.setStatus(200);
	        
	        // print the message
	        out.print("Successfully Updated the Class");		
        } else {
        	// Setting response status code to 404  Not Found
	        response.setStatus(404);
	        
	        // print the message
	        out.print("record not foundl");		
        }
		
	}



	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get the id from the request
		String id = request.getPathInfo().replace("/", "");
		
		// change the id from string to int
		int classId = Integer.parseInt(id);
		
		// create class DAO object
		IClassDAO classDAO = new ClassDAO();
		
		// call the method to delete a class using class dao object
		Boolean result = classDAO.deleteClass(classId);
		
		// create printwriter object
		PrintWriter out = response.getWriter();
		
		// Set the content type header of the response
        response.setContentType("application/json");
        
        if (result) {
	        // Setting response status code to 200 OK
	        response.setStatus(200);
	        
	        // create string message 
	        String json = "{\"message\":\"Successfully Deleted Class\"}";
	        
			// print the message
			out.print(new Gson().fromJson(json, JsonObject.class));
	        	
        } else {
        	// Setting response status code to 404  Not Found
	        response.setStatus(404);
	        
	        // create string message 
	        String json = "{\"message\":\"record not found\"}";
	        
			// print the message
			out.print(new Gson().fromJson(json, JsonObject.class));        
		}
		
	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
