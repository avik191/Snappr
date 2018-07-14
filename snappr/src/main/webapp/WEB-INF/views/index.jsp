<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>Snappr - Share pictures with your friends and family.</title>
<link rel="stylesheet" href="${css }/landing_page.css">

<script>

function validateForm() {
    var x = document.forms["myForm"]["email"].value;
    
    	var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        if(re.test(x))
        {
        	document.getElementById("error-text").style.display="none";
			return true;
        }
        else
        {
        	document.getElementById("error-text").style.display="block";
			return false;
        }
        
}
	
</script>

</head>
<body>
	<div class="main-container">
		<div class="left-section">
			<div class="logo">
				<img src="${images }/logo.png" alt="Avatar">
			</div>

			<div class="form-container">
				<div class="login-form-container">
					<h2>Login</h2>
					<form name="myForm" action="${contextRoot }/handleLogin" method="post" onsubmit="return validateForm()">
						<input type="text" placeholder="Email" name="email" onkeydown="document.getElementById('error-text').style.display = 'none' " required> 
						<p id="error-text" style="display:none">Enter proper email.</p>
						
						<input	type="password" placeholder="Password" name="pass" onkeydown="document.getElementById('error-text').style.display = 'none' "  required>
														
							<c:if test="${not empty error_message }">
								<p id="error-text">${error_message }</p>
							</c:if>
						<input type="submit" value="Login"> <a href="./">Forgot
							password?</a>
					</form>
					</br></br>
					<c:if test="${not empty message }">
								<p id="error-text">${message }</p>
					</c:if>
				</div>

			</div>
		</div>

		<div class="right-section">
			<img src="${images }/right_section_bg.png" alt="Avatar">

			<div class="text-and-buttons-container">
				<p id="caption-text">Share pictures with your friends and
					family.</p>
				<div class="buttons-container">
					<button class="btn btn-login btn-active">Login</button>
					<button class="btn btn-signup"><a href="${contextRoot }/signup">Sign Up</a></button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>