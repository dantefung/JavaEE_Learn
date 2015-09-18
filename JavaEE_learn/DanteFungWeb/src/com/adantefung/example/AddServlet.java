package com.adantefung.example;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.getWriter().write("------add-----");
		System.out.println("-----add-----");
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/Case/addStudent.html");
		System.out.println(rd);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		rd.forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
