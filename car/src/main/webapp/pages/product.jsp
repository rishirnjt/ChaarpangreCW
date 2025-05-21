<%@ page import="java.util.*, com.cars.dao.CarDAO, com.cars.model.Car"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products</title>
<link rel="stylesheet" href="/car//css/product.css" />
<style>
.navtitles {
	font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI",
		Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue",
		sans-serif;
	color: #f1f1f1;
	text-decoration: none;
}

.navtitles\:hover {
	color: gray;
}
  .nav-logo{
    height: 200px;
    }
</style>
</head>
<body>
	<!-- Navbar -->
  <header>
    <nav class="navbar">
      <div class="nav-left">
     	 <a href="${pageContext.request.contextPath}/pages/home.jsp">
       		 <img src="${pageContext.request.contextPath}/img/logo.png" alt="CharPangre Logo" class="nav-logo" />
      	</a>   
      </div>

      <div class="nav-center">
 		<a href="${pageContext.request.contextPath}/pages/home.jsp">Home</a>
        <a href="${pageContext.request.contextPath}/pages/product.jsp">Products</a>
        <a href="${pageContext.request.contextPath}/pages/aboutus.jsp">About Us</a>     
       </div>

      <div class="nav-right">
        <div class="search-box">
          <form class="d-flex" method="get" action="${pageContext.request.contextPath}/pages/product.jsp" role="search">
  <input type="search" name="searchTerm" placeholder="Search" value="${param.searchTerm}">
  <button class="search-button" type="submit">
    <img src="${pageContext.request.contextPath}/img/2.jpg" width="20px" class="searchimg">
  </button>
</form>

        </div>
		<a href="${pageContext.request.contextPath}/WishlistController" title="Wishlist">
   		 <img src="${pageContext.request.contextPath}/img/heart.png" width="40px" alt="Wishlist">
		</a>
          <div class="user-dropdown">
  		<a href="${pageContext.request.contextPath}/pages/profile.jsp" title="View Profile">
   		 <img src="${pageContext.request.contextPath}/img/usericon.png" width="40px">
  	   </a>
		</div>
        </div>
    </nav>
  </header>
<%
  CarDAO dao = new CarDAO();
  List<Car> cars = null;
  String searchTerm = request.getParameter("searchTerm");
  String sort = request.getParameter("sort");
  
  if (searchTerm != null && !searchTerm.isEmpty()) {
    cars = dao.searchCars(searchTerm);
  } else {
    cars = dao.getAllCars();
  }

  if (cars != null && sort != null) {
      if ("asc".equals(sort)) {
          Collections.sort(cars, new Comparator<Car>() {
              public int compare(Car c1, Car c2) {
                  return Double.compare(c1.getPrice(), c2.getPrice());
              }
          });
      } else if ("desc".equals(sort)) {
          Collections.sort(cars, new Comparator<Car>() {
              public int compare(Car c1, Car c2) {
                  return Double.compare(c2.getPrice(), c1.getPrice());
              }
          });
      }
  }
 %>


	<% if (cars == null || cars.isEmpty()) { %>
	<p style="text-align: center; color: red; font-weight: bold;">
		No cars found<%= (searchTerm != null && !searchTerm.isEmpty()) ? " for \"" + searchTerm + "\"" : "" %>.
	</p>
	<% } %>


	<div class="topimage">
		<div class="topimagecontainer">
			<h2>
				Find Your Car <br>Here
			</h2>
		</div>
	</div>
	<!-- Sort Dropdown -->
<form method="get" action="${pageContext.request.contextPath}/pages/product.jsp"
	style="text-align: center; margin: 20px 0;">
	<label for="sort">Sort by Price:</label>
	<select name="sort" id="sort" onchange="this.form.submit()">
		<option value="">--Select--</option>
		<option value="asc"
			<%="asc".equals(request.getParameter("sort")) ? "selected" : ""%>>Low to High</option>
		<option value="desc"
			<%="desc".equals(request.getParameter("sort")) ? "selected" : ""%>>High to Low</option>
	</select>
</form>

	<!-- EV Cars Section -->
