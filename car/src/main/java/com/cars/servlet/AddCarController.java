package com.cars.servlet;

import com.cars.dao.CarDAO;
import com.cars.model.Car;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
            // Retrieve form parameters
            String name = request.getParameter("car_name");
            String model = request.getParameter("model");
            String brand = request.getParameter("brand");
            String category = request.getParameter("category");
            String colour = request.getParameter("colour");
            String priceParam = request.getParameter("price");

            // Validate input
            if (name == null || model == null || brand == null || category == null || colour == null ||
                priceParam == null ||
                name.isEmpty() || model.isEmpty() || brand.isEmpty() || category.isEmpty() ||
                colour.isEmpty() || priceParam.isEmpty()) {
                response.getWriter().println("All fields are required.");
                return;
            }

            double price;
            try {
                price = Double.parseDouble(priceParam);
            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid numeric value for price.");
                return;
            }

            // Handle image upload
            Part imagePart = request.getPart("image");
            if (imagePart == null || imagePart.getSize() == 0) {
                response.getWriter().println("Image is required.");
                return;
            }

            // Generate unique file name
            String originalFileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

            // Get real path dynamically
            String uploadPath = getServletContext().getRealPath("/uploads/product_image/");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            // Save file
            File savedFile = new File(uploadDir, uniqueFileName);
            try (InputStream input = imagePart.getInputStream();
                 FileOutputStream output = new FileOutputStream(savedFile)) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
            }

            // Save relative path to DB
            String relativePath = "uploads/product_image/" + uniqueFileName;

            System.out.println("Relative image path: " + relativePath);
            System.out.println("File exists: " + savedFile.exists());

            // Create Car object with image path
            Car car = new Car(name, model, brand, category, price, colour, relativePath);
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