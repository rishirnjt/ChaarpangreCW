package com.cars.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

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

            Car currentCar = carDAO.getCarById(carId);

            if (carName == null || carName.isEmpty()) carName = currentCar.getCarName();
            if (model == null || model.isEmpty()) model = currentCar.getModel();
            if (brand == null || brand.isEmpty()) brand = currentCar.getBrand();
            if (category == null || category.isEmpty()) category = currentCar.getCategory();
            if (priceStr == null || priceStr.isEmpty()) priceStr = String.valueOf(currentCar.getPrice());
            if (colour == null || colour.isEmpty()) colour = currentCar.getColour();

            double price = Double.parseDouble(priceStr);
            String imageUrl = currentCar.getImageUrl();

            // Handle new image upload
            Part imagePart = request.getPart("imageUrl");
            if (imagePart != null && imagePart.getSize() > 0) {
                String originalFileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
                String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                String uniqueFileName = UUID.randomUUID().toString() + fileExtension;

             // Get real path of uploads/product_image in deployed app folder
                String uploadPath = getServletContext().getRealPath("/uploads/product_image/");
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdirs();

                
                // saving file to disk
                File savedFile = new File(uploadDir, uniqueFileName);
                try (InputStream input = imagePart.getInputStream();
                     FileOutputStream output = new FileOutputStream(savedFile)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = input.read(buffer)) != -1) {
                        output.write(buffer, 0, bytesRead);
                    }
                }

                imageUrl = "uploads/product_image/" + uniqueFileName;
            }

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
