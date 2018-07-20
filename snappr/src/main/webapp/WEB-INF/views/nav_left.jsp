<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.app.snappr.Entity.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Snappr - Home</title>
    <link rel="stylesheet" href="${css }/nav_left.css">
</head>
<body>


	<% User user = (User)session.getAttribute("loggedUser");%>
    <div class="nav-left-container">
        <div class="nav-left-user-details">
            <div class="nav-left-profile-pic">
                <img src="<%= user.getPath() %>" alt="Profile picture">
            </div>
            <div class="nav-left-name">
                <%= user.getName() %>
            </div>
            <div class="nav-left-user-name">
                @<%= user.getUsername() %>
            </div>
        </div>

        <div class="nav-left-link-container">
            <div id="Feeds" class="nav-left-link">
                <img src="${images }/feeds_icon.png" class="nav-left-icon" alt="Newsfeed icon">
                <a href="" class="nav-link-label nav-left-link-text">Feeds</a>
            </div>
            <div id="Posts" class="nav-left-link">
                <img src="${images }/posts_icon.png" class="nav-left-icon" alt="Post icon">
                <a href="" class="nav-link-count nav-left-link-text"> <%= user.getPosts() %></a>
                <a href="" class="nav-link-label nav-left-link-text">Posts</a>
            </div>
            <div id="Followers" class="nav-left-link ">
                <img src="${images }/followers-icon.png" class="nav-left-icon" alt="Followers icon">
                <a href="" class="nav-link-count nav-left-link-text"> <%= user.getFollowers() %></a>
                <a href="" class="nav-link-label nav-left-link-text">Followers</a>
            </div>
            <div id="Following" class="nav-left-link">
                <img src="${images }/following_icon.png" class="nav-left-icon" alt="Following icon">
                <a href="" class="nav-link-count nav-left-link-text"> <%= user.getFollowing() %></a>
                <a href="" class="nav-link-label nav-left-link-text">Following</a>
            </div>
        </div>

    </div>
    
    <script src="${js }/nav_left.js"></script>
    
</body>
</html>