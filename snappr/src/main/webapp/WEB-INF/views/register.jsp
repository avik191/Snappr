<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="images" value="/resources/images" />
<spring:url var="css" value="/resources/css" />


<c:set var="contextRoot" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Snappr - Signup to continue to Snappr</title>
<link rel="stylesheet" href="${css }/register.css">
</head>
<body>
	<div class="register-main-container">
		<div class="logo">
			<img src="${images }/logo.png" alt="logo">
		</div>
		<h2 class="register-heading">Create new account</h2>
		<div class="form-container">
			<sf:form action="${contextRoot }/addUser" method="post"
				class="register-form" modelAttribute="user">
				
				<label for="name">Name</label>
				<sf:input type="text" id="name" path="name"
					placeholder="Name shown to others" />
				<p id="error-text"><sf:errors cssClass="error-text" path="name" element="em" style="color:rgb(190, 48, 48)"/></p>
					
				<label for="email">Email</label>
				<sf:input type="email" id="email" path="email"
					placeholder="you@example.com" />
				<p id="error-text"><sf:errors cssClass="error-text" path="email" element="em" style="color:rgb(190, 48, 48)"/></p>
				
				<label for="pass">Password</label>
				<sf:input type="password" id="pass" path="password"
					placeholder="Create a password" />
				<p id="error-text"><sf:errors cssClass="error-text" path="password" element="em" style="color:rgb(190, 48, 48)"/></p>
					
				<input type="submit" value="Create account">
				<a href="${contextRoot }/index">Already have an account?</a>
			</sf:form>

			<div class="register-footer">
				<div></div>
				<div></div>
				<div></div>
				<div></div>
				<div></div>
			</div>
		</div>
	</div>
</body>
</html>