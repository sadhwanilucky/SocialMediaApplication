package com.cs.socialmedia.dao;

import java.util.List;

import com.cs.socialmedia.dto.Post;
import com.cs.socialmedia.exception.SocialMediaException;

/**
 * This interface provides operations for Post and Feed 
 * @author Lucky Sadhwani
 *
 */
public interface IPostDAO {
	
	/**This method is used to create the post.
	 * It returns true, if post is created successfully.
	 * It returns false, if any error occurred while creating the post.
	 * @param userId
	 * @param content
	 * @return
	 */
	boolean createPost(String userId, String content) throws SocialMediaException;

	/**This method is used to get the news feed for a user.
	 * It returns list of post.
	 * It returns null if user id not present. 
	 * It returns empty list if there are no post to display.
	 * @param userId
	 * @return
	 */
	List<Post> getNewsFeed(String userId) throws SocialMediaException;
	
}
