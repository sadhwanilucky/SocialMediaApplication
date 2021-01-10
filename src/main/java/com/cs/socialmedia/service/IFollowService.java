package com.cs.socialmedia.service;


/** This interface provides operations for Follow and UnFollow user
 * @author Lucky Sadhwani
 *
 */
public interface IFollowService {

	/**
	 * This method is used to follow user. 
	 * It returns true if operation is successful.
	 * It returns false if followerId or followeeId are same.
	 * @param followerId
	 * @param followeeId
	 * @return
	 */
	boolean followUser(String followerId, String followeeId) ;
	

	/**
	 * This method is used to unfollow user. 
	 * It returns true if followerId and followeeId are present.
	 *  It returns false if followerId or followeeId is absent.
	 * 
	 * @param followerId
	 * @param followeeId
	 * @return
	 */
	boolean unfollowUser(String followerId, String followeeId) ;

}
