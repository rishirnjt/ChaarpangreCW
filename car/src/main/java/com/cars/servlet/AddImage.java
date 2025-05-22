/*
 * package com.cars.servlet;
 * 
 * import javax.servlet.ServletException; import javax.servlet.annotation.*;
 * import javax.servlet.http.*; import java.io.*; import java.nio.file.Paths;
 * import java.sql.SQLException; import java.util.UUID;
 * 
 * import com.cars.dao.UserDAO; import com.cars.model.User;
 * 
 * @WebServlet("/AddImage")
 * 
 * @MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 *
 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50) public class AddImage extends
 * HttpServlet { private static final long serialVersionUID = 1L;
 * 
 * // Permanent upload folder (change as needed) private static final String
 * BASE_UPLOAD_DIR =
 * "C:/Users/acer/eclipse-workspace/car/src/main/webapp/uploads/profileImages/";
 * 
 * protected void doPost(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException {
 * 
 * HttpSession session = request.getSession(false); User user = (User)
 * session.getAttribute("userWithSession");
 * 
 * if (user == null) { response.sendRedirect("pages/login.jsp"); return; }
 * 
 * Part filePart = request.getPart("image"); if (filePart == null ||
 * filePart.getSize() == 0) { request.setAttribute("errorMessage",
 * "Please select an image to upload.");
 * request.getRequestDispatcher("pages/profile.jsp").forward(request, response);
 * return; }
 * 
 * // Generate unique filename String originalFileName =
 * Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); String
 * extension = originalFileName.substring(originalFileName.lastIndexOf("."));
 * String uniqueFileName = UUID.randomUUID().toString() + extension;
 * 
 * // Ensure directory exists File uploadDir = new File(BASE_UPLOAD_DIR); if
 * (!uploadDir.exists()) uploadDir.mkdirs();
 * 
 * // Save file File savedFile = new File(uploadDir, uniqueFileName); try
 * (InputStream input = filePart.getInputStream(); FileOutputStream output = new
 * FileOutputStream(savedFile)) {
 * 
 * byte[] buffer = new byte[1024]; int bytesRead; while ((bytesRead =
 * input.read(buffer)) != -1) { output.write(buffer, 0, bytesRead); } }
 * 
 * // Save relative path to DB String relativePath = "uploads/profileImages/" +
 * uniqueFileName;
 * 
 * try { System.out.println("Uploading image for user: " + user.getEmail());
 * System.out.println("Relative image path: " + relativePath);
 * 
 * UserDAO userDAO = new UserDAO(); userDAO.updateUserImage(user.getEmail(),
 * relativePath);
 * 
 * // Update session user.setProfileImage(relativePath);
 * session.setAttribute("userWithSession", user);
 * 
 * request.setAttribute("path", request.getContextPath() + "/" + relativePath);
 * request.setAttribute("message", "Profile image uploaded successfully.");
 * request.getRequestDispatcher("pages/profile.jsp").forward(request, response);
 * 
 * } catch (ClassNotFoundException | SQLException e) { e.printStackTrace();
 * throw new ServletException("Failed to update user image in DB", e); } } }
 */

package com.cars.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.UUID;

import com.cars.dao.CarDAO;
import com.cars.model.Car;

@WebServlet("/AddProductImage")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
                 maxFileSize = 1024 * 1024 * 10,
                 maxRequestSize = 1024 * 1024 * 50)
public class AddImage extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Absolute path to your project folder (change if needed)
    private static final String BASE_UPLOAD_DIR = "C:/Users/acer/eclipse-workspace/car/src/main/webapp/uploads/product_image/";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get parameters
        String carIdParam = request.getParameter("car_id");
        int carId;
        try {
            carId = Integer.parseInt(carIdParam);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid car ID.");
            request.getRequestDispatcher("pages/manageCars.jsp").forward(request, response);
            return;
        }

        // Get image file part
        Part filePart = request.getPart("image");
        if (filePart == null || filePart.getSize() == 0) {
            request.setAttribute("errorMessage", "Please select a product image to upload.");
            request.getRequestDispatcher("pages/manageCars.jsp").forward(request, response);
            return;
        }

        // Generate unique filename
        String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uniqueFileName = UUID.randomUUID().toString() + extension;

        // Ensure directory exists
        File uploadDir = new File(BASE_UPLOAD_DIR);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        // Save file
        File savedFile = new File(uploadDir, uniqueFileName);
        try (InputStream input = filePart.getInputStream();
             FileOutputStream output = new FileOutputStream(savedFile)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        }

        // Save relative path
        String relativePath = "uploads/product_image/" + uniqueFileName;

        try {
            CarDAO dao = new CarDAO();
            dao.updateCarImage(carId, relativePath); // Make sure this method exists in your DAO

            request.setAttribute("message", "Car image uploaded successfully.");
            request.getRequestDispatcher("pages/manageCars.jsp").forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new ServletException("Failed to update car image in DB", e);
        }
    }
}
