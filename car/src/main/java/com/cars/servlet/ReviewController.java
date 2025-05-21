package com.cars.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cars.dao.ReviewDAO;
import com.cars.model.Review;
import com.cars.model.User;

/**
 * Servlet implementation class ReviewController
 */
@WebServlet("/ReviewController")
public class ReviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewController() {
        super();
        // TODO Auto-generated constructor stub
    }

  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ReviewDAO reviewDAO = new ReviewDAO();
        List<Review> reviews = reviewDAO.getAllReviews(); // Method to fetch all reviews

        request.setAttribute("reviewList", reviews);
        request.getRequestDispatcher("/pages/Review.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	   @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Geting the logged-in user from session
	        HttpSession session = request.getSession(false);
	        User user = (session != null) ? (User) session.getAttribute("userWithSession") : null;

	        if (user == null) {
	            // No logged-in user, redirect to login page
	            response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
	            return;
	        }

	        int userId = user.getUserId();
	        String rating = request.getParameter("rating");
	        String comment = request.getParameter("comment");

	        if (rating == null || rating.trim().isEmpty() || comment == null || comment.trim().isEmpty()) {
	            request.setAttribute("errorMessage", "Rating and comment cannot be empty.");
	            request.getRequestDispatcher("/pages/home.jsp").forward(request, response);
	            return;
	        }

	        Review review = new Review();
	        review.setUserID(userId);
	        review.setRating(rating.trim());
	        review.setComment(comment.trim());

	        ReviewDAO reviewDAO = new ReviewDAO();
	        boolean inserted = reviewDAO.insertReview(review);

	        if (inserted) {
	            // Success message in session or request scope
	            request.setAttribute("successMessage", "Review submitted successfully.");
	            request.getRequestDispatcher("/pages/home.jsp").forward(request, response);
	        } else {
	            request.setAttribute("errorMessage", "Failed to submit review. Please try again.");
	            request.getRequestDispatcher("/pages/home.jsp").forward(request, response);
	        }

	}

}
