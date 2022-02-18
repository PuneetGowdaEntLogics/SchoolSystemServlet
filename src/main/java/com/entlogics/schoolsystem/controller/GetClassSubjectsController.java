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
import com.entlogics.schoolsystem.entity.Subject;
import com.google.gson.Gson;

/**
 * this servlet gets the subjects of a class
 */
@WebServlet("/classes/subjects/*")
public class GetClassSubjectsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * this method gets the set of class subjects
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// get the id from the request
		String id = request.getPathInfo().replace("/", "");
		
		// change the id from string to int
		int classId = Integer.parseInt(id);
		
		// create the class dao object
		IClassDAO classDAO = new ClassDAO();
		
		// call the method to get set of exam subjects using class dao object
		Set<Subject> setOfSubjects = classDAO.getClassSubjects(classId);
		
		// create printwriter object
		PrintWriter out = response.getWriter();
		
		// set the response content type header
		response.setContentType("application/json");
		
		// set the response status code
		response.setStatus(200);
		
		// print the json response body
		out.print(new Gson().toJson(setOfSubjects));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
