<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cars.model.User" %>
<%
    User user = (User) session.getAttribute("userWithSession");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #eef2f3;
        }

        .form-container {
            width: 400px;
            margin: 50px auto;
            padding: 30px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.15);
        }

        .form-container h2 {
            text-align: center;
            margin-bottom: 25px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            margin-top: 15px;
        }

        input[type="text"],
        input[type="file"] {
            width: 100%;
            padding: 8px;
            margin-top: 3px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        button {
            margin-top: 20px;
            width: 100%;
            padding: 10px;
            background-color: black;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #444;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2>Update Profile</h2>
	    <form action="<%= request.getContextPath() %>/UpdateUserController" method="post">
        <label for="name">Name:</label>
		<input type="text" name="name" value="<%= user.getName() %>" required>

        <label for="phone">Phone:</label>
        <input type="text" name="phone" value="<%= user.getPhone() %>" required>
<!-- 
        <label for="profileImage">Profile Image:</label>
        <input type="file" name="profileImage" accept="image/*"> -->

        <button type="submit">Update</button>
    </form>
</div>

</body>
</html>
