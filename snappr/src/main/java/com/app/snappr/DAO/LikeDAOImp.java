package com.app.snappr.DAO;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.snappr.Entity.Likes;
import com.app.snappr.Entity.Post;

@Repository
public class LikeDAOImp implements LikeDAO{
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Likes getLikeFromPostIdAndUserId(int postId, int userId) {
		// TODO Auto-generated method stub
		String sql = "FROM Likes where post_id = :pid and user_id = :uid";
		try {
			return sessionFactory.getCurrentSession().createQuery(sql,Likes.class).setParameter("pid",postId).setParameter("uid", userId).getSingleResult();
			}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean insertLike(Likes likes) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().save(likes);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteLike(Likes likes) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().remove(likes);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

}
