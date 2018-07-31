var str2 = '';
var postId=0;
var elements = {
        $commentsContainer: $(".post-image-comments-container"),
        $overlay: $(".post-popup-overlay"),
        $popupCloser: $(".post-popup-closer"),
        $postPopupImage: $(".post-popup-image"),
        $postDelete: $(".post-comment-delete"),
        $postImage: $(".post-user-image"),
        $likeButton: $(".post-like-button img"),
        $likeCount: $(".post-like-count"),
        $commentButton: $(".post-comment-button"),
        $commentBox: $(".post-comment-box-container input"),
        $current_user_Id: $(".current_user_id"),
        $current_user_name: $(".current_user_name"),
    };


elements.$commentBox.keypress(function(ev){
    if(ev.charCode == 13){
        ev.preventDefault();
        submitComment();
    }
});


function submitComment(){
    var comment = elements.$commentBox.val();
    elements.$commentBox.val("");
    renderComment(comment);
}

function renderComment(comment){
	
	var addCommentUrl = '/snappr/addComment';
	$.ajax({
		url:addCommentUrl,
		method:"POST",
		data:{post_id:postId,
			  user_id:elements.$current_user_Id.val(),
			  descritpion: comment},
		cache:false,
		success:function(data){
				str2+= '<div class="post-comment">'+
				        '<span class="post-comment-username">'+
				        elements.$current_user_name.val()+
				        '</span><span class="post-comment-text">'+
				        comment+
				        '</span><div class="post-comment-delete" onclick="removeComment('+data+',this)">'+				            
					        'Delete</div>'+
				        
				    '</div>';
				
	    	   	 $('.post-image-comments-container').html(str2);

			
		}
	});
}

function removeComment(commentId,el)
{	
	console.log(commentId+" delete")
	var $parent = $(el).parent();
	
	var removeCommentUrl = '/snappr/removeComment';
	
	$.ajax({
		url:removeCommentUrl,
		method:"POST",
		data:{comment_id:commentId},
		cache:false,
		success:function(data){
			
			  $parent.fadeOut(300);
			    setTimeout(function(){
			        $parent.remove();
			    }, 400);
		}
	});
}

function loadComments(post_id,user_id,description,date,location)
{
		postId = post_id;
    	var s='';
    	var poster_name='';
    	var str = '';
    		str2 = '';
    	 var userNameUrl ='/snappr/getPosterName';
    	  $.ajax({
    	   url:userNameUrl,
    	   method:"POST",
    	   data:{id:user_id},
    	   cache:false,
    	   success:function(data)
    	   {
    		   poster_name = data;


    		   
    		   s+='<div class="post-image-user-name">'+ data +                          
               '</div>'+
               '<div class="post-image-caption">'+
                   description +  ' </div>'+                       
                   '<div class="post-image-date">'+
                   date+'</div>';
                   
                   
                   $('.post-image-caption-container').html(s);
    	   }
    	  });
    	  
    	  
    	
    	console.log(post_id);
    	
    	
    	
    	var jsonUrl ='/snappr/commentList';
    	  $.ajax({
    	   url:jsonUrl,
    	   method:"POST",
    	   data:{id:post_id},
    	   cache:false,
    	   success:function(data)
    	   {
    	     var j =1;
    	     var l =data.length;
    		   for (var i = 0; i < data.length; i++) { 

    			  
    			   str+= '<div class="post-comment">'+
    		        '<span class="post-comment-username">'+
    		        data[i].username+
    		        '</span><span class="post-comment-text">'+
    		        data[i].description+
    		        '</span><div class="post-comment-delete" onclick="removeComment('+data[i].id+',this)">'+				            
    			        'Delete</div>'+
    		        
    		    '</div>';
    			   

    	   		}
    	   	 
    	   	 $('.post-image-comments-container').html(str);
    	   	 str2=str;
    		   
    		   
    	   }
    	  });
    }
