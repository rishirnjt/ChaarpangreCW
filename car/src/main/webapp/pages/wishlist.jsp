<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.cars.model.WishlistItem" %>

<%
    List<WishlistItem> wishlist = (List<WishlistItem>) request.getAttribute("wishlist");
    if (wishlist == null || wishlist.isEmpty()) {
%>
    <h2 style="text-align:center; margin-top: 50px;">Your wishlist is empty.</h2>
<%
    return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Wishlist</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
        }

        .container {
            width: 90%;
            margin: 50px auto;
        }

        .wishlist-item {
            display: flex;
            align-items: center;
            background-color: #fff;
            border: 1px solid #ddd;
            padding: 15px;
            margin-bottom: 15px;
            border-radius: 8px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.05);
        }

        .wishlist-item img {
            width: 150px;
            height: 100px;
            object-fit: cover;
            margin-right: 20px;
            border-radius: 5px;
        }

        .wishlist-details {
            flex-grow: 1;
        }

        .wishlist-details h3 {
            margin: 0;
            font-size: 20px;
        }

        .wishlist-details p {
            margin: 5px 0;
            color: #555;
        }

        .wishlist-actions {
            text-align: right;
        }

        .wishlist-actions a {
            padding: 8px 12px;
            margin-left: 10px;
            background-color: #e60000;
            color: #fff;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            font-size: 14px;
        }

        .wishlist-actions a:hover {
            background-color: #cc0000;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Your Wishlist</h2>

    <%
        for (WishlistItem item : wishlist) {
    %>
    <div class="wishlist-item">
	<img src="<%= request.getContextPath() + "/" + item.getImageUrl() %>" alt="Car Image">
        <div class="wishlist-details">
            <h3><%= item.getCarName() %></h3>
            <p>Price: Rs.<%= item.getPrice() %></p>
            <p>Brand: <%= item.getBrand() %></p>
        </div>
        <div class="wishlist-actions">
		<a href="RemoveWishlistController?wishlistId=<%= item.getWishlistId() %>">Remove</a>
 		<a href="CarDetailsController?carId=<%= item.getCarId() %>">View</a>

        </div>
    </div>
    <%
        }
    %>
</div>

</body>
</html>
