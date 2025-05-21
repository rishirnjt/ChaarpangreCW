<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Car Details</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #121212;
            color: #f0f0f0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .form-container {
            background-color: #1f1f1f;
            padding: 30px;
            border-radius: 10px;
            width: 400px;
            box-shadow: 0 0 20px rgba(0,0,0,0.5);
        }

        .form-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-top: 12px;
        }

        input, select {
            width: 100%;
            padding: 10px;
            background-color: #2a2a2a;
            color: #fff;
            border: 1px solid #444;
            border-radius: 5px;
            margin-top: 5px;
        }

        input[type="submit"] {
            background-color: #ffffff;
            color: #000;
            font-weight: bold;
            cursor: pointer;
            transition: 0.3s;
            margin-top: 20px;
        }

        input[type="submit"]:hover {
            background-color: #ccc;
        }

        .message {
            color: #4caf50;
            text-align: center;
            margin-top: 15px;
            font-weight: bold;
        }

        #preview img {
            width: 100%;
            margin-top: 10px;
            border-radius: 8px;
        }
        .back-btn {
            background-color: #ffffff;
            color: #000;
            font-weight: bold;
            cursor: pointer;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            transition: 0.3s;
            width: 100%;
            margin-top: 20px;
        }

        .back-btn:hover {
            background-color: #ccc;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2>Update Car Details</h2>
    <form action="${pageContext.request.contextPath}/UpdateCarController" method="post" enctype="multipart/form-data">
        <input type="hidden" name="carId" value="${car.carId}" />

        <label for="carName">Car Name:</label>
        <input type="text" id="carName" name="carName" value="${car.carName}" required />

        <label for="model">Model:</label>
        <input type="text" id="model" name="model" value="${car.model}" required />

        <label for="brand">Brand:</label>
        <input type="text" id="brand" name="brand" value="${car.brand}" required />

        <label for="category">Category:</label>
        <input type="text" id="category" name="category" value="${car.category}" required />

        <label for="price">Price:</label>
        <input type="number" id="price" name="price" value="${car.price}" step="0.01" required />

        <label for="colour">Colour:</label>
        <input type="text" id="colour" name="colour" value="${car.colour}" required />
        
        <label for="imageUrl">Upload New Image:</label>
        <input type="file" id="imageUrl" name="imageUrl" accept="image/*" onchange="previewImage(this)" /><br><br>

        <div id="preview"></div>

       <%--  <c:if test="${not empty car.imageUrl}">
            <p>Current Image:</p>
            <img src="${pageContext.request.contextPath}/images/${car.imageUrl}" alt="Current Car Image" width="150" />
        </c:if>
 --%>
        <input type="submit" value="Update Car" />
    </form>

<button class="back-btn" onclick="window.location.href='<%=request.getContextPath()%>/CarListServlet'">Back to Car List</button>
</div>

<script>
    function previewImage(input) {
        const preview = document.getElementById('preview');
        preview.innerHTML = "";
        if (input.files && input.files[0]) {
            const reader = new FileReader();
            reader.onload = function (e) {
                const img = document.createElement("img");
                img.src = e.target.result;
                preview.appendChild(img);
            };
            reader.readAsDataURL(input.files[0]);
        }
    }
</script>

</body>
</html>
