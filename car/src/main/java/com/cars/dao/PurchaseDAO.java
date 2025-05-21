package com.cars.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cars.database.DBConnection;

public class PurchaseDAO {
	public boolean createPurchase(int userId, int carId, String totalPrice, String quantity) {
		 Connection conn = null;
		 PreparedStatement purchaseStmt = null;
		 PreparedStatement itemStmt = null;
		 ResultSet rs = null;
		 
		 try {
			 conn = DBConnection.getConnection();
			 conn.setAutoCommit(false);
			 
			 //insert into purchase table
			 String sql1 = "INSERT INTO purchase (user_id, car_id, total_price, quanity) VALUES (?,?,?,?)";
			 purchaseStmt = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
			 purchaseStmt.setInt(1, userId);
			 purchaseStmt.setInt(2, carId);
			 purchaseStmt.setDouble(3, Double.parseDouble(totalPrice)); 
			 purchaseStmt.setInt(4, Integer.parseInt(quantity));     
			 int affected = purchaseStmt.executeUpdate();
			 
			 if(affected == 0) {
				 conn.rollback();
				 return false;
			 }
			 
			 rs = purchaseStmt.getGeneratedKeys();
			 int purchaseId = -1;
			 if(rs.next()) {
				 purchaseId = rs.getInt(1);
			 }
			 
			 if(purchaseId == -1) {
				 conn.rollback();
				 return false;
			 }
			
			 //insert into purchase_item
			  String sql2 = "INSERT INTO purchase_item (purchase_id) VALUES (?)";
	            itemStmt = conn.prepareStatement(sql2);
	            itemStmt.setInt(1, purchaseId);
	            itemStmt.executeUpdate();

	            // Commit the transaction
	            conn.commit();
	            return true;

	        } catch (Exception e) {
	            e.printStackTrace();
	            try {
	                if (conn != null) conn.rollback();
	            } catch (SQLException ignored) {}
	            return false;

	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (purchaseStmt != null) purchaseStmt.close();
	                if (itemStmt != null) itemStmt.close();
	                if (conn != null) {
	                    conn.setAutoCommit(true); // Reset to default
	                    conn.close();
	                }
	            } catch (Exception ignored) {}
	        }
	    }
	}