<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register - चार पाङ्ग्रे</title>
<link rel="stylesheet" type="text/css" href="/car/css/register.css" />
</head>
<body>
<div class="register-container">
        <h1>REGISTER NOW</h1>
        <div class="inputField" style="padding-top: 40px; padding-bottom: 40px;">

            <% String error = (String) request.getAttribute("errorMessage"); %>
            <% if (error != null) { %>
                <div style="color:red; text-align:center;"><%= error %></div>
            <% } %>

            <form action="${pageContext.request.contextPath}/LogInController" method="post">
                <input type="hidden" name="action" value="register">

                <div class="info" style="padding-bottom: 20px;">
                    <div class="Fullname">
                        <label for="fullname">Full Name</label>
                        <div class="underline-input">
							<input type="text" id="name" name="name" placeholder="Enter your full name" required>
                            <span class="underline"></span>
                        </div> 
                    </div>

                    <div class="Address">
                        <label for="address">Address</label>
                        <div class="underline-input">
                            <input type="text" id="address" name="address" placeholder="Enter your address" required>
                            <span class="underline"></span>
                        </div> 
                    </div>

                    <div class="PhoneNumber">
                        <label for="phone">Phone Number</label>
                        <div class="underline-input">
                            <input type="tel" id="phone" name="phone" placeholder="Enter your phone number" pattern="[0-9]{10}" required>
                            <span class="underline"></span>
                        </div> 
                    </div>
                    
                    <div class="Role">
   						 <label for="role">Role</label>
   						 <div class="underline-input">
       						 <select name="role" id="role" required>
       				    		 <option value="">--Select--</option>
           						 <option value="User">User</option>
      						  </select>
       						 <span class="underline"></span>
   						 </div> 
					</div> 
                    

                    <div class="email">
                        <label for="email">Email Address</label>
                        <div class="underline-input">
                            <input type="email" id="email" name="email" placeholder="Enter your email address" required>
                            <span class="underline"></span>
                        </div> 
                    </div>

                    <div class="password">
                        <label for="password">Password</label>
                        <div class="underline-input">
                            <input type="password" id="password" name="password" placeholder="Enter your password" required minlength="8">
                            <span class="underline"></span>
                        </div>  
                    </div>
                </div>

                <button class="Register-button" type="submit">REGISTER</button>
            </form>
        </div>  
      
        <p style="color: #BCC2C2; text-decoration: none; font-size: 12px;">ALREADY HAVE AN ACCOUNT? <a href="login.jsp">LOG IN</a></p>
    </div>

</body>
</html>