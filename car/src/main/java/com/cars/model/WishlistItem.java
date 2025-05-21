package com.cars.model;

public class WishlistItem {
    private int wishlistId;
    private int carId;
    private String carName;
    private double price;
    private String brand;
    private String imageUrl; 

    // Getters and setters
    public int getWishlistId() { 
    	return wishlistId; 
    	}
    
    public void setWishlistId(int wishlistId) { 
    	this.wishlistId = wishlistId; 
    	}

    public int getCarId() { 
    	return carId;
    	}
    public void setCarId(int carId) {
    	this.carId = carId; 
    	}

    public String getCarName() { 
    	return carName; 
    	}
    public void setCarName(String carName) {
    	this.carName = carName;
    	}

    public double getPrice() { 
    	return price; 
    	}
    public void setPrice(double price) { 
    	this.price = price; 
    	}

    public String getBrand() {
    	return brand; 
    }
    public void setBrand(String brand) { 
    	this.brand = brand; 
    	}

    public String getImageUrl() { 
    	return imageUrl; 
    	}
    public void setImageUrl(String imageUrl) {
    	this.imageUrl = imageUrl;
    	}
}
