package com.cars.model;

public class Car {
	private int carId; // added
	private String carName;
	private String model;
	private String brand;
	private String category;
	private double price;
	private String colour;
	private String imageUrl;

	public Car() {
		// default constructor
	}

	// Constructor for insert (without car_id)
	public Car(String carName, String model, String brand, String category, double price, String colour,
			String imageUrl) {
		this.carName = carName;
		this.model = model;
		this.brand = brand;
		this.category = category;
		this.price = price;
		this.colour = colour;
		this.imageUrl = imageUrl;
	}

	// Constructor with car_id (e.g. for update/delete or displaying ID)
	public Car(int carId, String carName, String model, String brand, String category, double price, String colour,
			String imageUrl) {
		this.carId = carId;
		this.carName = carName;
		this.model = model;
		this.brand = brand;
		this.category = category;
		this.price = price;
		this.colour = colour;
		this.imageUrl = imageUrl;
	}

	// Getters & setters
	public int getCarId() {
		return carId;
	}

	public String getCarName() {
		return carName;
	}

	public String getModel() {
		return model;
	}

	public String getBrand() {
		return brand;
	}

	public String getCategory() {
		return category;
	}

	public double getPrice() {
		return price;
	}

	public String getColour() {
		return colour;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}