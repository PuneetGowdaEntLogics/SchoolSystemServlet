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
import com.entlogics.schoolsystem.entity.Exam;
import com.google.gson.Gson;

/**
 * this servlet gets the exams of a class
 */
@WebServlet("/classes/exams/*")
public class GetClassExamsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * this method gets the class exams
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// get the id from the request
		String id = request.getPathInfo().replace("/", "");
		
		// change the id from string to int
		int classId = Integer.parseInt(id);
		
		// create the class dao object
		IClassDAO classDAO = new ClassDAO();
		
		// call the method to get the exams of a class using class dao object
		Set<Exam> setOfExams = classDAO.getClassExams(classId);
		
		// create the printwriter object
		PrintWriter out = response.getWriter();
		
		// set the response content type header
		response.setContentType("application/json");
		
		// set the response status
		response.setStatus(200);
		
		// print the json response body
		out.print(new Gson().toJson(setOfExams));
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
