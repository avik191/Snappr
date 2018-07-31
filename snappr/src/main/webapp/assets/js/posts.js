(function postIIFE(){
	var str = '';
	
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
    }

    elements.$postImage.click(function(){
        var src = $(this).attr("src");
        elements.$postPopupImage.attr("src", src);
        elements.$overlay.fadeIn(300);
    });

    elements.$popupCloser.click(function(){
        elements.$overlay.fadeOut(300);
    });

//    elements.$postDelete.click(function(){
//        var $parent = $(this).parent();
//        $parent.fadeOut(300);
//        setTimeout(function(){
//            $parent.remove();
//        }, 400);
//    });

    elements.$likeButton.click(function(){
        var status = $(this).attr("data-status");
        var $el = $(this);

        if(status == "active"){
            $el.attr("src", window.contextRoot+"/resources/images/like_inactive.png");
            $el.attr("data-status", "inactive");
            elements.$likeCount.text( +elements.$likeCount.text() - 1);
            console.log(window.contextRoot+"/resources/images/like_inactive.png");
        }
        else{
            $el.attr("src", window.contextRoot+"/resources/images/like.png");
            $el.attr("data-status", "active");
            elements.$likeCount.text( +elements.$likeCount.text() + 1);
        }
    });

    elements.$commentButton.click(function(){
        elements.$commentBox.focus();
    });


    
})();

