package com.app.snappr.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.snappr.DAO.UserDAO;
import com.app.snappr.Entity.User;

@Service @Transactional
public class UserServiceImp implements UserService{

	@Autowired
	UserDAO userDAO;
	
	@Override
	public User getUserFromEmail(String email) {
		// TODO Auto-generated method stub
		return userDAO.getUserFromEmail(email);
	}

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		return userDAO.addUser(user);
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return userDAO.updateUser(user);
	}

}
