<%@ page import="com.cars.dao.CarDAO" %>
<%@ page import="com.cars.model.Car" %>
<%@ page import="java.sql.*" %>

<%
    String carIdParam = request.getParameter("carId");
    Car car = null;

    if (carIdParam != null && !carIdParam.isEmpty()) {
        try {
            int carId = Integer.parseInt(carIdParam);
            CarDAO carDAO = new CarDAO();
            car = carDAO.getCarById(carId);
        } catch (NumberFormatException e) {
            out.println("<p>Invalid car ID format.</p>");
            return;
        }
    } else {
        out.println("<p>Car ID is missing.</p>");
        return;
    }

    if (car == null) {
        out.println("<p>Car not found.</p>");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title><%= car.getCarName() %> - Details</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/carDetails.css">
</head>
<body>
    <div class="card">
        <h2><%= car.getCarName() %> latest updates</h2>
        <p><strong>Description:</strong> The <%= car.getCarName() %> is now available in our inventory.</p>
        <p><strong>Price:</strong> Rs.<%= car.getPrice() %> (ex-showroom)</p>
        <p><strong>Model:</strong> <%= car.getModel() %></p>
        <p><strong>Brand:</strong> <%= car.getBrand() %></p>
        <p><strong>Category:</strong> <%= car.getCategory() %></p>
        <p><strong>Color:</strong> <%= car.getColour() %></p>
        <p><strong>Image:</strong> <img src="<%= car.getImageUrl() %>" alt="Car Image" width="200"/></p>
        <!-- Buy Button -->
       <form action="<%= request.getContextPath() %>/PurchaseController" method="post" style="display: inline;">
   		 <input type="hidden" name="carId" value="<%= car.getCarId() %>"/>
    	 <input type="hidden" name="quantity" value="1"/>
    	<button type="submit">Buy</button>
	</form>

    </div>
</body>
</html>
