<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.cars.model.User" %>
<%
    List<User> userList = (List<User>) request.getAttribute("userList");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer List</title>
    <style>
        body {
    background-color: #f4f4f4; /* Light grey background for the page */
    font-family: Arial, sans-serif;
    color: #333;
}

h2 {
    color: #444;
    text-align: center;
    margin-top: 20px;
    font-size: 2em;
}

table {
    width: 80%;
    margin: 20px auto;
    border-collapse: collapse;
    background-color: #fff; /* White background for the table */
    box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1); /* Light shadow around table */
    border-radius: 8px; /* Rounded corners for the table */
}

th, td {
    padding: 10px;
    text-align: center;
    border: 1px solid #ddd;
}

th {
    background-color: #333;
    color: white;
}

td {
    background-color: #fafafa;
}

td input[type="submit"] {
    color: white;
    background-color: #e74c3c; /* Red background for delete button */
    border: none;
    padding: 8px 15px;
    border-radius: 5px;
    cursor: pointer;
    font-weight: bold;
    text-transform: uppercase;
    box-shadow: 0px 1px 5px rgba(0, 0, 0, 0.1);
}

td input[type="submit"]:hover {
    background-color: #c0392b; /* Darker red when hovering over the button */
}

tr:hover {
    background-color: #f1f1f1; /* Highlight row on hover */
}

.no-users {
    text-align: center;
    font-size: 1.2em;
    color: #999;
}

form {
    display: inline-block;
    margin: 0;
}

form input[type="submit"] {
    transition: background-color 0.3s ease; /* Smooth transition on hover */
}

form input[type="submit"]:active {
    transform: scale(0.95); /* Slight shrink effect when clicked */
}

/* Responsive Design: Ensure the table is scrollable on smaller screens */
@media screen and (max-width: 768px) {
    table {
        width: 100%;
        font-size: 0.9em;
    }

    th, td {
        padding: 8px;
    }

    td input[type="submit"] {
        padding: 6px 10px;
        font-size: 0.9em;
    }
}

    </style>
</head>
<body>
    <h2>Customer List</h2>
    <table>
        <thead>
            <tr>
                <th>User ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Role</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                if (userList != null) {
                    for (User user : userList) {
            %>
            <tr>
                <td><%= user.getUserId() %></td>
                <td><%= user.getName() %></td>
                <td><%= user.getEmail() %></td>
                <td><%= user.getPhone() %></td>
                <td><%= user.getRole() %></td>
                <td>
               <form action="<%=request.getContextPath()%>/DeleteUserController" method="post" style="display:inline;">
   				 <input type="hidden" name="userId" value="<%= user.getUserId() %>" />
    			<input type="submit" value="Delete" onclick="return confirm('Are you sure you want to delete this user?');" />
			   </form>

            </td>
            </tr>
            <%      }
                } else {
            %>
            <tr><td colspan="5">No users found</td></tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>