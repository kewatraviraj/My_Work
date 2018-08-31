package org.testController;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.testDao.Dao;
import org.testDaoImpl.DaoImpl;
import org.testPojo.Pojo;

/**
 * Servlet implementation class ExampleDirect
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
	Dao testservice;
	Pojo testpojo;
	
    public Controller() {
        // TODO Auto-generated constructor stub
    	testservice = new DaoImpl();
    	testpojo = new Pojo();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		try {
				String action = request.getParameter("action");
				if(action.equals("delete")) {
					boolean result = testservice.del(Integer.parseInt(request.getParameter("id")));
					if(result) {
						request.setAttribute("message", "Successfully Deleted");
					}	
				}
					request.setAttribute("users", testservice.getAllUser());
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
			int success;
			try {
				testpojo.setValue1(request.getParameter("test"));
				testpojo.setValue2(request.getParameter("test2"));
				testpojo.setName(request.getParameter("testname"));
				
				success = testservice.insert(testpojo);
				if(success == 1) { 
					request.setAttribute("users",testservice.getAllUser());
					RequestDispatcher redirect = request.getRequestDispatcher("display.jsp");
					redirect.forward(request, response);
					//	response.sendRedirect("index.jsp");
				}	
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
