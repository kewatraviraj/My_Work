package com.basic.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.basic.dao.Dao;
import com.basic.daoImpl.DaoImpl;
import com.basic.pojo.AddressPojo;
import com.basic.pojo.FilesPojo;
import com.basic.pojo.UserPojo;

/**
 * Servlet implementation class ControllerRegister
 */
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
public class ControllerRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
		UserPojo userpojo;
		AddressPojo addresspojo;
		FilesPojo filepojo;
		Dao service;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerRegister() {
        // TODO Auto-generated constructor stub
    	userpojo = new UserPojo();
    	addresspojo = new AddressPojo();
    	filepojo = new FilesPojo();
        service = new DaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			try {
				request.setAttribute("users", service.getAllUser());
				RequestDispatcher redirect = request.getRequestDispatcher("/display.jsp");
				
				redirect.forward(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		userpojo.setRole_id(Integer.parseInt(request.getParameter("role")));
		userpojo.setFirstname(request.getParameter("firstName"));
		userpojo.setLastname(request.getParameter("lastName"));
		userpojo.setEmail(request.getParameter("email"));
		userpojo.setMobile_no(Long.parseLong(request.getParameter("mobileNo")));
		userpojo.setPassword(request.getParameter("password"));
		userpojo.setGender(request.getParameter("gender"));
		userpojo.setDate_of_birth(request.getParameter("dateOfBirth"));
		
		addresspojo.setAddress_line1(request.getParameter("address_line1"));
		addresspojo.setAddress_line2(request.getParameter("address_line2"));
		addresspojo.setCity(request.getParameter("city"));
		addresspojo.setState(request.getParameter("state"));
		addresspojo.setCountry(request.getParameter("country"));
		addresspojo.setPincode(Integer.parseInt(request.getParameter("pincode")));
		
		filepojo.setFile_type(request.getParameter("file_type"));
		filepojo.setFile(request.getPart("file").getInputStream());
		
		try {
			if(service.insert(userpojo, null)) {
				service.saveaddress(addresspojo);
				service.savefile(filepojo);
				request.setAttribute("users", service.getAllUser());
				request.setAttribute("files", service.getFiles());
				request.setAttribute("address", service.getAddress());
				
				RequestDispatcher redirect = request.getRequestDispatcher("display.jsp");
				redirect.forward(request, response);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
