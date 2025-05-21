<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sales Report</title>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/css/salesReport.css">
</head>
<body>
<div class="report-container">
    <h1> Car Sales Report</h1>

    <div class="summary-cards">
        <div class="card">
            <h2>Total Cars Sold</h2>
            <p>${totalCarsSold}</p>
        </div>
        <div class="card">
            <h2>Total Revenue</h2>
			<p>Nrs. <fmt:formatNumber value="${totalRevenue}" type="number" groupingUsed="true" maxFractionDigits="2"/></p>
        </div>
    </div>

    <table>
        <thead>
            <tr>
                <th>Purchase ID</th>
                <th>Car Name</th>
                <th>Model</th>
                <th>Quantity</th>
                <th>Total Price</th>
                <th>Buyer</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="sale" items="${salesList}">
                <tr>
                    <td>${sale.purchaseId}</td>
                    <td>${sale.car.carName}</td>
                    <td>${sale.car.model}</td>
                    <td>${sale.quantity}</td>
                    <td>Nrs. ${sale.totalPrice}</td>
                    <td>${sale.buyer.name}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div class="footer-note">
        © 2025  Auto Admin Dashboard
    </div>
</div>
</body>
</html>
