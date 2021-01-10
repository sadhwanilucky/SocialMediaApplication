package com.cs.socialmedia.service;

import java.util.List;

import com.cs.socialmedia.dto.Post;

/**
 * This interface provides operations for Post and Feed 
 * @author Lucky Sadhwani
 *
 */
public interface IPostService {

	/**This method is used to create the post.
	 * @param userId
	 * @param content
	 * @return It returns true, if post is created successfully otherwise false.
	 */
	boolean createPost(String userId, String content) ;

	/**This method is used to get the news feed for a user.
	 * It returns list of post.
	 * It returns null if user id not present. 
	 * It returns empty list if there are no post to display.
	 * @param userId
	 * @return List<Post> if present , null if user id not present and empty if no post to show.
	 */
	List<Post> getNewsFeed(String userId) ;
	
}
