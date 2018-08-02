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




    elements.$commentButton.click(function(){
        elements.$commentBox.focus();
    });


    
})();

