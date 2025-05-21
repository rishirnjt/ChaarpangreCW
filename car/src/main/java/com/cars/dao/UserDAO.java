package com.cars.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.cars.database.DBConnection;
import com.cars.model.User;

public class UserDAO {
    private Connection conn;
    private PreparedStatement ps;

    public UserDAO() {
        try {
            this.conn = DBConnection.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // CREATE: Register User
    public boolean register(User user) {
        boolean isRegistered = false;
        String query = "INSERT INTO user(user_name, email_address, phone_number, role, password) VALUES(?, ?, ?, ?, ?)";
        if (conn != null) {
            try {
                ps = conn.prepareStatement(query);
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getPhone());
                ps.setString(4, user.getRole());
                ps.setString(5, user.getPassword());
                if (ps.executeUpdate() > 0) {
                    isRegistered = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isRegistered;
    }

    // READ: Login User by Email
    public User login(String email) {
        User user = null;
        String query = "SELECT * FROM user WHERE email_address = ?";
        if (conn != null) {
            try {
                ps = conn.prepareStatement(query);
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setName(rs.getString("user_name"));
                    user.setEmail(rs.getString("email_address"));
                    user.setPhone(rs.getString("phone_number"));
                    user.setRole(rs.getString("role"));
                    user.setPassword(rs.getString("password"));
                    user.setProfileImage(rs.getString("profile_image"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    // READ: Get User by ID
    public User getUserById(int id) {
        User user = null;
        String query = "SELECT * FROM user WHERE user_id = ?";
        if (conn != null) {
            try {
                ps = conn.prepareStatement(query);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setName(rs.getString("user_name"));
                    user.setEmail(rs.getString("email_address"));
                    user.setPhone(rs.getString("phone_number"));
                    user.setRole(rs.getString("role"));
                    user.setPassword(rs.getString("password"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public boolean updateUserImage(String email, String imagePath) throws ClassNotFoundException, SQLException {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println(">>> updateUserImage called for: " + email);
            System.out.println(">>> Image path to save: " + imagePath);

            String sql = "UPDATE user SET profile_image = ? WHERE email_address = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, imagePath);
            ps.setString(2, email);

            int rows = ps.executeUpdate();
            System.out.println(">>> Rows affected: " + rows);
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
 // READ: Get All Users
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM user";
        if (conn != null) {
            try {
                ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    User user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setName(rs.getString("user_name"));
                    user.setEmail(rs.getString("email_address"));
                    user.setPhone(rs.getString("phone_number"));
                    user.setRole(rs.getString("role"));
                    
                    userList.add(user);
                }
                System.out.println("Total users found: " + userList.size());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userList;
    }




    // DELETE: delete method for removing user from the list by admin
    public boolean deleteUser(int userId) {
        boolean deleted = false;
        String query = "DELETE FROM user WHERE user_id = ?";

        if (conn != null) {
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                // Set the ID in query
                ps.setInt(1, userId);
                // Execute deletion
                int rows = ps.executeUpdate();
                deleted = (rows > 0); // true if at least one row affected
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return deleted;
    }
}
