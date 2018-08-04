package com.app.snappr.DAO;

import java.util.List;

import com.app.snappr.Entity.Post;

public interface PostDAO {

	List<Post> getPostListFromUserId(int id);
	boolean updateLike(Post post);
	Post getPostFromId(int id);
	boolean addPost(Post post);
}
