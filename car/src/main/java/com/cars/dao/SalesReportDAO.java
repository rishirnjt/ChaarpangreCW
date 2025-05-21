package com.cars.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.cars.model.*;

import java.util.ArrayList;
import java.util.List;

import com.cars.database.DBConnection;

public class SalesReportDAO {
	 public List<SalesReport> getAllSales() {
	        List<SalesReport> salesList = new ArrayList<>();

	        String query = "SELECT p.purchase_id, p.quanity, p.total_price, " +
	                       "c.car_id, c.car_name, c.model, " +
	                       "u.user_id, u.user_name " +
	                       "FROM purchase p " +
	                       "JOIN car c ON p.car_id = c.car_id " +
	                       "JOIN user u ON p.user_id = u.user_id";

	        try (Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(query);
	             ResultSet rs = ps.executeQuery()) {

	            while (rs.next()) {
	                SalesReport report = new SalesReport();
	                report.setPurchaseId(rs.getInt("purchase_id"));
	                report.setQuantity(Integer.parseInt(rs.getString("quanity")));
	                report.setTotalPrice(rs.getString("total_price"));

	                // Car
	                Car car = new Car();
	                car.setCarId(rs.getInt("car_id"));
	                car.setCarName(rs.getString("car_name"));
	                car.setModel(rs.getString("model"));
	                report.setCar(car);

	                // User
	                User user = new User();
	                user.setUserId(rs.getInt("user_id"));
	                user.setName(rs.getString("user_name")); // Ensure this method exists in User.java
	                report.setBuyer(user);

	                salesList.add(report);
	            }

	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace(); // You may log this error or throw a custom exception instead
	        }

	        return salesList;
	    }

	    public int getTotalCarsSold() {
	        int total = 0;
	        String query = "SELECT SUM(CAST(quanity AS UNSIGNED)) AS total FROM purchase";

	        try (Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(query);
	             ResultSet rs = ps.executeQuery()) {

	            if (rs.next()) {
	                total = rs.getInt("total");
	            }

	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }

	        return total;
	    }

	    public double getTotalRevenue() {
	        double revenue = 0.0;
	        String query = "SELECT SUM(CAST(total_price AS DECIMAL(10,2))) AS revenue FROM purchase";

	        try (Connection con = DBConnection.getConnection();
	             PreparedStatement ps = con.prepareStatement(query);
	             ResultSet rs = ps.executeQuery()) {

	            if (rs.next()) {
	                revenue = rs.getDouble("revenue");
	            }

	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }

	        return revenue;
	    }
}
