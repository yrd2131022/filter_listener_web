package com.yrd.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/testServlet")
public class TestServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("TestServlet.java...");
		String nameString = req.getParameter("name");
		System.out.println("TestServlet:"+nameString+":");
		String msgString = req.getParameter("msg");
		
		System.out.println("TestServlet:"+nameString+":"+msgString);
	}
	
	

}