<div id="evgadi">
	<h2 style="text-align: center;">Electric Vehicles</h2>
	<div class="productsection">
		<% for (Car car : cars) {
			if ("EV".equalsIgnoreCase(car.getCategory())) { %>
		<div class="container">
			<div class="box">
				<img src="${pageContext.request.contextPath}/<%= car.getImageUrl() %>"
					alt="Car Image" class="productimg" />
			</div>
			<div class="details">
				<span class="category"><%= car.getCarName() %></span>
			<h4>
 			 <a href="<%= request.getContextPath() %>/CarDetailsController?carId=<%= car.getCarId() %>">
  				  Car Specification
 			 </a>
 			 </h4>				
 			 <div class="price-wishlist">
					<div class="price">Rs <%= car.getPrice() %></div>
					
					<!-- Buy Button -->
					<form action="<%= request.getContextPath() %>/PurchaseController" method="post" style="display: inline;">
    					<input type="hidden" name="carId" value="<%= car.getCarId() %>"/>
   						 <input type="number" name="quantity" value="1" min="1" style="width: 60px;" required />
   						 <button type="submit" style="background-color: #28a745; border: none; color: white; padding: 5px 10px; border-radius: 5px; cursor: pointer; font-size: 14px;">Buy</button>
					</form>

					<!-- Wishlist Button -->
					<form action="<%= request.getContextPath() %>/AddToWishlistController" method="post" style="display: inline;">
						<input type="hidden" name="carId" value="<%= car.getCarId() %>"/>
						<button type="submit" style="background: none; border: none; cursor: pointer; font-size: 20px;">♥</button>
					</form>

				
				</div>
			</div>
		</div>
		<% } } %>
	</div>
</div>

<!-- Fuel Cars Section -->
<div id="fuelgadi">
	<h2 style="text-align: center;">Fuel Cars</h2>
	<div class="productsection">
		<% for (Car car : cars) {
			if (!"EV".equalsIgnoreCase(car.getCategory())) { %>
		<div class="container">
			<div class="box">
				<img src="${pageContext.request.contextPath}/<%= car.getImageUrl() %>"
				 alt="Car Image" class="productimg" />					 
			</div>
			<div class="details">
				<span class="category"><%= car.getCarName() %></span>
				<h4>
 				 <a href="<%= request.getContextPath() %>/CarDetailsController?carId=<%= car.getCarId() %>">
  				  Car Specification
 				 </a>
 			 </h4>	
				<div class="price-wishlist">
					<div class="price">Rs <%= car.getPrice() %></div>
					
					<!-- Buy Button -->
					<form action="<%= request.getContextPath() %>/PurchaseController" method="post" style="display: inline;">
    					<input type="hidden" name="carId" value="<%= car.getCarId() %>"/>
   						 <input type="number" name="quantity" value="1" min="1" style="width: 60px;" required />
   						 <button type="submit" style="background-color: #28a745; border: none; color: white; padding: 5px 10px; border-radius: 5px; cursor: pointer; font-size: 14px;">Buy</button>
					</form>

					<!-- Wishlist Button -->
					<form action="<%= request.getContextPath() %>/AddToWishlistController" method="post" style="display: inline;">
						<input type="hidden" name="carId" value="<%= car.getCarId() %>"/>
						<button type="submit" style="background: none; border: none; cursor: pointer; font-size: 20px;">♥</button>
					</form>
					
				</div>
			</div>
		</div>
		<% }
	} %>
	</div>
</div>

	<!-- Footer -->
	<div class="footer"
		style="background-color: #131313; height: 250px; width: 100%; margin-top: 50px; display: flex; justify-content: space-evenly;">
		<div class="footer_icon">
			<span class="title">Follow us :</span> <a><img
				src="${pageContext.request.contextPath}/img/instagram.png" /></a> <a><img
				src="${pageContext.request.contextPath}/img/facebook.jpg" /></a> <a><img
				src="${pageContext.request.contextPath}/img/twitter.jpg" /></a>
		</div>
		<div class="footer_text">
			<span class="title">Overview</span> <a
				href="${pageContext.request.contextPath}/pages/home.jsp">Home</a> <a
				href="${pageContext.request.contextPath}/pages/product.jsp">Products</a>
			<a href="${pageContext.request.contextPath}/pages/aboutus.jsp">About Us</a>
		</div>
		<div class="footer_products">
			<span class="title">Products</span> <a href="#">EV</a> <a href="#">Fuel</a>
		</div>
		<div class="Contact">
			<span class="title">Find Us</span>
			<div class="contactdetails">
				<p style="color: white;">061 - 528123</p>
				<a href="#" style="color: white; text-decoration: none;">charpangre@gmail.com</a>
				<p style="color: white;">Matepani, Pokhara - 12</p>
			</div>
		</div>
	</div>
</body>
</html>
