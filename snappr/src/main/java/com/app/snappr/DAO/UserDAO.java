package com.app.snappr.DAO;

import com.app.snappr.Entity.User;

public interface UserDAO {

	public User getUserFromEmail(String email);
}
