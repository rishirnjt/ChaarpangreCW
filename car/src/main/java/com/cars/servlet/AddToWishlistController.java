package com.cars.servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.cars.dao.WishlistDAO;
import com.cars.model.User;

@WebServlet("/AddToWishlistController")
public class AddToWishlistController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("userWithSession");

        if (user == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String carIdParam = request.getParameter("carId");

        if (carIdParam != null && !carIdParam.isEmpty()) {
            int carId = Integer.parseInt(carIdParam);

            try {
                WishlistDAO dao = new WishlistDAO();
                dao.addToWishlist(user.getUserId(), carId);
                response.sendRedirect(request.getContextPath() + "/pages/product.jsp");
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
