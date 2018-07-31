<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="images" value="/resources/images" />
<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="${css }/posts.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Snappr - ${title }</title>

   
    <script> window.menu= '${title}';
    	window.contextRoot= '${contextRoot}';
    </script>
</head>
<body>
		<%@ include file="nav_left.jsp" %>
		<%@ include file="header.jsp" %>
		
		<c:if test="${showPosts == true }">
			<%@ include file="posts.jsp" %>		
		</c:if>
	
<script src="${js }/jquery-3.3.1.js"></script>
<script src="${js }/nav_left.js"></script>
</body>
</html>