package com.app.snappr.DAO;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.snappr.Entity.Comment;
import com.app.snappr.Entity.Post;

@Repository
public class CommentDAOImp implements CommentDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Comment> getCommentListFromPostId(int id) {
		String sql = "FROM Comment WHERE post_id = :id";
		try {
			return sessionFactory.getCurrentSession().createQuery(sql,Comment.class).setParameter("id", id).list();
			}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int addComment(int postId, int userId, String comment) {
		// TODO Auto-generated method stub
		Comment c = new Comment();
		c.setPost_id(postId);
		c.setUser_id(userId);
		c.setDescription(comment);
		
		try {
			sessionFactory.getCurrentSession().save(c);
			return c.getId();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public boolean removeComment(Comment comment) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().remove(comment);;
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Comment getCommentFromId(int commentId) {
		// TODO Auto-generated method stub
		String sql = "FROM Comment WHERE id = :id";
		try {
			return sessionFactory.getCurrentSession().createQuery(sql,Comment.class).setParameter("id", commentId).getSingleResult();
			}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
