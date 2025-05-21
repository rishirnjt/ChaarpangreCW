package com.cars.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cars.dao.CarDAO;
import com.cars.model.Car;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 try {
             String searchTerm = request.getParameter("searchTerm");
             CarDAO dao = new CarDAO();

             List<Car> cars;
             if (searchTerm != null && !searchTerm.trim().isEmpty()) {
                 cars = dao.searchCars(searchTerm);  // Use the search method
                 request.setAttribute("searchTerm", searchTerm);
             } else {
                 cars = dao.getAllCars();  // If no search term, fetch all cars
             }

             request.setAttribute("cars", cars);
             RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/product.jsp");
             dispatcher.forward(request, response);

         } catch (Exception e) {
             e.printStackTrace();
             // Optionally, forward to an error page or handle gracefully
             request.setAttribute("errorMessage", "An error occurred while processing your request.");
             RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/error.jsp");
             dispatcher.forward(request, response);
         }
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
