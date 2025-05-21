<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.cars.model.Review" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Reviews</title>
    <style>
        body {
            background-color: #222;
            color: white;
            font-family: Arial, sans-serif;
        }
        .review-container {
            max-width: 800px;
            margin: 30px auto;
            background-color: #333;
            padding: 20px;
            border-radius: 10px;
        }
        .review-box {
            border-bottom: 1px solid #555;
            padding: 15px 0;
        }
        .review-box:last-child {
            border-bottom: none;
        }
        .rating {
            color: gold;
        }
        .no-reviews {
            text-align: center;
            color: #aaa;
        }
    </style>
</head>
<body>
    <div class="review-container">
        <h2>User Reviews</h2>
        <c:choose>
            <c:when test="${not empty reviewList}">
                <c:forEach var="rev" items="${reviewList}">
                    <div class="review-box">
                        <p><strong>User Name:</strong> ${rev.userName}</p>

                        <p><strong>Rating:</strong> <span class="rating">${rev.rating} / 5</span></p>
                        <p><strong>Comment:</strong> ${rev.comment}</p>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p class="no-reviews">No reviews yet.</p>
            </c:otherwise>
        </c:choose>
    </div>
</body>