

 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    Boolean isAdmin = (Boolean) session.getAttribute("admin");
    if (isAdmin == null || !isAdmin) {
        response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
        return;
    }
%>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Car Showroom Admin Dashboard</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/adminDashboard.css?v=1">
</head>
<body>

  <div class="sidebar_menue">
    <h2> चार पाङ्ग्रे</h2>
    <a href="#">Dashboard</a>
    <a href="<%=request.getContextPath()%>/CarListController">Cars</a>

    <a href="<%=request.getContextPath()%>/UserListController">Customers</a>

    <a href="<%=request.getContextPath()%>/SalesReportController">Sales</a>
    
    <a href="<%=request.getContextPath()%>/LogoutController">Logout</a>
  </div>

  <div class="main">
    <div class="nav_bar">
      <h2>Admin Dashboard</h2>
      <div>
        <input type="text" placeholder="Search">
        <span style="margin-left: 15px;"> Admin</span>
      </div>
    </div>

    <br>

    <div class="Detail_container">
      <div class="Details">
        <h2>Cars in Stock</h2>
        <p>100</p>
      </div>
      <div class="Details">
        <h2>Car Sold</h2>
        <p>400</p>
      </div>
      <div class="Details">
        <h2>Revenue</h2>
        <p>Rs.2,111,00,000</p>
      </div>
      <div class="Details">
        <h2>Total Customers</h2>
        <p>500</p>
      </div>
    </div>
    <div class="Details">
 	 <h2>EV Cars</h2>
 	 <p>10</p>
	</div>

	<div class="Details">
 	 <h2>Fuel Cars</h2>
  	<p>12</p>
	</div>

  

    <div class="actions">
      <a href="addCar.jsp"><button>Add Car</button></a>

     
    </div>

    <br><br>
  </div>
</body>
</html>