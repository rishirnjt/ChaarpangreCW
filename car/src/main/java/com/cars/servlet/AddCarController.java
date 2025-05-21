package com.cars.servlet;

import com.cars.dao.CarDAO;

import com.cars.model.Car;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@WebServlet("/AddCarController")
@MultipartConfig
public class AddCarController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddCarController() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try {
            String name = request.getParameter("car_name");
            String model = request.getParameter("model");
            String brand = request.getParameter("brand");
            String category = request.getParameter("category");
            String colour = request.getParameter("colour");
            String priceParam = request.getParameter("price");

            if (name == null || model == null || brand == null || category == null || colour == null || 
                priceParam == null ||
                name.isEmpty() || model.isEmpty() || brand.isEmpty() || category.isEmpty() || 
                colour.isEmpty() || priceParam.isEmpty()) {
                response.getWriter().println("All fields are required.");
                return;
            }

            double price;
            int quantity;
            try {
                price = Double.parseDouble(priceParam);
            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid numeric values for price.");
                return;
            }
            
            // Handle image upload
            Part imagePart = request.getPart("image");
            if (imagePart == null || imagePart.getSize() == 0) {
                response.getWriter().println("Image is required.");
                return;
            }
            
            String originalFileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

            String uploadPath = getServletContext().getRealPath("") + File.separator + "img";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            imagePart.write(uploadPath + File.separator + uniqueFileName);
            String imageUrl = "img/" + uniqueFileName;

            // Create Car object and save to DB
            Car car = new Car(name, model, brand, category, price, colour, imageUrl);
            CarDAO dao = new CarDAO();

            if (dao.insertCar(car)) {
            	request.setAttribute("message", "Car added successfully.");
            	request.getRequestDispatcher("/pages/addCar.jsp").forward(request, response);

            } else {
                response.getWriter().println("Failed to add car. Please try again.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}