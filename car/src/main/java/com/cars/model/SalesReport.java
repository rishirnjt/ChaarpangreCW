package com.cars.model;

public class SalesReport {
	private int purchaseId;
    private Car car;
    private User buyer;
    private int quantity;
    private String totalPrice;
    
    
    public SalesReport() {
        // default constructor
    }
	public SalesReport(int purchaseId, Car car, User buyer, int quantity, String totalPrice) {
		super();
		this.purchaseId = purchaseId;
		this.car = car;
		this.buyer = buyer;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}
	public int getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public User getBuyer() {
		return buyer;
	}
	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
}
