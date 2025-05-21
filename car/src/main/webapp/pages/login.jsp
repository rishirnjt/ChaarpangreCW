<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login - चार पाङ्ग्रे</title>
<link rel="stylesheet" type="text/css" href=" /car/css/login.css" />
</head>
<body>
	<div class="login-container">
        <h1>WELCOME BACK</h1>

        <!-- Error message display -->
        <c:if test="${not empty errorMessage}">
            <div style="color: red; font-weight: bold; text-align: center; padding: 10px;">
                ${errorMessage}
            </div>
        </c:if>
 
			<form action="${pageContext.request.contextPath}/LogInController" method="post">
            <input type="hidden" name="action" value="login" />
    
            <div class="inputField" style="padding-top: 40px; padding-bottom: 40px;">
                <div class="email">
                    <label for="User">Email Address</label>
                    <div class="underline-input">
                        <input type="email" id="email" name="email" placeholder="Enter your email address" required>
                        <span class="underline"></span>
                    </div>  
                </div>
                
                <div class="password">
                    <label for="pass">Password</label>
                    <div class="underline-input">
                        <input type="password" id="password" name="password" placeholder="Enter your password" required minlength="6">
                        <span class="underline"></span>
                    </div>  
                </div>
                
                <div class="remember-me">
                    <input type="checkbox" id="remember">
                    <label for="remember">Remember me</label>
                </div>
                
                <button class="login-button" type="submit">LOG IN</button>
            </div>  
        </form>
        
        <p style="color: #BCC2C2; text-decoration: none; font-size: 12px;">
            DON'T HAVE AN ACCOUNT? <a href="${pageContext.request.contextPath}pages/register.jsp">Register</a>
        </p>
    </div>
</body>
</html>
