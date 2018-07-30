package com.app.snappr.Service;

import java.util.List;

import com.app.snappr.Entity.Comment;

public interface CommentService {

	List<Comment> getCommentListFromPostId(int id);
	int addComment(int postId,int userId,String comment);
	boolean removeComment(Comment comment);
	Comment getCommentFromId(int commentId);


}
