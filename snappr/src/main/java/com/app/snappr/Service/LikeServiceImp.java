package com.app.snappr.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.snappr.DAO.LikeDAO;
import com.app.snappr.Entity.Likes;

@Service @Transactional
public class LikeServiceImp implements LikeService{

	@Autowired
	LikeDAO likeDAO;
	
	@Override
	public Likes getLikeFromPostIdAndUserId(int postId, int userId) {
		// TODO Auto-generated method stub
		return likeDAO.getLikeFromPostIdAndUserId(postId, userId);
	}

	@Override
	public boolean insertLike(Likes likes) {
		// TODO Auto-generated method stub
		return likeDAO.insertLike(likes);
	}

	@Override
	public boolean deleteLike(Likes likes) {
		// TODO Auto-generated method stub
		return likeDAO.deleteLike(likes);
	}

}
