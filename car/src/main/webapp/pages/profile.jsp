<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cars.model.User" %>
<%
    User user = (User) session.getAttribute("userWithSession");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String imagePath = (user.getProfileImage() != null && !user.getProfileImage().isEmpty())
        ? request.getContextPath() + "/" + user.getProfileImage()
        : request.getContextPath() + "/img/usericon.png";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f7f7;
        }

        .profile-card {
            width: 340px;
            margin: 50px auto;
            padding: 25px;
            border: 2px solid #ddd;
            border-radius: 10px;
            background-color: white;
            text-align: center;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }

        .profile-card img {
            width: 150px;
            height: 150px;
            border-radius: 50%;
            object-fit: cover;
            border: 2px solid #ccc;
            margin-bottom: 20px;
        }

        .profile-card h2 {
            margin: 15px 0 5px;
        }

        .profile-card p {
            margin: 5px 0;
            color: #333;
        }

        .btn {
            padding: 8px 16px;
            margin: 10px 5px;
            border: none;
            background-color: black;
            color: white;
            cursor: pointer;
            border-radius: 5px;
            text-decoration: none;
            display: inline-block;
        }

        .btn:hover {
            background-color: #444;
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

<div class="profile-card">
    <img src="<%= imagePath %>" alt="User Image">
    <h2><%= user.getName() %></h2>
    <p><strong>Email:</strong> <%= user.getEmail() %></p>
    <p><strong>Phone:</strong> <%= user.getPhone() %></p>
    <p><strong>Role:</strong> <%= user.getRole() %></p>

    <a href="updateProfile.jsp" class="btn">Update</a>
	<a href="<%= request.getContextPath() %>/LogoutController" class="btn">Log out</a>

    <!-- Upload new profile image -->
    <form action="<%= request.getContextPath() %>/AddImage" method="post" enctype="multipart/form-data">
        <input type="file" name="image" accept="image/*" required><br>
        <input type="submit" value="Upload New Image">
    </form>
</div>

</body>
</html>



