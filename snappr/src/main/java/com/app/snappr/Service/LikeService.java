package com.app.snappr.Service;

import com.app.snappr.Entity.Likes;

public interface LikeService {
	Likes getLikeFromPostIdAndUserId(int postId,int userId);
	boolean insertLike(Likes likes);
	boolean deleteLike(Likes likes);
}
