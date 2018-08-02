<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.app.snappr.Entity.User" %>


<%
	User current_user = (User)session.getAttribute("loggedUser");

%>

<div class="posts-main-container">
            <div class="posts-wrapper">
                <div class="posts-heading"><h2>Posts</h2></div>

                <div class="posts-container">
                
                	<c:forEach items="${postList }" var="post">
                	
                    <div class="post">
                        <img src="${images }/transparent.png" class="post-transparent-image" alt="Transparent overlay">
                        <img src="${post.path }" class="post-user-image" alt="Some image" srcset="" onclick="loadComments('${post.id}','${post.user_id}','${post.description}','${post.date}','${post.location}','${post.likes}')">             
                    </div>
                    
                    </c:forEach>
          
                </div>


                <div class="post-loader">
                    <div></div>
                    <div></div>
                    <div></div>
                </div>
            </div>
        </div>


        <div class="post-popup-overlay">
            <div class="post-popup-closer"></div>
            <div class="post-popup-container">

                <div class="post-popup-left-section">
                    <img src="${images }/building.jpeg" alt="Image" class="post-popup-image">
                </div>

                <div class="post-popup-right-section">

                    <div class="post-image-caption-container">
                       
                    </div>

                    <div class="post-image-comments-container">
                         
                    </div>

                    <div class="post-like-comment-button-container">
                        <div class="post-like-button">
                            <img src="${images }/like_inactive.png" alt="Like button" data-status="inactive" tabindex="-1">
                            <div class="post-like-count"></div>
                        </div>
                        <div class="post-comment-button">
                            <img src="${images }/speech-bubble-outline-of-rectangular-shape.png" alt="Comment Button" tabindex="-1">
                        </div>
                    </div>

                    <div class="post-comment-box-container">
                        <input type="text" placeholder="Write a comment..">
                    </div>
                    
                        <input type="hidden" value="<%= current_user.getId()%>" class="current_user_id">
                   		<input type="hidden" value="<%= current_user.getName() %>" class="current_user_name">
                   		
                    
                </div>
            </div>
        </div>

        <script src="${js }/posts.js"></script>
        <script src="${js }/posts_popup.js"></script>
