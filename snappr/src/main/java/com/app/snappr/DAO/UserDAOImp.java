package com.app.snappr.DAO;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.snappr.Entity.User;

@Repository
public class UserDAOImp implements UserDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public User getUserFromEmail(String email) {
		String sql = "FROM User where email = :email";
		try {
			return sessionFactory.getCurrentSession().createQuery(sql,User.class).setParameter("email",email).getSingleResult();
			}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
