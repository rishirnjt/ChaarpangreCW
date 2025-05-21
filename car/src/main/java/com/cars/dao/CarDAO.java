package com.cars.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cars.database.DBConnection;
import com.cars.model.Car;

public class CarDAO {

    public boolean insertCar(Car car) {
        String sql = "INSERT INTO car (car_name, model, brand, category, price, colour, image_url) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, car.getCarName());
            ps.setString(2, car.getModel());
            ps.setString(3, car.getBrand());
            ps.setString(4, car.getCategory());
            ps.setDouble(5, car.getPrice());
            ps.setString(6, car.getColour());
            ps.setString(7, car.getImageUrl());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    //get all car lists
    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM car";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Car car = new Car(
                    rs.getString("car_name"),
                    rs.getString("model"),
                    rs.getString("brand"),
                    rs.getString("category"),
                    rs.getDouble("price"),
                    rs.getString("colour"),
                    rs.getString("image_url")
                );
                car.setCarId(rs.getInt("car_id"));
                cars.add(car);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cars;
    }

    // Search filter 
    public List<Car> searchCars(String searchTerm) throws ClassNotFoundException {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM car WHERE car_name LIKE ? OR model LIKE ? OR brand LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String searchPattern = "%" + searchTerm + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            ps.setString(3, searchPattern);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Car car = new Car(
                        rs.getString("car_name"),
                        rs.getString("model"),
                        rs.getString("brand"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getString("colour"),
                        rs.getString("image_url")
                    );
                    car.setCarId(rs.getInt("car_id"));
                    cars.add(car);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    // Get car by ID
    public Car getCarById(int carId) throws ClassNotFoundException {
        String sql = "SELECT * FROM car WHERE car_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, carId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Car car = new Car(
                        rs.getString("car_name"),
                        rs.getString("model"),
                        rs.getString("brand"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getString("colour"),
                        rs.getString("image_url")
                        );
                    car.setCarId(rs.getInt("car_id"));
                    return car;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
 // update car by admin
 	public boolean updateCar(Car car) throws ClassNotFoundException, SQLException {
 	    String sql = "UPDATE car SET car_name = ?, model = ?, brand = ?, category = ?, price = ?, colour = ?, image_url = ? WHERE car_id = ?";
 	    
 	    try(Connection conn = DBConnection.getConnection();
 	            PreparedStatement ps = conn.prepareStatement(sql)) {
 	        
 	        ps.setString(1, car.getCarName());
 	        ps.setString(2, car.getModel());
 	        ps.setString(3, car.getBrand());
 	        ps.setString(4, car.getCategory());
 	        ps.setDouble(5, car.getPrice());
 	        ps.setString(6, car.getColour());
 	        ps.setString(7, car.getImageUrl());
 	        ps.setInt(8, car.getCarId()); 
 	        
 	        return ps.executeUpdate() > 0;
 	    } catch (SQLException e) {
 	        e.printStackTrace();
 	    }
 	    return false;
 	}
 	

 // Delete car by ID
    public boolean deleteCar(int carId) throws ClassNotFoundException {
        String sql = "DELETE FROM car WHERE car_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, carId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
