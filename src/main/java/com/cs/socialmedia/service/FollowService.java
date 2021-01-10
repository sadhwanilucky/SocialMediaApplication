package com.cs.socialmedia.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.cs.socialmedia.dao.FollowDAO;
import com.cs.socialmedia.dao.IFollowDAO;
import com.cs.socialmedia.exception.SocialMediaException;

public class FollowService implements IFollowService {
	private static final Logger logger = LogManager.getLogger(FollowService.class);
	private IFollowDAO followDAO = new FollowDAO();
	
	@Override
	public boolean followUser(String followerId, String followeeId) {
		logger.debug("Entering followUser method of FollowService");
		try {
			return followDAO.followUser(followerId.toLowerCase(), followeeId.toLowerCase());
		}catch(SocialMediaException ex) {
			logger.error(ex.getMessage());
			return false;
		}
		
	}

	@Override
	public boolean unfollowUser(String followerId, String followeeId) {
		logger.debug("Entering unfollowUser method of FollowService");
		try {
			return followDAO.unFollowUser(followerId.toLowerCase(), followeeId.toLowerCase());
		}catch(SocialMediaException ex) {
			logger.error(ex.getMessage());
			return false;
		}
	}

}
