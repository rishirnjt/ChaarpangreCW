<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>चार पाङ्ग्रे</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/home.css" />
<style>
.navtitles {
	font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI",
		Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue",
		sans-serif;
	color: #f1f1f1;
	text-decoration: none;
}

.navtitles:hover {
	color: gray;
}

.nav-logo {
	height: 200px;
}
/* Container for the form */
.reviewcontainer form {
	display: flex;
	flex-direction: column;
	gap: 20px;
	color: #f1f1f1;
}

/* Email and textarea styling */
.email label, .reviewholder label, .rating label {
	font-size: 14px;
	margin-bottom: 6px;
	color: #f1f1f1;
	font-weight: 600;
}

.underline-input input[type="email"], .underline-input textarea, .rating select
	{
	width: 100%;
	padding: 12px 10px;
	font-size: 16px;
	color: #f1f1f1;
	background-color: transparent;
	border: none;
	border-bottom: 2px solid #dfdfdf;
	outline: none;
	transition: border-color 0.3s ease;
}

.underline-input input[type="email"]:focus, .underline-input textarea:focus,
	.rating select:focus {
	border-bottom-color: #4caf50; /* Green highlight */
}

/* Placeholder color */
input::placeholder, textarea::placeholder {
	color: #aaa;
	font-size: 16px;
}

/* Textarea resizing */
textarea {
	resize: vertical;
	min-height: 150px;
}

/* Rating dropdown */
.rating {
	max-width: 150px;
}

/* Submit button */
.submitreview {
	align-self: flex-start;
	background-color: #4caf50;
	color: white;
	font-weight: bold;
	padding: 12px 30px;
	border-radius: 40px;
	border: none;
	cursor: pointer;
	transition: background-color 0.3s ease;
	font-size: 16px;
}

.submitreview:hover {
	background-color: #388e3c; /* Darker green */
}

/* Navbar login/signup buttons */
.nav-btn {
	font-weight: bold;
	padding: 10px 20px;
	border-radius: 40px;
	text-decoration: none;
	font-size: 16px;
	transition: background-color 0.3s ease, color 0.3s ease;
	margin-left: 15px;
	cursor: pointer;
}

/* Login button - transparent with white border */
.login-btn {
	color: #f1f1f1;
	border: 2px solid #f1f1f1;
	background-color: transparent;
}

.login-btn:hover {
	background-color: #f1f1f1;
	color: #131313;
}

/* Signup button */
.signup-btn {
	background-color: #000000;
	color: white;
	border: 2px solid #f1f1f1;
}

