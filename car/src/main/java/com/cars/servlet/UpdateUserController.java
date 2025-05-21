/*
 * package com.cars.servlet;
 * 
 * import com.cars.database.DBConnection; import com.cars.model.User;
 * 
 * import javax.servlet.RequestDispatcher; import
 * javax.servlet.ServletException; import
 * javax.servlet.annotation.MultipartConfig; import
 * javax.servlet.annotation.WebServlet; import javax.servlet.http.*; import
 * java.io.File; import java.io.FileOutputStream; import java.io.IOException;
 * import java.io.InputStream; import java.sql.Connection; import
 * java.sql.PreparedStatement;
 * 
 * @WebServlet("/UpdateUserController")
 * 
 * @MultipartConfig public class UpdateUserController extends HttpServlet {
 * private static final long serialVersionUID = 1L;
 * 
 * protected void doPost(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException {
 * 
 * HttpSession session = request.getSession(); User user = (User)
 * session.getAttribute("userWithSession");
 * 
 * if (user == null) { response.sendRedirect("login.jsp"); return; }
 * 
 * String name = request.getParameter("name"); String phone =
 * request.getParameter("phone");
 * 
 * Part filePart = request.getPart("profileImage"); String imageFileName =
 * filePart.getSubmittedFileName(); String imagePath = "";
 * 
 * try { Connection conn = DBConnection.getConnection(); String updateSQL;
 * 
 * if (imageFileName != null && !imageFileName.isEmpty()) { String uploadPath =
 * getServletContext().getRealPath("") + "img" + File.separator + "profiles";
 * File uploadDir = new File(uploadPath); if (!uploadDir.exists())
 * uploadDir.mkdirs();
 * 
 * String filePath = "img/profiles/" + imageFileName; imagePath = filePath;
 * 
 * // Save the image to disk FileOutputStream fos = new
 * FileOutputStream(uploadPath + File.separator + imageFileName); InputStream is
 * = filePart.getInputStream(); byte[] buffer = new byte[is.available()];
 * is.read(buffer); fos.write(buffer); fos.close();
 * 
 * updateSQL =
 * "UPDATE users SET name = ?, phone = ?, user_image = ? WHERE user_id = ?"; }
 * else { updateSQL = "UPDATE users SET name = ?, phone = ? WHERE user_id = ?";
 * }
 * 
 * PreparedStatement ps = conn.prepareStatement(updateSQL); ps.setString(1,
 * name); ps.setString(2, phone); if (imageFileName != null &&
 * !imageFileName.isEmpty()) { ps.setString(3, imagePath); ps.setInt(4,
 * user.getUserId()); } else { ps.setInt(3, user.getUserId()); }
 * 
 * int result = ps.executeUpdate();
 * 
 * if (result > 0) { user.setName(name); user.setPhone(phone); if
 * (!imagePath.isEmpty()) { user.setProfileImage(imagePath); }
 * session.setAttribute("userWithSession", user); RequestDispatcher rd =
 * request.getRequestDispatcher("pages/profile.jsp"); rd.forward(request,
 * response); } else { response.getWriter().println("Update failed."); }
 * 
 * } catch (Exception e) { e.printStackTrace(); } } }
 */
package com.cars.servlet;

import com.cars.database.DBConnection;
import com.cars.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/UpdateUserController")
public class UpdateUserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userWithSession");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String name = request.getParameter("name");
        String phone = request.getParameter("phone");

        // Input validation to avoid null values
        if (name == null || name.trim().isEmpty() || phone == null || phone.trim().isEmpty()) {
            response.getWriter().println("Name and Phone cannot be empty.");
            return;
        }

        try {
            Connection conn = DBConnection.getConnection();

            // Use correct table and where clause
            String updateSQL = "UPDATE user SET user_name = ?, phone_number = ? WHERE user_id = ?";
            PreparedStatement ps = conn.prepareStatement(updateSQL);
            ps.setString(1, name.trim());
            ps.setString(2, phone.trim());
            ps.setInt(3, user.getUserId());

            int result = ps.executeUpdate();

            if (result > 0) {
                user.setName(name);
                user.setPhone(phone);
                session.setAttribute("userWithSession", user);
                RequestDispatcher rd = request.getRequestDispatcher("pages/profile.jsp");
                rd.forward(request, response);
            } else {
                response.getWriter().println("Update failed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error occurred: " + e.getMessage());
        }
    }
}


