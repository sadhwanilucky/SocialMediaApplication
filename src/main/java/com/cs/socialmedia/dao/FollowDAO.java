package com.cs.socialmedia.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cs.socialmedia.dto.Post;
import com.cs.socialmedia.dto.User;
import com.cs.socialmedia.exception.SocialMediaException;

public class FollowDAO implements IFollowDAO {
	private static final Logger logger = LogManager.getLogger(FollowDAO.class);

	@Override
	public boolean followUser(String followerId, String followeeId) throws SocialMediaException {
		logger.debug("Entering followUser method of FollowDAO");
		Optional<User> optionalFollowerUser = getUserByUserId(followerId);
		Optional<User> optionalFolloweeUser = getUserByUserId(followeeId);
		User user = null;
		//For Junit we have added below additional check as same validations is done in main class
		if (followerId.equals(followeeId)) {
			logger.debug("Follower and Followee user id can not be same");
			return false;
		}
		if (!optionalFolloweeUser.isPresent()) {
			user = new User(followeeId, new ArrayList<Post>());
			PostDAO.userPostMap.put(user, new HashSet<String>());
		}
		if (!optionalFollowerUser.isPresent()) {
			user = new User(followerId, new ArrayList<Post>());
			Set<String> followeeList = new HashSet<String>();
			followeeList.add(followeeId);
			PostDAO.userPostMap.put(user,followeeList);
		} else {
			Optional<Set<String>> optionalFolloweeList = getFolloweeListByUserId(followerId);
			Set<String> followeeList = optionalFolloweeList.get();
			if (followeeList.contains(followeeId)) {
				logger.debug("user " + followerId + " is already following " + followeeId);
				return false;
			} else {
				followeeList.add(followeeId);
			}
		}
		logger.debug("Exiting followUser method of FollowDAO");
		return true;
	}

	@Override
	public boolean unFollowUser(String followerId, String followeeId) throws SocialMediaException {
		logger.debug("Entering unFollowUser method of FollowDAO");
		Optional<User> optionalFollowerUser = getUserByUserId(followerId);
		Optional<User> optionalFolloweeUser = getUserByUserId(followeeId);
		if (!optionalFollowerUser.isPresent()) {
			throw new SocialMediaException("Follower User Id "+ followerId +" is not present");
		} else if (!optionalFolloweeUser.isPresent()) {
			throw new SocialMediaException("Followee User Id "+ followeeId +" is not present");
		} else {
			Optional<Set<String>> optionalFolloweeList = getFolloweeListByUserId(followerId);
			Set<String> followeeList = optionalFolloweeList.get();
			if (followeeList.contains(followeeId)) {
				followeeList.remove(followeeId);
			} else {
				throw new SocialMediaException("User Id " + followerId + " is not following " + followeeId);
			}
			logger.debug("Exiting unFollowUser method of FollowDAO");
			return true;
		}
	}

	private Optional<Set<String>> getFolloweeListByUserId(String userId) {
		return PostDAO.userPostMap.entrySet().stream()
				.filter(entry -> (entry.getKey().getId()).equals(userId)).findFirst()
				.map(Map.Entry::getValue);
	}

	private Optional<User> getUserByUserId(String userId) {
		return PostDAO.userPostMap.entrySet().stream()
				.filter(entry -> (entry.getKey().getId()).equals(userId)).findFirst()
				.map(Map.Entry::getKey);
	}
}
