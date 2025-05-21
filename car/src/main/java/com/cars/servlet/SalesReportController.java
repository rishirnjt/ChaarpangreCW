package com.cars.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cars.model.SalesReport;
import com.cars.dao.SalesReportDAO;

@WebServlet("/SalesReportController")
public class SalesReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SalesReportController() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Create DAO object
		SalesReportDAO dao = new SalesReportDAO();

		// Get sales data
		List<SalesReport> salesList = dao.getAllSales();
		int totalCarsSold = dao.getTotalCarsSold();
		double totalRevenue = dao.getTotalRevenue();

		// Set attributes
		request.setAttribute("salesList", salesList);
		request.setAttribute("totalCarsSold", totalCarsSold);
		request.setAttribute("totalRevenue", totalRevenue);

		// Forward to JSP
		request.getRequestDispatcher("/pages/salesReport.jsp").forward(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
