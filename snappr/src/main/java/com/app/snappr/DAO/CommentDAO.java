package com.app.snappr.DAO;

import java.util.List;

import com.app.snappr.Entity.Comment;

public interface CommentDAO {

	List<Comment> getCommentListFromPostId(int id);
	int addComment(int postId,int userId,String comment);
	boolean removeComment(Comment comment);
	Comment getCommentFromId(int commentId);
}
