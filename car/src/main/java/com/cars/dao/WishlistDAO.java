package com.cars.dao;

import com.cars.database.DBConnection;
import com.cars.model.WishlistItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WishlistDAO {

    public List<WishlistItem> getWishlistByUserId(int userId) throws SQLException, ClassNotFoundException {
        List<WishlistItem> list = new ArrayList<>();

        String query = "SELECT w.wish_list_id, w.car_id, c.car_name, c.brand, c.price, c.image_url " +
                       "FROM wish_list w " +
                       "JOIN car c ON w.car_id = c.car_id " +
                       "WHERE w.user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                WishlistItem item = new WishlistItem();
                item.setWishlistId(rs.getInt("wish_list_id"));
                item.setCarId(rs.getInt("car_id"));
                item.setCarName(rs.getString("car_name"));
                item.setBrand(rs.getString("brand"));
                item.setPrice(rs.getDouble("price"));
                item.setImageUrl(rs.getString("image_url"));
                list.add(item);
            }
        }

        return list;
    }

    public boolean addToWishlist(int userId, int carId) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO wish_list (user_id, car_id) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setInt(2, carId);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean removeFromWishlist(int wishlistId) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM wish_list WHERE wish_list_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, wishlistId);
            return ps.executeUpdate() > 0;
    }
}
}
