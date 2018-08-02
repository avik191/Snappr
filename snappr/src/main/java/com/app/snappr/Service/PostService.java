package com.app.snappr.Service;

import java.util.List;

import com.app.snappr.Entity.Post;

public interface PostService {
	List<Post> getPostListFromUserId(int id);
	boolean updateLike(Post post);
	Post getPostFromId(int id);

}
