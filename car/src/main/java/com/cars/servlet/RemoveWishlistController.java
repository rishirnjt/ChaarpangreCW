package com.cars.servlet;

import com.cars.dao.WishlistDAO;
import com.cars.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/RemoveWishlistController")
public class RemoveWishlistController extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("userWithSession");

        if (user == null) {
            response.sendRedirect("pages/login.jsp");
            return;
        }

        String wishlistIdStr = request.getParameter("wishlistId");

        if (wishlistIdStr != null) {
            try {
                int wishlistId = Integer.parseInt(wishlistIdStr);
                WishlistDAO dao = new WishlistDAO();
                boolean success = dao.removeFromWishlist(wishlistId);

                if (success) {
                    response.sendRedirect("WishlistController");
                } else {
                    response.getWriter().println("Failed to remove item from wishlist.");
                }
            } catch (NumberFormatException | SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                throw new ServletException("Error removing wishlist item", e);
            }
        } else {
            response.sendRedirect("WishlistController");
        }
    }
}
