// UpdateCarServlet.java
package com.cars.servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.cars.dao.CarDAO;
import com.cars.model.Car;

@WebServlet("/UpdateCarController")
@MultipartConfig
public class UpdateCarController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CarDAO carDAO;

    public UpdateCarController() {
        super();
        carDAO = new CarDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int carId = Integer.parseInt(request.getParameter("carId"));
        try {
            Car car = carDAO.getCarById(carId);
            request.setAttribute("car", car);
            request.getRequestDispatcher("pages/updateCar.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching car details.");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int carId = Integer.parseInt(request.getParameter("carId"));
            String carName = request.getParameter("carName");
            String model = request.getParameter("model");
            String brand = request.getParameter("brand");
            String category = request.getParameter("category");
            String priceStr = request.getParameter("price");
            String colour = request.getParameter("colour");
            Part imagePart = request.getPart("imageUrl");

            Car currentCar = carDAO.getCarById(carId);

            if (carName == null || carName.isEmpty()) carName = currentCar.getCarName();
            if (model == null || model.isEmpty()) model = currentCar.getModel();
            if (brand == null || brand.isEmpty()) brand = currentCar.getBrand();
            if (category == null || category.isEmpty()) category = currentCar.getCategory();
            if (priceStr == null || priceStr.isEmpty()) priceStr = String.valueOf(currentCar.getPrice());
            if (colour == null || colour.isEmpty()) colour = currentCar.getColour();

            String imageUrl = currentCar.getImageUrl();
            if (imagePart != null && imagePart.getSize() > 0) {
                String fileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
                String uploadPath = getServletContext().getRealPath("/") + "newImg";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdir();

                imagePart.write(uploadPath + File.separator + fileName);
                imageUrl = "newImg/" + fileName;
            }

            double price = Double.parseDouble(priceStr);
            Car updatedCar = new Car(carId, carName, model, brand, category, price, colour, imageUrl);
            boolean isUpdated = carDAO.updateCar(updatedCar);

            if (isUpdated) {
                response.sendRedirect("CarListController");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update car.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Exception occurred during update.");
        }
    }
}