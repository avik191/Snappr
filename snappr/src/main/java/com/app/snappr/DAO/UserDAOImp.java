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

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().save(user);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

}
