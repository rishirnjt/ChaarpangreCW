package com.cars.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cars.dao.WishlistDAO;
import com.cars.model.User;
import com.cars.model.WishlistItem;

@WebServlet("/WishlistController")
public class WishlistController extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("userWithSession");
		
		if (user == null) {
			response.sendRedirect("pages/login.jsp");
			return;
		}
		
		try {
			WishlistDAO dao = new WishlistDAO();
			List<WishlistItem> wishlist = dao.getWishlistByUserId(user.getUserId());
			request.setAttribute("wishlist", wishlist);
			request.getRequestDispatcher("/pages/wishlist.jsp").forward(request, response);
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new ServletException("Error loading wishlist", e);
		}
	}

}
