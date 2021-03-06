package com.app.snappr.Service;

import java.util.List;

import com.app.snappr.Entity.User;

public interface UserService {

	public User getUserFromEmail(String email);
	public boolean addUser(User user);
	public boolean updateUser(User user);
	public List<User> getAllUsers(int start,int limit);
	User getUserFromId(int id);

	
}
