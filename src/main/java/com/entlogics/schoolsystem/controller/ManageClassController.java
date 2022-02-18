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

/**
 * This Servlet adds a class in the database
 */
@WebServlet("/classes")
public class ManageClassController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * This method adds a class
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// print to confirm servlet is called
		System.out.println("IN CONTROLLER");

		// store the JSON string to reuse
		String JSON = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		
		//  get the JSON body from the request and change the data to class object
		Clazz classData = new Gson().fromJson(JSON, Clazz.class);
		
		// get the JSON body from the request and change the data to school object
		School schoolData = new Gson().fromJson(JSON, School.class);
		
		// print the class data
		System.out.println("CLASS DATA "+classData);
		
		// print the school data
		System.out.println("SCHOOL DATA "+schoolData);
		
		// create classDAO object
		IClassDAO classDAO = new ClassDAO();
		
		// execute the method to add a class using DAO object
		Boolean result = classDAO.createClass(schoolData, classData);
		
		// create the printwriter object
		PrintWriter out = response.getWriter();
		
		// set the response content type header
		response.setContentType("application/json");
		
		// set the response status code
		response.setStatus(201);
		
		// print the message
		out.print("Successfully created a new class");
	}

}
