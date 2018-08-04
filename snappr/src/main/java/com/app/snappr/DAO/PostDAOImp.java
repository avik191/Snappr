package com.app.snappr.DAO;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.snappr.Entity.Post;
import com.app.snappr.Entity.User;

@Repository
public class PostDAOImp implements PostDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Post> getPostListFromUserId(int id) {
		String sql = "FROM Post WHERE user_id = :id ORDER BY id DESC";
		try {
			return sessionFactory.getCurrentSession().createQuery(sql,Post.class).setParameter("id", id).list();
			}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateLike(Post post) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update(post);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Post getPostFromId(int id) {
		// TODO Auto-generated method stub
		String sql = "FROM Post where id = :id";
		try {
			return sessionFactory.getCurrentSession().createQuery(sql,Post.class).setParameter("id",id).getSingleResult();
			}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean addPost(Post post) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().save(post);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	
}
