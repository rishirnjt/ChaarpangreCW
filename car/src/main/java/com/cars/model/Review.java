package com.cars.model;

public class Review {

    private int reviewID;
    private int userID;
    private String rating;
    private String comment;
    private String userName;

    // default constructer
    public Review() {

    }

    // parameter constructor
    public Review(int reviewID, int userID, String rating, String comment, String userName) {
        super();
        this.reviewID = reviewID;
        this.userID = userID;
        this.rating = rating;
        this.comment = comment;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // getter and setter method
    public int getReviewID() {
        return reviewID;
    }


    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }


    public int getUserID() {
        return userID;
    }


    public void setUserID(int userID) {
        this.userID = userID;
    }


    public String getRating() {
        return rating;
    }


    public void setRating(String rating) {
        this.rating = rating;
    }


    public String getComment() {
        return comment;
    }


    public void setComment(String comment) {
        this.comment = comment;
    }






}