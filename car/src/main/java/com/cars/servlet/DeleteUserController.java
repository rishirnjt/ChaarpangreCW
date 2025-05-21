package com.cars.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cars.dao.UserDAO;

/**
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/DeleteUserController")
public class DeleteUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserController() {
        super();
        // TODO Auto-generated constructor stub
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
		//Get user id
		int userId = Integer.parseInt(request.getParameter("userId"));

		// calling dao metho for deleting user
        UserDAO userDAO = new UserDAO();
        boolean isDeleted = userDAO.deleteUser(userId);

        // using if condition 
        // if user deleted print user deleted in terminal 
        if (isDeleted) {
            System.out.println("User deleted successfully.");
        } else { // if user is not deleted print failed to delete
            System.out.println("Failed to delete user.");
        }

        // redirect back to the user list page
        response.sendRedirect(request.getContextPath() + "/UserListController");
	}

}
