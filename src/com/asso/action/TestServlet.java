package com.asso.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TestServlet extends HttpServlet  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TestServlet(){		
		
	}	
	
	public void init(){	 
		 
	}
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("length: " + request.getParameterValues("date1").length);
		
	}

	
	
}
