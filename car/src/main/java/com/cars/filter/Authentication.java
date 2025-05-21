package com.cars.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

@WebFilter(urlPatterns = {
	    "/PurchaseController",
	    "/AddToWishlistController"
	})
  public class Authentication implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("AuthenticationFilter initialized");
    }

    @Override
    public void destroy() {
        System.out.println("AuthenticationFilter destroyed");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        HttpSession session = req.getSession(false);

        boolean userLoggedIn = session != null && session.getAttribute("userWithSession") != null;
        boolean adminLoggedIn = session != null && session.getAttribute("admin") != null;
        boolean loggedIn = userLoggedIn || adminLoggedIn;

        boolean isLoginRequest = uri.endsWith("login.jsp") || uri.endsWith("LogInController");
        boolean isRegisterRequest = uri.endsWith("register.jsp");

        if (loggedIn) {
            if (isLoginRequest) {
                if (adminLoggedIn) {
                    res.sendRedirect(req.getContextPath() + "/pages/adminDashboard.jsp");
                } else if (userLoggedIn) {
                    res.sendRedirect(req.getContextPath() + "/pages/home.jsp");
                }
                return;
            } else {
                chain.doFilter(request, response);
            }
        } else {
            // If not logged in and trying to access a restricted page (not login or register)
            if (!isLoginRequest && !isRegisterRequest) {
                res.sendRedirect(req.getContextPath() + "/pages/login.jsp");
                return;
            }

            // Allow access to login and register pages
            chain.doFilter(request, response);
        }
    }
}
