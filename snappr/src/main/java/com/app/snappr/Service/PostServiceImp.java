package com.app.snappr.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.snappr.DAO.PostDAO;
import com.app.snappr.Entity.Post;

@Service @Transactional
public class PostServiceImp implements PostService{

	@Autowired
	PostDAO postDAO;
	
	@Override
	public List<Post> getPostListFromUserId(int id) {
		// TODO Auto-generated method stub
		return postDAO.getPostListFromUserId(id);
	}

}
