package com.app.snappr.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.snappr.DAO.CommentDAO;
import com.app.snappr.Entity.Comment;

@Service @Transactional
public class CommentServieImp implements CommentService{

	@Autowired
	CommentDAO commentDAO;
	
	@Override
	public List<Comment> getCommentListFromPostId(int id) {
		// TODO Auto-generated method stub
		return commentDAO.getCommentListFromPostId(id);
	}

	@Override
	public int addComment(int postId, int userId, String comment) {
		// TODO Auto-generated method stub
		return commentDAO.addComment(postId, userId, comment);
	}

	@Override
	public boolean removeComment(Comment comment) {
		// TODO Auto-generated method stub
		return commentDAO.removeComment(comment);
	}

	@Override
	public Comment getCommentFromId(int commentId) {
		// TODO Auto-generated method stub
		return commentDAO.getCommentFromId(commentId);
	}

}
