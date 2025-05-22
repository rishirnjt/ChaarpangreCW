package com.cars.servlet;

import com.cars.dao.CarDAO;
import com.cars.model.Car;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/CarDetailsController")
public class CarDetailsController extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CarDAO carDAO;

    @Override
    public void init() {
        carDAO = new CarDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String carIdParam = request.getParameter("carId");

        if (carIdParam != null) {
            try {
                int carId = Integer.parseInt(carIdParam);
                Car car = carDAO.getCarById(carId);
                request.setAttribute("car", car);
                request.getRequestDispatcher("/pages/carDetails.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving car details.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing carId parameter.");
        }
    }
}
