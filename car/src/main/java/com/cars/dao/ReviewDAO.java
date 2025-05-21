package com.cars.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cars.database.DBConnection;
import com.cars.model.Review;

public class ReviewDAO {
    public boolean insertReview(Review review) {
     String sql = "INSERT INTO review (user_id, rating, comment) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, review.getUserID());
            ps.setString(2, review.getRating());
            ps.setString(3, review.getComment());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT r.review_id, r.user_id, r.rating, r.comment, u.user_name " +
                     "FROM review r JOIN user u ON r.user_id = u.user_id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Review review = new Review();
                review.setReviewID(rs.getInt("review_id"));
                review.setUserID(rs.getInt("user_id"));
                review.setRating(rs.getString("rating"));
                review.setComment(rs.getString("comment"));
                review.setUserName(rs.getString("user_name"));

                reviews.add(review);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return reviews;
    }

}