<%-- <%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Image Detail</title>
</head>
<body>
<h1 style="color:red; text-align:center;">ADD IMAGE DETAIL</h1>

<div style="text-align:center;">

<form action="../AddImage" method="post" enctype="multipart/form-data">
   <label for="image">Select Image:</label>
   <input type="file" name="image" id="image" accept="image/*" required>
   <br><br>
   <input type="submit" value="Add Image">
</form>

</div>
</body>
</html> --%>

 
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cars.model.User" %>
<%
    User user = (User) session.getAttribute("userWithSession");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    String imagePath = user.getProfileImage() != null ? user.getProfileImage() : "img/usericon.png";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upload Profile Image</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin-top: 50px;
        }
        img {
            width: 150px;
            height: 150px;
            border-radius: 50%;
            object-fit: cover;
            border: 2px solid #ccc;
            margin-bottom: 20px;
        }
        form {
            margin-top: 20px;
        }
        input[type="file"] {
            margin: 10px 0;
        }
        input[type="submit"] {
            padding: 8px 16px;
            background-color: #333;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #555;
        }
    </style>
</head>
<body>

    <h1>Upload Your Profile Picture</h1>

    <!-- Show current image -->
	<img src="<%= imagePath %>" alt="User Image">

    <!-- Upload form -->
    <form action="<%= request.getContextPath() %>/AddImage" method="post" enctype="multipart/form-data">
        <input type="file" name="image" accept="image/*" required><br>
        <input type="submit" value="Upload Image">
    </form>

</body>
</html>
