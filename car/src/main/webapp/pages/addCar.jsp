<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Car</title>
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
    </style>
</head>
<body>

<div class="form-container">
    <h2>Add New Car</h2>
    <form action="${pageContext.request.contextPath}/AddCarController" method="post" enctype="multipart/form-data">
        <label>Name:</label>
        <input type="text" name="car_name" required>

        <label>Model:</label>
        <input type="text" name="model" required>

        <label>Brand:</label>
        <input type="text" name="brand" required>

        <label>Category:</label>
        <select name="category" required>
            <option value="">-- Category --</option>
            <option value="EV">Electric</option>
            <option value="Fuel">Fuel</option>
        </select>

        <label>Price:</label>
        <input type="number" name="price" step="0.01" required>

        <label>Colour:</label>
        <input type="text" name="colour" required>

        <label>Image:</label>
        <input type="file" name="image" accept="image/*" onchange="previewImage(this)" required>

        <div id="preview"></div>

        <input type="submit" value="Add Car">
    </form>

    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
    %>
        <div class="message"><%= message %></div>
    <%
        }
    %>
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