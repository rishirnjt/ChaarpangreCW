package com.cars.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cars.dao.CarDAO;
import com.cars.dao.PurchaseDAO;
import com.cars.model.Car;
import com.cars.model.User;

@WebServlet("/PurchaseController")
public class PurchaseController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("userWithSession");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
            return;
        }

        String carIdParam = request.getParameter("carId");
        String quantityParam = request.getParameter("quantity");

        if (carIdParam == null || quantityParam == null || carIdParam.isEmpty() || quantityParam.isEmpty()) {
            response.getWriter().println("Car ID and Quantity are required.");
            return;
        }

        int carId = Integer.parseInt(carIdParam);
        int quantity = Integer.parseInt(quantityParam);

        CarDAO carDAO = new CarDAO();
        Car car = null;
        try {
            car = carDAO.getCarById(carId);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (car == null) {
            response.getWriter().println("Car not found.");
            return;
        }

        double totalPrice = car.getPrice() * quantity;

        PurchaseDAO purchaseDAO = new PurchaseDAO();
        boolean success = purchaseDAO.createPurchase(user.getUserId(), carId, String.valueOf(totalPrice), String.valueOf(quantity));

        if (success) {
            // Set attributes for confirmation page
            request.setAttribute("carName", car.getCarName());
            request.setAttribute("price", totalPrice);
            request.setAttribute("message", "Thank you for purchasing " + car.getCarName() + "!");

            // Forward to confirmation.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/purchaseSuccess.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("Error forwarding to confirmation page.");
            }
        } else {
            response.getWriter().println("Purchase failed. Try again.");
        }
    }
}
