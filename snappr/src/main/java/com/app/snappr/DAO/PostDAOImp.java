package com.app.snappr.DAO;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.snappr.Entity.Post;

@Repository
public class PostDAOImp implements PostDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Post> getPostListFromUserId(int id) {
		String sql = "FROM Post WHERE user_id = :id";
		try {
			return sessionFactory.getCurrentSession().createQuery(sql,Post.class).setParameter("id", id).list();
			}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
