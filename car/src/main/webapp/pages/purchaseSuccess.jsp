<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<html>
<head>
    <title>Payment Confirmation</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 60%;
            margin: 50px auto;
            background-color: #fff;
            padding: 30px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        h1 {
            color: #2e7d32;
            text-align: center;
        }

        .message {
            font-size: 18px;
            margin-top: 20px;
            text-align: center;
            color: #333;
        }

        .receipt {
            margin-top: 30px;
            border-top: 2px solid #ddd;
            padding-top: 20px;
        }

        .receipt table {
            width: 100%;
            border-collapse: collapse;
        }

        .receipt th, .receipt td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
            text-align: left;
        }

        .receipt th {
            background-color: #f9f9f9;
        }

        .back-link {
            display: block;
            text-align: center;
            margin-top: 30px;
            text-decoration: none;
            color: #1976d2;
            font-weight: bold;
        }

        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Payment Confirmation</h1>

        <div class="message">
            <p>Your purchase has been successful. The car will be delivered to your address soon.</p>
            <p><%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %></p>
        </div>

        <div class="receipt">
            <h2>Receipt</h2>
            <table>
                <tr>
                    <th>Item</th>
                    <td><%= request.getAttribute("carName") != null ? request.getAttribute("carName") : "Unknown Car" %></td>
                </tr>

                <tr>
                    <th>Price Paid</th>
                    <td>
                        Rs.<%= request.getAttribute("price") != null 
                                ? String.format("%.2f", request.getAttribute("price")) 
                                : "0.00" %>
                    </td>
                </tr>

                <tr>
                    <th>Purchase Date</th>
                    <td><%= new Date() %></td>
                </tr>
            </table>
        </div>

        <a class="back-link" href="<%= request.getContextPath() %>/CarListController">← Back to Car List</a>
    </div>
</body>
</html>
