package com.basic.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.basic.dao.Dao;
import com.basic.daoImpl.DaoImpl;
import com.basic.pojo.AddressPojo;

/**
 * Servlet implementation class ControllerUpdate
 */
public class ControllerUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AddressPojo addresspojo;
	Dao service;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerUpdate() {
        // TODO Auto-generated constructor stub
    	addresspojo = new AddressPojo();
        service = new DaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		addresspojo.setAddress_line1(request.getParameter("address_line1"));
		addresspojo.setAddress_line2(request.getParameter("addrss_line2"));
		addresspojo.setCity(request.getParameter("city"));
		addresspojo.setState(request.getParameter("state"));
		addresspojo.setCountry(request.getParameter("country"));
		addresspojo.setPincode(Integer.parseInt(request.getParameter("pincode")));
	}

}
