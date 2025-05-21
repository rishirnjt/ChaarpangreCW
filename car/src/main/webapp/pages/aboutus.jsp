 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
 <!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
  <title>चार पाङ्ग्रे</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/aboutUs.css" />
  <style>
    .navtitles {
      font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI",
        Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue",
        sans-serif;
      color: #f1f1f1;
      text-decoration: none;
    }
    .navtitles:hover {
      color: white;
    }
     .nav-logo{
    height: 200px;
    }
  </style>
</head>
<body>

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
        <form class="d-flex" role="search">
          <input type="search" placeholder="Search" />
          <button class="search-button" type="submit">
            <img src="${pageContext.request.contextPath}/img/2.jpg" width="20px" class="searchimg" />
          </button>
        </form>
      </div>
      <a href="${pageContext.request.contextPath}/WishlistController" title="Wishlist">
        <img src="${pageContext.request.contextPath}/img/heart.png" width="40px" alt="Wishlist" />
      </a>
      <a href="${pageContext.request.contextPath}/pages/profile.jsp" title="View Profile">
        <img src="${pageContext.request.contextPath}/img/usericon.png" width="40px" />
      </a>
    </div>
  </nav>
</header>



    <!-- Banner Section -->
    <div class="banner">
        <div class="banner-image">
		<img src="${pageContext.request.contextPath}/img/blackCar.jpg" alt="Banner showing a black car" />
        </div>
        <div class="container">
            <div class="banner-description">
                <h1 class="page">About CharPangre</h1>
                <p class="page-description">
                    A trusted online platform for purchasing a wide range of branded cars.
                </p>
            </div>
        </div>
    </div>

    <!-- About Us Section -->
    <div class="aboutUs">
        <div class="logo">
            <img src="${pageContext.request.contextPath}/img/logo.png" alt="About CharPangre image">
        </div>
        <div class="aboutUs-text">
            <div class="aboutUs-title">
                <h1>Welcome to <br> CharPangre</h1>
            </div>
            <div class="aboutUs-description">
                <p>
                    CharPangre is a leading online car selling platform designed to meet the
                    evolving needs of modern vehicle buyers. We specialize in offering a wide range of 
                    high-quality branded vehicles, including both traditional fuel-powered cars and
                    cutting-edge electric vehicles (EVs).<br><br>
                    We pride ourselves on providing not only a diverse
                    inventory but also excellent customer support, easy financing options, and up-to-date 
                    information to help buyers make informed decisions.
                </p>
            </div>
        </div>
    </div>

    <!-- Our Mission Section -->
    <div class="our-mission">
        <div class="our-mission-text">
            <div class="our-mission-title">
                <h1>Our Mission</h1>
            </div>
            <div class="our-mission-description">
                <p>
                    At CharPangre, our mission is to empower every car buyer with the confidence and
                    convenience to make the right choice by offering a wide selection of trusted brands
                    through our user-friendly online platform.<br><br>
                    We strive to become the go-to destination for reliable fuel and electric cars,
                    helping people move forward with ease and trust.<br><br>
                    Our team is committed to making your experience smooth, honest, and helpful.<br>
                    At CharPangre, you're not just buying a car.You're joining a community that
                    values trust and simplicity.
                </p>
            </div>	
        </div>
        <div class="our-mission-image">
            <img src="${pageContext.request.contextPath}/img/group.jpg" alt="CharPangre team working together">
        </div>
    </div>
<!-- Footer -->
  <div class="footer" style="background-color: #131313; height: 250px; width: 100%; margin-top: 50px; display: flex; justify-content: space-evenly;">
    <div class="footer_icon">
      <span class="title">Follow us :</span>
      <a><img src="${pageContext.request.contextPath}/img/instagram.png" /></a>
      <a><img src="${pageContext.request.contextPath}/img/facebook.jpg" /></a>
      <a><img src="${pageContext.request.contextPath}/img/twitter.jpg" /></a>
    </div>
    <div class="footer_text">
      <span class="title">Overview</span>
      <a href="${pageContext.request.contextPath}/pages/home.jsp">Home</a>
      <a href="${pageContext.request.contextPath}/pages/product.jsp">Products</a>
      <a href="${pageContext.request.contextPath}/pages/Blog.jsp">Blog</a>
      <a href="${pageContext.request.contextPath}/pages/aboutus.jsp">About Us</a>
    </div>
    <div class="footer_products">
      <span class="title">Products</span>
      <a href="#">EV</a>
      <a href="#">Fuel</a>
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