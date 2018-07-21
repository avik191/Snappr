(function postIIFE(){
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

    elements.$postDelete.click(function(){
        var $parent = $(this).parent();
        $parent.fadeOut(300);
        setTimeout(function(){
            $parent.remove();
        }, 400);
    });

    elements.$likeButton.click(function(){
        var status = $(this).attr("data-status");
        var $el = $(this);

        if(status == "active"){
            $el.attr("src", "./assets/img/like_inactive.png");
            $el.attr("data-status", "inactive");
            elements.$likeCount.text( +elements.$likeCount.text() - 1);
        }
        else{
            $el.attr("src", "./assets/img/like.png");
            $el.attr("data-status", "active");
            elements.$likeCount.text( +elements.$likeCount.text() + 1);
        }
    });

    elements.$commentButton.click(function(){
        elements.$commentBox.focus();
    });

    elements.$commentBox.keypress(function(ev){
        if(ev.charCode == 13){
            ev.preventDefault();
            submitComment();
        }
    });

    function submitComment(){
        var comment = elements.$commentBox.val();
        var userName = "Chris Martin"; //get current user's username
        elements.$commentBox.val("");
        renderComment(userName, comment);
    }


    function renderComment(userName, comment){
        var parent, child1, child2, child2, txt1, tx2, txt3;
        
        parent = document.createElement("div");
        parent.className = "post-comment post-comment-hidden";

        child1 = document.createElement("span");
        child1.className = "post-comment-username";
        txt1 = document.createTextNode(userName);
        child1.appendChild(txt1);
        
        child2 = document.createElement("span");
        child2.className = "post-comment-text";
        txt2 = document.createTextNode(comment);
        child2.appendChild(txt2);

        child3 = document.createElement("div");
        child3.className = "post-comment-delete";
        txt3 = document.createTextNode("Delete");
        child3.appendChild(txt3);

        parent.appendChild(child1);
        parent.appendChild(child2);
        parent.appendChild(child3);

        elements.$commentsContainer.prepend(parent);
        $(parent).fadeIn(300).removeClass("post-comment-hidden");


        $(child3).click(function(){
            var $parent = $(this).parent();
            $parent.fadeOut(300);
            setTimeout(function(){
                $parent.remove();
            }, 400);
        });
    }
})();

