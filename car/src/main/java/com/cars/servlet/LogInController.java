package com.cars.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.cars.dao.UserDAO;
import com.cars.model.User;
import com.cars.utility.EncryptDecrypt;

@WebServlet(asyncSupported = true, urlPatterns = { "/LogInController" })
public class LogInController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LogInController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("register".equalsIgnoreCase(action)) {
            handleRegister(request, response);
        } else if ("login".equalsIgnoreCase(action)) {
            handleLogin(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email_address = request.getParameter("email");
        String password = request.getParameter("password");

        if (email_address == null || password == null || email_address.trim().isEmpty() || password.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Email and password cannot be empty.");
            request.getRequestDispatcher("pages/login.jsp").forward(request, response);
            return;
        }

        // Check if the person is admin
        if (email_address.equals("ramadmin@pangre.com") && password.equals("admin12345")) {
            HttpSession session = request.getSession();
            session.setAttribute("admin", true);
            session.setMaxInactiveInterval(30 * 30);
            response.sendRedirect(request.getContextPath() + "/pages/adminDashboard.jsp");
            return;
        }

        UserDAO userDAO = new UserDAO();
        User user = userDAO.login(email_address.trim());

        if (user != null) {
            String encryptedInputPassword = EncryptDecrypt.encrypt(password.trim());
            if (encryptedInputPassword.equals(user.getPassword())) {
                // Login success
                HttpSession session = request.getSession();
                session.setAttribute("userWithSession", user);
                session.setMaxInactiveInterval(60 * 60);
                response.sendRedirect(request.getContextPath() + "/pages/home.jsp");
            } else {
                request.setAttribute("errorMessage", "Invalid email or password. Please try again.");
                request.getRequestDispatcher("pages/login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "Invalid email or password. Please try again.");
            request.getRequestDispatcher("pages/login.jsp").forward(request, response);
        }
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String role = request.getParameter("role");
        String password = request.getParameter("password");

        if (name == null || email == null || phone == null || role == null || password == null ||
            name.trim().isEmpty() || email.trim().isEmpty() || phone.trim().isEmpty() || 
            role.trim().isEmpty() || password.trim().isEmpty()) {
            request.setAttribute("errorMessage", "All fields are required.");
            request.getRequestDispatcher("pages/register.jsp").forward(request, response);
            return;
        }

        try {
            // Encrypt the password before saving
            String encryptedPassword = EncryptDecrypt.encrypt(password.trim());

            User user = new User();
            user.setName(name.trim());
            user.setEmail(email.trim());
            user.setPhone(phone.trim());
            user.setRole(role.trim());
            user.setPassword(encryptedPassword); // Set encrypted password
     

            UserDAO userDAO = new UserDAO();
            boolean success = userDAO.register(user);

            if (success) {
                response.sendRedirect("pages/login.jsp");
            } else {
                request.setAttribute("errorMessage", "Registration failed. Try again.");
                request.getRequestDispatcher("pages/register.jsp").forward(request, response);
            }

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid input. Please check the entered data.");
            request.getRequestDispatcher("pages/register.jsp").forward(request, response);
        }
    }
}
