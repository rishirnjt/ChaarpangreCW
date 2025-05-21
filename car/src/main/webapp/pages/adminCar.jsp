<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.cars.model.Car"%>
<%@ page import="java.util.List"%>

<%
    Boolean isAdmin = (Boolean) session.getAttribute("admin");
    if (isAdmin == null || !isAdmin) {
        response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
        return;
    }

    List<Car> cars = (List<Car>) request.getAttribute("cars");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Car List - Admin</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: black;
            padding: 20px;
        }

        h2 {
            text-align: center;
            color: white;
        }

        .add-btn {
            display: inline-block;
            margin-bottom: 20px;
            background-color: #2c2c2c ;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }

        .add-btn:hover {
            background-color: #0056b3;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 12px 15px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #2c2c2c ;
            color: white;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        img {
            border-radius: 6px;
        }

        .action-buttons form {
            display: inline-block;
            margin: 0 5px;
        }

        .action-buttons input[type="submit"] {
            padding: 6px 12px;
            border: none;
            border-radius: 4px;
            color: white;
            cursor: pointer;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        .action-buttons input[type="submit"]:hover {
            opacity: 0.85;
        }

        .action-buttons .update-btn {
            background-color: #28a745;
        }

        .action-buttons .delete-btn {
            background-color: #dc3545;
        }

        .no-cars {
            text-align: center;
            padding: 20px;
            color: #777;
        }
    </style>
</head>
<body>

    <h2>Car List - Admin</h2>

    <a href="<%=request.getContextPath()%>/pages/admin/addCar.jsp" class="add-btn">+ Add New Car</a>

    <table>
        <thead>
            <tr>
                <th>Image</th>
                <th>ID</th>
                <th>Name</th>
                <th>Model</th>
                <th>Brand</th>
                <th>Category</th>
                <th>Price</th>
                <th>Color</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                if (cars != null && !cars.isEmpty()) {
                    for (Car car : cars) {
            %>
            <tr>
                <td><img src="<%=car.getImageUrl()%>" width="100" height="60" alt="Car Image"></td>
                <td><%= car.getCarId() %></td>
                <td><%=car.getCarName()%></td>
                <td><%=car.getModel()%></td>
                <td><%=car.getBrand()%></td>
                <td><%=car.getCategory()%></td>
                <td>Rs. <%=car.getPrice()%></td>
                <td><%=car.getColour()%></td>
                <td class="action-buttons">
                    <form action="<%=request.getContextPath()%>/UpdateCarController">
                        <input type="hidden" name="carId" value="<%= car.getCarId() %>">
                        <input type="submit" class="update-btn" value="Update" onclick="return confirm('Update this car?');">
                    </form>
                    <form action="<%=request.getContextPath()%>/DeleteCarController" method="post">
                        <input type="hidden" name="carId" value="<%= car.getCarId() %>">
                        <input type="submit" class="delete-btn" value="Delete" onclick="return confirm('Delete this car?');">
                    </form>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="9" class="no-cars">No cars available.</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>

</body>
</html>
