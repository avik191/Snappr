package com.app.snappr.DAO;

import com.app.snappr.Entity.Likes;

public interface LikeDAO {

	Likes getLikeFromPostIdAndUserId(int postId,int userId);
	boolean insertLike(Likes likes);
	boolean deleteLike(Likes likes);
}
