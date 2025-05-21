package com.cars.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cars.dao.CarDAO;
import com.cars.model.Car;

@WebServlet("/CarListController")
public class CarListController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Constructor
    public CarListController() {
        super();
    }

    // Handles GET requests
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CarDAO carDAO = new CarDAO();
        List<Car> cars = carDAO.getAllCars();
        request.setAttribute("cars", cars);
        request.getRequestDispatcher("/pages/adminCar.jsp").forward(request, response);
    }

    // Handles POST requests (delegates to doGet)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