.signup-btn:hover {
	background-color: #f1f1f1; 
	border-color: #f1f1f1;
	color: #131313;
}
</style>
</head>
<body>
	<!-- Navbar -->
	<header>
		<nav class="navbar">
			<div class="nav-left">
				<a href="${pageContext.request.contextPath}/pages/home.jsp"> <img
					src="${pageContext.request.contextPath}/img/logo.png"
					alt="CharPangre Logo" class="nav-logo" />
				</a>
			</div>

			<div class="nav-center">
				<a href="${pageContext.request.contextPath}/pages/home.jsp">Home</a>
				<a href="${pageContext.request.contextPath}/pages/product.jsp">Products</a>
				<a href="${pageContext.request.contextPath}/pages/aboutus.jsp">About Us</a>
				<a href="${pageContext.request.contextPath}/ReviewController">Review</a>
				
			</div>

			<div class="nav-right">
				<a href="${pageContext.request.contextPath}/WishlistController"
					title="Wishlist"> <img
					src="${pageContext.request.contextPath}/img/heart.png" width="40px"
					alt="Wishlist">
					
				</a>
				<!-- Add Login and Signup buttons -->
				<!--  If user is not login show -->
				<c:if
					test="${empty sessionScope.userWithSession and empty sessionScope.admin}">
					<a href="${pageContext.request.contextPath}/pages/login.jsp"
						class="nav-btn login-btn">Login</a>
					<a href="${pageContext.request.contextPath}/pages/register.jsp"
						class="nav-btn signup-btn">Signup</a>
				</c:if>
				<c:if
				    test="${not empty sessionScope.userWithSession or not empty sessionScope.admin}">
					<div class="user-dropdown">
						<a href="${pageContext.request.contextPath}/pages/profile.jsp"
							title="View Profile"> <img
							src="${pageContext.request.contextPath}/img/usericon.png"
							width="40px">
						</a>
					</div>
				</c:if>
			</div>
		</nav>
	</header>

	<!-- Landing Page -->
	<div class="landing-page">
		<div class="landing-text">
			<h1>
				FROM YOUR<br>SCREEN TO THE STREETS
			</h1>
			<p class="subtext">
				No more dealership runs or paperwork headaches.<br> Find your
				car online, seal the deal,<br> and get it delivered to your
				door. It’s that easy.
			</p>
			<div class="cta-buttons">
				<a href="${pageContext.request.contextPath}/pages/product.jsp">
					<button class="explore">EXPLORE</button>
				</a>

			</div>
		</div>
		<div class="landing-img">
			<img src="${pageContext.request.contextPath}/img/home.png"
				class="car-img">
		</div>
	</div>

	<!-- Brands Section -->
	<div class="Section1">
		<div class="brands-section">
			<h2 align="center">BRANDS WE OFFER</h2>
			<div class="brands-grid">
				<img src="${pageContext.request.contextPath}/img/mercedes-benz.jpg"
					alt="Mercedes"> <img
					src="${pageContext.request.contextPath}/img/byd.jpg" width="200px"
					alt="BYD"> <img
					src="${pageContext.request.contextPath}/img/nissan.jpg"
					alt="Nissan"> <img
					src="${pageContext.request.contextPath}/img/toyota.jpg"
					alt="Toyota"> <img
					src="${pageContext.request.contextPath}/img/kia.jpg" alt="Kia">
				<img src="${pageContext.request.contextPath}/img/honda.jpg"
					alt="Honda"> <img
					src="${pageContext.request.contextPath}/img/mahindra.jpg"
					alt="Mahindra"> <img
					src="${pageContext.request.contextPath}/img/renault.jpg"
					alt="Renault"> <img
					src="${pageContext.request.contextPath}/img/maruti-suzuki1647009823420.jpg"
					alt="Suzuki"> <img
					src="${pageContext.request.contextPath}/img/audi.jpg" alt="Audi">
				<img src="${pageContext.request.contextPath}/img/volkswagen.jpg"
					alt="Volkswagen"> <img
					src="${pageContext.request.contextPath}/img/jeep.jpg" alt="Jeep">
				<img src="${pageContext.request.contextPath}/img/bmw.jpg" alt="BMW">
				<img src="${pageContext.request.contextPath}/img/tata.jpg"
					alt="Tata"> <img
					src="${pageContext.request.contextPath}/img/hyundai.jpg"
					alt="Hyundai">
			</div>
		</div>

		<!-- Top Picks -->
		<div class="top-picks">
			<h2 align="center">TOP PICKS</h2>
			<div class="toppicks">
				<div class="toppicks-container">
					<div class="imgbox">
						<img src="${pageContext.request.contextPath}/img/vw vitrus.webp"
							class="productimg">
					</div>
					<div class="details">
						<span class="category">Brand : Volkswagen</span>
						<h4>
							<a href="#">Volkswagen Vitrus</a>
						</h4>
					</div>
				</div>
				<div class="toppicks-container">
					<div class="imgbox">
						<img src="${pageContext.request.contextPath}/img/hondacity.png"
							class="productimg2">
					</div>
					<div class="details">
						<span class="category">Brand : Honda</span>
						<h4>
							<a href="#">HONDA CITY</a>
						</h4>
					</div>
				</div>
				<div class="toppicks-container">
					<div class="imgbox">
						<img src="${pageContext.request.contextPath}/img/bmwcar.png"
							class="productimg2">
					</div>
					<div class="details">
						<span class="category">Brand : BMW</span>
						<h4>
							<a href="#">BMW 3 Series LWB</a>
						</h4>
					</div>
				</div>
				<div class="toppicks-container">
					<div class="imgbox">
						<img
							src="${pageContext.request.contextPath}/img/renault-kiger-new.webp"
							class="productimg2">
					</div>
					<div class="details">
						<span class="category">Brand : Renault</span>
						<h4>
							<a href="#">Renault Kiger</a>
						</h4>
					</div>
				</div>
			</div>
		</div>
	</div>

	
	<!-- Showing succesfull msg here  -->
	<c:if test="${not empty successMessage}">
		<div style="color: green;">${successMessage}</div>
	</c:if>

	<!-- Showing error  msg here  -->
	<c:if test="${not empty errorMessage}">
		<div style="color: red;">${errorMessage}</div>
	</c:if>



	<!-- Review Form -->
	<form action="${pageContext.request.contextPath}/ReviewController"
		method="post"
		style="max-width: 500px; margin: 0 auto; background: #f1f1f1; padding: 20px; border-radius: 10px;">
		<label for="comment" style="color: #000000;">Your Review:</label>
		<textarea id="comment" name="comment" rows="5" required
			style="width: 100%; background-color: #ffffff; color: black; padding: 10px; border-radius: 5px;"></textarea>

		<label for="rating" style="color: #000000;">Rating:</label> <select
			id="rating" name="rating" required
			style="width: 100%; background-color: #444; color: white; padding: 10px; border-radius: 5px; margin-bottom: 15px;">
			<option value="">--Select--</option>
			<option value="1">1 - Poor</option>
			<option value="2">2 - Fair</option>
			<option value="3">3 - Good</option>
			<option value="4">4 - Very Good</option>
			<option value="5">5 - Excellent</option>
		</select>

		<button type="submit"
			style="background-color: #333; color: white; border: none; padding: 10px 25px; font-weight: bold; border-radius: 30px; cursor: pointer;">
			Submit Review</button>
	</form>


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
			<a href="${pageContext.request.contextPath}/pages/aboutus.jsp">About
				Us</a>
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
