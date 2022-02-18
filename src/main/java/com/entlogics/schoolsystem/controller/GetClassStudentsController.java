package com.entlogics.schoolsystem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entlogics.schoolsystem.dao.ClassDAO;
import com.entlogics.schoolsystem.dao.IClassDAO;
import com.entlogics.schoolsystem.entity.Student;
import com.google.gson.Gson;

/**
 * this servlet gets the students of a class
 */
@WebServlet("/classes/students/*")
public class GetClassStudentsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * this methods gets the students of a class
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// get the id from the request
		String id = request.getPathInfo().replace("/", "");
		
		// change the id from string to int
		int classId = Integer.parseInt(id);
		
		// create the class DAO object
		IClassDAO classDAO = new ClassDAO();
		
		// call the method to get the students of a class using class DAO object
		Set<Student> setOfStudents = classDAO.getClassStudents(classId);
		
		// create the printwriter object
		PrintWriter out = response.getWriter();
		
		// set the response content type heade
		response.setContentType("application/json");
		
		// set the response status code
		response.setStatus(200);
		
		// print the json response body
		out.print(new Gson().toJson(setOfStudents));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
