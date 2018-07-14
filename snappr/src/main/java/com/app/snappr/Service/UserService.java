package com.app.snappr.Service;

import com.app.snappr.Entity.User;

public interface UserService {

	public User getUserFromEmail(String email);
	public boolean addUser(User user);
	public boolean updateUser(User user);
}
